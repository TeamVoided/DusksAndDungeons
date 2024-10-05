package org.teamvoided.dusk_autumn.entity.scarecrow.model

import net.minecraft.client.model.*
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity

class ScarecrowArmorEntityModel(root: ModelPart) : ScarecrowEntityModel(root) {

    val hat: ModelPart = headNotFun.getChild("hat")

    override fun setAngles(
        scarecrowEntity: ScarecrowEntity,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {
        super.setAngles(scarecrowEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch)
        setRotation(body, scarecrowEntity.getBodyRotation())
        setRotation(headNotFun, scarecrowEntity.getHeadRotation())
        setRotation(rightArm, scarecrowEntity.getRightArmRotation())
        setRotation(leftArm, scarecrowEntity.getLeftArmRotation())
        setRotation(rightLeg, scarecrowEntity.getRightLegRotation())
        setRotation(leftLeg, scarecrowEntity.getLeftLegRotation())
    }

    fun setAttributes(model: ScarecrowArmorEntityModel) {
        super.setAttributes(model)
        model.hat.copyTransform(this.headNotFun)
    }

    fun setArmorVisible(visible: Boolean) {
        headNotFun.visible = visible
        hat.visible = visible
        body.visible = visible
        rightArm.visible = visible
        leftArm.visible = visible
        rightLeg.visible = visible
        leftLeg.visible = visible
    }

    companion object {
        fun getModelData(): ModelData {
            return getModelData(Dilation.NONE)
        }

        fun getModelData(dilation: Dilation): ModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val post = modelPartData.addChild(
                "post",
                ModelPartBuilder.create(),
                ModelTransform.pivot(0f, POST_OFFSET, 0f)
            )
            val body = post.addChild(
                "body",
                ModelPartBuilder.create()
                    .uv(16, 16)
                    .cuboid(
                        -4f, -6f, -2f,
                        8f, 12f, 4f,
                        dilation
                    ),
                ModelTransform.pivot(0f, BODY_OFFSET, 0f)
            )
            body.addChild(
                "head",
                ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(
                        -4f, -8f, -4f,
                        8f, 8f, 8f,
                        dilation
                    ),
                ModelTransform.pivot(0f, -6f, 0f)
            ).addChild(
                "hat",
                ModelPartBuilder.create()
                    .uv(32, 0)
                    .cuboid(
                        -4f, 0f, -4f,
                        8f, 8f, 8f,
                        dilation.add(0.5f)
                    ),
                ModelTransform.pivot(0f, 0f, 0f)
            )
            body.addChild(
                "right_arm",
                ModelPartBuilder.create()
                    .uv(40, 16)
                    .cuboid(
                        -2f, -2f, -2f,
                        4f, 12f, 4f,
                        dilation.add(-0.1f)
                    ),
                ModelTransform.pivot(-6f, -4f, 0f)
            )
            body.addChild(
                "left_arm",
                ModelPartBuilder.create()
                    .uv(40, 16).mirrored()
                    .cuboid(
                        -2f, -2f, -2f,
                        4f, 12f, 4f,
                        dilation.add(-0.1f)
                    ),
                ModelTransform.pivot(6f, -4f, 0f)
            )
            body.addChild(
                "right_leg",
                ModelPartBuilder.create()
                    .uv(0, 16)
                    .cuboid(
                        -2f, 0f, -2f,
                        4f, 12f, 4f,
                        dilation.add(-0.1f)
                    ),
                ModelTransform.pivot(-1.9f, 6f, 0f)
            )
            body.addChild(
                "left_leg",
                ModelPartBuilder.create()
                    .uv(0, 16).mirrored()
                    .cuboid(
                        -2f, 0f, -2f,
                        4f, 12f, 4f,
                        dilation.add(-0.1f)
                    ),
                ModelTransform.pivot(1.9f, 6f, 0f)
            )
            return modelData
        }
    }
}