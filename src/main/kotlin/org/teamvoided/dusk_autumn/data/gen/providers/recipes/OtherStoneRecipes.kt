package org.teamvoided.dusk_autumn.data.gen.providers.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.util.*

object OtherStoneRecipes {
    fun generateOtherStoneRecipes(e: RecipeExporter) {
        e.createSmallSquare(DnDBlocks.POLISHED_STONE, Blocks.STONE)
        e.createStackedCraft(DnDBlocks.STONE_PILLAR, Blocks.STONE_BRICKS, ItemTags.STONE_BRICKS)
        e.createStackedCraft(DnDBlocks.DEEPSLATE_PILLAR, Blocks.DEEPSLATE_BRICKS)
        e.createStonecuttedFromList(
            listOf(
                Blocks.STONE,
                Blocks.STONE_BRICKS,
                DnDBlocks.POLISHED_STONE
            ),
            DnDBlocks.STONE_PILLAR
        )
        e.createStonecuttedFromList(
            listOf(
                Blocks.COBBLED_DEEPSLATE,
                Blocks.DEEPSLATE_BRICKS,
                Blocks.POLISHED_DEEPSLATE
            ),
            DnDBlocks.DEEPSLATE_PILLAR
        )
        e.createOvergrown(DnDBlocks.OVERGROWN_POLISHED_STONE, DnDBlocks.POLISHED_STONE)
        e.createOvergrown(DnDBlocks.OVERGROWN_COBBLESTONE, Blocks.COBBLESTONE)
        e.createOvergrown(DnDBlocks.OVERGROWN_STONE_BRICKS, Blocks.STONE_BRICKS)
        e.createStonecuttedFromList(
            listOf(
                Blocks.STONE,
                Blocks.STONE_BRICKS
            ),
            DnDBlocks.POLISHED_STONE
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.POLISHED_STONE, Blocks.STONE, Blocks.STONE_BRICKS),
            null,
            DnDBlocks.POLISHED_STONE_STAIRS,
            DnDBlocks.POLISHED_STONE_SLAB,
            DnDBlocks.POLISHED_STONE_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.MOSSY_POLISHED_STONE, Blocks.MOSSY_STONE_BRICKS),
            null,
            DnDBlocks.MOSSY_POLISHED_STONE_STAIRS,
            DnDBlocks.MOSSY_POLISHED_STONE_SLAB,
            DnDBlocks.MOSSY_POLISHED_STONE_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.OVERGROWN_POLISHED_STONE, DnDBlocks.OVERGROWN_STONE_BRICKS),
            null,
            DnDBlocks.OVERGROWN_POLISHED_STONE_STAIRS,
            DnDBlocks.OVERGROWN_POLISHED_STONE_SLAB,
            DnDBlocks.OVERGROWN_POLISHED_STONE_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.OVERGROWN_COBBLESTONE),
            null,
            DnDBlocks.OVERGROWN_COBBLESTONE_STAIRS,
            DnDBlocks.OVERGROWN_COBBLESTONE_SLAB,
            DnDBlocks.OVERGROWN_COBBLESTONE_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.OVERGROWN_STONE_BRICKS),
            null,
            DnDBlocks.OVERGROWN_STONE_BRICK_STAIRS,
            DnDBlocks.OVERGROWN_STONE_BRICK_SLAB,
            DnDBlocks.OVERGROWN_STONE_BRICK_WALL
        )
    }
}