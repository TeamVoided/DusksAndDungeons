package org.teamvoided.dusks_and_dungeons.data.litho

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDWorldgenModifiers {

    val DUSKS_AND_DUNGEONS_BIOMES_RULES = key("dusks_and_dungeons_biomes_rules")
    val ADD_VERDANT_MINESHAFT = key("add_verdant_mineshaft")
    val ADD_CORN_CROP = key("add_corn_crop")
    val ADD_CORN_PILE = key("add_corn_pile")

    fun key(id: String): ResourceKey<WorldgenModifier> = LithostitchedRegistries.WORLDGEN_MODIFIER.key(id(id))

}