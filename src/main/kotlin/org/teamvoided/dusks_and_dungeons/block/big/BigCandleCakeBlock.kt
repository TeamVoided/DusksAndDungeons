package org.teamvoided.dusks_and_dungeons.block.big

import com.google.common.collect.ImmutableList
import net.minecraft.block.*
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import java.util.function.Consumer

open class BigCandleCakeBlock(candle: Block, val particle: DefaultParticleType, settings: Settings) :
    CandleCakeBlock(candle, settings) {
    override fun getParticleOffsets(state: BlockState?): Iterable<Vec3d> = BIG_CANDLE_PARTICLE_OFFSETS
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        if (state.get(AbstractCandleBlock.LIT)) {
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
        world.addParticle(particle, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape = SHAPE

    companion object {
        private val CANDLE_SHAPE: VoxelShape = createCuboidShape(6.0, 8.0, 6.0, 10.0, 20.0, 10.0)
        val SHAPE: VoxelShape = VoxelShapes.union(CAKE_SHAPE, this.CANDLE_SHAPE)
        val BIG_CANDLE_PARTICLE_OFFSETS: ImmutableList<Vec3d> = ImmutableList.of(Vec3d(0.5, 1.375, 0.5))
    }
}