package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.*
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.data.server.loot_table.VanillaBlockLootTableGenerator.JUNGLE_SAPLING_DROP_CHANCES
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util.DnDBlockLists.bigCandles
import net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util.DnDBlockLists.bigSoulCandles
import net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util.DnDBlockLists.leafPiles
import net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util.DnDBlockLists.logPiles
import net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util.DnDBlockLists.soulCandles
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
            DnDBlocks.WARPED_WART,
            DnDBlocks.GOLDEN_BEETROOTS,
            DnDBlocks.WILD_WHEAT,
            DnDBlocks.MOONBERRY_VINE
        ) +
                bigCandles.map { it.second } +
                soulCandles.map { it.second } +
                bigSoulCandles.map { it.second } +
                logPiles.map { it.first } +
                leafPiles.map { it.first }

    override fun generate() {
        val enchantmentLookup = field_51845.getLookupOrThrow(RegistryKeys.ENCHANTMENT)

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
        add(DnDBlocks.WARPED_WART) { block: Block ->
            LootTable.builder().pool(
                applyExplosionDecay(
                    block,
                    LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
                        ItemEntry.builder(DnDBlocks.WARPED_WART)
                            .apply(
                                SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))
                                    .conditionally(
                                        BlockStatePropertyLootCondition.builder(block).properties(
                                            StatePredicate.Builder.create().exactMatch(NetherWartBlock.AGE, 3)
                                        )
                                    )
                            )
                            .apply(
                                ApplyBonusLootFunction.method_456(enchantmentLookup.getHolderOrThrow(Enchantments.FORTUNE))
                                    .conditionally(
                                        BlockStatePropertyLootCondition.builder(block).properties(
                                            StatePredicate.Builder.create().exactMatch(NetherWartBlock.AGE, 3)
                                        )
                                    )
                            )
                    )
                )
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
        add(DnDBlocks.WILD_WHEAT) { block: Block ->
            this.dropsWithPropertyValue(
                block,
                TallPlantBlock.HALF,
                DoubleBlockHalf.LOWER
            )
        }
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
