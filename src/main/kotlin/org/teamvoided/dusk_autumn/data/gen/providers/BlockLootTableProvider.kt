package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.*
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.block.enums.SlabType
import net.minecraft.component.DataComponentTypes
import net.minecraft.data.server.loot_table.VanillaBlockLootTableGenerator.JUNGLE_SAPLING_DROP_CHANCES
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.*
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.function.CopyComponentsLootFunction
import net.minecraft.loot.function.CopyComponentsLootFunction.C_zcqyfuyv
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.state.property.Property
import net.minecraft.util.StringIdentifiable
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.block.not_blocks.TripleBlockSection
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.util.DnDBlockLists
import org.teamvoided.dusk_autumn.util.DnDBlockLists.bigCandles
import org.teamvoided.dusk_autumn.util.DnDBlockLists.bigSoulCandles
import org.teamvoided.dusk_autumn.util.DnDBlockLists.leafPiles
import org.teamvoided.dusk_autumn.util.DnDBlockLists.soulCandles
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, r) {
    val manualList: List<Block> = listOf(DnDFloraBlocks.MOONBERRY_VINE)
    override fun generate() {
        val enchantmentLookup = field_51845.getLookupOrThrow(RegistryKeys.ENCHANTMENT)
        DnDBlocks.BLOCKS.filterNot(manualList::contains).forEach {
            when (it) {
                is SlabBlock -> add(it, ::slabDrops)
                is DoorBlock -> add(it, ::doorDrops)
                is LogPileBlock -> add(it, ::logPile)
                is CandelabraBlock -> add(it, ::candelabraDrops)
                is CandleBlock -> add(it, ::candleDrops)
                is TripleTallPlantBlock -> add(it, ::threeTallDrop)
                is PinkPetalsBlock -> add(it, ::flowerbedDrops)
                is DecoratedPotBlock -> add(it, ::decoratedPotDrops)
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
        leafPiles.forEachIndexed { idx, pile ->
            add(pile) { leafPile(it, DnDBlockLists.leaves[idx]) }
        }
        add(DnDWoodBlocks.POTTED_CASCADE_SAPLING) { pottedPlantDrops(DnDWoodBlocks.CASCADE_SAPLING) }
        add(DnDWoodBlocks.POTTED_GOLDEN_BIRCH_SAPLING) { pottedPlantDrops(DnDWoodBlocks.GOLDEN_BIRCH_SAPLING) }
        add(DnDWoodBlocks.CASCADE_LEAVES) {
            oakLeavesDrops(it, DnDWoodBlocks.CASCADE_SAPLING, *JUNGLE_SAPLING_DROP_CHANCES)
        }
        add(DnDWoodBlocks.GOLDEN_BIRCH_LEAVES) {
            leavesDrops(it, DnDWoodBlocks.GOLDEN_BIRCH_SAPLING, *LEAVES_SAPLING_DROP_CHANCES)
        }
        twoTallDrop(DnDFloraBlocks.SPIDERLILY)
        addDropWithSilkTouch(DnDBlocks.ICE_STAIRS)
        addIceSlab(DnDBlocks.ICE_SLAB)
        addDropWithSilkTouch(DnDBlocks.ICE_WALL)
        add(DnDBlocks.TALL_REDSTONE_CRYSTAL, ::redstoneCrystalDrops)
        add(DnDFloraBlocks.WARPED_WART) { block: Block ->
            LootTable.builder().pool(
                applyExplosionDecay(
                    block,
                    LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
                        ItemEntry.builder(DnDFloraBlocks.WARPED_WART)
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

//        add(DnDFloraBlocks.CORN_CROP) { block: Block -> cornCrop() }
        add(
            DnDFloraBlocks.GOLDEN_BEETROOTS,
            this.cropDrops(
                DnDFloraBlocks.GOLDEN_BEETROOTS,
                DnDItems.GOLDEN_BEETROOT,
                DnDItems.GOLDEN_BEETROOT,
                BlockStatePropertyLootCondition.builder(Blocks.BEETROOTS).properties(
                    StatePredicate.Builder.create().exactMatch(BeetrootsBlock.AGE, 3)
                )
            )
        )
        twoTallDrop(DnDFloraBlocks.WILD_WHEAT)
    }

    private fun constantLootNumber(i: Number): ConstantLootNumberProvider =
        ConstantLootNumberProvider.create(i.toFloat())

    override fun <T> dropsWithPropertyValue(
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

    fun twoTallDrop(block: Block) {
        add(block) { block2: Block ->
            this.dropsWithPropertyValue(
                block2,
                TallPlantBlock.HALF,
                DoubleBlockHalf.LOWER
            )
        }
    }

    fun threeTallDrop(block: Block): LootTable.Builder {
        return this.dropsWithPropertyValue(
            block,
            TripleTallPlantBlock.SECTION,
            TripleBlockSection.BOTTOM
        )
    }

    fun candelabraDrops(drop: Block): LootTable.Builder {
        return LootTable.builder().pool(
            LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
                applyExplosionDecay(drop, ItemEntry.builder(drop).apply(
                    listOf(2, 3, 4, 5)
                ) { count: Int ->
                    SetCountLootFunction.builder(
                        ConstantLootNumberProvider.create(count.toFloat())
                    ).conditionally(
                        BlockStatePropertyLootCondition.builder(drop).properties(
                            StatePredicate.Builder.create().exactMatch(CandelabraBlock.CANDLES, count)
                        )
                    )
                })
            )
        )
    }

    fun addIceSlab(block: Block) {
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

    fun redstoneCrystalDrops(fullBlock: Block): LootTable.Builder {
        val registryLookup = field_51845.getLookupOrThrow(RegistryKeys.ENCHANTMENT)
        val blockstateCondition = BlockStatePropertyLootCondition.builder(fullBlock)
            .properties(
                StatePredicate.Builder.create()
                    .exactMatch(TallDirectionalBlock.HALF, DoubleBlockHalf.LOWER)
            )
        return dropsConditionally(
            fullBlock,
            this.method_60390().and(blockstateCondition),
            applyExplosionDecay(
                fullBlock,
                ItemEntry.builder(Items.REDSTONE)
                    .conditionally(blockstateCondition)
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 5.0f)))
                    .apply(ApplyBonusLootFunction.method_456(registryLookup.getHolderOrThrow(Enchantments.FORTUNE)))
            )
        )
    }

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
                AlternativeEntry.builder(LeafPileBlock.PILE_LAYERS.values) { layers ->
                    if (layers == 4) LootTableEntry.method_428(leaves.lootTableId)
                    else ItemEntry.builder(pile)
                        .apply(SetCountLootFunction.builder(constantLootNumber(layers)))
                        .conditionally(
                            BlockStatePropertyLootCondition.builder(pile).properties(
                                StatePredicate.Builder.create().exactMatch(LeafPileBlock.PILE_LAYERS, layers)
                            )
                        ).conditionally(this.method_60392())
                }
            )
        )
    }

    private fun decoratedPotDrops(pot: Block): LootTable.Builder {
        return LootTable.builder().pool(
            LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
                (DynamicEntry.builder(DecoratedPotBlock.SHERDS).conditionally(
                    BlockStatePropertyLootCondition.builder(pot).properties(
                        StatePredicate.Builder.create().exactMatch(DecoratedPotBlock.CRACKED, true)
                    )
                ) as LeafEntry.Builder<*>).alternatively(
                    ItemEntry.builder(pot).apply(
                        CopyComponentsLootFunction.method_57637(C_zcqyfuyv.BLOCK_ENTITY)
                            .method_58730(DataComponentTypes.POT_DECORATIONS)
                    )
                )
            )
        )
    }

//    private fun cornCrop(): LootTable.Builder {
//        return applyExplosionDecay(
//            DnDFloraBlocks.CORN_CROP, LootTable.builder().pool(
//                LootPool.builder().with(AlternativeEntry.builder(
//                    PitcherCropBlock.AGE.values
//                ) { integer: Int ->
//                    val builder =
//                        BlockStatePropertyLootCondition.builder(DnDFloraBlocks.CORN_CROP).properties(
//                            StatePredicate.Builder.create()
//                                .exactMatch(TripleTallPlantBlock.SECTION, TripleBlockSection.BOTTOM)
//                        )
//                    val builder2 =
//                        BlockStatePropertyLootCondition.builder(DnDFloraBlocks.CORN_CROP).properties(
//                            StatePredicate.Builder.create().exactMatch(
//                                Properties.AGE_7,
//                                integer
//                            )
//                        )
//                    if (integer == CornCropBlock.MAX_AGE) ItemEntry.builder(DnDItems.CORN_STALK)
//                        .conditionally(builder2).conditionally(builder).apply(
//                            SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))
//                        )
//                    else ItemEntry.builder(DnDItems.CORN_KERNELS)
//                        .conditionally(builder2).conditionally(builder).apply(
//                            SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))
//                        )
//                })
//            )
//        )
//    }
}
