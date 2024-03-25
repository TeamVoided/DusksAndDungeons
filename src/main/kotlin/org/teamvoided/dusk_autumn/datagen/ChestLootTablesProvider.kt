package org.teamvoided.dusk_autumn.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.EnchantWithLevelsLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.data.DuskLootTables
import java.util.function.BiConsumer

class ChestLootTablesProvider(o: FabricDataOutput) : SimpleFabricLootTableProvider(o, LootContextTypes.CHEST) {
    override fun generate(gen: BiConsumer<Identifier, LootTable.Builder>) {
        fun uniformNum(x: Number, y: Number): UniformLootNumberProvider =
        UniformLootNumberProvider.create(x.toFloat(), y.toFloat())
        fun setCount(x: Number, y: Number) = SetCountLootFunction.builder(uniformNum(x, y))

        // eStrongholdLibraryLootTable
        gen.accept(
            DuskLootTables.COOL_CHEST,
            LootTable.builder().pool(
                LootPool.builder().rolls(uniformNum(2, 10))
                    .with(
                        ItemEntry.builder(Items.BOOK).weight(20)
                            .apply(setCount(1, 3))
                    )
                    .with(
                        ItemEntry.builder(Items.PAPER).weight(20)
                            .apply(setCount(2, 7))
                    )
                    .with(ItemEntry.builder(Items.MAP))
                    .with(ItemEntry.builder(Items.COMPASS))
                    .with(
                        ItemEntry.builder(Items.BOOK).weight(10)
                            .apply(
                                EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30.0f))
                                    .allowTreasureEnchantments()
                            )
                    )
            ).pool(
                LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1))
            )
        )

    }

    companion object {
        private fun item(item: Item) = ItemEntry.builder(item)
    }
}