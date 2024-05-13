package org.teamvoided.dusk_autumn.datagen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.BeetrootsBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
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
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, r) {


    val dropsItSelf = listOf(
        DuskBlocks.CASCADE_SAPLING,
        DuskBlocks.CASCADE_LOG,
        DuskBlocks.STRIPPED_CASCADE_LOG,
        DuskBlocks.CASCADE_PLANKS,
        DuskBlocks.CASCADE_TRAPDOOR,
        DuskBlocks.GOLDEN_BIRCH_SAPLING
    )

    override fun generate() {
        dropsItSelf.forEach(::addDrop)

        add(DuskBlocks.CASCADE_DOOR, ::doorDrops)
        add(DuskBlocks.BLUE_DOOR, ::doorDrops)

        add(DuskBlocks.CASCADE_LEAVES) { oakLeavesDrops(it, DuskBlocks.CASCADE_SAPLING, *JUNGLE_SAPLING_DROP_CHANCES) }
        add(DuskBlocks.GOLDEN_BIRCH_LEAVES) {
            leavesDrops(
                it,
                DuskBlocks.GOLDEN_BIRCH_SAPLING,
                *LEAVES_SAPLING_DROP_CHANCES
            )
        }

        add(DuskBlocks.OAK_LEAF_PILE) { leafPile(it, Blocks.OAK_LEAVES) }
        add(DuskBlocks.SPRUCE_LEAF_PILE) { leafPile(it, Blocks.SPRUCE_LEAVES) }
        add(DuskBlocks.BIRCH_LEAF_PILE) { leafPile(it, Blocks.BIRCH_LEAVES) }
        add(DuskBlocks.JUNGLE_LEAF_PILE) { leafPile(it, Blocks.JUNGLE_LEAVES) }
        add(DuskBlocks.ACACIA_LEAF_PILE) { leafPile(it, Blocks.ACACIA_LEAVES) }
        add(DuskBlocks.DARK_OAK_LEAF_PILE) { leafPile(it, Blocks.DARK_OAK_LEAVES) }
        add(DuskBlocks.MANGROVE_LEAF_PILE) { leafPile(it, Blocks.MANGROVE_LEAVES) }
        add(DuskBlocks.AZALEA_LEAF_PILE) { leafPile(it, Blocks.AZALEA_LEAVES) }
        add(DuskBlocks.FLOWERING_AZALEA_LEAF_PILE) { leafPile(it, Blocks.FLOWERING_AZALEA_LEAVES) }
        add(DuskBlocks.CHERRY_LEAF_PILE) { leafPile(it, Blocks.CHERRY_LEAVES) }
        add(DuskBlocks.CASCADE_LEAF_PILE) { leafPile(it, DuskBlocks.CASCADE_LEAVES) }
        add(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE) { leafPile(it, DuskBlocks.GOLDEN_BIRCH_LEAVES) }

        add(DuskBlocks.BLUE_PETALS, ::flowerbedDrops)
        add(DuskBlocks.POTTED_CASCADE_SAPLING) { pottedPlantDrops(DuskBlocks.CASCADE_SAPLING) }
        add(DuskBlocks.POTTED_GOLDEN_BIRCH_SAPLING) { pottedPlantDrops(DuskBlocks.GOLDEN_BIRCH_SAPLING) }
        add(DuskBlocks.POTTED_VIOLET_DAISY, ::pottedPlantDrops)

        add(
            DuskBlocks.GOLDEN_BEETROOTS,
            applyExplosionDecay(
                DuskBlocks.GOLDEN_BEETROOTS, LootTable.builder().pool(
                    LootPool.builder().with(
                        ItemEntry.builder(
                            DuskItems.GOLDEN_BEETROOT
                        )
                    )
                ).pool(
                    LootPool.builder().conditionally(
                        BlockStatePropertyLootCondition.builder(DuskBlocks.GOLDEN_BEETROOTS).properties(
                            StatePredicate.Builder.create().exactMatch(BeetrootsBlock.AGE, 4)
                        )
                    ).with(
                        ItemEntry.builder(DuskItems.GOLDEN_BEETROOT)
                            .apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 3))
                    )
                )
            )
        )
        add(
            DuskBlocks.MOONBERRY_VINELET,
            applyExplosionDecay(
                DuskBlocks.MOONBERRY_VINELET, LootTable.builder().pool(
                    LootPool.builder().with(
                        ItemEntry.builder(
                            DuskItems.MOONBERRY_VINELET
                        )
                    )
                )
            )
        )
    }

    private fun constantLootNumber(i: Number): ConstantLootNumberProvider =
        ConstantLootNumberProvider.create(i.toFloat())

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
                    }.conditionally(WITH_SHEARS_OR_SILK_TOUCH)
                )
            )
        )
    }
}