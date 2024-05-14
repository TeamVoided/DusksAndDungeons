package org.teamvoided.dusk_autumn.world.gen.foliage

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import org.teamvoided.dusk_autumn.DuskAutumns.LOGGER
import org.teamvoided.dusk_autumn.init.DuskWorldgen

typealias FoliageSetter = FoliagePlacer.C_pwcqvmho


class CascadeFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    private val foliageHeight: IntProvider,
    private val leafPlacementAttempts: Int
) :
    FoliagePlacer(radius, offset) {
    val green = Blocks.GREEN_STAINED_GLASS.defaultState
    val red = Blocks.RED_STAINED_GLASS.defaultState

    override fun getType(): FoliagePlacerType<*> {
        return DuskWorldgen.CASCADE_FOLIAGE_PLACER
    }

    override fun method_23448(
        world: TestableWorld,
        place: FoliageSetter,
        random: RandomGenerator,
        treeFeatureConfig: TreeFeatureConfig,
        i: Int, treeNode: TreeNode,
        j: Int, k: Int, l: Int
    ) {
        try {
            val treeCenter = treeNode.center
            place.place(treeCenter.up(), red)


            val bigTrunk = treeNode.isGiantTrunk
            val list = listOf(3, 4, 5, 5, 5, 5, 4, 2, 1)

            if (bigTrunk) {
                for (idx in 0..9) {
                    this.generateSquare(
                        world, place, random, treeFeatureConfig,
                        treeCenter.down(4), list[idx], idx, bigTrunk
                    )
                }
            } else {
                for (idx in 3..9) {
                    this.generateSquare(
                        world, place, random, treeFeatureConfig,
                        treeCenter.down(4), (list[idx] / 2), idx, bigTrunk
                    )
                }
            }

        } catch (e: Exception) {
            LOGGER.info("aaaa $e")
        }

    }

    fun square(
        world: TestableWorld, place: FoliageSetter, random: RandomGenerator,
        config: TreeFeatureConfig, centerPos: BlockPos, radius: Int, y: Int, giantTrunk: Boolean
    ) {
        val i = if (giantTrunk) 1 else 0
        val mutable = BlockPos.Mutable()

        for (j in -radius..radius + i) {
            for (k in -radius..radius + i) {
                if (!this.isPositionInvalid(random, j, y, k, radius, giantTrunk)) {
                    mutable[centerPos, j, y] = k
                    world.place(place, mutable, green)
                }
            }
        }
    }

    override fun getRandomHeight(random: RandomGenerator, trunkHeight: Int, config: TreeFeatureConfig): Int {
        return 4
    }


    override fun isPositionInvalid(
        random: RandomGenerator?,
        dx: Int,
        y: Int,
        dz: Int,
        radius: Int,
        giantTrunk: Boolean
    ): Boolean {
        return isInsideCircle(dx.toFloat(), dz.toFloat(), radius.toFloat())
//        (MathHelper.square(dx.toFloat() + 0.5) + MathHelper.square(dz.toFloat() + 0.5) + if (giantTrunk) 1 else 0) > (radius * radius).toFloat()
    }

    fun isInsideCircle(x: Float, y: Float, circleRadius: Float, circleX: Float = 0F, circleY: Float = 0F): Boolean {
        val distanceSquared = (x - circleX) * (x - circleX) + (y - circleY) * (y - circleY)
        return distanceSquared > (circleRadius * circleRadius)
    }

    override fun isInvalidForLeaves(
        random: RandomGenerator?, dx: Int, y: Int, dz: Int, radius: Int, giantTrunk: Boolean
    ): Boolean {
        return if (y == -1 && !giantTrunk) dx == radius && dz == radius
        else if (y == 1) dx + dz > radius * 2 - 2
        else false
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
//        val CODEC: Codec<CascadeFoliagePlacer> =
//            RecordCodecBuilder.create {
//                fillFoliagePlacerFields(it)
//                    .apply(it, ::CascadeFoliagePlacer)
//            }
    }

    fun FoliageSetter.place(pos: BlockPos, state: BlockState) = this.method_49240(pos, state)
    fun TestableWorld.place(placer: FoliageSetter, pos: BlockPos, state: BlockState) {
        if (TreeFeature.canReplace(this, pos)) placer.place(pos, state)
    }
}
