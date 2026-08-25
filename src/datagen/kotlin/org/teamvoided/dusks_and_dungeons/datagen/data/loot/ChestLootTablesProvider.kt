package org.teamvoided.dusks_and_dungeons.datagen.data.loot

import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets.CHEST
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import java.util.function.BiConsumer

class ChestLootTablesProvider(o: FabricOutput, p: FutureProvider) : SimpleFabricLootTableProvider(o, p, CHEST) {

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

}