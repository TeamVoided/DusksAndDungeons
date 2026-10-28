package org.teamvoided.dusks_and_dungeons.datagen.data.tag.block

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.data.tags.c.CBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.voidlib.devin.provider.tag.DBlockTagProvider

object SandTags {

    fun createTags(provider: DBlockTagProvider): Any = with(provider) {
        // region Sand
        getOrCreateTagBuilder(BlockTags.SAND)
            .add(DnDBlocks.SUSPICIOUS_RED_SAND)
            .add(DnDBlocks.AZURINE_SAND)
            .add(DnDBlocks.SUSPICIOUS_AZURINE_SAND)

        getOrCreateTagBuilder(ConventionalBlockTags.SANDS)
            .forceAddTag(CBlockTags.TINTED_SANDS)
        getOrCreateTagBuilder(CBlockTags.TINTED_SANDS)
            .add(DnDBlocks.AZURINE_SAND)

        getOrCreateTagBuilder(BlockTags.SMELTS_TO_GLASS)
            .add(DnDBlocks.AZURINE_SAND)

        getOrCreateTagBuilder(BlockTags.LUSH_GROUND_REPLACEABLE)
            .add(DnDBlocks.AZURINE_SAND)

        getOrCreateTagBuilder(BlockTags.AZALEA_ROOT_REPLACEABLE)
            .add(DnDBlocks.AZURINE_SAND)
        // endregion

        // All
        getOrCreateTagBuilder(CBlockTags.SANDSTONE_WALLS)
            .forceAddTag(CBlockTags.UNCOLORED_SANDSTONE_WALLS)
            .forceAddTag(CBlockTags.RED_SANDSTONE_WALLS)
            .forceAddTag(CBlockTags.TINTED_SANDSTONE_WALLS)


        // region Uncolored
        getOrCreateTagBuilder(ConventionalBlockTags.UNCOLORED_SANDSTONE_BLOCKS)
            .add(
                DnDBlocks.ROUGH_SANDSTONE.parent,
                DnDBlocks.POLISHED_SANDSTONE.parent,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.UNCOLORED_SANDSTONE_SLABS)
            .add(
                DnDBlocks.ROUGH_SANDSTONE.slab,
                DnDBlocks.POLISHED_SANDSTONE.slab,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.UNCOLORED_SANDSTONE_STAIRS)
            .add(
                DnDBlocks.CUT_SANDSTONE_STAIR,
                DnDBlocks.ROUGH_SANDSTONE.stairs,
                DnDBlocks.POLISHED_SANDSTONE.stairs,
            )
        getOrCreateTagBuilder(CBlockTags.UNCOLORED_SANDSTONE_WALLS)
            .add(
                Blocks.SANDSTONE_WALL,
                DnDBlocks.SMOOTH_SANDSTONE_WALL,
                DnDBlocks.CUT_SANDSTONE_WALL,
                DnDBlocks.ROUGH_SANDSTONE.wall,
                DnDBlocks.POLISHED_SANDSTONE.wall,
            )
        // endregion

        // region Red
        getOrCreateTagBuilder(ConventionalBlockTags.RED_SANDSTONE_BLOCKS)
            .add(
                DnDBlocks.ROUGH_RED_SANDSTONE.parent,
                DnDBlocks.POLISHED_RED_SANDSTONE.parent,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.RED_SANDSTONE_SLABS)
            .add(
                DnDBlocks.ROUGH_RED_SANDSTONE.slab,
                DnDBlocks.POLISHED_RED_SANDSTONE.slab,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.RED_SANDSTONE_STAIRS)
            .add(
                DnDBlocks.CUT_RED_SANDSTONE_STAIR,
                DnDBlocks.ROUGH_RED_SANDSTONE.stairs,
                DnDBlocks.POLISHED_RED_SANDSTONE.stairs,
            )
        getOrCreateTagBuilder(CBlockTags.RED_SANDSTONE_WALLS)
            .add(
                Blocks.RED_SANDSTONE_WALL,
                DnDBlocks.SMOOTH_RED_SANDSTONE_WALL,
                DnDBlocks.CUT_RED_SANDSTONE_WALL,
                DnDBlocks.ROUGH_RED_SANDSTONE.wall,
                DnDBlocks.POLISHED_RED_SANDSTONE.wall,
            )
        // endregion

        // region Azure


        getOrCreateTagBuilder(ConventionalBlockTags.SANDSTONE_BLOCKS).forceAddTag(CBlockTags.TINTED_SANDSTONE_BLOCKS)
        getOrCreateTagBuilder(CBlockTags.TINTED_SANDSTONE_BLOCKS)
            .add(
                DnDBlocks.AZURINE_SANDSTONE.parent,
                DnDBlocks.CHISELED_AZURINE_SANDSTONE,
                DnDBlocks.SMOOTH_AZURINE_SANDSTONE.parent,
                DnDBlocks.CUT_AZURINE_SANDSTONE.parent,
                DnDBlocks.ROUGH_AZURINE_SANDSTONE.parent,
                DnDBlocks.POLISHED_AZURINE_SANDSTONE.parent,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.SANDSTONE_STAIRS).forceAddTag(CBlockTags.TINTED_SANDSTONE_STAIRS)
        getOrCreateTagBuilder(CBlockTags.TINTED_SANDSTONE_STAIRS)
            .add(
                DnDBlocks.AZURINE_SANDSTONE.stairs,
                DnDBlocks.SMOOTH_AZURINE_SANDSTONE.stairs,
                DnDBlocks.CUT_AZURINE_SANDSTONE.stairs,
                DnDBlocks.ROUGH_AZURINE_SANDSTONE.stairs,
                DnDBlocks.POLISHED_AZURINE_SANDSTONE.stairs,
            )
        getOrCreateTagBuilder(ConventionalBlockTags.SANDSTONE_SLABS).forceAddTag(CBlockTags.TINTED_SANDSTONE_SLABS)
        getOrCreateTagBuilder(CBlockTags.TINTED_SANDSTONE_SLABS)
            .add(
                DnDBlocks.AZURINE_SANDSTONE.slab,
                DnDBlocks.SMOOTH_AZURINE_SANDSTONE.slab,
                DnDBlocks.CUT_AZURINE_SANDSTONE.slab,
                DnDBlocks.ROUGH_AZURINE_SANDSTONE.slab,
                DnDBlocks.POLISHED_AZURINE_SANDSTONE.slab,
            )
        getOrCreateTagBuilder(CBlockTags.TINTED_SANDSTONE_WALLS)
            .add(
                DnDBlocks.AZURINE_SANDSTONE.wall,
                DnDBlocks.SMOOTH_AZURINE_SANDSTONE.wall,
                DnDBlocks.CUT_AZURINE_SANDSTONE.wall,
                DnDBlocks.ROUGH_AZURINE_SANDSTONE.wall,
                DnDBlocks.POLISHED_AZURINE_SANDSTONE.wall,
            )
        // endregion

        // Misc
        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(
                DnDBlocks.AZURINE_SANDSTONE.parent,
                DnDBlocks.ROUGH_SANDSTONE.parent,
                DnDBlocks.ROUGH_RED_SANDSTONE.parent,
                DnDBlocks.ROUGH_AZURINE_SANDSTONE.parent
            )
        getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
            .add(
                DnDBlocks.AZURINE_SAND,
                DnDBlocks.AZURINE_SANDSTONE.parent,
                DnDBlocks.ROUGH_SANDSTONE.parent,
                DnDBlocks.ROUGH_RED_SANDSTONE.parent,
                DnDBlocks.ROUGH_AZURINE_SANDSTONE.parent,
            )
    }

}