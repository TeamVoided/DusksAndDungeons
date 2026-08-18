package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level

// TODO move to Variance
// HAHA SIKE
class BigRedstoneLanternBlock(settings: Properties) : BigLanternBlock(settings) {
    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(LIT, true)
                .setValue(HANGING, false)
        )
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
        state: BlockState, world: Level, pos: BlockPos, block: Block, fromPos: BlockPos, notify: Boolean,
    ) {
        if (state.getValue(LIT) == this.shouldUnPower(world, pos, state) && !world.blockTicks.willTickThisTick(pos, this))
            world.scheduleTick(pos, this, 2)
    }

    override fun getSignal(state: BlockState, world: BlockGetter, pos: BlockPos, direction: Direction): Int {
        if (!state.getValue(LIT)) return 0

        return if ((state.getValue(HANGING) && Direction.DOWN != direction) || Direction.UP != direction) 15
        else 0
    }

    override fun getDirectSignal(state: BlockState, world: BlockGetter, pos: BlockPos, dir: Direction): Int {
        if (state.getValue(HANGING)) {
            return if (dir == Direction.UP) state.getSignal(world, pos, dir) else 0
        }
        return if (dir == Direction.DOWN) state.getSignal(world, pos, dir) else 0
    }

    private fun shouldUnPower(world: Level, pos: BlockPos, state: BlockState): Boolean {
        val isHanging = state.getValue(HANGING)
        return if (isHanging) world.hasSignal(pos.above(), Direction.UP)
        else world.hasSignal(pos.below(), Direction.DOWN)
    }

    override fun tick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        world.setBlock(
            pos,
            state.setValue(LIT, !(state.getValue(LIT) && shouldUnPower(world, pos, state))),
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
    }
}