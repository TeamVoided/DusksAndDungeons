package org.teamvoided.dusk_autumn.block.rocky

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.DirtPathBlock
import net.minecraft.item.ItemPlacementContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.event.GameEvent

class RockyDirtPathBlock(private val dirtAfter: Block, settings: Settings) : DirtPathBlock(settings) {

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return if (!defaultState.canPlaceAt(ctx.world, ctx.blockPos)) pushEntitiesUpBeforeBlockChange(
            this.defaultState, dirtAfter.defaultState, ctx.world, ctx.blockPos
        ) else super.getPlacementState(ctx)
    }

    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        val blockState = pushEntitiesUpBeforeBlockChange(state, dirtAfter.defaultState, world, pos)
        world.setBlockState(pos, blockState)
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.create(null, blockState))
    }
}