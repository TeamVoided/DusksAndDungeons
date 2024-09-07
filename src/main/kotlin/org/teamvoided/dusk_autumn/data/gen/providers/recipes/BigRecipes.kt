package org.teamvoided.dusk_autumn.data.gen.providers.recipes

import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDBigBlocks
import org.teamvoided.dusk_autumn.util.*

object BigRecipes {
    fun generateBigRecipes(e: RecipeExporter) {
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDBigBlocks.BIG_CHAIN, 1)
            .ingredient('I', Ingredient.ofItems(Items.IRON_INGOT))
            .ingredient('N', Ingredient.ofItems(Items.IRON_NUGGET))
            .pattern("I")
            .pattern("N")
            .pattern("I")
            .criterion(Items.IRON_NUGGET)
            .criterion(Items.IRON_INGOT)
            .offerTo(e)
        e.createBigLantern(DnDBigBlocks.BIG_LANTERN, Blocks.TORCH, Blocks.LANTERN)
        e.createBigLantern(DnDBigBlocks.BIG_SOUL_LANTERN, Blocks.SOUL_TORCH, Blocks.SOUL_LANTERN)
        e.createCandle(DnDBigBlocks.BIG_CANDLE, Items.HONEYCOMB_BLOCK)
        e.createCandle(DnDBigBlocks.SOUL_CANDLE, Items.HONEYCOMB, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        e.createCandle(DnDBigBlocks.BIG_SOUL_CANDLE, Items.HONEYCOMB_BLOCK, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        DnDBlockLists.dye.forEachIndexed { idx, dye ->
            e.createDyed(DnDBlockLists.bigCandles[idx + 1].first, DnDBigBlocks.BIG_CANDLE, dye)
            e.createDyed(DnDBlockLists.soulCandles[idx + 1].first, DnDBigBlocks.SOUL_CANDLE, dye)
            e.createDyed(DnDBlockLists.bigSoulCandles[idx + 1].first, DnDBigBlocks.BIG_SOUL_CANDLE, dye)
        }
    }
}