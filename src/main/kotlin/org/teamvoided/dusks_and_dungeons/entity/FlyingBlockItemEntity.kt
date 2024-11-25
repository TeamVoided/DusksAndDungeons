package org.teamvoided.dusks_and_dungeons.entity

import net.minecraft.block.BlockState
import net.minecraft.entity.FlyingItemEntity

interface FlyingBlockItemEntity : FlyingItemEntity {
    fun getState(): BlockState
}
