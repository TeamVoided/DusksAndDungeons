package org.teamvoided.dusks_and_dungeons.entity.pumpkin.piffling

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.entity.PifflingPumpkinEntity
import org.teamvoided.dusks_and_dungeons.entity.pumpkin.piffling.model.PifflingPumpkinModel
import org.teamvoided.dusks_and_dungeons.entity.pumpkin.piffling.render.PifflingPumpkinHeadFeatureRenderer
import org.teamvoided.dusks_and_dungeons.entity.pumpkin.piffling.render.PifflingPumpkinHeldItemFeatureRenderer

class PifflingPumpkinEntityRenderer(context: EntityRendererFactory.Context) :
    MobEntityRenderer<PifflingPumpkinEntity, PifflingPumpkinModel>(
        context,
        PifflingPumpkinModel(context.getPart(DnDEntityModelLayers.PIFFLING_PUMPKIN)),
        0.35f
    ) {
    init {
        this.addFeature(PifflingPumpkinHeldItemFeatureRenderer(this, context.heldItemRenderer))
        this.addFeature(PifflingPumpkinHeadFeatureRenderer(this, context.itemRenderer))
    }

    override fun getTexture(tuffGolemEntity: PifflingPumpkinEntity): Identifier {
        return TEXTURE
    }

    override fun isShaking(entity: PifflingPumpkinEntity): Boolean {
        return super.isShaking(entity)
    }

    companion object {
        private val TEXTURE: Identifier = id("textures/entity/pumpkin/piffling_pumpkin.png")
    }
}