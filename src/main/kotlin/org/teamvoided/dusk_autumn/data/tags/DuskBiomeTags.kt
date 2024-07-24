package org.teamvoided.dusk_autumn.data.tags

import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskBiomeTags {
    val IS_AUTUMN = create("is_autumn")
    val HAS_STRUCTURE_AUTUMN_RUINS = create("has_structure/autumn_ruins")
    @JvmField
    val SPAWNS_SILVER_FOXES = create("spawns_silver_foxes")

    fun create(id: String): TagKey<Biome> = TagKey.of(RegistryKeys.BIOME, id(id))
}