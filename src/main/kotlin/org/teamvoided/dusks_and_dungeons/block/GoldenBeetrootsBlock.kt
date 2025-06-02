package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.block.BeetrootsBlock
import net.minecraft.block.BlockState
import net.minecraft.item.ItemConvertible
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.WorldView
import org.teamvoided.dusks_and_dungeons.init.DnDItems

class GoldenBeetrootsBlock(settings: Settings) : BeetrootsBlock(settings) {
    override fun getSeedsItem(): ItemConvertible = DnDItems.GOLDEN_BEETROOT
    override fun isFertilizable(world: WorldView?, pos: BlockPos?, state: BlockState?): Boolean = false
    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        if (random.nextInt(2) != 0) super.randomTick(state, world, pos, random)
    }
}