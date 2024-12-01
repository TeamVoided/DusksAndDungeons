package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.block.*
import net.minecraft.data.server.loot_table.VanillaBlockLootTableGenerator.JUNGLE_SAPLING_DROP_CHANCES
import net.minecraft.enchantment.Enchantments
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.*
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.ICE_SET
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDFloraBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists.bigCandles
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists.bigSoulCandles
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists.leafPiles
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists.soulCandles
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.dusks_and_dungeons.util.datagen.decoratedPotDrops
import org.teamvoided.voidlib.devin.provider.OpenBlockLootTableProvider
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    OpenBlockLootTableProvider(o, r) {
    val manualList: List<Block> = listOf(DnDFloraBlocks.MOONBERRY_VINE)
    override fun generate() {
        // this is here cuz yeah
//        SETS.forEach(this::setDrops)
        val enchantmentLookup = getLookup().getLookupOrThrow(RegistryKeys.ENCHANTMENT)
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

        bigCandles.forEach { (candle, cake) -> add(cake) { candleCakeDrops(candle) } }
        soulCandles.forEach { (candle, cake) -> add(cake) { candleCakeDrops(candle) } }
        bigSoulCandles.forEach { (candle, cake) -> add(cake) { candleCakeDrops(candle) } }
        leafPiles.forEachIndexed { idx, pile -> add(pile) { leafPile(it, DnDBlockLists.leaves[idx]) } }
        add(DnDWoodBlocks.POTTED_CASCADE_SAPLING) { pottedPlantDrops(DnDWoodBlocks.CASCADE_SAPLING) }
        add(DnDWoodBlocks.POTTED_GOLDEN_BIRCH_SAPLING) { pottedPlantDrops(DnDWoodBlocks.GOLDEN_BIRCH_SAPLING) }
        add(DnDWoodBlocks.CASCADE_LEAVES) {
            oakLeavesDrops(it, DnDWoodBlocks.CASCADE_SAPLING, *JUNGLE_SAPLING_DROP_CHANCES)
        }
        add(DnDWoodBlocks.GOLDEN_BIRCH_LEAVES) {
            leavesDrops(it, DnDWoodBlocks.GOLDEN_BIRCH_SAPLING, *LEAVES_SAPLING_DROP_CHANCES)
        }
        twoTallDrop(DnDFloraBlocks.SPIDERLILY)
        addDropWithSilkTouch(ICE_SET.slab)
        addIceSlab(ICE_SET.slab)
        addDropWithSilkTouch(ICE_SET.wall)
        add(DnDBlocks.TALL_REDSTONE_CRYSTAL, ::redstoneCrystalDrops)
        add(DnDFloraBlocks.WARPED_WART) {
            var state = BlockStatePropertyLootCondition.builder(it)
                .properties(StatePredicate.Builder.create().exactMatch(NetherWartBlock.AGE, 3))
            LootTable.builder().pool(
                applyExplosionDecay(
                    it, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
                        ItemEntry.builder(DnDFloraBlocks.WARPED_WART).apply(
                            SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))
                                .conditionally(state)
                        ).apply(
                            ApplyBonusLootFunction.method_456(enchantmentLookup.getHolderOrThrow(Enchantments.FORTUNE))
                                .conditionally(state)
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

    /*   private fun cornCrop(): LootTable.Builder {
           return applyExplosionDecay(
               DnDFloraBlocks.CORN_CROP, LootTable.builder().pool(
                   LootPool.builder().with(AlternativeEntry.builder(
                       PitcherCropBlock.AGE.values
                   ) { integer: Int ->
                       val builder =
                           BlockStatePropertyLootCondition.builder(DnDFloraBlocks.CORN_CROP).properties(
                               StatePredicate.Builder.create()
                                   .exactMatch(TripleTallPlantBlock.SECTION, TripleBlockSection.BOTTOM)
                           )
                       val builder2 =
                           BlockStatePropertyLootCondition.builder(DnDFloraBlocks.CORN_CROP).properties(
                               StatePredicate.Builder.create().exactMatch(
                                   Properties.AGE_7,
                                   integer
                               )
                           )
                       if (integer == CornCropBlock.MAX_AGE) ItemEntry.builder(DnDItems.CORN_STALK)
                           .conditionally(builder2).conditionally(builder).apply(
                               SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))
                           )
                       else ItemEntry.builder(DnDItems.CORN_KERNELS)
                           .conditionally(builder2).conditionally(builder).apply(
                               SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))
                           )
                   })
               )
           )
       }*/
}
