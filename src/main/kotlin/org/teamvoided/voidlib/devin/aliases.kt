package org.teamvoided.voidlib.devin

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

typealias FDOutput = FabricDataOutput // TODO Change this to Pack before publish
typealias FutureLookup = CompletableFuture<HolderLookup.Provider>

