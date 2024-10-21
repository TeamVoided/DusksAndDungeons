package org.teamvoided.dusk_autumn.entity.pumpkin.piffling.model

import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.SinglePartEntityModel
import org.teamvoided.dusk_autumn.entity.PifflingPumpkinEntity
import org.teamvoided.dusk_autumn.entity.pumpkin.piffling.animation.PifflingPumpkinAnimations

class PifflingPumpkinModel(private val root: ModelPart) : SinglePartEntityModel<PifflingPumpkinEntity>() {
    val bone: ModelPart = root.getChild("bone")
    val body: ModelPart = bone.getChild("body")
    val head: ModelPart = body.getChild("head")
    val rightArm: ModelPart = body.getChild("right_arm")
    val leftArm: ModelPart = body.getChild("left_arm")
    val rightLeg: ModelPart = bone.getChild("right_leg")
    val leftLeg: ModelPart = bone.getChild("left_leg")

    override fun getPart(): ModelPart {
        return this.root
    }

    override fun setAngles(
        entity: PifflingPumpkinEntity,
        limbAngle: Float, //f
        limbDistance: Float, //g
        animationProgress: Float, //h
        headYaw: Float, //i
        headPitch: Float //j
    ) {
        this.part.traverse().forEach(ModelPart::resetTransform)
        this.animateWalk(
            PifflingPumpkinAnimations.RUN,
//            if (entity.isSprinting) PifflingPumpkinAnimations.RUN else PifflingPumpkinAnimations.WALK,
            limbAngle,
            limbDistance,
            1f,
            2.5f
        )
        this.animate(entity.idleAnimationState, PifflingPumpkinAnimations.IDLE, animationProgress, 1.0f)
        this.animate(entity.twitchAnimationState, PifflingPumpkinAnimations.TWITCH, animationProgress, 1.0f)
    }

    companion object {
        val texturedModelData: TexturedModelData
            get() {
                val modelData = ModelData()
                val modelPartData = modelData.root
                val base = modelPartData.addChild(
                    "bone",
                    ModelPartBuilder.create(),
//                        .uv(0, 0)
//                        .cuboid(-1f, -1f, -1f, 2f, 2f, 2f),
                    ModelTransform.pivot(0f, 24f, 0f)
                )
                val body = base.addChild(
                    "body",
                    ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-3f, -5f, -2f, 6f, 5f, 4f),
                    ModelTransform.pivot(0f, -3f, 0f)
                )
                body.addChild(
                    "head",
                    ModelPartBuilder.create()
                        .cuboid(0f, 0f, 0f, 0f, 0f, 0f),
                    ModelTransform.pivot(0f, -5f, 0f)
                )
                body.addChild(
                    "right_arm",
                    ModelPartBuilder.create()
                        .uv(20, 0)
                        .cuboid(-1f, 0f, -1f, 2f, 6f, 2f),
                    ModelTransform.of(-4f, -4f, 0f, 0f, 0f, 0.34907f)
                )
                body.addChild(
                    "left_arm",
                    ModelPartBuilder.create()
                        .uv(20, 0)
                        .mirrored()
                        .cuboid(-1f, 0f, -1f, 2f, 6f, 2f),
                    ModelTransform.of(4f, -4f, 0f, 0f, 0f, -0.34907f)
                )
                base.addChild(
                    "right_leg",
                    ModelPartBuilder.create()
                        .uv(0, 9)
                        .cuboid(-1f, 0f, -1f, 2f, 3f, 2f, Dilation(-0.01f)),
                    ModelTransform.pivot(-2f, -3f, 0f)
                )
                base.addChild(
                    "left_leg",
                    ModelPartBuilder.create()
                        .uv(0, 9)
                        .mirrored()
                        .cuboid(-1f, 0f, -1f, 2f, 3f, 2f, Dilation(-0.01f)),
                    ModelTransform.pivot(2f, -3f, 0f)
                )
                return TexturedModelData.of(modelData, 32, 16)
            }
    }
}