package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.state.BlockState


interface CryingBlock {

    fun createParticles(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        if (random.nextInt(5) != 0) return

        val dir = Direction.getRandom(random)
        if (dir == Direction.UP) return

        val offsetPos = pos.relative(dir)
        val sideSate = level.getBlockState(offsetPos)
        if (!state.canOcclude() || !sideSate.isFaceSturdy(level, offsetPos, dir.opposite)) {
            val xOffset = if (dir.stepX == 0) random.nextDouble() else 0.5 + dir.stepX * 0.6
            val yOffset = if (dir.stepY == 0) random.nextDouble() else 0.5 + dir.stepY * 0.6
            val zOffset = if (dir.stepZ == 0) random.nextDouble() else 0.5 + dir.stepZ * 0.6
            level.addParticle(
                getParticleOption(state, level, pos, random),
                pos.x + xOffset, pos.y + yOffset, pos.z + zOffset,
                0.0, 0.0, 0.0
            )
        }
    }

    fun getParticleOption(state: BlockState, level: Level, pos: BlockPos, random: RandomSource): ParticleOptions {
        return ParticleTypes.DRIPPING_OBSIDIAN_TEAR
    }

}

class CryingStairsBlock(block: BlockState, properties: Properties) : StairBlock(block, properties), CryingBlock {

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        createParticles(state, level, pos, random)
        super.animateTick(state, level, pos, random)
    }

}

class CryingSlabBlock(properties: Properties) : SlabBlock(properties), CryingBlock {

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        createParticles(state, level, pos, random)
        super.animateTick(state, level, pos, random)
    }

}

class CryingWallBlock(properties: Properties) : WallBlock(properties), CryingBlock {

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        createParticles(state, level, pos, random)
        super.animateTick(state, level, pos, random)
    }

}
