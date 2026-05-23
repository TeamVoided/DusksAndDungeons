package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDBiomeTags {
    val IS_AUTUMN = create("is_autumn")
    val HAS_STRUCTURE_AUTUMN_RUINS = create("has_structure/autumn_ruins")

    @JvmField
    val SPAWNS_SILVER_FOXES = create("spawns_silver_foxes")
    val SPAWNS_AUTUMN_WOLVES = create("spawns_autumn_wolves")

    val HAS_GLACIERS = create("has_glaciers")

    fun create(id: String): TagKey<Biome> = TagKey.create(Registries.BIOME, id(id))
}