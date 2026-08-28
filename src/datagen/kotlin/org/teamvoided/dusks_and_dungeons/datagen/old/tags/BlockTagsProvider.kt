package org.teamvoided.dusks_and_dungeons.datagen.old.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.data.tags.c.CBlockTags
import org.teamvoided.dusks_and_dungeons.datagen.old.util.createWoodTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.COLOR_CONSORTIUM
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.ICE_SET
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.block.*
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

        getOrCreateTagBuilder(DnDBlockTags.THROWN_BRICK_BREAK)
            .forceAddTag(ConventionalBlockTags.GLASS_BLOCKS)
            .forceAddTag(ConventionalBlockTags.GLASS_PANES)
            .forceAddTag(BlockTags.FLOWER_POTS)
            .add(
                Blocks.DECORATED_POT,
                Blocks.DIAMOND_BLOCK
            )
            .addOptionalTag(id("sable", "fragile"))

        getOrCreateTagBuilder(BlockTags.SAND)
            .add(DnDBlocks.SUSPICIOUS_RED_SAND)

        getOrCreateTagBuilder(DnDBlockTags.EMPTY)

        getOrCreateTagBuilder(DnDBlockTags.SCULK_SPREAD_SEARCH)
            .add(Blocks.SCULK_SENSOR)
            .add(Blocks.SCULK_SHRIEKER)

        getOrCreateTagBuilder(DnDBlockTags.SHRIEKER_SEARCH_BYPASSES)
            .forceAddTag(BlockTags.REPLACEABLE)
            .add(Blocks.SCULK_VEIN)
            .add(Blocks.SCULK_SENSOR)
            .add(Blocks.SCULK_SHRIEKER)

        // TODO(1.0) deal with VV stuff

        // TODO(1.0) add a mixin for this tag to only apply if the top has a full side
        getOrCreateTagBuilder(BlockTags.SNOW)
            .add(
                DnDBlocks.SNOW_SET.stairs,
                DnDBlocks.SNOW_SET.slab,
                DnDBlocks.SNOW_SET.wall,
            )
        getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
            .add(
                DnDBlocks.END_STONE_SET.stairs,
                DnDBlocks.END_STONE_SET.slab,
                DnDBlocks.END_STONE_SET.wall,
            )

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(
                DnDBlocks.OBSIDIAN_SET.stairs,
                DnDBlocks.OBSIDIAN_SET.slab,
                DnDBlocks.OBSIDIAN_SET.wall,
                DnDBlocks.CRYING_OBSIDIAN_SET.stairs,
                DnDBlocks.CRYING_OBSIDIAN_SET.slab,
                DnDBlocks.CRYING_OBSIDIAN_SET.wall,
            )

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(
                DnDBlocks.SMOOTH_STONE_STAIR,
                DnDBlocks.CUT_SANDSTONE_STAIR,
                DnDBlocks.CUT_RED_SANDSTONE_STAIR,
            )
        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(
                DnDBlocks.STONE_WALL,
                DnDBlocks.SMOOTH_STONE_WALL,
                DnDBlocks.CUT_SANDSTONE_WALL,
                DnDBlocks.CUT_RED_SANDSTONE_WALL,
                DnDBlocks.STONE_WALL,
                DnDBlocks.POLISHED_GRANITE_WALL,
                DnDBlocks.POLISHED_DIORITE_WALL,
                DnDBlocks.POLISHED_ANDESITE_WALL,
                DnDBlocks.SMOOTH_SANDSTONE_WALL,
                DnDBlocks.SMOOTH_RED_SANDSTONE_WALL,
                DnDBlocks.PRISMARINE_BRICKS_WALL,
                DnDBlocks.DARK_PRISMARINE_WALL,
                DnDBlocks.PURPUR_WALL,
                DnDBlocks.QUARTZ_WALL,
                DnDBlocks.SMOOTH_QUARTZ_WALL,
            )

        getOrCreateTagBuilder(BlockTags.FENCES)
            .add(DnDBlocks.BRICK_FENCE)

        getOrCreateTagBuilder(ConventionalBlockTags.INFESTED_COBBLESTONES)
            .add(
                DnDBlocks.INFESTED_MOSSY_COBBLESTONE,
                DnDBlocks.INFESTED_COBBLED_DEEPSLATE
            )

        getOrCreateTagBuilder(ConventionalBlockTags.DEEPSLATE_COBBLESTONES)
            .add(DnDBlocks.INFESTED_COBBLED_DEEPSLATE)
        getOrCreateTagBuilder(ConventionalBlockTags.MOSSY_COBBLESTONES)
            .add(DnDBlocks.INFESTED_MOSSY_COBBLESTONE)

        getOrCreateTagBuilder(BlockTags.ENCHANTMENT_POWER_PROVIDER)
            .forceAddTag(DnDBlockTags.BOOKSHELVES)
        getOrCreateTagBuilder(ConventionalBlockTags.BOOKSHELVES)
            .forceAddTag(DnDBlockTags.BOOKSHELVES)
        getOrCreateTagBuilder(DnDBlockTags.BOOKSHELVES)
            .forceAddTag(DnDBlockTags.BOOKSHELVES_THAT_BURN)
            .add(
                DnDBlocks.CRIMSON_BOOKSHELF,
                DnDBlocks.WARPED_BOOKSHELF,
            )
        getOrCreateTagBuilder(DnDBlockTags.BOOKSHELVES_THAT_BURN)
            .add(
                DnDBlocks.SPRUCE_BOOKSHELF,
                DnDBlocks.BIRCH_BOOKSHELF,
                DnDBlocks.JUNGLE_BOOKSHELF,
                DnDBlocks.ACACIA_BOOKSHELF,
                DnDBlocks.DARK_OAK_BOOKSHELF,
                DnDBlocks.MANGROVE_BOOKSHELF,
                DnDBlocks.CHERRY_BOOKSHELF,
                DnDBlocks.BAMBOO_BOOKSHELF,
                DnDBlocks.CASCADE_BOOKSHELF,
                DnDBlocks.SYPIA_BOOKSHELF,
                DnDBlocks.VERDANT_BOOKSHELF,
            )

        getOrCreateTagBuilder(ConventionalBlockTags.GLASS_PANES)
            .forceAddTag(CBlockTags.GLASS_PANES_TINTED)
        getOrCreateTagBuilder(CBlockTags.GLASS_PANES_TINTED)
            .add(DnDBlocks.TINTED_GLASS_PANE)


        getOrCreateTagBuilder(DnDBlockTags.CARPET_PLATES)
            .forceAddTag(DnDBlockTags.CARPET_PLATES_WOOL)
            .add(DnDBlocks.MOSS_CARPET_PLATE)
            .add(DnDBlocks.OVERGROWTH_CARPET_PLATE)

        getOrCreateTagBuilder(DnDBlockTags.CARPET_PLATES_WOOL)
            .add(DnDBlocks.WOOL_CARPET_PLATE)

        getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
            .forceAddTag(DnDBlockTags.CARPET_PLATES)
            .add(DnDBlocks.OVERGROWTH_CARPET)

        getOrCreateTagBuilder(BlockTags.DAMPENS_VIBRATIONS)
            .forceAddTag(DnDBlockTags.CARPET_PLATES_WOOL)

        // Sandstone
        getOrCreateTagBuilder(ConventionalBlockTags.UNCOLORED_SANDSTONE_BLOCKS)
            .add(
                DnDBlocks.ROUGH_SANDSTONE.parent,
                DnDBlocks.POLISHED_SANDSTONE.parent,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.RED_SANDSTONE_BLOCKS)
            .add(
                DnDBlocks.ROUGH_RED_SANDSTONE.parent,
                DnDBlocks.POLISHED_RED_SANDSTONE.parent,
            )

        getOrCreateTagBuilder(ConventionalBlockTags.UNCOLORED_SANDSTONE_SLABS)
            .add(
                DnDBlocks.ROUGH_SANDSTONE.slab,
                DnDBlocks.POLISHED_SANDSTONE.slab,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.RED_SANDSTONE_SLABS)
            .add(
                DnDBlocks.ROUGH_RED_SANDSTONE.slab,
                DnDBlocks.POLISHED_RED_SANDSTONE.slab,
            )

        getOrCreateTagBuilder(ConventionalBlockTags.UNCOLORED_SANDSTONE_STAIRS)
            .add(
                DnDBlocks.CUT_SANDSTONE_STAIR,
                DnDBlocks.ROUGH_SANDSTONE.stairs,
                DnDBlocks.POLISHED_SANDSTONE.stairs,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.RED_SANDSTONE_STAIRS)
            .add(
                DnDBlocks.CUT_RED_SANDSTONE_STAIR,
                DnDBlocks.ROUGH_RED_SANDSTONE.stairs,
                DnDBlocks.POLISHED_RED_SANDSTONE.stairs,
            )

        getOrCreateTagBuilder(CBlockTags.SANDSTONE_WALLS)
            .forceAddTag(CBlockTags.UNCOLORED_SANDSTONE_WALLS)
            .forceAddTag(CBlockTags.RED_SANDSTONE_WALLS)

        getOrCreateTagBuilder(CBlockTags.UNCOLORED_SANDSTONE_WALLS)
            .add(
                Blocks.SANDSTONE_WALL,
                DnDBlocks.SMOOTH_SANDSTONE_WALL,
                DnDBlocks.CUT_SANDSTONE_WALL,
                DnDBlocks.ROUGH_SANDSTONE.wall,
                DnDBlocks.POLISHED_SANDSTONE.wall,
            )
        getOrCreateTagBuilder(CBlockTags.RED_SANDSTONE_WALLS)
            .add(
                Blocks.RED_SANDSTONE_WALL,
                DnDBlocks.SMOOTH_RED_SANDSTONE_WALL,
                DnDBlocks.CUT_RED_SANDSTONE_WALL,
                DnDBlocks.ROUGH_RED_SANDSTONE.wall,
                DnDBlocks.POLISHED_RED_SANDSTONE.wall,
            )

        // These are for latter
        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(
                DnDBlocks.ROUGH_SANDSTONE.parent,
                DnDBlocks.ROUGH_RED_SANDSTONE.parent,
            )
        getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
            .add(
                DnDBlocks.ROUGH_SANDSTONE.parent,
                DnDBlocks.ROUGH_RED_SANDSTONE.parent,
            )
    }

    private fun duskTags() {
        getOrCreateTagBuilder(DnDBlockTags.HOLLOW_LOGS)
            .forceAddTag(DnDBlockTags.HOLLOW_LOGS_THAT_BURN)
            .add(DnDBlocks.HOLLOW_CRIMSON_STEM)
            .add(DnDBlocks.HOLLOW_WARPED_STEM)
            .add(DnDBlocks.HOLLOW_STRIPPED_CRIMSON_STEM)
            .add(DnDBlocks.HOLLOW_STRIPPED_WARPED_STEM)
        getOrCreateTagBuilder(DnDBlockTags.HOLLOW_LOGS_THAT_BURN)
            .add(
                DnDBlocks.HOLLOW_OAK_LOG,
                DnDBlocks.HOLLOW_SPRUCE_LOG,
                DnDBlocks.HOLLOW_BIRCH_LOG,
                DnDBlocks.HOLLOW_JUNGLE_LOG,
                DnDBlocks.HOLLOW_ACACIA_LOG,
                DnDBlocks.HOLLOW_DARK_OAK_LOG,
                DnDBlocks.HOLLOW_MANGROVE_LOG,
                DnDBlocks.HOLLOW_CHERRY_LOG,
                DnDBlocks.HOLLOW_CASCADE_LOG,
                DnDBlocks.HOLLOW_SYPIA_LOG,
                DnDBlocks.HOLLOW_VERDANT_LOG,
                DnDBlocks.HOLLOW_BAMBOO_BLOCK
            )
            .add(
                DnDBlocks.HOLLOW_STRIPPED_OAK_LOG,
                DnDBlocks.HOLLOW_STRIPPED_SPRUCE_LOG,
                DnDBlocks.HOLLOW_STRIPPED_BIRCH_LOG,
                DnDBlocks.HOLLOW_STRIPPED_JUNGLE_LOG,
                DnDBlocks.HOLLOW_STRIPPED_ACACIA_LOG,
                DnDBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG,
                DnDBlocks.HOLLOW_STRIPPED_MANGROVE_LOG,
                DnDBlocks.HOLLOW_STRIPPED_CHERRY_LOG,
                DnDBlocks.HOLLOW_STRIPPED_CASCADE_LOG,
                DnDBlocks.HOLLOW_STRIPPED_SYPIA_LOG,
                DnDBlocks.HOLLOW_STRIPPED_VERDANT_LOG,
                DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK
            )

        getOrCreateTagBuilder(DnDBlockTags.CASCADE_LOGS)
            .add(DnDBlocks.CASCADE_LOG)
            .add(DnDBlocks.CASCADE_WOOD.parent)
            .add(DnDBlocks.STRIPPED_CASCADE_LOG)
            .add(DnDBlocks.STRIPPED_CASCADE_WOOD.parent)
        getOrCreateTagBuilder(DnDBlockTags.SYPIA_LOGS)
            .add(DnDBlocks.SYPIA_LOG)
            .add(DnDBlocks.SYPIA_WOOD.parent)
            .add(DnDBlocks.STRIPPED_SYPIA_LOG)
            .add(DnDBlocks.STRIPPED_SYPIA_WOOD.parent)
        getOrCreateTagBuilder(DnDBlockTags.VERDANT_LOGS)
            .add(DnDBlocks.VERDANT_LOG)
            .add(DnDBlocks.VERDANT_WOOD.parent)
            .add(DnDBlocks.STRIPPED_VERDANT_LOG)
            .add(DnDBlocks.STRIPPED_VERDANT_WOOD.parent)

        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_PLANKS)
            .add(FLAMMABLE_PLANKS)
        getOrCreateTagBuilder(DnDBlockTags.FLAMMABLE_LOGS)
            .forceAddTag(DnDBlockTags.CASCADE_LOGS)
            .forceAddTag(DnDBlockTags.SYPIA_LOGS)
            .forceAddTag(DnDBlockTags.VERDANT_LOGS)
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
                DnDBlocks.SYPIA_LOG_PILE,
                DnDBlocks.VERDANT_LOG_PILE,
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
                DnDBlocks.STRIPPED_SYPIA_LOG_PILE,
                DnDBlocks.STRIPPED_VERDANT_LOG_PILE,
                DnDBlocks.STRIPPED_BAMBOO_PILE,
            )
        getOrCreateTagBuilder(DnDBlockTags.LEAF_PILES)
            .add(DnDBlockLists.leafPiles)
        getOrCreateTagBuilder(DnDBlockTags.LEAF_PILES_PLACE_ON)
            .forceAddTag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN)
            .forceAddTag(ConventionalBlockTags.COBBLESTONES)
            .forceAddTag(BlockTags.STONE_BRICKS)
            .forceAddTag(BlockTags.LOGS)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.PUMPKINS_CARVED)
            .forceAddTag(DnDBlockTags.PUMPKINS_GLOWING)
            .add(Blocks.MELON)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DnDBlockTags.FLOWERBEDS)
            .add(DnDBlockLists.flowerbedBlocks)
        getOrCreateTagBuilder(DnDBlockTags.VIVIONBEDS)
            .add(DnDBlockLists.vivionbedBlocks)
        getOrCreateTagBuilder(DnDBlockTags.SUPPORTS_VIVIONBED)
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
            .add(DnDBlocks.STONE_BRICK_GRAVESTONE)
            .add(DnDBlocks.DEEPSLATE_BRICK_GRAVESTONE)
            .add(DnDBlocks.TUFF_BRICK_GRAVESTONE)
            .add(DnDBlocks.BLACKSTONE_BRICK_GRAVESTONE)
        getOrCreateTagBuilder(DnDBlockTags.SMALL_GRAVESTONES)
            .add(DnDBlocks.SMALL_STONE_BRICK_GRAVESTONE)
            .add(DnDBlocks.SMALL_DEEPSLATE_BRICK_GRAVESTONE)
            .add(DnDBlocks.SMALL_TUFF_BRICK_GRAVESTONE)
            .add(DnDBlocks.SMALL_BLACKSTONE_BRICK_GRAVESTONE)
        getOrCreateTagBuilder(DnDBlockTags.HEADSTONES)
            .add(DnDBlocks.IRON_HEADSTONE)

        getOrCreateTagBuilder(DnDBlockTags.NETHER_BRICKS)
            .forceAddTag(DnDBlockTags.CRACKED_NETHER_BRICKS)
            .forceAddTag(DnDBlockTags.POLISHED_NETHER_BRICKS)
            .add(Blocks.NETHER_BRICKS)
            .add(Blocks.RED_NETHER_BRICKS)
            .add(DnDBlocks.BLUE_NETHER_BRICKS.parent)
            .add(DnDBlocks.GRAY_NETHER_BRICKS.parent)
        getOrCreateTagBuilder(DnDBlockTags.CRACKED_NETHER_BRICKS)
            .add(Blocks.CRACKED_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_BLUE_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_GRAY_NETHER_BRICKS)
        getOrCreateTagBuilder(DnDBlockTags.POLISHED_NETHER_BRICKS)
            .add(DnDBlocks.POLISHED_NETHER_BRICKS.parent)
            .add(DnDBlocks.POLISHED_RED_NETHER_BRICKS.parent)
            .add(DnDBlocks.POLISHED_BLUE_NETHER_BRICKS.parent)
            .add(DnDBlocks.POLISHED_GRAY_NETHER_BRICKS.parent)


        getOrCreateTagBuilder(DnDBlockTags.SUPPORTS_WARPED_NETHER_WART)
            .addOptionalTag(TaglightingBlockTags.SUPPORTS_NETHER_WART)
            .add(Blocks.SOUL_SAND)
        getOrCreateTagBuilder(DnDBlockTags.GOLD_MUSH_GROW_ON)
            .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
            .forceAddTag(BlockTags.DIRT)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DnDBlockTags.SUPPORTS_MOONBERRY)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DnDBlockTags.FARMLAND_PLACES_UNDER)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.PUMPKINS_CARVED)
            .forceAddTag(DnDBlockTags.PUMPKINS_GLOWING)
            .forceAddTag(BlockTags.CROPS)
            .forceAddTag(BlockTags.SAPLINGS)
            .forceAddTag(BlockTags.FLOWERS)
            .add(Blocks.MELON)
        getOrCreateTagBuilder(DnDBlockTags.REPLACEABLE_OR_DIRT)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.REPLACEABLE_BY_TREES)
            .forceAddTag(BlockTags.DIRT)
        getOrCreateTagBuilder(DnDBlockTags.VEGETATION_REPLACEABLE)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.REPLACEABLE_BY_TREES)

        getOrCreateTagBuilder(DnDBlockTags.PUMPKIN_PATCH_PLACE_ON)
            .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
            .forceAddTag(BlockTags.DIRT)
            .forceAddTag(DnDBlockTags.PUMPKINS_BLOCKS)
            .forceAddTag(ConventionalBlockTags.COBBLESTONES)
            .add(Blocks.FARMLAND)


        getOrCreateTagBuilder(CBlockTags.CORN_STORAGE)
            .add(DnDBlocks.CORN_BLOCK)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS_STEMS)
            .add(DnDBlocks.LANTERN_PUMPKIN_STEM)
            .add(DnDBlocks.MOSSKIN_PUMPKIN_STEM)
            .add(DnDBlocks.PALE_PUMPKIN_STEM)
            .add(DnDBlocks.GLOOM_PUMPKIN_STEM)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS_BLOCKS)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.PUMPKINS_CARVED)
            .forceAddTag(DnDBlockTags.PUMPKINS_GLOWING)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS_EIGHTHS)
            .forceAddTag(DnDBlockTags.PUMPKINS_SMALL)
            .forceAddTag(DnDBlockTags.PUMPKINS_SMALL_CARVED)
            .forceAddTag(DnDBlockTags.PUMPKINS_SMALL_GLOWING)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS)
            .add(Blocks.PUMPKIN)
            .add(DnDBlocks.LANTERN_PUMPKIN)
            .add(DnDBlocks.MOSSKIN_PUMPKIN)
            .add(DnDBlocks.PALE_PUMPKIN)
            .add(DnDBlocks.GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS_CARVED)
            .add(Blocks.CARVED_PUMPKIN)
            .add(DnDBlocks.CARVED_LANTERN_PUMPKIN)
            .add(DnDBlocks.CARVED_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.CARVED_PALE_PUMPKIN)
            .add(DnDBlocks.CARVED_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS_GLOWING)
            .add(Blocks.JACK_O_LANTERN)
            .add(DnDBlocks.GLOWING_LANTERN_PUMPKIN)
            .add(DnDBlocks.GLOWING_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.GLOWING_PALE_PUMPKIN)
            .add(DnDBlocks.GLOWING_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS_SMALL)
            .add(DnDBlocks.SMALL_PUMPKIN)
            .add(DnDBlocks.SMALL_LANTERN_PUMPKIN)
            .add(DnDBlocks.SMALL_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.SMALL_PALE_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS_SMALL_CARVED)
            .add(DnDBlocks.SMALL_CARVED_PUMPKIN)
            .add(DnDBlocks.SMALL_CARVED_LANTERN_PUMPKIN)
            .add(DnDBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.SMALL_CARVED_PALE_PUMPKIN)
            .add(DnDBlocks.SMALL_CARVED_GLOOM_PUMPKIN)
        getOrCreateTagBuilder(DnDBlockTags.PUMPKINS_SMALL_GLOWING)
            .add(DnDBlocks.SMALL_GLOWING_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOWING_LANTERN_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOWING_PALE_PUMPKIN)
            .add(DnDBlocks.SMALL_GLOWING_GLOOM_PUMPKIN)

        getOrCreateTagBuilder(TaglightingBlockTags.CANNOT_CONNECT_TO)
            .forceAddTag(DnDBlockTags.PUMPKINS)
            .forceAddTag(DnDBlockTags.PUMPKINS_CARVED)
            .forceAddTag(DnDBlockTags.PUMPKINS_GLOWING)
    }

    private fun woodTags() {
        WOOD_SETS.filterNot(excludeWood::contains).forEach { it.createWoodTags(::getOrCreateTagBuilder) }
        // TODO(1.0) make wood walls work as part of walls tags
        // getOrCreateTagBuilder(BlockTags.WALLS).forceAddTag(DnDBlockTags.WOODEN_WALLS)
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).forceAddTag(DnDBlockTags.WOODEN_WALLS)

        getOrCreateTagBuilder(DnDBlockTags.WOODEN_WALLS)
            .forceAddTag(DnDBlockTags.WOOD_WALLS)
            .forceAddTag(DnDBlockTags.PLANK_WALLS)

        getOrCreateTagBuilder(DnDBlockTags.PLANK_WALLS)
            .forceAddTag(DnDBlockTags.PLANK_WALLS_THAT_BURN)
            .add(DnDBlocks.CRIMSON_WALL)
            .add(DnDBlocks.WARPED_WALL)

        getOrCreateTagBuilder(DnDBlockTags.PLANK_WALLS_THAT_BURN)
            .add(DnDBlocks.OAK_WALL)
            .add(DnDBlocks.SPRUCE_WALL)
            .add(DnDBlocks.BIRCH_WALL)
            .add(DnDBlocks.JUNGLE_WALL)
            .add(DnDBlocks.ACACIA_WALL)
            .add(DnDBlocks.DARK_OAK_WALL)
            .add(DnDBlocks.MANGROVE_WALL)
            .add(DnDBlocks.CHERRY_WALL)
            .add(DnDBlocks.BAMBOO_WALL)
            .add(DnDBlocks.BAMBOO_MOSAIC_WALL)
            .add(DnDBlocks.CASCADE_WALL)
            .add(DnDBlocks.SYPIA_WALL)
            .add(DnDBlocks.VERDANT_WALL)

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


        getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_LOGS)
            .add(
                DnDBlocks.STRIPPED_CASCADE_LOG,
                DnDBlocks.STRIPPED_SYPIA_LOG,
                DnDBlocks.STRIPPED_VERDANT_LOG,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_WOODS)
            .add(
                DnDBlocks.STRIPPED_CASCADE_WOOD.parent,
                DnDBlocks.STRIPPED_SYPIA_WOOD.parent,
                DnDBlocks.STRIPPED_VERDANT_WOOD.parent,
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
            .forceAddTag(DnDBlockTags.PUMPKINS_SMALL)
            .forceAddTag(DnDBlockTags.PUMPKINS_SMALL_CARVED)
            .forceAddTag(DnDBlockTags.PUMPKINS_SMALL_GLOWING)
            .add(DnDBlocks.HANGING_OVERGROWTH)
            .add(DnDBlocks.ROOT_BLOCK)

        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES)
            .forceAddTag(DnDBlockTags.PUMPKINS_STEMS)
            .add(DnDBlocks.MOONBERRY_VINE)
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .forceAddTag(DnDBlockTags.PUMPKINS_STEMS)
            .add(DnDBlocks.CORN_CROP)
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
            .forceAddTag(DnDBlockTags.PUMPKINS_CARVED)
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
        getOrCreateTagBuilder(BlockTags.DIRT)
            .add(DnDBlocks.OVERGROWTH_BLOCK)
        getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK)
            .add(DnDBlocks.OVERGROWTH_BLOCK)
        getOrCreateTagBuilder(BlockTags.SNIFFER_EGG_HATCH_BOOST)
            .add(DnDBlocks.OVERGROWTH_BLOCK)
        getOrCreateTagBuilder(BlockTags.SMALL_DRIPLEAF_PLACEABLE)
            .add(DnDBlocks.OVERGROWTH_BLOCK)

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .forceAddTag(DnDBlockTags.CASCADE_LOGS)
            .forceAddTag(DnDBlockTags.SYPIA_LOGS)
            .forceAddTag(DnDBlockTags.VERDANT_LOGS)
        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(DnDBlocks.CASCADE_PLANKS)
            .add(DnDBlocks.SYPIA_PLANKS)
            .add(DnDBlocks.VERDANT_PLANKS)
        getOrCreateTagBuilder(BlockTags.CROPS)
            .add(DnDBlocks.CORN_CROP)
            .add(DnDBlocks.GOLDEN_BEETROOTS)
            .add(DnDBlocks.MOONBERRY_VINELET)
            .forceAddTag(DnDBlockTags.PUMPKINS_STEMS)

        getOrCreateTagBuilder(BlockTags.FLOWERS)
            .add(DnDBlocks.CHERRY_LEAF_PILE)
            .forceAddTag(DnDBlockTags.FLOWERBEDS)
            .forceAddTag(DnDBlockTags.VIVIONBEDS)
        getOrCreateTagBuilder(BlockTags.LEAVES)
            .add(DnDBlocks.CASCADE_LEAVES)
            .add(DnDBlocks.SYPIA_LEAVES)
            .add(DnDBlocks.VERDANT_LEAVES)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.SAPLINGS)
            .add(DnDBlocks.CASCADE_SAPLING)
            .add(DnDBlocks.SYPIA_SAPLING)
            .add(DnDBlocks.OVERGROWTH_BUSH)
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
            .add(DnDBlocks.POTTED_CASCADE_SAPLING)
            .add(DnDBlocks.POTTED_SYPIA_SAPLING)
            .add(DnDBlocks.POTTED_OVERGROWTH_BUSH)

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
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
            .add(DnDBlocks.CASCADE_STAIRS)
            .add(DnDBlocks.SYPIA_STAIRS)
            .add(DnDBlocks.VERDANT_STAIRS)
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
            .add(DnDBlocks.CASCADE_SLAB)
            .add(DnDBlocks.SYPIA_SLAB)
            .add(DnDBlocks.VERDANT_SLAB)
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add(DnDBlocks.CASCADE_DOOR)
            .add(DnDBlocks.SYPIA_DOOR)
            .add(DnDBlocks.VERDANT_DOOR)
            .add(DnDBlocks.BLUE_DOOR)
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add(DnDBlocks.CASCADE_TRAPDOOR)
            .add(DnDBlocks.SYPIA_TRAPDOOR)
            .add(DnDBlocks.VERDANT_TRAPDOOR)
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(DnDBlocks.CASCADE_FENCE)
            .add(DnDBlocks.SYPIA_FENCE)
            .add(DnDBlocks.VERDANT_FENCE)
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(DnDBlocks.CASCADE_FENCE_GATE)
            .add(DnDBlocks.SYPIA_FENCE_GATE)
            .add(DnDBlocks.VERDANT_FENCE_GATE)
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
            .add(DnDBlocks.CASCADE_PRESSURE_PLATE)
            .add(DnDBlocks.SYPIA_PRESSURE_PLATE)
            .add(DnDBlocks.VERDANT_PRESSURE_PLATE)
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
            .add(DnDBlocks.CASCADE_BUTTON)
            .add(DnDBlocks.SYPIA_BUTTON)
            .add(DnDBlocks.VERDANT_BUTTON)
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
            .add(DnDBlocks.CASCADE_SIGN)
            .add(DnDBlocks.SYPIA_SIGN)
            .add(DnDBlocks.VERDANT_SIGN)
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
            .add(DnDBlocks.CASCADE_WALL_SIGN)
            .add(DnDBlocks.SYPIA_WALL_SIGN)
            .add(DnDBlocks.VERDANT_WALL_SIGN)
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
            .add(DnDBlocks.CASCADE_HANGING_SIGN)
            .add(DnDBlocks.SYPIA_HANGING_SIGN)
            .add(DnDBlocks.VERDANT_HANGING_SIGN)
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
            .add(DnDBlocks.CASCADE_WALL_HANGING_SIGN)
            .add(DnDBlocks.SYPIA_WALL_HANGING_SIGN)
            .add(DnDBlocks.VERDANT_WALL_HANGING_SIGN)
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
}
