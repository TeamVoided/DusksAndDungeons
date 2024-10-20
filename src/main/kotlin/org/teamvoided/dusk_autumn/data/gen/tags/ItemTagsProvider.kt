package org.teamvoided.dusk_autumn.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.item.Items
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.ItemTags
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags
import org.teamvoided.dusk_autumn.data.tags.DnDItemTags
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.util.SECRET_ITEMS
import org.teamvoided.dusk_autumn.util.addAll
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
        copy(DnDBlockTags.CASCADE_LOGS, DnDItemTags.CASCADE_LOGS)
        copy(DnDBlockTags.LEAF_PILES, DnDItemTags.LEAF_PILES)
        copy(DnDBlockTags.LOG_PILES, DnDItemTags.LOG_PILES)
        copy(DnDBlockTags.LOG_PILES_THAT_BURN, DnDItemTags.LOG_PILES_THAT_BURN)
        copy(DnDBlockTags.FLOWERBEDS, DnDItemTags.FLOWERBEDS)
        copy(DnDBlockTags.VIVIONBEDS, DnDItemTags.VIVIONBEDS)
        copy(DnDBlockTags.BIG_CANDLES, DnDItemTags.BIG_CANDLES)
        copy(DnDBlockTags.SOUL_CANDLES, DnDItemTags.SOUL_CANDLES)
        copy(DnDBlockTags.BIG_SOUL_CANDLES, DnDItemTags.BIG_SOUL_CANDLES)
        copy(DnDBlockTags.GRAVESTONES, DnDItemTags.GRAVESTONES)
        copy(DnDBlockTags.SMALL_GRAVESTONES, DnDItemTags.SMALL_GRAVESTONES)
        copy(DnDBlockTags.HEADSTONES, DnDItemTags.HEADSTONES)
        copy(DnDBlockTags.NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)
        copy(DnDBlockTags.CRACKED_NETHER_BRICKS, DnDItemTags.CRACKED_NETHER_BRICKS)
        copy(DnDBlockTags.POLISHED_NETHER_BRICKS, DnDItemTags.POLISHED_NETHER_BRICKS)
        getOrCreateTagBuilder(DnDItemTags.CRAFTS_WARPED_NETHER_BRICKS)
            .add(DnDFloraBlocks.WARPED_WART.asItem())
        getOrCreateTagBuilder(DnDItemTags.CRAFTS_ASHEN_NETHER_BRICKS)
            .addOptional(id("supplementaries", "ash_pile"))
            .add(Items.BASALT)
        getOrCreateTagBuilder(DnDItemTags.SCARECROW_WOOD_ITEMS)
            .add(Items.OAK_PLANKS)
            .add(Items.DARK_OAK_PLANKS)
        getOrCreateTagBuilder(DnDItemTags.SCARECROW_BALE_ITEMS)
            .add(Items.HAY_BLOCK)
        getOrCreateTagBuilder(DnDItemTags.SCARECROW_HEAD_ITEMS)
            .add(Items.HAY_BLOCK)
        getOrCreateTagBuilder(DnDItemTags.SCARECROW_CLOTHES_ITEMS)
            .add(Items.LEATHER)
    }

    fun vanillaTags() {

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
        getOrCreateTagBuilder(ItemTags.LEAVES)
            .add(DnDWoodBlocks.CASCADE_LEAVES.asItem())
            .add(DnDWoodBlocks.GOLDEN_BIRCH_LEAVES.asItem())
        copy(BlockTags.FLOWERS, ItemTags.FLOWERS)

        copy(BlockTags.STAIRS, ItemTags.STAIRS)
        copy(BlockTags.SLABS, ItemTags.SLABS)
        copy(BlockTags.WALLS, ItemTags.WALLS)
        copy(BlockTags.FENCES, ItemTags.FENCES)

        copy(BlockTags.CANDLES, ItemTags.CANDLES)

        copy(BlockTags.SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS)

        copy(BlockTags.DIRT, ItemTags.DIRT)


        getOrCreateTagBuilder(ItemTags.DYEABLE)
            .add(DnDItems.FARMERS_HAT)
            .add(DnDItems.DIE_ITEM)

//        getOrCreateTagBuilder(ItemTags.)
//            .add(DnDItems.CORN_KERNELS)
        getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD)
            .add(DnDItems.CORN_KERNELS)


        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(DnDItems.BLACKSTONE_SWORD)
        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(DnDItems.BLACKSTONE_PICKAXE)
        getOrCreateTagBuilder(ItemTags.AXES)
            .add(DnDItems.BLACKSTONE_AXE)
        getOrCreateTagBuilder(ItemTags.SHOVELS)
            .add(DnDItems.BLACKSTONE_SHOVEL)
        getOrCreateTagBuilder(ItemTags.HOES)
            .add(DnDItems.BLACKSTONE_HOE)
    }

    fun conventionTags() {
        copy(ConventionalBlockTags.COBBLESTONES, ConventionalItemTags.COBBLESTONES)
        copy(ConventionalBlockTags.CHAINS, ConventionalItemTags.CHAINS)

        getOrCreateTagBuilder(ConventionalItemTags.BERRY_FOODS)
            .add(DnDItems.MOONBERRIES)

        getOrCreateTagBuilder(ConventionalItemTags.MINING_TOOL_TOOLS)
            .add(DnDItems.BLACKSTONE_PICKAXE)
        getOrCreateTagBuilder(ConventionalItemTags.MELEE_WEAPON_TOOLS)
            .add(DnDItems.BLACKSTONE_SWORD)
            .add(DnDItems.BLACKSTONE_AXE)

        getOrCreateTagBuilder(ConventionalItemTags.PIE_FOODS)
            .add(DnDItems.LANTERN_PUMPKIN_PIE)
            .add(DnDItems.MOSSKIN_PUMPKIN_PIE)
            .add(DnDItems.PALE_PUMPKIN_PIE)
            .add(DnDItems.GLOOM_PUMPKIN_PIE)

        getOrCreateTagBuilder(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS)
            .addAll(DnDItems.EVIL_ITEMS)
            .addAll(SECRET_ITEMS)
    }
}
