package org.teamvoided.dusk_autumn.init

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.projectile.ChillChargeEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DnDEntities {

    val CHILL_CHARGE = register(
        "chill_charge",
        EntityType.Builder.create(EntityType.EntityFactory(::ChillChargeEntity), SpawnGroup.MISC)
            .setDimensions(0.3125F, 0.3125F)
            .setEyeHeight(0.0F)
            .maxTrackingRange(4)
            .trackingTickInterval(10)
    )

    fun init() = Unit
    fun <T : Entity> register(id: String, entityType: EntityType.Builder<T>): EntityType<T> =
        Registry.register(Registries.ENTITY_TYPE, id(id), entityType.build(id))
}