package org.teamvoided.dusks_and_dungeons.util.mixin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CalibratedSculkSensorBlock
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.SixWayFacingBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags

object DirectionalSculk {

    // region Block Code
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

    @JvmStatic
    fun isUp(state: BlockState) = state.getValue(FACING) == Direction.UP

    @JvmStatic
    fun getPlacementState(original: BlockState, ctx: BlockPlaceContext): BlockState {
        return original.setValue(FACING, ctx.clickedFace)
    }

    @JvmStatic
    fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    @JvmStatic
    fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)))
    }

    @JvmStatic
    fun getWardenSpawnPos(level: ServerLevel, pos: BlockPos, beState: BlockState): BlockPos {
        if (isUp(beState)) {
            return pos
        }
        val movingPos = pos.mutable()
        repeat(29) {
            if (!level.getBlockState(movingPos.move(Direction.DOWN)).`is`(DnDBlockTags.SHRIEKER_SEARCH_BYPASSES)) {
                return movingPos.above()
            }
        }
        return pos
    }

    @JvmStatic
    fun isCreativeFlying(entity: Entity?): Boolean {
        return entity is Player && entity.isCreative && entity.abilities.flying
    }


    // endregion

    // region Spreader Code



    // endregion

}