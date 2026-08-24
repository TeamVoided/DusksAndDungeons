package org.teamvoided.dusks_and_dungeons.api

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.server.MinecraftServer

// TODO(lib) move to voidlib
fun interface PostDataLoadEvent {

    fun dataLoaded(server: MinecraftServer)

    companion object {

        @JvmStatic
        val DATA_LOADED: Event<PostDataLoadEvent> =
            EventFactory.createArrayBacked(PostDataLoadEvent::class.java) { callbacks ->
                PostDataLoadEvent { server ->
                    for (callback in callbacks) {
                        callback.dataLoaded(server)
                    }
                }
            }

    }
}