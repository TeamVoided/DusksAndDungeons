package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import org.teamvoided.dusk_autumn.entity.flying_pumpkin.FlyingBlockItemEntityRenderer
import org.teamvoided.dusk_autumn.entity.chill_charge.ChillChargeEntityRenderer
import org.teamvoided.dusk_autumn.entity.dice.DiceEntityRenderer
import org.teamvoided.dusk_autumn.entity.dust_bunny.DustBunnyEntityRenderer
import org.teamvoided.dusk_autumn.entity.pumpkin.piffling.PifflingPumpkinEntityRenderer
import org.teamvoided.dusk_autumn.entity.scarecrow.ScarecrowEntityRenderer

object DnDEntitiesClient {
    fun init() {
        EntityRendererRegistry.register(DnDEntities.CHILL_CHARGE, ::ChillChargeEntityRenderer)
//        EntityRendererRegistry.register(DnDEntities.BIRD_TEST, ::BirdEntityRenderer)

        EntityRendererRegistry.register(DnDEntities.SCARECROW, ::ScarecrowEntityRenderer)
        EntityRendererRegistry.register(DnDEntities.DIE, ::DiceEntityRenderer)

        EntityRendererRegistry.register(DnDEntities.FLYING_PUMPKIN, ::FlyingBlockItemEntityRenderer)
        EntityRendererRegistry.register(DnDEntities.DUST_BUNNY, ::DustBunnyEntityRenderer)
        EntityRendererRegistry.register(DnDEntities.PIFFLING_PUMPKIN, ::PifflingPumpkinEntityRenderer)
    }
}
