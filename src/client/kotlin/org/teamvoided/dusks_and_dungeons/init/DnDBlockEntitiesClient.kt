package org.teamvoided.dusks_and_dungeons.init

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity
import org.teamvoided.dusks_and_dungeons.renderer.blockentity.CandelabraRenderer

object DnDBlockEntitiesClient {

    val candelabraState: BlockState = DnDBlocks.IRON_CANDELABRA.defaultBlockState().setValue(CandelabraBlock.LIT, true)
    var CANDELABRA_1 = CandelabraBlockEntity(BlockPos.ZERO, candelabraState)
    var CANDELABRA_2 = CandelabraBlockEntity(BlockPos.ZERO, candelabraState.setValue(CandelabraBlock.CANDLES, 2))
    var CANDELABRA_3 = CandelabraBlockEntity(BlockPos.ZERO, candelabraState.setValue(CandelabraBlock.CANDLES, 3))
    var CANDELABRA_4 = CandelabraBlockEntity(BlockPos.ZERO, candelabraState.setValue(CandelabraBlock.CANDLES, 4))
    var CANDELABRA_5 = CandelabraBlockEntity(BlockPos.ZERO, candelabraState.setValue(CandelabraBlock.CANDLES, 5))

    fun init() {
        BlockEntityRenderers.register(DnDBlockEntities.CANDELABRA, ::CandelabraRenderer)

        BuiltinItemRendererRegistry.INSTANCE.register(DnDItems.IRON_CANDELABRA, ::renderCandelabra)
    }

    fun renderCandelabra(
        stack: ItemStack?, displayCtx: ItemDisplayContext,
        poseStack: PoseStack, buffers: MultiBufferSource, light: Int, overlay: Int,
    ) {
        val level = Minecraft.getInstance().level ?: return
        val data = stack?.get(DataComponents.BLOCK_ENTITY_DATA) ?: return
        val candelabra = when (stack.get(DataComponents.BLOCK_STATE)?.get(CandelabraBlock.CANDLES) ?: 1) {
            2 -> CANDELABRA_2
            3 -> CANDELABRA_3
            4 -> CANDELABRA_4
            5 -> CANDELABRA_5
            else -> CANDELABRA_1
        }
        if (!data.isEmpty) {
            data.loadInto(candelabra, level.registryAccess())
        }
        // setting the level might be a problem
        candelabra.updateStateCache(level)
        Minecraft.getInstance().blockEntityRenderDispatcher.renderItem(
            candelabra, poseStack, buffers, light, overlay
        )


        // TODO(1.0) make particles work

        /*  if (displayCtx.thirdPerson() && Minecraft.getInstance().fps % 2 == 0) {
              Candelabra.spawnCandelabraParticles(
                  candelabra, Minecraft.getInstance().player?.position() ?: Vec3.ZERO,
                  level, level.random, candelabra.blockState
              )
          }*/
    }

    fun ItemDisplayContext.thirdPerson(): Boolean {
        return this == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || this == ItemDisplayContext.THIRD_PERSON_LEFT_HAND
    }

}