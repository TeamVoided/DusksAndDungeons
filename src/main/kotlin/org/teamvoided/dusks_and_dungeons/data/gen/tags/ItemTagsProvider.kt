package org.teamvoided.dusks_and_dungeons.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.data.tags.c.CBlockTags
import org.teamvoided.dusks_and_dungeons.data.tags.c.CItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.voidlib.devin.extensions.tag.copyColorTags
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class ItemTagsProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
    blockTags: BlockTagsProvider,
) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture, blockTags) {
    override fun addTags(arg: HolderLookup.Provider) {
        duskTags()
        woodTags()
        vanillaTags()
        conventionTags()

        getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS).remove(Items.BLACKSTONE)
        getOrCreateTagBuilder(DnDItemTags.BLACKSTONE_TOOL_MATERIALS).add(Items.BLACKSTONE)
    }

    fun duskTags() {
        copy(DnDBlockTags.CASCADE_LOGS, DnDItemTags.CASCADE_LOGS)
        copy(DnDBlockTags.VERDANT_LOGS, DnDItemTags.VERDANT_LOGS)
        copy(DnDBlockTags.LEAF_PILES, DnDItemTags.LEAF_PILES)
        copy(DnDBlockTags.LOG_PILES, DnDItemTags.LOG_PILES)
        copy(DnDBlockTags.LOG_PILES_THAT_BURN, DnDItemTags.LOG_PILES_THAT_BURN)
        copy(DnDBlockTags.FLOWERBEDS, DnDItemTags.FLOWERBEDS)
        copy(DnDBlockTags.VIVIONBEDS, DnDItemTags.VIVIONBEDS)
        copy(DnDBlockTags.BIG_CANDLES, DnDItemTags.BIG_CANDLES)
        copy(DnDBlockTags.SOUL_CANDLES, DnDItemTags.SOUL_CANDLES)
        copy(DnDBlockTags.CANDELABRAS, DnDItemTags.CANDELABRAS)
        copy(DnDBlockTags.SOUL_CANDELABRAS, DnDItemTags.SOUL_CANDELABRAS)
        copy(DnDBlockTags.BIG_SOUL_CANDLES, DnDItemTags.BIG_SOUL_CANDLES)
        copy(DnDBlockTags.GRAVESTONES, DnDItemTags.GRAVESTONES)
        copy(DnDBlockTags.SMALL_GRAVESTONES, DnDItemTags.SMALL_GRAVESTONES)
        copy(DnDBlockTags.HEADSTONES, DnDItemTags.HEADSTONES)
        copy(DnDBlockTags.NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)
        copy(DnDBlockTags.CRACKED_NETHER_BRICKS, DnDItemTags.CRACKED_NETHER_BRICKS)
        copy(DnDBlockTags.POLISHED_NETHER_BRICKS, DnDItemTags.POLISHED_NETHER_BRICKS)

        copy(DnDBlockTags.CORN_STORAGE, DnDItemTags.CORN_STORAGE)
        copy(DnDBlockTags.PUMPKINS, DnDItemTags.PUMPKINS)
        copy(DnDBlockTags.CARVED_PUMPKINS, DnDItemTags.CARVED_PUMPKINS)
        copy(DnDBlockTags.GLOWING_PUMPKINS, DnDItemTags.GLOWING_PUMPKINS)
        copy(DnDBlockTags.SMALL_PUMPKINS, DnDItemTags.SMALL_PUMPKINS)
        copy(DnDBlockTags.SMALL_CARVED_PUMPKINS, DnDItemTags.SMALL_CARVED_PUMPKINS)
        copy(DnDBlockTags.SMALL_GLOWING_PUMPKINS, DnDItemTags.SMALL_GLOWING_PUMPKINS)

        getOrCreateTagBuilder(DnDItemTags.CRAFTS_WARPED_NETHER_BRICKS)
            .add(DnDBlocks.WARPED_WART.asItem())
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

        getOrCreateTagBuilder(DnDItemTags.PUMPKIN_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(DnDItems.LANTERN_PUMPKIN_SEEDS)
            .add(DnDItems.PALE_PUMPKIN_SEEDS)
            .add(DnDItems.MOSSKIN_PUMPKIN_SEEDS)
            .add(DnDItems.GLOOM_PUMPKIN_SEEDS)

        getOrCreateTagBuilder(DnDItemTags.RACCOON_FOOD)
            .add(Items.SWEET_BERRIES)
            .add(Items.GLOW_BERRIES)
    }

    private fun woodTags() {
        copy(DnDBlockTags.HOLLOW_LOGS, DnDItemTags.HOLLOW_LOGS)
        copy(DnDBlockTags.HOLLOW_LOGS_THAT_BURN, DnDItemTags.HOLLOW_LOGS_THAT_BURN)

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).forceAddTag(DnDItemTags.WOOD_STAIRS)
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).forceAddTag(DnDItemTags.WOOD_SLABS)
        getOrCreateTagBuilder(ItemTags.WALLS).forceAddTag(DnDItemTags.WOODEN_WALLS)
        copy(DnDBlockTags.WOODEN_WALLS, DnDItemTags.WOODEN_WALLS)
        copy(DnDBlockTags.PLANK_WALLS, DnDItemTags.PLANK_WALLS)
        copy(DnDBlockTags.PLANK_WALLS_THAT_BURN, DnDItemTags.PLANK_WALLS_THAT_BURN)

        copy(DnDBlockTags.WOOD_STAIRS, DnDItemTags.WOOD_STAIRS)
        copy(DnDBlockTags.WOOD_SLABS, DnDItemTags.WOOD_SLABS)
        copy(DnDBlockTags.WOOD_WALLS, DnDItemTags.WOOD_WALLS)

        copy(DnDBlockTags.WOOD_STAIRS_THAT_BURN, DnDItemTags.WOOD_STAIRS_THAT_BURN)
        copy(DnDBlockTags.WOOD_SLABS_THAT_BURN, DnDItemTags.WOOD_SLABS_THAT_BURN)
        copy(DnDBlockTags.WOOD_WALLS_THAT_BURN, DnDItemTags.WOOD_WALLS_THAT_BURN)

        getOrCreateTagBuilder(ItemTags.NON_FLAMMABLE_WOOD)
            .add(
                DnDBlocks.CRIMSON_HYPHAE.stairs.asItem(),
                DnDBlocks.CRIMSON_HYPHAE.slab.asItem(),
                DnDBlocks.CRIMSON_HYPHAE.wall.asItem(),
                DnDBlocks.CRIMSON_STEM_PILE.asItem(),
                DnDBlocks.STRIPPED_CRIMSON_HYPHAE.stairs.asItem(),
                DnDBlocks.STRIPPED_CRIMSON_HYPHAE.slab.asItem(),
                DnDBlocks.STRIPPED_CRIMSON_HYPHAE.wall.asItem(),
                DnDBlocks.STRIPPED_CRIMSON_STEM_PILE.asItem(),
                DnDBlocks.CRIMSON_WALL.asItem(),
                DnDBlocks.HOLLOW_CRIMSON_STEM.asItem(),
                DnDBlocks.HOLLOW_STRIPPED_CRIMSON_STEM.asItem(),

                DnDBlocks.WARPED_HYPHAE.stairs.asItem(),
                DnDBlocks.WARPED_HYPHAE.slab.asItem(),
                DnDBlocks.WARPED_HYPHAE.wall.asItem(),
                DnDBlocks.WARPED_STEM_PILE.asItem(),
                DnDBlocks.STRIPPED_WARPED_HYPHAE.stairs.asItem(),
                DnDBlocks.STRIPPED_WARPED_HYPHAE.slab.asItem(),
                DnDBlocks.STRIPPED_WARPED_HYPHAE.wall.asItem(),
                DnDBlocks.STRIPPED_WARPED_STEM_PILE.asItem(),
                DnDBlocks.WARPED_WALL.asItem(),
                DnDBlocks.HOLLOW_WARPED_STEM.asItem(),
                DnDBlocks.HOLLOW_STRIPPED_WARPED_STEM.asItem(),
            )

    }

    fun vanillaTags() {
        vanillaEnchantingTags()

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
            .add(DnDBlocks.CASCADE_LEAVES.asItem())
            .add(DnDBlocks.GOLDEN_BIRCH_LEAVES.asItem())
        copy(BlockTags.FLOWERS, ItemTags.FLOWERS)

        copy(BlockTags.STAIRS, ItemTags.STAIRS)
        copy(BlockTags.SLABS, ItemTags.SLABS)
        copy(BlockTags.WALLS, ItemTags.WALLS)
        copy(BlockTags.FENCES, ItemTags.FENCES)

        copy(BlockTags.CANDLES, ItemTags.CANDLES)

        copy(BlockTags.SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS)

        copy(BlockTags.DIRT, ItemTags.DIRT)

        copy(BlockTags.STONE_BRICKS, ItemTags.STONE_BRICKS)

        getOrCreateTagBuilder(ItemTags.DYEABLE)
            .add(DnDItems.FARMERS_HAT)

        getOrCreateTagBuilder(ItemTags.PARROT_FOOD)
            .add(DnDItems.CORN_KERNELS)
            .forceAddTag(DnDItemTags.PUMPKIN_SEEDS)
        getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD)
            .add(DnDItems.CORN_KERNELS)
            .forceAddTag(DnDItemTags.PUMPKIN_SEEDS)
        getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS)
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

    fun vanillaEnchantingTags() {
        getOrCreateTagBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE)
            .forceAddTag(DnDItemTags.CARVED_PUMPKINS)
            .forceAddTag(DnDItemTags.SMALL_CARVED_PUMPKINS)
            .add(DnDItems.FARMERS_HAT)
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
            .forceAddTag(DnDItemTags.CARVED_PUMPKINS)
            .forceAddTag(DnDItemTags.SMALL_CARVED_PUMPKINS)
            .add(DnDItems.FARMERS_HAT)
    }

    fun conventionTags() {
        copyColorTags(::copy)
        copy(ConventionalBlockTags.COBBLESTONES, ConventionalItemTags.COBBLESTONES)
        copy(ConventionalBlockTags.CHAINS, ConventionalItemTags.CHAINS)
        copy(CBlockTags.SCAFFOLDING, CItemTags.SCAFFOLDING)

        getOrCreateTagBuilder(ConventionalItemTags.STORAGE_BLOCKS)
            .forceAddTag(DnDItemTags.CORN_STORAGE)

        getOrCreateTagBuilder(ConventionalItemTags.PUMPKIN_CROPS)
            .forceAddTag(DnDItemTags.PUMPKINS)
            .forceAddTag(DnDItemTags.SMALL_PUMPKINS)
        getOrCreateTagBuilder(ConventionalItemTags.VEGETABLE_FOODS)
            .add(DnDItems.CORN)
        getOrCreateTagBuilder(ConventionalItemTags.BERRY_FOODS)
            .add(DnDItems.MOONBERRIES)
        getOrCreateTagBuilder(ConventionalItemTags.FOODS)
            .add(DnDItems.CORN_SYRUP_BOTTLE)

        getOrCreateTagBuilder(ConventionalItemTags.MINING_TOOL_TOOLS)
            .add(DnDItems.BLACKSTONE_PICKAXE)
        getOrCreateTagBuilder(ConventionalItemTags.MELEE_WEAPON_TOOLS)
            .add(DnDItems.BLACKSTONE_SWORD)
            .add(DnDItems.BLACKSTONE_AXE)

        getOrCreateTagBuilder(ConventionalItemTags.MUSHROOMS)
            .add(DnDBlocks.GOLDEN_MUSHROOM.asItem())
    }
}
