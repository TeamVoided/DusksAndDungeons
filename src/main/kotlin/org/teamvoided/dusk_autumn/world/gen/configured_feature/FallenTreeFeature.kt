package org.teamvoided.dusk_autumn.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.block.BlockState
import net.minecraft.block.PillarBlock
import net.minecraft.fluid.Fluids
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FallenTreeConfig

open class FallenTreeFeature(codec: Codec<FallenTreeConfig>) :
    Feature<FallenTreeConfig>(codec) {
    override fun place(context: FeatureContext<FallenTreeConfig>): Boolean {
        val structureWorldAccess = context.world
        val origin = context.origin
        // bottom of world check
        if (origin.y <= structureWorldAccess.bottomY + 1 || origin.y >= structureWorldAccess.topY - 1) return false
        val randomGenerator = context.random
        val direction = Direction.Type.HORIZONTAL.random(randomGenerator)
        val fallenTreeConfig = context.config
        val trunkLength = fallenTreeConfig.trunkLength.get(randomGenerator)
        val extraDistance = fallenTreeConfig.trunkDistanceFromStump.get(randomGenerator)
        if (structureWorldAccess.getBlockState(origin).isIn(fallenTreeConfig.replaceable)) {
            placeLog(
                fallenTreeConfig.stumpBlock.getBlockState(randomGenerator, origin),
                origin,
                Direction.UP,
                fallenTreeConfig,
                structureWorldAccess
            )
            if (trunkLength <= 3) {
                //edge case
                val posPlacement = origin.offset(direction, extraDistance)
                return placeSmallFallenTrunk(
                    trunkLength,
                    direction,
                    posPlacement,
                    fallenTreeConfig,
                    structureWorldAccess,
                    randomGenerator
                )
            } else {
                val trunkStartPos = origin.offset(direction, extraDistance)
                return placeFallenTrunk(
                    trunkLength,
                    direction,
                    trunkStartPos,
                    fallenTreeConfig,
                    structureWorldAccess,
                    randomGenerator
                )
            }
        }
        return false
    }

    //places the small fallen trunk
    private fun placeSmallFallenTrunk(
        trunkLength: Int,
        direction: Direction,
        start: BlockPos,
        config: FallenTreeConfig,
        world: StructureWorldAccess,
        random: RandomGenerator
    ): Boolean {
        var pos: BlockPos = start.offset(Direction.UP)
        //checks if the position is eligible, else, moves down until its blocked
        for (posCheck in 1 downTo config.trunkVerticalRange) {
            pos = pos.offset(Direction.DOWN)
            if (world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)) {
                //places the trunk
                for (length in 0..trunkLength) {
                    val posPlacer = pos.offset(direction, length)
                    placeLog(
                        config.logBlock.getBlockState(random, pos),
                        posPlacer,
                        direction,
                        config,
                        world
                    )
                }
                return true
            } else if (world.getBlockState(pos).isSolid || pos.y <= world.bottomY + 1) /* stops moving check position down and cancels feature */ break
        }
        return false
    }

    //places the regular fallen trunk
    private fun placeFallenTrunk(
        trunkLength: Int,
        direction: Direction,
        start: BlockPos,
        config: FallenTreeConfig,
        world: StructureWorldAccess,
        random: RandomGenerator
    ): Boolean {
        println("no, the complex fallen logs have not been done yet, this is done with horrendous code")
//        return false
        var pos: BlockPos = start.offset(Direction.UP)
        for (posCheck in 1 downTo config.trunkVerticalRange) {
            pos = pos.offset(Direction.DOWN)
            for (length in 0..trunkLength / 2) {
                val near = pos.down().offset(direction, length)
                val far = pos.down().offset(direction, trunkLength).offset(direction.opposite, length)
                if (
                    world.getBlockState(near).isSideSolidFullSquare(world, near, Direction.UP) &&
                    world.getBlockState(far).isSideSolidFullSquare(world, far, Direction.UP)
                ) {
                    for (ignore in 0..trunkLength){
                        val posPlacer = pos.offset(direction, length)
                        placeLog(
                            config.logBlock.getBlockState(random, pos),
                            posPlacer,
                            direction,
                            config,
                            world
                        )
                    }
                    return true
                }
            }
            if (world.getBlockState(pos).isSolid || pos.y <= world.bottomY + 1) /* stops moving check position down and cancels feature */ break
        }
        return false
    }

    fun placeLog(
        state: BlockState,
        pos: BlockPos,
        direction: Direction,
        config: FallenTreeConfig,
        world: StructureWorldAccess
    ) {
        if (world.getBlockState(pos).isIn(config.replaceable)) {
            val blockState = state
                .withIfExists(PillarBlock.AXIS, direction.axis)
                .withIfExists(Properties.WATERLOGGED, world.getFluidState(pos).fluid == Fluids.WATER)
            world.setBlockState(pos, blockState, 3)
        }
    }
}