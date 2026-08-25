package org.teamvoided.dusks_and_dungeons.datagen.old.recipes

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.datagen.old.util.createBigLantern
import org.teamvoided.dusks_and_dungeons.datagen.old.util.createCandelabra
import org.teamvoided.dusks_and_dungeons.datagen.old.util.createCandle
import org.teamvoided.dusks_and_dungeons.datagen.old.util.createDyed
import org.teamvoided.dusks_and_dungeons.datagen.old.util.criterion
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.DnDItemLists

object BigRecipes {

    fun build(output: RecipeOutput) {
        // Chains and Lanterns
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DnDBlocks.BIG_CHAIN, 1)
            .pattern("I")
            .pattern("N")
            .pattern("I")
            .define('I', Ingredient.of(Items.IRON_INGOT))
            .define('N', Ingredient.of(Items.IRON_NUGGET))
            .criterion(Items.IRON_NUGGET)
            .criterion(Items.IRON_INGOT)
            .save(output)
        output.createBigLantern(DnDBlocks.BIG_LANTERN, Blocks.TORCH, Blocks.LANTERN)
        output.createBigLantern(DnDBlocks.BIG_SOUL_LANTERN, Blocks.SOUL_TORCH, Blocks.SOUL_LANTERN)
        output.createBigLantern(DnDBlocks.BIG_REDSTONE_LANTERN, Blocks.REDSTONE_TORCH,DnDBlocks.REDSTONE_LANTERN)
        // Candles
        output.createCandle(DnDBlocks.BIG_CANDLES.uncolored, Items.HONEYCOMB_BLOCK)
        output.createCandle(DnDBlocks.SOUL_CANDLES.uncolored, Items.HONEYCOMB, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        output.createCandle(DnDBlocks.BIG_SOUL_CANDLES.uncolored, Items.HONEYCOMB_BLOCK, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        DnDItemLists.dye.forEachIndexed { index, dye ->
            val idx = index + 1
            output.createDyed(DnDBlockLists.bigCandles[idx].first, DnDBlocks.BIG_CANDLES.uncolored, dye)
            output.createDyed(DnDBlockLists.soulCandles[idx].first, DnDBlocks.SOUL_CANDLES.uncolored, dye)
            output.createDyed(DnDBlockLists.bigSoulCandles[idx].first, DnDBlocks.BIG_SOUL_CANDLES.uncolored, dye)
            output.createDyed(DnDBlockLists.candelabras[idx], DnDBlocks.CANDELABRAS.uncolored, dye, true)
            output.createDyed(DnDBlockLists.soulCandelabras[idx], DnDBlocks.SOUL_CANDELABRAS.uncolored, dye, true)
        }
        DnDBlockLists.allCandelabras.forEach(output::createCandelabra)

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DnDBlocks.BIG_SCAFFOLDING, 6)
            .define('|', Ingredient.of(Items.BAMBOO_PLANKS))
            .define('~', Ingredient.of(Items.STRING))
            .pattern("|~|")
            .pattern("| |")
            .pattern("| |")
            .criterion(Items.BAMBOO_PLANKS)
            .save(output)
    }
}