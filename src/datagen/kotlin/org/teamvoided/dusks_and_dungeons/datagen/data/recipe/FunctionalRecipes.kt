/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.datagen.data.recipe

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.SpecialRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.item.Items.IRON_INGOT
import net.minecraft.world.item.Items.IRON_NUGGET
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.datagen.data.recipe.helpers.*
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.recipe.CandelabraContentsRecipe
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.DnDItemLists

object FunctionalRecipes {

    fun build(output: RecipeOutput) {
        // Chains and Lanterns
        output.bigChain(DnDBlocks.BIG_CHAIN, IRON_INGOT, IRON_NUGGET)
        output.bigIronLantern(DnDBlocks.BIG_LANTERN, Blocks.TORCH, Blocks.LANTERN)
        output.bigIronLantern(DnDBlocks.BIG_SOUL_LANTERN, Blocks.SOUL_TORCH, Blocks.SOUL_LANTERN)

        output.lantern(DnDBlocks.REDSTONE_LANTERN, Items.REDSTONE_TORCH, IRON_NUGGET, IRON_INGOT)
        output.bigIronLantern(DnDBlocks.BIG_REDSTONE_LANTERN, Blocks.REDSTONE_TORCH, DnDBlocks.REDSTONE_LANTERN)
        // Candles
        output.candle(DnDBlocks.BIG_CANDLES.uncolored, Items.HONEYCOMB_BLOCK)
        output.reagentCandle(DnDBlocks.SOUL_CANDLES.uncolored, Items.HONEYCOMB, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        output.reagentCandle(
            DnDBlocks.BIG_SOUL_CANDLES.uncolored, Items.HONEYCOMB_BLOCK, ItemTags.SOUL_FIRE_BASE_BLOCKS
        )
        DnDItemLists.dye.forEachIndexed { index, dye ->
            val idx = index + 1
            output.shapelessDying(DnDBlockLists.bigCandles[idx].first, DnDBlocks.BIG_CANDLES.uncolored, dye)
            output.shapelessDying(DnDBlockLists.soulCandles[idx].first, DnDBlocks.SOUL_CANDLES.uncolored, dye)
            output.shapelessDying(DnDBlockLists.bigSoulCandles[idx].first, DnDBlocks.BIG_SOUL_CANDLES.uncolored, dye)
        }

        output.candelabra(DnDItems.IRON_CANDELABRA, IRON_INGOT, IRON_NUGGET)
        SpecialRecipeBuilder.special(::CandelabraContentsRecipe)
            .save(output, id("candelabra_contents"))

//        output.sconce(DnDBlocks.OAK_SCONCE, Items.OAK_PLANKS, Items.STICK)
        output.sconce(DnDBlocks.SPRUCE_SCONCE, Items.SPRUCE_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.BIRCH_SCONCE, Items.BIRCH_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.JUNGLE_SCONCE, Items.JUNGLE_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.ACACIA_SCONCE, Items.ACACIA_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.DARK_OAK_SCONCE, Items.DARK_OAK_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.MANGROVE_SCONCE, Items.MANGROVE_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.CHERRY_SCONCE, Items.CHERRY_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.CASCADE_SCONCE, DnDBlocks.CASCADE_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.SYPIA_SCONCE, DnDBlocks.SYPIA_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.VERDANT_SCONCE, DnDBlocks.VERDANT_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.BAMBOO_SCONCE, Items.BAMBOO_PLANKS, Items.BAMBOO)
//        output.sconce(DnDBlocks.CRIMSON_SCONCE, Items.CRIMSON_PLANKS, Items.STICK)
//        output.sconce(DnDBlocks.WARPED_SCONCE, Items.WARPED_PLANKS, Items.STICK)

        output.sconce(DnDBlocks.IRON_SCONCE, IRON_INGOT, IRON_NUGGET)
//        output.sconce(DnDBlocks.GOLD_SCONCE, Items.GOLD_INGOT, Items.GOLD_NUGGET)

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