package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.registry.HolderLookup
import org.teamvoided.transition.api.data.gen.MappingsProvider
import java.util.concurrent.CompletableFuture

class DnDMappingsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : MappingsProvider(o, r) {
    override fun makeMappings(lookup: HolderLookup.Provider, gen: MappingBuilder) {
        gen.addOldNamespace("dusk_autumn")
        gen.addOldPathMapping("rocky_rocks", "balls")
        gen.addOldPathMapping("slated_slate", "balls2")
        gen.addOldPathMapping("blackstoned_blackstone", "balls3")
    }
}
