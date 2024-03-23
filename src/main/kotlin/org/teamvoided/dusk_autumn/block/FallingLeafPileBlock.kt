package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.client.util.ParticleUtil
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World

class FallingLeafPileBlock(settings: Settings, val particle: DefaultParticleType) : LeafPileBlock(settings) {
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
        if (random.nextInt(10) == 0) {
            val blockPos = pos.down()
            val blockState = world.getBlockState(blockPos)
            if (!isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
                ParticleUtil.spawnParticle(world, pos, random, particle)
            }
        }
    }
}