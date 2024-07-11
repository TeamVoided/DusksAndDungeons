package net.minecraft.entity.passive.org.teamvoided.dusk_autumn.data

import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object DuskEntityTypeTags {

    fun create(id: String): TagKey<EntityType<*>> = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier(id))
}