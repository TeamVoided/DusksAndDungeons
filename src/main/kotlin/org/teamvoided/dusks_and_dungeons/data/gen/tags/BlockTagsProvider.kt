package org.teamvoided.dusks_and_dungeons.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.data.tags.c.CBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.COLOR_CONSORTIUM
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.ICE_SET
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.dusks_and_dungeons.util.datagen.createWoodTags
import org.teamvoided.taglighting.data.tags.TaglightingBlockTags
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.devin.extensions.tag.add
import org.teamvoided.voidlib.devin.extensions.tag.createColorTags
import org.teamvoided.voidlib.devin.extensions.tag.createSetTags
import java.util.concurrent.CompletableFuture

@Suppress("LongMethod")
class BlockTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    val excludeWood: List<AbstractBlockSet> = listOf(
        DnDBlocks.CRIMSON_HYPHAE, DnDBlocks.WARPED_HYPHAE,
        DnDBlocks.STRIPPED_CRIMSON_HYPHAE, DnDBlocks.STRIPPED_WARPED_HYPHAE
    )

    override fun addTags(arg: HolderLookup.Provider) {
        SETS.filterNot(WOOD_SETS::contains).forEach { it.createSetTags(::getOrCreateTagBuilder) }
        COLOR_CONSORTIUM.forEach { it.createColorTags(::getOrCreateTagBuilder) }
        duskTags()
        woodTags()
        vanillaTags()
        conventionTags()

        getOrCreateTagBuilder(TaglightingBlockTags.MOB_MOVING_STICKY_BLOCK)
            .add(DnDBlocks.CORN_SYRUP_BLOCK)

        overlayTags()
    }

    private fun duskTags() {
        getOrCreateTagBuilder(DnDBlockTags.HOLLOW_LOGS)
            .forceAddTag(DnDBlockTags.HOLLOW_LOGS_THAT_BURN)
            .add(DnDBlocks.HOLLOW_CRIMSON_STEM)
            .add(DnDBlocks.HOLLOW_WARPED_STEM)
            .add(DnDBlocks.HOLLOW_STRIPPED_CRIMSON_STEM)
            .add(DnDBlocks.HOLLOW_STRIPPED_WARPED_STEM)
        getOrCreateTagBuilder(DnDBlockTags.HOLLOW_LOGS_THAT_BURN)
            .add(DnDBlocks.HOLLOW_OAK_LOG)
            .add(DnDBlocks.HOLLOW_SPRUCE_LOG)
            .add(DnDBlocks.HOLLOW_BIRCH_LOG)
            .add(DnDBlocks.HOLLOW_JUNGLE_LOG)
            .add(DnDBlocks.HOLLOW_ACACIA_LOG)
            .add(DnDBlocks.HOLLOW_DARK_OAK_LOG)
            .add(DnDBlocks.HOLLOW_MANGROVE_LOG)
            .add(DnDBlocks.HOLLOW_CHERRY_LOG)
            .add(DnDBlocks.HOLLOW_CASCADE_LOG)
            .add(DnDBlocks.HOLLOW_STRIPPED_OAK_LOG)
            .add(DnDBlocks.HOLLOW_STRIPPED_SPRUCE_LOG)
            .add(DnDBlocks.HOLLOW_STRIPPED_BIRCH_LOG)
            .add(DnDBlocks.HOLLOW_STRIPPED_JUNGLE_LOG)
            .add(DnDBlocks.HOLLOW_STRIPPED_ACACIA_LOG)
            .add(DnDBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG)
            .add(DnDBlocks.HOLLOW_STRIPPED_MANGROVE_LOG)
            .add(DnDBlocks.HOLLOW_STRIPPED_CHERRY_LOG)
            .add(DnDBlocks.HOLLOW_STRIPPED_CASCADE_LOG)
        getOrCreateTagBuilder(DnDBlockTags.CASCADE_LOGS)
            .add(DnDBlocks.CASCADE_LOG)
            .add(DnDBlocks.CASCADE_WOOD.parent)
            .add(DnDBlocks.STRIPPED_CASCADE_LOG)
            .add(DnDBlocks.STRIPPED_CASCADE_WOOD)
        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_PLANKS)
            .add(FLAMMABLE_PLANKS)
        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_LOGS)
            .forceAddTag(DnDBlockTags.CASCADE_LOGS)
            .forceAddTag(DnDBlockTags.LOG_PILES_THAT_BURN)
            .forceAddTag(DnDBlockTags.HOLLOW_LOGS_THAT_BURN)
            .add(FLAMMABLE_LOGS)
        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_LEAVES)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .add(FLAMMABLE_LEAVES)

        getOrCreateTagBuilder(DnDBlockTags.LOG_PILES)
            .forceAddTag(DnDBlockTags.LOG_PILES_THAT_BURN)
            .add(DnDBlocks.CRIMSON_STEM_PILE, DnDBlocks.WARPED_STEM_PILE)
            .add(DnDBlocks.STRIPPED_CRIMSON_STEM_PILE, DnDBlocks.STRIPPED_WARPED_STEM_PILE)
        getOrCreateTagBuilder(DnDBlockTags.LOG_PILES_THAT_BURN)
            .add(
                DnDBlocks.OAK_LOG_PILE,
                DnDBlocks.SPRUCE_LOG_PILE,
                DnDBlocks.BIRCH_LOG_PILE,
                DnDBlocks.JUNGLE_LOG_PILE,
                DnDBlocks.ACACIA_LOG_PILE,
                DnDBlocks.DARK_OAK_LOG_PILE,
                DnDBlocks.MANGROVE_LOG_PILE,
                DnDBlocks.CHERRY_LOG_PILE,
                DnDBlocks.CASCADE_LOG_PILE,
                DnDBlocks.BAMBOO_PILE,
            )
            .add(
                DnDBlocks.STRIPPED_OAK_LOG_PILE,
                DnDBlocks.STRIPPED_SPRUCE_LOG_PILE,
                DnDBlocks.STRIPPED_BIRCH_LOG_PILE,
                DnDBlocks.STRIPPED_JUNGLE_LOG_PILE,
                DnDBlocks.STRIPPED_ACACIA_LOG_PILE,
                DnDBlocks.STRIPPED_DARK_OAK_LOG_PILE,
                DnDBlocks.STRIPPED_MANGROVE_LOG_PILE,
                DnDBlocks.STRIPPED_CHERRY_LOG_PILE,
                DnDBlocks.STRIPPED_CASCADE_LOG_PILE,
                DnDBlocks.STRIPPED_BAMBOO_PILE,
            )
        getOrCreateTagBuilder(DnDBlockTags.LEAF_PILES)
            .add(DnDBlocks.OAK_LEAF_PILE)
            .add(DnDBlocks.SPRUCE_LEAF_PILE)
            .add(DnDBlocks.BIRCH_LEAF_PILE)
            .add(DnDBlocks.JUNGLE_LEAF_PILE)
            .add(DnDBlocks.ACACIA_LEAF_PILE)
            .add(DnDBlocks.DARK_OAK_LEAF_PILE)
            .add(DnDBlocks.MANGROVE_LEAF_PILE)
            .add(DnDBlocks.CHERRY_LEAF_PILE)
            .add(DnDBlocks.AZALEA_LEAF_PILE)
            .add(DnDBlocks.FLOWERING_AZALEA_LEAF_PILE)
            .add(DnDBlocks.CASCADE_LEAF_PILE)
            .add(DnDBlocks.GOLDEN_BIRCH_LEAF_PILE)
        getOrCreateTagBuilder(DnDBlockTags.LEAF_PILES_PLACE_ON)
            .forceAddTag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN)
            .add(Blocks.COBBLESTONE)
            .add(Blocks.MOSSY_COBBLESTONE)
            .add(DnDBlocks.OVERGROWN_COBBLESTONE.parent)
            .forceAddTag(BlockTags.STONE_BRICKS)
            .forceAddTag(BlockTags.LOGS)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.CARVED_PUMPKINS)
            .forceAddTag(DnDBlockTags.GLOWING_PUMPKINS)
            .add(Blocks.MELON)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DnDBlockTags.FLOWERBEDS)
            .add(DnDBlockLists.flowerbedBlocks)
        getOrCreateTagBuilder(DnDBlockTags.VIVIONBEDS)
            .add(DnDBlockLists.vivionbedBlocks)
        getOrCreateTagBuilder(DnDBlockTags.VIVIONBED_PLACEABLE)
            .addOptionalTag(TaglightingBlockTags.SUPPORTS_NYLIUM_PLANTS)
            .forceAddTag(BlockTags.DIRT)
            .forceAddTag(BlockTags.NYLIUM)
            .add(Blocks.FARMLAND)
            .add(Blocks.SOUL_SOIL)
        getOrCreateTagBuilder(DnDBlockTags.ICE_BLOCK_TRANSLUCENT)
            .add(Blocks.ICE)
            .add(ICE_SET.list)
        // Candles
        getOrCreateTagBuilder(DnDBlockTags.BIG_CANDLES)
            .add(DnDBlocks.BIG_CANDLES)
            .forceAddTag(DnDBlockTags.BIG_SOUL_CANDLES)
        getOrCreateTagBuilder(DnDBlockTags.SOUL_CANDLES)
            .add(DnDBlocks.SOUL_CANDLES)
        getOrCreateTagBuilder(DnDBlockTags.BIG_SOUL_CANDLES)
            .add(DnDBlocks.BIG_SOUL_CANDLES)
        getOrCreateTagBuilder(DnDBlockTags.CANDELABRAS)
            .add(DnDBlocks.CANDELABRAS)
            .forceAddTag(DnDBlockTags.SOUL_CANDELABRAS)
        getOrCreateTagBuilder(DnDBlockTags.SOUL_CANDELABRAS)
            .add(DnDBlocks.SOUL_CANDELABRAS)

        getOrCreateTagBuilder(DnDBlockTags.GRAVESTONES)
            .forceAddTag(DnDBlockTags.SMALL_GRAVESTONES)
            .add(DnDBlocks.STONE_GRAVESTONE)
            .add(DnDBlocks.DEEPSLATE_GRAVESTONE)
            .add(DnDBlocks.TUFF_GRAVESTONE)
            .add(DnDBlocks.BLACKSTONE_GRAVESTONE)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_GRAVESTONES)
            .add(DnDBlocks.SMALL_STONE_GRAVESTONE)
            .add(DnDBlocks.SMALL_DEEPSLATE_GRAVESTONE)
            .add(DnDBlocks.SMALL_TUFF_GRAVESTONE)
            .add(DnDBlocks.SMALL_BLACKSTONE_GRAVESTONE)
        getOrCreateTagBuilder(DnDBlockTags.HEADSTONES)
            .add(DnDBlocks.HEADSTONE)

        getOrCreateTagBuilder(DnDBlockTags.NETHER_BRICKS)
            .forceAddTag(DnDBlockTags.CRACKED_NETHER_BRICKS)
            .forceAddTag(DnDBlockTags.POLISHED_NETHER_BRICKS)
            .add(Blocks.NETHER_BRICKS)
            .add(DnDBlocks.MIXED_RED_NETHER_BRICKS.parent)
            .add(Blocks.RED_NETHER_BRICKS)
            .add(DnDBlocks.BLUE_NETHER_BRICKS.parent)
            .add(DnDBlocks.MIXED_BLUE_NETHER_BRICKS.parent)
            .add(DnDBlocks.GRAY_NETHER_BRICKS.parent)
            .add(DnDBlocks.MIXED_GRAY_NETHER_BRICKS.parent)
        getOrCreateTagBuilder(DnDBlockTags.CRACKED_NETHER_BRICKS)
            .add(Blocks.CRACKED_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_MIXED_RED_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_BLUE_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_GRAY_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS)
        getOrCreateTagBuilder(DnDBlockTags.POLISHED_NETHER_BRICKS)
            .add(DnDBlocks.POLISHED_NETHER_BRICKS.parent)
            .add(DnDBlocks.POLISHED_RED_NETHER_BRICKS.parent)
            .add(DnDBlocks.POLISHED_BLUE_NETHER_BRICKS.parent)
            .add(DnDBlocks.POLISHED_GRAY_NETHER_BRICKS.parent)


        getOrCreateTagBuilder(DnDBlockTags.WARPED_NETHER_WART_PLACEABLE)
            .addOptionalTag(TaglightingBlockTags.SUPPORTS_NETHER_WART)
            .add(Blocks.SOUL_SAND)
        getOrCreateTagBuilder(DnDBlockTags.CHILL_CHARGE_AFFECTS)
            .forceAddTag(BlockTags.CANDLES)
            .forceAddTag(BlockTags.CAMPFIRES)
        getOrCreateTagBuilder(DnDBlockTags.MOONBERRY_CAN_PLACE_ON)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DnDBlockTags.FARMLAND_PLACES_UNDER)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.CARVED_PUMPKINS)
            .forceAddTag(DnDBlockTags.GLOWING_PUMPKINS)
            .forceAddTag(BlockTags.CROPS)
            .forceAddTag(BlockTags.SAPLINGS)
            .forceAddTag(BlockTags.FLOWERS)
            .add(Blocks.MELON)
        getOrCreateTagBuilder(DnDBlockTags.REPLACEABLE_OR_DIRT)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.REPLACEABLE_BY_TREES)
            .forceAddTag(BlockTags.DIRT)
        getOrCreateTagBuilder(DnDBlockTags.FALLEN_TREE_REPLACEABLE)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.REPLACEABLE_BY_TREES)

        getOrCreateTagBuilder(DnDBlockTags.PUMPKIN_PATCH_PLACE_ON)
            .forceAddTag(BlockTags.DIRT)
            .forceAddTag(DnDBlockTags.PUMPKIN_BLOCKS)
            .forceAddTag(ConventionalBlockTags.COBBLESTONES)
            .add(Blocks.FARMLAND)


        getOrCreateTagBuilder(DnDBlockTags.CORN_STORAGE)
            .add(DnDBlocks.CORN_BLOCK)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKIN_STEMS)
            .add(DnDBlocks.LANTERN_PUMPKIN_STEM)
            .add(DnDBlocks.MOSSKIN_PUMPKIN_STEM)
            .add(DnDBlocks.PALE_PUMPKIN_STEM)
            .add(DnDBlocks.GLOOM_PUMPKIN_STEM)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKIN_BLOCKS)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.CARVED_PUMPKINS)
            .forceAddTag(DnDBlockTags.GLOWING_PUMPKINS)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKIN_EIGHTHS)
            .forceAddTag(DnDBlockTags.SMALL_PUMPKINS)
            .forceAddTag(DnDBlockTags.SMALL_CARVED_PUMPKINS)
            .forceAddTag(DnDBlockTags.SMALL_GLOWING_PUMPKINS)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS)
            .add(Blocks.PUMPKIN)
            .add(DnDBlocks.LANTERN_PUMPKIN)
            .add(DnDBlocks.MOSSKIN_PUMPKIN)
            .add(DnDBlocks.PALE_PUMPKIN)
            .add(DnDBlocks.GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.CARVED_PUMPKINS)
            .add(Blocks.CARVED_PUMPKIN)
            .add(DnDBlocks.CARVED_LANTERN_PUMPKIN)
            .add(DnDBlocks.CARVED_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.CARVED_PALE_PUMPKIN)
            .add(DnDBlocks.CARVED_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.GLOWING_PUMPKINS)
            .add(Blocks.JACK_O_LANTERN)
            .add(DnDBlocks.GLOWING_LANTERN_PUMPKIN)
            .add(DnDBlocks.GLOWING_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.GLOWING_PALE_PUMPKIN)
            .add(DnDBlocks.GLOWING_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_PUMPKINS)
            .add(DnDBlocks.SMALL_PUMPKIN)
            .add(DnDBlocks.SMALL_LANTERN_PUMPKIN)
            .add(DnDBlocks.SMALL_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.SMALL_PALE_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_CARVED_PUMPKINS)
            .add(DnDBlocks.SMALL_CARVED_PUMPKIN)
            .add(DnDBlocks.SMALL_CARVED_LANTERN_PUMPKIN)
            .add(DnDBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.SMALL_CARVED_PALE_PUMPKIN)
            .add(DnDBlocks.SMALL_CARVED_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_GLOWING_PUMPKINS)
            .add(DnDBlocks.SMALL_GLOWING_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOWING_LANTERN_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOWING_PALE_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOWING_GLOOM_PUMPKIN)

        getOrCreateTagBuilder(TaglightingBlockTags.CANNOT_CONNECT_TO)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.CARVED_PUMPKINS)
            .forceAddTag(DnDBlockTags.GLOWING_PUMPKINS)
    }

    private fun woodTags() {
        WOOD_SETS.filterNot(excludeWood::contains).forEach { it.createWoodTags(::getOrCreateTagBuilder) }
        // TODO make wood walls work as part of walls tags
        // getOrCreateTagBuilder(BlockTags.WALLS).forceAddTag(DnDBlockTags.WOODEN_WALLS)
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).forceAddTag(DnDBlockTags.WOODEN_WALLS)

        getOrCreateTagBuilder(DnDBlockTags.WOODEN_WALLS)
            .forceAddTag(DnDBlockTags.WOOD_WALLS)
            .add(DnDBlocks.OAK_WALL)
            .add(DnDBlocks.SPRUCE_WALL)
            .add(DnDBlocks.BIRCH_WALL)
            .add(DnDBlocks.JUNGLE_WALL)
            .add(DnDBlocks.ACACIA_WALL)
            .add(DnDBlocks.DARK_OAK_WALL)
            .add(DnDBlocks.MANGROVE_WALL)
            .add(DnDBlocks.CHERRY_WALL)
            .add(DnDBlocks.CRIMSON_WALL)
            .add(DnDBlocks.WARPED_WALL)
            .add(DnDBlocks.BAMBOO_WALL)
            .add(DnDBlocks.BAMBOO_MOSAIC_WALL)
            .add(DnDBlocks.CASCADE_WALL)
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).forceAddTag(DnDBlockTags.WOOD_STAIRS)
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).forceAddTag(DnDBlockTags.WOOD_SLABS)


        getOrCreateTagBuilder(DnDBlockTags.WOOD_STAIRS)
            .forceAddTag(DnDBlockTags.WOOD_STAIRS_THAT_BURN)
            .add(
                DnDBlocks.CRIMSON_HYPHAE.stairs, DnDBlocks.WARPED_HYPHAE.stairs,
                DnDBlocks.STRIPPED_CRIMSON_HYPHAE.stairs, DnDBlocks.STRIPPED_WARPED_HYPHAE.stairs
            )
        getOrCreateTagBuilder(DnDBlockTags.WOOD_SLABS)
            .forceAddTag(DnDBlockTags.WOOD_SLABS_THAT_BURN)
            .add(
                DnDBlocks.CRIMSON_HYPHAE.slab, DnDBlocks.WARPED_HYPHAE.slab,
                DnDBlocks.STRIPPED_CRIMSON_HYPHAE.slab, DnDBlocks.STRIPPED_WARPED_HYPHAE.slab
            )
        getOrCreateTagBuilder(DnDBlockTags.WOOD_WALLS)
            .forceAddTag(DnDBlockTags.WOOD_WALLS_THAT_BURN)
            .add(
                DnDBlocks.CRIMSON_HYPHAE.wall, DnDBlocks.WARPED_HYPHAE.wall,
                DnDBlocks.STRIPPED_CRIMSON_HYPHAE.wall, DnDBlocks.STRIPPED_WARPED_HYPHAE.wall
            )

    }

    private fun vanillaTags() {
        vanillaBlockTypesTags()
        vanillaBlockTypeShapeTags()
        vanillaOverlayTags()

        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .forceAddTag(DnDBlockTags.FLOWERBEDS)
            .forceAddTag(DnDBlockTags.VIVIONBEDS)
            .forceAddTag(DnDBlockTags.SMALL_PUMPKINS)
            .forceAddTag(DnDBlockTags.SMALL_CARVED_PUMPKINS)
            .forceAddTag(DnDBlockTags.SMALL_GLOWING_PUMPKINS)
            .add(DnDBlocks.ROOT_BLOCK)

        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES)
            .add(DnDBlocks.MOONBERRY_VINE)
            .forceAddTag(DnDBlockTags.PUMPKIN_STEMS)
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .add(DnDBlocks.CORN_CROP)
            .forceAddTag(DnDBlockTags.PUMPKIN_STEMS)
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
            .forceAddTag(DnDBlockTags.FLOWERBEDS)
            .forceAddTag(DnDBlockTags.VIVIONBEDS)
        getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
            .forceAddTag(BlockTags.LOGS)
            .forceAddTag(DnDBlockTags.HOLLOW_LOGS)
        getOrCreateTagBuilder(BlockTags.GUARDED_BY_PIGLINS)
            .forceAddTag(DnDBlockTags.GRAVESTONES)
        getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.CARVED_PUMPKINS)
            .add(DnDBlocks.GOLDEN_MUSHROOM)


        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .add(SWORDABLE)
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(PICKAXABLE)
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
            .forceAddTag(DnDBlockTags.LOG_PILES)
            .add(AXABLE)
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(SHOVELABLE)
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .add(HOEABLE)


        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(Blocks.ICE, Blocks.SNOW)

        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
            .add(DnDBlocks.BIG_SCAFFOLDING)

    }

    private fun vanillaBlockTypesTags() {
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .forceAddTag(DnDBlockTags.CASCADE_LOGS)
        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(DnDBlocks.CASCADE_PLANKS)
        getOrCreateTagBuilder(BlockTags.CROPS)
            .add(DnDBlocks.CORN_CROP)
            .add(DnDBlocks.GOLDEN_BEETROOTS)
            .add(DnDBlocks.MOONBERRY_VINELET)
            .forceAddTag(DnDBlockTags.PUMPKIN_STEMS)

        getOrCreateTagBuilder(BlockTags.FLOWERS)
            .add(DnDBlocks.CHERRY_LEAF_PILE)
            .forceAddTag(DnDBlockTags.FLOWERBEDS)
            .forceAddTag(DnDBlockTags.VIVIONBEDS)
        getOrCreateTagBuilder(BlockTags.LEAVES)
            .add(DnDBlocks.CASCADE_LEAVES)
            .add(DnDBlocks.GOLDEN_BIRCH_LEAVES)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.SAPLINGS)
            .add(DnDBlocks.CASCADE_SAPLING)
            .add(DnDBlocks.GOLDEN_BIRCH_SAPLING)
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
            .add(DnDBlocks.POTTED_CASCADE_SAPLING)
            .add(DnDBlocks.POTTED_GOLDEN_BIRCH_SAPLING)

        getOrCreateTagBuilder(BlockTags.CANDLES)
            .forceAddTag(DnDBlockTags.SOUL_CANDLES)
            .forceAddTag(DnDBlockTags.BIG_CANDLES)
            .forceAddTag(DnDBlockTags.CANDELABRAS)

        getOrCreateTagBuilder(BlockTags.CANDLE_CAKES)
            .add(DnDBlocks.BIG_CANDLE_CAKES)
            .add(DnDBlocks.SOUL_CANDLE_CAKES)
            .add(DnDBlocks.BIG_SOUL_CANDLE_CAKES)

        getOrCreateTagBuilder(BlockTags.STONE_BRICKS)
            .add(DnDBlocks.OVERGROWN_STONE_BRICKS.parent)
    }

    private fun vanillaBlockTypeShapeTags() {
        getOrCreateTagBuilder(BlockTags.FENCES)
            .add(DnDBlocks.RED_NETHER_BRICK_FENCE)
            .add(DnDBlocks.BLUE_NETHER_BRICK_FENCE)
            .add(DnDBlocks.GRAY_NETHER_BRICK_FENCE)
            .add(DnDBlocks.MIXED_RED_NETHER_BRICK_FENCE)
            .add(DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE)
            .add(DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE)
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
            .add(DnDBlocks.CASCADE_STAIRS)
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
            .add(DnDBlocks.CASCADE_SLAB)
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add(DnDBlocks.CASCADE_DOOR)
            .add(DnDBlocks.BLUE_DOOR)
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add(DnDBlocks.CASCADE_TRAPDOOR)
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(DnDBlocks.CASCADE_FENCE)
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(DnDBlocks.CASCADE_FENCE_GATE)
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
            .add(DnDBlocks.CASCADE_PRESSURE_PLATE)
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
            .add(DnDBlocks.CASCADE_BUTTON)
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
            .add(DnDBlocks.CASCADE_SIGN)
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
            .add(DnDBlocks.CASCADE_WALL_SIGN)
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
            .add(DnDBlocks.CASCADE_HANGING_SIGN)
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
            .add(DnDBlocks.CASCADE_WALL_HANGING_SIGN)
    }

    private fun vanillaOverlayTags() {
        getOrCreateTagBuilder(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON)
            .add(DnDBlocks.CORN_SYRUP_BLOCK)
    }

    private fun conventionTags() {
        getOrCreateTagBuilder(ConventionalBlockTags.COBBLESTONES).add(DnDBlocks.OVERGROWN_COBBLESTONE.parent)
        getOrCreateTagBuilder(ConventionalBlockTags.CHAINS).add(DnDBlocks.BIG_CHAIN)

        getOrCreateTagBuilder(CBlockTags.SCAFFOLDING).add(Blocks.SCAFFOLDING, DnDBlocks.BIG_SCAFFOLDING)
    }

    private fun overlayTags() {
        DnDBlocks.OVERLAYS.forEach {
            getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK).add(it.podzol, it.mycelium)
            getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES).add(it.gravel)
            getOrCreateTagBuilder(BlockTags.NETHER_CARVER_REPLACEABLES).add(it.soulSand, it.soulSoil)
            getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
                .add(it.gravel, it.sand, it.redSand, it.soulSand, it.soulSoil)
            getOrCreateTagBuilder(BlockTags.DIRT).add(it.dirt, it.grass, it.podzol, it.mycelium, it.coarseDirt, it.mud)
            getOrCreateTagBuilder(BlockTags.SAND).add(it.sand, it.redSand)
            getOrCreateTagBuilder(BlockTags.SNOW).add(it.snow)
            getOrCreateTagBuilder(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON).add(it.mud, it.soulSand)
            getOrCreateTagBuilder(BlockTags.WITHER_SUMMON_BASE_BLOCKS).add(it.soulSand, it.soulSoil)
            getOrCreateTagBuilder(BlockTags.SOUL_SPEED_BLOCKS).add(it.soulSand, it.soulSoil)
            getOrCreateTagBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(it.soulSand, it.soulSoil)
            getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON).add(it.gravel)
            getOrCreateTagBuilder(BlockTags.VALID_SPAWN).add(it.grass, it.podzol)
            getOrCreateTagBuilder(BlockTags.ANIMALS_SPAWNABLE_ON).add(it.grass)
            getOrCreateTagBuilder(BlockTags.FOXES_SPAWNABLE_ON).add(it.grass, it.podzol, it.coarseDirt, it.snow)
            getOrCreateTagBuilder(BlockTags.MOOSHROOMS_SPAWNABLE_ON).add(it.mycelium)
            getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(it.dirt, it.grass, it.podzol, it.coarseDirt)
            getOrCreateTagBuilder(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH).add(it.mud)
        }
    }
}
