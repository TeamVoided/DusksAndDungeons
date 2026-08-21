package org.teamvoided.dusks_and_dungeons.data.gen.data.loot

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import org.teamvoided.dusks_and_dungeons.block.pumpkin.CarvableBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.DnDLootContext
import org.teamvoided.dusks_and_dungeons.util.block.getId
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class BlockInteractLootTablesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(o, r, DnDLootContext.BLOCK_INTERACT) {

    override fun generate(gen: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {

        gen.pumpkin(DnDBlocks.SMALL_PUMPKIN, Items.PUMPKIN_SEEDS, 2)

        gen.pumpkin(DnDBlocks.LANTERN_PUMPKIN, DnDItems.LANTERN_PUMPKIN_SEEDS)
        gen.pumpkin(DnDBlocks.SMALL_LANTERN_PUMPKIN, DnDItems.LANTERN_PUMPKIN_SEEDS, 2)

        gen.pumpkin(DnDBlocks.MOSSKIN_PUMPKIN, DnDItems.MOSSKIN_PUMPKIN_SEEDS)
        gen.pumpkin(DnDBlocks.SMALL_MOSSKIN_PUMPKIN, DnDItems.MOSSKIN_PUMPKIN_SEEDS, 2)

        gen.pumpkin(DnDBlocks.GLOOM_PUMPKIN, DnDItems.GLOOM_PUMPKIN_SEEDS)
        gen.pumpkin(DnDBlocks.SMALL_GLOOM_PUMPKIN, DnDItems.GLOOM_PUMPKIN_SEEDS, 2)

        gen.pumpkin(DnDBlocks.PALE_PUMPKIN, DnDItems.PALE_PUMPKIN_SEEDS)
        gen.pumpkin(DnDBlocks.SMALL_PALE_PUMPKIN, DnDItems.PALE_PUMPKIN_SEEDS, 2)

    }

    fun BiConsumer<ResourceKey<LootTable>, LootTable.Builder>.pumpkin(block: Block, seed: Item, amount: Int = 4) {
        carvedBlock(block, LootTable.lootTable().pool(LootPool.lootPool().add(item(seed).setAmount(amount)).build()))
    }

    fun LootPoolSingletonContainer.Builder<*>.setAmount(amount: Int): LootPoolSingletonContainer.Builder<*> {
        return apply(SetItemCountFunction.setCount(ConstantValue.exactly(amount.toFloat())))
    }

    fun BiConsumer<ResourceKey<LootTable>, LootTable.Builder>.carvedBlock(block: Block, table: LootTable.Builder) {
        accept(CarvableBlock.crateKey(getId(block)), table)
    }

    fun item(item: Item): LootPoolSingletonContainer.Builder<*> = LootItem.lootTableItem(item)
}