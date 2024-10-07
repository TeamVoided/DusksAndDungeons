package org.teamvoided.dusk_autumn.entity.dice.render

import net.minecraft.client.model.*
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.model.SinglePartEntityModel
import net.minecraft.util.math.MathHelper
import org.joml.Math.lerp
import org.teamvoided.dusk_autumn.entity.DiceEntity

class DiceEntityModel(root: ModelPart) : SinglePartEntityModel<DiceEntity>() {
    private val root: ModelPart = root.getChild("die")

    override fun getPart(): ModelPart = this.root

    override fun setAngles(
        entity: DiceEntity,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {
//        root.pitch = animationProgress * entity.rotationVec.pitch
//        root.roll = animationProgress * entity.rotationVec.roll
//        root.yaw = animationProgress * entity.rotationVec.yaw

        val lerp = entity.timeSinceLastFall.toFloat() / 100f
        root.pitch = lerp(lerp, entity.lerpRotationValues(entity.sideUp).pitch, root.pitch)
        root.roll = lerp(lerp, entity.lerpRotationValues(entity.sideUp).roll, root.roll)
        root.yaw = lerp(lerp, entity.lerpRotationValues(entity.sideUp).yaw, root.yaw)
    }

    companion object {
        val texturedModelData: TexturedModelData
            get() {
                val modelData = ModelData()
                val modelPartData: ModelPartData = modelData.root
                modelPartData.addChild(
                    "die",
                    ModelPartBuilder.create()
                        .uv(0, 0).cuboid(
                            -4.0f, -4.0f, -4.0f,
                            8.0f, 8.0f, 8.0f
                        ),
                    ModelTransform.pivot(0.0f, 4.0f, 0.0f)
                )
                return TexturedModelData.of(modelData, 32, 16)
            }
    }
}