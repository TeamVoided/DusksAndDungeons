package org.teamvoided.dusk_autumn.data

import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskBiomeTags {
    val IS_AUTUMN = create("is_autumn")
    @JvmField
    val SPAWNS_SILVER_FOXES = create("spawns_silver_foxes")

    fun create(id: String): TagKey<Biome> = TagKey.of(RegistryKeys.BIOME, id(id))
}