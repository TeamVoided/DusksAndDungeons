package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraContents
import org.teamvoided.dusks_and_dungeons.util.register

object DnDDataComponents {

    val CANDELABRA_CONTENTS = register("candelabra_contents") {
        it
            .persistent(CandelabraContents.CODEC)
            .networkSynchronized(CandelabraContents.STREAM_CODEC)
            .build()
    }

    fun init() = Unit

    fun <T> register(
        name: String, build: (DataComponentType.Builder<T>) -> DataComponentType<T>,
    ): DataComponentType<T> {
        return BuiltInRegistries.DATA_COMPONENT_TYPE.register(id(name), build(DataComponentType.builder()))
    }

}