package org.teamvoided.dusks_and_dungeons.data.variants

import net.minecraft.world.entity.animal.WolfVariant
import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDWolfVariants {
    val AUTUMN = create("autumn")
    fun create(id: String): ResourceKey<WolfVariant> = ResourceKey.create(Registries.WOLF_VARIANT, id(id))
}