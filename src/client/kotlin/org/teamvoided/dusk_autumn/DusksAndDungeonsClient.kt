package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.entity.DecoratedPotBlockEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import org.teamvoided.dusk_autumn.DusksAndDungeons.MODID
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.DusksAndDungeons.isDev
import org.teamvoided.dusk_autumn.block.entity.ChestOSoulsBlockEntity
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.init.*
import org.teamvoided.dusk_autumn.init.DnDTabs.register
import org.teamvoided.dusk_autumn.util.SECRET_ITEMS
import org.teamvoided.dusk_autumn.util.addLists

@Suppress("unused")
object DusksAndDungeonsClient {

    //    val key = KeyBindingHelper.registerKeyBinding(KeyBind("debug", InputUtil.KEY_R_CODE, "debug"))
//    const val CASCADE_LEAF_COLOR = 14701655
//    const val GOLDEN_BIRCH_COLOR = 16761873
//    const val GOLDEN_BIRCH_COLOR = 16760872

    //    var cooldown = 0
    val DUSKS_AND_DUNGEONS_SECRET: ItemGroup = register("dnd_secret",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDItems.HARVESTER_SCYTHE) }
            .name(Text.literal("Secret Items ;)"))
            .entries { params, entries ->
//                println("Name : ${MinecraftClient.getInstance().player?.gameProfile?.id}")
                if (isDev()) entries.addLists(SECRET_ITEMS)
            }
            .build()
    )

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
    }
}
