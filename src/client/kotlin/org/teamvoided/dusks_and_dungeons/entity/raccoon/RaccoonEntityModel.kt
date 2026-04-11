package org.teamvoided.dusks_and_dungeons.entity.raccoon

import net.minecraft.client.model.ModelData
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.entity.model.AnimalModel
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class RaccoonEntityModel(val root: ModelPart) : AnimalModel<RaccoonEntity>() {
    override fun getHeadParts(): Iterable<ModelPart?> {
        return listOf()
    }

    override fun getBodyParts(): Iterable<ModelPart?> {
        return listOf()
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
                return TexturedModelData.of(modelData, 16, 16)
            }
    }
}