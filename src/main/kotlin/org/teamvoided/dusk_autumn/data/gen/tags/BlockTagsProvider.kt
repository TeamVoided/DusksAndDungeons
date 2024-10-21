package org.teamvoided.dusk_autumn.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.*
import org.teamvoided.dusk_autumn.util.DnDBlockLists
import org.teamvoided.dusk_autumn.util.addAll
import java.util.concurrent.CompletableFuture

@Suppress("LongMethod")
class BlockTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider) {
        duskTags()
        vanillaTags()
        conventionTags()
    }

    private fun duskTags() {
        getOrCreateTagBuilder(DnDBlockTags.HOLLOW_LOGS)
            .forceAddTag(DnDBlockTags.HOLLOW_LOGS_THAT_BURN)
            .add(DnDWoodBlocks.HOLLOW_CRIMSON_STEM)
            .add(DnDWoodBlocks.HOLLOW_WARPED_STEM)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_CRIMSON_STEM)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_WARPED_STEM)
        getOrCreateTagBuilder(DnDBlockTags.HOLLOW_LOGS_THAT_BURN)
            .add(DnDWoodBlocks.HOLLOW_OAK_LOG)
            .add(DnDWoodBlocks.HOLLOW_SPRUCE_LOG)
            .add(DnDWoodBlocks.HOLLOW_BIRCH_LOG)
            .add(DnDWoodBlocks.HOLLOW_JUNGLE_LOG)
            .add(DnDWoodBlocks.HOLLOW_ACACIA_LOG)
            .add(DnDWoodBlocks.HOLLOW_DARK_OAK_LOG)
            .add(DnDWoodBlocks.HOLLOW_MANGROVE_LOG)
            .add(DnDWoodBlocks.HOLLOW_CHERRY_LOG)
            .add(DnDWoodBlocks.HOLLOW_CASCADE_LOG)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_OAK_LOG)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_SPRUCE_LOG)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_BIRCH_LOG)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_JUNGLE_LOG)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_ACACIA_LOG)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_MANGROVE_LOG)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_CHERRY_LOG)
            .add(DnDWoodBlocks.HOLLOW_STRIPPED_CASCADE_LOG)
        getOrCreateTagBuilder(DnDBlockTags.CASCADE_LOGS)
            .add(DnDWoodBlocks.CASCADE_LOG)
            .add(DnDWoodBlocks.CASCADE_WOOD)
            .add(DnDWoodBlocks.STRIPPED_CASCADE_LOG)
            .add(DnDWoodBlocks.STRIPPED_CASCADE_WOOD)
        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_PLANKS)
            .addAll(DnDBlocks.FLAMMABLE_PLANKS)
        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_LOGS)
            .forceAddTag(DnDBlockTags.CASCADE_LOGS)
            .forceAddTag(DnDBlockTags.LOG_PILES_THAT_BURN)
            .forceAddTag(DnDBlockTags.HOLLOW_LOGS_THAT_BURN)
            .addAll(DnDBlocks.FLAMMABLE_LOGS)
        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_LEAVES)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .addAll(DnDBlocks.FLAMMABLE_LEAVES)
        getOrCreateTagBuilder(DnDBlockTags.WOOD_STAIRS)
            .forceAddTag(DnDBlockTags.WOOD_STAIRS_THAT_BURN)
            .add(DnDWoodBlocks.CRIMSON_HYPHAE_STAIRS)
            .add(DnDWoodBlocks.WARPED_HYPHAE_STAIRS)
        getOrCreateTagBuilder(DnDBlockTags.WOOD_STAIRS_THAT_BURN)
            .add(DnDWoodBlocks.OAK_WOOD_STAIRS)
            .add(DnDWoodBlocks.SPRUCE_WOOD_STAIRS)
            .add(DnDWoodBlocks.BIRCH_WOOD_STAIRS)
            .add(DnDWoodBlocks.JUNGLE_WOOD_STAIRS)
            .add(DnDWoodBlocks.ACACIA_WOOD_STAIRS)
            .add(DnDWoodBlocks.DARK_OAK_WOOD_STAIRS)
            .add(DnDWoodBlocks.MANGROVE_WOOD_STAIRS)
            .add(DnDWoodBlocks.CHERRY_WOOD_STAIRS)
            .add(DnDWoodBlocks.CASCADE_WOOD_STAIRS)
        getOrCreateTagBuilder(DnDBlockTags.WOOD_SLABS)
            .forceAddTag(DnDBlockTags.WOOD_SLABS_THAT_BURN)
            .add(DnDWoodBlocks.CRIMSON_HYPHAE_SLAB)
            .add(DnDWoodBlocks.WARPED_HYPHAE_SLAB)
        getOrCreateTagBuilder(DnDBlockTags.WOOD_SLABS_THAT_BURN)
            .add(DnDWoodBlocks.OAK_WOOD_SLAB)
            .add(DnDWoodBlocks.SPRUCE_WOOD_SLAB)
            .add(DnDWoodBlocks.BIRCH_WOOD_SLAB)
            .add(DnDWoodBlocks.JUNGLE_WOOD_SLAB)
            .add(DnDWoodBlocks.ACACIA_WOOD_SLAB)
            .add(DnDWoodBlocks.DARK_OAK_WOOD_SLAB)
            .add(DnDWoodBlocks.MANGROVE_WOOD_SLAB)
            .add(DnDWoodBlocks.CHERRY_WOOD_SLAB)
            .add(DnDWoodBlocks.CASCADE_WOOD_SLAB)
        getOrCreateTagBuilder(DnDBlockTags.WOODEN_WALLS)
            .forceAddTag(DnDBlockTags.WOODEN_WALLS_THAT_BURN)
            .add(DnDWoodBlocks.CRIMSON_HYPHAE_WALL)
            .add(DnDWoodBlocks.WARPED_HYPHAE_WALL)
        getOrCreateTagBuilder(DnDBlockTags.WOODEN_WALLS_THAT_BURN)
            .add(DnDWoodBlocks.OAK_WOOD_WALL)
            .add(DnDWoodBlocks.SPRUCE_WOOD_WALL)
            .add(DnDWoodBlocks.BIRCH_WOOD_WALL)
            .add(DnDWoodBlocks.JUNGLE_WOOD_WALL)
            .add(DnDWoodBlocks.ACACIA_WOOD_WALL)
            .add(DnDWoodBlocks.DARK_OAK_WOOD_WALL)
            .add(DnDWoodBlocks.MANGROVE_WOOD_WALL)
            .add(DnDWoodBlocks.CHERRY_WOOD_WALL)
            .add(DnDWoodBlocks.CASCADE_WOOD_WALL)
        getOrCreateTagBuilder(DnDBlockTags.LOG_PILES)
            .forceAddTag(DnDBlockTags.LOG_PILES_THAT_BURN)
            .add(DnDWoodBlocks.CRIMSON_STEM_PILE)
            .add(DnDWoodBlocks.WARPED_STEM_PILE)
        getOrCreateTagBuilder(DnDBlockTags.LOG_PILES_THAT_BURN)
            .add(DnDWoodBlocks.OAK_LOG_PILE)
            .add(DnDWoodBlocks.SPRUCE_LOG_PILE)
            .add(DnDWoodBlocks.BIRCH_LOG_PILE)
            .add(DnDWoodBlocks.JUNGLE_LOG_PILE)
            .add(DnDWoodBlocks.ACACIA_LOG_PILE)
            .add(DnDWoodBlocks.DARK_OAK_LOG_PILE)
            .add(DnDWoodBlocks.MANGROVE_LOG_PILE)
            .add(DnDWoodBlocks.CHERRY_LOG_PILE)
            .add(DnDWoodBlocks.CASCADE_LOG_PILE)
        getOrCreateTagBuilder(DnDBlockTags.LEAF_PILES)
            .add(DnDWoodBlocks.OAK_LEAF_PILE)
            .add(DnDWoodBlocks.SPRUCE_LEAF_PILE)
            .add(DnDWoodBlocks.BIRCH_LEAF_PILE)
            .add(DnDWoodBlocks.JUNGLE_LEAF_PILE)
            .add(DnDWoodBlocks.ACACIA_LEAF_PILE)
            .add(DnDWoodBlocks.DARK_OAK_LEAF_PILE)
            .add(DnDWoodBlocks.MANGROVE_LEAF_PILE)
            .add(DnDWoodBlocks.CHERRY_LEAF_PILE)
            .add(DnDWoodBlocks.AZALEA_LEAF_PILE)
            .add(DnDWoodBlocks.FLOWERING_AZALEA_LEAF_PILE)
            .add(DnDWoodBlocks.CASCADE_LEAF_PILE)
            .add(DnDWoodBlocks.GOLDEN_BIRCH_LEAF_PILE)
        getOrCreateTagBuilder(DnDBlockTags.LEAF_PILES_PLACE_ON)
            .forceAddTag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN)
            .add(Blocks.COBBLESTONE)
            .add(Blocks.MOSSY_COBBLESTONE)
            .add(DnDStoneBlocks.OVERGROWN_COBBLESTONE)
            .forceAddTag(BlockTags.STONE_BRICKS)
            .forceAddTag(BlockTags.LOGS)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.CARVED_PUMPKINS)
            .forceAddTag(DnDBlockTags.GLOWING_PUMPKINS)
            .add(Blocks.MELON)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DnDBlockTags.FLOWERBEDS)
            .addAll(DnDBlockLists.flowerbedBlocks)
        getOrCreateTagBuilder(DnDBlockTags.VIVIONBEDS)
            .addAll(DnDBlockLists.vivionbedBlocks)
        getOrCreateTagBuilder(DnDBlockTags.VIVIONBED_PLACEABLE)
            .addOptionalTag(id("nullium", "support/nylium_plants"))
            .forceAddTag(BlockTags.DIRT)
            .forceAddTag(BlockTags.NYLIUM)
            .add(Blocks.FARMLAND)
            .add(Blocks.SOUL_SOIL)
        getOrCreateTagBuilder(DnDBlockTags.ICE_BLOCK_TRANSLUCENT)
            .add(Blocks.ICE)
            .add(DnDBlocks.ICE_STAIRS)
            .add(DnDBlocks.ICE_SLAB)
            .add(DnDBlocks.ICE_WALL)
        getOrCreateTagBuilder(DnDBlockTags.BIG_CANDLES)
            .forceAddTag(DnDBlockTags.BIG_SOUL_CANDLES)
            .add(DnDBigBlocks.BIG_CANDLE)
            .add(DnDBigBlocks.BIG_WHITE_CANDLE)
            .add(DnDBigBlocks.BIG_LIGHT_GRAY_CANDLE)
            .add(DnDBigBlocks.BIG_GRAY_CANDLE)
            .add(DnDBigBlocks.BIG_BLACK_CANDLE)
            .add(DnDBigBlocks.BIG_BROWN_CANDLE)
            .add(DnDBigBlocks.BIG_RED_CANDLE)
            .add(DnDBigBlocks.BIG_ORANGE_CANDLE)
            .add(DnDBigBlocks.BIG_YELLOW_CANDLE)
            .add(DnDBigBlocks.BIG_LIME_CANDLE)
            .add(DnDBigBlocks.BIG_GREEN_CANDLE)
            .add(DnDBigBlocks.BIG_CYAN_CANDLE)
            .add(DnDBigBlocks.BIG_LIGHT_BLUE_CANDLE)
            .add(DnDBigBlocks.BIG_BLUE_CANDLE)
            .add(DnDBigBlocks.BIG_PURPLE_CANDLE)
            .add(DnDBigBlocks.BIG_MAGENTA_CANDLE)
            .add(DnDBigBlocks.BIG_PINK_CANDLE)
        getOrCreateTagBuilder(DnDBlockTags.SOUL_CANDLES)
            .add(DnDBigBlocks.SOUL_CANDLE)
            .add(DnDBigBlocks.WHITE_SOUL_CANDLE)
            .add(DnDBigBlocks.LIGHT_GRAY_SOUL_CANDLE)
            .add(DnDBigBlocks.GRAY_SOUL_CANDLE)
            .add(DnDBigBlocks.BLACK_SOUL_CANDLE)
            .add(DnDBigBlocks.BROWN_SOUL_CANDLE)
            .add(DnDBigBlocks.RED_SOUL_CANDLE)
            .add(DnDBigBlocks.ORANGE_SOUL_CANDLE)
            .add(DnDBigBlocks.YELLOW_SOUL_CANDLE)
            .add(DnDBigBlocks.LIME_SOUL_CANDLE)
            .add(DnDBigBlocks.GREEN_SOUL_CANDLE)
            .add(DnDBigBlocks.CYAN_SOUL_CANDLE)
            .add(DnDBigBlocks.LIGHT_BLUE_SOUL_CANDLE)
            .add(DnDBigBlocks.BLUE_SOUL_CANDLE)
            .add(DnDBigBlocks.PURPLE_SOUL_CANDLE)
            .add(DnDBigBlocks.MAGENTA_SOUL_CANDLE)
            .add(DnDBigBlocks.PINK_SOUL_CANDLE)
        getOrCreateTagBuilder(DnDBlockTags.BIG_SOUL_CANDLES)
            .add(DnDBigBlocks.BIG_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_WHITE_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_GRAY_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_BLACK_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_BROWN_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_RED_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_ORANGE_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_YELLOW_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_LIME_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_GREEN_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_CYAN_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_BLUE_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_PURPLE_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_MAGENTA_SOUL_CANDLE)
            .add(DnDBigBlocks.BIG_PINK_SOUL_CANDLE)

        getOrCreateTagBuilder(DnDBlockTags.CANDELABRAS)
            .addAll(DnDBlockLists.candelabras)
            .addAll(DnDBlockLists.soulCandelabras)

        getOrCreateTagBuilder(DnDBlockTags.GRAVESTONES)
            .forceAddTag(DnDBlockTags.SMALL_GRAVESTONES)
            .add(DnDStoneBlocks.GRAVESTONE)
            .add(DnDStoneBlocks.DEEPSLATE_GRAVESTONE)
            .add(DnDStoneBlocks.TUFF_GRAVESTONE)
            .add(DnDStoneBlocks.BLACKSTONE_GRAVESTONE)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_GRAVESTONES)
            .add(DnDStoneBlocks.SMALL_GRAVESTONE)
            .add(DnDStoneBlocks.SMALL_DEEPSLATE_GRAVESTONE)
            .add(DnDStoneBlocks.SMALL_TUFF_GRAVESTONE)
            .add(DnDStoneBlocks.SMALL_BLACKSTONE_GRAVESTONE)
        getOrCreateTagBuilder(DnDBlockTags.HAUNTED_GRAVESTONES)
            .forceAddTag(DnDBlockTags.SMALL_HAUNTED_GRAVESTONES)
            .add(DnDStoneBlocks.HAUNTED_GRAVESTONE)
            .add(DnDStoneBlocks.HAUNTED_DEEPSLATE_GRAVESTONE)
            .add(DnDStoneBlocks.HAUNTED_TUFF_GRAVESTONE)
            .add(DnDStoneBlocks.HAUNTED_BLACKSTONE_GRAVESTONE)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_HAUNTED_GRAVESTONES)
            .add(DnDStoneBlocks.SMALL_HAUNTED_GRAVESTONE)
            .add(DnDStoneBlocks.SMALL_HAUNTED_DEEPSLATE_GRAVESTONE)
            .add(DnDStoneBlocks.SMALL_HAUNTED_TUFF_GRAVESTONE)
            .add(DnDStoneBlocks.SMALL_HAUNTED_BLACKSTONE_GRAVESTONE)
        getOrCreateTagBuilder(DnDBlockTags.HEADSTONES)
            .add(DnDStoneBlocks.HEADSTONE)

        getOrCreateTagBuilder(DnDBlockTags.NETHER_BRICKS)
            .forceAddTag(DnDBlockTags.CRACKED_NETHER_BRICKS)
            .forceAddTag(DnDBlockTags.POLISHED_NETHER_BRICKS)
            .add(Blocks.NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.MIXED_NETHER_BRICKS)
            .add(Blocks.RED_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.BLUE_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.GRAY_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS)
        getOrCreateTagBuilder(DnDBlockTags.CRACKED_NETHER_BRICKS)
            .add(Blocks.CRACKED_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.CRACKED_MIXED_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.CRACKED_BLUE_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.CRACKED_GRAY_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS)
        getOrCreateTagBuilder(DnDBlockTags.POLISHED_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.POLISHED_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICKS)
            .add(DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICKS)
        getOrCreateTagBuilder(DnDBlockTags.WARPED_NETHER_WART_PLACEABLE)
            .addOptionalTag(id("nullium", "support/nether_wart"))
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


        getOrCreateTagBuilder(DnDBlockTags.CORN_STORAGE)
            .add(DnDFloraBlocks.CORN_BLOCK)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS)
            .add(Blocks.PUMPKIN)
            .add(DnDFloraBlocks.LANTERN_PUMPKIN)
            .add(DnDFloraBlocks.MOSSKIN_PUMPKIN)
            .add(DnDFloraBlocks.PALE_PUMPKIN)
            .add(DnDFloraBlocks.GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.CARVED_PUMPKINS)
            .add(Blocks.CARVED_PUMPKIN)
            .add(DnDFloraBlocks.CARVED_LANTERN_PUMPKIN)
            .add(DnDFloraBlocks.CARVED_MOSSKIN_PUMPKIN)
            .add(DnDFloraBlocks.CARVED_PALE_PUMPKIN)
            .add(DnDFloraBlocks.CARVED_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.GLOWING_PUMPKINS)
            .add(Blocks.JACK_O_LANTERN)
            .add(DnDFloraBlocks.GLOWING_LANTERN_PUMPKIN)
            .add(DnDFloraBlocks.GLOWING_MOSSKIN_PUMPKIN)
            .add(DnDFloraBlocks.GLOWING_PALE_PUMPKIN)
            .add(DnDFloraBlocks.GLOWING_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_PUMPKINS)
            .add(DnDFloraBlocks.SMALL_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_LANTERN_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_MOSSKIN_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_PALE_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_CARVED_PUMPKINS)
            .add(DnDFloraBlocks.SMALL_CARVED_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_CARVED_LANTERN_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_CARVED_PALE_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_CARVED_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_GLOWING_PUMPKINS)
            .add(DnDFloraBlocks.SMALL_GLOWING_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_GLOWING_LANTERN_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_GLOWING_PALE_PUMPKIN)
            .add(DnDFloraBlocks.SMALL_GLOWING_GLOOM_PUMPKIN)

        getOrCreateTagBuilder(DnDBlockTags.BLOCKS_CANNOT_CONNECT_TO)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.CARVED_PUMPKINS)
            .forceAddTag(DnDBlockTags.GLOWING_PUMPKINS)
    }

    private fun vanillaTags() {
        vanillaBlockTypesTags()
        vanillaBlockTypeShapeTags()
        vanillaOverlayTags()

        getOrCreateTagBuilder(BlockTags.FEATURES_CANNOT_REPLACE)
            .add(DnDBlocks.CHEST_O_SOULS)
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .forceAddTag(DnDBlockTags.FLOWERBEDS)
            .forceAddTag(DnDBlockTags.VIVIONBEDS)
            .forceAddTag(DnDBlockTags.SMALL_PUMPKINS)
            .forceAddTag(DnDBlockTags.SMALL_CARVED_PUMPKINS)
            .forceAddTag(DnDBlockTags.SMALL_GLOWING_PUMPKINS)
            .add(DnDFloraBlocks.ROOT_BLOCK)

        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES)
            .add(DnDFloraBlocks.MOONBERRY_VINE)
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .add(DnDFloraBlocks.CORN_CROP)
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
            .forceAddTag(DnDBlockTags.FLOWERBEDS)
            .forceAddTag(DnDBlockTags.VIVIONBEDS)
        getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
            .add(DnDOverlayBlocks.ROCKY_PODZOL, DnDOverlayBlocks.SLATED_PODZOL, DnDOverlayBlocks.BLACKSTONE_PODZOL)
            .add(
                DnDOverlayBlocks.ROCKY_MYCELIUM,
                DnDOverlayBlocks.SLATED_MYCELIUM,
                DnDOverlayBlocks.BLACKSTONE_MYCELIUM
            )
            .forceAddTag(BlockTags.LOGS)
            .forceAddTag(DnDBlockTags.HOLLOW_LOGS)
        getOrCreateTagBuilder(BlockTags.GUARDED_BY_PIGLINS)
            .forceAddTag(DnDBlockTags.GRAVESTONES)
        getOrCreateTagBuilder(BlockTags.PIGLIN_REPELLENTS)
            .forceAddTag(DnDBlockTags.HAUNTED_GRAVESTONES)
            .add(DnDBlocks.CHEST_O_SOULS)
            .add(DnDBlocks.POT_O_SCREAMS)
        getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.CARVED_PUMPKINS)


        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .addAll(DnDBlocks.SWORDABLE)
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .addAll(DnDBlocks.PICKAXABLE)
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .forceAddTag(DnDBlockTags.LOG_PILES)
            .addAll(DnDBlocks.AXABLE)
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
            .addAll(DnDBlocks.SHOVELABLE)
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .addAll(DnDBlocks.HOEABLE)


        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(Blocks.ICE, Blocks.SNOW)
    }

    private fun vanillaBlockTypesTags() {
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .forceAddTag(DnDBlockTags.CASCADE_LOGS)
        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(DnDWoodBlocks.CASCADE_PLANKS)
        getOrCreateTagBuilder(BlockTags.CROPS)
            .add(DnDFloraBlocks.CORN_CROP)
            .add(DnDFloraBlocks.GOLDEN_BEETROOTS)
            .add(DnDFloraBlocks.MOONBERRY_VINELET)

        getOrCreateTagBuilder(BlockTags.FLOWERS)
            .add(DnDWoodBlocks.CHERRY_LEAF_PILE)
            .forceAddTag(DnDBlockTags.FLOWERBEDS)
            .forceAddTag(DnDBlockTags.VIVIONBEDS)
        getOrCreateTagBuilder(BlockTags.LEAVES)
            .add(DnDWoodBlocks.CASCADE_LEAVES)
            .add(DnDWoodBlocks.GOLDEN_BIRCH_LEAVES)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.SAPLINGS)
            .add(DnDWoodBlocks.CASCADE_SAPLING)
            .add(DnDWoodBlocks.GOLDEN_BIRCH_SAPLING)
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
            .add(DnDWoodBlocks.POTTED_CASCADE_SAPLING)
            .add(DnDWoodBlocks.POTTED_GOLDEN_BIRCH_SAPLING)

        getOrCreateTagBuilder(BlockTags.CANDLES)
            .forceAddTag(DnDBlockTags.BIG_CANDLES)
            .forceAddTag(DnDBlockTags.SOUL_CANDLES)

        getOrCreateTagBuilder(BlockTags.STONE_BRICKS)
            .add(DnDStoneBlocks.OVERGROWN_STONE_BRICKS)
    }

    private fun vanillaBlockTypeShapeTags() {
        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(DnDStoneBlocks.POLISHED_STONE_STAIRS)
            .add(DnDStoneBlocks.MOSSY_POLISHED_STONE_STAIRS)
            .add(DnDStoneBlocks.OVERGROWN_POLISHED_STONE_STAIRS)
            .add(DnDBlocks.ICE_STAIRS)
            .add(DnDBlocks.PACKED_ICE_STAIRS)
            .add(DnDBlocks.BLUE_ICE_STAIRS)
            .add(DnDNetherBrickBlocks.NETHERRACK_STAIRS)
            .add(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_STAIRS)
            .add(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_STAIRS)
            .add(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_STAIRS)
            .add(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS)
            .add(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS)
            .add(DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_STAIRS)
            .add(DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_STAIRS)
            .add(DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS)
            .add(DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS)
            .add(DnDStoneBlocks.OVERGROWN_COBBLESTONE_STAIRS)
            .add(DnDStoneBlocks.OVERGROWN_STONE_BRICK_STAIRS)
        getOrCreateTagBuilder(BlockTags.SLABS)
            .add(DnDStoneBlocks.POLISHED_STONE_SLAB)
            .add(DnDStoneBlocks.MOSSY_POLISHED_STONE_SLAB)
            .add(DnDStoneBlocks.OVERGROWN_POLISHED_STONE_SLAB)
            .add(DnDBlocks.ICE_SLAB)
            .add(DnDBlocks.PACKED_ICE_SLAB)
            .add(DnDBlocks.BLUE_ICE_SLAB)
            .add(DnDNetherBrickBlocks.NETHERRACK_SLAB)
            .add(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_SLAB)
            .add(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_SLAB)
            .add(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_SLAB)
            .add(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_SLAB)
            .add(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_SLAB)
            .add(DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_SLAB)
            .add(DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_SLAB)
            .add(DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB)
            .add(DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB)
            .add(DnDStoneBlocks.OVERGROWN_COBBLESTONE_SLAB)
            .add(DnDStoneBlocks.OVERGROWN_STONE_BRICK_SLAB)
        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(DnDStoneBlocks.POLISHED_STONE_WALL)
            .add(DnDStoneBlocks.MOSSY_POLISHED_STONE_WALL)
            .add(DnDStoneBlocks.OVERGROWN_POLISHED_STONE_WALL)
            .add(DnDBlocks.ICE_WALL)
            .add(DnDBlocks.PACKED_ICE_WALL)
            .add(DnDBlocks.BLUE_ICE_WALL)
            .add(DnDStoneBlocks.OVERGROWN_COBBLESTONE_WALL)
            .add(DnDStoneBlocks.OVERGROWN_STONE_BRICK_WALL)
            .add(DnDNetherBrickBlocks.NETHERRACK_WALL)
            .add(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_WALL)
            .add(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_WALL)
            .add(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_WALL)
            .add(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_WALL)
            .add(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_WALL)
            .add(DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_WALL)
            .add(DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_WALL)
            .add(DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_WALL)
            .add(DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_WALL)
        getOrCreateTagBuilder(BlockTags.FENCES)
            .add(DnDNetherBrickBlocks.RED_NETHER_BRICK_FENCE)
            .add(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_FENCE)
            .add(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_FENCE)
            .add(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_FENCE)
            .add(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_FENCE)
            .add(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_FENCE)
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
            .add(DnDWoodBlocks.CASCADE_STAIRS)
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
            .add(DnDWoodBlocks.CASCADE_SLAB)
            .add(DnDWoodBlocks.OAK_WOOD_SLAB)
            .add(DnDWoodBlocks.SPRUCE_WOOD_SLAB)
            .add(DnDWoodBlocks.BIRCH_WOOD_SLAB)
            .add(DnDWoodBlocks.JUNGLE_WOOD_SLAB)
            .add(DnDWoodBlocks.ACACIA_WOOD_SLAB)
            .add(DnDWoodBlocks.DARK_OAK_WOOD_SLAB)
            .add(DnDWoodBlocks.MANGROVE_WOOD_SLAB)
            .add(DnDWoodBlocks.CHERRY_WOOD_SLAB)
            .add(DnDWoodBlocks.CASCADE_WOOD_SLAB)
            .add(DnDWoodBlocks.CRIMSON_HYPHAE_SLAB)
            .add(DnDWoodBlocks.WARPED_HYPHAE_SLAB)
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add(DnDWoodBlocks.CASCADE_DOOR)
            .add(DnDWoodBlocks.BLUE_DOOR)
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add(DnDWoodBlocks.CASCADE_TRAPDOOR)
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(DnDWoodBlocks.CASCADE_FENCE)
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(DnDWoodBlocks.CASCADE_FENCE_GATE)
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
            .add(DnDWoodBlocks.CASCADE_PRESSURE_PLATE)
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
            .add(DnDWoodBlocks.CASCADE_BUTTON)
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
            .add(DnDWoodBlocks.CASCADE_SIGN)
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
            .add(DnDWoodBlocks.CASCADE_WALL_SIGN)
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
            .add(DnDWoodBlocks.CASCADE_HANGING_SIGN)
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
            .add(DnDWoodBlocks.CASCADE_WALL_HANGING_SIGN)
    }

    private fun vanillaOverlayTags() {
        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(DnDOverlayBlocks.ROCKY_GRAVEL, DnDOverlayBlocks.SLATED_GRAVEL, DnDOverlayBlocks.BLACKSTONE_GRAVEL)
        getOrCreateTagBuilder(BlockTags.NETHER_CARVER_REPLACEABLES)
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SAND,
                DnDOverlayBlocks.SLATED_SOUL_SAND,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SAND
            )
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SOIL,
                DnDOverlayBlocks.SLATED_SOUL_SOIL,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL
            )
        getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
            .add(DnDOverlayBlocks.ROCKY_GRAVEL, DnDOverlayBlocks.SLATED_GRAVEL, DnDOverlayBlocks.BLACKSTONE_GRAVEL)
            .add(DnDOverlayBlocks.ROCKY_SAND, DnDOverlayBlocks.SLATED_SAND, DnDOverlayBlocks.BLACKSTONE_SAND)
            .add(
                DnDOverlayBlocks.ROCKY_RED_SAND,
                DnDOverlayBlocks.SLATED_RED_SAND,
                DnDOverlayBlocks.BLACKSTONE_RED_SAND
            )
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SAND,
                DnDOverlayBlocks.SLATED_SOUL_SAND,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SAND
            )
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SOIL,
                DnDOverlayBlocks.SLATED_SOUL_SOIL,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL
            )
        getOrCreateTagBuilder(BlockTags.DIRT)
            .add(DnDOverlayBlocks.ROCKY_DIRT, DnDOverlayBlocks.SLATED_DIRT, DnDOverlayBlocks.BLACKSTONE_DIRT)
            .add(DnDOverlayBlocks.ROCKY_GRASS, DnDOverlayBlocks.SLATED_GRASS, DnDOverlayBlocks.BLACKSTONE_GRASS)
            .add(DnDOverlayBlocks.ROCKY_PODZOL, DnDOverlayBlocks.SLATED_PODZOL, DnDOverlayBlocks.BLACKSTONE_PODZOL)
            .add(
                DnDOverlayBlocks.ROCKY_MYCELIUM,
                DnDOverlayBlocks.SLATED_MYCELIUM,
                DnDOverlayBlocks.BLACKSTONE_MYCELIUM
            )
            .add(
                DnDOverlayBlocks.ROCKY_COARSE_DIRT,
                DnDOverlayBlocks.SLATED_COARSE_DIRT,
                DnDOverlayBlocks.BLACKSTONE_COARSE_DIRT
            )
            .add(DnDOverlayBlocks.ROCKY_MUD, DnDOverlayBlocks.SLATED_MUD, DnDOverlayBlocks.BLACKSTONE_MUD)
        getOrCreateTagBuilder(BlockTags.SAND)
            .add(DnDOverlayBlocks.ROCKY_SAND, DnDOverlayBlocks.SLATED_SAND, DnDOverlayBlocks.BLACKSTONE_SAND)
            .add(
                DnDOverlayBlocks.ROCKY_RED_SAND,
                DnDOverlayBlocks.SLATED_RED_SAND,
                DnDOverlayBlocks.BLACKSTONE_RED_SAND
            )
        getOrCreateTagBuilder(BlockTags.SNOW)
            .add(DnDOverlayBlocks.ROCKY_SNOW, DnDOverlayBlocks.SLATED_SNOW, DnDOverlayBlocks.BLACKSTONE_SNOW)
        getOrCreateTagBuilder(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON)
            .add(DnDFloraBlocks.CORN_SYRUP_BLOCK)
            .add(DnDOverlayBlocks.ROCKY_MUD, DnDOverlayBlocks.SLATED_MUD, DnDOverlayBlocks.BLACKSTONE_MUD)
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SAND,
                DnDOverlayBlocks.SLATED_SOUL_SAND,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SAND
            )
        getOrCreateTagBuilder(BlockTags.WITHER_SUMMON_BASE_BLOCKS)
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SAND,
                DnDOverlayBlocks.SLATED_SOUL_SAND,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SAND
            )
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SOIL,
                DnDOverlayBlocks.SLATED_SOUL_SOIL,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL
            )
        getOrCreateTagBuilder(BlockTags.SOUL_SPEED_BLOCKS)
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SAND,
                DnDOverlayBlocks.SLATED_SOUL_SAND,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SAND
            )
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SOIL,
                DnDOverlayBlocks.SLATED_SOUL_SOIL,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL
            )
        getOrCreateTagBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS)
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SAND,
                DnDOverlayBlocks.SLATED_SOUL_SAND,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SAND
            )
            .add(
                DnDOverlayBlocks.ROCKY_SOUL_SOIL,
                DnDOverlayBlocks.SLATED_SOUL_SOIL,
                DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL
            )
        getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON)
            .add(DnDOverlayBlocks.ROCKY_GRAVEL, DnDOverlayBlocks.SLATED_GRAVEL, DnDOverlayBlocks.BLACKSTONE_GRAVEL)
        getOrCreateTagBuilder(BlockTags.VALID_SPAWN)
            .add(DnDOverlayBlocks.ROCKY_GRASS, DnDOverlayBlocks.SLATED_GRASS, DnDOverlayBlocks.BLACKSTONE_GRASS)
            .add(DnDOverlayBlocks.ROCKY_PODZOL, DnDOverlayBlocks.SLATED_PODZOL, DnDOverlayBlocks.BLACKSTONE_PODZOL)
        getOrCreateTagBuilder(BlockTags.ANIMALS_SPAWNABLE_ON)
            .add(DnDOverlayBlocks.ROCKY_GRASS, DnDOverlayBlocks.SLATED_GRASS, DnDOverlayBlocks.BLACKSTONE_GRASS)
        getOrCreateTagBuilder(BlockTags.FOXES_SPAWNABLE_ON)
            .add(DnDOverlayBlocks.ROCKY_GRASS, DnDOverlayBlocks.SLATED_GRASS, DnDOverlayBlocks.BLACKSTONE_GRASS)
            .add(DnDOverlayBlocks.ROCKY_PODZOL, DnDOverlayBlocks.SLATED_PODZOL, DnDOverlayBlocks.BLACKSTONE_PODZOL)
            .add(
                DnDOverlayBlocks.ROCKY_COARSE_DIRT,
                DnDOverlayBlocks.SLATED_COARSE_DIRT,
                DnDOverlayBlocks.BLACKSTONE_COARSE_DIRT
            )
            .add(DnDOverlayBlocks.ROCKY_SNOW, DnDOverlayBlocks.SLATED_SNOW, DnDOverlayBlocks.BLACKSTONE_SNOW)
        getOrCreateTagBuilder(BlockTags.MOOSHROOMS_SPAWNABLE_ON)
            .add(
                DnDOverlayBlocks.ROCKY_MYCELIUM,
                DnDOverlayBlocks.SLATED_MYCELIUM,
                DnDOverlayBlocks.BLACKSTONE_MYCELIUM
            )
        getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK)
            .add(DnDOverlayBlocks.ROCKY_DIRT, DnDOverlayBlocks.SLATED_DIRT, DnDOverlayBlocks.BLACKSTONE_DIRT)
            .add(DnDOverlayBlocks.ROCKY_GRASS, DnDOverlayBlocks.SLATED_GRASS, DnDOverlayBlocks.BLACKSTONE_GRASS)
            .add(DnDOverlayBlocks.ROCKY_PODZOL, DnDOverlayBlocks.SLATED_PODZOL, DnDOverlayBlocks.BLACKSTONE_PODZOL)
            .add(
                DnDOverlayBlocks.ROCKY_COARSE_DIRT,
                DnDOverlayBlocks.SLATED_COARSE_DIRT,
                DnDOverlayBlocks.BLACKSTONE_COARSE_DIRT
            )
        getOrCreateTagBuilder(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
            .add(DnDOverlayBlocks.ROCKY_MUD, DnDOverlayBlocks.SLATED_MUD, DnDOverlayBlocks.BLACKSTONE_MUD)
    }

    private fun conventionTags() {
        getOrCreateTagBuilder(ConventionalBlockTags.COBBLESTONES)
            .add(DnDStoneBlocks.OVERGROWN_COBBLESTONE)
        getOrCreateTagBuilder(ConventionalBlockTags.CHAINS)
            .add(DnDBigBlocks.BIG_CHAIN)
        getOrCreateTagBuilder(ConventionalBlockTags.WOODEN_CHESTS)
            .add(DnDBlocks.CHEST_O_SOULS)
    }

//    private fun FabricTagProvider.addOverlay() {
//        listOf(
//            DnDOverlayBlocks.ROCKY_COARSE_DIRT,
//            DnDOverlayBlocks.SLATED_COARSE_DIRT,
//            DnDOverlayBlocks.BLACKSTONE_COARSE_DIRT
//        ).forEach(this::add)
//        return this
//    }
}
