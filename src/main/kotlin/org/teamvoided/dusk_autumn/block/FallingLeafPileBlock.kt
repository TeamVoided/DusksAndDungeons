package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleEffect
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World

class FallingLeafPileBlock(val particle: DefaultParticleType, settings: Settings) : LeafPileBlock(settings) {
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
        if (random.nextInt(10) == 0) {
            val blockPos = pos.down()
            val blockStateBelow = world.getBlockState(blockPos)
            if (state.get(HANGING) && state.get(PILE_LAYERS) < 4) {
                spawnParticle(world, pos.d.add(0, (4 - state.get(PILE_LAYERS)) / 4.0, 0), random, particle)
            } else if (!isFaceFullSquare(blockStateBelow.getOutlineShape(world, blockPos), Direction.UP)) {
                spawnParticle(world, pos, random, particle)
            }
        }
    }

    private fun spawnParticle(world: World, pos: BlockPos, random: RandomGenerator, effect: ParticleEffect?) =
        spawnParticle(world, DPos(pos), random, effect)

    private fun spawnParticle(world: World, pos: DPos, random: RandomGenerator, effect: ParticleEffect?) {
        val d = pos.x + random.nextDouble()
        val e = pos.y - 0.05
        val f = pos.z + random.nextDouble()
        world.addParticle(effect, d, e, f, 0.0, 0.0, 0.0)
    }

    private val BlockPos.d get() = DPos(this)

    data class DPos(var x: Double, var y: Double, var z: Double) {
        constructor(pos: BlockPos) : this(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())

        fun add(ix: Number, iy: Number, iz: Number): DPos =
            DPos(x + ix.toDouble(), y + iy.toDouble(), z + iz.toDouble())
    }
}