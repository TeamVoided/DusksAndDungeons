package org.teamvoided.dusks_and_dungeons.block.candelabra

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.ItemTags
import net.minecraft.world.Containers
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block.box
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.candelabra.EmptyCandelabraBlock.Companion.CANDLES
import org.teamvoided.dusks_and_dungeons.block.candelabra.EmptyCandelabraBlock.Companion.HORIZONTAL_AXIS
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities
import org.teamvoided.dusks_and_dungeons.util.rotate
import kotlin.jvm.optionals.getOrNull

object Candelabra {

    val SINGLE_SHAPE: VoxelShape = box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0)
    val DOUBLE_SHAPE: VoxelShape = Shapes.or(
        box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
        box(2.0, 4.0, 6.0, 14.0, 8.0, 10.0),
    )
    val TRIPLE_SHAPE: VoxelShape = Shapes.or(
        box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
        box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
    )
    val QUADRUPLE_SHAPE: VoxelShape = Shapes.or(
        box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
        box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
        box(6.0, 4.0, 1.0, 10.0, 8.0, 15.0),
    )
    val QUINTUPLE_SHAPE: VoxelShape = Shapes.or(
        box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
        box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
        box(6.0, 4.0, 1.0, 10.0, 8.0, 15.0),
    )

    val SHAPES = HORIZONTAL_AXIS.possibleValues.associateWith { dir ->
        CANDLES.possibleValues.associateWith { count ->
            when (count) {
                1 -> SINGLE_SHAPE
                2 -> DOUBLE_SHAPE
                3 -> TRIPLE_SHAPE
                4 -> QUADRUPLE_SHAPE
                5 -> QUINTUPLE_SHAPE
                else -> Shapes.block()
            }.rotate(dir.getRotations())
        }
    }

    fun Direction.Axis.getRotations(): Int = if (this == Direction.Axis.X) 0 else 1

    fun canAddToCandelabra(stack: ItemStack): Boolean = stack.`is`(ItemTags.CANDLES)

    fun tryAddToCandelabra(level: Level, pos: BlockPos, stack: ItemStack, player: Player): Boolean {
        val candelabra = level.getBlockEntity(pos, DnDBlockEntities.CANDELABRA).getOrNull() ?: return false
        var idx = 0
        for (item in candelabra.candles) {
            if (item.isEmpty) {
                break
            }
            idx++
        }
        if (candelabra.tryAddCandle(stack, idx)) {
            stack.consume(1, player)
            return true
        }
        return false
    }

    fun dropContentsOnDestroy(state: BlockState, otherState: BlockState, level: Level, pos: BlockPos) {
        if (state.`is`(otherState.block)) {
            return
        }
        val be = level.getBlockEntity(pos, DnDBlockEntities.CANDELABRA).getOrNull() ?: return
        Containers.dropContents(level, pos, be.candles)
        level.updateNeighbourForOutputSignal(pos, state.block)
    }
}