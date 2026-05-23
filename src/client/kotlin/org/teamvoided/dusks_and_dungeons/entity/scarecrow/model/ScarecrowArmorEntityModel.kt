package org.teamvoided.dusks_and_dungeons.entity.scarecrow.model

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.model.ArmedModel
import net.minecraft.client.model.HeadedModel
import net.minecraft.client.model.HierarchicalModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.core.Rotations
import net.minecraft.world.entity.HumanoidArm
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel.Companion.BODY_OFFSET
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel.Companion.POST_OFFSET

class ScarecrowArmorEntityModel(root: ModelPart) : HierarchicalModel<ScarecrowEntity>(), ArmedModel,
    HeadedModel {
    val post: ModelPart = root.getChild("post")
    val body: ModelPart = post.getChild("body")

    @JvmField
    val head: ModelPart = post.getChild("head")
    val hat: ModelPart = head.getChild("hat")

    val rightArm: ModelPart = body.getChild("right_arm")
    val leftArm: ModelPart = body.getChild("left_arm")
    val rightLeg: ModelPart = post.getChild("right_leg")
    val leftLeg: ModelPart = post.getChild("left_leg")

    override fun setupAnim(
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

    fun setRotation(part: ModelPart, angle: Rotations) {
        val the = 0.017453292f
        part.xRot = the * angle.x
        part.zRot = the * angle.z
        part.yRot = the * angle.y
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


    override fun root(): ModelPart = post
    override fun getHead(): ModelPart = head
    override fun translateToHand(arm: HumanoidArm, matrices: PoseStack) = getArm(arm).translateAndRotate(matrices)
    fun getArm(arm: HumanoidArm): ModelPart = if (arm == HumanoidArm.LEFT) leftArm else rightArm

    companion object {
        fun getModelData(dilation: CubeDeformation): MeshDefinition {
            val modelData = MeshDefinition()
            val modelPartData = modelData.root
            val post = modelPartData.addOrReplaceChild(
                "post",
                CubeListBuilder.create(),
                PartPose.offset(0f, POST_OFFSET, 0f)
            )
            val body = post.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                    .texOffs(16, 16)
                    .addBox(
                        -4f, -6f, -2f,
                        8f, 12f, 4f,
                        dilation
                    ),
                PartPose.offset(0f, BODY_OFFSET, 0f)
            )
            post.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(
                        -4f, -8f, -4f,
                        8f, 8f, 8f,
                        dilation
                    ),
                PartPose.offset(0f, -6f, 0f)
            ).addOrReplaceChild(
                "hat",
                CubeListBuilder.create()
                    .texOffs(32, 0)
                    .addBox(
                        -4f, 0f, -4f,
                        8f, 8f, 8f,
                        dilation.extend(0.5f)
                    ),
                PartPose.offset(0f, 0f, 0f)
            )
            body.addOrReplaceChild(
                "right_arm",
                CubeListBuilder.create()
                    .texOffs(40, 16)
                    .addBox(
                        -2f, -2f, -2f,
                        4f, 12f, 4f,
                        dilation.extend(-0.1f)
                    ),
                PartPose.offset(-6f, -4f, 0f)
            )
            body.addOrReplaceChild(
                "left_arm",
                CubeListBuilder.create()
                    .texOffs(40, 16).mirror()
                    .addBox(
                        -2f, -2f, -2f,
                        4f, 12f, 4f,
                        dilation.extend(-0.1f)
                    ),
                PartPose.offset(6f, -4f, 0f)
            )
            post.addOrReplaceChild(
                "right_leg",
                CubeListBuilder.create()
                    .texOffs(0, 16)
                    .addBox(
                        -2f, 0f, -2f,
                        4f, 12f, 4f,
                        dilation.extend(-0.1f)
                    ),
                PartPose.offset(-1.9f, 6f, 0f)
            )
            post.addOrReplaceChild(
                "left_leg",
                CubeListBuilder.create()
                    .texOffs(0, 16).mirror()
                    .addBox(
                        -2f, 0f, -2f,
                        4f, 12f, 4f,
                        dilation.extend(-0.1f)
                    ),
                PartPose.offset(1.9f, 6f, 0f)
            )
            return modelData
        }
    }
}