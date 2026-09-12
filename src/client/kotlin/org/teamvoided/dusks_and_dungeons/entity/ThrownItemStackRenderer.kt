package org.teamvoided.dusks_and_dungeons.entity

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemDisplayContext

class ThrownItemStackRenderer(ctx: EntityRendererProvider.Context) : EntityRenderer<ThrownItemStack>(ctx) {

    private val itemRenderer: ItemRenderer = ctx.itemRenderer
    val scale = 1.5f
    val fullBright = false

    override fun getBlockLightLevel(entity: ThrownItemStack, blockPos: BlockPos): Int {
        return if (fullBright) 15 else super.getBlockLightLevel(entity, blockPos)
    }

    override fun render(
        entity: ThrownItemStack, yaw: Float, tickDelta: Float,
        poseStack: PoseStack, multiBufferSource: MultiBufferSource, light: Int,
    ) {
        if (entity.tickCount >= 2 || !(entityRenderDispatcher.camera.entity.distanceToSqr(entity) < MIN_CAMERA_DISTANCE_SQUARED)) {
            poseStack.pushPose()
            poseStack.scale(scale, scale, scale)
            if (entity.onGround()) {
                poseStack.mulPose(Axis.YN.rotationDegrees(entity.getViewYRot(tickDelta)))
                poseStack.mulPose(Axis.ZN.rotationDegrees(entity.getViewXRot(tickDelta) + entity.tickCount + tickDelta))
            }else{
                poseStack.mulPose(Axis.YN.rotationDegrees(90f))
            }
            itemRenderer.renderStatic(
                entity.item, ItemDisplayContext.GROUND,
                light, OverlayTexture.NO_OVERLAY,
                poseStack, multiBufferSource,
                entity.level(), entity.id
            )
            poseStack.popPose()
            super.render(entity, yaw, tickDelta, poseStack, multiBufferSource, light)
        }
    }

    override fun getTextureLocation(entity: ThrownItemStack): ResourceLocation = TextureAtlas.LOCATION_BLOCKS

    companion object {

        const val MIN_CAMERA_DISTANCE_SQUARED = 12.25f

    }
}
