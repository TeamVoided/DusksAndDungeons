package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class BigScaffoldingBlock(settings: Properties) : Block(settings), SimpleWaterloggedBlock {
    public override fun codec(): MapCodec<BigScaffoldingBlock> = CODEC

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(BlockStateProperties.WATERLOGGED, false)
                .setValue(BOTTOM, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(BlockStateProperties.WATERLOGGED, BOTTOM)
    }

    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext,
    ): VoxelShape {
        return if (!context.isHoldingItem(state.block.asItem()))
            if (state.getValue(BOTTOM))
                UNSTABLE_SHAPE
            else
                STABLE_SHAPE
        else
            Shapes.block()

    }

    override fun getInteractionShape(state: BlockState, world: BlockGetter, pos: BlockPos): VoxelShape = Shapes.block()

    override fun canBeReplaced(state: BlockState, context: BlockPlaceContext): Boolean {
        return context.itemInHand.`is`(asItem())
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val blockPos = ctx.clickedPos
        val world = ctx.level
        return defaultBlockState()
            .setValue(BlockStateProperties.WATERLOGGED, world.getFluidState(blockPos).type == Fluids.WATER)
            .setValue(BOTTOM, shouldHaveBottom(world, blockPos))
    }

    override fun playerWillDestroy(world: Level, pos: BlockPos, state: BlockState, player: Player): BlockState {
        if (player.mainHandItem.`is`(asItem()) && world.getBlockState(pos.above()).`is`(this)) {
            world.scheduleTick(pos.above(), this, 1)
        }
        return super.playerWillDestroy(world, pos, state, player)
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos,
    ): BlockState {
        val updatedState = super.updateShape(state, direction, neighborState, world, pos, neighborPos)
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        return if (direction == Direction.DOWN)
            updatedState.setValue(BOTTOM, shouldHaveBottom(world, pos))
        else
            updatedState
    }

    override fun tick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        Direction.entries.forEach {
            if (it != Direction.DOWN && world.getBlockState(pos.relative(it)).`is`(this)) {
                world.scheduleTick(pos.relative(it), this, 1)
            }
        }
        world.destroyBlock(pos, true)
    }

    override fun getCollisionShape(
        state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext,
    ): VoxelShape {
        return if (ctx.isAbove(Shapes.block(), pos, true) && !ctx.isDescending)
            STABLE_SHAPE
        else if (state.getValue(BOTTOM) && ctx.isAbove(BELOW_BLOCK, pos, true))
            UNSTABLE_BOTTOM_SHAPE
        else
            Shapes.empty()
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(BlockStateProperties.WATERLOGGED))
            Fluids.WATER.getSource(false)
        else
            super.getFluidState(state)
    }

    fun shouldHaveBottom(world: BlockGetter, pos: BlockPos): Boolean {
        val downState = world.getBlockState(pos.below())
        return !(downState.`is`(this) || downState.isFaceSturdy(world, pos.below(), Direction.UP))
    }

    companion object {

        val CODEC: MapCodec<BigScaffoldingBlock> = simpleCodec(::BigScaffoldingBlock)
        val BOTTOM: BooleanProperty = BlockStateProperties.BOTTOM

        val UNSTABLE_BOTTOM_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0)
        val BELOW_BLOCK: VoxelShape = Shapes.block().move(0.0, -1.0, 0.0)
        val STABLE_SHAPE: VoxelShape = Shapes.or(
            box(0.0, 12.0, 0.0, 16.0, 16.0, 16.0),
            leg(),
            leg(12.0),
            leg(0.0, 12.0),
            leg(12.0, 12.0)
        )
        val UNSTABLE_SHAPE: VoxelShape = Shapes.or(UNSTABLE_BOTTOM_SHAPE, STABLE_SHAPE)

        fun leg(offsetX: Double = 0.0, offsetZ: Double = 0.0): VoxelShape {
            return box(
                offsetX, 0.0, offsetZ,
                offsetX + 4, 16.0, offsetZ + 4
            )
        }

    }
}
