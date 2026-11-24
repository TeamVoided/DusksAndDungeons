package org.teamvoided.voidlib.init

import org.teamvoided.voidlib.impl.PostDataLoadEventImpl

@Deprecated("Use Voidlib")
object VEventInit {

    fun init() {
        PostDataLoadEventImpl.init()
    }

}