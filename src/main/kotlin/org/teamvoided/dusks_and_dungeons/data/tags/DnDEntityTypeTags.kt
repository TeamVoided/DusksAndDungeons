package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.world.entity.EntityType
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDEntityTypeTags {
    fun create(id: String): TagKey<EntityType<*>> = TagKey.create(Registries.ENTITY_TYPE, id(id))
}