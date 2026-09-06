package org.teamvoided.dusks_and_dungeons.renderer.blockentity

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.core.Direction
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.AbstractCandleBlock
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.phys.Vec3
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity

class CandelabraRenderer(ctx: BlockEntityRendererProvider.Context) : BlockEntityRenderer<CandelabraBlockEntity> {

    internal val blockRenderer = ctx.blockRenderDispatcher
    internal val itemRenderer = ctx.itemRenderer

    val offsetList = listOf(
        listOf(Vec3(0.0, 8.0, 0.0)),
        listOf(
            Vec3(4.0, 8.0, 0.0),
            Vec3(-4.0, 8.0, 0.0),
        ),
        listOf(
            Vec3(5.0, 8.0, 0.0),
            Vec3(-5.0, 8.0, 0.0),
            Vec3(0.0, 10.0, 0.0),
        ),
        listOf(
            Vec3(5.0, 8.0, 0.0),
            Vec3(-5.0, 8.0, 0.0),
            Vec3(0.0, 8.0, 5.0),
            Vec3(0.0, 8.0, -5.0),
        ),
        listOf(
            Vec3(0.0, 10.0, 0.0),
            Vec3(5.0, 8.0, 0.0),
            Vec3(-5.0, 8.0, 0.0),
            Vec3(0.0, 8.0, 5.0),
            Vec3(0.0, 8.0, -5.0),
        )
    )

    override fun render(
        candelabra: CandelabraBlockEntity,
        tickDelta: Float, posStack: PoseStack, buffers: MultiBufferSource, light: Int, overlay: Int,
    ) {
        val state = candelabra.blockState
        val direction = when (state.getValue(CandelabraBlock.HORIZONTAL_AXIS)) {
            Direction.Axis.Z -> Direction.EAST
            else -> Direction.NORTH
        }
        posStack.pushPose()

        posStack.rotateAround(Axis.YP.rotationDegrees(-direction.toYRot() - 180), 0.5f, 0.5f, 0.5f)

        val candles = state.getValue(CandelabraBlock.CANDLES)
        val isLit = state.getValue(CandelabraBlock.LIT)

        val offsets = offsetList[candles - 1]
        for ((index, stack) in candelabra.getCandles().withIndex()) {
            if (stack.isEmpty) {
                continue
            }
            val item = stack.item
            val off = offsets.getOrElse(index) { Vec3.ZERO }
            val x = 0.0625
            posStack.pushPose()
            posStack.translate(off.x * x, off.y * x, off.z * x)
            if (item is BlockItem) {
                val block = item.block
                var state = block.defaultBlockState()
                if (state.hasProperty(BlockStateProperties.FACING)) {
//                state = state.setValue(BlockStateProperties.FACING, direction)
                }
                state = state.trySetValue(AbstractCandleBlock.LIT, isLit)
                blockRenderer.renderSingleBlock(state, posStack, buffers, light, overlay)
            } else {
                itemRenderer.renderStatic(
                    stack, ItemDisplayContext.FIXED, light, overlay, posStack, buffers, candelabra.level, 0
                )
            }
            posStack.popPose()
        }
        if (candelabra.isEmpty()) {
            posStack.pushPose()
            posStack.translate(0.5, 0.5, 0.5)
            itemRenderer.renderStatic(
                Items.BARRIER.defaultInstance, ItemDisplayContext.FIXED,
                light, overlay,
                posStack, buffers,
                candelabra.level, 0
            )
            posStack.popPose()
        }
        posStack.popPose()
    }
}
