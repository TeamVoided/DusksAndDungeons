package org.teamvoided.dusk_autumn.entity.scarecrow.render

import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.SinglePartEntityModel
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity

class ScarecrowEntityModel(private val root: ModelPart) : SinglePartEntityModel<ScarecrowEntity>() {
    val post: ModelPart = root.getChild("post")
    val bale: ModelPart = post.getChild("bale")
    val head: ModelPart = bale.getChild("head")
    val rightArm: ModelPart = bale.getChild("right_arm")
    val leftArm: ModelPart = bale.getChild("left_arm")

    override fun getPart(): ModelPart {
        return this.root
    }

    override fun setAngles(
        scarecrowEntity: ScarecrowEntity,
        limbAngle: Float, //f
        limbDistance: Float, //g
        animationProgress: Float, //h
        headYaw: Float, //i
        headPitch: Float //j
    ) {
        val the = 0.017453292f
        post.pitch = the * scarecrowEntity.getBodyRotation().pitch
        post.roll = the * scarecrowEntity.getBodyRotation().roll
        post.yaw = the * scarecrowEntity.getBodyRotation().yaw
        head.pitch = the * scarecrowEntity.getHeadRotation().pitch
        head.roll = the * scarecrowEntity.getHeadRotation().roll
        head.yaw = the * scarecrowEntity.getHeadRotation().yaw
        rightArm.pitch = the * scarecrowEntity.getRightArmRotation().pitch
        rightArm.roll = the * scarecrowEntity.getRightArmRotation().roll
        rightArm.yaw = the * scarecrowEntity.getRightArmRotation().yaw
        leftArm.pitch = the * scarecrowEntity.getLeftArmRotation().pitch
        leftArm.roll = the * scarecrowEntity.getLeftArmRotation().roll
        leftArm.yaw = the * scarecrowEntity.getLeftArmRotation().yaw
    }

    companion object {
        val texturedModelData: TexturedModelData
            get() {
                val modelData = ModelData()
                val modelPartData = modelData.root
                val post = modelPartData.addChild(
                    "post",
                    ModelPartBuilder.create().uv(0, 0).cuboid(
                        -2f, -14f, -2f,
                        4f, 18f, 4f
                    ),
                    ModelTransform.pivot(0f, 20f, 0f)
                )
                val bale = post.addChild(
                    "bale",
                    ModelPartBuilder.create().uv(0, 0).cuboid(
                        -4f, -8f, -2f,
                        8f, 12f, 4f,
                        Dilation(0.2f)
                    ),
                    ModelTransform.pivot(0f, -18f, 0f)
                )
                bale.addChild(
                    "head",
                    ModelPartBuilder.create().uv(0, 0).cuboid(
                        -4f, -8f, -4f,
                        8f, 8f, 8f
                    ),
                    ModelTransform.pivot(0f, -8f, 0f)
                )
                bale.addChild(
                    "right_arm",
                    ModelPartBuilder.create().uv(0, 0).cuboid(
                        -1f, -12f, -1f,
                        2f, 12f, 2f
                    ),
                    ModelTransform.pivot(-4f, -7f, 0f)
                )
                bale.addChild(
                    "left_arm",
                    ModelPartBuilder.create().uv(0, 0).cuboid(
                        -1f, -12f, -1f,
                        2f, 12f, 2f
                    ).mirrored(),
                    ModelTransform.pivot(4f, -7f, 0f)
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