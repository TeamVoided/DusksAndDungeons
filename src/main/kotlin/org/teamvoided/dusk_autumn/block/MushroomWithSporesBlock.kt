package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.MushroomBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.particle.ColorableParticleEffect

class MushroomWithSporesBlock(
    private val color: Int,
    private val particleChance: Double,
    settings: Settings
) : MushroomBlock(settings) {
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
        if (random.nextDouble() >= particleChance) {
            val offset = state.getModelOffset(world, pos)
            world.addParticle(
                ColorableParticleEffect(color),
                pos.x + offset.x + (random.nextDouble() * 0.6 + 0.2),
                pos.y + offset.y + (random.nextDouble() * 0.7 - 0.1),
                pos.z + offset.z + (random.nextDouble() * 0.6 + 0.2),
                (random.nextDouble() - random.nextDouble()) * 0.125,
                (random.nextDouble() * -0.1) - 0.1,
                (random.nextDouble() - random.nextDouble()) * 0.125
            )
        }
    }
}