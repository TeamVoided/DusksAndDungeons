package org.teamvoided.dusk_autumn.block

import com.google.common.collect.ImmutableList
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.block.AbstractCandleBlock
import net.minecraft.block.BlockState
import net.minecraft.block.CandleBlock
import net.minecraft.block.ShapeContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import java.util.function.Consumer

class BigSoulCandleBlock(settings: Settings) : BigCandleBlock(settings) {
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        if (state.get(AbstractCandleBlock.LIT) as Boolean) {
            getParticleOffsets(state).forEach(Consumer { offset: Vec3d ->
                spawnCandleParticles(
                    world,
                    offset.add(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()),
                    random
                )
            })
        }
    }
    private fun spawnCandleParticles(world: World, vec3d: Vec3d, random: RandomGenerator) {
        val f = random.nextFloat()
        if (f < 0.3f) {
            world.addParticle(ParticleTypes.SMOKE, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0)
            if (f < 0.17f) {
                world.playSound(
                    vec3d.x + 0.5,
                    vec3d.y + 0.5,
                    vec3d.z + 0.5,
                    SoundEvents.BLOCK_CANDLE_AMBIENT,
                    SoundCategory.BLOCKS,
                    1.0f + random.nextFloat(),
                    random.nextFloat() * 0.7f + 0.1f,
                    false
                )
            }
        }
        world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0)
    }
}