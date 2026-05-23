package org.teamvoided.dusks_and_dungeons.entity.scarecrow.model

import net.minecraft.client.model.*
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity

class ScarecrowHeadModel(root: ModelPart) : ScarecrowEntityModel(root) {

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
                    CubeListBuilder.create(),
                    PartPose.offset(0f, POST_OFFSET, 0f)
                )
                val body = post.addOrReplaceChild(
                    "body",
                    CubeListBuilder.create(),
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
                return LayerDefinition.create(modelData, 32, 16)
            }
    }
}