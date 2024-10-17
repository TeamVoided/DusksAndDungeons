package org.teamvoided.dusk_autumn.data.gen.providers.recipes

import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.dusk_autumn.data.tags.DnDItemTags
import org.teamvoided.dusk_autumn.init.blocks.DnDStoneBlocks
import org.teamvoided.dusk_autumn.util.*

object StoneRecipes {
    fun generateStoneRecipes(e: RecipeExporter) {

        e.createGravestone(DnDStoneBlocks.GRAVESTONE, Blocks.STONE_BRICKS)
        e.createGravestone(DnDStoneBlocks.DEEPSLATE_GRAVESTONE, Blocks.DEEPSLATE_BRICKS)
        e.createGravestone(DnDStoneBlocks.TUFF_GRAVESTONE, Blocks.TUFF_BRICKS)
        e.createGravestone(DnDStoneBlocks.BLACKSTONE_GRAVESTONE, Blocks.POLISHED_BLACKSTONE_BRICKS)
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDStoneBlocks.HEADSTONE)
            .ingredient('#', Items.IRON_BARS)
            .ingredient('%', Items.IRON_NUGGET)
            .pattern("%#%")
            .pattern("###")
            .pattern("%#%")
            .criterion(Items.IRON_BARS)
            .criterion(DnDItemTags.GRAVESTONES)
            .offerTo(e)

        e.createSmallSquare(DnDStoneBlocks.POLISHED_STONE, Blocks.STONE)
        e.createStackedCraft(DnDStoneBlocks.STONE_PILLAR, Blocks.STONE_BRICKS, ItemTags.STONE_BRICKS)
        e.createStackedCraft(DnDStoneBlocks.DEEPSLATE_PILLAR, Blocks.DEEPSLATE_BRICKS)
        e.createStonecuttedFromList(
            listOf(
                Blocks.STONE,
                Blocks.STONE_BRICKS,
                DnDStoneBlocks.POLISHED_STONE
            ),
            DnDStoneBlocks.STONE_PILLAR
        )
        e.createStonecuttedFromList(
            listOf(
                Blocks.COBBLED_DEEPSLATE,
                Blocks.DEEPSLATE_BRICKS,
                Blocks.POLISHED_DEEPSLATE
            ),
            DnDStoneBlocks.DEEPSLATE_PILLAR
        )
        e.createOvergrown(DnDStoneBlocks.OVERGROWN_POLISHED_STONE, DnDStoneBlocks.POLISHED_STONE)
        e.createOvergrown(DnDStoneBlocks.OVERGROWN_COBBLESTONE, Blocks.COBBLESTONE)
        e.createOvergrown(DnDStoneBlocks.OVERGROWN_STONE_BRICKS, Blocks.STONE_BRICKS)
        e.createStonecuttedFromList(
            listOf(
                Blocks.STONE,
                Blocks.STONE_BRICKS
            ),
            DnDStoneBlocks.POLISHED_STONE
        )
        e.createStonecuttedSet(
            listOf(DnDStoneBlocks.POLISHED_STONE, Blocks.STONE, Blocks.STONE_BRICKS),
            null,
            DnDStoneBlocks.POLISHED_STONE_STAIRS,
            DnDStoneBlocks.POLISHED_STONE_SLAB,
            DnDStoneBlocks.POLISHED_STONE_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDStoneBlocks.MOSSY_POLISHED_STONE, Blocks.MOSSY_STONE_BRICKS),
            null,
            DnDStoneBlocks.MOSSY_POLISHED_STONE_STAIRS,
            DnDStoneBlocks.MOSSY_POLISHED_STONE_SLAB,
            DnDStoneBlocks.MOSSY_POLISHED_STONE_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDStoneBlocks.OVERGROWN_POLISHED_STONE, DnDStoneBlocks.OVERGROWN_STONE_BRICKS),
            null,
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE_STAIRS,
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE_SLAB,
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDStoneBlocks.OVERGROWN_COBBLESTONE),
            null,
            DnDStoneBlocks.OVERGROWN_COBBLESTONE_STAIRS,
            DnDStoneBlocks.OVERGROWN_COBBLESTONE_SLAB,
            DnDStoneBlocks.OVERGROWN_COBBLESTONE_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDStoneBlocks.OVERGROWN_STONE_BRICKS),
            null,
            DnDStoneBlocks.OVERGROWN_STONE_BRICK_STAIRS,
            DnDStoneBlocks.OVERGROWN_STONE_BRICK_SLAB,
            DnDStoneBlocks.OVERGROWN_STONE_BRICK_WALL
        )
    }
}