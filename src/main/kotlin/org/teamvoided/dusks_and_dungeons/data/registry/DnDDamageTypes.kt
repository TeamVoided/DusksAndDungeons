package org.teamvoided.dusks_and_dungeons.data.registry

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.damagesource.DamageType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDDamageTypes {

    val THROWN_BRICK = create("thrown_brick")

    fun create(id: String): ResourceKey<DamageType> = Registries.DAMAGE_TYPE.key(id(id))

}