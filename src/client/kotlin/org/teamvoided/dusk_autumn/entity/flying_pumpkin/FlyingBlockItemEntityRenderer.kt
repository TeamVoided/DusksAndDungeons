package org.teamvoided.dusk_autumn.entity.BB

import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity
import net.minecraft.util.Identifier
import net.minecraft.util.math.Axis
import net.minecraft.util.math.BlockPos
import org.joml.Math.lerp
import org.teamvoided.dusk_autumn.entity.FlyingBlockItemEntity


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
            matrices.translate(-OFFSET, 0f, -OFFSET)

            matrices.rotateAround(
                Axis.Y_POSITIVE.rotationDegrees(lerp(tickDelta, entity.prevYaw, entity.yaw)), OFFSET, 0f, OFFSET
            )
            matrices.rotateAround(
                Axis.X_NEGATIVE.rotationDegrees(lerp(tickDelta, entity.prevPitch, entity.pitch) + 90),
                OFFSET,
                0f,
                OFFSET
            )
            blockRenderer.renderBlockAsEntity(
                entity.getState(), matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV
            )
            matrices.pop()
            super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
        }
    }

    override fun getTexture(entity: T): Identifier = SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE

    companion object {
        const val OFFSET = 0.5f
    }
}
