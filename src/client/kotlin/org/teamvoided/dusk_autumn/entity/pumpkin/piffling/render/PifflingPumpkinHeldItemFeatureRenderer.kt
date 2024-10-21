package org.teamvoided.dusk_autumn.entity.pumpkin.piffling.render

import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.item.HeldItemRenderer
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemStack
import org.joml.Quaternionf
import org.teamvoided.dusk_autumn.entity.PifflingPumpkinEntity
import org.teamvoided.dusk_autumn.entity.pumpkin.piffling.model.PifflingPumpkinModel

class PifflingPumpkinHeldItemFeatureRenderer(
    context: FeatureRendererContext<PifflingPumpkinEntity, PifflingPumpkinModel>,
    private val heldItemRenderer: HeldItemRenderer
) : FeatureRenderer<PifflingPumpkinEntity, PifflingPumpkinModel>(context) {
    val scale = 0.625f
    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        entity: PifflingPumpkinEntity,
        f: Float,
        g: Float,
        h: Float,
        j: Float,
        k: Float,
        l: Float
    ) {
        val mainhand = entity.getEquippedStack(EquipmentSlot.MAINHAND)
        if (!mainhand.isEmpty) {
            matrices.push()
            val model = (this.contextModel as PifflingPumpkinModel)
            model.rightArm.moveRelativeToHand(matrices, model)
            val scale = 1f
            matrices.translate(0.0f, -0.5f, -0.55f)
//            matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(180.0f))
            matrices.scale(-scale, -scale, scale)
            heldItemRenderer.renderItem(
                entity,
                ItemStack(mainhand.item),
                ModelTransformationMode.THIRD_PERSON_RIGHT_HAND,
                false,
                matrices,
                vertexConsumers,
                light
            )
            matrices.pop()
        }
    }

    private fun ModelPart.moveRelativeToHand(matrix: MatrixStack, model: PifflingPumpkinModel) {
        val bone = model.bone
        val body = model.body

        matrix.translate(bone.pivotX / 16.0f, bone.pivotY / 16.0f, bone.pivotZ / 16.0f)
        matrix.rotate(Quaternionf().rotationZYX(bone.roll, bone.yaw, bone.pitch))

        matrix.translate(body.pivotX / 16.0f, body.pivotY / 16.0f, body.pivotZ / 16.0f)
        matrix.rotate(Quaternionf().rotationZYX(body.roll, body.yaw, body.pitch))

        matrix.translate(this.pivotX / 16.0f, this.pivotY / 16.0f, this.pivotZ / 16.0f)
        matrix.rotate(Quaternionf().rotationZYX(this.roll, this.yaw, this.pitch))

        if (this.scaleX != 1.0f || (this.scaleY != 1.0f) || (this.scaleZ != 1.0f)) {
            matrix.scale(this.scaleX, this.scaleY, this.scaleZ)
        }
    }
}