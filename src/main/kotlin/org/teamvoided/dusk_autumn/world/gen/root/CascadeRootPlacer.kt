package org.teamvoided.dusk_autumn.world.gen.root

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.PillarBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.root.AboveRootPlacement
import net.minecraft.world.gen.root.RootPlacer
import net.minecraft.world.gen.root.RootPlacerType
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import org.teamvoided.dusk_autumn.init.DnDWorldgen
import java.util.*
import java.util.function.BiConsumer

class CascadeRootPlacer(
    trunkOffsetY: IntProvider,
    rootProvider: BlockStateProvider,
    aboveRootPlacement: Optional<AboveRootPlacement>,
    private val cascadeRootConfig: CascadeRootConfig
) : RootPlacer(trunkOffsetY, rootProvider, aboveRootPlacement) {

    override fun getType(): RootPlacerType<*> = DnDWorldgen.CASCADE_ROOT_PLACER

    override fun generate(
        world: TestableWorld, replacer: BiConsumer<BlockPos, BlockState>,
        random: RandomGenerator, pos: BlockPos, trunkPos: BlockPos, config: TreeFeatureConfig
    ): Boolean {
        for (dir in Direction.Type.HORIZONTAL) {
            val originalPos = trunkPos.up(trunkOffsetY.get(random)).offset(dir, 1)
            val axis = dir.axis.invert()
            for (listPos in listOf(originalPos.offset(axis, 1), originalPos, originalPos.offset(axis, -1))) {
                if (random.range(0, cascadeRootConfig.chanceForRoot) == 0) continue
                var movingPos = listPos
                for (ignored in 0..random.range(0, cascadeRootConfig.maxDistanceFromTrunk)) {
                    movingPos = movingPos.offset(dir, 1)
                    if (canReplace(world, movingPos))
                        replacer.placeRoot(movingPos, random) { it.with(PillarBlock.AXIS, dir.axis) }
                }
                for (ignored in 0..cascadeRootConfig.maxRootLength) {
                    movingPos = movingPos.down()
                    if (canReplace(world, movingPos)) replacer.placeRoot(movingPos, random)
                    else break
                }
            }
        }
        return true
    }

    private fun BiConsumer<BlockPos, BlockState>.placeRoot(
        pos: BlockPos, random: RandomGenerator, modify: (BlockState) -> BlockState = { it }
    ) = this.accept(pos, modify.invoke(rootProvider.getBlockState(random, pos)))


    override fun canReplace(world: TestableWorld, pos: BlockPos): Boolean {
        return super.canReplace(world, pos)
                || world.testBlockState(pos) { it.isIn(cascadeRootConfig.canGrowThrough) }
    }

    override fun getTrunkOrigin(pos: BlockPos, random: RandomGenerator?): BlockPos = pos

    companion object {
        val CODEC: MapCodec<CascadeRootPlacer> = RecordCodecBuilder.mapCodec { instance ->
            rootPlacerCodec(instance)
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
