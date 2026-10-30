package org.teamvoided.dusks_and_dungeons.data

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.storage.loot.LootTable
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDLootTables {

    val LOOT_TABLES = mutableSetOf<ResourceKey<LootTable>>()

    val ADD_MOONBERRY = inject("add_moonberries")
    val ADD_VIVIONS = inject("add_vivions")
    // Adds graves & pumpkins to Dungeons
    val ADD_SPOOKY = inject("add_spooky")
    val ADD_DND_SEEDS = inject("add_dnd_seeds")
    val ADD_CORN = inject("add_corn")

    fun inject(name: String): ResourceKey<LootTable> = key("inject/$name")
    fun key(name: String): ResourceKey<LootTable> = key(id(name))

    fun key(id: ResourceLocation): ResourceKey<LootTable> {
        val key = Registries.LOOT_TABLE.key(id)
        if (LOOT_TABLES.add(key)) {
            return key
        }
        throw IllegalArgumentException("$id is already a registered built-in loot table")
    }

}