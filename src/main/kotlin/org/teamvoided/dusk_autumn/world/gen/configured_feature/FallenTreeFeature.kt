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
import net.minecraft.world.WorldView
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import org.teamvoided.dusk_autumn.util.getPropertyFromDirection
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FallenTreeConfig

open class FallenTreeFeature(codec: Codec<FallenTreeConfig>) :
    Feature<FallenTreeConfig>(codec) {
    override fun place(context: FeatureContext<FallenTreeConfig>): Boolean {
        val world = context.world
        val random = context.random
        val config = context.config
        val pos = context.origin
        if (world.isOutOfWorld(pos)) return false

        val direction = Direction.Type.HORIZONTAL.random(random)
        val trunkLength = config.trunkLength.get(random)
        val trunkOffset = config.trunkDistanceFromStump.get(random) + 1


        if (!world.getBlockState(pos).isIn(config.replaceable)) return false

        val trunkStartPos = pos.offset(direction, trunkOffset)

        val placeStump: Boolean = (if (trunkLength <= 3) ::placeSmallFallenTrunk else ::placeFallenTrunk
                ).invoke(trunkLength, direction, trunkStartPos, config, world, random)

        if (placeStump) {
            world.placeLogs(pos, Direction.UP, config.stumpHeight.get(random), config, random, true)
        }
        return placeStump
    }

    //places the small fallen trunk
    private fun placeSmallFallenTrunk(
        trunkLength: Int, direction: Direction, start: BlockPos, config: FallenTreeConfig,
        world: StructureWorldAccess, random: RandomGenerator
    ): Boolean {
        var pos = start.offset(Direction.UP, 3)
        if (world.aboveTop(pos)) return false

        //checks if the position is eligible, else, moves down until its blocked
        for (ignored in 0..config.trunkVerticalRange) {
            pos = pos.down()
            if (world.bellowBottom(pos) ||
                (world.getBlockState(pos).isFullCube(world, pos) && !world.getBlockState(pos).isIn(config.replaceable))
            ) break /* stops moving check position down and cancels feature */

            if (world.getBlockState(pos).isSideSolidFullSquare(world, pos, Direction.UP)) {
                world.placeLogs(pos.up(), direction, trunkLength, config, random) //places the trunk
                return true
            }
        }
        return false
    }

    //places the regular fallen trunk
    private fun placeFallenTrunk(
        trunkLength: Int, direction: Direction, start: BlockPos, config: FallenTreeConfig,
        world: StructureWorldAccess, random: RandomGenerator
    ): Boolean {
        val trunkLength3 = trunkLength / 3
        var pos: BlockPos = start.offset(Direction.UP, 3)
        var near = pos.offset(direction, trunkLength3)
        var far = pos.offset(direction, trunkLength - trunkLength3)
        for (ignored in 0..config.trunkVerticalRange) {
            if (
                world.getBlockState(near).isSideSolidFullSquare(world, near, Direction.UP) &&
                world.getBlockState(far).isSideSolidFullSquare(world, far, Direction.UP)
            ) {
                world.placeLogs(pos, direction, trunkLength, config, random)
                return true
            }
            pos = pos.down()
            near = near.down()
            far = far.down()
            if (
                (world.getBlockState(near).isFullCube(world, near) && !world.getBlockState(near)
                    .isIn(config.replaceable)) ||
                world.bellowBottom(pos)
            ) /* stops moving check position down and cancels feature */ break
        }
        return false
    }

    fun StructureWorldAccess.placeLogs(
        pos: BlockPos, direction: Direction, size: Int, config: FallenTreeConfig, random: RandomGenerator,
        stump: Boolean = false
    ) {
        val width = config.treeWidth
        if (width == 1) {
            for (loop in 0..size) {
                val position = pos.offset(direction, loop)
                if (this.getBlockState(pos).isIn(config.replaceable)) {
                    val logBlockState = if (stump) config.stumpBlock.getBlockState(random, position)
                    else config.logBlock.getBlockState(random, position)
                    this.setBlockState(
                        pos, logBlockState
                            .withIfExists(PillarBlock.AXIS, direction.axis)
                            .withIfExists(Properties.WATERLOGGED, this.getFluidState(pos).fluid == Fluids.WATER), 3
                    )
                }
                if (stump) placeSides(pos, config, this, random)
                placeTopper(pos, config, this, random)
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
                            this.setBlockState(
                                position, config.stumpBlock.getBlockState(random, position)
                                    .withIfExists(
                                        Properties.WATERLOGGED,
                                        this.getFluidState(pos).fluid == Fluids.WATER
                                    ), 3
                            )
                            placeSides(pos, config, this, random)
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

    fun placeTopper(pos: BlockPos, config: FallenTreeConfig, world: StructureWorldAccess, random: RandomGenerator) {
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

    companion object {
        // move to util
        fun WorldView.isOutOfWorld(pos: BlockPos): Boolean = this.bellowBottom(pos) || this.aboveTop(pos)
        fun WorldView.bellowBottom(pos: BlockPos): Boolean = pos.y <= this.bottomY + 1
        fun WorldView.aboveTop(pos: BlockPos): Boolean = pos.y >= this.topY - 1
    }
}
