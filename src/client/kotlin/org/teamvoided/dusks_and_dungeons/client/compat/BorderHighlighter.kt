package org.teamvoided.dusks_and_dungeons.client.compat

import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.border.WorldBorder
import xaero.map.highlight.ChunkHighlighter

object BorderHighlighter : ChunkHighlighter(false) {

    val CLIENT: Minecraft = Minecraft.getInstance()

    override fun regionHasHighlights(dimension: ResourceKey<Level>, regionX: Int, regionZ: Int): Boolean {
        return true
//        !(isWithinBounds(ChunkPos.maxFromRegion(regionX, regionX)) || getBorder().isWithinBounds(ChunkPos.minFromRegion(regionX, regionX)))
    }

    fun getBorder(): WorldBorder = CLIENT.level!!.worldBorder

    override fun getColors(dimension: ResourceKey<Level>, chunkX: Int, chunkZ: Int): IntArray? {
        if (!chunkIsHighlit(dimension, chunkX, chunkZ)) {
            return null
        }

        return resultStore
    }


    override fun getChunkHighlitColor(dimension: ResourceKey<Level>, chunkX: Int, chunkZ: Int): IntArray? {
        val colors = getColors(dimension, chunkX, chunkZ)
        if (colors == null) {
            return null
        } else {
            val sideColor = 0xff_ff_00_c0.toInt()
            val centerColor = 0xff_ff_00_50.toInt()
            val border = getBorder()
            for (x in 0..15) {
                for (z in 0..15) {
                    when (border.isInBounds(chunkX, chunkZ, x, z)) {
                        1 -> setResult(x, z, centerColor)
                        0 -> setResult(x, z, sideColor)
                        else -> setResult(x, z, 0x0)
                    }
                }
            }

            return resultStore
        }
    }

    private fun WorldBorder.isInBounds(chunkX: Int, chunkZ: Int, x: Int, z: Int): Int {
        val pos = ChunkPos(chunkX, chunkZ).getBlockAt(x, 0, z)
        val d = getDistanceToBorder(pos.x.toDouble(), pos.z.toDouble())
        if (d < -0.125) {
            return 1
        } else if (d > 0.125) {
            return -1
        }
        return 0
    }

    private fun getSideBlend(color1: Int, color2: Int, centerColor: Int): Int {
        return getBlend(
            if (color1 == centerColor) color2 else color1,
            if (color2 == centerColor) color1 else color2
        )
    }

    override fun calculateRegionHash(dimension: ResourceKey<Level>, regionX: Int, regionZ: Int): Int {
        return 412365
    }

    override fun chunkIsHighlit(dimension: ResourceKey<Level>, chunkX: Int, chunkZ: Int): Boolean {
        return !getBorder().isWithinBounds(ChunkPos(chunkX, chunkZ))
    }

    override fun getChunkHighlightSubtleTooltip(
        dimension: ResourceKey<Level>,
        chunkX: Int,
        chunkZ: Int,
    ): Component {
        return Component.literal("subtle!")
    }

    override fun getChunkHighlightBluntTooltip(dimension: ResourceKey<Level>, chunkX: Int, chunkZ: Int): Component {
        return Component.empty()
    }

    override fun addMinimapBlockHighlightTooltips(
        list: MutableList<Component>, dimension: ResourceKey<Level>, blockX: Int, blockZ: Int, width: Int,
    ) {
        list.add(Component.literal("minimap tooltip!"))
    }

}