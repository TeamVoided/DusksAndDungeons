package org.teamvoided.dusks_and_dungeons.client.init

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import org.teamvoided.dusks_and_dungeons.client.entity.ThrownItemStackRenderer
import org.teamvoided.dusks_and_dungeons.client.entity.scarecrow.ScarecrowEntityRenderer
import org.teamvoided.dusks_and_dungeons.init.DnDEntityTypes

object DnDEntitiesClient {

    fun init() {
        EntityRendererRegistry.register(DnDEntityTypes.SCARECROW, ::ScarecrowEntityRenderer)
        EntityRendererRegistry.register(DnDEntityTypes.THROWN_ITEM, ::ThrownItemStackRenderer)
    }

}