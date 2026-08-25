package org.teamvoided.voidlib.devin.provider

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.core.HolderLookup
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider

abstract class OpenBlockLootTableProvider protected constructor(o: FabricOutput, p: FutureProvider) :
    FabricBlockLootTableProvider(o, p) {

    fun getLookup(): HolderLookup.Provider = registries

}
