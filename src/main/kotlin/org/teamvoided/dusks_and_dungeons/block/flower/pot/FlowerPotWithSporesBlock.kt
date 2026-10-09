package org.teamvoided.dusks_and_dungeons.block.flower.pot

import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.FlowerPotBlock
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.block.MushroomWithSporesPlantBlock.Companion.spawnSporeParticles

class FlowerPotWithSporesBlock(
    val color: Int, val particleChance: Double,
    block: Block, properties: Properties,
) : FlowerPotBlock(block, properties) {

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, level, pos, random)
        spawnSporeParticles(state, level, pos, random, particleChance, color)
    }

}