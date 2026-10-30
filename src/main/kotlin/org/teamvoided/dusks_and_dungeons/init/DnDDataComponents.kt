package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraContents
import org.teamvoided.dusks_and_dungeons.util.register
import java.util.function.UnaryOperator

object DnDDataComponents {

    val CANDELABRA_CONTENTS = register("candelabra_contents") { builder ->
        builder
            .persistent(CandelabraContents.CODEC)
            .networkSynchronized(CandelabraContents.STREAM_CODEC)
            .cacheEncoding()
    }

    fun init() = Unit

    fun <T> register(name: String, op: UnaryOperator<DataComponentType.Builder<T>>): DataComponentType<T> {
        return BuiltInRegistries.DATA_COMPONENT_TYPE.register(id(name), op.apply(DataComponentType.builder()).build())
    }

}