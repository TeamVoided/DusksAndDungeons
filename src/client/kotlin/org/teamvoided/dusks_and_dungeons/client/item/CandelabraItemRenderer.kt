package org.teamvoided.dusks_and_dungeons.client.item

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.BlockPos
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.AABB
import org.joml.Vector3f
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraContents
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity
import org.teamvoided.dusks_and_dungeons.init.DnDDataComponents.CANDELABRA_CONTENTS

object CandelabraItemRenderer {

    fun register(item: Item, block: CandelabraBlock) {
        BuiltinItemRendererRegistry.INSTANCE.register(item) { stack, ctx, poseStack, buffers, light, overlay ->
            renderCandelabraItem(block, stack, ctx, poseStack, buffers, light, overlay)
        }
    }

    var CANDELABRA_ITEM_CACHE = mutableMapOf<CandelabraContents, CandelabraBlockEntity>()

    fun getCandelabra(stack: ItemStack, level: ClientLevel, block: CandelabraBlock): CandelabraBlockEntity? {
        val contents = stack.get(CANDELABRA_CONTENTS) ?: return null
        if (contents.isEmpty()) {
            return null
        }

        var candelabra = CANDELABRA_ITEM_CACHE[contents]

        if (candelabra == null) {
            candelabra = CandelabraBlockEntity(
                BlockPos.ZERO,
                block.defaultBlockState()
                    .setValue(CandelabraBlock.LIT, true)
                    .setValue(CandelabraBlock.CANDLES, Candelabra.getSlotCount(stack))
            )
            candelabra.applyComponentsFromItemStack(stack)
            candelabra.updateStateCache(level)
            CANDELABRA_ITEM_CACHE[contents] = candelabra
        }

        return candelabra
    }

    var vecPos: Vector3f? = null
    var bb = AABB(0.0, 0.0, 0.0, 0.1, 0.1, 0.1)

    fun renderCandelabraItem(
        block: CandelabraBlock, stack: ItemStack?, ctx: ItemDisplayContext,
        poseStack: PoseStack, buffers: MultiBufferSource, light: Int, overlay: Int,
    ) {
        stack ?: return
        val level = Minecraft.getInstance().level ?: return
        val candelabra = getCandelabra(stack, level, block) ?: return
        Minecraft.getInstance().blockEntityRenderDispatcher.renderItem(candelabra, poseStack, buffers, light, overlay)

        // TODO(1.0) make particles work
//        val lineConsumer = buffers.getBuffer(RenderType.LINES)
//        LevelRenderer.renderLineBox(poseStack, lineConsumer, bb, 1f, 1f, 1f, 1f)
        @Suppress("ControlFlowWithEmptyBody")
        if (ctx == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || ctx == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || ctx == ItemDisplayContext.HEAD) {
//            poseStack.pushPose()
//            poseStack.translate(-10f, 10f, 10f)
//            val scale = -0.0001f
//            poseStack.scale(scale, -1f, scale)
//            vecPos = poseStack.last().pose().transformPosition(0f, 0f, 0f, Vector3f())
//            poseStack.popPose()
        }
    }

}