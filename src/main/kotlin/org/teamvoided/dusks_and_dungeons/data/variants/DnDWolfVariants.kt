package org.teamvoided.dusks_and_dungeons.data.variants

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.animal.WolfVariant
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDWolfVariants {

    val AUTUMN = create("autumn")

    fun create(id: String): ResourceKey<WolfVariant> = Registries.WOLF_VARIANT.key(id(id))

}