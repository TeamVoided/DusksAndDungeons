package org.teamvoided.dusk_autumn.init

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskEntities {
    fun init() = Unit
    fun <T : Entity> register(id: String, entityType: EntityType.Builder<T>): EntityType<T> =
        Registry.register(Registries.ENTITY_TYPE, id(id), entityType.build(id))
}