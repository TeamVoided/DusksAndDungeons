package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.util.ParticleUtils
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState

class FallingLeafPileBlock(val particle: ParticleOptions, properties: Properties) : LeafPileBlock(properties) {

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, level, pos, random)
        if (random.nextInt(10) == 0) {
            val belowPos = pos.below()
            val belowState = level.getBlockState(belowPos)
            val layers = state.getValue(PILE_LAYERS)
            if (state.getValue(HANGING) && layers < 4) {
                val x = pos.x + random.nextDouble()
                val y = pos.y + (4 - layers) / 4.0 - 0.05
                val z = pos.z + random.nextDouble()
                level.addParticle(particle, x, y, z, 0.0, 0.0, 0.0)
            } else if (!isFaceFull(belowState.getShape(level, belowPos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, pos, random, particle)
            }
        }
    }

}