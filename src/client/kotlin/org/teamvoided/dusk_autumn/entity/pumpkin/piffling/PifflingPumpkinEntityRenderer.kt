package org.teamvoided.dusk_autumn.entity.pumpkin.piffling

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.entity.PifflingPumpkinEntity
import org.teamvoided.dusk_autumn.entity.pumpkin.piffling.model.PifflingPumpkinModel
import org.teamvoided.dusk_autumn.entity.pumpkin.piffling.render.PifflingPumpkinHeadFeatureRenderer
import org.teamvoided.dusk_autumn.entity.pumpkin.piffling.render.PifflingPumpkinHeldItemFeatureRenderer

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