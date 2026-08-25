package org.teamvoided.dusks_and_dungeons.data.structure

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDTemplatePool {

    val AUTUMN_RUINS_SINGLE = create("autumn_ruins/single")

    fun create(id: String): ResourceKey<StructureTemplatePool> = Registries.TEMPLATE_POOL.key(id(id))

}
