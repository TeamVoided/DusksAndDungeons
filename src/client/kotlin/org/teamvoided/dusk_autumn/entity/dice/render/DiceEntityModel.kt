package org.teamvoided.dusk_autumn.entity.dice.render

import net.minecraft.client.model.*
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.model.SinglePartEntityModel
import org.teamvoided.dusk_autumn.entity.DiceEntity

class DiceEntityModel(root: ModelPart) :
    SinglePartEntityModel<DiceEntity>(RenderLayer::getEntitySolid) {
    private val root: ModelPart = root.getChild("die")

    override fun setAngles(
        entity: DiceEntity,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {
        root.pitch = animationProgress * entity.rotationVec.pitch
        root.roll = animationProgress * entity.rotationVec.roll
        root.yaw = animationProgress * entity.rotationVec.yaw
    }

    override fun getPart(): ModelPart = this.root


    companion object {
        val texturedModelData: TexturedModelData
            get() {
                val modelData = ModelData()
                val modelPartData: ModelPartData = modelData.root
                val bone: ModelPartData = modelPartData.addChild(
                    "bone",
                    ModelPartBuilder.create(),
                    ModelTransform.pivot(0.0f, 0.0f, 0.0f)
                )
                bone.addChild(
                    "die",
                    ModelPartBuilder.create()
                        .uv(0, 9).cuboid(
                            -2.0f, -2.0f, -2.0f,
                            4.0f, 4.0f, 4.0f
                        ),
                    ModelTransform.pivot(0.0f, 0.0f, 0.0f)
                )
                return TexturedModelData.of(modelData, 32, 16)
            }
    }
}