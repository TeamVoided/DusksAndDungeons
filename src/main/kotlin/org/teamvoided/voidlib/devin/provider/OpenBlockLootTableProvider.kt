package org.teamvoided.voidlib.devin.provider

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.registry.HolderLookup
import java.util.concurrent.CompletableFuture

typealias FDOutput = FabricDataOutput
typealias FutureLookup = CompletableFuture<HolderLookup.Provider>

abstract class OpenBlockLootTableProvider protected constructor(o: FDOutput, r: FutureLookup) :
    FabricBlockLootTableProvider(o, r) {
    fun getLookup(): HolderLookup.Provider = field_51845
}
