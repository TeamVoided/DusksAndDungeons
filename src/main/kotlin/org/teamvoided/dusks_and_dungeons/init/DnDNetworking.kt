package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import org.teamvoided.dusks_and_dungeons.net.DnDLevelEventPayload

object DnDNetworking {

    fun init() {
        PayloadTypeRegistry.playS2C().register(DnDLevelEventPayload.ID, DnDLevelEventPayload.CODEC)
    }

}