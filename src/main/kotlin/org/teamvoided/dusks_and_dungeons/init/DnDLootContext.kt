package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.world.entity.Entity
import net.minecraft.world.level.storage.loot.parameters.LootContextParam
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.mixin.accessors.LootContextParamSetsAccessor
import java.util.function.Consumer

@Suppress("SameParameterValue")
object DnDLootContext {

    val INTERACTING_ENTITY = create<Entity>("interacting_entity")

    val BLOCK_INTERACT = register("block_interact") { builder ->
        builder.required(LootContextParams.BLOCK_STATE)
            .required(LootContextParams.ORIGIN)
            .optional(LootContextParams.BLOCK_ENTITY)
            .optional(INTERACTING_ENTITY)
            .optional(LootContextParams.TOOL)
    }

    fun init() = Unit

    private fun register(name: String, build: Consumer<LootContextParamSet.Builder>): LootContextParamSet {
        val builder = LootContextParamSet.Builder()
        build.accept(builder)
        val set = builder.build()
        val id = id(name)
        val oldSet = LootContextParamSetsAccessor.dnd_REGISTRY().put(id, set)
        check(oldSet == null) { "Loot table parameter set $id is already registered" }
        return set
    }

    private fun <T> create(id: String): LootContextParam<T> = LootContextParam(id(id))

}