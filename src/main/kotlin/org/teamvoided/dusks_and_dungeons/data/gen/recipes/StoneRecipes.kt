package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDStoneBlocks
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecuttingSet

object StoneRecipes {
    fun generateStoneRecipes(e: RecipeExporter) {
        e.createGragestones(
            DnDStoneBlocks.GRAVESTONE,
            DnDStoneBlocks.SMALL_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_GRAVESTONE,
            Blocks.STONE_BRICKS
        )
        e.createGragestones(
            DnDStoneBlocks.DEEPSLATE_GRAVESTONE,
            DnDStoneBlocks.SMALL_DEEPSLATE_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_DEEPSLATE_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_DEEPSLATE_GRAVESTONE,
            Blocks.DEEPSLATE_BRICKS
        )
        e.createGragestones(
            DnDStoneBlocks.TUFF_GRAVESTONE,
            DnDStoneBlocks.SMALL_TUFF_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_TUFF_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_TUFF_GRAVESTONE,
            Blocks.TUFF_BRICKS
        )
        e.createGragestones(
            DnDStoneBlocks.BLACKSTONE_GRAVESTONE,
            DnDStoneBlocks.SMALL_BLACKSTONE_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_BLACKSTONE_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_BLACKSTONE_GRAVESTONE,
            Blocks.POLISHED_BLACKSTONE_BRICKS
        )
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDStoneBlocks.HEADSTONE)
            .ingredient('#', Items.IRON_BARS)
            .ingredient('%', Items.IRON_NUGGET)
            .pattern("%#%")
            .pattern("###")
            .pattern("%#%")
            .criterion(Items.IRON_BARS)
            .criterion(DnDItemTags.GRAVESTONES)
            .offerTo(e)

        e.createStackedCraft(DnDStoneBlocks.STONE_PILLAR, Blocks.STONE_BRICKS, ItemTags.STONE_BRICKS)
        e.createStackedCraft(DnDStoneBlocks.DEEPSLATE_PILLAR, Blocks.DEEPSLATE_BRICKS)
        e.createStonecuttedFromList(
            DnDStoneBlocks.STONE_PILLAR,
            Blocks.STONE, Blocks.STONE_BRICKS, DnDStoneBlocks.POLISHED_STONE.parent
        )
        e.createStonecuttedFromList(
            DnDStoneBlocks.DEEPSLATE_PILLAR,
            Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE_BRICKS, Blocks.POLISHED_DEEPSLATE,
        )
        e.createSmallSquare(DnDStoneBlocks.POLISHED_STONE, Blocks.STONE, 4)
        e.createTwoPiece(DnDStoneBlocks.MOSSY_POLISHED_STONE, DnDStoneBlocks.POLISHED_STONE, Items.MOSS_BLOCK, "_from_moss")
        e.createTwoPiece(DnDStoneBlocks.MOSSY_POLISHED_STONE, DnDStoneBlocks.POLISHED_STONE, Items.VINE, "_from_vine")

        e.createOvergrown(DnDStoneBlocks.OVERGROWN_POLISHED_STONE, DnDStoneBlocks.POLISHED_STONE)
        e.createOvergrown(DnDStoneBlocks.OVERGROWN_COBBLESTONE, Blocks.COBBLESTONE)
        e.createOvergrown(DnDStoneBlocks.OVERGROWN_STONE_BRICKS, Blocks.STONE_BRICKS)

        e.createStonecuttingSet(DnDStoneBlocks.POLISHED_STONE, Blocks.STONE, Blocks.STONE_BRICKS)
        e.createStonecuttingSet(DnDStoneBlocks.MOSSY_POLISHED_STONE, Blocks.MOSSY_STONE_BRICKS)
        e.createStonecuttingSet(DnDStoneBlocks.OVERGROWN_POLISHED_STONE, DnDStoneBlocks.OVERGROWN_STONE_BRICKS.parent)
    }
}