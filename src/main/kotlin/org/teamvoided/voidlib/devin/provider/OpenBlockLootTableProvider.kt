package org.teamvoided.voidlib.devin.provider

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.core.HolderLookup
import org.teamvoided.voidlib.devin.FDOutput
import org.teamvoided.voidlib.devin.FutureLookup

abstract class OpenBlockLootTableProvider protected constructor(o: FDOutput, r: FutureLookup) :
    FabricBlockLootTableProvider(o, r) {
    fun getLookup(): HolderLookup.Provider = this.registries
}
