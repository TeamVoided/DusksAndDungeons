package org.teamvoided.dusks_and_dungeons.data.litho

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDBiomeInjectors {

    val AUTUMN_WOODS = key("autumn_woods")
    val AUTUMN_PASTURES = key("autumn_pastures")
    val AUTUMN_CASCADES = key("autumn_cascades")

    fun key(id: String) = LithostitchedRegistries.BIOME_INJECTOR.key(id(id))

}