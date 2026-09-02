package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.net.DnDLevelEventPayload

object DnDClientNetworking {

    fun init() {
        ClientPlayNetworking.registerGlobalReceiver(DnDLevelEventPayload.ID) { payload, ctx ->
            handleDnDLevelEvents(payload, ctx.client(), ctx.player())
        }
    }

    fun handleDnDLevelEvents(event: DnDLevelEventPayload, client: Minecraft, player: LocalPlayer) {
        val level = client.level ?: error("Received an event, but there is no level!")
        val pos = event.pos
        val data = event.data
        val handler = DnDLevelEventHandlers.ID_HANDLERS[event.eventId]

        if (handler != null) {
            handler.triggerEvent(client, level, player, pos, data)
        } else {
            log.warn("Received unknown level event: {}", event.eventId)
        }

        for (handler in DnDLevelEventHandlers.NO_ID_HANDLERS) {
            handler.triggerEvent(client, level, player, pos, data)
        }
    }

}