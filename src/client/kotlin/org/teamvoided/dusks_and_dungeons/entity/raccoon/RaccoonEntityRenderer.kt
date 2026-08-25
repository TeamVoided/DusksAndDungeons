package org.teamvoided.dusks_and_dungeons.entity.raccoon

import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class RaccoonEntityRenderer(context: EntityRendererProvider.Context) :
    MobRenderer<RaccoonEntity, RaccoonEntityModel>(context, RaccoonEntityModel(context.bakeLayer(
        DnDEntityModelLayers.RACCOON
    )), 0.55F) {

    init {
        addLayer(RaccoonEntityHeldItemFeatureRenderer(this, context.itemInHandRenderer))
    }

    override fun getTextureLocation(entity: RaccoonEntity): ResourceLocation {
        return entity.variant.value().texture
    }
}