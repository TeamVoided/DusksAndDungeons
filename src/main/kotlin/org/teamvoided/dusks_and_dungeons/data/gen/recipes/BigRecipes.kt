package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.DnDItemLists
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object BigRecipes {
    fun generateBigRecipes(e: RecipeExporter) {
        // Chains and Lanterns
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDBlocks.BIG_CHAIN, 1)
            .pattern("I")
            .pattern("N")
            .pattern("I")
            .ingredient('I', Ingredient.ofItems(Items.IRON_INGOT))
            .ingredient('N', Ingredient.ofItems(Items.IRON_NUGGET))
            .criterion(Items.IRON_NUGGET)
            .criterion(Items.IRON_INGOT)
            .offerTo(e)
        e.createBigLantern(DnDBlocks.BIG_LANTERN, Blocks.TORCH, Blocks.LANTERN)
        e.createBigLantern(DnDBlocks.BIG_SOUL_LANTERN, Blocks.SOUL_TORCH, Blocks.SOUL_LANTERN)
        // Candles
        e.createCandle(DnDBlocks.BIG_CANDLES.uncolored, Items.HONEYCOMB_BLOCK)
        e.createCandle(DnDBlocks.SOUL_CANDLES.uncolored, Items.HONEYCOMB, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        e.createCandle(DnDBlocks.BIG_SOUL_CANDLES.uncolored, Items.HONEYCOMB_BLOCK, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        DnDItemLists.dye.forEachIndexed { index, dye ->
            val idx = index + 1
            e.createDyed(DnDBlockLists.bigCandles[idx].first, DnDBlocks.BIG_CANDLES.uncolored, dye)
            e.createDyed(DnDBlockLists.soulCandles[idx].first, DnDBlocks.SOUL_CANDLES.uncolored, dye)
            e.createDyed(DnDBlockLists.bigSoulCandles[idx].first, DnDBlocks.BIG_SOUL_CANDLES.uncolored, dye)
            e.createDyed(DnDBlockLists.candelabras[idx], DnDBlocks.CANDELABRAS.uncolored, dye, true)
            e.createDyed(DnDBlockLists.soulCandelabras[idx], DnDBlocks.SOUL_CANDELABRAS.uncolored, dye, true)
        }
        DnDBlockLists.allCandelabras.forEach(e::createCandelabra)
    }
}
