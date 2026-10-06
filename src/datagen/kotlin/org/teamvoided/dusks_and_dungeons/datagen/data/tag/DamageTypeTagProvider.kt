package org.teamvoided.dusks_and_dungeons.datagen.data.tag

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.damagesource.DamageType
import org.teamvoided.dusks_and_dungeons.data.registry.DnDDamageTypes
import org.teamvoided.dusks_and_dungeons.data.tags.DnDDamageTypeTags
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider

class DamageTypeTagProvider(o: FabricOutput, p: FutureProvider) :
    FabricTagProvider<DamageType>(o, Registries.DAMAGE_TYPE, p) {

    override fun addTags(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(DnDDamageTypeTags.BRICK_DAMAGE)
            .add(DnDDamageTypes.THROWN_BRICK)
    }
}