package org.teamvoided.dusks_and_dungeons.world.gen.root

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen
import java.util.*
import java.util.function.BiConsumer

class CascadeRootPlacer(
    trunkOffsetY: IntProvider,
    rootProvider: BlockStateProvider,
    aboveRootPlacement: Optional<AboveRootPlacement>,
    private val cascadeRootConfig: CascadeRootConfig
) : RootPlacer(trunkOffsetY, rootProvider, aboveRootPlacement) {

    override fun type(): RootPlacerType<CascadeRootPlacer> = DnDWorldgen.CASCADE_ROOT_PLACER

    override fun placeRoots(
        world: LevelSimulatedReader, replacer: BiConsumer<BlockPos, BlockState>,
        random: RandomSource, pos: BlockPos, trunkPos: BlockPos, config: TreeConfiguration
    ): Boolean {
        for (dir in Direction.Plane.HORIZONTAL) {
            val originalPos = trunkPos.above(trunkOffsetY.sample(random)).relative(dir, 1)
            val axis = dir.axis.invert()
            for (listPos in listOf(originalPos.relative(axis, 1), originalPos, originalPos.relative(axis, -1))) {
                if (random.nextInt(0, cascadeRootConfig.chanceForRoot) == 0) continue
                var movingPos = listPos
                for (ignored in 0..random.nextInt(0, cascadeRootConfig.maxDistanceFromTrunk.sample(random))) {
                    movingPos = movingPos.relative(dir, 1)
                    if (canPlaceRoot(world, movingPos))
                        replacer.placeRoot(movingPos, random) { it.trySetValue(RotatedPillarBlock.AXIS, dir.axis) }
                }
                for (ignored in 0..cascadeRootConfig.maxRootLength) {
                    movingPos = movingPos.below()
                    if (canPlaceRoot(world, movingPos)) replacer.placeRoot(movingPos, random)
                    else break
                }
            }
        }
        return true
    }

    private fun BiConsumer<BlockPos, BlockState>.placeRoot(
        pos: BlockPos, random: RandomSource, modify: (BlockState) -> BlockState = { it }
    ) = this.accept(pos, modify.invoke(rootProvider.getState(random, pos)))


    override fun canPlaceRoot(world: LevelSimulatedReader, pos: BlockPos): Boolean {
        return super.canPlaceRoot(world, pos)
                || world.isStateAtPosition(pos) { it.`is`(cascadeRootConfig.canGrowThrough) }
    }

    override fun getTrunkOrigin(pos: BlockPos, random: RandomSource?): BlockPos = pos

    companion object {
        val CODEC: MapCodec<CascadeRootPlacer> = RecordCodecBuilder.mapCodec { instance ->
            rootPlacerParts(instance)
                .and(CascadeRootConfig.CODEC.fieldOf("cascade_root_placement").forGetter { it.cascadeRootConfig })
                .apply(instance, ::CascadeRootPlacer)
        }

        fun Direction.Axis.invert() = when (this) {
            Direction.Axis.X -> Direction.Axis.Z
            Direction.Axis.Y -> Direction.Axis.Y
            Direction.Axis.Z -> Direction.Axis.X
        }
    }
}
