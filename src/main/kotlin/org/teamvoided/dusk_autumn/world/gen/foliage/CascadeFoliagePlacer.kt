package org.teamvoided.dusk_autumn.world.gen.foliage

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacerType
import org.teamvoided.dusk_autumn.init.DnDWorldgen
import org.teamvoided.dusk_autumn.util.FoliageHelper
import org.teamvoided.dusk_autumn.util.FoliageSetter


class CascadeFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    private val foliageHeight: IntProvider,
    private val leafPlacementAttempts: Int
) : FoliageHelper(radius, offset) {

    override fun getType(): FoliagePlacerType<CascadeFoliagePlacer> = DnDWorldgen.CASCADE_FOLIAGE_PLACER

    override fun createFoliage(
        world: TestableWorld,
        place: FoliageSetter,
        random: RandomGenerator,
        config: TreeFeatureConfig,
        i: Int, treeNode: TreeNode,
        j: Int, radius: Int, l: Int
    ) {
        val pos = treeNode.center
        val giantTrunk = treeNode.isGiantTrunk

        // Variant 1
        /*  if (giantTrunk) {
              val height = -4
              this.genSquareRounded(world, place, random, config, pos, false, 0 + height, k)
              this.genSquareNoCorners(world, place, random, config, pos, false, 1 + height, k)
              this.genSquareRounded(world, place, random, config, pos, false, 2 + height, k)

              this.genSquareNoCorners(world, place, random, config, pos, false, 3 + height, k - 1)
              this.genSquareNoCorners(world, place, random, config, pos, false, 4 + height, k - 1)
              this.genSquare(world, place, random, config, pos, false, 5 + height, k - 2)

              this.genSquareNoCorners(world, place, random, config, pos, false, 6 + height, k - 2)
          } else {
              val pos2 = pos//.north().west()

              val isEven = false
              this.genSquareRounded(world, place, random, config, pos2, isEven, -1, k - 1)
              this.genSquareNoCorners(world, place, random, config, pos2, isEven, 0, k - 1)
              this.genSquareRounded(world, place, random, config, pos2, isEven, 1, k - 1)
              this.genSquareRounded(world, place, random, config, pos2, isEven, 2, k - 1)
              this.genSquareNoCorners(world, place, random, config, pos2, isEven, 3, k - 2)
              this.genSquareNoCorners(world, place, random, config, pos2, isEven, 4, k - 2)
              this.genSquare(world, place, random, config, pos2, isEven, 5, k - 3)

          }*/

        // Variant 2
        /*  if (giantTrunk) {
              val isEven = false
              this.genSquareRandomNoCorners(world, place, random, config, pos, isEven, -1, k )
              this.genSquareRandomNoCorners(world, place, random, config, pos, isEven, 0, k )
              this.genSquareNoCorners(world, place, random, config, pos, isEven, 1, k - 1)
          } else {
              val pos2 = pos.north().west()

              val isEven = true
              this.genSquareNoCorners(world, place, random, config, pos2, isEven, -1, k - 1)
              this.genSquareNoCorners(world, place, random, config, pos2, isEven, 0, k - 1)
              this.genSquareNoCorners(world, place, random, config, pos2, isEven, 1, k - 2)
              this.genSquare(world, place, random, config, pos2, isEven, 2, k - 3)
          }*/

        // Variant 3
        /* if (giantTrunk) {
             val isEven = false
             this.genSquareRandomNoCorners(world, place, random, config, pos, isEven, -1, k )
             this.genSquareRandomNoCorners(world, place, random, config, pos, isEven, 0, k )
             this.genSquareNoCorners(world, place, random, config, pos, isEven, 1, k - 1)
         } else {
             val pos2 = pos.north().west()

             val isEven = true
             this.genSquareRandomNoCorners(world, place, random, config, pos2, isEven, -1, k - 1)
             this.genSquareNoCorners(world, place, random, config, pos2, isEven, 0, k - 1)
             this.genSquareRandomNoCorners(world, place, random, config, pos2, isEven, 1, k - 2)
             this.genSquareNoCorners(world, place, random, config, pos2, isEven, 2, k - 2)
         }*/

        // Variant 4
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
            val pos2 = pos//.north().west()
            val isEven = false

//              this.genSquareRounded(world, place, random, config, pos2, isEven, -2, radius - 2)
//              this.genSquareNoCorners(world, place, random, config, pos2, isEven, -1, radius- 1)
//              this.genSquareRandomNoCorners(world, place, random, config, pos2, isEven, 0, radius- 1)
//              this.genSquareNoCorners(world, place, random, config, pos2, isEven, 1, radius - 2)

            this.genSquareRounded(world, place, random, config, pos2, isEven, -1, radius - 1)
            this.genSquareNoCorners(world, place, random, config, pos2, isEven, 0, radius - 1)
            this.genSquareRounded(world, place, random, config, pos2, isEven, 1, radius - 1)
            this.genSquareRounded(world, place, random, config, pos2, isEven, 2, radius - 1)
            this.genSquareNoCorners(world, place, random, config, pos2, isEven, 3, radius - 2)
            this.genSquareNoCorners(world, place, random, config, pos2, isEven, 4, radius - 2)
            this.genSquare(world, place, random, config, pos2, isEven, 5, radius - 3)

        }
    }

    override fun getRandomHeight(random: RandomGenerator, trunkHeight: Int, config: TreeFeatureConfig): Int {
        return 4
    }

    companion object {
        val CODEC: MapCodec<CascadeFoliagePlacer> = RecordCodecBuilder.mapCodec { instance ->
            fillFoliagePlacerFields(instance).and(
                instance.group(
                    IntProvider.method_35004(1, 512).fieldOf("foliage_height").forGetter { it.foliageHeight },
                    Codec.intRange(0, 256).fieldOf("leaf_placement_attempts").forGetter { it.leafPlacementAttempts }
                )
            ).apply(instance, ::CascadeFoliagePlacer)
        }
    }
}
