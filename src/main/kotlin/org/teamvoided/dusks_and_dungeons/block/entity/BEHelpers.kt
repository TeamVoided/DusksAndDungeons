package org.teamvoided.dusks_and_dungeons.block.entity

import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType


fun <E : BlockEntity, A : BlockEntity> createTicker(
    worldType: BlockEntityType<A>?, correctType: BlockEntityType<E>?, ticker: BlockEntityTicker<in E>?,
): BlockEntityTicker<A>? {
    @Suppress("UNCHECKED_CAST")
    return if (correctType === worldType) ticker as? BlockEntityTicker<A>? else null
}