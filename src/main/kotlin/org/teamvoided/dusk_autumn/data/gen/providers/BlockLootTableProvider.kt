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
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.HolderLookup
import org.teamvoided.dusk_autumn.block.DnDLists.bigCandles
import org.teamvoided.dusk_autumn.block.DnDLists.bigSoulCandles
import org.teamvoided.dusk_autumn.block.DnDLists.leafPiles
import org.teamvoided.dusk_autumn.block.DnDLists.logPiles
import org.teamvoided.dusk_autumn.block.DnDLists.soulCandles
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.block.LogPileBlock
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, r) {
    private val excludeList =
        listOf(
            DnDBlocks.BLUE_PETALS,
            DnDBlocks.CASCADE_LEAVES,
            DnDBlocks.GOLDEN_BIRCH_LEAVES,
            DnDBlocks.WILD_WHEAT,
            DnDBlocks.GOLDEN_BEETROOTS,
            DnDBlocks.MOONBERRY_VINE
        ) +
                bigCandles.map { it.second } +
                soulCandles.map { it.second } +
                bigSoulCandles.map { it.second } +
                logPiles.map { it.first } +
                leafPiles.map { it.first }

    override fun generate() {
        DnDBlocks.BLOCKS.filter { (it !in excludeList && it !is FlowerPotBlock) }.forEach {
            when (it) {
                is SlabBlock -> add(it, ::slabDrops)
                is DoorBlock -> add(it, ::doorDrops)
                is LogPileBlock -> add(it, ::logPile)
                is CandleBlock -> add(it, ::candleDrops)
                else -> addDrop(it)
            }
        }
        bigCandles.forEach { (candle, cake) ->
            add(cake) { candleCakeDrops(candle) }
        }
        soulCandles.forEach { (candle, cake) ->
            add(cake) { candleCakeDrops(candle) }
        }
        bigSoulCandles.forEach { (candle, cake) ->
            add(cake) { candleCakeDrops(candle) }
        }
        leafPiles.forEach { (pile, leaves) ->
            add(pile) { leafPile(it, leaves) }
        }
        add(DnDBlocks.BLUE_PETALS, ::flowerbedDrops)
        add(DnDBlocks.POTTED_CASCADE_SAPLING) { pottedPlantDrops(DnDBlocks.CASCADE_SAPLING) }
        add(DnDBlocks.POTTED_GOLDEN_BIRCH_SAPLING) { pottedPlantDrops(DnDBlocks.GOLDEN_BIRCH_SAPLING) }
        add(DnDBlocks.CASCADE_LEAVES) {
            oakLeavesDrops(it, DnDBlocks.CASCADE_SAPLING, *JUNGLE_SAPLING_DROP_CHANCES)
        }
        add(DnDBlocks.GOLDEN_BIRCH_LEAVES) {
            leavesDrops(it, DnDBlocks.GOLDEN_BIRCH_SAPLING, *LEAVES_SAPLING_DROP_CHANCES)
        }
        add(DnDBlocks.WILD_WHEAT) { block: Block ->
            this.dropsWithPropertyValue(
                block,
                TallPlantBlock.HALF,
                DoubleBlockHalf.LOWER
            )
        }
        add(
            DnDBlocks.GOLDEN_BEETROOTS,
            this.cropDrops(
                DnDBlocks.GOLDEN_BEETROOTS,
                DnDItems.GOLDEN_BEETROOT,
                DnDItems.GOLDEN_BEETROOT,
                BlockStatePropertyLootCondition.builder(Blocks.BEETROOTS).properties(
                    StatePredicate.Builder.create().exactMatch(BeetrootsBlock.AGE, 3)
                )
            )
        )
        add(DnDBlocks.POTTED_VIOLET_DAISY) { pottedPlantDrops(DnDBlocks.VIOLET_DAISY) }
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
