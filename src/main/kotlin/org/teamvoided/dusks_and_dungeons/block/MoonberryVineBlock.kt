package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.multifaceRemoveFace


// TODO(1.0) ask dusk if the berry count is right
class MoonberryVineBlock(properties: Properties) : MultifaceBlock(properties), SimpleWaterloggedBlock, BonemealableBlock {

    public override fun codec(): MapCodec<MoonberryVineBlock> = CODEC

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false).setValue(BERRIES, 0)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, BERRIES)
    }

    override fun canBeReplaced(state: BlockState, ctx: BlockPlaceContext): Boolean {
        return ctx.itemInHand.`is`(DnDBlocks.MOONBERRY_VINE.asItem())
    }

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        return state.getValue(BERRIES) < 2
    }

    override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean {
        return level.isNight
    }

    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        level.setBlock(pos, state.setValue(BERRIES, state.getValue(BERRIES) + 1), 2)
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }
        return if (!hasAnyFace(state))
            Blocks.AIR.defaultBlockState()
        else {
            if (hasFace(state, direction) && !canGrowOnOrOverride(level, direction, neighborPos, neighborState))
                multifaceRemoveFace(state, getFaceProperty(direction))
            else
                state
        }
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        var canSurvive = false
        for (direction in DIRECTIONS) {
            if (hasFace(state, direction)) {
                val relativePos = pos.relative(direction)
                if (!canGrowOnOrOverride(level, direction, relativePos, level.getBlockState(relativePos))) {
                    return false
                }
                canSurvive = true
            }
        }
        return canSurvive
    }

    override fun isValidStateForPlacement(
        level: BlockGetter, state: BlockState, pos: BlockPos, dir: Direction,
    ): Boolean {
        return if (this.isFaceSupported(dir) && (!state.`is`(this) || !hasFace(state, dir))) {
            val blockPos = pos.relative(dir)
            canGrowOnOrOverride(level, dir, blockPos, level.getBlockState(blockPos))
        } else false
    }

    fun canGrowOnOrOverride(
        level: BlockGetter, direction: Direction, pos: BlockPos, state: BlockState,
    ): Boolean {
        return isFaceFull(state.getBlockSupportShape(level, pos), direction.opposite)
                || isFaceFull(state.getCollisionShape(level, pos), direction.opposite)
                || level.getBlockState(pos).`is`(DnDBlockTags.SUPPORTS_MOONBERRY)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun useItemOn(
        stack: ItemStack, state: BlockState, level: Level, pos: BlockPos,
        player: Player, hand: InteractionHand, hit: BlockHitResult,
    ): ItemInteractionResult {
        val hasBerries = state.getValue(BERRIES) == 2
        return if (!hasBerries && stack.`is`(Items.BONE_MEAL))
            ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION
        else
            super.useItemOn(stack, state, level, pos, player, hand, hit)
    }

    override fun useWithoutItem(
        state: BlockState, level: Level, pos: BlockPos, player: Player, hit: BlockHitResult,
    ): InteractionResult {
        val berries = state.getValue(BERRIES)
        if (berries > 1) {
            val amount = 1 + level.random.nextInt(2) + (berries / 3)
            popResource(level, pos, ItemStack(DnDItems.MOONBERRIES, amount))
            level.playSound(
                null, pos,
                SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS,
                1.0f, 0.8f + level.random.nextFloat() * 0.4f
            )
            val newState = state.setValue(BERRIES, 0)
            level.setBlock(pos, newState, 2)
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState))
            return InteractionResult.sidedSuccess(level.isClientSide)
        } else {
            return super.useWithoutItem(state, level, pos, player, hit)
        }
    }

    override fun isRandomlyTicking(state: BlockState): Boolean {
        return state.getValue(BERRIES) < 2
    }

    override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        val berries = state.getValue(BERRIES)
        if (berries < 3 && random.nextInt(5) == 0 && level.isNight) {
            val blockState = state.setValue(BERRIES, berries + 1)
            level.setBlock(pos, blockState, 2)
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState))
        }
    }

    override fun getSpreader(): MultifaceSpreader = MultifaceSpreader(this)

    companion object {

        val CODEC: MapCodec<MoonberryVineBlock> = simpleCodec(::MoonberryVineBlock)

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val BERRIES: IntegerProperty = IntegerProperty.create("berries", 0, 2)

        fun Properties.moonberryLightLevel(luminanceLow: Int, luminance: Int): Properties = lightLevel { state ->
            if (hasAnyFace(state) && state.getValue(BERRIES) > 0)
                if (state.getValue(BERRIES) > 1) luminance else luminanceLow
            else
                0
        }

    }
}