package org.teamvoided.dusk_autumn.block.big

import com.google.common.collect.ImmutableList
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.util.FULL_CUBE
import org.teamvoided.dusk_autumn.util.rotate
import org.teamvoided.dusk_autumn.util.rotateFlat90
import java.util.function.Consumer

open class BigCandleBlock(settings: Settings) : CandleBlock(settings) {
    open val particle = ParticleTypes.FLAME

    init {
        this.defaultState = stateManager.defaultState
            .with(CANDLES, 1)
            .with(LIT, false)
            .with(FACING, Direction.NORTH)
            .with(WATERLOGGED, false)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return super.getPlacementState(ctx)?.with(FACING, ctx.playerFacing.opposite)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return (when (state.get(CANDLES)) {
            1 -> ONE_BIG_CANDLE_SHAPE
            2 -> TWO_BIG_CANDLES_SHAPE
            3 -> THREE_BIG_CANDLES_SHAPE
            4 -> FOUR_BIG_CANDLES_SHAPE
            else -> FULL_CUBE
        }).rotate(state.get(FACING).horizontal)
    }
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

    override fun getParticleOffsets(state: BlockState): Iterable<Vec3d> {
        return BIG_CANDLES_TO_PARTICLE_OFFSETS[state.get(CANDLES)].rotateFlat90(state.get(FACING).horizontal)
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
                    random.nextFloat() * 0.7f + 0.3f,
                    false
                )
            }
        }

        world.addParticle(ParticleTypes.FLAME, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(FACING)
    }

    companion object {
        val FACING: DirectionProperty = Properties.HORIZONTAL_FACING

        val ONE_BIG_CANDLE_SHAPE: VoxelShape =
            candle(6.0, 6.0, 12.0)
        val TWO_BIG_CANDLES_SHAPE: VoxelShape = VoxelShapes.union(
            candle(9.0, 6.0, 12.0),
            candle(3.0, 7.0, 10.0)
        )
        val THREE_BIG_CANDLES_SHAPE: VoxelShape = VoxelShapes.union(
            candle(8.0, 4.0, 12.0),
            candle(3.0, 5.0, 10.0),
            candle(7.0, 9.0, 6.0)
        )
        val FOUR_BIG_CANDLES_SHAPE: VoxelShape = VoxelShapes.union(
            candle(8.0, 3.0, 12.0),
            candle(3.0, 3.0, 10.0),
            candle(4.0, 8.0, 6.0),
            candle(9.0, 8.0, 10.0)
        )
        val BIG_CANDLES_TO_PARTICLE_OFFSETS = Util.make {
            val int2ObjectMap: Int2ObjectMap<List<Vec3d>> = Int2ObjectOpenHashMap()
            int2ObjectMap.defaultReturnValue(ImmutableList.of())
            int2ObjectMap.put(
                1, ImmutableList.of(
                    Vec3d(0.5, 0.875, 0.5)
                )
            )
            int2ObjectMap.put(
                2, ImmutableList.of(
                    Vec3d(0.6875, 0.875, 0.5),
                    Vec3d(0.3125, 0.75, 0.5625)
                )
            )
            int2ObjectMap.put(
                3, ImmutableList.of(
                    Vec3d(0.625, 0.875, 0.375),
                    Vec3d(0.3125, 0.75, 0.4375),
                    Vec3d(0.5625, 0.5, 0.6875),
                )
            )
            int2ObjectMap.put(
                4, ImmutableList.of(
                    Vec3d(0.625, 0.875, 0.3125),
                    Vec3d(0.3125, 0.75, 0.3125),
                    Vec3d(0.375, 0.5, 0.625),
                    Vec3d(0.6875, 0.75, 0.625),
                )
            )
            Int2ObjectMaps.unmodifiable(int2ObjectMap)
        }

        fun candle(x: Double, z: Double, height: Double): VoxelShape {
            return createCuboidShape(x, 0.0, z, x + 4, height, z + 4)
        }
    }
}
