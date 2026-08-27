package org.teamvoided.dusks_and_dungeons.util.mixin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SculkShriekerBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.SculkPatchConfiguration
import net.minecraft.world.level.material.Fluids
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.mixin.accessors.SculkBlockAccessor

object SculkDirectionalStuff {

    /* - - - SPREADING FUNCTIONS - - - */
    @JvmStatic
    fun tryUseChargeSpreadRewrite(
        world: LevelAccessor, pos: BlockPos, charge: Int, cost: Int, random: RandomSource, canSummon: Boolean,
    ): Boolean {
        val worldState = world.getBlockState(pos)
        val worldBlock = worldState.block
        if (worldBlock is SculkBlockAccessor) {
            if (random.nextInt(cost) < charge) {
                Direction.entries.forEach {
                    val posOffset: BlockPos = pos.relative(it)
                    if (canSpreadTo(world, pos, it)) {
                        var blockState: BlockState =
                            worldBlock.dnd_getRandomGrowthState(world, posOffset, random, canSummon)
                        if (blockState.hasProperty(BlockStateProperties.FACING) || it == Direction.UP) {
                            blockState = blockState.trySetValue(BlockStateProperties.FACING, it)
                            world.setBlock(posOffset, blockState, 3)
                            world.playSound(null, pos, blockState.soundType.placeSound, SoundSource.BLOCKS, 1f, 1f)
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    fun canSpreadTo(world: LevelAccessor, pos: BlockPos, direction: Direction): Boolean {
        val upState = world.getBlockState(pos.relative(direction))
        if (upState.isAir || upState.`is`(Blocks.WATER) && upState.fluidState.`is`(Fluids.WATER)) {
            val search: Iterator<BlockPos> = getIteratorFromDirection(pos, direction)

            var limit = 0
            search.forEach {
                if (world.getBlockState(it).`is`(DnDBlockTags.SCULK_SPREAD_SEARCH) && ++limit > 2) {
                    return false
                }
            }
            return true
        }
        return false
    }

    fun getIteratorFromDirection(pos: BlockPos, dir: Direction): Iterator<BlockPos> {
        return when (dir) {
            Direction.UP -> BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 2, 4))
            Direction.DOWN -> BlockPos.betweenClosed(pos.offset(-4, -2, -4), pos.offset(4, 0, 4))
            Direction.NORTH -> BlockPos.betweenClosed(pos.offset(-4, -4, -2), pos.offset(4, 4, 0))
            Direction.SOUTH -> BlockPos.betweenClosed(pos.offset(-4, -4, 0), pos.offset(4, 4, 2))
            Direction.WEST -> BlockPos.betweenClosed(pos.offset(-2, -4, -4), pos.offset(0, 4, 4))
            Direction.EAST -> BlockPos.betweenClosed(pos.offset(0, -4, -4), pos.offset(2, 4, 4))
        }.iterator()
    }

    /* - - - FEATURE FUNCTIONS - - - */
    @JvmStatic
    fun featureCatalystAndShrieker(context: FeaturePlaceContext<SculkPatchConfiguration>) {
        val config = context.config()
        val random = context.random()
        val world = context.level()
        val blockPos = context.origin()

        //blockPos is sculk block it is placing on
        if (random.nextFloat() <= config.catalystChance()) {
            extraGrowthCatalyst(world, blockPos)
        }

        val extraGrowths = config.extraRareGrowths.sample(random)
        for (loop in 0 until extraGrowths) {
            extraGrowthShrieker(world, random, blockPos)
        }
    }


    fun extraGrowthCatalyst(world: WorldGenLevel, blockPos: BlockPos) {
        Direction.entries.forEach {
            val posOffset = blockPos.relative(it)
            if (
                world.getBlockState(posOffset).isCollisionShapeFullBlock(world, posOffset) &&
                (it == Direction.DOWN || world.getBlockState(blockPos).`is`(BlockTags.REPLACEABLE))
            ) {
                world.setBlock(
                    blockPos,
                    Blocks.SCULK_CATALYST.defaultBlockState().setValue(BlockStateProperties.FACING, it.opposite),
                    3
                )
                return
            }
        }
    }

    fun extraGrowthShrieker(world: WorldGenLevel, random: RandomSource, blockPos: BlockPos) {
        Direction.entries.forEach {
            val blockPosRand = blockPos.getRandomOffset(random, it)
            if (world.getBlockState(blockPosRand).isAir &&
                world.getBlockState(blockPosRand.relative(it))
                    .isFaceSturdy(world, blockPosRand.relative(it), it.opposite)
            ) {
                world.setBlock(
                    blockPosRand,
                    Blocks.SCULK_SHRIEKER.defaultBlockState()
                        .setValue(BlockStateProperties.FACING, it.opposite)
                        .setValue(SculkShriekerBlock.CAN_SUMMON, true),
                    3
                )
                return
            }
        }
    }

    fun BlockPos.getRandomOffset(random: RandomSource, direction: Direction): BlockPos {
        val x = random.nextInt(5) - 2
        val y = 0
        val z = random.nextInt(5) - 2
        return when (direction.axis) {
            Direction.Axis.Y -> offset(x, y, z)
            Direction.Axis.X -> offset(y, x, z)
            Direction.Axis.Z -> offset(x, z, y)
        }
    }

}