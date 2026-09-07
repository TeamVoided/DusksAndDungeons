package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import org.teamvoided.dusks_and_dungeons.entity.ThrownItemStackRenderer
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.ScarecrowEntityRenderer

object DnDEntitiesClient {

    fun init() {
        EntityRendererRegistry.register(DnDEntityTypes.SCARECROW, ::ScarecrowEntityRenderer)
        EntityRendererRegistry.register(DnDEntityTypes.THROWN_ITEM, ::ThrownItemStackRenderer)
    }

}