package org.teamvoided.dusks_and_dungeons.datagen.old.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.*
import net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId
import net.minecraft.data.recipes.RecipeProvider.getConversionRecipeName
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.datagen.old.util.createCount
import org.teamvoided.dusks_and_dungeons.datagen.old.util.createDoubleCraft
import org.teamvoided.dusks_and_dungeons.datagen.old.util.createPiles
import org.teamvoided.dusks_and_dungeons.datagen.old.util.createPumpkinStuffs
import org.teamvoided.dusks_and_dungeons.datagen.old.util.criterion
import org.teamvoided.dusks_and_dungeons.datagen.old.util.smeltDefault
import org.teamvoided.dusks_and_dungeons.datagen.old.util.suffix
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists

object FloraRecipes {

    fun generateFloraRecipes(output: RecipeOutput) {

        RecipeProvider.threeByThreePacker(
            output, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK, DnDBlocks.WARPED_WART
        )

        output.createPiles(DnDBlocks.BAMBOO_PILE, Blocks.BAMBOO_BLOCK)
        output.createPiles(DnDBlocks.STRIPPED_BAMBOO_PILE, Blocks.STRIPPED_BAMBOO_BLOCK)
        DnDBlockLists.logPiles.forEachIndexed { idx, pile ->
            output.createPiles(pile, DnDBlockLists.logsAndStrippedLogs[idx].first)
        }
        DnDBlockLists.stripedLogPiles.forEachIndexed { idx, pile ->
            output.createPiles(pile, DnDBlockLists.logsAndStrippedLogs[idx].second)
        }
        DnDBlockLists.leafPiles.forEachIndexed { idx, pile ->
            output.createPiles(pile, DnDBlockLists.leaves[idx])
        }
        FabricRecipeProvider.oneToOneConversionRecipe(output, Items.PURPLE_DYE, DnDItems.MOONBERRIES, "purple_dye")

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DnDBlocks.ROOT_BLOCK)
            .define('#', Blocks.HANGING_ROOTS)
            .pattern("##")
            .pattern("##")
            .criterion(Blocks.HANGING_ROOTS)
            .save(output)
        output.createCount(Blocks.HANGING_ROOTS, DnDBlocks.ROOT_BLOCK, 4)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.RABBIT_STEW)
            .requires(Items.BAKED_POTATO)
            .requires(Items.COOKED_RABBIT)
            .requires(Items.BOWL)
            .requires(Items.CARROT)
            .requires(DnDBlocks.GOLDEN_MUSHROOM)
            .group("rabbit_stew")
            .criterion(Items.COOKED_RABBIT)
            .save(output, getConversionRecipeName(Items.RABBIT_STEW, DnDBlocks.GOLDEN_MUSHROOM))

        pumpkins(output)
        corn(output)

        flowerbeds(output)
    }

    fun pumpkins(e: RecipeOutput) {
        e.createDoubleCraft(DnDBlocks.SMALL_GLOWING_PUMPKIN, DnDBlocks.SMALL_CARVED_PUMPKIN, Items.TORCH)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.PUMPKIN_SEEDS, 2)
            .requires(DnDBlocks.SMALL_PUMPKIN)
            .criterion(DnDBlocks.SMALL_PUMPKIN)
            .save(e)

        e.createPumpkinStuffs(
            DnDBlocks.LANTERN_PUMPKIN, DnDBlocks.CARVED_LANTERN_PUMPKIN, DnDBlocks.GLOWING_LANTERN_PUMPKIN,
            DnDBlocks.SMALL_LANTERN_PUMPKIN, DnDBlocks.SMALL_CARVED_LANTERN_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_LANTERN_PUMPKIN, DnDItems.LANTERN_PUMPKIN_SEEDS
        )
        e.createPumpkinStuffs(
            DnDBlocks.MOSSKIN_PUMPKIN, DnDBlocks.CARVED_MOSSKIN_PUMPKIN, DnDBlocks.GLOWING_MOSSKIN_PUMPKIN,
            DnDBlocks.SMALL_MOSSKIN_PUMPKIN, DnDBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN, DnDItems.MOSSKIN_PUMPKIN_SEEDS
        )
        e.createPumpkinStuffs(
            DnDBlocks.PALE_PUMPKIN, DnDBlocks.CARVED_PALE_PUMPKIN, DnDBlocks.GLOWING_PALE_PUMPKIN,
            DnDBlocks.SMALL_PALE_PUMPKIN, DnDBlocks.SMALL_CARVED_PALE_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_PALE_PUMPKIN, DnDItems.PALE_PUMPKIN_SEEDS,
        )
        e.createPumpkinStuffs(
            DnDBlocks.GLOOM_PUMPKIN, DnDBlocks.CARVED_GLOOM_PUMPKIN, DnDBlocks.GLOWING_GLOOM_PUMPKIN,
            DnDBlocks.SMALL_GLOOM_PUMPKIN, DnDBlocks.SMALL_CARVED_GLOOM_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_GLOOM_PUMPKIN, DnDItems.GLOOM_PUMPKIN_SEEDS,
        )
    }

    fun corn(e: RecipeOutput) {
        e.createCount(DnDItems.CORN_KERNELS, DnDItems.CORN, 1)

        e.smeltDefault(DnDBlocks.CORN_SYRUP_BLOCK, DnDBlocks.CORN_BLOCK)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DnDItems.CORN_SYRUP_BOTTLE, 4)
            .requires(DnDBlocks.CORN_SYRUP_BLOCK)
            .requires(Items.GLASS_BOTTLE, 4)
            .criterion(DnDBlocks.CORN_SYRUP_BLOCK)
            .save(e)
        RecipeProvider.twoByTwoPacker(
            e,
            RecipeCategory.REDSTONE,
            DnDBlocks.CORN_SYRUP_BLOCK,
            DnDItems.CORN_SYRUP_BOTTLE
        )

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DnDItems.CORN, 6)
            .requires(DnDBlocks.CORN)
            .criterion(DnDBlocks.CORN)
            .save(e, getDefaultRecipeId(DnDItems.CORN).suffix("_from_plant"))

        RecipeProvider.nineBlockStorageRecipes(
            e,
            RecipeCategory.MISC, DnDItems.CORN,
            RecipeCategory.BUILDING_BLOCKS, DnDBlocks.CORN_BLOCK
        )
    }

    fun flowerbeds(e: RecipeOutput) {
        e.toDye(DnDBlocks.COLD_WILDFLOWER, Items.PURPLE_DYE)
        e.toDye(DnDBlocks.WHITE_PETALS, Items.WHITE_DYE)
        e.toDye(DnDBlocks.RED_PETALS, Items.RED_DYE)
        e.toDye(DnDBlocks.ORANGE_PETALS, Items.ORANGE_DYE)
        e.toDye(DnDBlocks.BLUE_PETALS, Items.LIGHT_BLUE_DYE)
    }

    fun RecipeOutput.toDye(item: ItemLike, dye: ItemLike, count: Int = 1) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, dye, count)
            .requires(item)
            .criterion(item)
            .save(this)
    }

}