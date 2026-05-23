package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level

class FallingLeafPileBlock(val particle: SimpleParticleType, settings: Properties) : LeafPileBlock(settings) {
    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, world, pos, random)
        if (random.nextInt(10) == 0) {
            val blockPos = pos.below()
            val blockStateBelow = world.getBlockState(blockPos)
            if (state.getValue(HANGING) && state.getValue(PILE_LAYERS) < 4) {
                spawnParticle(world, pos.d.add(0, (4 - state.getValue(PILE_LAYERS)) / 4.0, 0), random, particle)
            } else if (!isFaceFull(blockStateBelow.getShape(world, blockPos), Direction.UP)) {
                spawnParticle(world, pos, random, particle)
            }
        }
    }

    private fun spawnParticle(world: Level, pos: BlockPos, random: RandomSource, effect: ParticleOptions?) =
        spawnParticle(world, DPos(pos), random, effect)

    private fun spawnParticle(world: Level, pos: DPos, random: RandomSource, effect: ParticleOptions?) {
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