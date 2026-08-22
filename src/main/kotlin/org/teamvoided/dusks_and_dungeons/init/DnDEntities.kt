package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntityType.EntityFactory
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.animal.Fox
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity
import org.teamvoided.dusks_and_dungeons.entity.ScarecrowEntity
import org.teamvoided.dusks_and_dungeons.entity.ThrownItemStack
import org.teamvoided.dusks_and_dungeons.util.doEvil
import org.teamvoided.dusks_and_dungeons.util.register

// TODO rename to DnDEntityTypes
object DnDEntities {

    val SCARECROW = register(
        "scarecrow", EntityType.Builder.of(EntityFactory(::ScarecrowEntity), MobCategory.MISC)
            .sized(0.5F, 2.4375f)
            .eyeHeight(2.2375F)
            .clientTrackingRange(10)
    )
    val RACCOON = register(
        "raccoon", EntityType.Builder.of(EntityFactory(::RaccoonEntity), MobCategory.CREATURE)
            .sized(0.6F, 0.7F)
            .eyeHeight(0.4F)
            .clientTrackingRange(8)
    )
    val THROWN_ITEM = register(
        "thrown_item", EntityType.Builder.of(EntityFactory(::ThrownItemStack), MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
    )

    fun init() {
        FabricDefaultAttributeRegistry.register(SCARECROW, ScarecrowEntity.createAttributes().build())
        FabricDefaultAttributeRegistry.register(RACCOON, Fox.createAttributes().build())
        doEvil()
    }

    fun <T : Entity> register(name: String, entityType: EntityType.Builder<T>): EntityType<T> {
        val id = id(name)
        return BuiltInRegistries.ENTITY_TYPE.register(id, entityType.build(id.toString()))
    }

}