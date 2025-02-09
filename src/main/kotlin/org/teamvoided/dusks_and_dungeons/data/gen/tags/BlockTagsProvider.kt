package org.teamvoided.dusks_and_dungeons.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.ICE_SET
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.blocks.*
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.voidlib.devin.extensions.tag.add
import org.teamvoided.voidlib.devin.extensions.tag.createSetTags
import java.util.concurrent.CompletableFuture

@Suppress("LongMethod")
class BlockTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider) {
        SETS.forEach { it.createSetTags(::getOrCreateTagBuilder) }
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
            .add(FLAMMABLE_PLANKS)
        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_LOGS)
            .forceAddTag(DnDBlockTags.CASCADE_LOGS)
            .forceAddTag(DnDBlockTags.LOG_PILES_THAT_BURN)
            .forceAddTag(DnDBlockTags.HOLLOW_LOGS_THAT_BURN)
            .add(FLAMMABLE_LOGS)
        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_LEAVES)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .add(FLAMMABLE_LEAVES)
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
            .addOptionalTag(id("nullium", "support/nylium_plants"))
            .forceAddTag(BlockTags.DIRT)
            .forceAddTag(BlockTags.NYLIUM)
            .add(Blocks.FARMLAND)
            .add(Blocks.SOUL_SOIL)
        getOrCreateTagBuilder(DnDBlockTags.ICE_BLOCK_TRANSLUCENT)
            .add(Blocks.ICE)
            .add(ICE_SET.collect())
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
        getOrCreateTagBuilder(DnDBlockTags.HAUNTED_GRAVESTONES)
            .forceAddTag(DnDBlockTags.SMALL_HAUNTED_GRAVESTONES)
            .add(DnDBlocks.HAUNTED_GRAVESTONE)
            .add(DnDBlocks.HAUNTED_DEEPSLATE_GRAVESTONE)
            .add(DnDBlocks.HAUNTED_TUFF_GRAVESTONE)
            .add(DnDBlocks.HAUNTED_BLACKSTONE_GRAVESTONE)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_HAUNTED_GRAVESTONES)
            .add(DnDBlocks.SMALL_HAUNTED_GRAVESTONE)
            .add(DnDBlocks.SMALL_HAUNTED_DEEPSLATE_GRAVESTONE)
            .add(DnDBlocks.SMALL_HAUNTED_TUFF_GRAVESTONE)
            .add(DnDBlocks.SMALL_HAUNTED_BLACKSTONE_GRAVESTONE)
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
            .add(SWORDABLE)
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(PICKAXABLE)
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .forceAddTag(DnDBlockTags.LOG_PILES)
            .add(AXABLE)
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
            .add(SHOVELABLE)
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .add(HOEABLE)


        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(Blocks.ICE, Blocks.SNOW)
    }

    private fun vanillaBlockTypesTags() {
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .forceAddTag(DnDBlockTags.CASCADE_LOGS)
        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(DnDWoodBlocks.CASCADE_PLANKS)
        getOrCreateTagBuilder(BlockTags.CROPS)
            .add(DnDBlocks.CORN_CROP)
            .add(DnDBlocks.GOLDEN_BEETROOTS)
            .add(DnDBlocks.MOONBERRY_VINELET)
            .forceAddTag(DnDBlockTags.PUMPKIN_STEMS)

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
            .forceAddTag(DnDBlockTags.SOUL_CANDLES)
            .forceAddTag(DnDBlockTags.BIG_CANDLES)
            .forceAddTag(DnDBlockTags.CANDELABRAS)

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
            .add(DnDBlocks.CORN_SYRUP_BLOCK)
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
        getOrCreateTagBuilder(ConventionalBlockTags.COBBLESTONES).add(DnDBlocks.OVERGROWN_COBBLESTONE.parent)
        getOrCreateTagBuilder(ConventionalBlockTags.CHAINS).add(DnDBlocks.BIG_CHAIN)
        getOrCreateTagBuilder(ConventionalBlockTags.WOODEN_CHESTS).add(DnDBlocks.CHEST_O_SOULS)
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
