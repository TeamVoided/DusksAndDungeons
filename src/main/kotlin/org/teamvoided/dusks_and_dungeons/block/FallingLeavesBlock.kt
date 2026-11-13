package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.util.ParticleUtils
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.LeavesBlock
import net.minecraft.world.level.block.state.BlockState

open class FallingLeavesBlock(val particle: ParticleOptions, properties: Properties) : LeavesBlock(properties) {

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, level, pos, random)
        if (random.nextInt(10) == 0) {
            val belowPos = pos.below()
            val belowState = level.getBlockState(belowPos)
            if (!isFaceFull(belowState.getCollisionShape(level, belowPos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, pos, random, particle)
            }
        }
    }

}