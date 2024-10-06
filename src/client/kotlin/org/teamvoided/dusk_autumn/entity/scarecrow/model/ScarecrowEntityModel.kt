package org.teamvoided.dusk_autumn.entity.scarecrow.model

import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.ModelWithArms
import net.minecraft.client.render.entity.model.ModelWithHead
import net.minecraft.client.render.entity.model.SinglePartEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Arm
import net.minecraft.util.math.EulerAngle
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity

open class ScarecrowEntityModel(private val root: ModelPart) :
    SinglePartEntityModel<ScarecrowEntity>(),
    ModelWithArms,
    ModelWithHead {

    val post: ModelPart = root.getChild("post")
    val body: ModelPart = post.getChild("body")
    val headNotFun: ModelPart = body.getChild("head")
    val rightArm: ModelPart = body.getChild("right_arm")
    val leftArm: ModelPart = body.getChild("left_arm")
    val rightLeg: ModelPart = body.getChild("right_leg")
    val leftLeg: ModelPart = body.getChild("left_leg")

    open fun setAttributes(model: ScarecrowEntityModel) {
        super.copyStateTo(model)
        model.headNotFun.copyTransform(this.headNotFun)
        model.body.copyTransform(this.body)
        model.rightArm.copyTransform(this.rightArm)
        model.leftArm.copyTransform(this.leftArm)
        model.rightLeg.copyTransform(this.rightLeg)
        model.leftLeg.copyTransform(this.leftLeg)
    }

    override fun getPart(): ModelPart {
        return this.root
    }

    override fun getHead(): ModelPart {
        return this.headNotFun
    }

    fun getArm(arm: Arm): ModelPart {
        return if (arm == Arm.LEFT) this.leftArm else this.rightArm
    }

    override fun setAngles(
        scarecrowEntity: ScarecrowEntity,
        limbAngle: Float, //f
        limbDistance: Float, //g
        animationProgress: Float, //h
        headYaw: Float, //i
        headPitch: Float //j
    ) {
        rightLeg.visible = scarecrowEntity.hasLegs
        leftLeg.visible = scarecrowEntity.hasLegs
        setRotation(post, scarecrowEntity.getPostRotation())
        setRotation(body, scarecrowEntity.getBodyRotation())
        setRotation(headNotFun, scarecrowEntity.getHeadRotation())
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

    override fun setArmAngle(arm: Arm, matrices: MatrixStack) {
        getArm(arm).rotate(matrices)
    }

    companion object {
        const val POST_OFFSET = 24f
        const val BODY_OFFSET = -25f
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
                    ModelPartBuilder.create().uv(0, 0).cuboid(
                        -4f, -6f, -2f,
                        8f, 12f, 4f,
                        Dilation(0.025f)
                    ),
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
                body.addChild(
                    "right_arm",
                    ModelPartBuilder.create().uv(0, 0).cuboid(
                        -2f, -2f, -2f,
                        4f, 12f, 4f
                    ),
                    ModelTransform.pivot(-6f, -4f, 0f)
                )
                body.addChild(
                    "left_arm",
                    ModelPartBuilder.create()
                        .uv(0, 0).mirrored()
                        .cuboid(
                            -2f, -2f, -2f,
                            4f, 12f, 4f
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
                            Dilation(0.01f)
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
                            Dilation(0.01f)
                        ),
                    ModelTransform.pivot(1.9f, 6f, 0f)
                )

//                bale.addChild(
//                    "cloak",
//                    ModelPartBuilder.create()
//                        .uv(0, 0)
//                        .cuboid(
//                            -4f, 0f, -2f,
//                            8f, 20f, 4f,
//                            Dilation(0.75f)
//                        ),
//                    ModelTransform.pivot(0f, -8f, 0f)
//                )
                return TexturedModelData.of(modelData, 16, 16)
            }
    }
}