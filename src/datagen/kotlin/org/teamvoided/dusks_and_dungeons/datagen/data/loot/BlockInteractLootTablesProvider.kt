package org.teamvoided.dusks_and_dungeons.datagen.data.loot

import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import org.teamvoided.dusks_and_dungeons.block.pumpkin.CarvableBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.DnDLootContext.BLOCK_INTERACT
import org.teamvoided.dusks_and_dungeons.util.block.getId
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import java.util.function.BiConsumer

class BlockInteractLootTablesProvider(o: FabricOutput, p: FutureProvider) :
    SimpleFabricLootTableProvider(o, p, BLOCK_INTERACT) {

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
        carvedBlock(
            block, LootTable.lootTable().pool(
                LootPool.lootPool().add(item(seed).setAmount(amount)).build()
            )
        )
    }

    fun BiConsumer<ResourceKey<LootTable>, LootTable.Builder>.carvedBlock(block: Block, table: LootTable.Builder) {
        accept(CarvableBlock.crateKey(getId(block)), table)
    }

}