package org.teamvoided.voidlib.devin.extensions.recipe

import net.minecraft.data.server.recipe.RecipeExporter
import org.teamvoided.dusks_and_dungeons.util.datagen.createSlab
import org.teamvoided.dusks_and_dungeons.util.datagen.createStair
import org.teamvoided.dusks_and_dungeons.util.datagen.createWall
import org.teamvoided.voidlib.consortium.block.HeadlessBlockSet

fun RecipeExporter.createSet(set: HeadlessBlockSet) {
    var input = set.parent
    this.createStair(set.stairs, input)
    this.createSlab(set.slab, input)
    this.createWall(set.wall, input)
    if (set.hasStoneCutting) {
        this.createStonecutting(set.stairs, input)
        this.createStonecutting(set.slab, input, 2)
        this.createStonecutting(set.wall, input)
    }
}