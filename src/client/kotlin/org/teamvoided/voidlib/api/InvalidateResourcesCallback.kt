package org.teamvoided.voidlib.api

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory


// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
/**
 * Event for Invalidated caches for readable resources.
 * Made so I (end) have to write fewer Listeners.
 */
fun interface InvalidateResourcesCallback {

    fun invalidate()

    companion object {

        @Deprecated("Use Voidlib")
        @JvmStatic
        val EVENT: Event<InvalidateResourcesCallback> =
            EventFactory.createArrayBacked(InvalidateResourcesCallback::class.java) { callbacks ->
                InvalidateResourcesCallback { callbacks.forEach { it.invalidate() } }
            }

    }
}