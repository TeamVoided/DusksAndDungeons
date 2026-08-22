package org.teamvoided.dusks_and_dungeons.data.registry

import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDDamageTypes {

    val FISSURE = create("fissure")

    fun create(id: String) = Registries.DAMAGE_TYPE.key(id(id))

}