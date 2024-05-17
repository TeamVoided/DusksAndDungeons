package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.entity.CrabEntity

object DuskEntities {
    val CRAB = register(
        "crab", EntityType.Builder
            .create(EntityType.EntityFactory(::CrabEntity), SpawnGroup.CREATURE)
            .setDimensions(0.5f, 0.5f)
            .maxTrackingRange(10)
    )
    fun init() {
        FabricDefaultAttributeRegistry.register(CRAB, MobEntity.createAttributes()
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 7.0)
            .build()
        )
    }
    fun <T : Entity> register(id: String, entityType: EntityType.Builder<T>): EntityType<T> =
        Registry.register(Registries.ENTITY_TYPE, id(id), entityType.build(id))
}