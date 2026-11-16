package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.PinkPetalsBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import java.util.function.BiFunction

class VivionbedBlock(properties: Properties) : PinkPetalsBlock(properties) {

    override fun mayPlaceOn(floor: BlockState, level: BlockGetter, pos: BlockPos): Boolean =
        floor.`is`(DnDBlockTags.SUPPORTS_VIVIONBED)


    override fun getShape(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        collisionContext: CollisionContext
    ): VoxelShape = SHAPE_BY_PROPERTIES.apply(blockState.getValue(FACING), blockState.getValue(AMOUNT))

    companion object {
        private const val HEIGHT = 8.0
        val SHAPE_BY_PROPERTIES: BiFunction<Direction, Int, VoxelShape> =
            Util.memoize { direction: Direction, integer: Int ->
                val voxelShapes: Array<VoxelShape> = arrayOf(
                    box(8.0, 0.0, 8.0, 16.0, HEIGHT, 16.0),
                    box(8.0, 0.0, 0.0, 16.0, HEIGHT, 8.0),
                    box(0.0, 0.0, 0.0, 8.0, HEIGHT, 8.0),
                    box(0.0, 0.0, 8.0, 8.0, HEIGHT, 16.0)
                )
                var voxelShape = Shapes.empty()

                for (i in 1..integer) {
                    val j = Math.floorMod(i - direction.get2DDataValue(), 4)
                    voxelShape = Shapes.or(voxelShape, voxelShapes[j])
                }

                voxelShape.singleEncompassing()
            }
    }

}