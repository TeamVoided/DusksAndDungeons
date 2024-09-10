package org.teamvoided.dusk_autumn.block.big

import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.init.DnDParticles

class MoonscribeLanternBlock(settings: Settings) : BigLanternBlock(settings) {
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
//        if (random.nextDouble() >= particleChance) {
        world.addParticle(
            DnDParticles.SPIRAL,
            pos.x + 0.5,
            pos.y + 1.0,
            pos.z + 0.5,
            0.0,
            0.0,
            0.0
        )
//        }
    }
}