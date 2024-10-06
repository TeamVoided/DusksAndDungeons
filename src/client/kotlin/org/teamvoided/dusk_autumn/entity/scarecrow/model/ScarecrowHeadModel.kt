package org.teamvoided.dusk_autumn.entity.scarecrow.model

import net.minecraft.client.model.*
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity

class ScarecrowHeadModel(root: ModelPart) : ScarecrowEntityModel(root) {

    override fun setAngles(
        scarecrowEntity: ScarecrowEntity,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {
        super.setAngles(scarecrowEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch)
    }

    companion object {
        val texturedModelData: TexturedModelData
            get() {
                val modelData = ModelData()
                val modelPartData = modelData.root
                val post = modelPartData.addChild(
                    "post",
                    ModelPartBuilder.create(),
                    ModelTransform.pivot(0f, POST_OFFSET, 0f)
                )
                val body = post.addChild(
                    "body",
                    ModelPartBuilder.create(),
                    ModelTransform.pivot(0f, BODY_OFFSET, 0f)
                )
                body.addChild(
                    "head",
                    ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(
                            -4f, -8f, -4f,
                            8f, 8f, 8f
                        ),
                    ModelTransform.pivot(0f, -6f, 0f)
                )
                return TexturedModelData.of(modelData, 32, 16)
            }
    }
}