package org.teamvoided.dusks_and_dungeons.entity.raccoon

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.render.entity.model.AnimalModel
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class RaccoonEntityRenderer(context: EntityRendererFactory.Context) :
    MobEntityRenderer<RaccoonEntity, AnimalModel<RaccoonEntity>>(context, RaccoonEntityModel(context.getPart(
        DnDEntityModelLayers.RACCOON
    )), 0.5F) {

    override fun getTexture(entity: RaccoonEntity): Identifier {
        return entity.variant!!.value().texture
    }
}