package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty

class BigRedstoneLanternBlock(settings: Properties) : BigLanternBlock(settings) {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(WATERLOGGED, false)
                .setValue(LIT, true)
        )
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val state = super.getStateForPlacement(ctx)
        return state?.setValue(LIT, !hasNeighborSignal(ctx.level, ctx.clickedPos, state))
    }

    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, oldState: BlockState, movedByPiston: Boolean) {
        for (direction in Direction.entries) {
            level.updateNeighborsAt(pos.relative(direction), this)
        }
    }

    override fun onRemove(
        state: BlockState, world: Level, pos: BlockPos, newState: BlockState, movedByPiston: Boolean,
    ) {
        if (movedByPiston) return

        for (dir in Direction.entries) {
            world.updateNeighborsAt(pos.relative(dir), this)
        }
    }

    override fun neighborChanged(
        state: BlockState, level: Level, pos: BlockPos, block: Block, neighborPos: BlockPos, movedByPiston: Boolean,
    ) {
        if (state.getValue(LIT) == hasNeighborSignal(level, pos, state) &&
            !level.blockTicks.willTickThisTick(pos, this)
        ) {
            level.scheduleTick(pos, this, 2)
        }
    }

    override fun getSignal(state: BlockState, level: BlockGetter, pos: BlockPos, dir: Direction): Int {
        return if (state.getValue(LIT) && state.getValue(FACING) != dir) 15 else 0
    }

    override fun getDirectSignal(state: BlockState, level: BlockGetter, pos: BlockPos, dir: Direction): Int {
        return if (dir == state.getValue(FACING).opposite) state.getSignal(level, pos, dir) else 0
    }

    override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        level.setBlock(
            pos,
            state.setValue(LIT, !(state.getValue(LIT) && hasNeighborSignal(level, pos, state))),
            UPDATE_ALL
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(LIT)
    }

    override fun isSignalSource(state: BlockState): Boolean = state.getValue(LIT)

    companion object {

        val LIT: BooleanProperty = BlockStateProperties.LIT

        fun hasNeighborSignal(level: Level, pos: BlockPos, state: BlockState): Boolean {
            val dir = state.getValue(FACING).opposite
            return level.hasSignal(pos.relative(dir), dir)
        }

    }
}