package org.teamvoided.dusks_and_dungeons.util.datagen

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DecoratedPotBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.SlabType
import net.minecraft.core.component.DataComponents
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry
import net.minecraft.world.level.storage.loot.entries.DynamicLoot
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.util.StringRepresentable
import org.teamvoided.dusks_and_dungeons.block.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.LeafPileBlock
import org.teamvoided.dusks_and_dungeons.block.LogPileBlock
import org.teamvoided.dusks_and_dungeons.block.TripleTallPlantBlock
import org.teamvoided.dusks_and_dungeons.block.not_blocks.TripleBlockSection

fun BlockLootSubProvider.leafPile(pile: Block, leaves: Block): LootTable.Builder {
    return LootTable.lootTable().withPool(
        LootPool.lootPool().add(
            AlternativesEntry.alternatives(LeafPileBlock.PILE_LAYERS.possibleValues) { layers ->
                if (layers == 4) NestedLootTable.lootTableReference(leaves.lootTable)
                else LootItem.lootTableItem(pile)
                    .apply(SetItemCountFunction.setCount(constNum(layers)))
                    .`when`(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(pile).setProperties(
                            StatePropertiesPredicate.Builder.properties().hasProperty(LeafPileBlock.PILE_LAYERS, layers)
                        )
                    ).`when`(this.hasShearsOrSilkTouch())
            }
        )
    )
}

fun BlockLootSubProvider.logPile(drop: Block): LootTable.Builder {
    return LootTable.lootTable().withPool(
        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(
            applyExplosionDecay(
                drop, LootItem.lootTableItem(drop).apply(
                    listOf(2, 3, 4)
                ) { count: Int ->
                    SetItemCountFunction.setCount(
                        ConstantValue.exactly(count.toFloat())
                    ).`when`(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(drop).setProperties(
                            StatePropertiesPredicate.Builder.properties().hasProperty(LogPileBlock.PILE_LAYERS, count)
                        )
                    )
                })
        )
    )
}

fun BlockLootSubProvider.candelabraDrops(drop: Block): LootTable.Builder {
    return LootTable.lootTable().withPool(
        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(
            applyExplosionDecay(drop, LootItem.lootTableItem(drop).apply(listOf(2, 3, 4, 5)) { count: Int ->
                SetItemCountFunction.setCount(constNum(count)).`when`(
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(drop).setProperties(
                        StatePropertiesPredicate.Builder.properties().hasProperty(CandelabraBlock.CANDLES, count)
                    )
                )
            })
        )
    )
}

fun BlockLootSubProvider.addIceSlab(block: Block) {
    return add(
        block, LootTable.lootTable().withPool(
            LootPool.lootPool().`when`(this.hasSilkTouch()).setRolls(ConstantValue.exactly(1.0f))
                .add(
                    LootItem.lootTableItem(block).apply(
                        SetItemCountFunction.setCount(ConstantValue.exactly(2.0f)).`when`(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                                StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)
                            )
                        )
                    )
                )
        )
    )
}

fun decoratedPotDrops(pot: Block): LootTable.Builder {
    return LootTable.lootTable().withPool(
        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(
            DynamicLoot.dynamicEntry(DecoratedPotBlock.SHERDS_DYNAMIC_DROP_ID).`when`(
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(pot)
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DecoratedPotBlock.CRACKED, true))
            ).otherwise(
                LootItem.lootTableItem(pot).apply(
                    CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                        .include(DataComponents.POT_DECORATIONS)
                )
            )
        )
    )
}

/*fun OpenBlockLootTableProvider.redstoneCrystalDrops(fullBlock: Block): LootTable.Builder {
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
}*/

fun BlockLootSubProvider.twoTallDrop(block: Block) {
    add(block) { this.customDropsWithPropertyValue(it, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER) }
}

fun BlockLootSubProvider.threeTallDrop(block: Block): LootTable.Builder {
    return this.customDropsWithPropertyValue(block, TripleTallPlantBlock.SECTION, TripleBlockSection.BOTTOM)
}

fun <T> BlockLootSubProvider.customDropsWithPropertyValue(
    drop: Block,
    property: Property<T>,
    value: T
): LootTable.Builder where T : Comparable<T>, T : StringRepresentable? {
    return LootTable.lootTable().withPool(
        applyExplosionCondition(
            drop, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(
                LootItem.lootTableItem(drop).`when`(
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(drop)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, value))
                )
            )
        )
    )
}

fun constNum(i: Number): ConstantValue =
    ConstantValue.exactly(i.toFloat())