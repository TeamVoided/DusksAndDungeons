package org.teamvoided.dusks_and_dungeons.client

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.client.Minecraft
import net.minecraft.core.particles.ParticleTypes
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
import org.teamvoided.dusks_and_dungeons.client.item.CandelabraItemRenderer
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
                    CandelabraItemRenderer.CANDELABRA_ITEM_CACHE.clear()
                }

            })

        if (isDev()) ClientTickEvents.END_WORLD_TICK.register { level ->
            val player = Minecraft.getInstance().player ?: return@register
            val particlePos = CandelabraItemRenderer.vecPos
//            player.displayClientMessage(Component.literal("Vec: $particlePos"), true)

            if (particlePos != null) {
                level.addParticle(
                    ParticleTypes.SMALL_FLAME,
                    (particlePos.x.toDouble()),
                    (particlePos.y.toDouble()),
                    (particlePos.z.toDouble()),
                    0.0, 0.0, 0.0,
                )
                CandelabraItemRenderer.vecPos = null
            }
        }

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