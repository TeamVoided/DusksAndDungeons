package org.teamvoided.dusks_and_dungeons.entity.scarecrow.model

import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity

class ScarecrowWoodModel(root: ModelPart) : ScarecrowEntityModel(root) {

    override fun setupAnim(
        scarecrowEntity: ScarecrowEntity,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {
        super.setupAnim(scarecrowEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch)
    }

    companion object {
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
                    CubeListBuilder.create()
                        .texOffs(16, 0)
                        .addBox(
                            -4f, -10f, -1f,
                            8f, 2f, 2f
                        ),
                    PartPose.offset(0f, BODY_OFFSET, 0f)
                )
                body.addOrReplaceChild(
                    "head",
                    CubeListBuilder.create(),
                    PartPose.offset(0f, 0f, 0f)
                )
                body.addOrReplaceChild(
                    "right_arm",
                    CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(
                            -2f, -2f, -2f,
                            4f, 12f, 4f
                        ),
                    PartPose.offset(-6f, -4f, 0f)
                )
                body.addOrReplaceChild(
                    "left_arm",
                    CubeListBuilder.create()
                        .texOffs(16, 16).mirror()
                        .addBox(
                            -2f, -2f, -2f,
                            4f, 12f, 4f
                        ),
                    PartPose.offset(6f, -4f, 0f)
                )
                body.addOrReplaceChild(
                    "right_leg",
                    CubeListBuilder.create()
                        .texOffs(32, 16)
                        .addBox(
                            -2f, 0f, -2f,
                            4f, 12f, 4f
                        ),
                    PartPose.offset(-1.9f, 6f, 0f)
                )
                body.addOrReplaceChild(
                    "left_leg",
                    CubeListBuilder.create()
                        .texOffs(32, 16).mirror()
                        .addBox(
                            -2f, 0f, -2f,
                            4f, 12f, 4f
                        ),
                    PartPose.offset(1.9f, 6f, 0f)
                )
                return LayerDefinition.create(modelData, 64, 32)
            }
    }
}