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
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.projectile.ItemSupplier
import net.minecraft.world.item.ItemDisplayContext

class ThrownStackRenderer(
    context: EntityRendererProvider.Context,
    private val scale: Float = 2f,
    private val fullBright: Boolean = false
) : EntityRenderer<ThrownItemStack?>(context) {
    private val itemRenderer: ItemRenderer = context.itemRenderer

    override fun getBlockLightLevel(entity: ThrownItemStack?, blockPos: BlockPos?): Int {
        return if (this.fullBright) 15 else super.getBlockLightLevel(entity, blockPos)
    }

    override fun render(
        entity: ThrownItemStack?,
        yaw: Float,
        tickDelta: Float,
        poseStack: PoseStack,
        multiBufferSource: MultiBufferSource?,
        light: Int
    ) {
        if (entity!!.tickCount >= 2 || !(this.entityRenderDispatcher.camera.entity.distanceToSqr(entity) < MIN_CAMERA_DISTANCE_SQUARED)
        ) {
            poseStack.pushPose()
            poseStack.scale(this.scale, this.scale, this.scale)
            poseStack.mulPose(Axis.YN.rotationDegrees(entity.getViewYRot(tickDelta)))
            poseStack.mulPose(Axis.ZN.rotationDegrees(entity.getViewXRot(tickDelta) + entity.age +tickDelta))
            this.itemRenderer.renderStatic(
                (entity as ItemSupplier).item,
                ItemDisplayContext.GROUND,
                light,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                multiBufferSource,
                entity.level(),
                entity.id
            )
            poseStack.popPose()
            super.render(entity, yaw, tickDelta, poseStack, multiBufferSource, light)
        }
    }

    override fun getTextureLocation(entity: ThrownItemStack?): ResourceLocation {
        return TextureAtlas.LOCATION_BLOCKS
    }

    companion object {
        private const val MIN_CAMERA_DISTANCE_SQUARED = 12.25f
    }
}
