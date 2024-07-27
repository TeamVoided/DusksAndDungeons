package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.data.server.recipe.SingleItemRecipeJsonFactory
import net.minecraft.feature_flags.FeatureFlagBitSet
import net.minecraft.feature_flags.FeatureFlags
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.TagKey
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor
import org.teamvoided.dusk_autumn.block.DuskBlockFamilies.allBlockFamilies
import org.teamvoided.dusk_autumn.data.tags.DuskItemTags
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.concurrent.CompletableFuture

class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {
        allBlockFamilies.forEach { generateFamily(e, it, FeatureFlagBitSet.ofFlag(FeatureFlags.VANILLA)) }
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
        createChiseledBlockRecipe(
            RecipeCategory.BUILDING_BLOCKS,
            DuskBlocks.CHISELED_RED_NETHER_BRICKS,
            Ingredient.ofItems(Blocks.RED_NETHER_BRICK_SLAB)
        ).criterion("has_nether_bricks", conditionsFromItem(Blocks.RED_NETHER_BRICKS)).offerTo(e)
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DuskBlocks.MIXED_NETHER_BRICKS, 4)
            .ingredient(Blocks.NETHER_BRICKS, 2)
            .ingredient(Blocks.RED_NETHER_BRICKS, 2)
        e.createPillar(DuskBlocks.NETHER_BRICK_PILLAR, Blocks.NETHER_BRICKS, DuskItemTags.NETHER_BRICKS)
        e.createPillar(DuskBlocks.RED_NETHER_BRICK_PILLAR, Blocks.RED_NETHER_BRICKS, DuskItemTags.NETHER_BRICKS)
        e.createStonecutted(
            listOf(Blocks.NETHER_BRICKS, DuskBlocks.POLISHED_NETHER_BRICKS),
            DuskBlocks.POLISHED_NETHER_BRICKS,
            DuskBlocks.POLISHED_NETHER_BRICK_STAIRS,
            DuskBlocks.POLISHED_NETHER_BRICK_SLAB,
            DuskBlocks.POLISHED_NETHER_BRICK_WALL
        )
        e.createStonecutted(
            listOf(Blocks.RED_NETHER_BRICKS, DuskBlocks.POLISHED_RED_NETHER_BRICKS),
            DuskBlocks.POLISHED_NETHER_BRICKS,
            DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS,
            DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB,
            DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL
        )
        e.createStonecutted(
            DuskBlocks.OVERGROWN_COBBLESTONE,
            null,
            DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS,
            DuskBlocks.OVERGROWN_COBBLESTONE_SLAB,
            DuskBlocks.OVERGROWN_COBBLESTONE_WALL
        )
        e.createStonecutted(
            DuskBlocks.OVERGROWN_STONE_BRICKS,
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
        e.createLeafPiles(DuskBlocks.OAK_LEAF_PILE, Items.OAK_LEAVES)
        e.createLeafPiles(DuskBlocks.SPRUCE_LEAF_PILE, Items.SPRUCE_LEAVES)
        e.createLeafPiles(DuskBlocks.BIRCH_LEAF_PILE, Items.BIRCH_LEAVES)
        e.createLeafPiles(DuskBlocks.JUNGLE_LEAF_PILE, Items.JUNGLE_LEAVES)
        e.createLeafPiles(DuskBlocks.ACACIA_LEAF_PILE, Items.ACACIA_LEAVES)
        e.createLeafPiles(DuskBlocks.DARK_OAK_LEAF_PILE, Items.DARK_OAK_LEAVES)
        e.createLeafPiles(DuskBlocks.MANGROVE_LEAF_PILE, Items.MANGROVE_LEAVES)
        e.createLeafPiles(DuskBlocks.AZALEA_LEAF_PILE, Items.AZALEA_LEAVES)
        e.createLeafPiles(DuskBlocks.FLOWERING_AZALEA_LEAF_PILE, Items.FLOWERING_AZALEA_LEAVES)
        e.createLeafPiles(DuskBlocks.CHERRY_LEAF_PILE, Items.CHERRY_LEAVES)
        e.createLeafPiles(DuskBlocks.CASCADE_LEAF_PILE, DuskBlocks.CASCADE_LEAVES.asItem())
        e.createLeafPiles(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, DuskBlocks.GOLDEN_BIRCH_LEAVES.asItem())
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

    fun RecipeExporter.createStonecutted(
        input: Block,
        polish: ItemConvertible?,
        stair: ItemConvertible?,
        slab: ItemConvertible?,
        wall: ItemConvertible?
    ) {
        this.createStonecutted(listOf(input), polish, stair, slab, wall)
    }

    fun RecipeExporter.createStonecutted(
        input: List<Block>,
        polish: ItemConvertible?,
        stair: ItemConvertible?,
        slab: ItemConvertible?,
        wall: ItemConvertible?
    ) {
        input.forEach {
            if (polish != null && polish == it)
                createStonecuttingRecipe(this, RecipeCategory.BUILDING_BLOCKS, polish, it)
            if (stair != null) createStonecuttingRecipe(this, RecipeCategory.BUILDING_BLOCKS, stair, it)
            if (slab != null) createStonecuttingRecipe(this, RecipeCategory.BUILDING_BLOCKS, slab, it, 2)
            if (wall != null) createStonecuttingRecipe(this, RecipeCategory.DECORATIONS, wall, it)
        }
    }

    fun RecipeExporter.createPillar(output: ItemConvertible, block: ItemConvertible, itemTag: TagKey<Item>) {
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
            .ingredient('#', block)
            .pattern("#")
            .pattern("#")
            .criterion("has_" + itemTag.id.path, conditionsFromTag(itemTag)).offerTo(this)
    }

    fun RecipeExporter.createCobblestoned(output: ItemConvertible, convert: ItemConvertible, cobble: ItemConvertible) {
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
            .ingredient(convert, 2).ingredient(cobble, 2)
            .criterion("has_cobble", conditionsFromItem(cobble)).offerTo(this)
    }

    fun RecipeExporter.createLeafPiles(output: ItemConvertible, input: ItemConvertible) {
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 8)
            .ingredient('#', Ingredient.ofItems(input))
            .pattern("##")
            .criterion(
                hasItem(input), conditionsFromItem(
                    input
                )
            ).offerTo(this)
    }

    fun RecipeExporter.cobbled() {
        var cobble = Blocks.COBBLESTONE
        this.createCobblestoned(DuskBlocks.ROCKY_GRASS, Blocks.GRASS_BLOCK, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_PODZOL, Blocks.PODZOL, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_MYCELIUM, Blocks.MYCELIUM, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_DIRT, Blocks.DIRT, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_MUD, Blocks.MUD, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_SNOW, Blocks.SNOW_BLOCK, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_GRAVEL, Blocks.GRAVEL, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_SAND, Blocks.SAND, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_RED_SAND, Blocks.RED_SAND, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_SOUL_SAND, Blocks.SOUL_SAND, cobble)
        this.createCobblestoned(DuskBlocks.ROCKY_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
        cobble = Blocks.COBBLED_DEEPSLATE
        this.createCobblestoned(DuskBlocks.SLATED_GRASS, Blocks.GRASS_BLOCK, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_PODZOL, Blocks.PODZOL, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_MYCELIUM, Blocks.MYCELIUM, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_DIRT, Blocks.DIRT, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_MUD, Blocks.MUD, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_SNOW, Blocks.SNOW_BLOCK, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_GRAVEL, Blocks.GRAVEL, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_SAND, Blocks.SAND, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_RED_SAND, Blocks.RED_SAND, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_SOUL_SAND, Blocks.SOUL_SAND, cobble)
        this.createCobblestoned(DuskBlocks.SLATED_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
        cobble = Blocks.BLACKSTONE
        this.createCobblestoned(DuskBlocks.BLACKSTONE_GRASS, Blocks.GRASS_BLOCK, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_PODZOL, Blocks.PODZOL, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_MYCELIUM, Blocks.MYCELIUM, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_DIRT, Blocks.DIRT, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_MUD, Blocks.MUD, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_SNOW, Blocks.SNOW_BLOCK, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_GRAVEL, Blocks.GRAVEL, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_SAND, Blocks.SAND, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_RED_SAND, Blocks.RED_SAND, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_SOUL_SAND, Blocks.SOUL_SAND, cobble)
        this.createCobblestoned(DuskBlocks.BLACKSTONE_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
    }
}
