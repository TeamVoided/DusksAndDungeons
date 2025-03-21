package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.registry.HolderLookup
import org.teamvoided.transition.api.data.gen.MappingsProvider
import java.util.concurrent.CompletableFuture

class DnDMappingsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : MappingsProvider(o, r) {
    override fun makeMappings(lookup: HolderLookup.Provider, gen: MappingBuilder) {
        gen.addOldNamespace("dusk_autumn")
        gen.addOldPathMapping("rocky_rocks", "rocky_gravel")
        gen.addOldPathMapping("slated_slate", "slated_gravel")
        gen.addOldPathMapping("blackstoned_blackstone", "blackstoned_gravel")
    }
}
