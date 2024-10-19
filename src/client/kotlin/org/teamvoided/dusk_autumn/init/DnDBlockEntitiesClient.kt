package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.block.entity.DecoratedPotBlockEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer
import net.minecraft.util.math.BlockPos
import org.teamvoided.dusk_autumn.DusksAndDungeonsClient
import org.teamvoided.dusk_autumn.block.entity.ChestOSoulsBlockEntity
import org.teamvoided.dusk_autumn.entity.block.CelestalBellBlockEntityRenderer
import org.teamvoided.dusk_autumn.entity.block.QuarterBlockPileBlockEntityRenderer

object DnDBlockEntitiesClient {

    private var decoratedPotBlockEntity = DecoratedPotBlockEntity(BlockPos.ORIGIN, DnDBlocks.POT_O_SCREAMS.defaultState)
    private var chestOSoulsBlockEntity = ChestOSoulsBlockEntity(BlockPos.ORIGIN, DnDBlocks.CHEST_O_SOULS.defaultState)

    fun init() {
        BlockEntityRendererFactories.register(DnDBlockEntities.CELESTAL_BELL, ::CelestalBellBlockEntityRenderer)
        BlockEntityRendererFactories.register(DnDBlockEntities.CHEST_O_SOULS, ::ChestBlockEntityRenderer)
        BlockEntityRendererFactories.register(DnDBlockEntities.QUARTER_BLOCK_PILE, ::QuarterBlockPileBlockEntityRenderer)

        BuiltinItemRendererRegistry.INSTANCE.register(DnDBlocks.POT_O_SCREAMS) { stack, mode, matrices, vertexConsumers, light, overlay ->
            MinecraftClient.getInstance().blockEntityRenderDispatcher.renderEntity(
                decoratedPotBlockEntity, matrices, vertexConsumers, light, overlay
            )
        }
        BuiltinItemRendererRegistry.INSTANCE.register(DnDBlocks.CHEST_O_SOULS) { stack, mode, matrices, vertextConsumers, light, overlay ->
            MinecraftClient.getInstance().blockEntityRenderDispatcher.renderEntity(
                chestOSoulsBlockEntity, matrices, vertextConsumers, light, overlay
            )
        }
    }
}
