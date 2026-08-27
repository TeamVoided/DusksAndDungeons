package org.teamvoided.dusks_and_dungeons.util.mixin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
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
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.SixWayFacingBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.mixin.accessors.SculkBlockAccessor

object DirectionalSculk {

    // region Block Code
    val BOTTOM_SHAPE: VoxelShape = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0)
    val TOP_SHAPE: VoxelShape = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)
    val SIDE_SHAPE: VoxelShape = Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0)

    val SHAPES = SixWayFacingBlock.createShapeMap(BOTTOM_SHAPE, TOP_SHAPE, SIDE_SHAPE)

    @JvmField
    val FACING: DirectionProperty = BlockStateProperties.FACING

    @JvmStatic
    fun getShape(state: BlockState): VoxelShape {
        return SHAPES[state.getValue(FACING)] ?: Shapes.block()
    }

    @JvmStatic
    fun isCalibrated(block: Block) = block is CalibratedSculkSensorBlock

    @JvmStatic
    fun isUp(state: BlockState) = state.getValue(FACING) == Direction.UP

    @JvmStatic
    fun getPlacementState(original: BlockState, ctx: BlockPlaceContext): BlockState {
        return original.setValue(FACING, ctx.clickedFace)
    }

    @JvmStatic
    fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    @JvmStatic
    fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)))
    }

    @JvmStatic
    fun getWardenSpawnPos(level: ServerLevel, pos: BlockPos, beState: BlockState): BlockPos {
        if (isUp(beState)) {
            return pos
        }
        val movingPos = pos.mutable()
        repeat(29) {
            if (!level.getBlockState(movingPos.move(Direction.DOWN)).`is`(DnDBlockTags.SHRIEKER_SEARCH_BYPASSES)) {
                return movingPos.above()
            }
        }
        return pos
    }

    @JvmStatic
    fun isCreativeFlying(entity: Entity?): Boolean {
        return entity is Player && entity.isCreative && entity.abilities.flying
    }

    // endregion

    // region Spreader Code

    @JvmStatic
    fun tryUseChargeSpreadRewrite(
        level: LevelAccessor, pos: BlockPos, charge: Int, cost: Int, random: RandomSource, canSummon: Boolean,
    ): Boolean {
        val block = level.getBlockState(pos).block
        if (block !is SculkBlockAccessor || random.nextInt(cost) >= charge) {
            return false
        }

        for (dir in Direction.entries) {
            val posOffset = pos.relative(dir)
            if (canSpreadTo(level, pos, dir)) {
                var growthState = block.dnd_getRandomGrowthState(level, posOffset, random, canSummon)
                if (growthState.hasProperty(FACING) || dir == Direction.UP) {
                    growthState = growthState.trySetValue(FACING, dir)
                    level.setBlock(posOffset, growthState, Block.UPDATE_ALL)
                    level.playSound(null, pos, growthState.soundType.placeSound, SoundSource.BLOCKS, 1f, 1f)
                    return true
                }
            }
        }
        return false
    }

    fun canSpreadTo(level: LevelAccessor, pos: BlockPos, dir: Direction): Boolean {
        val upState = level.getBlockState(pos.relative(dir))
        if (upState.isAir || upState.`is`(Blocks.WATER) && upState.fluidState.`is`(Fluids.WATER)) {
            var limit = 0
            for (pos in getPosFromDirection(pos, dir)) {
                if (level.getBlockState(pos).`is`(DnDBlockTags.SCULK_SPREAD_SEARCH) && ++limit > 2) {
                    return false
                }
            }
            return true
        }
        return false
    }

    fun getPosFromDirection(pos: BlockPos, dir: Direction): Iterator<BlockPos> {
        return when (dir) {
            Direction.UP -> BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 2, 4))
            Direction.DOWN -> BlockPos.betweenClosed(pos.offset(-4, -2, -4), pos.offset(4, 0, 4))
            Direction.NORTH -> BlockPos.betweenClosed(pos.offset(-4, -4, -2), pos.offset(4, 4, 0))
            Direction.SOUTH -> BlockPos.betweenClosed(pos.offset(-4, -4, 0), pos.offset(4, 4, 2))
            Direction.WEST -> BlockPos.betweenClosed(pos.offset(-2, -4, -4), pos.offset(0, 4, 4))
            Direction.EAST -> BlockPos.betweenClosed(pos.offset(0, -4, -4), pos.offset(2, 4, 4))
        }.iterator()
    }

    @JvmStatic
    fun featureCatalystAndShrieker(ctx: FeaturePlaceContext<SculkPatchConfiguration>) {
        val cfg = ctx.config()
        val random = ctx.random()
        val level = ctx.level()
        val pos = ctx.origin()

        if (random.nextFloat() <= cfg.catalystChance()) {
            extraGrowthCatalyst(level, pos)
        }

        val extraGrowths = cfg.extraRareGrowths.sample(random)
        repeat(extraGrowths - 1) {
            extraGrowthShrieker(level, random, pos)
        }
    }

    fun extraGrowthCatalyst(level: WorldGenLevel, pos: BlockPos) {
        for (dir in Direction.entries) {
            val posOffset = pos.relative(dir)
            if (
                level.getBlockState(posOffset).isCollisionShapeFullBlock(level, posOffset) &&
                (dir == Direction.DOWN || level.getBlockState(pos).`is`(BlockTags.REPLACEABLE))
            ) {
                level.setBlock(
                    pos,
                    Blocks.SCULK_CATALYST.defaultBlockState().setValue(FACING, dir.opposite),
                    Block.UPDATE_ALL
                )
                return
            }
        }
    }

    fun extraGrowthShrieker(level: WorldGenLevel, random: RandomSource, pos: BlockPos) {
        for (dir in Direction.entries) {
            val offsetPos = pos.getRandomOffset(random, dir)
            if (level.getBlockState(offsetPos).isAir &&
                level.getBlockState(offsetPos.relative(dir)).isFaceSturdy(level, offsetPos.relative(dir), dir.opposite)
            ) {
                level.setBlock(
                    offsetPos,
                    Blocks.SCULK_SHRIEKER.defaultBlockState()
                        .setValue(FACING, dir.opposite)
                        .setValue(SculkShriekerBlock.CAN_SUMMON, true),
                    Block.UPDATE_ALL
                )
                return
            }
        }
    }

    fun BlockPos.getRandomOffset(random: RandomSource, dir: Direction): BlockPos {
        val x = random.nextInt(5) - 2
        val y = 0
        val z = random.nextInt(5) - 2
        return when (dir.axis) {
            Direction.Axis.Y -> offset(x, y, z)
            Direction.Axis.X -> offset(y, x, z)
            Direction.Axis.Z -> offset(x, z, y)
        }
    }

    // endregion

}