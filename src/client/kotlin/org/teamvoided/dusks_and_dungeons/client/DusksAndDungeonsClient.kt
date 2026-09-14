package org.teamvoided.dusks_and_dungeons.client

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.core.registries.Registries
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import org.teamvoided.creative_works.util.mc.textMain
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.client.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.client.init.*
import org.teamvoided.dusks_and_dungeons.client.util.BETTER_BRICK_NAMES
import org.teamvoided.voidlib.helpers.registerBuiltInPack
import kotlin.jvm.optionals.getOrNull

@Suppress("unused")
object DusksAndDungeonsClient {

    fun init() {
        DnDEntityModelLayers.init()
        DnDBlocksClient.init()
        DnDItemsClient.init()
        DnDParticlesClient.init()
        DnDEntitiesClient.init()
        DnDBlockEntitiesClient.init()
        DnDLevelEventHandlers.init()
        DnDClientNetworking.init()

        registerBuiltInPack(MODID, BETTER_BRICK_NAMES)

        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES)
            .registerReloadListener(object : ResourceManagerReloadListener, IdentifiableResourceReloadListener {

                override fun getFabricId() = id("cache_invalidator")

                override fun onResourceManagerReload(resourceManager: ResourceManager) {
                    DnDBlockEntitiesClient.CANDELABRA_ITEM_CACHE.clear()
                }

            })

        if (isDev()) ClientCommandRegistrationCallback.EVENT.register { dispatcher, access ->
            val test = literal("dump_vile").executes { scc ->
                val src = scc.source
                val lookup = src.world.registryAccess()

                val types = lookup.lookup(Registries.DAMAGE_TYPE).getOrNull()

                if (types == null) {
                    src.sendError(textMain("no registry"))
                    return@executes -1
                }

                val elements = types.listElementIds().toList()
                for (key in elements) {
                    src.sendFeedback(textMain(key.location().toString()))
                }

                if (elements.isEmpty()) {
                    src.sendError(textMain("empty"))
                }

                0
            }.build()
            dispatcher.root.addChild(test)
        }
    }

}