package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.CookingRecipeJsonFactory
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.feature_flags.FeatureFlags
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.block.DuskBlockFamilies.recipesBlockFamilies
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
        offerCrackingRecipe(e, DuskBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DuskBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK)
        createChiseledBlockRecipe(
            RecipeCategory.BUILDING_BLOCKS,
            DuskBlocks.CHISELED_RED_NETHER_BRICKS,
            Ingredient.ofItems(Blocks.RED_NETHER_BRICK_SLAB)
        ).criterion("has_nether_bricks", conditionsFromItem(Blocks.RED_NETHER_BRICKS)).offerTo(e)
        e.createDiagonalRecipe(DuskBlocks.MIXED_NETHER_BRICKS, Blocks.NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        offerCrackingRecipe(e, DuskBlocks.CRACKED_MIXED_NETHER_BRICKS, DuskBlocks.MIXED_NETHER_BRICKS)
        e.createFence(DuskBlocks.MIXED_NETHER_BRICK_FENCE, DuskBlocks.MIXED_NETHER_BRICKS, Items.NETHER_BRICK)
        createChiseledBlockRecipe(
            RecipeCategory.BUILDING_BLOCKS,
            DuskBlocks.CHISELED_MIXED_NETHER_BRICKS,
            Ingredient.ofItems(DuskBlocks.MIXED_NETHER_BRICK_SLAB)
        ).criterion("has_nether_bricks", conditionsFromItem(DuskBlocks.MIXED_NETHER_BRICKS)).offerTo(e)
        createStonecuttingRecipe(e, RecipeCategory.BUILDING_BLOCKS, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_FENCE)
        e.createStackedCraft(DuskBlocks.NETHER_BRICK_PILLAR, Blocks.NETHER_BRICKS, DuskItemTags.NETHER_BRICKS)
        e.createStackedCraft(DuskBlocks.RED_NETHER_BRICK_PILLAR, Blocks.RED_NETHER_BRICKS, DuskItemTags.NETHER_BRICKS)
        e.createStackedCraft(
            DuskBlocks.MIXED_NETHER_BRICK_PILLAR,
            DuskBlocks.MIXED_NETHER_BRICKS,
            DuskItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(Blocks.NETHER_BRICKS, DuskBlocks.POLISHED_NETHER_BRICKS),
            DuskBlocks.POLISHED_NETHER_BRICKS,
            DuskBlocks.POLISHED_NETHER_BRICK_STAIRS,
            DuskBlocks.POLISHED_NETHER_BRICK_SLAB,
            DuskBlocks.POLISHED_NETHER_BRICK_WALL,
            listOf(Blocks.NETHER_BRICK_FENCE, DuskBlocks.NETHER_BRICK_PILLAR)
        )
        e.createStonecuttedSet(
            listOf(Blocks.RED_NETHER_BRICKS, DuskBlocks.POLISHED_RED_NETHER_BRICKS),
            DuskBlocks.POLISHED_RED_NETHER_BRICKS,
            DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS,
            DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB,
            DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL,
            listOf(
                DuskBlocks.RED_NETHER_BRICK_FENCE,
                DuskBlocks.RED_NETHER_BRICK_PILLAR,
                DuskBlocks.CHISELED_RED_NETHER_BRICKS
            )
        )
        e.createStonecuttedSet(
            listOf(DuskBlocks.MIXED_NETHER_BRICKS),
            null,
            DuskBlocks.MIXED_NETHER_BRICK_STAIRS,
            DuskBlocks.MIXED_NETHER_BRICK_SLAB,
            DuskBlocks.MIXED_NETHER_BRICK_WALL,
            listOf(DuskBlocks.MIXED_NETHER_BRICK_PILLAR, DuskBlocks.RED_NETHER_BRICK_FENCE)
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
