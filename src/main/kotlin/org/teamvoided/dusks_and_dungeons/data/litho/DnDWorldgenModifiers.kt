package org.teamvoided.dusks_and_dungeons.data.litho

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDWorldgenModifiers {

    val DUSKS_AND_DUNGEONS_BIOMES_RULES = key("dusks_and_dungeons_biomes_rules")
    val ADD_VERDANT_MINESHAFT = key("add_verdant_mineshaft")
    val ADD_CORN = key("add_corn")
    val ADD_CORN_PILE = key("add_corn_pile")

    fun key(id: String) = LithostitchedRegistries.WORLDGEN_MODIFIER.key(id(id))

}