package org.teamvoided.dusk_autumn.datagen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.dusk_autumn.data.DuskBlockTags
import org.teamvoided.dusk_autumn.init.DuskBlocks
import java.util.concurrent.CompletableFuture

class BlockTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider) {

        getOrCreateTagBuilder(DuskBlockTags.CASCADE_LOGS)
            .add(DuskBlocks.CASCADE_LOG)
            .add(DuskBlocks.STRIPPED_CASCADE_LOG)
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
            .forceAddTag(BlockTags.STONE_BRICKS)
            .forceAddTag(BlockTags.LOGS)
            .add(Blocks.MELON)
            .add(Blocks.PUMPKIN)
            .add(Blocks.CARVED_PUMPKIN)
            .add(Blocks.JACK_O_LANTERN)
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

//VANILLA
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
            .add(DuskBlocks.BLUE_PETALS)
        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add(DuskBlocks.CASCADE_PLANKS)
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add(DuskBlocks.CASCADE_DOOR)
            .add(DuskBlocks.BLUE_DOOR)
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add(DuskBlocks.CASCADE_TRAPDOOR)
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .forceAddTag(DuskBlockTags.CASCADE_LOGS)
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
            .add(DuskBlocks.BLUE_PETALS)
        getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.FLOWERS)
            .add(DuskBlocks.BLUE_PETALS)
        getOrCreateTagBuilder(BlockTags.LEAVES)
            .add(DuskBlocks.CASCADE_LEAVES)
            .forceAddTag(DuskBlockTags.LEAF_PILES)
        getOrCreateTagBuilder(BlockTags.SAPLINGS)
            .add(DuskBlocks.CASCADE_SAPLING)
            .add(DuskBlocks.GOLDEN_BIRCH_SAPLING)
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
            .add(DuskBlocks.POTTED_CASCADE_SAPLING)
            .add(DuskBlocks.POTTED_GOLDEN_BIRCH_SAPLING)
        getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
            .forceAddTag(BlockTags.LOGS)

        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
            .add(DuskBlocks.BLUE_PETALS)
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
            .add(DuskBlocks.BLUE_PETALS)
    }
}