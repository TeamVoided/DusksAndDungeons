package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.BirdEntity
import org.teamvoided.dusk_autumn.entity.ChillChargeEntity

object DnDEntities {

    val CHILL_CHARGE = register(
        "chill_charge",
        EntityType.Builder.create(EntityType.EntityFactory(::ChillChargeEntity), SpawnGroup.MISC)
            .setDimensions(0.3125F, 0.3125F)
            .setEyeHeight(0.0F)
            .maxTrackingRange(4)
            .trackingTickInterval(10)
    )
    val BIRD_TEST = register(
        "bird",
        EntityType.Builder.create(EntityType.EntityFactory(::BirdEntity), SpawnGroup.CREATURE)
            .setDimensions(0.3125F, 0.625F)
            .setEyeHeight(0.55F)
            .maxTrackingRange(4)
            .trackingTickInterval(10)
    )

    fun init() {
        FabricDefaultAttributeRegistry.register(BIRD_TEST, BirdEntity.createAttributes().build())
    }

    fun <T : Entity> register(id: String, entityType: EntityType.Builder<T>): EntityType<T> =
        Registry.register(Registries.ENTITY_TYPE, id(id), entityType.build(id))
}