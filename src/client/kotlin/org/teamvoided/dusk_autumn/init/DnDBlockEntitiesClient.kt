package org.teamvoided.dusk_autumn.init

import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer
import org.teamvoided.dusk_autumn.entity.block.CelestalBellBlockEntityRenderer

object DnDBlockEntitiesClient {

    fun init() {
        BlockEntityRendererFactories.register(DnDBlockEntities.CELESTAL_BELL, ::CelestalBellBlockEntityRenderer)
        BlockEntityRendererFactories.register(DnDBlockEntities.CHEST_O_SOULS, ::ChestBlockEntityRenderer)
    }
}
