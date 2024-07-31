package org.teamvoided.dusk_autumn.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.item.Items
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.data.tags.DuskBlockTags
import org.teamvoided.dusk_autumn.data.tags.DuskItemTags
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.concurrent.CompletableFuture

class ItemTagsProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
    blockTags: BlockTagsProvider
) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture, blockTags) {
    override fun configure(arg: HolderLookup.Provider) {
        duskTags()
        vanillaTags()
        conventionTags()
    }

    fun duskTags() {
        copy(DuskBlockTags.CASCADE_LOGS, DuskItemTags.CASCADE_LOGS)
        copy(DuskBlockTags.BIG_CANDLES, DuskItemTags.BIG_CANDLES)
        copy(DuskBlockTags.SOUL_CANDLES, DuskItemTags.SOUL_CANDLES)
        copy(DuskBlockTags.BIG_SOUL_CANDLES, DuskItemTags.BIG_SOUL_CANDLES)
        copy(DuskBlockTags.NETHER_BRICKS, DuskItemTags.NETHER_BRICKS)
        copy(DuskBlockTags.CRACKED_NETHER_BRICKS, DuskItemTags.CRACKED_NETHER_BRICKS)
        copy(DuskBlockTags.POLISHED_NETHER_BRICKS, DuskItemTags.POLISHED_NETHER_BRICKS)
        getOrCreateTagBuilder(DuskItemTags.CRAFTS_GREY_NETHER_BRICKS)
            .addOptional(id("twigs","silt_ball"))
//            .addOptional(id("supplementaries","ash_pile")) what mod adds them?
            .add(Items.BASALT)
        copy(DuskBlockTags.LEAF_PILES, DuskItemTags.LEAF_PILES)

    }

    fun vanillaTags() {
        getOrCreateTagBuilder(ItemTags.DYEABLE)
            .add(DuskItems.FARMERS_HAT)

        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN)
        copy(BlockTags.PLANKS, ItemTags.PLANKS)
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS)
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS)
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES)
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES)
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES)
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS)
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS)
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES)
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS)
        copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS)
        copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS)

        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS)
        copy(BlockTags.LEAVES, ItemTags.LEAVES)
        copy(BlockTags.FLOWERS, ItemTags.FLOWERS)

        copy(BlockTags.SLABS, ItemTags.SLABS)
        copy(BlockTags.WALLS, ItemTags.WALLS)
        copy(BlockTags.STAIRS, ItemTags.STAIRS)

        copy(BlockTags.CANDLES, ItemTags.CANDLES)

        copy(BlockTags.SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS)

        copy(BlockTags.DIRT, ItemTags.DIRT)

        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(DuskItems.BLACKSTONE_SWORD)
        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(DuskItems.BLACKSTONE_PICKAXE)
        getOrCreateTagBuilder(ItemTags.AXES)
            .add(DuskItems.BLACKSTONE_AXE)
        getOrCreateTagBuilder(ItemTags.SHOVELS)
            .add(DuskItems.BLACKSTONE_SHOVEL)
        getOrCreateTagBuilder(ItemTags.HOES)
            .add(DuskItems.BLACKSTONE_HOE)
    }

    fun conventionTags() {
        copy(ConventionalBlockTags.COBBLESTONES, ConventionalItemTags.COBBLESTONES)
        copy(ConventionalBlockTags.CHAINS, ConventionalItemTags.CHAINS)

        getOrCreateTagBuilder(ConventionalItemTags.BERRY_FOODS)
            .add(DuskItems.MOONBERRIES)

        getOrCreateTagBuilder(ConventionalItemTags.MINING_TOOL_TOOLS)
            .add(DuskItems.BLACKSTONE_PICKAXE)
        getOrCreateTagBuilder(ConventionalItemTags.MINING_TOOL_TOOLS)
            .add(DuskItems.BLACKSTONE_SWORD)
            .add(DuskItems.BLACKSTONE_AXE)
    }
}