package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.entity.EntityType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.tag

object DnDDamageTypeTags {

    val BRICK_DAMAGE = key("brick_damage")

    fun key(id: String): TagKey<DamageType> = Registries.DAMAGE_TYPE.tag(id(id))

}