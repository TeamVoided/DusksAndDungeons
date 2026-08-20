package org.teamvoided.dusks_and_dungeons.data.litho

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDBiomeInjectors {

    val OVERGROWN_GROTTO = key("overgrown_grotto")
    val AUTUMN_WOODS = key("autumn_woods")
    val AUTUMN_PASTURES = key("autumn_pastures")
    val AUTUMN_CASCADES = key("autumn_cascades")
    val GOLDEN_WOODS = key("golden_woods")
    val GOLDEN_PASTURES = key("golden_pastures")
    val GOLDEN_CASCADES = key("golden_cascades")

    fun key(id: String) = LithostitchedRegistries.BIOME_INJECTOR.key(id(id))

}