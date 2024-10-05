package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.teamvoided.dusk_autumn.DusksAndDungeons.MODID
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.init.*
import org.teamvoided.dusk_autumn.util.id

@Suppress("unused")
object DusksAndDungeonsClient {

    //    val key = KeyBindingHelper.registerKeyBinding(KeyBind("debug", InputUtil.KEY_R_CODE, "debug"))
//    const val CASCADE_LEAF_COLOR = 14701655
//    const val GOLDEN_BIRCH_COLOR = 16761873
//    const val GOLDEN_BIRCH_COLOR = 16760872

//    var cooldown = 0

    fun init() {
        DnDEntityModelLayers.init()
        DnDBlocksClient.init()
        DnDItemsClient.init()
        DnDParticlesClient.init()
        DnDEntitiesClient.init()
        DnDBlockEntitiesClient.init()

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

        ItemTooltipCallback.EVENT.register { stack, tooltipContext, tooltipType, lines ->
            if (stack.item.id.namespace == MODID && DnDItems.EVIL_ITEMS.contains(stack.item)) {
                lines.addLast(
                    Text.literal("Experimental item, may disappear in future updates!").formatted(Formatting.RED)
                )
            }
        }
    }
}
