package org.teamvoided.dusk_autumn.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.Items
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.data.tags.DuskBlockTags
import org.teamvoided.dusk_autumn.data.tags.DuskItemTags
import org.teamvoided.dusk_autumn.init.DuskBlocks
import java.util.concurrent.CompletableFuture

class BlockTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider) {
        duskTags()
        vanillaTags()
        conventionTags()
    }

    fun duskTags() {
        getOrCreateTagBuilder(DuskBlockTags.CASCADE_LOGS)
            .add(DuskBlocks.CASCADE_LOG)
            .add(DuskBlocks.CASCADE_WOOD)
            .add(DuskBlocks.STRIPPED_CASCADE_LOG)
            .add(DuskBlocks.STRIPPED_CASCADE_WOOD)
        getOrCreateTagBuilder(DuskBlockTags.LOG_PILES)
            .add(DuskBlocks.OAK_LOG_PILE)
            .add(DuskBlocks.SPRUCE_LOG_PILE)
            .add(DuskBlocks.BIRCH_LOG_PILE)
            .add(DuskBlocks.JUNGLE_LOG_PILE)
            .add(DuskBlocks.ACACIA_LOG_PILE)
            .add(DuskBlocks.DARK_OAK_LOG_PILE)
            .add(DuskBlocks.MANGROVE_LOG_PILE)
            .add(DuskBlocks.CHERRY_LOG_PILE)
            .add(DuskBlocks.CASCADE_LOG_PILE)
        getOrCreateTagBuilder(DuskBlockTags.LEAF_PILES)
            .add(DuskBlocks.OAK_LEAF_PILE)
            .add(DuskBlocks.SPRUCE_LEAF_PILE)
            .add(DuskBlocks.BIRCH_LEAF_PILE)
            .add(DuskBlocks.JUNGLE_LEAF_PILE)
            .add(DuskBlocks.ACACIA_LEAF_PILE)
            .add(DuskBlocks.DARK_OAK_LEAF_PILE)
            .add(DuskBlocks.MANGROVE_LEAF_PILE)
            .add(DuskBlocks.CHERRY_LEAF_PILE)
            .add(DuskBlocks.AZALEA_LEAF_PILE)
            .add(DuskBlocks.FLOWERING_AZALEA_LEAF_PILE)
            .add(DuskBlocks.CASCADE_LEAF_PILE)
            .add(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE)
        getOrCreateTagBuilder(DuskBlockTags.LEAF_PILES_PLACE_ON)
            .forceAddTag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN)
            .add(Blocks.COBBLESTONE)
            .add(Blocks.MOSSY_COBBLESTONE)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE)
            .forceAddTag(BlockTags.STONE_BRICKS)
            .forceAddTag(BlockTags.LOGS)
            .add(Blocks.MELON)
            .add(Blocks.PUMPKIN)
            .add(Blocks.CARVED_PUMPKIN)
            .add(Blocks.JACK_O_LANTERN)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DuskBlockTags.BIG_CANDLES)
            .forceAddTag(DuskBlockTags.BIG_SOUL_CANDLES)
            .add(DuskBlocks.BIG_CANDLE)
            .add(DuskBlocks.BIG_WHITE_CANDLE)
            .add(DuskBlocks.BIG_LIGHT_GRAY_CANDLE)
            .add(DuskBlocks.BIG_GRAY_CANDLE)
            .add(DuskBlocks.BIG_BLACK_CANDLE)
            .add(DuskBlocks.BIG_BROWN_CANDLE)
            .add(DuskBlocks.BIG_RED_CANDLE)
            .add(DuskBlocks.BIG_ORANGE_CANDLE)
            .add(DuskBlocks.BIG_YELLOW_CANDLE)
            .add(DuskBlocks.BIG_LIME_CANDLE)
            .add(DuskBlocks.BIG_GREEN_CANDLE)
            .add(DuskBlocks.BIG_CYAN_CANDLE)
            .add(DuskBlocks.BIG_LIGHT_BLUE_CANDLE)
            .add(DuskBlocks.BIG_BLUE_CANDLE)
            .add(DuskBlocks.BIG_PURPLE_CANDLE)
            .add(DuskBlocks.BIG_MAGENTA_CANDLE)
            .add(DuskBlocks.BIG_PINK_CANDLE)
        getOrCreateTagBuilder(DuskBlockTags.SOUL_CANDLES)
            .add(DuskBlocks.SOUL_CANDLE)
            .add(DuskBlocks.WHITE_SOUL_CANDLE)
            .add(DuskBlocks.LIGHT_GRAY_SOUL_CANDLE)
            .add(DuskBlocks.GRAY_SOUL_CANDLE)
            .add(DuskBlocks.BLACK_SOUL_CANDLE)
            .add(DuskBlocks.BROWN_SOUL_CANDLE)
            .add(DuskBlocks.RED_SOUL_CANDLE)
            .add(DuskBlocks.ORANGE_SOUL_CANDLE)
            .add(DuskBlocks.YELLOW_SOUL_CANDLE)
            .add(DuskBlocks.LIME_SOUL_CANDLE)
            .add(DuskBlocks.GREEN_SOUL_CANDLE)
            .add(DuskBlocks.CYAN_SOUL_CANDLE)
            .add(DuskBlocks.LIGHT_BLUE_SOUL_CANDLE)
            .add(DuskBlocks.BLUE_SOUL_CANDLE)
            .add(DuskBlocks.PURPLE_SOUL_CANDLE)
            .add(DuskBlocks.MAGENTA_SOUL_CANDLE)
            .add(DuskBlocks.PINK_SOUL_CANDLE)
        getOrCreateTagBuilder(DuskBlockTags.BIG_SOUL_CANDLES)
            .add(DuskBlocks.BIG_SOUL_CANDLE)
            .add(DuskBlocks.BIG_WHITE_SOUL_CANDLE)
            .add(DuskBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE)
            .add(DuskBlocks.BIG_GRAY_SOUL_CANDLE)
            .add(DuskBlocks.BIG_BLACK_SOUL_CANDLE)
            .add(DuskBlocks.BIG_BROWN_SOUL_CANDLE)
            .add(DuskBlocks.BIG_RED_SOUL_CANDLE)
            .add(DuskBlocks.BIG_ORANGE_SOUL_CANDLE)
            .add(DuskBlocks.BIG_YELLOW_SOUL_CANDLE)
            .add(DuskBlocks.BIG_LIME_SOUL_CANDLE)
            .add(DuskBlocks.BIG_GREEN_SOUL_CANDLE)
            .add(DuskBlocks.BIG_CYAN_SOUL_CANDLE)
            .add(DuskBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE)
            .add(DuskBlocks.BIG_BLUE_SOUL_CANDLE)
            .add(DuskBlocks.BIG_PURPLE_SOUL_CANDLE)
            .add(DuskBlocks.BIG_MAGENTA_SOUL_CANDLE)
            .add(DuskBlocks.BIG_PINK_SOUL_CANDLE)
        getOrCreateTagBuilder(DuskBlockTags.NETHER_BRICKS)
            .forceAddTag(DuskBlockTags.CRACKED_NETHER_BRICKS)
            .forceAddTag(DuskBlockTags.POLISHED_NETHER_BRICKS)
            .add(Blocks.NETHER_BRICKS)
            .add(DuskBlocks.MIXED_NETHER_BRICKS)
            .add(Blocks.RED_NETHER_BRICKS)
            .add(DuskBlocks.BLUE_NETHER_BRICKS)
            .add(DuskBlocks.MIXED_BLUE_NETHER_BRICKS)
            .add(DuskBlocks.GREY_NETHER_BRICKS)
            .add(DuskBlocks.MIXED_GREY_NETHER_BRICKS)
        getOrCreateTagBuilder(DuskBlockTags.CRACKED_NETHER_BRICKS)
            .add(Blocks.CRACKED_NETHER_BRICKS)
            .add(DuskBlocks.CRACKED_MIXED_NETHER_BRICKS)
            .add(DuskBlocks.CRACKED_RED_NETHER_BRICKS)
            .add(DuskBlocks.CRACKED_BLUE_NETHER_BRICKS)
            .add(DuskBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS)
            .add(DuskBlocks.CRACKED_GREY_NETHER_BRICKS)
            .add(DuskBlocks.CRACKED_MIXED_GREY_NETHER_BRICKS)
        getOrCreateTagBuilder(DuskBlockTags.POLISHED_NETHER_BRICKS)
            .add(DuskBlocks.POLISHED_NETHER_BRICKS)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICKS)
            .add(DuskBlocks.POLISHED_BLUE_NETHER_BRICKS)
            .add(DuskBlocks.POLISHED_GREY_NETHER_BRICKS)
        getOrCreateTagBuilder(DuskBlockTags.WARPED_NETHER_WART_PLACEABLE)
            .addOptionalTag(id("nullium","nether_wart_placeable"))
            .add(Blocks.SOUL_SOIL)
        getOrCreateTagBuilder(DuskBlockTags.MOONBERRY_CAN_PLACE_ON)
            .add(Blocks.FARMLAND)
        getOrCreateTagBuilder(DuskBlockTags.FARMLAND_PLACES_UNDER)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(BlockTags.CROPS)
            .forceAddTag(BlockTags.SAPLINGS)
            .forceAddTag(BlockTags.FLOWERS)
            .add(Blocks.MELON)
            .add(Blocks.PUMPKIN)
            .add(Blocks.CARVED_PUMPKIN)
            .add(Blocks.JACK_O_LANTERN)
        getOrCreateTagBuilder(DuskBlockTags.REPLACEABLE_OR_DIRT)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.REPLACEABLE_BY_TREES)
            .forceAddTag(BlockTags.DIRT)
    }

    fun vanillaTags() {
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
            .add(DuskBlocks.BLUE_PETALS)
            .add(DuskBlocks.ROOT_BLOCK)
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .forceAddTag(DuskBlockTags.CASCADE_LOGS)
        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(DuskBlocks.CASCADE_PLANKS)
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
            .add(DuskBlocks.CASCADE_STAIRS)
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
            .add(DuskBlocks.CASCADE_SLAB)
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add(DuskBlocks.CASCADE_DOOR)
            .add(DuskBlocks.BLUE_DOOR)
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add(DuskBlocks.CASCADE_TRAPDOOR)
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(DuskBlocks.CASCADE_FENCE)
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(DuskBlocks.CASCADE_FENCE_GATE)
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
            .add(DuskBlocks.CASCADE_PRESSURE_PLATE)
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
            .add(DuskBlocks.CASCADE_BUTTON)
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
            .add(DuskBlocks.CASCADE_SIGN)
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
            .add(DuskBlocks.CASCADE_WALL_SIGN)
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
            .add(DuskBlocks.CASCADE_HANGING_SIGN)
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
            .add(DuskBlocks.CASCADE_WALL_HANGING_SIGN)
        getOrCreateTagBuilder(BlockTags.CROPS)
            .add(DuskBlocks.GOLDEN_BEETROOTS)
            .add(DuskBlocks.MOONBERRY_VINELET)
        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES)
            .add(DuskBlocks.MOONBERRY_VINE)
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
            .add(DuskBlocks.BLUE_PETALS)
        getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.FLOWERS)
            .add(DuskBlocks.CHERRY_LEAF_PILE)
            .add(DuskBlocks.BLUE_PETALS)
        getOrCreateTagBuilder(BlockTags.LEAVES)
            .add(DuskBlocks.CASCADE_LEAVES)
            .add(DuskBlocks.GOLDEN_BIRCH_LEAVES)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.SAPLINGS)
            .add(DuskBlocks.CASCADE_SAPLING)
            .add(DuskBlocks.GOLDEN_BIRCH_SAPLING)
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
            .add(DuskBlocks.POTTED_CASCADE_SAPLING)
            .add(DuskBlocks.POTTED_GOLDEN_BIRCH_SAPLING)
        getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
            .add(DuskBlocks.ROCKY_PODZOL, DuskBlocks.SLATED_PODZOL, DuskBlocks.BLACKSTONE_PODZOL)
            .add(DuskBlocks.ROCKY_MYCELIUM, DuskBlocks.SLATED_MYCELIUM, DuskBlocks.BLACKSTONE_MYCELIUM)
            .forceAddTag(BlockTags.LOGS)

        getOrCreateTagBuilder(BlockTags.CANDLES)
            .forceAddTag(DuskBlockTags.BIG_CANDLES)
            .forceAddTag(DuskBlockTags.SOUL_CANDLES)

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(DuskBlocks.NETHERRACK_STAIRS)
            .add(DuskBlocks.BLUE_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.GREY_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.MIXED_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.MIXED_GREY_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.POLISHED_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.POLISHED_GREY_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS)
            .add(DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS)
        getOrCreateTagBuilder(BlockTags.SLABS)
            .add(DuskBlocks.NETHERRACK_SLAB)
            .add(DuskBlocks.BLUE_NETHER_BRICK_SLAB)
            .add(DuskBlocks.GREY_NETHER_BRICK_SLAB)
            .add(DuskBlocks.MIXED_NETHER_BRICK_SLAB)
            .add(DuskBlocks.MIXED_BLUE_NETHER_BRICK_SLAB)
            .add(DuskBlocks.MIXED_GREY_NETHER_BRICK_SLAB)
            .add(DuskBlocks.POLISHED_NETHER_BRICK_SLAB)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB)
            .add(DuskBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB)
            .add(DuskBlocks.POLISHED_GREY_NETHER_BRICK_SLAB)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE_SLAB)
            .add(DuskBlocks.OVERGROWN_STONE_BRICK_SLAB)
        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(DuskBlocks.NETHERRACK_WALL)
            .add(DuskBlocks.BLUE_NETHER_BRICK_WALL)
            .add(DuskBlocks.GREY_NETHER_BRICK_WALL)
            .add(DuskBlocks.MIXED_NETHER_BRICK_WALL)
            .add(DuskBlocks.MIXED_BLUE_NETHER_BRICK_WALL)
            .add(DuskBlocks.MIXED_GREY_NETHER_BRICK_WALL)
            .add(DuskBlocks.POLISHED_NETHER_BRICK_WALL)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL)
            .add(DuskBlocks.POLISHED_BLUE_NETHER_BRICK_WALL)
            .add(DuskBlocks.POLISHED_GREY_NETHER_BRICK_WALL)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE_WALL)
            .add(DuskBlocks.OVERGROWN_STONE_BRICK_WALL)
        getOrCreateTagBuilder(BlockTags.FENCES)
            .add(DuskBlocks.BRICK_FENCE)
            .add(DuskBlocks.RED_NETHER_BRICK_FENCE)
            .add(DuskBlocks.BLUE_NETHER_BRICK_FENCE)
            .add(DuskBlocks.GREY_NETHER_BRICK_FENCE)
            .add(DuskBlocks.MIXED_NETHER_BRICK_FENCE)
            .add(DuskBlocks.MIXED_BLUE_NETHER_BRICK_FENCE)
            .add(DuskBlocks.MIXED_GREY_NETHER_BRICK_FENCE)

        getOrCreateTagBuilder(BlockTags.STONE_BRICKS)
            .add(DuskBlocks.OVERGROWN_STONE_BRICKS)

        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(DuskBlocks.ROCKY_GRAVEL, DuskBlocks.SLATED_GRAVEL, DuskBlocks.BLACKSTONE_GRAVEL)
        getOrCreateTagBuilder(BlockTags.NETHER_CARVER_REPLACEABLES)
            .add(DuskBlocks.ROCKY_SOUL_SAND, DuskBlocks.SLATED_SOUL_SAND, DuskBlocks.BLACKSTONE_SOUL_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SOIL, DuskBlocks.SLATED_SOUL_SOIL, DuskBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
            .add(DuskBlocks.ROCKY_GRAVEL, DuskBlocks.SLATED_GRAVEL, DuskBlocks.BLACKSTONE_GRAVEL)
            .add(DuskBlocks.ROCKY_SAND, DuskBlocks.SLATED_SAND, DuskBlocks.BLACKSTONE_SAND)
            .add(DuskBlocks.ROCKY_RED_SAND, DuskBlocks.SLATED_RED_SAND, DuskBlocks.BLACKSTONE_RED_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SAND, DuskBlocks.SLATED_SOUL_SAND, DuskBlocks.BLACKSTONE_SOUL_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SOIL, DuskBlocks.SLATED_SOUL_SOIL, DuskBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.DIRT)
            .add(DuskBlocks.ROCKY_DIRT, DuskBlocks.SLATED_DIRT, DuskBlocks.BLACKSTONE_DIRT)
            .add(DuskBlocks.ROCKY_GRASS, DuskBlocks.SLATED_GRASS, DuskBlocks.BLACKSTONE_GRASS)
            .add(DuskBlocks.ROCKY_PODZOL, DuskBlocks.SLATED_PODZOL, DuskBlocks.BLACKSTONE_PODZOL)
            .add(DuskBlocks.ROCKY_MYCELIUM, DuskBlocks.SLATED_MYCELIUM, DuskBlocks.BLACKSTONE_MYCELIUM)
            .add(DuskBlocks.ROCKY_COARSE_DIRT, DuskBlocks.SLATED_COARSE_DIRT, DuskBlocks.BLACKSTONE_COARSE_DIRT)
            .add(DuskBlocks.ROCKY_MUD, DuskBlocks.SLATED_MUD, DuskBlocks.BLACKSTONE_MUD)
        getOrCreateTagBuilder(BlockTags.SAND)
            .add(DuskBlocks.ROCKY_SAND, DuskBlocks.SLATED_SAND, DuskBlocks.BLACKSTONE_SAND)
            .add(DuskBlocks.ROCKY_RED_SAND, DuskBlocks.SLATED_RED_SAND, DuskBlocks.BLACKSTONE_RED_SAND)
        getOrCreateTagBuilder(BlockTags.SNOW)
            .add(DuskBlocks.ROCKY_SNOW, DuskBlocks.SLATED_SNOW, DuskBlocks.BLACKSTONE_SNOW)
        getOrCreateTagBuilder(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON)
            .add(DuskBlocks.ROCKY_MUD, DuskBlocks.SLATED_MUD, DuskBlocks.BLACKSTONE_MUD)
            .add(DuskBlocks.ROCKY_SOUL_SAND, DuskBlocks.SLATED_SOUL_SAND, DuskBlocks.BLACKSTONE_SOUL_SAND)
        getOrCreateTagBuilder(BlockTags.WITHER_SUMMON_BASE_BLOCKS)
            .add(DuskBlocks.ROCKY_SOUL_SAND, DuskBlocks.SLATED_SOUL_SAND, DuskBlocks.BLACKSTONE_SOUL_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SOIL, DuskBlocks.SLATED_SOUL_SOIL, DuskBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.SOUL_SPEED_BLOCKS)
            .add(DuskBlocks.ROCKY_SOUL_SAND, DuskBlocks.SLATED_SOUL_SAND, DuskBlocks.BLACKSTONE_SOUL_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SOIL, DuskBlocks.SLATED_SOUL_SOIL, DuskBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS)
            .add(DuskBlocks.ROCKY_SOUL_SAND, DuskBlocks.SLATED_SOUL_SAND, DuskBlocks.BLACKSTONE_SOUL_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SOIL, DuskBlocks.SLATED_SOUL_SOIL, DuskBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON)
            .add(DuskBlocks.ROCKY_GRAVEL, DuskBlocks.SLATED_GRAVEL, DuskBlocks.BLACKSTONE_GRAVEL)
        getOrCreateTagBuilder(BlockTags.VALID_SPAWN)
            .add(DuskBlocks.ROCKY_GRASS, DuskBlocks.SLATED_GRASS, DuskBlocks.BLACKSTONE_GRASS)
            .add(DuskBlocks.ROCKY_PODZOL, DuskBlocks.SLATED_PODZOL, DuskBlocks.BLACKSTONE_PODZOL)
        getOrCreateTagBuilder(BlockTags.ANIMALS_SPAWNABLE_ON)
            .add(DuskBlocks.ROCKY_GRASS, DuskBlocks.SLATED_GRASS, DuskBlocks.BLACKSTONE_GRASS)
        getOrCreateTagBuilder(BlockTags.FOXES_SPAWNABLE_ON)
            .add(DuskBlocks.ROCKY_GRASS, DuskBlocks.SLATED_GRASS, DuskBlocks.BLACKSTONE_GRASS)
            .add(DuskBlocks.ROCKY_PODZOL, DuskBlocks.SLATED_PODZOL, DuskBlocks.BLACKSTONE_PODZOL)
            .add(DuskBlocks.ROCKY_COARSE_DIRT, DuskBlocks.SLATED_COARSE_DIRT, DuskBlocks.BLACKSTONE_COARSE_DIRT)
            .add(DuskBlocks.ROCKY_SNOW, DuskBlocks.SLATED_SNOW, DuskBlocks.BLACKSTONE_SNOW)
        getOrCreateTagBuilder(BlockTags.MOOSHROOMS_SPAWNABLE_ON)
            .add(DuskBlocks.ROCKY_MYCELIUM, DuskBlocks.SLATED_MYCELIUM, DuskBlocks.BLACKSTONE_MYCELIUM)
        getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK)
            .add(DuskBlocks.ROCKY_DIRT, DuskBlocks.SLATED_DIRT, DuskBlocks.BLACKSTONE_DIRT)
            .add(DuskBlocks.ROCKY_GRASS, DuskBlocks.SLATED_GRASS, DuskBlocks.BLACKSTONE_GRASS)
            .add(DuskBlocks.ROCKY_PODZOL, DuskBlocks.SLATED_PODZOL, DuskBlocks.BLACKSTONE_PODZOL)
            .add(DuskBlocks.ROCKY_COARSE_DIRT, DuskBlocks.SLATED_COARSE_DIRT, DuskBlocks.BLACKSTONE_COARSE_DIRT)
        getOrCreateTagBuilder(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
            .add(DuskBlocks.ROCKY_MUD, DuskBlocks.SLATED_MUD, DuskBlocks.BLACKSTONE_MUD)
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
            .addAll(DuskBlocks.SWORDABLE)
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .addAll(DuskBlocks.PICKAXABLE)
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .forceAddTag(DuskBlockTags.LOG_PILES)
            .addAll(DuskBlocks.AXABLE)
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
            .addAll(DuskBlocks.SHOVELABLE)
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
            .addAll(DuskBlocks.HOEABLE)
    }

    fun conventionTags() {
        getOrCreateTagBuilder(ConventionalBlockTags.COBBLESTONES)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE)
        getOrCreateTagBuilder(ConventionalBlockTags.CHAINS)
    }
}

private fun <T> FabricTagProvider<T>.FabricTagBuilder.addAll(list: Collection<T>): FabricTagProvider<T>.FabricTagBuilder {
    list.forEach{this.add(it)}
    return this
}
