package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.*
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.data.server.loot_table.VanillaBlockLootTableGenerator.JUNGLE_SAPLING_DROP_CHANCES
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootPoolEntry
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.HolderLookup
import org.teamvoided.dusk_autumn.block.DuskBlockLists.leafPiles
import org.teamvoided.dusk_autumn.block.DuskBlockLists.logPiles
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.block.LogPileBlock
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, r) {


    val dropsItSelf = listOf(
        DuskBlocks.CASCADE_LOG,
        DuskBlocks.CASCADE_WOOD,
        DuskBlocks.STRIPPED_CASCADE_LOG,
        DuskBlocks.STRIPPED_CASCADE_WOOD,
        DuskBlocks.CASCADE_PLANKS,
        DuskBlocks.CASCADE_SLAB,
        DuskBlocks.CASCADE_FENCE,
        DuskBlocks.CASCADE_FENCE_GATE,
        DuskBlocks.CASCADE_TRAPDOOR,
        DuskBlocks.CASCADE_PRESSURE_PLATE,
        DuskBlocks.CASCADE_BUTTON,
        DuskBlocks.CASCADE_SAPLING,
        DuskBlocks.GOLDEN_BIRCH_SAPLING,

        DuskBlocks.BIG_CHAIN,
        DuskBlocks.BIG_LANTERN,
        DuskBlocks.BIG_SOUL_LANTERN,

        DuskBlocks.NETHER_BRICK_PILLAR,
        DuskBlocks.POLISHED_NETHER_BRICKS,
        DuskBlocks.POLISHED_NETHER_BRICK_STAIRS,
        DuskBlocks.POLISHED_NETHER_BRICK_WALL,

        DuskBlocks.CRACKED_RED_NETHER_BRICKS,
        DuskBlocks.RED_NETHER_BRICK_FENCE,
        DuskBlocks.CHISELED_RED_NETHER_BRICKS,
        DuskBlocks.RED_NETHER_BRICK_PILLAR,
        DuskBlocks.POLISHED_RED_NETHER_BRICKS,
        DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS,
        DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL,

        DuskBlocks.MIXED_NETHER_BRICKS,
        DuskBlocks.CRACKED_MIXED_NETHER_BRICKS,
        DuskBlocks.MIXED_NETHER_BRICK_STAIRS,
        DuskBlocks.MIXED_NETHER_BRICK_WALL,
        DuskBlocks.MIXED_NETHER_BRICK_FENCE,
        DuskBlocks.CHISELED_MIXED_NETHER_BRICKS,
        DuskBlocks.MIXED_NETHER_BRICK_PILLAR,

        DuskBlocks.OVERGROWN_COBBLESTONE,
        DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS,
        DuskBlocks.OVERGROWN_COBBLESTONE_WALL,
        DuskBlocks.OVERGROWN_STONE_BRICKS,
        DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS,
        DuskBlocks.OVERGROWN_STONE_BRICK_WALL,
        DuskBlocks.ROOT_BLOCK,

        DuskBlocks.VIOLET_DAISY,

        DuskBlocks.ROCKY_GRASS,
        DuskBlocks.ROCKY_PODZOL,
        DuskBlocks.ROCKY_MYCELIUM,
        DuskBlocks.ROCKY_DIRT_PATH,
        DuskBlocks.ROCKY_DIRT,
        DuskBlocks.ROCKY_COARSE_DIRT,
        DuskBlocks.ROCKY_MUD,
        DuskBlocks.ROCKY_SNOW,
        DuskBlocks.ROCKY_GRAVEL,
        DuskBlocks.ROCKY_SAND,
        DuskBlocks.ROCKY_RED_SAND,
        DuskBlocks.ROCKY_SOUL_SAND,
        DuskBlocks.ROCKY_SOUL_SOIL,

        DuskBlocks.SLATED_GRASS,
        DuskBlocks.SLATED_PODZOL,
        DuskBlocks.SLATED_MYCELIUM,
        DuskBlocks.SLATED_DIRT_PATH,
        DuskBlocks.SLATED_DIRT,
        DuskBlocks.SLATED_COARSE_DIRT,
        DuskBlocks.SLATED_MUD,
        DuskBlocks.SLATED_SNOW,
        DuskBlocks.SLATED_GRAVEL,
        DuskBlocks.SLATED_SAND,
        DuskBlocks.SLATED_RED_SAND,
        DuskBlocks.SLATED_SOUL_SAND,
        DuskBlocks.SLATED_SOUL_SOIL,

        DuskBlocks.BLACKSTONE_GRASS,
        DuskBlocks.BLACKSTONE_PODZOL,
        DuskBlocks.BLACKSTONE_MYCELIUM,
        DuskBlocks.BLACKSTONE_DIRT_PATH,
        DuskBlocks.BLACKSTONE_DIRT,
        DuskBlocks.BLACKSTONE_COARSE_DIRT,
        DuskBlocks.BLACKSTONE_MUD,
        DuskBlocks.BLACKSTONE_SNOW,
        DuskBlocks.BLACKSTONE_GRAVEL,
        DuskBlocks.BLACKSTONE_SAND,
        DuskBlocks.BLACKSTONE_RED_SAND,
        DuskBlocks.BLACKSTONE_SOUL_SAND,
        DuskBlocks.BLACKSTONE_SOUL_SOIL
    )
    val slabs = listOf(
        DuskBlocks.CASCADE_SLAB,
        DuskBlocks.POLISHED_NETHER_BRICK_SLAB,
        DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB,
        DuskBlocks.MIXED_NETHER_BRICK_SLAB,
        DuskBlocks.OVERGROWN_STONE_BRICK_SLAB,
        DuskBlocks.OVERGROWN_COBBLESTONE_SLAB,
    )

    override fun generate() {
        dropsItSelf.forEach(::addDrop)
        slabs.forEach(::slabDrops)
        add(DuskBlocks.CASCADE_DOOR, ::doorDrops)
        add(DuskBlocks.BLUE_DOOR, ::doorDrops)
        add(DuskBlocks.BLUE_PETALS, ::flowerbedDrops)
        add(DuskBlocks.POTTED_CASCADE_SAPLING) { pottedPlantDrops(DuskBlocks.CASCADE_SAPLING) }
        add(DuskBlocks.POTTED_GOLDEN_BIRCH_SAPLING) { pottedPlantDrops(DuskBlocks.GOLDEN_BIRCH_SAPLING) }
        add(DuskBlocks.CASCADE_LEAVES) {
            oakLeavesDrops(it, DuskBlocks.CASCADE_SAPLING, *JUNGLE_SAPLING_DROP_CHANCES)
        }
        add(DuskBlocks.GOLDEN_BIRCH_LEAVES) {
            leavesDrops(it, DuskBlocks.GOLDEN_BIRCH_SAPLING, *LEAVES_SAPLING_DROP_CHANCES)
        }
        logPiles.forEach { (pile, _) ->
            add(pile) { logPile(it) }
        }
        leafPiles.forEach { (pile, leaves) ->
            add(pile) { leafPile(it, leaves) }
        }
        add(
            DuskBlocks.MOONBERRY_VINELET,
            applyExplosionDecay(
                DuskBlocks.MOONBERRY_VINELET, LootTable.builder().pool(
                    LootPool.builder().with(ItemEntry.builder(DuskItems.MOONBERRY_VINELET))
                )
            )
        )
        add(DuskBlocks.WILD_WHEAT) { block: Block ->
            this.dropsWithPropertyValue(
                block,
                TallPlantBlock.HALF,
                DoubleBlockHalf.LOWER
            )
        }
        add(
            DuskBlocks.GOLDEN_BEETROOTS,
            this.cropDrops(
                DuskBlocks.GOLDEN_BEETROOTS,
                DuskItems.GOLDEN_BEETROOT,
                DuskItems.GOLDEN_BEETROOT,
                BlockStatePropertyLootCondition.builder(Blocks.BEETROOTS).properties(
                    StatePredicate.Builder.create().exactMatch(BeetrootsBlock.AGE, 3)
                )
            )
        )
        add(DuskBlocks.POTTED_VIOLET_DAISY) { pottedPlantDrops(DuskBlocks.VIOLET_DAISY) }
    }

    private fun constantLootNumber(i: Number): ConstantLootNumberProvider =
        ConstantLootNumberProvider.create(i.toFloat())

    fun logPile(drop: Block): LootTable.Builder {
        return LootTable.builder().pool(
            LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
                applyExplosionDecay(drop, ItemEntry.builder(drop).apply(
                    listOf(2, 3, 4)
                ) { count: Int ->
                    SetCountLootFunction.builder(
                        ConstantLootNumberProvider.create(count.toFloat())
                    ).conditionally(
                        BlockStatePropertyLootCondition.builder(drop).properties(
                            StatePredicate.Builder.create().exactMatch(LogPileBlock.PILE_LAYERS, count)
                        )
                    )
                })
            )
        )
    }

    fun leafPile(pile: Block, leaves: Block): LootTable.Builder {
        return LootTable.builder().pool(
            LootPool.builder().with(
                AlternativeEntry.builder(
                    AlternativeEntry.builder(LeafPileBlock.PILE_LAYERS.values) { layers ->
                        if (layers == 4) LootTableEntry.method_428(leaves.lootTableId)
                        else
                            applyExplosionDecay(
                                leaves,
                                ItemEntry.builder(Items.STICK)
                                    .apply(
                                        SetCountLootFunction.builder(
                                            UniformLootNumberProvider.create(
                                                0.0f,
                                                layers * (2.0f / 3.0f)
                                            )
                                        )
                                    )
                            ).conditionally(
                                BlockStatePropertyLootCondition.builder(pile).properties(
                                    StatePredicate.Builder.create().exactMatch(LeafPileBlock.PILE_LAYERS, layers)
                                )
                            )
                    },
                    AlternativeEntry.builder(LeafPileBlock.PILE_LAYERS.values) { layers ->
                        if (layers == 4) LootTableEntry.method_428(leaves.lootTableId)
                        else
                            ItemEntry.builder(pile)
                                .apply(SetCountLootFunction.builder(constantLootNumber(layers)))
                                .conditionally(
                                    BlockStatePropertyLootCondition.builder(pile).properties(
                                        StatePredicate.Builder.create().exactMatch(LeafPileBlock.PILE_LAYERS, layers)
                                    )
                                )
                    }
                        .conditionally(this.method_60392())
                )
            )
        )
    }
}