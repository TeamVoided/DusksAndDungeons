package org.teamvoided.dusk_autumn.entity.scarecrow.render

import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.EntityModelLoader
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity
import org.teamvoided.dusk_autumn.entity.scarecrow.model.ScarecrowEntityModel
import org.teamvoided.dusk_autumn.entity.scarecrow.model.ScarecrowWoodModel

class ScarecrowWoodFeatureRenderer(
    context: FeatureRendererContext<ScarecrowEntity, ScarecrowEntityModel>,
    loader: EntityModelLoader
) : FeatureRenderer<ScarecrowEntity, ScarecrowEntityModel>(context) {
    private val model: ScarecrowWoodModel =
        ScarecrowWoodModel(loader.getModelPart(DnDEntityModelLayers.SCARECROW_WOOD))

    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        i: Int,
        scarecrowEntity: ScarecrowEntity,
        f: Float,
        g: Float,
        h: Float,
        j: Float,
        k: Float,
        l: Float
    ) {
        val woodBlock = scarecrowEntity.getDecorationItems().toList()[0]
        val texture = if (woodBlock.isEmpty) woodTextureId("default")
        else woodTextureId(Registries.ITEM.getId(woodBlock.item).path)
        render(
            this.contextModel,
            this.model,
            texture,
            matrices,
            vertexConsumers,
            i,
            scarecrowEntity,
            f,
            g,
            j,
            k,
            l,
            h,
            -1
        )
    }

    companion object {
        private fun woodTextureId(string: String): Identifier =
            id("textures/entity/scarecrow/wood/$string.png")
    }
}