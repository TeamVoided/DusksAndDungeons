package org.teamvoided.dusk_autumn.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.EntityType
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.EntityTypeTags
import org.teamvoided.dusk_autumn.data.tags.DnDEntityTypeTags
import org.teamvoided.dusk_autumn.init.DnDEntities
import java.util.concurrent.CompletableFuture

class EntityTypeTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {


    override fun configure(arg: HolderLookup.Provider) {
        duskTags()
        vanillaTags()
    }

    private fun duskTags() {
//        getOrCreateTagBuilder(DnDEntityTypeTags.CHILL_CHARGE_GOES_THROUGH)
//            .add(DnDEntities.CHILL_CHARGE)
//            .add(EntityType.END_CRYSTAL)
    }

    private fun vanillaTags() {
//        getOrCreateTagBuilder(EntityTypeTags.IMPACT_PROJECTILES)
//            .add(DnDEntities.CHILL_CHARGE)
//        getOrCreateTagBuilder(EntityTypeTags.REDIRECTABLE_PROJECTILE)
//            .add(DnDEntities.CHILL_CHARGE)
    }

    private fun conventionTags() {}

}