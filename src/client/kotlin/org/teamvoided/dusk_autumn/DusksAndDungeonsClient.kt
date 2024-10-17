package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.entity.DecoratedPotBlockEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import org.teamvoided.dusk_autumn.DusksAndDungeons.MODID
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.block.entity.ChestOSoulsBlockEntity
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.init.*

@Suppress("unused")
object DusksAndDungeonsClient {

    //    val key = KeyBindingHelper.registerKeyBinding(KeyBind("debug", InputUtil.KEY_R_CODE, "debug"))
//    const val CASCADE_LEAF_COLOR = 14701655
//    const val GOLDEN_BIRCH_COLOR = 16761873
//    const val GOLDEN_BIRCH_COLOR = 16760872

//    var cooldown = 0

    private var decoratedPotBlockEntity = DecoratedPotBlockEntity(BlockPos.ORIGIN, DnDBlocks.POT_O_SCREAMS.defaultState)
    private var chestOSoulsBlockEntity = ChestOSoulsBlockEntity(BlockPos.ORIGIN, DnDBlocks.CHEST_O_SOULS.defaultState)

    fun init() {
        DnDEntityModelLayers.init()
        DnDBlocksClient.init()
        DnDItemsClient.init()
        DnDParticlesClient.init()
        DnDEntitiesClient.init()
        DnDBlockEntitiesClient.init()
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

        ModelPredicateProviderRegistry.register(DnDItems.WEB_WEAVER, Identifier.ofDefault("pull"))
        { stack, _, entity, _ ->
            if (entity == null || entity.activeItem != stack) 0.0f
            else (stack.method_7935(entity) - entity.itemUseTimeLeft).toFloat() / 20.0f
        }
        ModelPredicateProviderRegistry.register(DnDItems.WEB_WEAVER, Identifier.ofDefault("pulling"))
        { stack, _, entity, _ -> if (entity != null && entity.isUsingItem && entity.activeItem == stack) 1.0f else 0.0f }

//        ClientTickEvents.END_CLIENT_TICK.register {
//            if (key.isPressed && cooldown < 1) {
//                it.networkHandler?.sendCommand("place feature dusk_autumn:cascade_tree")
//                cooldown = 35
//            }
//
//            if (cooldown > 0) cooldown--
//        }


        FabricLoader.getInstance().getModContainer(MODID).ifPresent {
            ResourceManagerHelper.registerBuiltinResourcePack(id("fancy_names"), it, ResourcePackActivationType.NORMAL)
        }
    }
}
