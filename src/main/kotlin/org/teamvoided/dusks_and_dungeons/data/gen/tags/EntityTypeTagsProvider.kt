package org.teamvoided.dusks_and_dungeons.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.EntityTypeTags
import org.teamvoided.dusks_and_dungeons.init.DnDEntities
import java.util.concurrent.CompletableFuture

class EntityTypeTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {


    override fun configure(arg: HolderLookup.Provider) {
        duskTags()
        vanillaTags()
    }

    private fun duskTags() {
    }

    private fun vanillaTags() {
        getOrCreateTagBuilder(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
            .add(DnDEntities.SCARECROW)
    }

    private fun conventionTags() {}
}