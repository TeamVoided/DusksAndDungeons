package org.teamvoided.dusks_and_dungeons.entity.raccoon

import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.AnimalModel
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class RaccoonEntityModel(val root: ModelPart) : AnimalModel<RaccoonEntity>() {

    override fun getHeadParts(): Iterable<ModelPart?> {
        return listOf()
    }

    override fun getBodyParts(): Iterable<ModelPart?> {
        return listOf(root)
    }

    override fun setAngles(
        entity: RaccoonEntity?,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {
    }

    companion object {
        val texturedModelData: TexturedModelData
            get() {
                val modelData = ModelData()
                val modelPartData = modelData.root

                val body: ModelPartData = modelPartData.addChild(
                    "body",
                    ModelPartBuilder().uv(19, 12)
                        .cuboid(-4F, -6F, -4F, 8F, 11F, 8F),
                    ModelTransform.of(0F, 17F, 3F, 1.5708f, 0F, 0F)
                )

                body.addChild(
                    "head",
                    ModelPartBuilder().uv(1, 5)
                        .cuboid(-3F, -2F, -5F, 6F, 5F, 5F)
                        .uv(3, 1).cuboid(-3F, -4F, -4F, 2F, 2F, 1F)
                        .uv(11, 1).cuboid(1F, -4F, -4F, 2F, 2F, 1F)
                        .uv(6, 16).cuboid(-1F, 1F, -7F, 2F, 2F, 2F),
                    ModelTransform.of(0F, -6F, 0F, -1.5708f, 0F, 0F)
                )

                body.addChild(
                    "tail",
                    ModelPartBuilder().uv(47, 1)
                        .cuboid(-2F, -1.9239f, -2.3827f, 4F, 11F, 4F),
                    ModelTransform.of(0F, 6F, 1F, -0.3927f, 0F, 0F)
                )

                body.addChild(
                    "leg_front_right",
                    ModelPartBuilder().uv(1, 25)
                        .cuboid(-1F, -1F, -1F, 2F, 4F, 2F),
                    ModelTransform.of(-2F, -4F, -4F, -1.5708f, 0F, 0F)
                )

                body.addChild(
                    "leg_front_left",
                    ModelPartBuilder().uv(10, 25)
                        .cuboid(-1F, -1F, -1F, 2F, 4F, 2F),
                    ModelTransform.of(2F, -4F, -4F, -1.5708f, 0F, 0F)
                )

                modelPartData.addChild(
                    "leg_back_right",
                    ModelPartBuilder().uv(1, 25)
                        .cuboid(-1F, -1F, -1F, 2F, 4F, 2F),
                    ModelTransform.pivot(-2F, 21F, 6F)
                )

                modelPartData.addChild(
                    "leg_back_left",
                    ModelPartBuilder().uv(10, 25)
                        .cuboid(-1F, -1F, -1F, 2F, 4F, 2F),
                    ModelTransform.pivot(2F, 21F, 6F)
                )

                return TexturedModelData.of(modelData, 64, 32)
            }
    }
}