package org.teamvoided.dusk_autumn.world.gen.foliage

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import org.teamvoided.dusk_autumn.init.DuskWorldgen
import kotlin.math.max

class ManhattanFoliagePlacer(radius: IntProvider, offset: IntProvider) :
    FoliagePlacer(radius, offset) {
    override fun getType(): FoliagePlacerType<*> {
        return DuskWorldgen.MANHATTAN_FOLIAGE_PLACER
    }

    override fun method_23448(
        world: TestableWorld,
        c_pwcqvmho: C_pwcqvmho,
        random: RandomGenerator,
        treeFeatureConfig: TreeFeatureConfig,
        i: Int,
        treeNode: TreeNode,
        j: Int,
        k: Int,
        l: Int
    ) {
        var m = 0

        for (n in l downTo l - j) {
            this.generateSquare(
                world,
                c_pwcqvmho,
                random,
                treeFeatureConfig,
                treeNode.center,
                m,
                n,
                treeNode.isGiantTrunk
            )
            if (m >= 1 && n == l - j + 1) {
                --m
            } else if (m < k + treeNode.foliageRadius) {
                ++m
            }
        }
    }

    override fun getRandomRadius(random: RandomGenerator, baseHeight: Int): Int {
        return super.getRandomRadius(random, baseHeight) + random.nextInt(max((baseHeight + 1).toDouble(), 1.0).toInt())
    }

    override fun getRandomHeight(random: RandomGenerator, trunkHeight: Int, config: TreeFeatureConfig): Int {
        return 0
    }

    override fun isInvalidForLeaves(
        random: RandomGenerator,
        dx: Int,
        y: Int,
        dz: Int,
        radius: Int,
        giantTrunk: Boolean
    ): Boolean {
        return dx == radius && dz == radius && radius > 0
    }

    companion object {
        val CODEC: Codec<ManhattanFoliagePlacer> =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<ManhattanFoliagePlacer> ->
                fillFoliagePlacerFields(instance).apply(
                    instance
                ) { radius: IntProvider, offset: IntProvider ->
                    ManhattanFoliagePlacer(
                        radius,
                        offset
                    )
                }
            }
    }
}