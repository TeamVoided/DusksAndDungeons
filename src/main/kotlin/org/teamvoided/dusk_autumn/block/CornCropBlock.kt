package org.teamvoided.dusk_autumn.block

import net.minecraft.block.*
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.RavagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.*
import org.teamvoided.dusk_autumn.block.not_blocks.TripleBlockSection
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import java.lang.Integer.min

class CornCropBlock(settings: Settings) : TripleTallPlantBlock(settings), Fertilizable {

    init {
        this.defaultState = stateManager.defaultState.with(SECTION, TripleBlockSection.BOTTOM)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(AGE)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return this.defaultState
    }

    public override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val age = state.get(AGE)
        if (age % 2 != 0) {
            return FULL_SHAPE
        }
        val section = state.get(SECTION)
        return if (
            (age == 0 && section == TripleBlockSection.BOTTOM) ||
            (age == 2 && section == TripleBlockSection.MIDDLE) ||
            (age == 4 && section == TripleBlockSection.TOP)
        ) HALF_SHAPE
        else FULL_SHAPE
    }

    public override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        val blockSection = state.get(SECTION)
        return if (heightAtAge(state.get(AGE), 3)) {
            super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
        } else if (
            heightAtAge(state.get(AGE), 2) &&
            (direction.axis == Direction.Axis.Y) &&
            !(blockSection == TripleBlockSection.MIDDLE && direction == Direction.UP) &&
            !state.canPlaceAt(world, pos)
        ) {
            Blocks.AIR.defaultState
        } else {
            if (state.canPlaceAt(world, pos)) state
            else Blocks.AIR.defaultState
        }
    }

    public override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        return if (isLowestSection(state) && !hasEnoughLight(world, pos)) false
        else super.canPlaceAt(state, world, pos)
    }

    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean =
        floor.isOf(Blocks.FARMLAND)

    public override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (entity is RavagerEntity && world.gameRules.getBooleanValue(GameRules.DO_MOB_GRIEFING)) {
            world.breakBlock(pos, true, entity)
        }
        if (entity is PlayerEntity && !entity.isCreative) {
            val mult = ((MAX_AGE - state.get(AGE)) / MAX_AGE.toDouble())
            entity.setMovementMultiplier(state, CornMazeBlock.cornMovementMultiplier.multiply(mult))
        }

        super.onEntityCollision(state, world, pos, entity)
    }

    public override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean = false

    override fun onPlaced(
        world: World,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        itemStack: ItemStack
    ) {
    }

    public override fun getRandomTicks(state: BlockState): Boolean =
        state.get(SECTION) == TripleBlockSection.BOTTOM && !this.isMaxAge(state)

    public override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        val moisture = CropBlock.getAvailableMoisture(this, world, pos)
        val chance = random.nextInt((25f / moisture).toInt() + 1) == 0
        if (chance) {
            this.grow(world, state, pos, 1)
        }
    }

    fun withAge(age: Int): BlockState {
        return if (age >= 6) defaultCornPlant().defaultState else defaultCornCrop().defaultState
    }


    private fun grow(world: ServerWorld, state: BlockState, pos: BlockPos, amount: Int) {
        val newAge = min((state.get(AGE) + amount), MAX_AGE)
        if (this.canGrow(world, pos, state, newAge)) {
            val blockState = withAge(newAge).withIfExists(AGE, newAge)
            world.setBlockState(pos, blockState, 2)
            val height = heightAtAge(newAge)
            if (height >= 2) {
                world.setBlockState(pos.up(), blockState.with(SECTION, TripleBlockSection.MIDDLE), 3)
                if (height >= 3) {
                    world.setBlockState(pos.up(2), blockState.with(SECTION, TripleBlockSection.TOP), 3)
                }
            }
        }
    }

    private fun canGrow(world: WorldView, pos: BlockPos, state: BlockState, age: Int): Boolean {
        return !this.isMaxAge(state) &&
                hasEnoughLight(world, pos) &&
                (age > 1 || canGrowInto(world, pos.up()))
    }

    private fun isMaxAge(state: BlockState): Boolean {
        return state.get(AGE) >= MAX_AGE
    }

    private fun getLowerHalf(world: WorldView, pos: BlockPos, state: BlockState): LowerHalfInfo? {
        if (isLowestSection(state)) {
            return LowerHalfInfo(pos, state)
        } else {
            val blockPosDown = pos.down(if (state.get(SECTION) == TripleBlockSection.MIDDLE) 1 else 2)
            val blockState = world.getBlockState(blockPosDown)
            return if (isLowestSection(blockState)) LowerHalfInfo(blockPosDown, blockState)
            else null
        }
    }

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState): Boolean {
        val lowerHalfInfo = this.getLowerHalf(world, pos, state)
        return if (lowerHalfInfo == null) false
        else this.canGrow(world, lowerHalfInfo.pos, lowerHalfInfo.state, lowerHalfInfo.state.get(AGE) + 1)
    }

    override fun canFertilize(world: World, random: RandomGenerator, pos: BlockPos, state: BlockState): Boolean = true

    override fun fertilize(world: ServerWorld, random: RandomGenerator, pos: BlockPos, state: BlockState) {
        val lowerHalfInfo = this.getLowerHalf(world, pos, state)
        if (lowerHalfInfo != null) {
            this.grow(world, lowerHalfInfo.state, lowerHalfInfo.pos, 1)
        }
    }

    private class LowerHalfInfo(val pos: BlockPos, val state: BlockState) {
        fun pos(): BlockPos {
            return this.pos
        }

        fun state(): BlockState {
            return this.state
        }
    }

    companion object {
        const val MAX_AGE: Int = 6
        val AGE: IntProperty = Properties.AGE_5
        private val FULL_SHAPE =
            VoxelShapes.fullCube()
        private val HALF_SHAPE =
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)

        private fun defaultCornCrop(): Block = DnDFloraBlocks.CORN_CROP
        private fun defaultCornPlant(): Block = DnDFloraBlocks.CORN

        private fun canGrowInto(world: WorldView, pos: BlockPos): Boolean {
            val blockState = world.getBlockState(pos)
            return blockState.isAir || blockState.isOf(defaultCornCrop())
        }

        private fun hasEnoughLight(world: WorldView, pos: BlockPos): Boolean {
            return CropBlock.hasEnoughLight(world, pos)
        }

        private fun isLowestSection(state: BlockState): Boolean {
            return state.isOf(defaultCornCrop()) && state.get(SECTION) == TripleBlockSection.BOTTOM
        }

        private fun heightAtAge(age: Int, height: Int): Boolean {
            return ((height == 1 && age < 2) ||
                    (height == 2 && age < 4) ||
                    (height == 3))
        }

        private fun heightAtAge(age: Int): Int {
            return if (age < 2) 1 else if (age < 4) 2 else 3
        }
    }
}