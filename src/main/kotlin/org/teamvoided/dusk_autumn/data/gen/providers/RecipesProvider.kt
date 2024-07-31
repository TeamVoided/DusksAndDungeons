package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Block
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
import org.teamvoided.dusk_autumn.block.DuskBlockFamilies.recipesBlockFamilies
import org.teamvoided.dusk_autumn.block.DuskBlockLists
import org.teamvoided.dusk_autumn.block.DuskBlockLists.leafPiles
import org.teamvoided.dusk_autumn.block.DuskBlockLists.logPiles
import org.teamvoided.dusk_autumn.data.tags.DuskItemTags
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import org.teamvoided.dusk_autumn.util.*
import java.util.concurrent.CompletableFuture

class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {
        recipesBlockFamilies.forEach { generateFamily(e, it, FeatureFlags.VANILLA_SET) }
        offerPlanksRecipe(e, DuskBlocks.CASCADE_PLANKS, DuskItemTags.CASCADE_LOGS, 4)
        offerBarkBlockRecipe(e, DuskBlocks.CASCADE_WOOD, DuskBlocks.CASCADE_LOG)
        offerBarkBlockRecipe(e, DuskBlocks.STRIPPED_CASCADE_WOOD, DuskBlocks.STRIPPED_CASCADE_LOG)
        offerHangingSignRecipe(e, DuskItems.CASCADE_HANGING_SIGN, DuskBlocks.STRIPPED_CASCADE_LOG)
        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, DuskBlocks.BLUE_DOOR, 3)
            .ingredient('#', Ingredient.ofItems(DuskBlocks.CASCADE_PLANKS.asItem()))
            .ingredient('@', Ingredient.ofItems(Items.GOLD_NUGGET))
            .pattern("## ")
            .pattern("##@")
            .pattern("## ")
            .criterion(
                "has_cascade_planks", conditionsFromItem(
                    DuskBlocks.CASCADE_PLANKS.asItem()
                )
            ).offerTo(e)
        offerShapelessRecipe(e, Items.BLUE_DYE, DuskBlocks.BLUE_PETALS, "blue_dye")

        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DuskBlocks.BIG_CHAIN, 1)
            .ingredient('I', Ingredient.ofItems(Items.IRON_INGOT))
            .ingredient('N', Ingredient.ofItems(Items.IRON_NUGGET))
            .pattern("I")
            .pattern("N")
            .pattern("I")
            .criterion("has_iron_nugget", conditionsFromItem(Items.IRON_NUGGET))
            .criterion("has_iron_ingot", conditionsFromItem(Items.IRON_INGOT)).offerTo(e)
        e.createBigLantern(DuskBlocks.BIG_LANTERN, Blocks.TORCH, Blocks.LANTERN)
        e.createBigLantern(DuskBlocks.BIG_SOUL_LANTERN, Blocks.SOUL_TORCH, Blocks.SOUL_LANTERN)
        e.createCandle(DuskBlocks.BIG_CANDLE)
        e.createCandle(DuskBlocks.SOUL_CANDLE, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        e.createCandle(DuskBlocks.BIG_SOUL_CANDLE, ItemTags.SOUL_FIRE_BASE_BLOCKS)
        DuskBlockLists.dye.forEachIndexed{ idx, dye ->
            e.createDyed(DuskBlockLists.bigCandles[idx+1].first, dye)
            e.createDyed(DuskBlockLists.soulCandles[idx+1].first, dye)
            e.createDyed(DuskBlockLists.bigSoulCandles[idx+1].first, dye)
        }

        e.createFence(DuskBlocks.BRICK_FENCE, Blocks.BRICKS, Items.BRICK)
        e.createStair(DuskBlocks.NETHERRACK_STAIRS, Blocks.NETHERRACK)
        e.createSlab(DuskBlocks.NETHERRACK_SLAB, Blocks.NETHERRACK)
        e.createwall(DuskBlocks.NETHERRACK_WALL, Blocks.NETHERRACK)
        e.createStackedCraft(DuskBlocks.NETHER_BRICK_PILLAR, Blocks.NETHER_BRICKS, DuskItemTags.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.NETHERRACK),
            null,
            DuskBlocks.NETHERRACK_STAIRS,
            DuskBlocks.NETHERRACK_SLAB,
            DuskBlocks.NETHERRACK_WALL
        )
        e.createStonecuttedSet(
            listOf(Blocks.NETHER_BRICKS, DuskBlocks.POLISHED_NETHER_BRICKS),
            DuskBlocks.POLISHED_NETHER_BRICKS,
            DuskBlocks.POLISHED_NETHER_BRICK_STAIRS,
            DuskBlocks.POLISHED_NETHER_BRICK_SLAB,
            DuskBlocks.POLISHED_NETHER_BRICK_WALL,
        )
        e.createStonecuttedSet(
            listOf(Blocks.NETHER_BRICKS),
            DuskBlocks.NETHER_BRICK_PILLAR, null, null,
            Blocks.NETHER_BRICK_FENCE
        )
        offerCrackingRecipe(e, DuskBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DuskBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK)
        createChiseledBlockRecipe(
            RecipeCategory.BUILDING_BLOCKS,
            DuskBlocks.CHISELED_RED_NETHER_BRICKS,
            Ingredient.ofItems(Blocks.RED_NETHER_BRICK_SLAB)
        ).criterion("has_nether_bricks", conditionsFromItem(Blocks.RED_NETHER_BRICKS)).offerTo(e)
        e.createStackedCraft(DuskBlocks.RED_NETHER_BRICK_PILLAR, Blocks.RED_NETHER_BRICKS, DuskItemTags.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.RED_NETHER_BRICKS),
            DuskBlocks.RED_NETHER_BRICK_PILLAR,
            DuskBlocks.CHISELED_RED_NETHER_BRICKS, null,
            DuskBlocks.RED_NETHER_BRICK_FENCE
        )
        e.createDiagonalRecipe(DuskBlocks.MIXED_NETHER_BRICKS, Blocks.NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DuskBlocks.MIXED_NETHER_BRICK_FENCE, DuskBlocks.MIXED_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DuskBlocks.MIXED_NETHER_BRICK_PILLAR,
            DuskBlocks.MIXED_NETHER_BRICKS,
            DuskItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(Blocks.RED_NETHER_BRICKS, DuskBlocks.POLISHED_RED_NETHER_BRICKS),
            DuskBlocks.POLISHED_RED_NETHER_BRICKS,
            DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS,
            DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB,
            DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL
        )
        e.createStonecuttedSet(
            listOf(DuskBlocks.MIXED_NETHER_BRICKS),
            null,
            DuskBlocks.MIXED_NETHER_BRICK_STAIRS,
            DuskBlocks.MIXED_NETHER_BRICK_SLAB,
            DuskBlocks.MIXED_NETHER_BRICK_WALL,
            listOf(DuskBlocks.MIXED_NETHER_BRICK_FENCE, DuskBlocks.MIXED_NETHER_BRICK_PILLAR)
        )
        e.createFence(DuskBlocks.BLUE_NETHER_BRICK_FENCE, DuskBlocks.BLUE_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DuskBlocks.BLUE_NETHER_BRICK_PILLAR,
            DuskBlocks.BLUE_NETHER_BRICKS,
            DuskItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DuskBlocks.BLUE_NETHER_BRICKS),
            null,
            DuskBlocks.BLUE_NETHER_BRICK_STAIRS,
            DuskBlocks.BLUE_NETHER_BRICK_SLAB,
            DuskBlocks.BLUE_NETHER_BRICK_WALL,
            listOf(DuskBlocks.BLUE_NETHER_BRICK_FENCE, DuskBlocks.BLUE_NETHER_BRICK_PILLAR)
        )
        e.createStonecuttedSet(
            listOf(DuskBlocks.BLUE_NETHER_BRICKS, DuskBlocks.POLISHED_BLUE_NETHER_BRICKS),
            DuskBlocks.POLISHED_BLUE_NETHER_BRICKS,
            DuskBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS,
            DuskBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB,
            DuskBlocks.POLISHED_BLUE_NETHER_BRICK_WALL
        )
        e.createDiagonalRecipe(DuskBlocks.MIXED_BLUE_NETHER_BRICKS, Blocks.NETHER_BRICKS, DuskBlocks.BLUE_NETHER_BRICKS)
        e.createFence(DuskBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DuskBlocks.MIXED_BLUE_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DuskBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
            DuskBlocks.MIXED_BLUE_NETHER_BRICKS,
            DuskItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DuskBlocks.MIXED_BLUE_NETHER_BRICKS),
            null,
            DuskBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS,
            DuskBlocks.MIXED_BLUE_NETHER_BRICK_SLAB,
            DuskBlocks.MIXED_BLUE_NETHER_BRICK_WALL,
            listOf(DuskBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DuskBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR)
        )
        e.createDiagonalRecipe(
            DuskBlocks.GRAY_NETHER_BRICKS,
            Blocks.NETHER_BRICKS,
            DuskItemTags.CRAFTS_ASHEN_NETHER_BRICKS
        )
        e.createFence(DuskBlocks.GRAY_NETHER_BRICK_FENCE, DuskBlocks.GRAY_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DuskBlocks.GRAY_NETHER_BRICK_PILLAR,
            DuskBlocks.GRAY_NETHER_BRICKS,
            DuskItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DuskBlocks.GRAY_NETHER_BRICKS),
            null,
            DuskBlocks.GRAY_NETHER_BRICK_STAIRS,
            DuskBlocks.GRAY_NETHER_BRICK_SLAB,
            DuskBlocks.GRAY_NETHER_BRICK_WALL,
            listOf(DuskBlocks.GRAY_NETHER_BRICK_FENCE, DuskBlocks.GRAY_NETHER_BRICK_PILLAR)
        )
        e.createStonecuttedSet(
            listOf(DuskBlocks.GRAY_NETHER_BRICKS, DuskBlocks.POLISHED_GRAY_NETHER_BRICKS),
            DuskBlocks.POLISHED_GRAY_NETHER_BRICKS,
            DuskBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS,
            DuskBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB,
            DuskBlocks.POLISHED_GRAY_NETHER_BRICK_WALL
        )
        e.createDiagonalRecipe(DuskBlocks.MIXED_GRAY_NETHER_BRICKS, Blocks.NETHER_BRICKS, DuskBlocks.GRAY_NETHER_BRICKS)
        e.createFence(DuskBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DuskBlocks.MIXED_GRAY_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DuskBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
            DuskBlocks.MIXED_GRAY_NETHER_BRICKS,
            DuskItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DuskBlocks.MIXED_GRAY_NETHER_BRICKS),
            null,
            DuskBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS,
            DuskBlocks.MIXED_GRAY_NETHER_BRICK_SLAB,
            DuskBlocks.MIXED_GRAY_NETHER_BRICK_WALL,
            listOf(DuskBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DuskBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR)
        )

        ShapedRecipeJsonFactory.create(RecipeCategory.COMBAT, DuskItems.BLACKSTONE_SWORD)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("X")
            .pattern("X")
            .pattern("#")
            .criterion("has_blackstone", conditionsFromItem(Items.BLACKSTONE)).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DuskItems.BLACKSTONE_PICKAXE)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("XXX")
            .pattern(" # ")
            .pattern(" # ")
            .criterion("has_blackstone", conditionsFromItem(Items.BLACKSTONE)).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DuskItems.BLACKSTONE_AXE)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("XX")
            .pattern("X#")
            .pattern(" #")
            .criterion("has_blackstone", conditionsFromItem(Items.BLACKSTONE)).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DuskItems.BLACKSTONE_SHOVEL)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("X")
            .pattern("#").pattern("#")
            .criterion("has_blackstone", conditionsFromItem(Items.BLACKSTONE)).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DuskItems.BLACKSTONE_HOE)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("XX")
            .pattern(" #")
            .pattern(" #")
            .criterion("has_blackstone", conditionsFromItem(Items.BLACKSTONE)).offerTo(e)

        e.createOvergrown(DuskBlocks.OVERGROWN_COBBLESTONE, Blocks.COBBLESTONE)
        e.createOvergrown(DuskBlocks.OVERGROWN_STONE_BRICKS, Blocks.STONE_BRICKS)
        e.createStonecuttedSet(
            listOf(DuskBlocks.OVERGROWN_COBBLESTONE),
            null,
            DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS,
            DuskBlocks.OVERGROWN_COBBLESTONE_SLAB,
            DuskBlocks.OVERGROWN_COBBLESTONE_WALL
        )
        e.createStonecuttedSet(
            listOf(DuskBlocks.OVERGROWN_STONE_BRICKS),
            null,
            DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS,
            DuskBlocks.OVERGROWN_STONE_BRICK_SLAB,
            DuskBlocks.OVERGROWN_STONE_BRICK_WALL
        )
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DuskBlocks.ROOT_BLOCK, 1)
            .ingredient(Blocks.HANGING_ROOTS, 4)
            .criterion("has_roots", conditionsFromItem(Blocks.HANGING_ROOTS)).offerTo(e)
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, Blocks.HANGING_ROOTS, 4)
            .ingredient(DuskBlocks.ROOT_BLOCK, 1)
            .criterion("has_root_block", conditionsFromItem(DuskBlocks.ROOT_BLOCK)).offerTo(e)
        (leafPiles + logPiles).forEach { (pile, block) ->
            e.createPiles(pile, block)
        }
        ShapedRecipeJsonFactory.create(RecipeCategory.MISC, DuskItems.FARMERS_HAT, 1)
            .ingredient('#', Ingredient.ofItems(Items.WHEAT))
            .ingredient('@', Ingredient.ofItems(Items.STRING))
            .ingredient('%', Ingredient.ofItems(Items.LEATHER))
            .pattern("###")
            .pattern("@%@")
            .pattern("# #")
            .criterion(
                "has_farmers_hat", conditionsFromItem(
                    DuskItems.FARMERS_HAT
                )
            ).offerTo(e)
        offerShapelessRecipe(e, Items.PURPLE_DYE, DuskItems.MOONBERRIES, "purple_dye")
        e.cobbled()
    }
}
