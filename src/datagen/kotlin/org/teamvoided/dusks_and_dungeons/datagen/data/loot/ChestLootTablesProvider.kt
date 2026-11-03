package org.teamvoided.dusks_and_dungeons.datagen.data.loot

import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootPool.lootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.LootTable.lootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets.CHEST
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import org.teamvoided.dusks_and_dungeons.data.DnDLootTables
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import java.util.function.BiConsumer

class ChestLootTablesProvider(o: FabricOutput, p: FutureProvider) : SimpleFabricLootTableProvider(o, p, CHEST) {

    override fun generate(gen: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {
        gen.lootInjections()
    }

    fun BiConsumer<ResourceKey<LootTable>, LootTable.Builder>.lootInjections() {
        accept(
            DnDLootTables.ADD_CORN,
            lootTable().withPool(
                lootPool()
                    .setRolls(UniformGenerator.between(0f, 3f))
                    .add(item(DnDItems.CORN).setCount(1, 3))
                    .add(item(DnDItems.CORN_KERNELS).setCount(2, 6))
            )
        )
    }

}