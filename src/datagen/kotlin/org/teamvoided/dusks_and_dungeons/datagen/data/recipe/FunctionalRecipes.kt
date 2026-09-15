package org.teamvoided.dusks_and_dungeons.datagen.data.recipe

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.SpecialRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.datagen.data.recipe.helpers.candelabra
import org.teamvoided.dusks_and_dungeons.datagen.old.util.*
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.recipe.CandelabraContentsRecipe
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.DnDItemLists

object FunctionalRecipes {

    fun build(output: RecipeOutput) {
        // Chains and Lanterns
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DnDBlocks.BIG_CHAIN, 1)
            .pattern("I")
            .pattern("N")
            .pattern("I")
            .define('I', Ingredient.of(Items.IRON_INGOT))
            .define('N', Ingredient.of(Items.IRON_NUGGET))
            .unlockedBy(Items.IRON_NUGGET)
            .unlockedBy(Items.IRON_INGOT)
            .save(output)
        output.createBigLantern(DnDBlocks.BIG_LANTERN, Blocks.TORCH, Blocks.LANTERN)
        output.createBigLantern(DnDBlocks.BIG_SOUL_LANTERN, Blocks.SOUL_TORCH, Blocks.SOUL_LANTERN)

        output.lantern(DnDBlocks.REDSTONE_LANTERN, Items.REDSTONE_TORCH)
        output.createBigLantern(DnDBlocks.BIG_REDSTONE_LANTERN, Blocks.REDSTONE_TORCH, DnDBlocks.REDSTONE_LANTERN)
        // Candles
        output.createCandle(DnDBlocks.BIG_CANDLES.uncolored, Items.HONEYCOMB_BLOCK)
        output.createCandle(DnDBlocks.SOUL_CANDLES.uncolored, Items.HONEYCOMB, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        output.createCandle(DnDBlocks.BIG_SOUL_CANDLES.uncolored, Items.HONEYCOMB_BLOCK, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        DnDItemLists.dye.forEachIndexed { index, dye ->
            val idx = index + 1
            output.createDyed(DnDBlockLists.bigCandles[idx].first, DnDBlocks.BIG_CANDLES.uncolored, dye)
            output.createDyed(DnDBlockLists.soulCandles[idx].first, DnDBlocks.SOUL_CANDLES.uncolored, dye)
            output.createDyed(DnDBlockLists.bigSoulCandles[idx].first, DnDBlocks.BIG_SOUL_CANDLES.uncolored, dye)
        }

        output.candelabra(DnDItems.IRON_CANDELABRA, Items.IRON_INGOT, Items.IRON_NUGGET)
        SpecialRecipeBuilder.special(::CandelabraContentsRecipe).save(output, id("candelabra_contents"))

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DnDBlocks.BIG_SCAFFOLDING, 6)
            .define('|', Ingredient.of(Items.BAMBOO_PLANKS))
            .define('~', Ingredient.of(Items.STRING))
            .pattern("|~|")
            .pattern("| |")
            .pattern("| |")
            .unlockedBy(Items.BAMBOO_PLANKS)
            .save(output)

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DnDBlocks.TINTED_GLASS_PANE, 16)
            .define('#', Blocks.TINTED_GLASS)
            .pattern("###")
            .pattern("###")
            .unlockedBy(Blocks.TINTED_GLASS)
            .save(output)

    }

}