package org.teamvoided.dusks_and_dungeons.util.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.registry.HolderLookup
import java.util.concurrent.CompletableFuture

abstract class OpenBlockLootTableProvider protected constructor(
    o: FabricDataOutput,
    r: CompletableFuture<HolderLookup.Provider>
) : FabricBlockLootTableProvider(o, r) {
    fun getLookup(): HolderLookup.Provider = field_51845
}
