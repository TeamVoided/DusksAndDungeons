package org.teamvoided.dusks_and_dungeons.entity.scarecrow.model

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.model.*
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.core.Rotations
import net.minecraft.world.entity.HumanoidArm
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity

open class ScarecrowEntityModel(val root: ModelPart) : HierarchicalModel<ScarecrowEntity>(), ArmedModel,
    HeadedModel {

    val post: ModelPart = root.getChild("post")
    val body: ModelPart = post.getChild("body")

    @JvmField
    val head: ModelPart = body.getChild("head")
    val rightArm: ModelPart = body.getChild("right_arm")
    val leftArm: ModelPart = body.getChild("left_arm")
    val rightLeg: ModelPart = body.getChild("right_leg")
    val leftLeg: ModelPart = body.getChild("left_leg")

    /*open fun setAttributes(model: ScarecrowEntityModel) {
        super.copyStateTo(model)
        model.head.copyTransform(head)
        model.body.copyTransform(body)
        model.rightArm.copyTransform(rightArm)
        model.leftArm.copyTransform(leftArm)
        model.rightLeg.copyTransform(rightLeg)
        model.leftLeg.copyTransform(leftLeg)
    }*/

    open fun setAttributes(model: ScarecrowArmorEntityModel) {
        super.copyPropertiesTo(model)
        model.head.copyFrom(head)
        model.body.copyFrom(body)
        model.rightArm.copyFrom(rightArm)
        model.leftArm.copyFrom(leftArm)
        model.rightLeg.copyFrom(rightLeg)
        model.leftLeg.copyFrom(leftLeg)
    }

    override fun root(): ModelPart = root
    override fun getHead(): ModelPart = head
    fun getArm(arm: HumanoidArm): ModelPart = if (arm == HumanoidArm.LEFT) leftArm else rightArm

    override fun translateToHand(arm: HumanoidArm, matrices: PoseStack) = getArm(arm).translateAndRotate(matrices)
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


    companion object {
        const val POST_OFFSET = 24f
        const val BODY_OFFSET = -25f
        val texturedModelData: LayerDefinition
            get() {
                val modelData = MeshDefinition()
                val modelPartData = modelData.root
                val post = modelPartData.addOrReplaceChild(
                    "post",
                    CubeListBuilder.create().texOffs(0, 0).addBox(
                        -2f, -25f, -2f,
                        4f, 26f, 4f
                    ),
                    PartPose.offset(0f, POST_OFFSET, 0f)
                )
                val body = post.addOrReplaceChild(
                    "body",
                    CubeListBuilder.create().texOffs(0, 0).addBox(
                        -4f, -6f, -2f,
                        8f, 12f, 4f,
                        CubeDeformation(0.025f)
                    ),
                    PartPose.offset(0f, BODY_OFFSET, 0f)
                )
                body.addOrReplaceChild(
                    "head",
                    CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                            -4f, -8f, -4f,
                            8f, 8f, 8f
                        ),
                    PartPose.offset(0f, -6f, 0f)
                )

                body.addOrReplaceChild(
                    "right_arm",
                    CubeListBuilder.create().texOffs(0, 0).addBox(
                        -2f, -2f, -2f,
                        4f, 12f, 4f
                    ),
                    PartPose.offset(-6f, -4f, 0f)
                )
                body.addOrReplaceChild(
                    "left_arm",
                    CubeListBuilder.create()
                        .texOffs(0, 0).mirror()
                        .addBox(
                            -2f, -2f, -2f,
                            4f, 12f, 4f
                        ),
                    PartPose.offset(6f, -4f, 0f)
                )
                body.addOrReplaceChild(
                    "right_leg",
                    CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(
                            -2f, 0f, -2f,
                            4f, 12f, 4f,
                            CubeDeformation(0.01f)
                        ),
                    PartPose.offset(-1.9f, 6f, 0f)
                )
                body.addOrReplaceChild(
                    "left_leg",
                    CubeListBuilder.create()
                        .texOffs(0, 16).mirror()
                        .addBox(
                            -2f, 0f, -2f,
                            4f, 12f, 4f,
                            CubeDeformation(0.01f)
                        ),
                    PartPose.offset(1.9f, 6f, 0f)
                )

                /*  bale.addChild(
                      "cloak",
                      ModelPartBuilder.create()
                          .uv(0, 0)
                          .cuboid(
                              -4f, 0f, -2f,
                              8f, 20f, 4f,
                              Dilation(0.75f)
                          ),
                      ModelTransform.pivot(0f, -8f, 0f)
                  )*/
                return LayerDefinition.create(modelData, 16, 16)
            }
    }
}