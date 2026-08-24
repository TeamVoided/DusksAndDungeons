package org.teamvoided.dusks_and_dungeons

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.minecraft.core.registries.Registries
import org.teamvoided.creative_works.util.mc.textMain
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.entity.DnDEntityModelLayers
import org.teamvoided.dusks_and_dungeons.init.*
import org.teamvoided.dusks_and_dungeons.util.BETTER_BRICK_NAMES
import org.teamvoided.dusks_and_dungeons.util.block.CUTOUT_BLOCKS
import org.teamvoided.dusks_and_dungeons.util.block.TRANSLUCENT_BLOCKS
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
        DnDClientNetworking.init()

        registerBuiltInPack(MODID, BETTER_BRICK_NAMES)
        if (!isDev()) {
            CUTOUT_BLOCKS.clear()
            TRANSLUCENT_BLOCKS.clear()
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
