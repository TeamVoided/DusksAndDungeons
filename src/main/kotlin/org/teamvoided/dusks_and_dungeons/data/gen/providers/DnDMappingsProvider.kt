package org.teamvoided.dusks_and_dungeons.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.registry.HolderLookup
import org.teamvoided.transition.api.data.gen.MappingsProvider
import java.util.concurrent.CompletableFuture

class DnDMappingsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : MappingsProvider(o, r) {
    override fun makeMappings(lookup: HolderLookup.Provider, gen: MappingBuilder) {
        gen.addOldNamespace("dusk_autumn")
        gen.addOldPathMapping("rocky_gravel", "rocky_rocks")
        gen.addOldPathMapping("slated_gravel", "slated_slate")
        gen.addOldPathMapping("blackstoned_gravel", "blackstoned_blackstone")
        gen.addOldPathMapping("cold_wildflower", "wild_petals")

        gen.addOldPathMapping("mixed_red_nether_bricks", "mixed_nether_bricks")
        gen.addOldPathMapping("mixed_red_nether_brick_stairs", "mixed_nether_brick_stairs")
        gen.addOldPathMapping("mixed_red_nether_brick_slab", "mixed_nether_brick_slab")
        gen.addOldPathMapping("mixed_red_nether_brick_wall", "mixed_nether_brick_wall")
        gen.addOldPathMapping("mixed_red_nether_brick_fence", "mixed_nether_brick_fence")

        gen.addOldPathMapping("cracked_mixed_red_nether_bricks", "cracked_mixed_nether_bricks")
        gen.addOldPathMapping("chiseled_mixed_red_nether_bricks", "chiseled_mixed_nether_bricks")

        gen.addOldPathMapping("mixed_red_nether_brick_pillar", "mixed_nether_brick_pillar")
    }
}
