package org.teamvoided.dusk_autumn.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.dusk_autumn.data.tags.DuskBlockTags
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

        getOrCreateTagBuilder(BlockTags.STONE_BRICKS)
            .add(DuskBlocks.OVERGROWN_STONE_BRICKS)

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS)
            .add(DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS)
            .add(DuskBlocks.POLISHED_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS)
        getOrCreateTagBuilder(BlockTags.SLABS)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE_SLAB)
            .add(DuskBlocks.OVERGROWN_STONE_BRICK_SLAB)
            .add(DuskBlocks.POLISHED_NETHER_BRICK_SLAB)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB)
        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE_WALL)
            .add(DuskBlocks.OVERGROWN_STONE_BRICK_WALL)
            .add(DuskBlocks.POLISHED_NETHER_BRICK_WALL)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL)

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
            .add(DuskBlocks.BLUE_PETALS)
            .add(DuskBlocks.MOONBERRY_VINE)
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(DuskBlocks.BIG_CHAIN)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE)
            .add(DuskBlocks.OVERGROWN_STONE_BRICKS)
            .add(DuskBlocks.POLISHED_NETHER_BRICKS)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICKS)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS)
            .add(DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS)
            .add(DuskBlocks.POLISHED_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE_SLAB)
            .add(DuskBlocks.OVERGROWN_STONE_BRICK_SLAB)
            .add(DuskBlocks.POLISHED_NETHER_BRICK_SLAB)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE_WALL)
            .add(DuskBlocks.OVERGROWN_STONE_BRICK_WALL)
            .add(DuskBlocks.POLISHED_NETHER_BRICK_WALL)
            .add(DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL)
            .add(DuskBlocks.ROCKY_DIRT, DuskBlocks.SLATED_DIRT, DuskBlocks.BLACKSTONE_DIRT)
            .add(DuskBlocks.ROCKY_GRASS, DuskBlocks.SLATED_GRASS, DuskBlocks.BLACKSTONE_GRASS)
            .add(DuskBlocks.ROCKY_PODZOL, DuskBlocks.SLATED_PODZOL, DuskBlocks.BLACKSTONE_PODZOL)
            .add(DuskBlocks.ROCKY_MYCELIUM, DuskBlocks.SLATED_MYCELIUM, DuskBlocks.BLACKSTONE_MYCELIUM)
            .add(DuskBlocks.ROCKY_DIRT_PATH, DuskBlocks.SLATED_DIRT_PATH, DuskBlocks.BLACKSTONE_DIRT_PATH)
            .add(DuskBlocks.ROCKY_COARSE_DIRT, DuskBlocks.SLATED_COARSE_DIRT, DuskBlocks.BLACKSTONE_COARSE_DIRT)
            .add(DuskBlocks.ROCKY_MUD, DuskBlocks.SLATED_MUD, DuskBlocks.BLACKSTONE_MUD)
            .add(DuskBlocks.ROCKY_SNOW, DuskBlocks.SLATED_SNOW, DuskBlocks.BLACKSTONE_SNOW)
            .add(DuskBlocks.ROCKY_GRAVEL, DuskBlocks.SLATED_GRAVEL, DuskBlocks.BLACKSTONE_GRAVEL)
            .add(DuskBlocks.ROCKY_SAND, DuskBlocks.SLATED_SAND, DuskBlocks.BLACKSTONE_SAND)
            .add(DuskBlocks.ROCKY_RED_SAND, DuskBlocks.SLATED_RED_SAND, DuskBlocks.BLACKSTONE_RED_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SAND, DuskBlocks.SLATED_SOUL_SAND, DuskBlocks.BLACKSTONE_SOUL_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SOIL, DuskBlocks.SLATED_SOUL_SOIL, DuskBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
            .add(DuskBlocks.ROCKY_DIRT, DuskBlocks.SLATED_DIRT, DuskBlocks.BLACKSTONE_DIRT)
            .add(DuskBlocks.ROCKY_GRASS, DuskBlocks.SLATED_GRASS, DuskBlocks.BLACKSTONE_GRASS)
            .add(DuskBlocks.ROCKY_PODZOL, DuskBlocks.SLATED_PODZOL, DuskBlocks.BLACKSTONE_PODZOL)
            .add(DuskBlocks.ROCKY_MYCELIUM, DuskBlocks.SLATED_MYCELIUM, DuskBlocks.BLACKSTONE_MYCELIUM)
            .add(DuskBlocks.ROCKY_DIRT_PATH, DuskBlocks.SLATED_DIRT_PATH, DuskBlocks.BLACKSTONE_DIRT_PATH)
            .add(DuskBlocks.ROCKY_COARSE_DIRT, DuskBlocks.SLATED_COARSE_DIRT, DuskBlocks.BLACKSTONE_COARSE_DIRT)
            .add(DuskBlocks.ROCKY_MUD, DuskBlocks.SLATED_MUD, DuskBlocks.BLACKSTONE_MUD)
            .add(DuskBlocks.ROCKY_SNOW, DuskBlocks.SLATED_SNOW, DuskBlocks.BLACKSTONE_SNOW)
            .add(DuskBlocks.ROCKY_GRAVEL, DuskBlocks.SLATED_GRAVEL, DuskBlocks.BLACKSTONE_GRAVEL)
            .add(DuskBlocks.ROCKY_SAND, DuskBlocks.SLATED_SAND, DuskBlocks.BLACKSTONE_SAND)
            .add(DuskBlocks.ROCKY_RED_SAND, DuskBlocks.SLATED_RED_SAND, DuskBlocks.BLACKSTONE_RED_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SAND, DuskBlocks.SLATED_SOUL_SAND, DuskBlocks.BLACKSTONE_SOUL_SAND)
            .add(DuskBlocks.ROCKY_SOUL_SOIL, DuskBlocks.SLATED_SOUL_SOIL, DuskBlocks.BLACKSTONE_SOUL_SOIL)
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
            .add(DuskBlocks.BLUE_PETALS)
            .add(DuskBlocks.MOONBERRY_VINE)
    }

    fun conventionTags() {
        getOrCreateTagBuilder(ConventionalBlockTags.COBBLESTONES)
            .add(DuskBlocks.OVERGROWN_COBBLESTONE)
    }
}