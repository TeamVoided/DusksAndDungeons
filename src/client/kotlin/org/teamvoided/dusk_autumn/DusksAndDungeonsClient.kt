package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DusksAndDungeons.isDev
import org.teamvoided.dusk_autumn.entity.DnDEntityModelLayers
import org.teamvoided.dusk_autumn.init.*
import org.teamvoided.dusk_autumn.init.DnDTabs.register
import org.teamvoided.dusk_autumn.util.BETTER_BRICK_NAMES
import org.teamvoided.dusk_autumn.util.SECRET_ITEMS
import org.teamvoided.dusk_autumn.util.addLists
import org.teamvoided.dusk_autumn.util.registerBuiltInPack

@Suppress("unused")
object DusksAndDungeonsClient {

    fun init() {
        DnDEntityModelLayers.init()
        DnDBlocksClient.init()
        DnDItemsClient.init()
        DnDParticlesClient.init()
        DnDEntitiesClient.init()
        DnDBlockEntitiesClient.init()

        registerBuiltInPack(BETTER_BRICK_NAMES)
    }

    val DUSKS_AND_DUNGEONS_SECRET: ItemGroup = register("dnd_secret",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDItems.HARVESTER_SCYTHE) }
            .name(Text.literal("Secret Items ;)"))
            .entries { _, entries ->
//                println("Name : ${MinecraftClient.getInstance().player?.gameProfile?.id}")
                if (isDev()) entries.addLists(SECRET_ITEMS)
            }
            .build()
    )
}
