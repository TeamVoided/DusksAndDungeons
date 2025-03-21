package org.teamvoided.dusks_and_dungeons.util.datagen

import net.minecraft.block.Block
import net.minecraft.block.DecoratedPotBlock
import net.minecraft.block.SlabBlock
import net.minecraft.block.TallPlantBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.block.enums.SlabType
import net.minecraft.component.DataComponentTypes
import net.minecraft.data.server.loot_table.BlockLootTableGenerator
import net.minecraft.data.server.loot_table.BlockLootTableGenerator.dropsConditionally
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.DynamicEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.function.CopyComponentsLootFunction
import net.minecraft.loot.function.CopyComponentsLootFunction.C_zcqyfuyv
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.RegistryKeys
import net.minecraft.state.property.Property
import net.minecraft.util.StringIdentifiable
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.block.not_blocks.TripleBlockSection
import org.teamvoided.voidlib.devin.provider.OpenBlockLootTableProvider

fun BlockLootTableGenerator.leafPile(pile: Block, leaves: Block): LootTable.Builder {
    return LootTable.builder().pool(
        LootPool.builder().with(
            AlternativeEntry.builder(LeafPileBlock.PILE_LAYERS.values) { layers ->
                if (layers == 4) LootTableEntry.method_428(leaves.lootTableId)
                else ItemEntry.builder(pile)
                    .apply(SetCountLootFunction.builder(constNum(layers)))
                    .conditionally(
                        BlockStatePropertyLootCondition.builder(pile).properties(
                            StatePredicate.Builder.create().exactMatch(LeafPileBlock.PILE_LAYERS, layers)
                        )
                    ).conditionally(this.method_60392())
            }
        )
    )
}

fun BlockLootTableGenerator.logPile(drop: Block): LootTable.Builder {
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

fun BlockLootTableGenerator.candelabraDrops(drop: Block): LootTable.Builder {
    return LootTable.builder().pool(
        LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
            applyExplosionDecay(drop, ItemEntry.builder(drop).apply(listOf(2, 3, 4, 5)) { count: Int ->
                SetCountLootFunction.builder(constNum(count)).conditionally(
                    BlockStatePropertyLootCondition.builder(drop).properties(
                        StatePredicate.Builder.create().exactMatch(CandelabraBlock.CANDLES, count)
                    )
                )
            })
        )
    )
}

fun BlockLootTableGenerator.addIceSlab(block: Block) {
    return add(
        block, LootTable.builder().pool(
            LootPool.builder().conditionally(this.method_60390()).rolls(ConstantLootNumberProvider.create(1.0f))
                .with(
                    ItemEntry.builder(block).apply(
                        SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f)).conditionally(
                            BlockStatePropertyLootCondition.builder(block).properties(
                                StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE)
                            )
                        )
                    )
                )
        )
    )
}

fun decoratedPotDrops(pot: Block): LootTable.Builder {
    return LootTable.builder().pool(
        LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
            DynamicEntry.builder(DecoratedPotBlock.SHERDS).conditionally(
                BlockStatePropertyLootCondition.builder(pot)
                    .properties(StatePredicate.Builder.create().exactMatch(DecoratedPotBlock.CRACKED, true))
            ).alternatively(
                ItemEntry.builder(pot).apply(
                    CopyComponentsLootFunction.method_57637(C_zcqyfuyv.BLOCK_ENTITY)
                        .method_58730(DataComponentTypes.POT_DECORATIONS)
                )
            )
        )
    )
}

fun OpenBlockLootTableProvider.redstoneCrystalDrops(fullBlock: Block): LootTable.Builder {
    val registryLookup = getLookup().getLookupOrThrow(RegistryKeys.ENCHANTMENT)
    val blockstateCondition = BlockStatePropertyLootCondition.builder(fullBlock)
        .properties(
            StatePredicate.Builder.create()
                .exactMatch(TallDirectionalBlock.HALF, DoubleBlockHalf.LOWER)
        )
    return dropsConditionally(
        fullBlock, this.method_60390().and(blockstateCondition), applyExplosionDecay(
            fullBlock,
            ItemEntry.builder(Items.REDSTONE)
                .conditionally(blockstateCondition)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 5.0f)))
                .apply(ApplyBonusLootFunction.method_456(registryLookup.getHolderOrThrow(Enchantments.FORTUNE)))
        )
    )
}

fun BlockLootTableGenerator.twoTallDrop(block: Block) {
    add(block) { this.customDropsWithPropertyValue(it, TallPlantBlock.HALF, DoubleBlockHalf.LOWER) }
}

fun BlockLootTableGenerator.threeTallDrop(block: Block): LootTable.Builder {
    return this.customDropsWithPropertyValue(block, TripleTallPlantBlock.SECTION, TripleBlockSection.BOTTOM)
}

fun <T> BlockLootTableGenerator.customDropsWithPropertyValue(
    drop: Block,
    property: Property<T>,
    value: T
): LootTable.Builder where T : Comparable<T>, T : StringIdentifiable? {
    return LootTable.builder().pool(
        applySurvivesExplosionCondition(
            drop, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
                ItemEntry.builder(drop).conditionally(
                    BlockStatePropertyLootCondition.builder(drop)
                        .properties(StatePredicate.Builder.create().exactMatch(property, value))
                )
            )
        )
    )
}

fun constNum(i: Number): ConstantLootNumberProvider =
    ConstantLootNumberProvider.create(i.toFloat())