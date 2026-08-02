package org.teamvoided.dusks_and_dungeons.api

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

interface EntityCollisionContextExtension {

    fun isHoldingItem(tag: TagKey<Item>): Boolean

}