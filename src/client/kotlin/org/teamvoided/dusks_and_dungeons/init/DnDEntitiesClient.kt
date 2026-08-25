package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.renderer.entity.ThrownItemRenderer
import org.teamvoided.dusks_and_dungeons.entity.scarecrow.ScarecrowEntityRenderer

object DnDEntitiesClient {

    fun init() {
        EntityRendererRegistry.register(DnDEntities.SCARECROW, ::ScarecrowEntityRenderer)
        EntityRendererRegistry.register(DnDEntities.THROWN_ITEM, ::ThrownItemRenderer)
    }

}