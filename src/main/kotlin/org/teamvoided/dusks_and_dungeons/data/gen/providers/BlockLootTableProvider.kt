package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.*
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import org.teamvoided.dusks_and_dungeons.block.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.LogPileBlock
import org.teamvoided.dusks_and_dungeons.block.TripleTallPlantBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.ICE_SET
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists.bigCandles
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists.bigSoulCandles
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists.leafPiles
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists.soulCandles
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.voidlib.devin.provider.OpenBlockLootTableProvider
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class BlockLootTableProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>) :
    OpenBlockLootTableProvider(o, p) {
    val manualList: List<Block> = listOf(
        DnDBlocks.MOONBERRY_VINE,
        DnDBlocks.MOLTEN_LAVASPONGE,
        DnDBlocks.FUSED_LAVASPONGE,
    )

    override fun generate() {
        val enchantmentLookup = getLookup().lookupOrThrow(Registries.ENCHANTMENT)

        for (block in DnDBlocks.BLOCKS.filterNot(manualList::contains)) {
            when (block) {
                is SlabBlock -> add(block, ::createSlabItemTable)
                is DoorBlock -> add(block, ::createDoorTable)
                is LogPileBlock -> add(block, ::logPile)
                is CandelabraBlock -> add(block, ::candelabraDrops)
                is CandleBlock -> add(block, ::createCandleDrops)
                is TripleTallPlantBlock -> add(block, ::threeTallDrop)
                is PinkPetalsBlock -> add(block, ::createPetalsDrops)
                is DecoratedPotBlock -> add(block, ::decoratedPotDrops)
                else -> dropSelf(block)
            }
        }

        bigCandles.forEach { (candle, cake) -> add(cake) { createCandleCakeDrops(candle) } }
        soulCandles.forEach { (candle, cake) -> add(cake) { createCandleCakeDrops(candle) } }
        bigSoulCandles.forEach { (candle, cake) -> add(cake) { createCandleCakeDrops(candle) } }
        leafPiles.forEachIndexed { idx, pile -> add(pile) { leafPile(it, DnDBlockLists.leaves[idx]) } }

        add(DnDBlocks.POTTED_CASCADE_SAPLING) { createPotFlowerItemTable(DnDBlocks.CASCADE_SAPLING) }
        add(DnDBlocks.POTTED_SYPIA_SAPLING) { createPotFlowerItemTable(DnDBlocks.SYPIA_SAPLING) }
        add(DnDBlocks.CASCADE_LEAVES) {
            createOakLeavesDrops(it, DnDBlocks.CASCADE_SAPLING, *NORMAL_LEAVES_SAPLING_CHANCES)
        }
        add(DnDBlocks.SYPIA_LEAVES) {
            createLeavesDrops(it, DnDBlocks.SYPIA_SAPLING, *NORMAL_LEAVES_SAPLING_CHANCES)
        }

        dropWhenSilkTouch(ICE_SET.slab)
        addIceSlab(ICE_SET.slab)
        dropWhenSilkTouch(ICE_SET.wall)

        add(DnDBlocks.WARPED_WART) {
            val state = LootItemBlockStatePropertyCondition.hasBlockStateProperties(it)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(NetherWartBlock.AGE, 3))
            LootTable.lootTable().withPool(
                applyExplosionDecay(
                    it, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(
                        LootItem.lootTableItem(DnDBlocks.WARPED_WART).apply(
                            SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 4.0f))
                                .`when`(state)
                        ).apply(
                            ApplyBonusCount.addOreBonusCount(enchantmentLookup.getOrThrow(Enchantments.FORTUNE))
                                .`when`(state)
                        )
                    )
                )
            )
        }

        add(
            DnDBlocks.GOLDEN_BEETROOTS,
            this.createCropDrops(
                DnDBlocks.GOLDEN_BEETROOTS,
                DnDItems.GOLDEN_BEETROOT,
                DnDItems.GOLDEN_BEETROOT,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.BEETROOTS).setProperties(
                    StatePropertiesPredicate.Builder.properties().hasProperty(BeetrootBlock.AGE, 3)
                )
            )
        )

        twoTallDrop(DnDBlocks.WILD_WHEAT)

        dropWhenSilkTouch(DnDBlocks.MOLTEN_LAVASPONGE)
        add(DnDBlocks.FUSED_LAVASPONGE) { block ->
            createSilkTouchDispatchTable(
                block, NestedLootTable.inlineLootTable(
                    LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(DnDBlocks.LAVASPONGE)))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.OBSIDIAN)))
                        .build()
                )
            )
        }

    }

}