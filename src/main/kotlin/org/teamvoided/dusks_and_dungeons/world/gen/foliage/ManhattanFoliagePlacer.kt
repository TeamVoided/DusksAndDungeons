package org.teamvoided.dusks_and_dungeons.world.gen.foliage

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen
import kotlin.math.max

class ManhattanFoliagePlacer(radius: IntProvider, offset: IntProvider) : FoliagePlacer(radius, offset) {
    override fun type(): FoliagePlacerType<*> {
        return DnDWorldgen.MANHATTAN_FOLIAGE_PLACER
    }

    override fun createFoliage(
        world: LevelSimulatedReader,
        placer: FoliageSetter,
        random: RandomSource,
        treeFeatureConfig: TreeConfiguration,
        i: Int,
        treeNode: FoliageAttachment,
        j: Int,
        k: Int,
        l: Int
    ) {
        var m = 0

        for (n in l downTo l - j) {
            this.placeLeavesRow(
                world,
                placer,
                random,
                treeFeatureConfig,
                treeNode.pos(),
                m,
                n,
                treeNode.doubleTrunk()
            )
            if (m >= 1 && n == l - j + 1) {
                --m
            } else if (m < k + treeNode.radiusOffset()) {
                ++m
            }
        }
    }

    override fun foliageRadius(random: RandomSource, baseHeight: Int): Int {
        return super.foliageRadius(random, baseHeight) + random.nextInt(max((baseHeight + 1).toDouble(), 1.0).toInt())
    }

    override fun foliageHeight(random: RandomSource, trunkHeight: Int, config: TreeConfiguration): Int {
        return 0
    }

    override fun shouldSkipLocation(
        random: RandomSource,
        dx: Int,
        y: Int,
        dz: Int,
        radius: Int,
        giantTrunk: Boolean
    ): Boolean {
        return dx == radius && dz == radius && radius > 0
    }

    companion object {
        val CODEC: MapCodec<ManhattanFoliagePlacer> =
            RecordCodecBuilder.mapCodec { foliagePlacerParts(it).apply(it, ::ManhattanFoliagePlacer) }
    }
}
