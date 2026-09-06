package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import org.teamvoided.dusks_and_dungeons.renderer.blockentity.CandelabraRenderer

object DnDBlockEntitiesClient {

    fun init() {
        BlockEntityRenderers.register(DnDBlockEntities.CANDELABRA, ::CandelabraRenderer)
    }

}
