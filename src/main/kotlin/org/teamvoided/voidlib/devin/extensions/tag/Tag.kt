@file:Suppress("unused")

package org.teamvoided.voidlib.devin.extensions.tag

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Block
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voidlib.consortium.block.AbstractBlockSet

fun <T> FabricTagProvider<T>.FabricTagBuilder.add(list: Collection<T>): FabricTagProvider<T>.FabricTagBuilder {
    list.forEach { this.add(it) }
    return this
}

fun AbstractBlockSet.createSetTags(tagBuilder:(TagKey<Block>) -> FabricTagProvider<Block>.FabricTagBuilder) {
    tagBuilder(BlockTags.STAIRS).add(this.stairs)
    tagBuilder(BlockTags.SLABS).add(this.slab)
    tagBuilder(BlockTags.WALLS).add(this.wall)
}
