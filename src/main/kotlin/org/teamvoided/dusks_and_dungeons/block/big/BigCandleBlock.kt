package org.teamvoided.dusks_and_dungeons.block.big

import com.google.common.collect.ImmutableList
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundSource
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.phys.Vec3
import net.minecraft.util.RandomSource
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.AbstractCandleBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import org.teamvoided.dusks_and_dungeons.util.block.FULL_CUBE
import org.teamvoided.dusks_and_dungeons.util.rotate
import org.teamvoided.voidlib.helpers.mc.rotateFlat90
import java.util.function.Consumer

open class BigCandleBlock(val particle: SimpleParticleType, settings: Properties) : CandleBlock(settings) {
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(CANDLES, 1)
                .setValue(LIT, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? =
        super.getStateForPlacement(ctx)?.setValue(FACING, ctx.horizontalDirection.opposite)

    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ): VoxelShape = (when (state.getValue(CANDLES)) {
        1 -> ONE_BIG_CANDLE_SHAPE
        2 -> TWO_BIG_CANDLES_SHAPE
        3 -> THREE_BIG_CANDLES_SHAPE
        4 -> FOUR_BIG_CANDLES_SHAPE
        else -> FULL_CUBE
    }).rotate(state.getValue(FACING).get2DDataValue())

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

    override fun getParticleOffsets(state: BlockState): Iterable<Vec3> =
        BIG_CANDLES_TO_PARTICLE_OFFSETS[state.getValue(CANDLES)].rotateFlat90(state.getValue(FACING).get2DDataValue())

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
                    random.nextFloat() * 0.7f + 0.3f,
                    false
                )
            }
        }

        world.addParticle(particle, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState =
        state.setValue(HorizontalDirectionalBlock.FACING, rotation.rotate(state.getValue(FACING)))

    override fun mirror(state: BlockState, mirror: Mirror): BlockState =
        state.rotate(mirror.getRotation(state.getValue(FACING)))

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(FACING)
    }

    companion object {
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING

        val ONE_BIG_CANDLE_SHAPE: VoxelShape =
            candle(6.0, 6.0, 12.0)
        val TWO_BIG_CANDLES_SHAPE: VoxelShape = Shapes.or(
            candle(9.0, 6.0, 12.0),
            candle(3.0, 7.0, 10.0)
        )
        val THREE_BIG_CANDLES_SHAPE: VoxelShape = Shapes.or(
            candle(8.0, 4.0, 12.0),
            candle(3.0, 5.0, 10.0),
            candle(7.0, 9.0, 6.0)
        )
        val FOUR_BIG_CANDLES_SHAPE: VoxelShape = Shapes.or(
            candle(8.0, 3.0, 12.0),
            candle(3.0, 3.0, 10.0),
            candle(4.0, 8.0, 6.0),
            candle(9.0, 8.0, 10.0)
        )
        val BIG_CANDLES_TO_PARTICLE_OFFSETS: Int2ObjectMap<List<Vec3>> = Util.make {
            val int2ObjectMap: Int2ObjectMap<List<Vec3>> = Int2ObjectOpenHashMap()
            int2ObjectMap.defaultReturnValue(ImmutableList.of())
            int2ObjectMap.put(
                1, ImmutableList.of(
                    Vec3(0.5, 0.875, 0.5)
                )
            )
            int2ObjectMap.put(
                2, ImmutableList.of(
                    Vec3(0.6875, 0.875, 0.5),
                    Vec3(0.3125, 0.75, 0.5625)
                )
            )
            int2ObjectMap.put(
                3, ImmutableList.of(
                    Vec3(0.625, 0.875, 0.375),
                    Vec3(0.3125, 0.75, 0.4375),
                    Vec3(0.5625, 0.5, 0.6875),
                )
            )
            int2ObjectMap.put(
                4, ImmutableList.of(
                    Vec3(0.625, 0.875, 0.3125),
                    Vec3(0.3125, 0.75, 0.3125),
                    Vec3(0.375, 0.5, 0.625),
                    Vec3(0.6875, 0.75, 0.625),
                )
            )
            Int2ObjectMaps.unmodifiable(int2ObjectMap)
        }

        fun candle(x: Double, z: Double, height: Double): VoxelShape =
            box(x, 0.0, z, x + 4, height, z + 4)
    }
}
