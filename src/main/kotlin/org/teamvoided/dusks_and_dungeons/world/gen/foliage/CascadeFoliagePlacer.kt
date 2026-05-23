package org.teamvoided.dusks_and_dungeons.world.gen.foliage

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen.CASCADE_FOLIAGE_PLACER
import org.teamvoided.voidlib.reef.world.gen.foliage.FoliageHelper

class CascadeFoliagePlacer(
    radius: IntProvider, offset: IntProvider,
    private val foliageHeight: IntProvider, private val leafPlacementAttempts: Int
) : FoliageHelper(radius, offset) {
    override fun type(): FoliagePlacerType<CascadeFoliagePlacer> = CASCADE_FOLIAGE_PLACER
    override fun createFoliage(
        world: LevelSimulatedReader, place: FoliageSetter, random: RandomSource, config: TreeConfiguration,
        i: Int, treeNode: FoliageAttachment, j: Int, radius: Int, l: Int
    ) {
        val pos = treeNode.pos()
        val giantTrunk = treeNode.doubleTrunk()
        if (giantTrunk) {
            val height = -3
            this.genSquareRounded(world, place, random, config, pos, false, -1 + height, radius)
            this.genSquareNoCorners(world, place, random, config, pos, false, 0 + height, radius)
            this.genSquareNoCorners(world, place, random, config, pos, false, 1 + height, radius)
            this.genSquareRounded(world, place, random, config, pos, false, 2 + height, radius)

            this.genSquareRandomNoCorners(world, place, random, config, pos, false, 3 + height, radius - 1)
            this.genSquareNoCorners(world, place, random, config, pos, false, 4 + height, radius - 1)
            this.genSquareNoCorners(world, place, random, config, pos, false, 5 + height, radius - 1)

            this.genSquare(world, place, random, config, pos, false, 6 + height, radius - 2)
            this.genSquareRandomNoCorners(world, place, random, config, pos, false, 7 + height, radius - 2)
            this.genSquareNoCorners(world, place, random, config, pos, false, 8 + height, radius - 2)
        } else {
            val isEven = false
            this.genSquareRounded(world, place, random, config, pos, isEven, -1, radius - 1)
            this.genSquareNoCorners(world, place, random, config, pos, isEven, 0, radius - 1)
            this.genSquareRounded(world, place, random, config, pos, isEven, 1, radius - 1)
            this.genSquareRounded(world, place, random, config, pos, isEven, 2, radius - 1)
            this.genSquareNoCorners(world, place, random, config, pos, isEven, 3, radius - 2)
            this.genSquareNoCorners(world, place, random, config, pos, isEven, 4, radius - 2)
            this.genSquare(world, place, random, config, pos, isEven, 5, radius - 3)
        }
    }

    override fun foliageHeight(random: RandomSource, trunkHeight: Int, config: TreeConfiguration): Int = 4

    companion object {
        val CODEC: MapCodec<CascadeFoliagePlacer> = RecordCodecBuilder.mapCodec { instance ->
            foliagePlacerParts(instance).and(
                instance.group(
                    IntProvider.codec(1, 512).fieldOf("foliage_height").forGetter { it.foliageHeight },
                    Codec.intRange(0, 256).fieldOf("leaf_placement_attempts").forGetter { it.leafPlacementAttempts }
                )
            ).apply(instance, ::CascadeFoliagePlacer)
        }
    }
}
