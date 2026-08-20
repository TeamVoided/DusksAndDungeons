package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDBiomes {

    val AUTUMN_WOODS = key("autumn_woods")
    val AUTUMN_PASTURES = key("autumn_pasture")
    val AUTUMN_CASCADES = key("autumn_cascade")

    val GOLDEN_WOODS = key("golden_woods")
    val GOLDEN_PASTURES = key("golden_pasture")

    val OVERGROWN_GROTTO = key("overgrown_grotto")


    fun key(id: String) = Registries.BIOME.key(id(id))

}
