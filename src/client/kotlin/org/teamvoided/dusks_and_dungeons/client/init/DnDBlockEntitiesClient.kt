package org.teamvoided.dusks_and_dungeons.client.init

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraContents
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity
import org.teamvoided.dusks_and_dungeons.client.renderer.blockentity.CandelabraRenderer
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDDataComponents.CANDELABRA_CONTENTS
import org.teamvoided.dusks_and_dungeons.init.DnDItems

object DnDBlockEntitiesClient {

    fun init() {
        BlockEntityRenderers.register(DnDBlockEntities.CANDELABRA, ::CandelabraRenderer)

        // TODO make this compatible with other custom candelabras
        BuiltinItemRendererRegistry.INSTANCE.register(DnDItems.IRON_CANDELABRA, ::renderCandelabraItem)
    }

    var CANDELABRA_ITEM_CACHE = mutableMapOf<CandelabraContents, CandelabraBlockEntity>()

    fun getCandelabra(stack: ItemStack, level: ClientLevel, block: CandelabraBlock): CandelabraBlockEntity? {
        val contents = stack.get(CANDELABRA_CONTENTS) ?: return null
        if (contents.isEmpty()) {
            return null
        }

        var candelabra = CANDELABRA_ITEM_CACHE[contents]

        if (candelabra == null) {
            candelabra = CandelabraBlockEntity(
                BlockPos.ZERO,
                block.defaultBlockState()
                    .setValue(CandelabraBlock.LIT, true)
                    .setValue(CandelabraBlock.CANDLES, Candelabra.getSlotCount(stack))
            )
            candelabra.applyComponentsFromItemStack(stack)
            candelabra.updateStateCache(level)
            CANDELABRA_ITEM_CACHE[contents] = candelabra
        }

        return candelabra
    }

    fun renderCandelabraItem(
        stack: ItemStack?, ctx: ItemDisplayContext,
        poseStack: PoseStack, buffers: MultiBufferSource, light: Int, overlay: Int,
    ) {
        stack ?: return
        val level = Minecraft.getInstance().level ?: return
        val candelabra = getCandelabra(stack, level, DnDBlocks.IRON_CANDELABRA) ?: return
        Minecraft.getInstance().blockEntityRenderDispatcher.renderItem(candelabra, poseStack, buffers, light, overlay)

        // TODO(1.0) make particles work

        /*  if (displayCtx.thirdPerson() && Minecraft.getInstance().fps % 2 == 0) {
              Candelabra.spawnCandelabraParticles(
                  candelabra, Minecraft.getInstance().player?.position() ?: Vec3.ZERO,
                  level, level.random, candelabra.blockState
              )
          }*/
    }

}