package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.tag

object DnDBiomeTags {

    val IS_AUTUMN = key("is_autumn")
    val IS_CAVE = key("is_cave")

    // Replacer
    val AUTUMN_WOODS = key("autumn_replacer/woods")
    val AUTUMN_PASTURES = key("autumn_replacer/pastures")
    val AUTUMN_RIVERS = key("autumn_replacer/river")

    // Has Structure
    val HAS_STRUCTURE_AUTUMN_RUINS = key("has_structure/autumn_ruins")

    // Spawns Mobs
    @JvmField
    val SPAWNS_SILVER_FOXES = key("spawns_silver_foxes")
    val SPAWNS_AUTUMN_WOLVES = key("spawns_autumn_wolves")

    // Features
    val GOLD_MUSHROOMS_CAVE = features("golden_mushroom/cave")
    val GOLD_MUSHROOMS_SURFACE = features("golden_mushroom/surface")
    val GOLD_MUSHROOMS_HUGE = features("golden_mushroom/huge")

    val MOSSKIN_PUMPKINS_CAVE = features("pumpkins/mosskin_cave")
    val GLOOM_PUMPKINS_EXTRA = features("pumpkins/gloom_extra")

    val CRIMSON_WART = features("nether/crimson_wart")
    val WARPED_WART = features("nether/warped_wart")

    // TODO(1.0) remove
    val HAS_GLACIERS = key("has_glaciers")

    fun features(id: String): TagKey<Biome> = key("features/$id")

    fun key(id: String): TagKey<Biome> = Registries.BIOME.tag(id(id))

}