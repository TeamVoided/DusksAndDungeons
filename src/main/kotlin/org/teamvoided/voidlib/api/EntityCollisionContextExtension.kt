package org.teamvoided.voidlib.api

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
interface EntityCollisionContextExtension {
    // TODO(del) Update Voidlib Delete this
    @Deprecated("Use Voidlib")
    fun isHoldingItem(tag: TagKey<Item>): Boolean

    // TODO(del) Update Voidlib Delete this
    @Deprecated("Use Voidlib")
    fun setRecursive(state: Boolean)

    // TODO(del) Update Voidlib Delete this
    @Deprecated("Use Voidlib")
    fun isRecursive(): Boolean

}