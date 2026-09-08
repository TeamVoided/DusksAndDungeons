package org.teamvoided.dusks_and_dungeons.init

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.CustomData
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity
import org.teamvoided.dusks_and_dungeons.renderer.blockentity.CandelabraRenderer

object DnDBlockEntitiesClient {

    fun init() {
        BlockEntityRenderers.register(DnDBlockEntities.CANDELABRA, ::CandelabraRenderer)

        BuiltinItemRendererRegistry.INSTANCE.register(DnDItems.IRON_CANDELABRA, ::renderCandelabraItem)
    }

    var CANDELABRA_ITEM_CACHE = mutableMapOf<CustomData, CandelabraBlockEntity>()

    fun getCandelabra(
        stack: ItemStack, level: ClientLevel, candelabraBlock: CandelabraBlock
    ): CandelabraBlockEntity? {
        val data = stack.get(DataComponents.BLOCK_ENTITY_DATA) ?: return null
        if (data.isEmpty) {
            return null
        }

        var candelabra = CANDELABRA_ITEM_CACHE[data]

        if (candelabra == null) {
            candelabra = CandelabraBlockEntity(
                BlockPos.ZERO,
                candelabraBlock.defaultBlockState()
                    .setValue(CandelabraBlock.LIT, true)
                    .setValue(CandelabraBlock.CANDLES, Candelabra.getCandleCount(stack))
            )
            data.loadInto(candelabra, level.registryAccess())
            candelabra.updateStateCache(level)
            println("Create new Candelabra")
            CANDELABRA_ITEM_CACHE[data] = candelabra
        }

        return candelabra
    }

    fun renderCandelabraItem(
        stack: ItemStack?, displayCtx: ItemDisplayContext,
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