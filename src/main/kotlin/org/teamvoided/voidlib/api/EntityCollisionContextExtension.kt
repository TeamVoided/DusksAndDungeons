package org.teamvoided.voidlib.api

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

// TODO(vlib) move to voidlib
interface EntityCollisionContextExtension {

    fun isHoldingItem(tag: TagKey<Item>): Boolean

    // TODO check if this can be a hit pos instead?
    fun setRecursive(state: Boolean)

    fun isRecursive(): Boolean

}