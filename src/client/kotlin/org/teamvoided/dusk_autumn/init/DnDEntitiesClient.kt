package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import org.teamvoided.dusk_autumn.entity.bird.BirdEntityRenderer
import org.teamvoided.dusk_autumn.entity.chill_charge.ChillChargeEntityRenderer
import org.teamvoided.dusk_autumn.entity.scarecrow.ScarecrowEntityRenderer

object DnDEntitiesClient {
    fun init() {
        EntityRendererRegistry.register(DnDEntities.CHILL_CHARGE, ::ChillChargeEntityRenderer)
//        EntityRendererRegistry.register(DnDEntities.BIRD_TEST, ::BirdEntityRenderer)

        EntityRendererRegistry.register(DnDEntities.SCARECROW, ::ScarecrowEntityRenderer)
    }
}