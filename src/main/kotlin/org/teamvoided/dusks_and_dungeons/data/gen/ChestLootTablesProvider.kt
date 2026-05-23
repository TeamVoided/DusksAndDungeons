package org.teamvoided.dusks_and_dungeons.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.world.item.Item
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class ChestLootTablesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(o, r, LootContextParamSets.CHEST) {
    override fun generate(gen: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {

        // eStrongholdLibraryLootTable

//        gen.accept(
//            RegistryKey.of(RegistryKeys.LOOT_TABLE, DuskLootTables.COOL_CHEST),
//            LootTable.builder().pool(
//                LootPool.builder().rolls(Utils.uniformNum(2, 10))
//                    .with(
//                        ItemEntry.builder(Items.BOOK).weight(20)
//                            .apply(Utils.setCount(1, 3))
//                    )
//                    .with(
//                        ItemEntry.builder(Items.PAPER).weight(20)
//                            .apply(Utils.setCount(2, 7))
//                    )
//                    .with(ItemEntry.builder(Items.MAP))
//                    .with(ItemEntry.builder(Items.COMPASS))
//                    .with(
//                        ItemEntry.builder(Items.BOOK).weight(10)
//                            .apply(
//                                EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30.0f))
//                                    .allowTreasureEnchantments()
//                            )
//                    )
//            ).pool(
//                LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
//                    .with(ItemEntry.builder(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1))
//            )
//        )

    }

    companion object {
        private fun item(item: Item) = LootItem.lootTableItem(item)
    }
}