package org.teamvoided.dusks_and_dungeons.renderer.blockentity

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.core.Direction
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.AbstractCandleBlock
import net.minecraft.world.phys.Vec3
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity

class CandelabraRenderer(ctx: BlockEntityRendererProvider.Context) : BlockEntityRenderer<CandelabraBlockEntity> {

    internal val blockRenderer = ctx.blockRenderDispatcher
    internal val itemRenderer = ctx.itemRenderer
    val emptyStack: ItemStack = Items.BARRIER.defaultInstance

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

        val offsets = Candelabra.OFFSETS[candles - 1]
        for ((index, stack) in candelabra.getCandles().withIndex()) {
            if (stack.isEmpty) {
                continue
            }
            val item = stack.item
            val off = offsets.getOrElse(index) { Vec3.ZERO }
            posStack.pushPose()
            posStack.translate(off.x, off.y, off.z)
            if (item is BlockItem) {
                var state = candelabra.stateCache[index]
                state = state.trySetValue(AbstractCandleBlock.LIT, isLit)
                blockRenderer.renderSingleBlock(state, posStack, buffers, light, overlay)
            } else {
                posStack.translate(0.5, 0.5, 0.5)
                itemRenderer.renderStatic(
                    stack, ItemDisplayContext.FIXED, light, overlay, posStack, buffers, candelabra.level, 0
                )
            }
            posStack.popPose()
        }
        if (candelabra.isEmpty()) {
            posStack.pushPose()
            posStack.translate(0.5, 1.0, 0.5)
            itemRenderer.renderStatic(
                emptyStack, ItemDisplayContext.FIXED, light, overlay, posStack, buffers, candelabra.level, 0
            )
            posStack.popPose()
        }
        posStack.popPose()
    }

}
