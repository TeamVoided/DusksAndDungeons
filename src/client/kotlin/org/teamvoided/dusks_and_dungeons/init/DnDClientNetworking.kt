package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.TerrainParticle
import net.minecraft.client.player.LocalPlayer
import net.minecraft.core.BlockPos
import net.minecraft.util.Mth
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.block.CuttableHollowLogBlock
import org.teamvoided.dusks_and_dungeons.block.CuttableHollowLogBlock.Companion.AXIS
import org.teamvoided.dusks_and_dungeons.init.misc.DnDLevelEvents
import org.teamvoided.dusks_and_dungeons.net.DnDLevelEventPayload
import kotlin.math.max
import kotlin.math.min

object DnDClientNetworking {

    fun init() {
        ClientPlayNetworking.registerGlobalReceiver(DnDLevelEventPayload.ID) { payload, ctx ->
            handleDnDLevelEvents(payload, ctx.client(), ctx.player())
        }
    }

    fun handleDnDLevelEvents(event: DnDLevelEventPayload, client: Minecraft, player: LocalPlayer) {
        val level = client.level ?: error("Received and event with no level!")
        when (event.eventId) {
            DnDLevelEvents.CUT_HOLLOW_LOG -> {
                val pos = event.pos
                val state = level.getBlockState(pos)
                val block = state.block
                val voxelShape: VoxelShape
                if (block is CuttableHollowLogBlock) {
                    voxelShape = block.shapeMap[state.getValue(AXIS)]?.get(event.data) ?: Shapes.block()
                } else voxelShape = Shapes.block()

                if (voxelShape == Shapes.block()) {
                    println("blocked")
                }

                level.destroy(pos, state, voxelShape)
            }

            else -> {
                log.warn("Received unknown level event: {}", event.eventId)
            }
        }
    }

    fun ClientLevel.destroy(blockPos: BlockPos, blockState: BlockState, shape: VoxelShape) {
        val scaler = 0.25
        shape.forAllBoxes { minX, minY, minZ, maxX, maxY, maxZ ->
            val j = min(1.0, maxX - minX)
            val k = min(1.0, maxY - minY)
            val l = min(1.0, maxZ - minZ)
            val m = max(2, Mth.ceil(j / scaler))
            val n = max(2, Mth.ceil(k / scaler))
            val o = max(2, Mth.ceil(l / scaler))
            for (p in 0..<m) {
                for (q in 0..<n) {
                    for (r in 0..<o) {
                        val s = (p + 0.5) / m
                        val t = (q + 0.5) / n
                        val u = (r + 0.5) / o
                        val xOffset = s * j + minX
                        val yOffset = t * k + minY
                        val zOffset = u * l + minZ
                        Minecraft.getInstance().particleEngine.add(
                            TerrainParticle(
                                this,
                                blockPos.x + xOffset,
                                blockPos.y + yOffset,
                                blockPos.z + zOffset,
                                s - 0.5,
                                t - 0.5,
                                u - 0.5,
                                blockState,
                                blockPos
                            )
                        )
                    }
                }
            }
        }
    }


}