package org.teamvoided.dusk_autumn.util

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import kotlin.math.abs
import kotlin.math.min


typealias FoliageSetter = FoliagePlacer.Placer

typealias ShapePredicate = (dx: Int, dz: Int) -> Boolean

abstract class FoliageHelper(radius: IntProvider, offset: IntProvider) : FoliagePlacer(radius, offset) {

    fun genSquareRounded(
        world: TestableWorld,
        place: FoliageSetter,
        random: RandomGenerator,
        config: TreeFeatureConfig,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
        rounding: Double = 2.0
    ) = genShapeAbsInputs(world, place, random, config, centerPos, isEven, y, radius)
    { dx, dz -> dx + dz <= radius * 2 - rounding }

    fun genCircle(
        world: TestableWorld,
        place: FoliageSetter,
        random: RandomGenerator,
        config: TreeFeatureConfig,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int
    ) = genShapeAbsInputs(world, place, random, config, centerPos, isEven, y, radius)
    { dx, dz -> !(if (dx + dz >= 7) true else dx * dx + dz * dz > radius * radius) }

    fun genSquareNoCorners(
        world: TestableWorld,
        place: FoliageSetter,
        random: RandomGenerator,
        config: TreeFeatureConfig,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
    ) = genShapeAbsInputs(world, place, random, config, centerPos, isEven, y, radius)
    { dx, dz -> !(dx == radius && dz == radius) }

    fun genSquareRandomNoCorners(
        world: TestableWorld,
        place: FoliageSetter,
        random: RandomGenerator,
        config: TreeFeatureConfig,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
        cornerChance: Float = 0.5f
    ) = genShapeAbsInputs(world, place, random, config, centerPos, isEven, y, radius)
    { dx, dz -> !(dx == radius && dz == radius) || random.nextFloat() > cornerChance }

    fun genSquare(
        world: TestableWorld,
        place: FoliageSetter,
        random: RandomGenerator,
        config: TreeFeatureConfig,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
    ) = genShape(world, place, random, config, centerPos, isEven, y, radius) { _, _ -> true }

    fun genShapeAbsInputs(
        world: TestableWorld,
        place: FoliageSetter,
        random: RandomGenerator,
        config: TreeFeatureConfig,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
        predicate: ShapePredicate
    ) = genShape(world, place, random, config, centerPos, isEven, y, radius) { x, z ->
        val dx = if (isEven) min(abs(x), abs((x - 1))) else abs(x)
        val dz = if (isEven) min(abs(z), abs((z - 1))) else abs(z)
        predicate(dx, dz)
    }

    fun genShape(
        world: TestableWorld,
        place: FoliageSetter,
        random: RandomGenerator,
        config: TreeFeatureConfig,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
        predicate: ShapePredicate
    ) {
        val i = if (isEven) 1 else 0
        val mutable = BlockPos.Mutable()

        for (j in -radius..radius + i) {
            for (k in -radius..radius + i) {
                if (predicate(j, k)) {
                    mutable[centerPos, j, y] = k
                    placeFoliageBlock(world, place, random, config, mutable)
                }
            }
        }
    }

    override fun isInvalidForLeaves(
        random: RandomGenerator, dx: Int, y: Int, dz: Int, radius: Int, giantTrunk: Boolean
    ): Boolean = false
}
