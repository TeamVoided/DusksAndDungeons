/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.client.init

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import org.teamvoided.dusks_and_dungeons.client.item.CandelabraItemRenderer
import org.teamvoided.dusks_and_dungeons.client.renderer.blockentity.CandelabraRenderer
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems

object DnDBlockEntitiesClient {

    fun init() {
        BlockEntityRenderers.register(DnDBlockEntities.CANDELABRA, ::CandelabraRenderer)
        CandelabraItemRenderer.register(DnDItems.IRON_CANDELABRA, DnDBlocks.IRON_CANDELABRA)
    }

}