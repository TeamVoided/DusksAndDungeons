package org.teamvoided.voidlib.impl

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import org.teamvoided.voidlib.api.PostDataLoadEvent

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
object PostDataLoadEventImpl {

    fun init() {
        ServerLifecycleEvents.SERVER_STARTED.register { server ->
            PostDataLoadEvent.DATA_LOADED.invoker().dataLoaded(server)
        }
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register { server, _, _ ->
            PostDataLoadEvent.DATA_LOADED.invoker().dataLoaded(server)
        }
    }

}