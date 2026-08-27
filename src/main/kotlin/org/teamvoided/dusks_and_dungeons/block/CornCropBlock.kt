package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.monster.Ravager
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.not_blocks.TripleBlockSection
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.cropGetGrowthSpeed
import org.teamvoided.dusks_and_dungeons.util.cropHasSufficientLight
import java.lang.Integer.min

class CornCropBlock(settings: Properties) : TripleTallPlantBlock(settings), BonemealableBlock {
    init {
        this.registerDefaultState(stateDefinition.any().setValue(SECTION, TripleBlockSection.BOTTOM))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(AGE)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState = this.defaultBlockState()

    public override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ): VoxelShape {
        val age = state.getValue(AGE)
        if (age % 2 != 0) {
            return FULL_SHAPE
        }
        val section = state.getValue(SECTION)
        return if (
            (age == 0 && section == TripleBlockSection.BOTTOM) ||
            (age == 2 && section == TripleBlockSection.MIDDLE) ||
            (age == 4 && section == TripleBlockSection.TOP)
        ) HALF_SHAPE
        else FULL_SHAPE
    }

    public override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        val blockSection = state.getValue(SECTION)
        return if (heightAtAge(state.getValue(AGE), 3)) {
            super.updateShape(state, direction, neighborState, world, pos, neighborPos)
        } else if (
            heightAtAge(state.getValue(AGE), 2) &&
            (direction.axis == Direction.Axis.Y) &&
            !(blockSection == TripleBlockSection.MIDDLE && direction == Direction.UP) &&
            !state.canSurvive(world, pos)
        ) {
            Blocks.AIR.defaultBlockState()
        } else {
            if (state.canSurvive(world, pos)) state
            else Blocks.AIR.defaultBlockState()
        }
    }

    override fun neighborUpdatesAboveAndBelow(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState
    ): Boolean {
        if (heightAtAge(state.getValue(AGE), 3))
            return super.neighborUpdatesAboveAndBelow(state, direction, neighborState)
        else if (heightAtAge(state.getValue(AGE), 2)) {
            val section = state.getValue(SECTION)
            return ((direction == Direction.UP && section != TripleBlockSection.MIDDLE && neighborState.`is`(this)) ||
                    (direction == Direction.DOWN && section != TripleBlockSection.BOTTOM && neighborState.`is`(this)))
        } else return true
    }

    public override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        return if (isLowestSection(state) && !hasEnoughLight(world, pos)) false
        else super.canSurvive(state, world, pos)
    }

    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean =
        floor.`is`(Blocks.FARMLAND)

    public override fun entityInside(state: BlockState, world: Level, pos: BlockPos, entity: Entity) {
        if (entity is Ravager && world.gameRules.getBoolean(GameRules.RULE_MOBGRIEFING)) {
            world.destroyBlock(pos, true, entity)
        }
        super.entityInside(state, world, pos, entity)
    }

    public override fun canBeReplaced(state: BlockState, context: BlockPlaceContext): Boolean = false

    override fun setPlacedBy(world: Level, pos: BlockPos, state: BlockState, placer: LivingEntity?, itemStack: ItemStack) =
        Unit

    public override fun isRandomlyTicking(state: BlockState): Boolean =
        state.getValue(SECTION) == TripleBlockSection.BOTTOM && !this.isMaxAge(state)

    public override fun randomTick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        val moisture = cropGetGrowthSpeed(this, world, pos)
        val chance = random.nextInt((25f / moisture).toInt() + 1) == 0
        if (chance) {
            this.grow(world, state, pos, 1)
        }
    }

    fun withAge(age: Int): BlockState =
        if (age >= 6) defaultCornPlant().defaultBlockState() else defaultCornCrop().defaultBlockState()


    private fun grow(world: ServerLevel, state: BlockState, pos: BlockPos, amount: Int) {
        val newAge = min((state.getValue(AGE) + amount), MAX_AGE)
        if (this.canGrow(world, pos, state, newAge)) {
            val blockState = withAge(newAge).trySetValue(AGE, newAge)
            world.setBlock(pos, blockState, 2)
            //val height = heightAtAge(newAge)
            //if (height >= 2) {
            //    world.setBlockState(pos.up(), blockState.with(SECTION, TripleBlockSection.MIDDLE), 2)
            //    if (height >= 3) {
            //        world.setBlockState(pos.up(2), blockState.with(SECTION, TripleBlockSection.TOP), 2)
            //    }
            //}
        }
    }

    private fun canGrow(world: LevelReader, pos: BlockPos, state: BlockState, age: Int): Boolean {
        return !this.isMaxAge(state) &&
                hasEnoughLight(world, pos) &&
                (age > 1 || canGrowInto(world, pos.above()))
    }

    private fun isMaxAge(state: BlockState): Boolean = state.getValue(AGE) >= MAX_AGE

    private fun getLowerHalf(world: LevelReader, pos: BlockPos, state: BlockState): LowerHalfInfo? {
        if (isLowestSection(state)) {
            return LowerHalfInfo(pos, state)
        } else {
            val blockPosDown = pos.below(if (state.getValue(SECTION) == TripleBlockSection.MIDDLE) 1 else 2)
            val blockState = world.getBlockState(blockPosDown)
            return if (isLowestSection(blockState)) LowerHalfInfo(blockPosDown, blockState)
            else null
        }
    }

    override fun isValidBonemealTarget(world: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        val lowerHalfInfo = this.getLowerHalf(world, pos, state)
        return if (lowerHalfInfo == null) false
        else this.canGrow(world, lowerHalfInfo.pos, lowerHalfInfo.state, lowerHalfInfo.state.getValue(AGE) + 1)
    }

    override fun isBonemealSuccess(world: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean = true

    override fun performBonemeal(world: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        val lowerHalfInfo = this.getLowerHalf(world, pos, state)
        if (lowerHalfInfo != null) {
            this.grow(world, lowerHalfInfo.state, lowerHalfInfo.pos, 1)
        }
    }

    private class LowerHalfInfo(val pos: BlockPos, val state: BlockState) {
        fun pos(): BlockPos = this.pos
        fun state(): BlockState = this.state
    }

    companion object {
        const val MAX_AGE: Int = 6
        val AGE: IntegerProperty = BlockStateProperties.AGE_5
        private val FULL_SHAPE =
            Shapes.block()
        private val HALF_SHAPE =
            box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)

        private fun defaultCornCrop(): Block = DnDBlocks.CORN_CROP
        private fun defaultCornPlant(): Block = DnDBlocks.CORN
        private fun canGrowInto(world: LevelReader, pos: BlockPos): Boolean {
            val blockState = world.getBlockState(pos)
            return blockState.isAir || blockState.`is`(defaultCornCrop())
        }

        private fun hasEnoughLight(world: LevelReader, pos: BlockPos): Boolean = cropHasSufficientLight(world, pos)
        private fun isLowestSection(state: BlockState): Boolean =
            state.`is`(defaultCornCrop()) && state.getValue(SECTION) == TripleBlockSection.BOTTOM

        private fun heightAtAge(age: Int, height: Int): Boolean {
            return ((height == 1 && age < 2) ||
                    (height == 2 && age < 4) ||
                    (height == 3))
        }

        private fun heightAtAge(age: Int): Int = if (age < 2) 1 else if (age < 4) 2 else 3
    }
}