package org.teamvoided.voidlib.api

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

// TODO(lib) move to voidlib
interface EntityCollisionContextExtension {

    fun isHoldingItem(tag: TagKey<Item>): Boolean

    fun setRecursive(state: Boolean)

    fun isRecursive(): Boolean

}