package org.teamvoided.dusks_and_dungeons.impl

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import org.teamvoided.dusks_and_dungeons.api.PostDataLoadEvent

object DnDApiImpl {

    fun init() {
        ServerLifecycleEvents.SERVER_STARTED.register { server ->
            PostDataLoadEvent.DATA_LOADED.invoker().dataLoaded(server)
        }
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register { server, _, _ ->
            PostDataLoadEvent.DATA_LOADED.invoker().dataLoaded(server)
        }
    }

}