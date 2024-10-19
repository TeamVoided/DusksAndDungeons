package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import org.teamvoided.dusk_autumn.entity.DiceEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.ChillChargeEntity
import org.teamvoided.dusk_autumn.entity.DustBunnyEntity
import org.teamvoided.dusk_autumn.entity.ScarecrowEntity
import org.teamvoided.dusk_autumn.entity.projectile.FlyingPumpkinProjectile

object DnDEntities {

    val CHILL_CHARGE = register(
        "chill_charge",
        EntityType.Builder.create(EntityType.EntityFactory(::ChillChargeEntity), SpawnGroup.MISC)
            .setDimensions(0.3125F, 0.3125F)
            .setEyeHeight(0F)
            .maxTrackingRange(4)
            .trackingTickInterval(10)
    )
//    val BIRD_TEST = register(
//        "bird",
//        EntityType.Builder.create(EntityType.EntityFactory(::BirdEntity), SpawnGroup.CREATURE)
//            .setDimensions(0.3125F, 0.625F)
//            .setEyeHeight(0.55F)
//            .maxTrackingRange(4)
//            .trackingTickInterval(10)
//    )

    val SCARECROW = register(
        "scarecrow",
        EntityType.Builder.create(EntityType.EntityFactory(::ScarecrowEntity), SpawnGroup.MISC)
            .setDimensions(0.5F, 2.375F)
            .setEyeHeight(2.175F)
            .maxTrackingRange(10)
    )

    val DIE = register(
        "die",
        EntityType.Builder.create(EntityType.EntityFactory(::DiceEntity), SpawnGroup.MISC)
            .setDimensions(0.5F, 0.5F)
            .setEyeHeight(0F)
            .maxTrackingRange(4)
            .trackingTickInterval(10)
    )
    val FLYING_PUMPKIN = register(
        "flying_pumpkin",
        EntityType.Builder.create(EntityType.EntityFactory(::FlyingPumpkinProjectile), SpawnGroup.MISC)
            .setDimensions(0.5F, 0.5F)
            .setEyeHeight(0.25F)
            .maxTrackingRange(4)
            .trackingTickInterval(10)
    )
    val DUST_BUNNY = register(
        "dust_bunny",
        EntityType.Builder.create(EntityType.EntityFactory(::DustBunnyEntity), SpawnGroup.MONSTER)
            .setDimensions(2F, 2F)
            .setEyeHeight(1F)
            .maxTrackingRange(8)
            .trackingTickInterval(10)
            .makeFireImmune()
    )

    fun init() {
//        FabricDefaultAttributeRegistry.register(BIRD_TEST, BirdEntity.createAttributes().build())

        FabricDefaultAttributeRegistry.register(SCARECROW, ScarecrowEntity.createAttributes().build())

        FabricDefaultAttributeRegistry.register(DUST_BUNNY, DustBunnyEntity.createAttributes().build())
    }

    fun <T : Entity> register(id: String, entityType: EntityType.Builder<T>): EntityType<T> =
        Registry.register(Registries.ENTITY_TYPE, id(id), entityType.build(id))
}
