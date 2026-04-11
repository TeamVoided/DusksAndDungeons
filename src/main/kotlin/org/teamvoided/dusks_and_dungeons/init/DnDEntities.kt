package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.passive.FoxEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity

object DnDEntities {
    val SCARECROW = register(
        "scarecrow", EntityType.Builder.create(EntityType.EntityFactory(::ScarecrowEntity), SpawnGroup.MISC)
            .setDimensions(0.5F, 2.4375f)
            .setEyeHeight(2.2375F)
            .maxTrackingRange(10)
    )
    val RACCOON = register(
        "raccoon", EntityType.Builder.create(EntityType.EntityFactory(::RaccoonEntity), SpawnGroup.CREATURE)
            .setDimensions(0.6F, 0.7F)
            .setEyeHeight(0.4F)
            .maxTrackingRange(8)
    )

    fun init() {
        FabricDefaultAttributeRegistry.register(SCARECROW, ScarecrowEntity.createAttributes().build())
        FabricDefaultAttributeRegistry.register(RACCOON, FoxEntity.createAttributes().build())
    }

    fun <T : Entity> register(id: String, entityType: EntityType.Builder<T>): EntityType<T> =
        Registry.register(Registries.ENTITY_TYPE, id(id), entityType.build(id))
}
