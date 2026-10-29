package org.teamvoided.voidlib.reef.world.gen.foliage

import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import kotlin.math.abs
import kotlin.math.min


// Diameter X & Z
typealias ShapePredicate = (dx: Int, dz: Int) -> Boolean

abstract class FoliageHelper(radius: IntProvider, offset: IntProvider) : FoliagePlacer(radius, offset) {

    fun genSquareRounded(
        level: LevelSimulatedReader,
        place: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
        rounding: Double = 2.0,
    ) {
        genShapeAbsInputs(level, place, random, config, centerPos, isEven, y, radius) { dx, dz ->
            dx + dz <= radius * 2 - rounding
        }
    }

    fun genCircle(
        level: LevelSimulatedReader,
        place: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
    ) {
        genShapeAbsInputs(level, place, random, config, centerPos, isEven, y, radius) { dx, dz ->
            !(if (dx + dz >= 7) true else dx * dx + dz * dz > radius * radius)
        }
    }

    fun genSquareNoCorners(
        level: LevelSimulatedReader,
        place: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
    ) {
        genShapeAbsInputs(level, place, random, config, centerPos, isEven, y, radius) { dx, dz ->
            !(dx == radius && dz == radius)
        }
    }

    fun genSquareRandomNoCorners(
        level: LevelSimulatedReader,
        place: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
        cornerChance: Float = 0.5f,
    ) = genShapeAbsInputs(level, place, random, config, centerPos, isEven, y, radius)
    { dx, dz -> !(dx == radius && dz == radius) || random.nextFloat() > cornerChance }

    fun genSquare(
        level: LevelSimulatedReader,
        place: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
    ) {
        genShape(level, place, random, config, centerPos, isEven, y, radius) { _, _ -> true }
    }

    fun genShapeAbsInputs(
        level: LevelSimulatedReader,
        place: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
        predicate: ShapePredicate,
    ) {
        genShape(level, place, random, config, centerPos, isEven, y, radius) { x, z ->
            val dx = if (isEven) min(abs(x), abs((x - 1))) else abs(x)
            val dz = if (isEven) min(abs(z), abs((z - 1))) else abs(z)
            predicate(dx, dz)
        }
    }

    fun genShape(
        level: LevelSimulatedReader,
        place: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
        predicate: ShapePredicate,
    ) {
        val i = if (isEven) 1 else 0
        val mutable = BlockPos.MutableBlockPos()

        for (j in -radius..radius + i) {
            for (k in -radius..radius + i) {
                if (predicate(j, k)) {
                    mutable.setWithOffset(centerPos, j, y, k)
                    tryPlaceLeaf(level, place, random, config, mutable)
                }
            }
        }
    }

    override fun shouldSkipLocation(
        random: RandomSource, dx: Int, y: Int, dz: Int, radius: Int, giantTrunk: Boolean,
    ): Boolean = false

}