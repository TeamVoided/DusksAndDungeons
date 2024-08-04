package org.teamvoided.dusk_autumn.block.big

import com.google.common.collect.ImmutableList
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import org.teamvoided.dusk_autumn.util.rotate

open class BigCandleBlock(settings: Settings) : CandleBlock(settings) {

//    init {
//        this.defaultState =
//            (stateManager.defaultState)
//                .with(CANDLES, 1)
//                .with(LIT, false)
//                .with(FACING, Direction.NORTH)
//                .with(WATERLOGGED, false)
//    }
//
//    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
//        return super.getPlacementState(ctx)?.with(FACING, ctx.playerFacing)
//    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
//        val rotations = when (state.get(FACING)) {
//            Direction.NORTH -> 0
//            Direction.SOUTH -> 2
//            Direction.WEST -> 3
//            Direction.EAST -> 1
//            else -> 0
//        }
        return (when (state.get(CANDLES)) {
            1 -> ONE_BIG_CANDLE_SHAPE
            2 -> TWO_BIG_CANDLES_SHAPE
            3 -> THREE_BIG_CANDLES_SHAPE
            4 -> FOUR_BIG_CANDLES_SHAPE
            else -> ONE_BIG_CANDLE_SHAPE
        })
//            .rotate(rotations)
    }

    override fun getParticleOffsets(state: BlockState): Iterable<Vec3d> {
        return BIG_CANDLES_TO_PARTICLE_OFFSETS[state.get(CANDLES)]
    }

//    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
//        builder.add(LIT, CANDLES, FACING, WATERLOGGED)
//    }

    companion object {
//        val FACING: DirectionProperty = HorizontalFacingBlock.FACING

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