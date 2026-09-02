package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.TerrainParticle
import net.minecraft.client.player.LocalPlayer
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.CuttableHollowLogBlock
import org.teamvoided.dusks_and_dungeons.init.misc.DnDLevelEvents
import kotlin.math.max
import kotlin.math.min

object DnDLevelEventHandlers {

    val ID_HANDLERS = mutableMapOf<ResourceLocation, LevelEventHandler>()
    val NO_ID_HANDLERS = mutableListOf<LevelEventHandler>()

    fun init() {
        ID_HANDLERS[DnDLevelEvents.CUT_HOLLOW_LOG] = LevelEventHandler(::handleCutHollowLog)
    }

    fun interface LevelEventHandler {

        fun triggerEvent(client: Minecraft, level: ClientLevel, player: LocalPlayer, pos: BlockPos, data: Int)

    }

    fun handleCutHollowLog(client: Minecraft, level: ClientLevel, player: LocalPlayer, pos: BlockPos, data: Int) {
        val state = level.getBlockState(pos)
        val block = state.block
        var particleDensity = 0.3
        val voxelShape = if (block is CuttableHollowLogBlock) {
            particleDensity = block.getParticleDensity()
            block.shapeMap[state.getValue(RotatedPillarBlock.AXIS)]?.getOrNull(data) ?: Shapes.block()
        } else
            Shapes.block()

        level.flatDestroyParticles(pos, state, voxelShape, particleDensity)
    }

    fun ClientLevel.flatDestroyParticles(pos: BlockPos, state: BlockState, shape: VoxelShape, density: Double = 0.25) {
        if (!state.shouldSpawnTerrainParticles()) return

        shape.forAllBoxes { x1, y1, z1, x2, y2, z2 ->
            val widthX = min(1.0, x2 - x1)
            val widthY = min(1.0, y2 - y1)
            val widthZ = min(1.0, z2 - z1)
            val countX = max(2, Mth.ceil(widthX / density))
            val countY = max(2, Mth.ceil(widthY / density))
            val countZ = max(2, Mth.ceil(widthZ / density))
            for (xx in 0..<countX) {
                for (yy in 0..<countY) {
                    for (zz in 0..<countZ) {
                        val relX = (xx + 0.5) / countX
                        val relY = (yy + 0.5) / countY
                        val relZ = (zz + 0.5) / countZ
                        val x = relX * widthX + x1
                        val y = relY * widthY + y1
                        val z = relZ * widthZ + z1
                        Minecraft.getInstance().particleEngine.add(
                            TerrainParticle(
                                this,
                                pos.x + x, pos.y + y, pos.z + z,
                                0.0, -1.0, 0.0,
                                // Vanilla destroy partile logic, in case you need to for something
//                                relX - 0.5, relY - 0.5, relZ - 0.5,
                                state,
                                pos
                            )
                        )
                    }
                }
            }
        }
    }

}