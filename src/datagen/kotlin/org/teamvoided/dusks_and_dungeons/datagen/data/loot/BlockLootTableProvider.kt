package org.teamvoided.dusks_and_dungeons.datagen.data.loot

import net.minecraft.core.registries.Registries
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.*
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
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
import org.teamvoided.dusks_and_dungeons.util.isEmpty
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import org.teamvoided.voidlib.devin.provider.OpenBlockLootTableProvider

class BlockLootTableProvider(o: FabricOutput, p: FutureProvider) : OpenBlockLootTableProvider(o, p) {

    // This list is here for manually written JSON files
    val manual = listOf(
        DnDBlocks.MOONBERRY_VINE
    )

    override fun generate() {
        val enchantments = getLookup().lookupOrThrow(Registries.ENCHANTMENT)
        val fortune = enchantments.getOrThrow(Enchantments.FORTUNE)

        // Block loot allows for overrides so you can do generic coverage here and add specific overrides after
        for (block in DnDBlocks.BLOCKS.filterNot(manual::contains)) {
            if (block.lootTable.isEmpty()) {
                continue
            }
            when (block) {
                is SlabBlock -> add(block, ::createSlabItemTable)
                is DoorBlock -> add(block, ::createDoorTable)
                is LogPileBlock -> add(block, ::createLogPileDrops)
                is CandelabraBlock -> add(block, ::candelabraDrops)
                is CandleBlock -> add(block, ::createCandleDrops)
                is DoublePlantBlock -> add(block, ::twoTallDrop)
                is TripleTallPlantBlock -> add(block, ::threeTallDrop)
                is PinkPetalsBlock -> add(block, ::createPetalsDrops)
                is FlowerPotBlock -> add(block) { createPotFlowerItemTable(block.potted) }
                else -> dropSelf(block)
            }
        }

        val saplingChance = NORMAL_LEAVES_SAPLING_CHANCES
        add(DnDBlocks.CASCADE_LEAVES) { createOakLeavesDrops(it, DnDBlocks.CASCADE_SAPLING, *saplingChance) }
        add(DnDBlocks.SYPIA_LEAVES) { createLeavesDrops(it, DnDBlocks.SYPIA_SAPLING, *saplingChance) }
        add(DnDBlocks.VERDANT_LEAVES) { createLeavesDrops(it, DnDBlocks.OVERGROWTH_BUSH, *saplingChance) }

        leafPiles.forEachIndexed { idx, pile -> add(pile) { createLeafPileDrops(it, DnDBlockLists.leaves[idx]) } }

        bigCandles.forEach { (candle, cake) -> add(cake) { createCandleCakeDrops(candle) } }
        soulCandles.forEach { (candle, cake) -> add(cake) { createCandleCakeDrops(candle) } }
        bigSoulCandles.forEach { (candle, cake) -> add(cake) { createCandleCakeDrops(candle) } }

        add(DnDBlocks.WARPED_WART) {
            val state = blockProperty(it).setProperty(NetherWartBlock.AGE, 3)
            LootTable.lootTable().withPool(
                applyExplosionDecay(
                    it, LootPool.lootPool().add(
                        item(DnDBlocks.WARPED_WART)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 4.0f)).`when`(state))
                            .apply(ApplyBonusCount.addOreBonusCount(fortune).`when`(state))
                    )
                )
            )
        }

        add(
            DnDBlocks.GOLDEN_BEETROOTS,
            createCropDrops(
                DnDBlocks.GOLDEN_BEETROOTS, DnDItems.GOLDEN_BEETROOT, DnDItems.GOLDEN_BEETROOT,
                blockProperty(Blocks.BEETROOTS).setProperty(BeetrootBlock.AGE, 3)
            )
        )

        dropWhenSilkTouch(ICE_SET.slab)
        dropSlabWhenSilkTouch(ICE_SET.slab)
        dropWhenSilkTouch(ICE_SET.wall)

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

        add(DnDBlocks.SUSPICIOUS_RED_SAND, noDrop())
    }

}