package org.teamvoided.dusks_and_dungeons.world.gen.foliage

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen.CASCADE_FOLIAGE_PLACER
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen.OVERGROWTH_FOLIAGE_PLACER
import kotlin.math.max

class OvergrowthFoliagePlacer(intProvider: IntProvider, intProvider2: IntProvider) :
    DnDFoliageHelper(intProvider, intProvider2) {
    override fun type(): FoliagePlacerType<OvergrowthFoliagePlacer> = OVERGROWTH_FOLIAGE_PLACER


    override fun createFoliage(
        world: LevelSimulatedReader,
        placer: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        trunkHeight: Int,
        node: FoliageAttachment,
        foliageHeight: Int,
        radius: Int,
        offset: Int
    ) {
        val blockPos = node.pos().above(offset)
        val isBig = node.doubleTrunk()

        this.genSquareNoCorners(world, placer, random, config, blockPos, isBig, -3, radius - 1)
        this.genSquareNoCornersRandomEdge(world, placer, random, config, blockPos, isBig, -2, radius, 0.5f)
        this.genSquareRandomNoCorners(world, placer, random, config, blockPos, isBig, -1, radius, 0.75f)
        this.genSquareRandomNoCorners(world, placer, random, config, blockPos, isBig, 0, radius - 1, 0.75f)
        if (radius > 2)
            this.genSquareRandomNoCorners(world, placer, random, config, blockPos, isBig, 1, radius - 2, 0.75f)
    }

    override fun foliageHeight(random: RandomSource, trunkHeight: Int, config: TreeConfiguration): Int = 4

    companion object {
        val CODEC: MapCodec<OvergrowthFoliagePlacer> =
            RecordCodecBuilder.mapCodec { instance ->
                foliagePlacerParts(instance)
                    .apply(instance, ::OvergrowthFoliagePlacer)
            }
    }
}

