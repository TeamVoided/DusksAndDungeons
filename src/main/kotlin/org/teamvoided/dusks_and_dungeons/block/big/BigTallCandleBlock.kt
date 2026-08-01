package org.teamvoided.dusks_and_dungeons.block.big

import com.google.common.collect.ImmutableList
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.level.BlockGetter

open class BigTallCandleBlock(particle: SimpleParticleType, settings: Properties) : BigCandleBlock(particle, settings) {

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return (when (state.getValue(CANDLES)) {
            1 -> ONE_BIG_CANDLE_SHAPE
            2 -> TWO_BIG_CANDLES_SHAPE
            3 -> THREE_BIG_CANDLES_SHAPE
            4 -> FOUR_BIG_CANDLES_SHAPE
            else -> ONE_BIG_CANDLE_SHAPE
        })
    }

    override fun getParticleOffsets(state: BlockState): Iterable<Vec3> {
        return BIG_CANDLES_TO_PARTICLE_OFFSETS[state.getValue(CANDLES)]
    }

    companion object {
        val ONE_BIG_CANDLE_SHAPE: VoxelShape =
            candle(5.5, 5.5, 24.0)
        val TWO_BIG_CANDLES_SHAPE: VoxelShape = Shapes.or(
            candle(9.0, 5.0, 24.0),
            candle(2.0, 6.0, 20.0)
        )
        val THREE_BIG_CANDLES_SHAPE: VoxelShape = Shapes.or(
            candle(8.0, 3.0, 24.0),
            candle(2.0, 4.0, 20.0),
            candle(7.0, 9.0, 12.0)
        )
        val FOUR_BIG_CANDLES_SHAPE: VoxelShape = Shapes.or(
            candle(8.0, 2.0, 24.0),
            candle(2.0, 2.0, 20.0),
            candle(3.0, 8.0, 12.0),
            candle(9.0, 8.0, 20.0)
        )
        val BIG_CANDLES_TO_PARTICLE_OFFSETS: Int2ObjectMap<List<Vec3>> = Util.make {
            val fire: Int2ObjectMap<List<Vec3>> = Int2ObjectOpenHashMap()
            fire.defaultReturnValue(ImmutableList.of())
            fire.put(
                1, ImmutableList.of(
                    Vec3(0.5, 1.625, 0.5)
                )
            )
            fire.put(
                2, ImmutableList.of(
                    Vec3(0.71875, 1.625, 0.46875),
                    Vec3(0.28125, 1.375, 0.53125)
                )
            )
            fire.put(
                3, ImmutableList.of(
                    Vec3(0.65625, 1.625, 0.34375),
                    Vec3(0.28125, 1.375, 0.40625),
                    Vec3(0.59375, 0.875, 0.71875),
                )
            )
            fire.put(
                4, ImmutableList.of(
                    Vec3(0.65625, 1.625, 0.28125),
                    Vec3(0.28125, 1.375, 0.28125),
                    Vec3(0.34375, 0.875, 0.65625),
                    Vec3(0.71875, 1.375, 0.65625),
                )
            )
            Int2ObjectMaps.unmodifiable(fire)
        }

        fun candle(x: Double, z: Double, height: Double): VoxelShape {
            return box(x, 0.0, z, x + 5, height, z + 5)
        }
    }
}