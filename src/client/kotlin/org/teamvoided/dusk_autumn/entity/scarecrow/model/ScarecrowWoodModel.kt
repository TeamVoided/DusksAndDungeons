package org.teamvoided.dusk_autumn.entity.scarecrow.model

import net.minecraft.client.model.*
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity

class ScarecrowWoodModel(root: ModelPart) : ScarecrowEntityModel(root) {

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
                    ModelPartBuilder.create().uv(0, 0).cuboid(
                        -2f, -25f, -2f,
                        4f, 26f, 4f
                    ),
                    ModelTransform.pivot(0f, POST_OFFSET, 0f)
                )
                val body = post.addChild(
                    "body",
                    ModelPartBuilder.create()
                        .uv(16, 0)
                        .cuboid(
                            -4f, -10f, -1f,
                            8f, 2f, 2f
                        ),
                    ModelTransform.pivot(0f, BODY_OFFSET, 0f)
                )
                body.addChild(
                    "head",
                    ModelPartBuilder.create(),
                    ModelTransform.pivot(0f, 0f, 0f)
                )
                body.addChild(
                    "right_arm",
                    ModelPartBuilder.create()
                        .uv(16, 16)
                        .cuboid(
                            -2f, -2f, -2f,
                            4f, 12f, 4f
                        ),
                    ModelTransform.pivot(-6f, -4f, 0f)
                )
                body.addChild(
                    "left_arm",
                    ModelPartBuilder.create()
                        .uv(16, 16).mirrored()
                        .cuboid(
                            -2f, -2f, -2f,
                            4f, 12f, 4f
                        ),
                    ModelTransform.pivot(6f, -4f, 0f)
                )
                body.addChild(
                    "right_leg",
                    ModelPartBuilder.create()
                        .uv(32, 16)
                        .cuboid(
                            -2f, 0f, -2f,
                            4f, 12f, 4f
                        ),
                    ModelTransform.pivot(-1.9f, 6f, 0f)
                )
                body.addChild(
                    "left_leg",
                    ModelPartBuilder.create()
                        .uv(32, 16).mirrored()
                        .cuboid(
                            -2f, 0f, -2f,
                            4f, 12f, 4f
                        ),
                    ModelTransform.pivot(1.9f, 6f, 0f)
                )
                return TexturedModelData.of(modelData, 64, 32)
            }
    }
}