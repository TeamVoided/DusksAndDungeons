package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.BeetrootsBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.DoorBlock
import net.minecraft.block.FlowerPotBlock
import net.minecraft.block.SlabBlock
import net.minecraft.block.TallPlantBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.data.server.loot_table.VanillaBlockLootTableGenerator.JUNGLE_SAPLING_DROP_CHANCES
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.HolderLookup
import org.teamvoided.dusk_autumn.block.BigCandleBlock
import org.teamvoided.dusk_autumn.block.DuskBlockLists.leafPiles
import org.teamvoided.dusk_autumn.block.DuskBlockLists.logPiles
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.block.LogPileBlock
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, r) {
    private val excludeList =
        listOf(
            DuskBlocks.BLUE_PETALS,
            DuskBlocks.CASCADE_LEAVES,
            DuskBlocks.GOLDEN_BIRCH_LEAVES,
            DuskBlocks.WILD_WHEAT,
            DuskBlocks.GOLDEN_BEETROOTS,
            DuskBlocks.MOONBERRY_VINE
        )

    override fun generate() {
        DuskBlocks.BLOCKS.filter {
            (
                    it !is SlabBlock &&
                            it !is DoorBlock &&
                            it !is BigCandleBlock &&
                            it !is FlowerPotBlock &&
                            it !is LogPileBlock &&
                            it !is LeafPileBlock) &&
                    it !in excludeList
        }.forEach(::addDrop)
        DuskBlocks.BLOCKS.filter { it is SlabBlock && it !in excludeList }.forEach { add(it, ::slabDrops) }
        DuskBlocks.BLOCKS.filter { it is DoorBlock && it !in excludeList }.forEach { add(it, ::doorDrops) }
        DuskBlocks.BLOCKS.filter { it is BigCandleBlock && it !in excludeList }.forEach { add(it, ::candleDrops) }
        logPiles.forEach { (pile, _) ->
            add(pile) { logPile(pile) }
        }
        leafPiles.forEach { (pile, leaves) ->
            add(pile) { leafPile(it, leaves) }
        }
        add(DuskBlocks.BLUE_PETALS, ::flowerbedDrops)
        add(DuskBlocks.POTTED_CASCADE_SAPLING) { pottedPlantDrops(DuskBlocks.CASCADE_SAPLING) }
        add(DuskBlocks.POTTED_GOLDEN_BIRCH_SAPLING) { pottedPlantDrops(DuskBlocks.GOLDEN_BIRCH_SAPLING) }
        add(DuskBlocks.CASCADE_LEAVES) {
            oakLeavesDrops(it, DuskBlocks.CASCADE_SAPLING, *JUNGLE_SAPLING_DROP_CHANCES)
        }
        add(DuskBlocks.GOLDEN_BIRCH_LEAVES) {
            leavesDrops(it, DuskBlocks.GOLDEN_BIRCH_SAPLING, *LEAVES_SAPLING_DROP_CHANCES)
        }
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
