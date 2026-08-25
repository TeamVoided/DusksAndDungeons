package org.teamvoided.dusks_and_dungeons.data.gen.data.tag

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.EntityTypeTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.EntityTypeTags.CAN_BREATHE_UNDER_WATER
import org.teamvoided.dusks_and_dungeons.init.DnDEntities
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider

class EntityTypeTagProvider(o: FabricOutput, p: FutureProvider) : EntityTypeTagProvider(o, p) {

    override fun addTags(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(CAN_BREATHE_UNDER_WATER)
            .add(DnDEntities.SCARECROW)
    }

}