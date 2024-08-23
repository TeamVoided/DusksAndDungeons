package org.teamvoided.dusk_autumn.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.block.Blocks
import net.minecraft.block.PillarBlock
import net.minecraft.fluid.Fluids
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import org.teamvoided.dusk_autumn.util.getPropertyFromDirection
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
        val extraDistance = fallenTreeConfig.trunkDistanceFromStump.get(randomGenerator) + 1
        if (structureWorldAccess.getBlockState(origin).isIn(fallenTreeConfig.replaceable)) {
            val retur: Boolean
            val trunkStartPos = origin.offset(direction, extraDistance)
            if (trunkLength <= 3) {
                //edge case
                retur = placeSmallFallenTrunk(
                    trunkLength,
                    direction,
                    trunkStartPos,
                    fallenTreeConfig,
                    structureWorldAccess,
                    randomGenerator
                )
            } else {
                retur = placeFallenTrunk(
                    trunkLength,
                    direction,
                    trunkStartPos,
                    fallenTreeConfig,
                    structureWorldAccess,
                    randomGenerator
                )
            }
            if (retur) {
                placeLogs(
                    origin,
                    Direction.UP,
                    fallenTreeConfig.stumpHeight.get(randomGenerator),
                    fallenTreeConfig,
                    structureWorldAccess,
                    randomGenerator,
                    true
                )
                return true
            } else return false
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
        var pos: BlockPos = start.offset(Direction.UP, 3)
        //checks if the position is eligible, else, moves down until its blocked
        for (posCheck in 0 downTo -config.trunkVerticalRange) {
            if (world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)) {
                //places the trunk
                placeLogs(
                    pos,
                    direction,
                    trunkLength,
                    config,
                    world,
                    random
                )
                return true
            }
            pos = pos.down()
            if (
                (world.getBlockState(pos).isFullCube(world, pos) &&
                        !world.getBlockState(pos).isIn(config.replaceable)) ||
                pos.y <= world.bottomY + 1
            ) /* stops moving check position down and cancels feature */ break
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
//        println("no, the complex fallen logs have not been done yet, this is done with horrendous code")
//        return false
        val trunkLength3 = trunkLength / 3
        var pos: BlockPos = start.offset(Direction.UP, 3)
        var near = pos.offset(direction, trunkLength3)
        var far = pos.offset(direction, trunkLength - trunkLength3)
        for (posCheck in 0 downTo -config.trunkVerticalRange) {
            if (
                world.getBlockState(near).isSideSolidFullSquare(world, near, Direction.UP) &&
                world.getBlockState(far).isSideSolidFullSquare(world, far, Direction.UP)
            ) {
                placeLogs(
                    pos,
                    direction,
                    trunkLength,
                    config,
                    world,
                    random
                )
                return true
            }
            pos = pos.down()
            near = near.down()
            far = far.down()
            if (
                (world.getBlockState(near).isFullCube(world, near) &&
                        !world.getBlockState(near).isIn(config.replaceable)) ||
                pos.y <= world.bottomY + 1
            ) /* stops moving check position down and cancels feature */ break
        }
        return false
    }

    fun placeLogs(
        pos: BlockPos,
        direction: Direction,
        size: Int,
        config: FallenTreeConfig,
        world: StructureWorldAccess,
        random: RandomGenerator,
        stump: Boolean = false
    ) {
        val width = config.treeWidth
        if (width == 1) {
            for (loop in 0..size) {
                val position = pos.offset(direction, loop)
                if (world.getBlockState(pos).isIn(config.replaceable)) {
                    val logBlockState = if (stump) config.stumpBlock.getBlockState(random, position)
                    else config.logBlock.getBlockState(random, position)
                    world.setBlockState(
                        pos, logBlockState
                            .withIfExists(PillarBlock.AXIS, direction.axis)
                            .withIfExists(Properties.WATERLOGGED, world.getFluidState(pos).fluid == Fluids.WATER), 3
                    )
                }
                if (stump) placeSides(pos, config, world, random)
                placeTopper(pos, config, world, random)
            }
        } else {
            val offset = width - (width / 2)
            val directionSide = when (direction) {
                Direction.NORTH -> Direction.EAST
                Direction.EAST -> Direction.SOUTH
                Direction.SOUTH -> Direction.WEST
                Direction.WEST -> Direction.NORTH
                else -> Direction.NORTH
            }
            if (stump) {
                var position: BlockPos
                for (x in 0..width) {
                    for (z in 0..width) {
                        position = pos
                            .offset(
                                directionSide,
                                if (directionSide.direction == Direction.AxisDirection.NEGATIVE) x + offset
                                else x - offset
                            )
                            .offset(direction.opposite, z)
                        for (y in 0..config.stumpHeight.get(random)) {
                            position = position.up(y)
                            world.setBlockState(
                                position, config.stumpBlock.getBlockState(random, position)
                                    .withIfExists(
                                        Properties.WATERLOGGED,
                                        world.getFluidState(pos).fluid == Fluids.WATER
                                    ), 3
                            )
                            placeSides(pos, config, world, random)
                        }
                    }
                }
            } else {

            }
        }
    }

    fun placeSides(
        pos: BlockPos,
        config: FallenTreeConfig,
        world: StructureWorldAccess,
        random: RandomGenerator
    ) {
        val sideChance = config.stumpSidesChance
        if (sideChance != -1) {
            var vineBlockState = config.stumpSides.getBlockState(random, pos)
            if (vineBlockState != Blocks.AIR) {
                Direction.Type.HORIZONTAL.forEach {
                    val vinePos = pos.offset(it)
                    val worldBlockState = world.getBlockState(vinePos)
                    vineBlockState = config.stumpSides.getBlockState(random, pos)
                    if (
                        (sideChance == 0 || random.range(0, sideChance) == 0) &&
                        worldBlockState.isIn(config.replaceable)
                    ) {
                        world.setBlockState(
                            vinePos,
                            vineBlockState.withIfExists(getPropertyFromDirection(it.opposite), true),
                            3
                        )
                    }
                }
            }
        }
    }

    fun placeTopper(
        pos: BlockPos,
        config: FallenTreeConfig,
        world: StructureWorldAccess,
        random: RandomGenerator
    ) {
        val topperChance = config.logTopperChance
        if (topperChance != -1 && (topperChance == 0 || random.range(0, topperChance) == 0)) {
            val abovePos = pos.up()
            val mushroomBlockState = config.logTopper.getBlockState(random, abovePos)
            if (mushroomBlockState != Blocks.AIR && world.getBlockState(abovePos).isIn(config.replaceable)) {
                mushroomBlockState
                    .withIfExists(Properties.WATERLOGGED, world.getFluidState(pos).fluid == Fluids.WATER)
                world.setBlockState(abovePos, mushroomBlockState, 3)
            }
        }
    }

}
