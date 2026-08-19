package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RedstoneTorchBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty

// TODO move to Variance
// HAHA SIKE
class BigRedstoneLanternBlock(settings: Properties) : BigLanternBlock(settings) {
    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(WATERLOGGED, false)
                .setValue(LIT, true)
        )
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val state = super.getStateForPlacement(ctx)
        return state.setValue(LIT, !hasNeighborSignal(ctx.level, ctx.clickedPos, state))
    }

    override fun onPlace(state: BlockState, world: Level, pos: BlockPos, oldState: BlockState, notify: Boolean) {
        for (direction in Direction.entries) {
            world.updateNeighborsAt(pos.relative(direction), this)
        }
    }

    override fun onRemove(state: BlockState, world: Level, pos: BlockPos, newState: BlockState, moved: Boolean) {
        if (moved) return
        for (direction in Direction.entries) {
            world.updateNeighborsAt(pos.relative(direction), this)
        }
    }


    override fun neighborChanged(
        blockState: BlockState,
        level: Level,
        blockPos: BlockPos,
        block: Block,
        blockPos2: BlockPos,
        bl: Boolean
    ) {
        if (blockState.getValue(LIT) == hasNeighborSignal(level, blockPos, blockState) &&
            !level.blockTicks.willTickThisTick(blockPos, this)
        ) {
            level.scheduleTick(blockPos, this, 2)
        }
    }

    override fun getSignal(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        direction: Direction
    ): Int {
        return if (blockState.getValue(LIT) && blockState.getValue(FACING) != direction) 15
        else 0
    }

    override fun getDirectSignal(state: BlockState, world: BlockGetter, pos: BlockPos, dir: Direction): Int {
        return if (dir == state.getValue(FACING).opposite) state.getSignal(world, pos, dir) else 0
    }

    override fun tick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        world.setBlock(
            pos,
            state.setValue(LIT, !(state.getValue(LIT) && hasNeighborSignal(world, pos, state))),
            3
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(LIT)
    }

    override fun isSignalSource(state: BlockState): Boolean = state.getValue(LIT)

    companion object {
        val LIT: BooleanProperty = BlockStateProperties.LIT

        fun hasNeighborSignal(level: Level, blockPos: BlockPos, blockState: BlockState): Boolean {
            val direction = (blockState.getValue(FACING)).opposite
            return level.hasSignal(blockPos.relative(direction), direction)
        }
    }
}