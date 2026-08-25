package org.teamvoided.dusks_and_dungeons.util

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.players.PlayerList
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block.UPDATE_ALL
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.net.DnDLevelEventPayload


fun Level.setBlockAndUpdateFluid(pos: BlockPos, blockState: BlockState, updateFlag: Int = UPDATE_ALL) {
    setBlock(pos, blockState, updateFlag)
    val fluid = getFluidState(pos)?.type
    if (fluid != null) {
        scheduleTick(pos, fluid, fluid.getTickDelay(this))
    }
}

fun Level.dndLevelEvent(id: ResourceLocation, blockPos: BlockPos, data: Int) = dndLevelEvent(null, id, blockPos, data)
fun Level.dndLevelEvent(player: Player?, id: ResourceLocation, blockPos: BlockPos, data: Int) {
    (this as? ServerLevel)?.server
        ?.playerList
        ?.broadcast(
            player,
            blockPos.x.toDouble(),
            blockPos.y.toDouble(),
            blockPos.z.toDouble(),
            64.0,
            dimension(),
            DnDLevelEventPayload(id, blockPos, data)
        )
}

fun PlayerList.broadcast(
    originPlayer: Player?, x: Double, y: Double, z: Double,
    range: Double, dimension: ResourceKey<Level>, payload: CustomPacketPayload,
) {
    for (i in players.indices) {
        val player = players[i]
        if (player !== originPlayer && player.level().dimension() === dimension) {
            val dX = x - player.x
            val dY = y - player.y
            val dZ = z - player.z
            if (dX * dX + dY * dY + dZ * dZ < range * range) {
                ServerPlayNetworking.send(player, payload)
            }
        }
    }
}

fun Level.scheduleFluidTick(pos: BlockPos, state: BlockState) {
    scheduleTick(pos, state.fluidState.type, state.fluidState.type.getTickDelay(this))
}
