package net.minecraft.entity.passive.org.teamvoided.dusk_autumn.data

import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskEntityTypeTags {

    fun create(id: String): TagKey<EntityType<*>> = TagKey.of(RegistryKeys.ENTITY_TYPE, id(id))
}