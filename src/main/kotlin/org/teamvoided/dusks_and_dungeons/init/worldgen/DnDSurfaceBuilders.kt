package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.tags.TagKey
import net.minecraft.core.BlockPos
import net.minecraft.world.level.levelgen.synth.NormalNoise
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeManager
import net.minecraft.world.level.chunk.ChunkAccess
import net.minecraft.world.level.levelgen.RandomState
import net.minecraft.world.level.chunk.BlockColumn
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDNoises
import org.teamvoided.reef.api.events.CustomSurfaceBuilder
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.ceil

// TODO(1.0) remove
object DnDSurfaceBuilders {


    //    var glacierIceOld: DoublePerlinNoiseSampler? = null
    var glacierIce: NormalNoise? = null
    var glacierJaggedness: NormalNoise? = null
    var glacierSnow: NormalNoise? = null
    var glacierWaterRoof: NormalNoise? = null
    var glacierBorders: NormalNoise? = null


    fun init() {
        CustomSurfaceBuilder.POST_RULES.register { random, defaultBlock, seaLevel, biome, chunk, blockColumn, x, z ->
            createGlaciers(random, seaLevel, biome, chunk, blockColumn, x, z)
        }
    }

    fun createOreVein(
        ore: BlockState,
        rawOre: BlockState,
        filler: BlockState,
        maxY: Int,
        minY: Int,
        oreVeinBiomes: TagKey<Biome>,

        random: RandomState,
        seaLevel: Int,
        biome: BiomeManager,
        chunk: ChunkAccess,
        blockColumn: BlockColumn,
        x: Int,
        z: Int
    ) {
        if (biome.getBiome(BlockPos(x, seaLevel, z)).`is`(oreVeinBiomes)) {
            val veinClamp = 1
            val veinA = 1
            val veinB = 1
            val veinRidged = -0.08 + Math.max(abs(veinA), abs(veinB))
            val veinGap = 1

//            val randomGenerator/*: PositionalRandomFactory*/ = 1
//
//            val yLevel = 0
//            val veinType = null
//            val j = maxY - yLevel
//            val k = yLevel - minY
//            val block = if (k >= 0 && j >= 0) {
//                val l = min(j.toDouble(), k.toDouble()).toInt()
//                val g = MathHelper.clampedMap(e, 0.4, 0.6, 0.1, 0.3)
//                if (randomGenerator.nextFloat().toDouble() < g && veinGap > -0.3
//                ) {
//                    if (randomGenerator.nextFloat() < 0.02f) rawOre else ore
//                } else {
//                    filler
//                }
//            } else null
        }
    }

    fun createGlaciers(
        random: RandomState,
        seaLevel: Int,
        biome: BiomeManager,
        chunk: ChunkAccess,
        blockColumn: BlockColumn,
        x: Int,
        z: Int
    ) {
        if (glacierIce == null) glacierIce =
            random.getOrCreateNoise(DnDNoises.GLACIER_ICE_PICKER)
        if (glacierJaggedness == null) glacierJaggedness =
            random.getOrCreateNoise(DnDNoises.GLACIER_JAGGEDNESS)
        if (glacierSnow == null) glacierSnow =
            random.getOrCreateNoise(DnDNoises.GLACIER_SNOW_SURFACE)
        if (glacierWaterRoof == null) glacierWaterRoof =
            random.getOrCreateNoise(DnDNoises.GLACIER_WATER_ROOF)
        if (glacierBorders == null) glacierBorders =
            random.getOrCreateNoise(DnDNoises.GLACIER_BORDERS)

        val y = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z) + 1

        if (biome.getBiome(BlockPos(x, y, z)).`is`(DnDBiomeTags.HAS_GLACIERS)) {
            val glacierJaggednessRange: Double =
                glacierJaggedness!!.sample(150 * x, 0.0, 150 * z)
            val glacierJaggedness: Double = if (glacierJaggednessRange > 0) {
                glacierJaggednessRange * (halfNegative(
                    glacierJaggedness!!.sample(1500 * x, 0.0, 1500 * z)
                ) * 40)
            } else 0.0

            val bias = seaLevel + 40
            val glacierYLevel: Int = ((y - bias) * 0.175 + bias + glacierJaggedness).toInt()

            val snowSurface: Double = glacierSnow!!.getValue(x * 0.75, 0.0, z * 0.75) * 1.5
            val snowLevel: Int = (glacierYLevel + ceil(snowSurface * 10)).toInt()

            var isCorner = false
            for (offset in listOf(-5, 5)) {
                val negativeOffset = -offset
                if (!biome.getBiome(BlockPos(x + offset, y, z + negativeOffset))
                        .`is`(DnDBiomeTags.HAS_GLACIERS) ||
                    !biome.getBiome(BlockPos(x + negativeOffset, y, z + offset))
                        .`is`(DnDBiomeTags.HAS_GLACIERS)
                ) {
                    isCorner = true
                    break
                }
            }

            val glacierIce: Double = glacierIce!!.sample(x, y, z)
            if (y < seaLevel || isCorner) {
                val sampledNoise: Double = if (isCorner) glacierBorders!!.getValue(x * 0.35, 0.0, z * 0.35) + 0.3
                else glacierWaterRoof!!.getValue(x * 0.75, 0.0, z * 0.75) * 1.5
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
                        !biome.getBiome(BlockPos(x, yLevel, z)).`is`(DnDBiomeTags.HAS_GLACIERS)
                    ) break
                    placeGlacierBlock(yLevel, blockColumn, snowLevel, glacierIce)
                }
            }
        }
    }

    private fun placeGlacierBlock(yLevel: Int, blockColumn: BlockColumn, snowLevel: Int, icePicker: Double) {
        val block =
            if (yLevel > snowLevel) {
                if (yLevel - snowLevel > 8) Blocks.POWDER_SNOW
                else Blocks.SNOW_BLOCK
            } else if (icePicker > 7.7) Blocks.BLUE_ICE
            else if (icePicker > 3.2) Blocks.PACKED_ICE
            else Blocks.ICE

        blockColumn.setBlock(yLevel, block.defaultBlockState())
    }

    private fun halfNegative(double: Double): Double {
        return if (double < 0) double / 2 else double
    }

    fun NormalNoise.sample(x: Number, y: Number, z: Number): Double {
        return this.getValue(x.toDouble(), y.toDouble(), z.toDouble())
    }
}
