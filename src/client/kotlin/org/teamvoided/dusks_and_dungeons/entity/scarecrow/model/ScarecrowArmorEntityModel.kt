package org.teamvoided.dusks_and_dungeons.entity.scarecrow.model

import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.ModelWithArms
import net.minecraft.client.render.entity.model.ModelWithHead
import net.minecraft.client.render.entity.model.SinglePartEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Arm
import net.minecraft.util.math.EulerAngle
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel.Companion.BODY_OFFSET
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel.Companion.POST_OFFSET

class ScarecrowArmorEntityModel(root: ModelPart) : SinglePartEntityModel<ScarecrowEntity>(), ModelWithArms,
    ModelWithHead {
    val post: ModelPart = root.getChild("post")
    val body: ModelPart = post.getChild("body")

    @JvmField
    val head: ModelPart = post.getChild("head")
    val hat: ModelPart = head.getChild("hat")

    val rightArm: ModelPart = body.getChild("right_arm")
    val leftArm: ModelPart = body.getChild("left_arm")
    val rightLeg: ModelPart = post.getChild("right_leg")
    val leftLeg: ModelPart = post.getChild("left_leg")

    override fun setAngles(
        scarecrowEntity: ScarecrowEntity,
        limbAngle: Float, //f
        limbDistance: Float, //g
        animationProgress: Float, //h
        headYaw: Float, //i
        headPitch: Float, //j
    ) {
        rightLeg.visible = scarecrowEntity.hasLegs
        leftLeg.visible = scarecrowEntity.hasLegs
        setRotation(post, scarecrowEntity.getPostRotation())
        setRotation(body, scarecrowEntity.getBodyRotation())
        setRotation(head, scarecrowEntity.getHeadRotation())
        setRotation(hat, scarecrowEntity.getHeadRotation())
        setRotation(rightArm, scarecrowEntity.getRightArmRotation())
        setRotation(leftArm, scarecrowEntity.getLeftArmRotation())
        setRotation(rightLeg, scarecrowEntity.getRightLegRotation())
        setRotation(leftLeg, scarecrowEntity.getLeftLegRotation())
    }

    fun setRotation(part: ModelPart, angle: EulerAngle) {
        val the = 0.017453292f
        part.pitch = the * angle.pitch
        part.roll = the * angle.roll
        part.yaw = the * angle.yaw
    }

    fun setArmorVisible(visible: Boolean) {
        head.visible = visible
        hat.visible = visible
        body.visible = visible
        rightArm.visible = visible
        leftArm.visible = visible
        rightLeg.visible = visible
        leftLeg.visible = visible
    }


    override fun getPart(): ModelPart = post
    override fun getHead(): ModelPart = head
    override fun setArmAngle(arm: Arm, matrices: MatrixStack) = getArm(arm).rotate(matrices)
    fun getArm(arm: Arm): ModelPart = if (arm == Arm.LEFT) leftArm else rightArm

    companion object {
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
            post.addChild(
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
            post.addChild(
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
            post.addChild(
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