package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.LeavesBlock
import net.minecraft.util.ParticleUtils
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level

open class FallingLeavesBlock(val particle: SimpleParticleType, settings: Properties) : LeavesBlock(settings) {
    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, world, pos, random)
        if (random.nextInt(10) == 0) {
            val blockPos = pos.below()
            val blockState = world.getBlockState(blockPos)
            if (!isFaceFull(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(world, pos, random, particle)
            }
        }
    }
}