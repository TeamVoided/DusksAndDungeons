package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.storage.loot.LootTable
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.tag

object DnDLootTableTags {

    fun key(id: String): TagKey<LootTable> = Registries.LOOT_TABLE.tag(id(id))

}