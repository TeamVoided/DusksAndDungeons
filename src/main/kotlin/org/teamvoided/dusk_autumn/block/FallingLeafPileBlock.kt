package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.client.util.ParticleUtil
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3i
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World

class FallingLeafPileBlock(settings: Settings, val particle: DefaultParticleType) : LeafPileBlock(settings) {
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
        if (random.nextInt(10) == 0) {
            val blockPos = pos.down()
            val blockStateBelow = world.getBlockState(blockPos)
            if (state.get(HANGING) && state.get(PILE_LAYERS) < 4) {
                ParticleUtil.spawnParticle(world, pos.add(0,1,0), random, particle)
            } else if (!isFaceFullSquare(blockStateBelow.getOutlineShape(world, blockPos), Direction.UP)) {
                ParticleUtil.spawnParticle(world, pos, random, particle)
            }
        }
    }
}