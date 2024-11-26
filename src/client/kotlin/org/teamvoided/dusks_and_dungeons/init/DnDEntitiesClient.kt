package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import org.teamvoided.dusks_and_dungeons.entity.chill_charge.ChillChargeEntityRenderer
import org.teamvoided.dusks_and_dungeons.entity.dice.DiceEntityRenderer
import org.teamvoided.dusks_and_dungeons.entity.dust_bunny.DustBunnyEntityRenderer
import org.teamvoided.dusks_and_dungeons.entity.flying_pumpkin.FlyingBlockItemEntityRenderer
import org.teamvoided.dusks_and_dungeons.entity.pumpkin.piffling.PifflingPumpkinEntityRenderer
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.ScarecrowEntityRenderer

object DnDEntitiesClient {
    fun init() {
        EntityRendererRegistry.register(DnDEntities.CHILL_CHARGE, ::ChillChargeEntityRenderer)
        EntityRendererRegistry.register(DnDEntities.SCARECROW, ::ScarecrowEntityRenderer)

        EntityRendererRegistry.register(DnDEntities.DIE, ::DiceEntityRenderer)
//        EntityRendererRegistry.register(DnDEntities.BIRD_TEST, ::BirdEntityRenderer)
        EntityRendererRegistry.register(DnDEntities.FLYING_PUMPKIN, ::FlyingBlockItemEntityRenderer)
        EntityRendererRegistry.register(DnDEntities.DUST_BUNNY, ::DustBunnyEntityRenderer)
        EntityRendererRegistry.register(DnDEntities.PIFFLING_PUMPKIN, ::PifflingPumpkinEntityRenderer)
    }
}
