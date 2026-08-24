package org.teamvoided.voidlib.devin

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

// TODO rename to be less messy and use everywhere
typealias FDOutput = FabricDataOutput
typealias FutureLookup = CompletableFuture<HolderLookup.Provider>

