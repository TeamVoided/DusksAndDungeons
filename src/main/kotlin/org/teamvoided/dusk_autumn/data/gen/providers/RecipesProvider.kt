package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.feature_flags.FeatureFlags
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.dusk_autumn.block.DnDFamilies.recipesBlockFamilies
import org.teamvoided.dusk_autumn.block.DnDLists
import org.teamvoided.dusk_autumn.block.DnDLists.leafPiles
import org.teamvoided.dusk_autumn.block.DnDLists.logPiles
import org.teamvoided.dusk_autumn.data.tags.DnDItemTags
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.util.*
import java.util.concurrent.CompletableFuture

class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {
        recipesBlockFamilies.forEach { generateFamily(e, it, FeatureFlags.VANILLA_SET) }
        offerPlanksRecipe(e, DnDBlocks.CASCADE_PLANKS, DnDItemTags.CASCADE_LOGS, 4)
        offerBarkBlockRecipe(e, DnDBlocks.CASCADE_WOOD, DnDBlocks.CASCADE_LOG)
        offerBarkBlockRecipe(e, DnDBlocks.STRIPPED_CASCADE_WOOD, DnDBlocks.STRIPPED_CASCADE_LOG)
        offerHangingSignRecipe(e, DnDItems.CASCADE_HANGING_SIGN, DnDBlocks.STRIPPED_CASCADE_LOG)
        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, DnDBlocks.BLUE_DOOR, 3)
            .ingredient('#', Ingredient.ofItems(DnDBlocks.CASCADE_PLANKS.asItem()))
            .ingredient('@', Ingredient.ofItems(Items.GOLD_NUGGET))
            .pattern("## ")
            .pattern("##@")
            .pattern("## ")
            .criterion(DnDBlocks.CASCADE_PLANKS.asItem())
            .offerTo(e)
        offerShapelessRecipe(e, Items.BLUE_DYE, DnDBlocks.BLUE_PETALS, "blue_dye")

        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDBlocks.BIG_CHAIN, 1)
            .ingredient('I', Ingredient.ofItems(Items.IRON_INGOT))
            .ingredient('N', Ingredient.ofItems(Items.IRON_NUGGET))
            .pattern("I")
            .pattern("N")
            .pattern("I")
            .criterion(Items.IRON_NUGGET)
            .criterion(Items.IRON_INGOT)
            .offerTo(e)
        e.createBigLantern(DnDBlocks.BIG_LANTERN, Blocks.TORCH, Blocks.LANTERN)
        e.createBigLantern(DnDBlocks.BIG_SOUL_LANTERN, Blocks.SOUL_TORCH, Blocks.SOUL_LANTERN)
        e.createCandle(DnDBlocks.BIG_CANDLE)
        e.createCandle(DnDBlocks.SOUL_CANDLE, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        e.createCandle(DnDBlocks.BIG_SOUL_CANDLE, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        DnDLists.dye.forEachIndexed { idx, dye ->
            e.createDyed(DnDLists.bigCandles[idx + 1].first, dye)
            e.createDyed(DnDLists.soulCandles[idx + 1].first, dye)
            e.createDyed(DnDLists.bigSoulCandles[idx + 1].first, dye)
        }
        e.createFence(DnDBlocks.BRICK_FENCE, Blocks.BRICKS, Items.BRICK)
        e.createStair(DnDBlocks.NETHERRACK_STAIRS, Blocks.NETHERRACK)
        e.createSlab(DnDBlocks.NETHERRACK_SLAB, Blocks.NETHERRACK)
        e.createwall(DnDBlocks.NETHERRACK_WALL, Blocks.NETHERRACK)
        e.createStackedCraft(DnDBlocks.NETHER_BRICK_PILLAR, Blocks.NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.NETHERRACK),
            null,
            DnDBlocks.NETHERRACK_STAIRS,
            DnDBlocks.NETHERRACK_SLAB,
            DnDBlocks.NETHERRACK_WALL
        )
        e.createStonecuttedSet(
            listOf(Blocks.NETHER_BRICKS, DnDBlocks.POLISHED_NETHER_BRICKS),
            DnDBlocks.POLISHED_NETHER_BRICKS,
            DnDBlocks.POLISHED_NETHER_BRICK_STAIRS,
            DnDBlocks.POLISHED_NETHER_BRICK_SLAB,
            DnDBlocks.POLISHED_NETHER_BRICK_WALL,
        )
        e.createStonecuttedSet(
            listOf(Blocks.NETHER_BRICKS),
            DnDBlocks.NETHER_BRICK_PILLAR, null, null,
            Blocks.NETHER_BRICK_FENCE
        )
        offerCrackingRecipe(e, DnDBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DnDBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK)
        createChiseledBlockRecipe(
            RecipeCategory.BUILDING_BLOCKS,
            DnDBlocks.CHISELED_RED_NETHER_BRICKS,
            Ingredient.ofItems(Blocks.RED_NETHER_BRICK_SLAB)
        ).criterion(Blocks.RED_NETHER_BRICKS).offerTo(e)
        e.createStackedCraft(DnDBlocks.RED_NETHER_BRICK_PILLAR, Blocks.RED_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.RED_NETHER_BRICKS),
            DnDBlocks.RED_NETHER_BRICK_PILLAR,
            DnDBlocks.CHISELED_RED_NETHER_BRICKS, null,
            DnDBlocks.RED_NETHER_BRICK_FENCE
        )
        e.createDiagonalRecipe(DnDBlocks.MIXED_NETHER_BRICKS, Blocks.NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DnDBlocks.MIXED_NETHER_BRICK_FENCE, DnDBlocks.MIXED_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.MIXED_NETHER_BRICK_PILLAR,
            DnDBlocks.MIXED_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(Blocks.RED_NETHER_BRICKS, DnDBlocks.POLISHED_RED_NETHER_BRICKS),
            DnDBlocks.POLISHED_RED_NETHER_BRICKS,
            DnDBlocks.POLISHED_RED_NETHER_BRICK_STAIRS,
            DnDBlocks.POLISHED_RED_NETHER_BRICK_SLAB,
            DnDBlocks.POLISHED_RED_NETHER_BRICK_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.MIXED_NETHER_BRICKS),
            null,
            DnDBlocks.MIXED_NETHER_BRICK_STAIRS,
            DnDBlocks.MIXED_NETHER_BRICK_SLAB,
            DnDBlocks.MIXED_NETHER_BRICK_WALL,
            listOf(DnDBlocks.MIXED_NETHER_BRICK_FENCE, DnDBlocks.MIXED_NETHER_BRICK_PILLAR)
        )
        e.createDiagonalRecipe(
            DnDBlocks.BLUE_NETHER_BRICKS,
            DnDItemTags.CRAFTS_WARPED_NETHER_BRICKS,
            Blocks.NETHER_BRICKS
        )
        e.createFence(DnDBlocks.BLUE_NETHER_BRICK_FENCE, DnDBlocks.BLUE_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.BLUE_NETHER_BRICK_PILLAR,
            DnDBlocks.BLUE_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.BLUE_NETHER_BRICKS),
            null,
            DnDBlocks.BLUE_NETHER_BRICK_STAIRS,
            DnDBlocks.BLUE_NETHER_BRICK_SLAB,
            DnDBlocks.BLUE_NETHER_BRICK_WALL,
            listOf(DnDBlocks.BLUE_NETHER_BRICK_FENCE, DnDBlocks.BLUE_NETHER_BRICK_PILLAR)
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.BLUE_NETHER_BRICKS, DnDBlocks.POLISHED_BLUE_NETHER_BRICKS),
            DnDBlocks.POLISHED_BLUE_NETHER_BRICKS,
            DnDBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS,
            DnDBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB,
            DnDBlocks.POLISHED_BLUE_NETHER_BRICK_WALL
        )
        e.createDiagonalRecipe(DnDBlocks.MIXED_BLUE_NETHER_BRICKS, DnDBlocks.BLUE_NETHER_BRICKS, Blocks.NETHER_BRICKS)
        e.createFence(DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDBlocks.MIXED_BLUE_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
            DnDBlocks.MIXED_BLUE_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.MIXED_BLUE_NETHER_BRICKS),
            null,
            DnDBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS,
            DnDBlocks.MIXED_BLUE_NETHER_BRICK_SLAB,
            DnDBlocks.MIXED_BLUE_NETHER_BRICK_WALL,
            listOf(DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR)
        )
        e.createDiagonalRecipe(
            DnDBlocks.GRAY_NETHER_BRICKS,
            DnDItemTags.CRAFTS_ASHEN_NETHER_BRICKS,
            Blocks.NETHER_BRICKS

        )
        e.createFence(DnDBlocks.GRAY_NETHER_BRICK_FENCE, DnDBlocks.GRAY_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.GRAY_NETHER_BRICK_PILLAR,
            DnDBlocks.GRAY_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.GRAY_NETHER_BRICKS),
            null,
            DnDBlocks.GRAY_NETHER_BRICK_STAIRS,
            DnDBlocks.GRAY_NETHER_BRICK_SLAB,
            DnDBlocks.GRAY_NETHER_BRICK_WALL,
            listOf(DnDBlocks.GRAY_NETHER_BRICK_FENCE, DnDBlocks.GRAY_NETHER_BRICK_PILLAR)
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.GRAY_NETHER_BRICKS, DnDBlocks.POLISHED_GRAY_NETHER_BRICKS),
            DnDBlocks.POLISHED_GRAY_NETHER_BRICKS,
            DnDBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS,
            DnDBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB,
            DnDBlocks.POLISHED_GRAY_NETHER_BRICK_WALL
        )
        e.createDiagonalRecipe(DnDBlocks.MIXED_GRAY_NETHER_BRICKS, Blocks.NETHER_BRICKS, DnDBlocks.GRAY_NETHER_BRICKS)
        e.createFence(DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDBlocks.MIXED_GRAY_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
            DnDBlocks.MIXED_GRAY_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DnDBlocks.MIXED_GRAY_NETHER_BRICKS),
            null,
            DnDBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS,
            DnDBlocks.MIXED_GRAY_NETHER_BRICK_SLAB,
            DnDBlocks.MIXED_GRAY_NETHER_BRICK_WALL,
            listOf(DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR)
        )

        ShapedRecipeJsonFactory.create(RecipeCategory.COMBAT, DnDItems.BLACKSTONE_SWORD)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("X")
            .pattern("X")
            .pattern("#")
            .criterion(Items.BLACKSTONE).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_PICKAXE)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("XXX")
            .pattern(" # ")
            .pattern(" # ")
            .criterion(Items.BLACKSTONE).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_AXE)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("XX")
            .pattern("X#")
            .pattern(" #")
            .criterion(Items.BLACKSTONE).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_SHOVEL)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("X")
            .pattern("#").pattern("#")
            .criterion(Items.BLACKSTONE).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_HOE)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("XX")
            .pattern(" #")
            .pattern(" #")
            .criterion(Items.BLACKSTONE).offerTo(e)

        e.createOvergrown(DnDBlocks.OVERGROWN_COBBLESTONE, Blocks.COBBLESTONE)
        e.createOvergrown(DnDBlocks.OVERGROWN_STONE_BRICKS, Blocks.STONE_BRICKS)
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
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDBlocks.ROOT_BLOCK, 1)
            .ingredient(Blocks.HANGING_ROOTS, 4)
            .criterion(Blocks.HANGING_ROOTS).offerTo(e)
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, Blocks.HANGING_ROOTS, 4)
            .ingredient(DnDBlocks.ROOT_BLOCK, 1)
            .criterion(DnDBlocks.ROOT_BLOCK).offerTo(e)
        (leafPiles + logPiles).forEach { (pile, block) ->
            e.createPiles(pile, block)
        }
        ShapedRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.FARMERS_HAT, 1)
            .ingredient('#', Ingredient.ofItems(Items.WHEAT))
            .ingredient('@', Ingredient.ofItems(Items.STRING))
            .ingredient('%', Ingredient.ofItems(Items.LEATHER))
            .pattern("###")
            .pattern("@%@")
            .pattern("# #")
            .criterion(DnDItems.FARMERS_HAT).offerTo(e)
        offerShapelessRecipe(e, Items.PURPLE_DYE, DnDItems.MOONBERRIES, "purple_dye")
        e.cobbled()
    }
}
