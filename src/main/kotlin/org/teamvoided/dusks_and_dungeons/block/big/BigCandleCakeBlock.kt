package org.teamvoided.dusks_and_dungeons.block.big

import com.google.common.collect.ImmutableList
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.AbstractCandleBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CandleCakeBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Consumer

open class BigCandleCakeBlock(candle: Block, val particle: SimpleParticleType, settings: Properties) :
    CandleCakeBlock(candle, settings) {
    override fun getParticleOffsets(state: BlockState?): Iterable<Vec3> = BIG_CANDLE_PARTICLE_OFFSETS
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

    private fun spawnCandleParticles(world: Level, vec3d: Vec3, random: RandomSource) {
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
        world.addParticle(particle, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape = SHAPE

    companion object {
        private val CANDLE_SHAPE: VoxelShape = box(6.0, 8.0, 6.0, 10.0, 20.0, 10.0)
        val SHAPE: VoxelShape = Shapes.or(CAKE_SHAPE, this.CANDLE_SHAPE)
        val BIG_CANDLE_PARTICLE_OFFSETS: ImmutableList<Vec3> = ImmutableList.of(Vec3(0.5, 1.375, 0.5))
    }
}