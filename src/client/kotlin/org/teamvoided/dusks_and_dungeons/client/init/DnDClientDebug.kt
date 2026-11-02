package org.teamvoided.dusks_and_dungeons.client.init

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.Minecraft
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import org.teamvoided.creative_works.util.mc.textMain
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.client.item.CandelabraItemRenderer
import kotlin.jvm.optionals.getOrNull

object DnDClientDebug {

    fun init() {
        if (!isDev()) {
            return
        }

        // This code for candelabra Particle renderer. It's not done
        ClientTickEvents.END_WORLD_TICK.register { level ->
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

        // Uhh..., this should not be called out of dev as it references CW
        ClientCommandRegistrationCallback.EVENT.register { dispatcher, access ->
            val test = literal("client_dump").executes { scc ->
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


    fun debug(message: String) {
        if (isDev()) Minecraft.getInstance().player?.displayClientMessage(Component.literal(message), true)
        else log.info("this message: $message; has been brought to you by:$MODID")
    }

}