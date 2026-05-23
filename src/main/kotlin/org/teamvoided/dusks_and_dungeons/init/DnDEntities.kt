package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.animal.Fox
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.Registry
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity

object DnDEntities {
    val SCARECROW = register(
        "scarecrow", EntityType.Builder.of(EntityType.EntityFactory(::ScarecrowEntity), MobCategory.MISC)
            .sized(0.5F, 2.4375f)
            .eyeHeight(2.2375F)
            .clientTrackingRange(10)
    )
    val RACCOON = register(
        "raccoon", EntityType.Builder.of(EntityType.EntityFactory(::RaccoonEntity), MobCategory.CREATURE)
            .sized(0.6F, 0.7F)
            .eyeHeight(0.4F)
            .clientTrackingRange(8)
    )

    fun init() {
        FabricDefaultAttributeRegistry.register(SCARECROW, ScarecrowEntity.createAttributes().build())
        FabricDefaultAttributeRegistry.register(RACCOON, Fox.createAttributes().build())
    }

    fun <T : Entity> register(id: String, entityType: EntityType.Builder<T>): EntityType<T> =
        Registry.register(BuiltInRegistries.ENTITY_TYPE, id(id), entityType.build(id))
}
