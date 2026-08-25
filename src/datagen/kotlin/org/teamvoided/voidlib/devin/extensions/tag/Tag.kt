@file:Suppress("unused")

package org.teamvoided.voidlib.devin.extensions.tag

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.item.Item
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import org.teamvoided.voidlib.consortium.block.color.ColorConsortium
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet

fun <T> FabricTagProvider<T>.FabricTagBuilder.add(list: Collection<T>): FabricTagProvider<T>.FabricTagBuilder {
    list.forEach { this.add(it) }
    return this
}

fun AbstractBlockSet.createSetTags(tagBuilder: (TagKey<Block>) -> FabricTagProvider<Block>.FabricTagBuilder) {
    tagBuilder(BlockTags.STAIRS).add(this.stairs)
    tagBuilder(BlockTags.SLABS).add(this.slab)
    tagBuilder(BlockTags.WALLS).add(this.wall)
}

fun <T : Block> ColorConsortium<T>.createColorTags(tagBuilder: (TagKey<Block>) -> FabricTagProvider<Block>.FabricTagBuilder) {
    tagBuilder(ConventionalBlockTags.BLACK_DYED).add(this.black)
    tagBuilder(ConventionalBlockTags.BLUE_DYED).add(this.blue)
    tagBuilder(ConventionalBlockTags.BROWN_DYED).add(this.brown)
    tagBuilder(ConventionalBlockTags.CYAN_DYED).add(this.cyan)
    tagBuilder(ConventionalBlockTags.GRAY_DYED).add(this.gray)
    tagBuilder(ConventionalBlockTags.GREEN_DYED).add(this.green)
    tagBuilder(ConventionalBlockTags.LIGHT_BLUE_DYED).add(this.lightBlue)
    tagBuilder(ConventionalBlockTags.LIGHT_GRAY_DYED).add(this.lightGray)
    tagBuilder(ConventionalBlockTags.LIME_DYED).add(this.lime)
    tagBuilder(ConventionalBlockTags.MAGENTA_DYED).add(this.magenta)
    tagBuilder(ConventionalBlockTags.ORANGE_DYED).add(this.orange)
    tagBuilder(ConventionalBlockTags.PINK_DYED).add(this.pink)
    tagBuilder(ConventionalBlockTags.PURPLE_DYED).add(this.purple)
    tagBuilder(ConventionalBlockTags.RED_DYED).add(this.red)
    tagBuilder(ConventionalBlockTags.WHITE_DYED).add(this.white)
    tagBuilder(ConventionalBlockTags.YELLOW_DYED).add(this.yellow)
}

fun copyColorTags(copy: (TagKey<Block>, TagKey<Item>) -> Unit) {
    copy(ConventionalBlockTags.BLACK_DYED, ConventionalItemTags.BLACK_DYED)
    copy(ConventionalBlockTags.BLUE_DYED, ConventionalItemTags.BLUE_DYED)
    copy(ConventionalBlockTags.BROWN_DYED, ConventionalItemTags.BROWN_DYED)
    copy(ConventionalBlockTags.CYAN_DYED, ConventionalItemTags.CYAN_DYED)
    copy(ConventionalBlockTags.GRAY_DYED, ConventionalItemTags.GRAY_DYED)
    copy(ConventionalBlockTags.GREEN_DYED, ConventionalItemTags.GREEN_DYED)
    copy(ConventionalBlockTags.LIGHT_BLUE_DYED, ConventionalItemTags.LIGHT_BLUE_DYED)
    copy(ConventionalBlockTags.LIGHT_GRAY_DYED, ConventionalItemTags.LIGHT_GRAY_DYED)
    copy(ConventionalBlockTags.LIME_DYED, ConventionalItemTags.LIME_DYED)
    copy(ConventionalBlockTags.MAGENTA_DYED, ConventionalItemTags.MAGENTA_DYED)
    copy(ConventionalBlockTags.ORANGE_DYED, ConventionalItemTags.ORANGE_DYED)
    copy(ConventionalBlockTags.PINK_DYED, ConventionalItemTags.PINK_DYED)
    copy(ConventionalBlockTags.PURPLE_DYED, ConventionalItemTags.PURPLE_DYED)
    copy(ConventionalBlockTags.RED_DYED, ConventionalItemTags.RED_DYED)
    copy(ConventionalBlockTags.WHITE_DYED, ConventionalItemTags.WHITE_DYED)
    copy(ConventionalBlockTags.YELLOW_DYED, ConventionalItemTags.YELLOW_DYED)
}