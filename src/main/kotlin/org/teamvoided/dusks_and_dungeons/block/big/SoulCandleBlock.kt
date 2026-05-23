package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.world.level.block.AbstractCandleBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundSource
import net.minecraft.sounds.SoundEvents
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.init.DnDParticles
import java.util.function.Consumer

class SoulCandleBlock(settings: Properties) : CandleBlock(settings) {
    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        if (state.getValue(AbstractCandleBlock.LIT)) {
            getParticleOffsets(state).forEach(Consumer { offset: Vec3 ->
                spawnCandleParticles(
                    world,
                    offset.add(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()),
                    random
                )
            })
        }
    }

    fun spawnCandleParticles(world: Level, vec3d: Vec3, random: RandomSource) {
        val f = random.nextFloat()
        if (f < 0.3f) {
            world.addParticle(ParticleTypes.SMOKE, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0)
            if (f < 0.17f) {
                world.playLocalSound(
                    vec3d.x + 0.5,
                    vec3d.y + 0.5,
                    vec3d.z + 0.5,
                    SoundEvents.CANDLE_AMBIENT,
                    SoundSource.BLOCKS,
                    1.0f + random.nextFloat(),
                    random.nextFloat() * 0.7f + 0.1f,
                    false
                )
            }
        }
        world.addParticle(DnDParticles.SMALL_SOUL_FLAME_PARTICLE, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0)
    }
}
