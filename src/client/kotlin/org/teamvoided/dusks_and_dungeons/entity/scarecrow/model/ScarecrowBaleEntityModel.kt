//package org.teamvoided.dusk_autumn.entity.scarecrow.model
//
//import net.minecraft.client.model.*
//import org.teamvoided.dusk_autumn.entity.ScarecrowEntity
//
//class ScarecrowBaleEntityModel(root: ModelPart) : ScarecrowEntityModel(root) {
//    val rightArm: ModelPart = body.getChild("right_arm")
//    val leftArm: ModelPart = body.getChild("left_arm")
//    val rightLeg: ModelPart = body.getChild("right_leg")
//    val leftLeg: ModelPart = body.getChild("left_leg")
//    override fun setAngles(
//        scarecrowEntity: ScarecrowEntity,
//        limbAngle: Float,
//        limbDistance: Float,
//        animationProgress: Float,
//        headYaw: Float,
//        headPitch: Float
//    ) {
//        super.setAngles(scarecrowEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch)
//        setRotation(head, scarecrowEntity.getHeadRotation())
//        setRotation(rightArm, scarecrowEntity.getRightArmRotation())
//        setRotation(leftArm, scarecrowEntity.getLeftArmRotation())
//        setRotation(rightLeg, scarecrowEntity.getRightLegRotation())
//        setRotation(leftLeg, scarecrowEntity.getLeftLegRotation())
//        leftLeg.visible = scarecrowEntity.hasLegs
//        rightLeg.visible = scarecrowEntity.hasLegs
//    }
//
//    companion object {
//        fun texturedModelData(dilation: Dilation): TexturedModelData {
//            val modelData = ModelData()
//            val modelPartData = modelData.root
//            val post = modelPartData.addChild(
//                "post",
//                ModelPartBuilder.create(),
//                ModelTransform.pivot(0f, postHeight, 0f)
//            )
//            val bale = post.addChild(
//                "body",
//                ModelPartBuilder.create(),
//                ModelTransform.pivot(0f, postHeight, 0f)
//            )
//            bale.addChild(
//                "head",
//                ModelPartBuilder.create()
//                    .uv(0, 0)
//                    .cuboid(
//                        -4.0f, -8.0f, -4.0f,
//                        8.0f, 8.0f, 8.0f,
//                        dilation
//                    ),
//                ModelTransform.pivot(0.0f, 1.0f, 0.0f)
//            )
//            bale.addChild(
//                "right_arm",
//                ModelPartBuilder.create()
//                    .uv(40, 16)
//                    .cuboid(
//                        -2.0f, 0.0f, -2.0f,
//                        4.0f, 12.0f, 4.0f,
//                        dilation.add(-0.1f)
//                    ),
//                ModelTransform.pivot(-6f, 10.0f, 0.0f)
//            )
//            bale.addChild(
//                "left_arm",
//                ModelPartBuilder.create()
//                    .uv(40, 16).mirrored()
//                    .cuboid(
//                        -2.0f, 0.0f, -2.0f,
//                        4.0f, 12.0f, 4.0f,
//                        dilation.add(-0.1f)
//                    ),
//                ModelTransform.pivot(6f, 10.0f, 0.0f)
//            )
//            bale.addChild(
//                "right_leg",
//                ModelPartBuilder.create()
//                    .uv(0, 16)
//                    .cuboid(
//                        -2.0f, 0.0f, -2.0f,
//                        4.0f, 12.0f, 4.0f,
//                        dilation.add(-0.1f)
//                    ),
//                ModelTransform.pivot(-1.9f, 11.0f, 0.0f)
//            )
//            bale.addChild(
//                "left_leg",
//                ModelPartBuilder.create()
//                    .uv(0, 16).mirrored()
//                    .cuboid(
//                        -2.0f, 0.0f, -2.0f,
//                        4.0f, 12.0f, 4.0f,
//                        dilation.add(-0.1f)
//                    ),
//                ModelTransform.pivot(1.9f, 11.0f, 0.0f)
//            )
//            return TexturedModelData.of(modelData, 16, 16)
//        }
//    }
//}