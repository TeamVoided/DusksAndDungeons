package org.teamvoided.dusks_and_dungeons.entity.raccoon

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class RaccoonEntityRenderer(context: EntityRendererFactory.Context) :
    MobEntityRenderer<RaccoonEntity, RaccoonEntityModel>(context, RaccoonEntityModel(context.getPart(
        DnDEntityModelLayers.RACCOON
    )), 0.75F) {

    init {
        addFeature(RaccoonEntityHeldItemFeatureRenderer(this, context.heldItemRenderer))
    }

    override fun getTexture(entity: RaccoonEntity): Identifier {
        return entity.variant!!.value().texture
    }
}