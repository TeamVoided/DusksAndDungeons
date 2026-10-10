package org.teamvoided.dusks_and_dungeons.datagen.data.recipe

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.datagen.data.recipe.helpers.unlockedBy
import org.teamvoided.dusks_and_dungeons.datagen.old.util.create2x2
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.voidlib.devin.provider.OpenRecipeOutput

object MinecraftRecipeOverrides {

    fun build(dndOutput: RecipeOutput) {
        val output = OpenRecipeOutput(dndOutput)

        output.create2x2(Blocks.STONE_BRICKS, DnDBlocks.POLISHED_STONE)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.SNOW, 8)
            .define('#', Blocks.SNOW_BLOCK)
            .pattern("##")
            .unlockedBy(Blocks.SNOW_BLOCK)
            .unlockedBy(Blocks.SNOW)
            .save(output)
    }

}