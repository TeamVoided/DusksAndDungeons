package org.teamvoided.dusks_and_dungeons.block.meltable

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.block.meltable.Meltable.shouldCullFace

class MeltableWallBlock(settings: Properties) : WallBlock(settings) {
    override fun skipRendering(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean =
        shouldCullFace(state, stateFrom, direction) || super.skipRendering(state, stateFrom, direction)

    override fun playerDestroy(
        world: Level, player: Player, pos: BlockPos, state: BlockState, blockEntity: BlockEntity?, stack: ItemStack,
    ) {
        super.playerDestroy(world, player, pos, state, blockEntity, stack)
        Meltable.meltAfterBreak(world, pos, stack)
    }

    override fun randomTick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) =
        Meltable.meltFromLight(state, world, pos)
}
