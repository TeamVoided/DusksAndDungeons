package org.teamvoided.dusks_and_dungeons.util.mixin

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CalibratedSculkSensorBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.SixWayFacingBlock

object DirectionalSculk {

    val BOTTOM_SHAPE: VoxelShape = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0)
    val TOP_SHAPE: VoxelShape = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)
    val SIDE_SHAPE: VoxelShape = Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0)

    val SHAPES = SixWayFacingBlock.createShapeMap(BOTTOM_SHAPE, TOP_SHAPE, SIDE_SHAPE)

    @JvmField
    val FACING: DirectionProperty = BlockStateProperties.FACING

    @JvmStatic
    fun getShape(state: BlockState): VoxelShape {
        return SHAPES[state.getValue(FACING)] ?: Shapes.block()
    }

    @JvmStatic
    fun isCalibrated(block: Block) = block is CalibratedSculkSensorBlock

}