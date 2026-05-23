package org.teamvoided.dusks_and_dungeons.entity.scarecrow.render

import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.layers.RenderLayer
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.client.model.geom.EntityModelSet
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowEntityModel
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.model.ScarecrowWoodModel

class ScarecrowWoodFeatureRenderer(
    context: RenderLayerParent<ScarecrowEntity, ScarecrowEntityModel>,
    loader: EntityModelSet
) : RenderLayer<ScarecrowEntity, ScarecrowEntityModel>(context) {
    private val model: ScarecrowWoodModel =
        ScarecrowWoodModel(loader.bakeLayer(DnDEntityModelLayers.SCARECROW_WOOD))

    override fun render(
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
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
        else woodTextureId(BuiltInRegistries.ITEM.getKey(woodBlock.item).path)
        coloredCutoutModelCopyLayerRender(
            this.parentModel,
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
        private fun woodTextureId(string: String): ResourceLocation =
            id("textures/entity/scarecrow/wood/$string.png")
    }
}