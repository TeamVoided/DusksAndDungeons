package org.teamvoided.dusk_autumn.init.worldgen

import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.chunk.BlockColumn
import org.teamvoided.dusk_autumn.data.tags.DnDBiomeTags
import org.teamvoided.dusk_autumn.data.worldgen.DnDNoise
import org.teamvoided.reef.api.events.CustomSurfaceBuilder
import kotlin.math.absoluteValue
import kotlin.math.ceil

object DnDSurfaceBuilders {
    var glacierIce: DoublePerlinNoiseSampler? = null
    var glacierJaggedness: DoublePerlinNoiseSampler? =
        null //roof offset will most likely turn into the jagged spike region instead of an unnecessary offset value
    var glacierSnow: DoublePerlinNoiseSampler? = null
    var glacierWaterRoof: DoublePerlinNoiseSampler? = null

    fun init() {
        CustomSurfaceBuilder.POST_RULES.register { random, defaultBlock, seaLevel, biome, chunk, blockColumn, x, z ->
            if (glacierIce == null) glacierIce =
                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_ICE_PICKER)
            if (glacierJaggedness == null) glacierJaggedness =
                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_JAGGEDNESS)
            if (glacierSnow == null) glacierSnow =
                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_SNOW_SURFACE)
            if (glacierWaterRoof == null) glacierWaterRoof =
                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_WATER_ROOF)

            val y = chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, x, z) + 1

            if (biome.getBiome(BlockPos(x, y, z)).isIn(DnDBiomeTags.HAS_GLACIERS)) {
                val glacierJaggednessRange: Double = glacierJaggedness!!.sample(x.toDouble(), 0.0, z.toDouble())
                val glacierJaggedness: Double = if (glacierJaggednessRange > 0) {
                    glacierJaggednessRange * (halfNegative(
                        glacierJaggedness!!.sample(
                            1500 * x.toDouble(),
                            0.0,
                            1500 * z.toDouble()
                        )
                    ) * 40)
                } else 0.0

                val bias = seaLevel + 40
                val glacierYLevel: Int = ((y - bias) * 0.175 + bias + glacierJaggedness).toInt()

                val snowSurface: Double = glacierSnow!!.sample(x * 0.75, 0.0, z * 0.75) * 1.5
                val snowLevel: Int = (glacierYLevel + ceil(snowSurface * 10)).toInt()

                //they are seperate because I want to see them differently, i dont care if they are combined
                if (y < seaLevel) {
                    val glacierWater: Double = glacierWaterRoof!!.sample(x * 0.75, 0.0, z * 0.75) * 1.5
                    for (yLevel in glacierYLevel downTo y) {
                        if (glacierWater > (halfNegative((4 + yLevel - glacierYLevel) * 0.2)).absoluteValue) {
                            val glacierIce: Double = glacierIce!!.sample(x * 0.75, yLevel * 0.75, z * 0.75) * 1.5
                            placeGlacierBlock(yLevel, blockColumn, snowLevel, glacierIce)
                        }
                    }
                } else {
                    for (yLevel in glacierYLevel downTo y) {
                        if (yLevel % 4 == 0 && !biome.getBiome(BlockPos(x, yLevel, z))
                                .isIn(DnDBiomeTags.HAS_GLACIERS)
                        ) {
                            break
                        }
                        val glacierIce: Double = glacierIce!!.sample(x * 0.75, yLevel * 0.75, z * 0.75) * 1.5
                        placeGlacierBlock(yLevel, blockColumn, snowLevel, glacierIce)
                    }
                }
            }
        }
    }

    private fun halfNegative(double: Double): Double {
        return if (double < 0) double / 2 else double
    }

    private fun placeGlacierBlock(yLevel: Int, blockColumn: BlockColumn, snowLevel: Int, icePicker: Double) {
        if (yLevel > snowLevel)
            if (yLevel - snowLevel > 8)
                blockColumn.setState(yLevel, Blocks.POWDER_SNOW.defaultState)
            else
                blockColumn.setState(yLevel, Blocks.SNOW_BLOCK.defaultState)
        else if (icePicker > 0)
            blockColumn.setState(yLevel, Blocks.ICE.defaultState)
        else if (icePicker < -0.75)
            blockColumn.setState(yLevel, Blocks.BLUE_ICE.defaultState)
        else
            blockColumn.setState(yLevel, Blocks.PACKED_ICE.defaultState)
    }
}