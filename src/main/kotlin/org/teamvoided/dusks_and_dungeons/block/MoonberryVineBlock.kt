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
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.MultifaceBlock
import net.minecraft.world.level.block.MultifaceSpreader
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems

class MoonberryVineBlock(settings: Properties) : MultifaceBlock(settings), SimpleWaterloggedBlock, BonemealableBlock {
    public override fun codec(): MapCodec<MoonberryVineBlock> = CODEC

    init {
        this.registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false).setValue(BERRIES, 0))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, BERRIES)
    }

    override fun canBeReplaced(state: BlockState, context: BlockPlaceContext): Boolean =
        context.itemInHand.`is`(DnDBlocks.MOONBERRY_VINE.asItem())

    override fun isValidBonemealTarget(world: LevelReader, pos: BlockPos, state: BlockState): Boolean = state.getValue(BERRIES) < 2
    override fun isBonemealSuccess(world: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean =
        world.isNight

    override fun performBonemeal(world: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        world.setBlock(pos, state.setValue(BERRIES, state.getValue(BERRIES) + 1), 2)
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }
        return if (!hasAnyFace(state)) {
            Blocks.AIR.defaultBlockState()
        } else {
            if (hasFace(state, direction) &&
                !canGrowOnOrOveride(world, direction, neighborPos, neighborState)
            ) removeFace(state, getFaceProperty(direction)) else state
        }
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        var bl = false
        val var5 = DIRECTIONS
        val var6 = var5.size

        for (var7 in 0 until var6) {
            val direction = var5[var7]
            if (hasFace(state, direction)) {
                val blockPos = pos.relative(direction)
                if (!canGrowOnOrOveride(world, direction, blockPos, world.getBlockState(blockPos))
                ) {
                    return false
                }

                bl = true
            }
        }

        return bl
    }

    override fun isValidStateForPlacement(view: BlockGetter, state: BlockState, pos: BlockPos, dir: Direction): Boolean {
        return if (this.isFaceSupported(dir) && (!state.`is`(this) || !hasFace(state, dir))) {
            val blockPos = pos.relative(dir)
            canGrowOnOrOveride(view, dir, blockPos, view.getBlockState(blockPos))
        } else false
    }

    private fun canGrowOnOrOveride(world: BlockGetter, direction: Direction, pos: BlockPos, state: BlockState): Boolean {
        return (isFaceFull(state.getBlockSupportShape(world, pos), direction.opposite)
                || isFaceFull(state.getCollisionShape(world, pos), direction.opposite)
                || world.getBlockState(pos).`is`(DnDBlockTags.MOONBERRY_CAN_PLACE_ON))
    }

    override fun getFluidState(state: BlockState): FluidState =
        if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)

    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        world: Level,
        pos: BlockPos,
        entity: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): ItemInteractionResult {
        val bl = state.getValue(BERRIES) == 2
        return if (!bl && stack.`is`(Items.BONE_MEAL)) ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION
        else super.useItemOn(stack, state, world, pos, entity, hand, hitResult)
    }

    override fun useWithoutItem(
        state: BlockState, world: Level, pos: BlockPos, entity: Player, hitResult: BlockHitResult
    ): InteractionResult {
        val i = state.getValue(BERRIES)
        val bl = i == 3
        if (i > 1) {
            val j = 1 + world.random.nextInt(2)
            popResource(world, pos, ItemStack(DnDItems.MOONBERRIES, j + (if (bl) 1 else 0)))
            world.playSound(
                null,
                pos,
                SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,
                SoundSource.BLOCKS,
                1.0f,
                0.8f + world.random.nextFloat() * 0.4f
            )
            val blockState = state.setValue(BERRIES, 0)
            world.setBlock(pos, blockState, 2)
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockState))
            return InteractionResult.sidedSuccess(world.isClientSide)
        } else {
            return super.useWithoutItem(state, world, pos, entity, hitResult)
        }
    }

    override fun isRandomlyTicking(state: BlockState): Boolean = state.getValue(BERRIES) < 2
    override fun randomTick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        val berries = state.getValue(BERRIES)
        if (berries < 3 && random.nextInt(5) == 0 && world.isNight) {
            val blockState = state.setValue(BERRIES, berries + 1)
            world.setBlock(pos, blockState, 2)
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState))
        }
    }

    override fun getSpreader(): MultifaceSpreader = MultifaceSpreader(this)

    companion object {
        val CODEC = simpleCodec(::MoonberryVineBlock)
        val WATERLOGGED = BlockStateProperties.WATERLOGGED
        val BERRIES = IntegerProperty.create("berries", 0, 2)
        fun Properties.moonberryLuminance(luminanceLow: Int, luminance: Int): Properties = this.lightLevel { state ->
            if (hasAnyFace(state) && state.getValue(BERRIES) > 0) {
                if (state.getValue(BERRIES) > 1) luminance else luminanceLow
            } else 0
        }
    }
}
