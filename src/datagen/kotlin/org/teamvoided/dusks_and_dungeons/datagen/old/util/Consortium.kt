package org.teamvoided.dusks_and_dungeons.datagen.old.util

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.world.level.block.Block
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.tags.TagKey
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet

fun BlockModelGenerators.createWood(set: AbstractBlockSet, log: Block) {
    this.stairs(set.stairs, log)
    this.slab(set.slab, log, set.parent)
    this.wall(set.wall, log)
}

fun AbstractBlockSet.createWoodTags(tagBuilder: (TagKey<Block>) -> FabricTagProvider<Block>.FabricTagBuilder) {
    tagBuilder(DnDBlockTags.WOOD_STAIRS_THAT_BURN).add(this.stairs)
    tagBuilder(DnDBlockTags.WOOD_SLABS_THAT_BURN).add(this.slab)
    tagBuilder(DnDBlockTags.WOOD_WALLS_THAT_BURN).add(this.wall)
}