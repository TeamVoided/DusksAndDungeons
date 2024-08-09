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
    //    var glacierIceOld: DoublePerlinNoiseSampler? = null
    var glacierIce: DoublePerlinNoiseSampler? = null

    var glacierJaggedness: DoublePerlinNoiseSampler? =
        null //roof offset will most likely turn into the jagged spike region instead of an unnecessary offset value
    var glacierSnow: DoublePerlinNoiseSampler? = null
    var glacierWaterRoof: DoublePerlinNoiseSampler? = null
    var glacierBorders: DoublePerlinNoiseSampler? = null


    fun init() {
        CustomSurfaceBuilder.POST_RULES.register { random, defaultBlock, seaLevel, biome, chunk, blockColumn, x, z ->
//            if (glacierIceOld == null) glacierIceOld =
//                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_ICE_PICKER_OLD)
            if (glacierIce == null) glacierIce =
                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_ICE_PICKER)
            if (glacierJaggedness == null) glacierJaggedness =
                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_JAGGEDNESS)
            if (glacierSnow == null) glacierSnow =
                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_SNOW_SURFACE)
            if (glacierWaterRoof == null) glacierWaterRoof =
                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_WATER_ROOF)
            if (glacierBorders == null) glacierBorders =
                random.getOrCreateNoiseSampler(DnDNoise.GLACIER_BORDERS)

            val y = chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, x, z) + 1

            if (biome.getBiome(BlockPos(x, y, z)).isIn(DnDBiomeTags.HAS_GLACIERS)) {
                val glacierJaggednessRange: Double =
                    glacierJaggedness!!.sample(150 * x, 0.0, 150 * z)
                val glacierJaggedness: Double = if (glacierJaggednessRange > 0) {
                    glacierJaggednessRange * (halfNegative(
                        glacierJaggedness!!.sample(1500 * x, 0.0, 1500 * z)
                    ) * 40)
                } else 0.0

                val bias = seaLevel + 40
                val glacierYLevel: Int = ((y - bias) * 0.175 + bias + glacierJaggedness).toInt()

                val snowSurface: Double = glacierSnow!!.sample(x * 0.75, 0.0, z * 0.75) * 1.5
                val snowLevel: Int = (glacierYLevel + ceil(snowSurface * 10)).toInt()

                var isCorner = false
                for (offset in listOf(-5, 5)) {
                    val negativeOffset = -offset
                    if (!biome.getBiome(BlockPos(x + offset, y, z + negativeOffset))
                            .isIn(DnDBiomeTags.HAS_GLACIERS) ||
                        !biome.getBiome(BlockPos(x + negativeOffset, y, z + offset))
                            .isIn(DnDBiomeTags.HAS_GLACIERS)
                    ) {
                        isCorner = true
                        break
                    }
                }

                val glacierIce: Double = glacierIce!!.sample(x, y, z)
                if (y < seaLevel || isCorner) {
                    val sampledNoise: Double = if (isCorner) glacierBorders!!.sample(x * 0.35, 0.0, z * 0.35) + 0.3
                    else glacierWaterRoof!!.sample(x * 0.75, 0.0, z * 0.75) * 1.5
                    var fill = false
                    for (yLevel in y..glacierYLevel) {
                        if (fill) {
                            placeGlacierBlock(yLevel, blockColumn, snowLevel, glacierIce)
                        } else if (sampledNoise > (halfNegative((4 + yLevel - glacierYLevel) * 0.2)).absoluteValue) {
                            placeGlacierBlock(yLevel, blockColumn, snowLevel, glacierIce)
                            fill = true
                        }
                    }
                } else {
                    for (yLevel in glacierYLevel downTo y) {
                        if (yLevel % 4 == 0 &&
                            !biome.getBiome(BlockPos(x, yLevel, z)).isIn(DnDBiomeTags.HAS_GLACIERS)
                        ) break
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
        val block =
            if (yLevel > snowLevel) {
                if (yLevel - snowLevel > 8) Blocks.POWDER_SNOW
                else Blocks.SNOW_BLOCK
            } else if (icePicker > 7.7) Blocks.BLUE_ICE
            else if (icePicker > 3.2) Blocks.PACKED_ICE
            else Blocks.ICE

        blockColumn.setState(yLevel, block.defaultState)
    }

    fun DoublePerlinNoiseSampler.sample(x: Number, y: Number, z: Number): Double {
        return this.sample(x.toDouble(), y.toDouble(), z.toDouble())
    }
}
