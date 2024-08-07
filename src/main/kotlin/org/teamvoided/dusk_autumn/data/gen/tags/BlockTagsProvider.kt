package org.teamvoided.dusk_autumn.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags
import org.teamvoided.dusk_autumn.init.DnDBlocks
import java.util.concurrent.CompletableFuture

class BlockTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider) {
        duskTags()
        vanillaTags()
        conventionTags()
    }

    fun duskTags() {
        getOrCreateTagBuilder(DnDBlockTags.CASCADE_LOGS)
            .add(DnDBlocks.CASCADE_LOG)
            .add(DnDBlocks.CASCADE_WOOD)
            .add(DnDBlocks.STRIPPED_CASCADE_LOG)
            .add(DnDBlocks.STRIPPED_CASCADE_WOOD)
        getOrCreateTagBuilder(DnDBlockTags.LOG_PILES)
            .add(DnDBlocks.OAK_LOG_PILE)
            .add(DnDBlocks.SPRUCE_LOG_PILE)
            .add(DnDBlocks.BIRCH_LOG_PILE)
            .add(DnDBlocks.JUNGLE_LOG_PILE)
            .add(DnDBlocks.ACACIA_LOG_PILE)
            .add(DnDBlocks.DARK_OAK_LOG_PILE)
            .add(DnDBlocks.MANGROVE_LOG_PILE)
            .add(DnDBlocks.CHERRY_LOG_PILE)
            .add(DnDBlocks.CASCADE_LOG_PILE)
            .add(DnDBlocks.CRIMSON_STEM_PILE)
            .add(DnDBlocks.WARPED_STEM_PILE)
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
            .add(DnDBlocks.OVERGROWN_COBBLESTONE)
            .forceAddTag(BlockTags.STONE_BRICKS)
            .forceAddTag(BlockTags.LOGS)
            .add(Blocks.MELON)
            .add(Blocks.PUMPKIN)
            .add(Blocks.CARVED_PUMPKIN)
            .add(Blocks.JACK_O_LANTERN)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DnDBlockTags.BIG_CANDLES)
            .forceAddTag(DnDBlockTags.BIG_SOUL_CANDLES)
            .add(DnDBlocks.BIG_CANDLE)
            .add(DnDBlocks.BIG_WHITE_CANDLE)
            .add(DnDBlocks.BIG_LIGHT_GRAY_CANDLE)
            .add(DnDBlocks.BIG_GRAY_CANDLE)
            .add(DnDBlocks.BIG_BLACK_CANDLE)
            .add(DnDBlocks.BIG_BROWN_CANDLE)
            .add(DnDBlocks.BIG_RED_CANDLE)
            .add(DnDBlocks.BIG_ORANGE_CANDLE)
            .add(DnDBlocks.BIG_YELLOW_CANDLE)
            .add(DnDBlocks.BIG_LIME_CANDLE)
            .add(DnDBlocks.BIG_GREEN_CANDLE)
            .add(DnDBlocks.BIG_CYAN_CANDLE)
            .add(DnDBlocks.BIG_LIGHT_BLUE_CANDLE)
            .add(DnDBlocks.BIG_BLUE_CANDLE)
            .add(DnDBlocks.BIG_PURPLE_CANDLE)
            .add(DnDBlocks.BIG_MAGENTA_CANDLE)
            .add(DnDBlocks.BIG_PINK_CANDLE)
        getOrCreateTagBuilder(DnDBlockTags.SOUL_CANDLES)
            .add(DnDBlocks.SOUL_CANDLE)
            .add(DnDBlocks.WHITE_SOUL_CANDLE)
            .add(DnDBlocks.LIGHT_GRAY_SOUL_CANDLE)
            .add(DnDBlocks.GRAY_SOUL_CANDLE)
            .add(DnDBlocks.BLACK_SOUL_CANDLE)
            .add(DnDBlocks.BROWN_SOUL_CANDLE)
            .add(DnDBlocks.RED_SOUL_CANDLE)
            .add(DnDBlocks.ORANGE_SOUL_CANDLE)
            .add(DnDBlocks.YELLOW_SOUL_CANDLE)
            .add(DnDBlocks.LIME_SOUL_CANDLE)
            .add(DnDBlocks.GREEN_SOUL_CANDLE)
            .add(DnDBlocks.CYAN_SOUL_CANDLE)
            .add(DnDBlocks.LIGHT_BLUE_SOUL_CANDLE)
            .add(DnDBlocks.BLUE_SOUL_CANDLE)
            .add(DnDBlocks.PURPLE_SOUL_CANDLE)
            .add(DnDBlocks.MAGENTA_SOUL_CANDLE)
            .add(DnDBlocks.PINK_SOUL_CANDLE)
        getOrCreateTagBuilder(DnDBlockTags.BIG_SOUL_CANDLES)
            .add(DnDBlocks.BIG_SOUL_CANDLE)
            .add(DnDBlocks.BIG_WHITE_SOUL_CANDLE)
            .add(DnDBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE)
            .add(DnDBlocks.BIG_GRAY_SOUL_CANDLE)
            .add(DnDBlocks.BIG_BLACK_SOUL_CANDLE)
            .add(DnDBlocks.BIG_BROWN_SOUL_CANDLE)
            .add(DnDBlocks.BIG_RED_SOUL_CANDLE)
            .add(DnDBlocks.BIG_ORANGE_SOUL_CANDLE)
            .add(DnDBlocks.BIG_YELLOW_SOUL_CANDLE)
            .add(DnDBlocks.BIG_LIME_SOUL_CANDLE)
            .add(DnDBlocks.BIG_GREEN_SOUL_CANDLE)
            .add(DnDBlocks.BIG_CYAN_SOUL_CANDLE)
            .add(DnDBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE)
            .add(DnDBlocks.BIG_BLUE_SOUL_CANDLE)
            .add(DnDBlocks.BIG_PURPLE_SOUL_CANDLE)
            .add(DnDBlocks.BIG_MAGENTA_SOUL_CANDLE)
            .add(DnDBlocks.BIG_PINK_SOUL_CANDLE)
        getOrCreateTagBuilder(DnDBlockTags.NETHER_BRICKS)
            .forceAddTag(DnDBlockTags.CRACKED_NETHER_BRICKS)
            .forceAddTag(DnDBlockTags.POLISHED_NETHER_BRICKS)
            .add(Blocks.NETHER_BRICKS)
            .add(DnDBlocks.MIXED_NETHER_BRICKS)
            .add(Blocks.RED_NETHER_BRICKS)
            .add(DnDBlocks.BLUE_NETHER_BRICKS)
            .add(DnDBlocks.MIXED_BLUE_NETHER_BRICKS)
            .add(DnDBlocks.GRAY_NETHER_BRICKS)
            .add(DnDBlocks.MIXED_GRAY_NETHER_BRICKS)
        getOrCreateTagBuilder(DnDBlockTags.CRACKED_NETHER_BRICKS)
            .add(Blocks.CRACKED_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_MIXED_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_BLUE_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_GRAY_NETHER_BRICKS)
            .add(DnDBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS)
        getOrCreateTagBuilder(DnDBlockTags.POLISHED_NETHER_BRICKS)
            .add(DnDBlocks.POLISHED_NETHER_BRICKS)
            .add(DnDBlocks.POLISHED_RED_NETHER_BRICKS)
            .add(DnDBlocks.POLISHED_BLUE_NETHER_BRICKS)
            .add(DnDBlocks.POLISHED_GRAY_NETHER_BRICKS)
        getOrCreateTagBuilder(DnDBlockTags.WARPED_NETHER_WART_PLACEABLE)
            .addOptionalTag(id("nullium","support/nether_wart"))
            .add(Blocks.SOUL_SAND)
        getOrCreateTagBuilder(DnDBlockTags.CHILL_CHARGE_AFFECTS)
            .forceAddTag(BlockTags.CANDLES)
            .forceAddTag(BlockTags.CAMPFIRES)
        getOrCreateTagBuilder(DnDBlockTags.MOONBERRY_CAN_PLACE_ON)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DnDBlockTags.FARMLAND_PLACES_UNDER)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(BlockTags.CROPS)
            .forceAddTag(BlockTags.SAPLINGS)
            .forceAddTag(BlockTags.FLOWERS)
            .add(Blocks.MELON)
            .add(Blocks.PUMPKIN)
            .add(Blocks.CARVED_PUMPKIN)
            .add(Blocks.JACK_O_LANTERN)
        getOrCreateTagBuilder(DnDBlockTags.REPLACEABLE_OR_DIRT)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.REPLACEABLE_BY_TREES)
            .forceAddTag(BlockTags.DIRT)
    }

    fun vanillaTags() {
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
            .add(DnDBlocks.BLUE_PETALS)
            .add(DnDBlocks.ROOT_BLOCK)
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .forceAddTag(DnDBlockTags.CASCADE_LOGS)
        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(DnDBlocks.CASCADE_PLANKS)
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
        getOrCreateTagBuilder(BlockTags.CROPS)
            .add(DnDBlocks.GOLDEN_BEETROOTS)
            .add(DnDBlocks.MOONBERRY_VINELET)
        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES)
            .add(DnDBlocks.MOONBERRY_VINE)
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
            .add(DnDBlocks.BLUE_PETALS)
        getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
            .forceAddTag(DnDBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.FLOWERS)
            .add(DnDBlocks.CHERRY_LEAF_PILE)
            .add(DnDBlocks.BLUE_PETALS)
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
        getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
            .add(DnDBlocks.ROCKY_PODZOL, DnDBlocks.SLATED_PODZOL, DnDBlocks.BLACKSTONE_PODZOL)
            .add(DnDBlocks.ROCKY_MYCELIUM, DnDBlocks.SLATED_MYCELIUM, DnDBlocks.BLACKSTONE_MYCELIUM)
            .forceAddTag(BlockTags.LOGS)

        getOrCreateTagBuilder(BlockTags.CANDLES)
            .forceAddTag(DnDBlockTags.BIG_CANDLES)
            .forceAddTag(DnDBlockTags.SOUL_CANDLES)

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(DnDBlocks.NETHERRACK_STAIRS)
            .add(DnDBlocks.BLUE_NETHER_BRICK_STAIRS)
            .add(DnDBlocks.GRAY_NETHER_BRICK_STAIRS)
            .add(DnDBlocks.MIXED_NETHER_BRICK_STAIRS)
            .add(DnDBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS)
            .add(DnDBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS)
            .add(DnDBlocks.POLISHED_NETHER_BRICK_STAIRS)
            .add(DnDBlocks.POLISHED_RED_NETHER_BRICK_STAIRS)
            .add(DnDBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS)
            .add(DnDBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS)
            .add(DnDBlocks.OVERGROWN_COBBLESTONE_STAIRS)
            .add(DnDBlocks.OVERGROWN_STONE_BRICK_STAIRS)
        getOrCreateTagBuilder(BlockTags.SLABS)
            .add(DnDBlocks.NETHERRACK_SLAB)
            .add(DnDBlocks.BLUE_NETHER_BRICK_SLAB)
            .add(DnDBlocks.GRAY_NETHER_BRICK_SLAB)
            .add(DnDBlocks.MIXED_NETHER_BRICK_SLAB)
            .add(DnDBlocks.MIXED_BLUE_NETHER_BRICK_SLAB)
            .add(DnDBlocks.MIXED_GRAY_NETHER_BRICK_SLAB)
            .add(DnDBlocks.POLISHED_NETHER_BRICK_SLAB)
            .add(DnDBlocks.POLISHED_RED_NETHER_BRICK_SLAB)
            .add(DnDBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB)
            .add(DnDBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB)
            .add(DnDBlocks.OVERGROWN_COBBLESTONE_SLAB)
            .add(DnDBlocks.OVERGROWN_STONE_BRICK_SLAB)
        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(DnDBlocks.ICE_WALL)
            .add(DnDBlocks.PACKED_ICE_WALL)
            .add(DnDBlocks.BLUE_ICE_WALL)
            .add(DnDBlocks.NETHERRACK_WALL)
            .add(DnDBlocks.BLUE_NETHER_BRICK_WALL)
            .add(DnDBlocks.GRAY_NETHER_BRICK_WALL)
            .add(DnDBlocks.MIXED_NETHER_BRICK_WALL)
            .add(DnDBlocks.MIXED_BLUE_NETHER_BRICK_WALL)
            .add(DnDBlocks.MIXED_GRAY_NETHER_BRICK_WALL)
            .add(DnDBlocks.POLISHED_NETHER_BRICK_WALL)
            .add(DnDBlocks.POLISHED_RED_NETHER_BRICK_WALL)
            .add(DnDBlocks.POLISHED_BLUE_NETHER_BRICK_WALL)
            .add(DnDBlocks.POLISHED_GRAY_NETHER_BRICK_WALL)
            .add(DnDBlocks.OVERGROWN_COBBLESTONE_WALL)
            .add(DnDBlocks.OVERGROWN_STONE_BRICK_WALL)
        getOrCreateTagBuilder(BlockTags.FENCES)
            .add(DnDBlocks.RED_NETHER_BRICK_FENCE)
            .add(DnDBlocks.BLUE_NETHER_BRICK_FENCE)
            .add(DnDBlocks.GRAY_NETHER_BRICK_FENCE)
            .add(DnDBlocks.MIXED_NETHER_BRICK_FENCE)
            .add(DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE)
            .add(DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE)

        getOrCreateTagBuilder(BlockTags.STONE_BRICKS)
            .add(DnDBlocks.OVERGROWN_STONE_BRICKS)

        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(DnDBlocks.ROCKY_GRAVEL, DnDBlocks.SLATED_GRAVEL, DnDBlocks.BLACKSTONE_GRAVEL)
        getOrCreateTagBuilder(BlockTags.NETHER_CARVER_REPLACEABLES)
            .add(DnDBlocks.ROCKY_SOUL_SAND, DnDBlocks.SLATED_SOUL_SAND, DnDBlocks.BLACKSTONE_SOUL_SAND)
            .add(DnDBlocks.ROCKY_SOUL_SOIL, DnDBlocks.SLATED_SOUL_SOIL, DnDBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
            .add(DnDBlocks.ROCKY_GRAVEL, DnDBlocks.SLATED_GRAVEL, DnDBlocks.BLACKSTONE_GRAVEL)
            .add(DnDBlocks.ROCKY_SAND, DnDBlocks.SLATED_SAND, DnDBlocks.BLACKSTONE_SAND)
            .add(DnDBlocks.ROCKY_RED_SAND, DnDBlocks.SLATED_RED_SAND, DnDBlocks.BLACKSTONE_RED_SAND)
            .add(DnDBlocks.ROCKY_SOUL_SAND, DnDBlocks.SLATED_SOUL_SAND, DnDBlocks.BLACKSTONE_SOUL_SAND)
            .add(DnDBlocks.ROCKY_SOUL_SOIL, DnDBlocks.SLATED_SOUL_SOIL, DnDBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.DIRT)
            .add(DnDBlocks.ROCKY_DIRT, DnDBlocks.SLATED_DIRT, DnDBlocks.BLACKSTONE_DIRT)
            .add(DnDBlocks.ROCKY_GRASS, DnDBlocks.SLATED_GRASS, DnDBlocks.BLACKSTONE_GRASS)
            .add(DnDBlocks.ROCKY_PODZOL, DnDBlocks.SLATED_PODZOL, DnDBlocks.BLACKSTONE_PODZOL)
            .add(DnDBlocks.ROCKY_MYCELIUM, DnDBlocks.SLATED_MYCELIUM, DnDBlocks.BLACKSTONE_MYCELIUM)
            .add(DnDBlocks.ROCKY_COARSE_DIRT, DnDBlocks.SLATED_COARSE_DIRT, DnDBlocks.BLACKSTONE_COARSE_DIRT)
            .add(DnDBlocks.ROCKY_MUD, DnDBlocks.SLATED_MUD, DnDBlocks.BLACKSTONE_MUD)
        getOrCreateTagBuilder(BlockTags.SAND)
            .add(DnDBlocks.ROCKY_SAND, DnDBlocks.SLATED_SAND, DnDBlocks.BLACKSTONE_SAND)
            .add(DnDBlocks.ROCKY_RED_SAND, DnDBlocks.SLATED_RED_SAND, DnDBlocks.BLACKSTONE_RED_SAND)
        getOrCreateTagBuilder(BlockTags.SNOW)
            .add(DnDBlocks.ROCKY_SNOW, DnDBlocks.SLATED_SNOW, DnDBlocks.BLACKSTONE_SNOW)
        getOrCreateTagBuilder(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON)
            .add(DnDBlocks.ROCKY_MUD, DnDBlocks.SLATED_MUD, DnDBlocks.BLACKSTONE_MUD)
            .add(DnDBlocks.ROCKY_SOUL_SAND, DnDBlocks.SLATED_SOUL_SAND, DnDBlocks.BLACKSTONE_SOUL_SAND)
        getOrCreateTagBuilder(BlockTags.WITHER_SUMMON_BASE_BLOCKS)
            .add(DnDBlocks.ROCKY_SOUL_SAND, DnDBlocks.SLATED_SOUL_SAND, DnDBlocks.BLACKSTONE_SOUL_SAND)
            .add(DnDBlocks.ROCKY_SOUL_SOIL, DnDBlocks.SLATED_SOUL_SOIL, DnDBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.SOUL_SPEED_BLOCKS)
            .add(DnDBlocks.ROCKY_SOUL_SAND, DnDBlocks.SLATED_SOUL_SAND, DnDBlocks.BLACKSTONE_SOUL_SAND)
            .add(DnDBlocks.ROCKY_SOUL_SOIL, DnDBlocks.SLATED_SOUL_SOIL, DnDBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS)
            .add(DnDBlocks.ROCKY_SOUL_SAND, DnDBlocks.SLATED_SOUL_SAND, DnDBlocks.BLACKSTONE_SOUL_SAND)
            .add(DnDBlocks.ROCKY_SOUL_SOIL, DnDBlocks.SLATED_SOUL_SOIL, DnDBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON)
            .add(DnDBlocks.ROCKY_GRAVEL, DnDBlocks.SLATED_GRAVEL, DnDBlocks.BLACKSTONE_GRAVEL)
        getOrCreateTagBuilder(BlockTags.VALID_SPAWN)
            .add(DnDBlocks.ROCKY_GRASS, DnDBlocks.SLATED_GRASS, DnDBlocks.BLACKSTONE_GRASS)
            .add(DnDBlocks.ROCKY_PODZOL, DnDBlocks.SLATED_PODZOL, DnDBlocks.BLACKSTONE_PODZOL)
        getOrCreateTagBuilder(BlockTags.ANIMALS_SPAWNABLE_ON)
            .add(DnDBlocks.ROCKY_GRASS, DnDBlocks.SLATED_GRASS, DnDBlocks.BLACKSTONE_GRASS)
        getOrCreateTagBuilder(BlockTags.FOXES_SPAWNABLE_ON)
            .add(DnDBlocks.ROCKY_GRASS, DnDBlocks.SLATED_GRASS, DnDBlocks.BLACKSTONE_GRASS)
            .add(DnDBlocks.ROCKY_PODZOL, DnDBlocks.SLATED_PODZOL, DnDBlocks.BLACKSTONE_PODZOL)
            .add(DnDBlocks.ROCKY_COARSE_DIRT, DnDBlocks.SLATED_COARSE_DIRT, DnDBlocks.BLACKSTONE_COARSE_DIRT)
            .add(DnDBlocks.ROCKY_SNOW, DnDBlocks.SLATED_SNOW, DnDBlocks.BLACKSTONE_SNOW)
        getOrCreateTagBuilder(BlockTags.MOOSHROOMS_SPAWNABLE_ON)
            .add(DnDBlocks.ROCKY_MYCELIUM, DnDBlocks.SLATED_MYCELIUM, DnDBlocks.BLACKSTONE_MYCELIUM)
        getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK)
            .add(DnDBlocks.ROCKY_DIRT, DnDBlocks.SLATED_DIRT, DnDBlocks.BLACKSTONE_DIRT)
            .add(DnDBlocks.ROCKY_GRASS, DnDBlocks.SLATED_GRASS, DnDBlocks.BLACKSTONE_GRASS)
            .add(DnDBlocks.ROCKY_PODZOL, DnDBlocks.SLATED_PODZOL, DnDBlocks.BLACKSTONE_PODZOL)
            .add(DnDBlocks.ROCKY_COARSE_DIRT, DnDBlocks.SLATED_COARSE_DIRT, DnDBlocks.BLACKSTONE_COARSE_DIRT)
        getOrCreateTagBuilder(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
            .add(DnDBlocks.ROCKY_MUD, DnDBlocks.SLATED_MUD, DnDBlocks.BLACKSTONE_MUD)
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
    }

    fun conventionTags() {
        getOrCreateTagBuilder(ConventionalBlockTags.COBBLESTONES)
            .add(DnDBlocks.OVERGROWN_COBBLESTONE)
        getOrCreateTagBuilder(ConventionalBlockTags.CHAINS)
    }
}

private fun <T> FabricTagProvider<T>.FabricTagBuilder.addAll(list: Collection<T>): FabricTagProvider<T>.FabricTagBuilder {
    list.forEach{this.add(it)}
    return this
}
