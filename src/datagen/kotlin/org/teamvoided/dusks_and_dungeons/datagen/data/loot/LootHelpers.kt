package org.teamvoided.dusks_and_dungeons.datagen.data.loot

import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.util.StringRepresentable
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.MossyCarpetBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.level.block.state.properties.SlabType
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import org.teamvoided.dusks_and_dungeons.block.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.LeafPileBlock
import org.teamvoided.dusks_and_dungeons.block.LogPileBlock
import org.teamvoided.dusks_and_dungeons.block.TripleTallPlantBlock
import org.teamvoided.dusks_and_dungeons.block.not_blocks.TripleBlockSection

fun item(item: ItemLike): LootPoolSingletonContainer.Builder<*> = LootItem.lootTableItem(item)


fun LootPoolSingletonContainer.Builder<*>.setAmount(amount: Int): LootPoolSingletonContainer.Builder<*> {
    return apply(countOf(amount))
}

fun countOf(amount: Number): LootItemConditionalFunction.Builder<*> {
    return SetItemCountFunction.setCount(ConstantValue.exactly(amount.toFloat()))
}

fun blockProperty(block: Block): LootItemBlockStatePropertyCondition.Builder {
    return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
}

fun <T> LootItemBlockStatePropertyCondition.Builder.setProperty(
    property: Property<T>, comparable: T,
): LootItemBlockStatePropertyCondition.Builder where T : Comparable<T>, T : StringRepresentable {
    return setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, comparable))
}

fun LootItemBlockStatePropertyCondition.Builder.setProperty(
    property: Property<Int>, comparable: Int,
): LootItemBlockStatePropertyCondition.Builder {
    return setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, comparable))
}

fun LootItemBlockStatePropertyCondition.Builder.setProperty(
    property: Property<Boolean>, comparable: Boolean,
): LootItemBlockStatePropertyCondition.Builder {
    return setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, comparable))
}

fun <T> BlockLootSubProvider.crateDropsWithProperty(
    drop: Block, property: Property<T>, value: T,
): LootTable.Builder where T : Comparable<T>, T : StringRepresentable {
    return LootTable.lootTable().withPool(
        applyExplosionCondition(
            drop, LootPool.lootPool()
                .add(item(drop).`when`(blockProperty(drop).setProperty(property, value)))
        )
    )
}

fun BlockLootSubProvider.crateDropsWithProperty(drop: Block, property: BooleanProperty): LootTable.Builder {
    return LootTable.lootTable().withPool(
        applyExplosionCondition(
            drop, LootPool.lootPool()
                .add(item(drop).`when`(blockProperty(drop).setProperty(property, true)))
        )
    )
}

// region Specific Block Drops
fun BlockLootSubProvider.createLeafPileDrops(pile: Block, leaves: Block): LootTable.Builder {
    return LootTable.lootTable().withPool(
        LootPool.lootPool().add(
            AlternativesEntry.alternatives(LeafPileBlock.PILE_LAYERS.possibleValues) { layers ->
                if (layers == 4)
                    NestedLootTable.lootTableReference(leaves.lootTable)
                else
                    item(pile)
                        .apply(countOf(layers))
                        .`when`(blockProperty(pile).setProperty(LeafPileBlock.PILE_LAYERS, layers))
                        .`when`(hasShearsOrSilkTouch())
            }
        )
    )
}

fun BlockLootSubProvider.createLogPileDrops(drop: Block): LootTable.Builder {
    return LootTable.lootTable().withPool(
        LootPool.lootPool().add(
            applyExplosionDecay(drop, item(drop).apply(2..4) { layers ->
                countOf(layers)
                    .`when`(blockProperty(drop).setProperty(LogPileBlock.PILE_LAYERS, layers))
            })
        )
    )
}


fun BlockLootSubProvider.candelabraDrops(drop: Block): LootTable.Builder {
    return LootTable.lootTable().withPool(
        LootPool.lootPool().add(
            applyExplosionDecay(drop, item(drop).apply(2..5) { candles ->
                countOf(candles)
                    .`when`(blockProperty(drop).setProperty(CandelabraBlock.CANDLES, candles))
            })
        )
    )
}

fun BlockLootSubProvider.dropSlabWhenSilkTouch(block: Block) {
    return add(
        block, LootTable.lootTable().withPool(
            LootPool.lootPool().`when`(hasSilkTouch()).add(
                item(block)
                    .apply(countOf(2).`when`(blockProperty(block).setProperty(SlabBlock.TYPE, SlabType.DOUBLE)))
            )
        )
    )
}

fun BlockLootSubProvider.twoTallDrop(block: Block): LootTable.Builder {
    return crateDropsWithProperty(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
}

fun BlockLootSubProvider.mossyCarpetDrop(block: Block): LootTable.Builder {
    return crateDropsWithProperty(block, MossyCarpetBlock.BASE)
}

fun BlockLootSubProvider.threeTallDrop(block: Block): LootTable.Builder {
    return crateDropsWithProperty(block, TripleTallPlantBlock.SECTION, TripleBlockSection.BOTTOM)
}
// endregion
