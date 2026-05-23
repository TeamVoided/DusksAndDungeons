package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import org.teamvoided.dusks_and_dungeons.util.getPropertyFromDirection
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FallenTreeConfig

open class FallenTreeFeature(codec: Codec<FallenTreeConfig>) :
    Feature<FallenTreeConfig>(codec) {
    override fun place(context: FeaturePlaceContext<FallenTreeConfig>): Boolean {
        val world = context.level()
        val random = context.random()
        val config = context.config()
        val pos = context.origin()
        if (world.isOutOfWorld(pos)) return false

        val direction = Direction.Plane.HORIZONTAL.getRandomDirection(random)
        val trunkLength = config.trunkLength.sample(random)
        val trunkOffset = config.trunkDistanceFromStump.sample(random) + 1


        if (!world.getBlockState(pos).`is`(config.replaceable)) return false

        val trunkStartPos = pos.relative(direction, trunkOffset)

        val placeStump: Boolean = (if (trunkLength <= 3) ::placeSmallFallenTrunk else ::placeFallenTrunk
                ).invoke(trunkLength, direction, trunkStartPos, config, world, random)

        if (placeStump) {
            world.placeLogs(pos, Direction.UP, config.stumpHeight.sample(random), config, random, true)
        }
        return placeStump
    }

    //places the small fallen trunk
    private fun placeSmallFallenTrunk(
        trunkLength: Int, direction: Direction, start: BlockPos, config: FallenTreeConfig,
        world: WorldGenLevel, random: RandomSource
    ): Boolean {
        var pos = start.relative(Direction.UP, 3)
        if (world.aboveTop(pos)) return false

        //checks if the position is eligible, else, moves down until its blocked
        for (ignored in 0..config.trunkVerticalRange) {
            pos = pos.below()
            if (world.bellowBottom(pos) ||
                (world.getBlockState(pos).isCollisionShapeFullBlock(world, pos) && !world.getBlockState(pos).`is`(config.replaceable))
            ) break /* stops moving check position down and cancels feature */

            if (world.getBlockState(pos).isFaceSturdy(world, pos, Direction.UP)) {
                world.placeLogs(pos.above(), direction, trunkLength, config, random) //places the trunk
                return true
            }
        }
        return false
    }

    //places the regular fallen trunk
    private fun placeFallenTrunk(
        trunkLength: Int, direction: Direction, start: BlockPos, config: FallenTreeConfig,
        world: WorldGenLevel, random: RandomSource
    ): Boolean {
        val trunkLength3 = trunkLength / 3
        var pos: BlockPos = start.relative(Direction.UP, 3)
        var near = pos.relative(direction, trunkLength3)
        var far = pos.relative(direction, trunkLength - trunkLength3)
        for (ignored in 0..config.trunkVerticalRange) {
            if (
                world.getBlockState(near).isFaceSturdy(world, near, Direction.UP) &&
                world.getBlockState(far).isFaceSturdy(world, far, Direction.UP)
            ) {
                world.placeLogs(pos, direction, trunkLength, config, random)
                return true
            }
            pos = pos.below()
            near = near.below()
            far = far.below()
            if (
                (world.getBlockState(near).isCollisionShapeFullBlock(world, near) && !world.getBlockState(near)
                    .`is`(config.replaceable)) ||
                world.bellowBottom(pos)
            ) /* stops moving check position down and cancels feature */ break
        }
        return false
    }

    fun WorldGenLevel.placeLogs(
        pos: BlockPos, direction: Direction, size: Int, config: FallenTreeConfig, random: RandomSource,
        stump: Boolean = false
    ) {
        val width = config.treeWidth
        if (width == 1) {
            for (loop in 0..size) {
                val position = pos.relative(direction, loop)
                if (this.getBlockState(pos).`is`(config.replaceable)) {
                    val logBlockState = if (stump) config.stumpBlock.getState(random, position)
                    else config.logBlock.getState(random, position)
                    this.setBlock(
                        pos, logBlockState
                            .trySetValue(RotatedPillarBlock.AXIS, direction.axis)
                            .trySetValue(BlockStateProperties.WATERLOGGED, this.getFluidState(pos).type == Fluids.WATER), 3
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
                            .relative(
                                directionSide,
                                if (directionSide.axisDirection == Direction.AxisDirection.NEGATIVE) x + offset
                                else x - offset
                            )
                            .relative(direction.opposite, z)
                        for (y in 0..config.stumpHeight.sample(random)) {
                            position = position.above(y)
                            this.setBlock(
                                position, config.stumpBlock.getState(random, position)
                                    .trySetValue(
                                        BlockStateProperties.WATERLOGGED,
                                        this.getFluidState(pos).type == Fluids.WATER
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
        world: WorldGenLevel,
        random: RandomSource
    ) {
        val sideChance = config.stumpSidesChance
        if (sideChance != -1) {
            var vineBlockState = config.stumpSides.getState(random, pos)
            if (vineBlockState != Blocks.AIR) {
                Direction.Plane.HORIZONTAL.forEach {
                    val vinePos = pos.relative(it)
                    val worldBlockState = world.getBlockState(vinePos)
                    vineBlockState = config.stumpSides.getState(random, pos)
                    if (
                        (sideChance == 0 || random.nextInt(0, sideChance) == 0) &&
                        worldBlockState.`is`(config.replaceable)
                    ) {
                        world.setBlock(
                            vinePos,
                            vineBlockState.trySetValue(getPropertyFromDirection(it.opposite), true),
                            3
                        )
                    }
                }
            }
        }
    }

    fun placeTopper(pos: BlockPos, config: FallenTreeConfig, world: WorldGenLevel, random: RandomSource) {
        val topperChance = config.logTopperChance
        if (topperChance != -1 && (topperChance == 0 || random.nextInt(0, topperChance) == 0)) {
            val abovePos = pos.above()
            val mushroomBlockState = config.logTopper.getState(random, abovePos)
            if (mushroomBlockState != Blocks.AIR && world.getBlockState(abovePos).`is`(config.replaceable)) {
                mushroomBlockState
                    .trySetValue(BlockStateProperties.WATERLOGGED, world.getFluidState(pos).type == Fluids.WATER)
                world.setBlock(abovePos, mushroomBlockState, 3)
            }
        }
    }

    companion object {
        // move to util
        fun LevelReader.isOutOfWorld(pos: BlockPos): Boolean = this.bellowBottom(pos) || this.aboveTop(pos)
        fun LevelReader.bellowBottom(pos: BlockPos): Boolean = pos.y <= this.minBuildHeight + 1
        fun LevelReader.aboveTop(pos: BlockPos): Boolean = pos.y >= this.maxBuildHeight - 1
    }
}
