package org.teamvoided.dusks_and_dungeons.world.gen.foliage

import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import org.teamvoided.voidlib.reef.world.gen.foliage.FoliageHelper

abstract class DnDFoliageHelper(radius: IntProvider, offset: IntProvider) : FoliageHelper(radius, offset) {
    constructor(radius: Int, offset: Int) : this(ConstantInt.of(radius), ConstantInt.of(offset))

    /*KEY
     * -> Always air
     % -> Possibly air
     # -> Always Leaves
    */

    fun genSquareNoCornersRandomEdge(
        world: LevelSimulatedReader,
        place: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        centerPos: BlockPos,
        isEven: Boolean,
        y: Int,
        radius: Int,
        chance: Float = 0.5f,
    ) = genShapeAbsInputs(world, place, random, config, centerPos, isEven, y, radius)
    { dx, dz ->
        if (dx == radius && dz == radius) {
            false
        } else if (dx == radius || dz == radius) {
            random.nextFloat() >= chance
        } else true
    }
}