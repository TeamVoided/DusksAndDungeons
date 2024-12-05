@file:Suppress("unused")

package org.teamvoided.voidlib.devin.extensions.loot

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.SlabBlock
import org.teamvoided.voidlib.consortium.block.AbstractBlockSet

fun FabricBlockLootTableProvider.createSetDrops(set: AbstractBlockSet) {
    set.forEach { block ->
        if (block is SlabBlock) add(block, ::slabDrops)
        else addDrop(block)
    }
}