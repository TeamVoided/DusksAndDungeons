package org.teamvoided.dusks_and_dungeons.block.meltable

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class MeltableStairsBlock(base: BlockState, properties: Properties) : StairBlock(base, properties) {

    override fun skipRendering(state: BlockState, neighborState: BlockState, dir: Direction): Boolean {
        return super.skipRendering(state, neighborState, dir) || Meltable.shouldCullFace(state, neighborState, dir)
    }

    override fun playerDestroy(
        level: Level, player: Player, pos: BlockPos, state: BlockState, blockEntity: BlockEntity?, tool: ItemStack,
    ) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool)
        Meltable.meltAfterBreak(level, pos, tool)
    }

    override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        Meltable.meltFromLight(state, level, pos)
    }

}