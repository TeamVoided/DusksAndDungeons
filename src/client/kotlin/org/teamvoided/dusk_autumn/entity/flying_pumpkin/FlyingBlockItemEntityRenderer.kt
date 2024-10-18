package org.teamvoided.dusk_autumn.entity.flying_pumpkin

import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.Axis
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import org.joml.Math.lerp
import org.teamvoided.dusk_autumn.entity.FlyingBlockItemEntity
import org.teamvoided.dusk_autumn.util.sendMessageIngame


class FlyingBlockItemEntityRenderer<T>(
    ctx: EntityRendererFactory.Context, private val scale: Float, private val lit: Boolean
) : EntityRenderer<T>(ctx) where T : Entity, T : FlyingBlockItemEntity {
    private val blockRenderer = ctx.blockRenderManager

    constructor(context: EntityRendererFactory.Context) : this(context, 1.0f, false)

    override fun getBlockLight(entity: T, pos: BlockPos): Int {
        return if (this.lit) 15 else super.getBlockLight(entity, pos)
    }

    override fun render(
        entity: T,
        yaw: Float,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int
    ) {
        if (entity.age >= 2) {
            matrices.push()
            matrices.scale(this.scale, this.scale, this.scale)
            matrices.rotate(
                Axis.Y_POSITIVE.rotationDegrees(
                    MathHelper.lerp(tickDelta, entity.prevYaw, entity.yaw) + 90f
                )
            )
            matrices.rotate(
                Axis.Z_POSITIVE.rotationDegrees(
                    -MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch)
                )
            )
            matrices.translate(-OFFSET, -OFFSET, -OFFSET)
            blockRenderer.renderBlockAsEntity(
                entity.getState(), matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV
            )
            matrices.pop()
            sendMessageIngame(entity.yaw.toString())
            super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
        }
    }

    override fun getTexture(entity: T): Identifier = SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE

    companion object {
        private val distance = MathHelper.square(3.5f)
        const val OFFSET = 0.5f
    }
}
