package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDEntityTypeTags {
    fun create(id: String): TagKey<EntityType<*>> = TagKey.of(RegistryKeys.ENTITY_TYPE, id(id))
}