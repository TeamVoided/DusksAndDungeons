package org.teamvoided.voidlib.devin.extensions.recipe

import net.minecraft.world.level.block.Block
import net.minecraft.data.recipes.RecipeOutput
import org.teamvoided.dusks_and_dungeons.util.datagen.createSlab
import org.teamvoided.dusks_and_dungeons.util.datagen.createStair
import org.teamvoided.dusks_and_dungeons.util.datagen.createWall
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.set.BlockSet

fun RecipeOutput.createSet(set: AbstractBlockSet) {
    val input = set.parent
    this.createStair(set.stairs, input)
    this.createSlab(set.slab, input)
    this.createWall(set.wall, input)
    if (set.hasStoneCutting) {
        this.createStonecutting(set.stairs, input)
        this.createStonecutting(set.slab, input, 2)
        this.createStonecutting(set.wall, input)
    }
}

fun RecipeOutput.createStonecuttingSet(set: AbstractBlockSet, vararg blocks: Block) {
    blocks.forEach { input ->
        this.createStonecutting(set.stairs, input)
        this.createStonecutting(set.slab, input, 2)
        this.createStonecutting(set.wall, input)
        if (set is BlockSet) this.createStonecutting(set.parent, input)
    }
}