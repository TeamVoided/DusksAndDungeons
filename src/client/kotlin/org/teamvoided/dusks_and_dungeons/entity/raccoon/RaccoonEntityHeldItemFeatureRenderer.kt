package org.teamvoided.dusks_and_dungeons.entity.raccoon

import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.item.HeldItemRenderer
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class RaccoonEntityHeldItemFeatureRenderer(
    context: FeatureRendererContext<RaccoonEntity, RaccoonEntityModel>,
    private val heldItemRenderer: HeldItemRenderer
) :
    FeatureRenderer<RaccoonEntity, RaccoonEntityModel>(
        context
    ) {

    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        entity: RaccoonEntity,
        limbAngle: Float,
        limbDistance: Float,
        tickDelta: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {
        matrices.push()
        matrices.translate(0.0, -0.1, 0.0)
        val stack = entity.getEquippedStack(EquipmentSlot.MAINHAND)
        heldItemRenderer.renderItem(entity, stack,
            ModelTransformationMode.GROUND, false,
            matrices, vertexConsumers, light
        )
        matrices.pop()
    }
}