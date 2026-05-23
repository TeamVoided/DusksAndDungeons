package org.teamvoided.dusks_and_dungeons.data.structure

import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDStructurePools {

    val AUTUMN_RUINS_SINGLE = create("autumn_ruins/single")

    fun create(id: String): ResourceKey<StructureTemplatePool> =
        ResourceKey.create(Registries.TEMPLATE_POOL, id(id))
}
