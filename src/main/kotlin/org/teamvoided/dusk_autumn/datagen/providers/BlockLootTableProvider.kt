package org.teamvoided.dusk_autumn.datagen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.server.loot_table.VanillaBlockLootTableGenerator.JUNGLE_SAPLING_DROP_CHANCES
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.predicate.StatePredicate
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.init.DuskBlocks

class BlockLootTableProvider(o: FabricDataOutput) : FabricBlockLootTableProvider(o) {


    val dropsItSelf = listOf(
        DuskBlocks.CASCADE_PLANKS
    )

    override fun generate() {
        dropsItSelf.forEach(::addDrop)

        add(DuskBlocks.CASCADE_DOOR, ::doorDrops)

        add(DuskBlocks.OAK_LEAF_PILE) { oakLeavesDrops(it, Blocks.OAK_SAPLING, *LEAVES_SAPLING_DROP_CHANCES) }
        add(DuskBlocks.JUNGLE_LEAF_PILE) { leavesDrops(it, Blocks.JUNGLE_SAPLING, *JUNGLE_SAPLING_DROP_CHANCES) }
        add(DuskBlocks.MANGROVE_LEAF_PILE, ::mangroveLeavesDrops)

        add(DuskBlocks.CASCADE_LEAF_PILE) { leafPile(it, DuskBlocks.CASCADE_LEAVES) }

        add(DuskBlocks.BLUE_PETALS, ::method_49358)

        add(DuskBlocks.POTTED_VIOLET_DAISY, ::pottedPlantDrops)
    }

    private fun constantLootNumber(i: Number): ConstantLootNumberProvider =
        ConstantLootNumberProvider.create(i.toFloat())

    fun leafPile(pile: Block, leaves: Block): LootTable.Builder {
        return LootTable.builder().pool(
            LootPool.builder().with(
                AlternativeEntry.builder(
                    AlternativeEntry.builder(LeafPileBlock.PILE_LAYERS.values) { layers ->
                        if (layers == 4) LootTableEntry.builder(leaves.lootTableId)
                        else
                        //NEEDS TO BE CHANGED !!!!!! (idk what you want here)
                            ItemEntry.builder(pile)
                                .apply(SetCountLootFunction.builder(constantLootNumber(layers)))
                                .conditionally(
                                    BlockStatePropertyLootCondition.builder(pile).properties(
                                        StatePredicate.Builder.create().exactMatch(LeafPileBlock.PILE_LAYERS, layers)
                                    )
                                )
                    },
                    AlternativeEntry.builder(LeafPileBlock.PILE_LAYERS.values) { layers ->
                        ItemEntry.builder(pile)
                            .apply(SetCountLootFunction.builder(constantLootNumber(layers)))
                            .conditionally(
                                BlockStatePropertyLootCondition.builder(pile).properties(
                                    StatePredicate.Builder.create().exactMatch(LeafPileBlock.PILE_LAYERS, layers)
                                )
                            )
                    }.conditionally(WITH_SHEARS_OR_SILK_TOUCH)
                )
            )
        )
    }
}