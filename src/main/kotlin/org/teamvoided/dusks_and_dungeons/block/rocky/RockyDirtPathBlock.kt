package org.teamvoided.dusks_and_dungeons.block.rocky

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.DirtPathBlock
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.gameevent.GameEvent

class RockyDirtPathBlock(private val dirtAfter: Block, settings: Properties) : DirtPathBlock(settings) {

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        return if (!defaultBlockState().canSurvive(ctx.level, ctx.clickedPos)) pushEntitiesUp(
            this.defaultBlockState(), dirtAfter.defaultBlockState(), ctx.level, ctx.clickedPos
        ) else super.getStateForPlacement(ctx)
    }

    override fun tick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        val blockState = pushEntitiesUp(state, dirtAfter.defaultBlockState(), world, pos)
        world.setBlockAndUpdate(pos, blockState)
        world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(null, blockState))
    }
}