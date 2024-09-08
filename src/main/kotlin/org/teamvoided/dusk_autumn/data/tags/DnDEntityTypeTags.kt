package org.teamvoided.dusk_autumn.data.tags

import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DusksAndDungeons.id

object DnDEntityTypeTags {

    val CHILL_CHARGE_GOES_THROUGH = create("chill_charge_goes_through")

    fun create(id: String): TagKey<EntityType<*>> = TagKey.of(RegistryKeys.ENTITY_TYPE, id(id))
}