package org.teamvoided.dusk_autumn.world.gen.foliage

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer
import org.teamvoided.dusk_autumn.init.DuskWorldgen

class CascadeFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    private val foliageHeight: IntProvider,
    private val leafPlacementAttempts: Int) :
    FoliagePlacer(radius, offset) {
    override fun getType(): FoliagePlacerType<*> {
        return DuskWorldgen.CASCADE_FOLIAGE_PLACER
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
        val blockPos = treeNode.center
        val mutable = blockPos.mutableCopy()

        for (m in 0 until this.leafPlacementAttempts) {
            mutable[blockPos, random.nextInt(k) - random.nextInt(k), random.nextInt(j) - random.nextInt(j)] =
                random.nextInt(k) - random.nextInt(k)
            placeFoliageBlock(world, c_pwcqvmho, random, treeFeatureConfig, mutable)
        }
    }

    override fun getRandomHeight(random: RandomGenerator, trunkHeight: Int, config: TreeFeatureConfig): Int {
        return 4
    }

    override fun isPositionInvalid(
        random: RandomGenerator,
        dx: Int,
        y: Int,
        dz: Int,
        radius: Int,
        giantTrunk: Boolean
    ): Boolean {
        return if (y != 0 || !giantTrunk || dx != -radius && dx < radius || dz != -radius && dz < radius) super.isPositionInvalid(
            random,
            dx,
            y,
            dz,
            radius,
            giantTrunk
        ) else true
    }

    override fun isInvalidForLeaves(
        random: RandomGenerator,
        dx: Int,
        y: Int,
        dz: Int,
        radius: Int,
        giantTrunk: Boolean
    ): Boolean {
        return if (y == -1 && !giantTrunk) {
            dx == radius && dz == radius
        } else if (y == 1) {
            dx + dz > radius * 2 - 2
        } else {
            false
        }
    }

    companion object {
        val CODEC: Codec<CascadeFoliagePlacer> =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<CascadeFoliagePlacer> ->
                fillFoliagePlacerFields(instance).and(
                    instance.group(
                        IntProvider.create(1, 512).fieldOf("foliage_height")
                            .forGetter { placer: CascadeFoliagePlacer -> placer.foliageHeight },
                        Codec.intRange(0, 256).fieldOf("leaf_placement_attempts")
                            .forGetter { placer: CascadeFoliagePlacer -> placer.leafPlacementAttempts }
                    )
                ).apply(
                    instance
                ) { radius: IntProvider, offset: IntProvider, foliageHeight: IntProvider, leafPlacementAttempts: Int ->
                    CascadeFoliagePlacer(
                        radius,
                        offset,
                        foliageHeight,
                        leafPlacementAttempts
                    )
                }
            }
//        val CODEC: Codec<CascadeFoliagePlacer> =
//            RecordCodecBuilder.create {
//                fillFoliagePlacerFields(it)
//                    .apply(it, ::CascadeFoliagePlacer)
//            }
    }
}