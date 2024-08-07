package net.minecraft.world.gen.feature.org.teamvoided.dusk_autumn.init.worldgen

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import org.teamvoided.dusk_autumn.data.tags.DnDBiomeTags
import org.teamvoided.reef.events.CustomSurfaceBuilder

object DnDSurfaceBuilders {
    fun init() {
        CustomSurfaceBuilder.POST_VANILLA.register { defaultBlock, seaLevel, biome, chunk, blockColumn, x, z ->
            val y = chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, x, z) + 1

            if (biome.getBiome(BlockPos(x, y, z)).isIn(DnDBiomeTags.HAS_GLACIERS)) {
                val bias = 100
                val glacierYLevel: Int = ((y - bias) * 0.175 + bias).toInt()
                for (yLevel in glacierYLevel downTo y) {
                    if (yLevel % 4 == 0 && !biome.getBiome(BlockPos(x, yLevel, z)).isIn(DnDBiomeTags.HAS_GLACIERS)) {
                        break
                    }
                    if (yLevel < seaLevel)
                        blockColumn.setState(yLevel, Blocks.PACKED_ICE.defaultState)
                    else
                        blockColumn.setState(yLevel, Blocks.ICE.defaultState)
                }
            }
        }
    }
}