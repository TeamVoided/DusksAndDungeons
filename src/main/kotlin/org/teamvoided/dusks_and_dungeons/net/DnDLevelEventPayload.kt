package org.teamvoided.dusks_and_dungeons.net

import io.netty.buffer.ByteBuf
import net.minecraft.core.BlockPos
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

class DnDLevelEventPayload(val eventId: ResourceLocation, val pos: BlockPos, val data: Int) : CustomPacketPayload {

    override fun type(): CustomPacketPayload.Type<DnDLevelEventPayload> = ID

    companion object {
        val ID = CustomPacketPayload.Type<DnDLevelEventPayload>(id("level_event"))
        val CODEC: StreamCodec<ByteBuf, DnDLevelEventPayload> = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, DnDLevelEventPayload::eventId,
            BlockPos.STREAM_CODEC, DnDLevelEventPayload::pos,
            ByteBufCodecs.INT, DnDLevelEventPayload::data,
            ::DnDLevelEventPayload
        )
    }
}