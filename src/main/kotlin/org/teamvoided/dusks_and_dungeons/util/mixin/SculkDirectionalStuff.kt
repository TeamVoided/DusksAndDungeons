package org.teamvoided.dusks_and_dungeons.util.mixin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Vec3i
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.SculkPatchConfiguration
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.Vec3
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.mixin.accessors.SculkBlockAccessor

object SculkDirectionalStuff {

    /* - - - BLOCK FUNCTIONS - - - */
    val FACING: DirectionProperty = BlockStateProperties.FACING

    @JvmStatic
    fun getPlacementState(supr: BlockState?, ctx: BlockPlaceContext?): BlockState? {
        return if (ctx == null || supr == null) supr
        else supr.setValue(DirectionalSculk.FACING, ctx.clickedFace)
    }

    @JvmStatic
    fun isNotUp(state: BlockState) = state.getValue(DirectionalSculk.FACING) != Direction.UP

    @JvmStatic
    fun isNotCalibrated(block: Block) = block !is CalibratedSculkSensorBlock

    @JvmStatic
    fun isNotUpCalibrated(state: BlockState) = isNotCalibrated(state.block) && isNotUp(state)

    @JvmStatic
    fun spin(state: BlockState, rotation: Rotation): BlockState =
        state.setValue(DirectionalSculk.FACING, rotation.rotate(state.getValue(DirectionalSculk.FACING)))

    @JvmStatic
    fun spin(state: BlockState, mirror: Mirror): BlockState =
        state.rotate(mirror.getRotation(state.getValue(DirectionalSculk.FACING)))

    @JvmStatic
    fun noCreativeFlightAnnoyance(entity: Entity): Boolean =
        !(entity is Player && entity.isCreative && entity.abilities.flying)


    /* - - - SPREADING FUNCTIONS - - - */
    @JvmStatic
    fun tryUseChargeSpreadRewrite(
        world: LevelAccessor,
        pos: BlockPos,
        charge: Int,
        cost: Int,
        random: RandomSource,
        canSummon: Boolean
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
                            sound(world, pos, blockState)
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun sound(world: LevelAccessor, pos: BlockPos, state: BlockState) =
        world.playSound(null, pos, state.soundType.placeSound, SoundSource.BLOCKS, 1f, 1f)

    private fun canSpreadTo(world: LevelAccessor, pos: BlockPos, direction: Direction): Boolean {
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
        } else {
            return false
        }
    }
//            do {
//                if (!search.hasNext()) {
//                    return true
//                }
//
//                val searchPos = search.next()
//                val worldState = world.getBlockState(searchPos)
//                if (worldState.isIn(DuskBlockTags.SCULK_SPREAD_SEARCH)) {
//                    ++i
//                }
//            } while (i <= 2)
//
//            return false

    fun getIteratorFromDirection(pos: BlockPos, direction: Direction): Iterator<BlockPos> {
//        val var4: Iterator<BlockPos> = BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 2, 4)).iterator()
        return when (direction) {
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


    private fun extraGrowthCatalyst(world: WorldGenLevel, blockPos: BlockPos) {
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

    private fun extraGrowthShrieker(
        world: WorldGenLevel,
        random: RandomSource,
        blockPos: BlockPos
    ) {
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

    private fun BlockPos.getRandomOffset(random: RandomSource, direction: Direction): BlockPos {
        val x = random.nextInt(5) - 2
        val y = 0
        val z = random.nextInt(5) - 2
        return when (direction.axis) {
            Direction.Axis.Y -> this.offset(Vec3i(x, y, z))
            Direction.Axis.X -> this.offset(Vec3i(y, x, z))
            Direction.Axis.Z -> this.offset(Vec3i(x, z, y))
        }
    }

}

fun Vec3i.toVec3d(): Vec3 {
    return Vec3(this.x.toDouble(), this.y.toDouble(), this.z.toDouble())
}