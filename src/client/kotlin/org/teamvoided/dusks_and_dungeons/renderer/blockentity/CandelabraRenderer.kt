package org.teamvoided.dusks_and_dungeons.renderer.blockentity

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.AbstractCandleBlock
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.MISSING_OFFSET
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity

class CandelabraRenderer(ctx: BlockEntityRendererProvider.Context) : BlockEntityRenderer<CandelabraBlockEntity> {

    internal val blockRenderer = ctx.blockRenderDispatcher
    internal val itemRenderer = ctx.itemRenderer
    val emptyStack: ItemStack = Items.BARRIER.defaultInstance

    override fun render(
        candelabra: CandelabraBlockEntity,
        tickDelta: Float, poseStack: PoseStack, buffers: MultiBufferSource, light: Int, overlay: Int,
    ) {
        val state = candelabra.blockState
        poseStack.pushPose()

        val dir = state.getValue(CandelabraBlock.FACING)
        poseStack.rotateAround(Axis.YP.rotationDegrees(-dir.toYRot() - 180), 0.5f, 0.5f, 0.5f)

        val candles = state.getValue(CandelabraBlock.CANDLES)
        val isLit = state.getValue(CandelabraBlock.LIT)

        val offsets = Candelabra.OFFSETS[candles - 1]
        for ((index, stack) in candelabra.getCandles().withIndex()) {
            if (stack.isEmpty) {
                continue
            }
            val item = stack.item
            val off = offsets.getOrElse(index) { MISSING_OFFSET }
            poseStack.pushPose()
            poseStack.translate(off.x, off.y, off.z)
            if (item is BlockItem) {
                var state = candelabra.internalBlockStates[index]
                state = state.trySetValue(AbstractCandleBlock.LIT, isLit)
                blockRenderer.renderSingleBlock(state, poseStack, buffers, light, overlay)
            } else {
                poseStack.translate(0.5, 0.5, 0.5)
                itemRenderer.renderStatic(
                    stack, ItemDisplayContext.FIXED, light, overlay, poseStack, buffers, candelabra.level, 0
                )
            }
            poseStack.popPose()
        }
        if (candelabra.isEmpty()) {
            poseStack.pushPose()
            poseStack.translate(0.5, 1.0, 0.5)
            itemRenderer.renderStatic(
                emptyStack, ItemDisplayContext.FIXED, light, overlay, poseStack, buffers, candelabra.level, 0
            )
            poseStack.popPose()
        }
        poseStack.popPose()
    }

}
