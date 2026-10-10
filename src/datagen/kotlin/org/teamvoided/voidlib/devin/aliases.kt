/*
 * TODO(cleanup)
 */

package org.teamvoided.voidlib.devin

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

typealias FabricOutput = FabricDataOutput
typealias FutureProvider = CompletableFuture<HolderLookup.Provider>

