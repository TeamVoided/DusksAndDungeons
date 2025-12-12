package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity

object DnDEntities {
    val SCARECROW = register(
        "scarecrow", EntityType.Builder.create(EntityType.EntityFactory(::ScarecrowEntity), SpawnGroup.MISC)
            .setDimensions(0.5F, 2.4375f)
            .setEyeHeight(2.2375F)
            .maxTrackingRange(10)
    )

    fun init() {
        FabricDefaultAttributeRegistry.register(SCARECROW, ScarecrowEntity.createAttributes().build())
    }

    fun <T : Entity> register(id: String, entityType: EntityType.Builder<T>): EntityType<T> =
        Registry.register(Registries.ENTITY_TYPE, id(id), entityType.build(id))
}
