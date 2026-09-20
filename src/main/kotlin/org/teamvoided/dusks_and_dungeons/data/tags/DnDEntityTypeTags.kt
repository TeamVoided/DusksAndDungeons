package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.tag

object DnDEntityTypeTags {

    val ANTS_ATTACKS = key("ants_attack")

    fun key(id: String): TagKey<EntityType<*>> = Registries.ENTITY_TYPE.tag(id(id))

}