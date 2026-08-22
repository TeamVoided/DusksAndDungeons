package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDBiomeTags {
    val AUTUMN_WOODS = create("autumn_replacer/woods")
    val AUTUMN_PASTURES = create("autumn_replacer/pastures")
    val AUTUMN_RIVERS = create("autumn_replacer/river")
    val IS_AUTUMN = create("is_autumn")
    val HAS_STRUCTURE_AUTUMN_RUINS = create("has_structure/autumn_ruins")

    val IS_DUSK_CAVE = create("is_cave")

    @JvmField
    val SPAWNS_SILVER_FOXES = create("spawns_silver_foxes")
    val SPAWNS_AUTUMN_WOLVES = create("spawns_autumn_wolves")

    val GOLD_MUSHROOMS_CAVE = create("features/golden_mushroom_cave")
    val GOLD_MUSHROOMS_SURFACE = create("features/golden_mushroom_surface")
    val GOLD_MUSHROOMS_HUGE = create("features/golden_mushroom_huge")


    val HAS_GLACIERS = create("has_glaciers")

    fun create(id: String): TagKey<Biome> = TagKey.create(Registries.BIOME, id(id))
}