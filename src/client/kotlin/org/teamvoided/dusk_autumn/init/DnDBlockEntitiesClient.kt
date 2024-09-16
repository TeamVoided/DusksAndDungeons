package org.teamvoided.dusk_autumn.init

import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import org.teamvoided.dusk_autumn.entity.block.CelestalBellBlockEntityRenderer

object DnDBlockEntitiesClient {
    fun init() {
        BlockEntityRendererFactories.register(DnDBlockEntities.CELESTAL_BELL) { context: BlockEntityRendererFactory.Context ->
            CelestalBellBlockEntityRenderer(
                context, context.renderManager, DnDBlocks.CELESTAL_BELL
            )
        }
    }
}