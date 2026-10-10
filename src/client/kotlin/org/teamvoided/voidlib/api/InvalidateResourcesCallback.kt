/*
 * TODO(cleanup)
 */

package org.teamvoided.voidlib.api

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory

// TODO(lib) move to voidlib
/**
 * Event for Invalidated caches for readable resources.
 * Made so I (end) have to write fewer Listeners.
 */
fun interface InvalidateResourcesCallback {

    fun invalidate()

    companion object {

        @JvmStatic
        val EVENT: Event<InvalidateResourcesCallback> =
            EventFactory.createArrayBacked(InvalidateResourcesCallback::class.java) { callbacks ->
                InvalidateResourcesCallback { callbacks.forEach { it.invalidate() } }
            }

    }
}