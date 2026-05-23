package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.BeetrootBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.ItemLike
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelReader
import org.teamvoided.dusks_and_dungeons.init.DnDItems

class GoldenBeetrootsBlock(settings: Properties) : BeetrootBlock(settings) {
    override fun getBaseSeedId(): ItemLike = DnDItems.GOLDEN_BEETROOT
    override fun isValidBonemealTarget(world: LevelReader?, pos: BlockPos?, state: BlockState?): Boolean = false
    override fun randomTick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (random.nextInt(2) != 0) super.randomTick(state, world, pos, random)
    }
}