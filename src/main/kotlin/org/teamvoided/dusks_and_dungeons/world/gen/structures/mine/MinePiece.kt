package org.teamvoided.dusks_and_dungeons.world.gen.structures.mine

import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.structure.BoundingBox
import net.minecraft.world.level.levelgen.structure.StructurePiece
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType
import org.teamvoided.dusks_and_dungeons.util.getIntOr
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MinePieces.TAG_TYPE
import kotlin.math.max
import kotlin.math.min

abstract class MinePiece : StructurePiece {

    var type: MineSettings

    constructor(
        pieceType: StructurePieceType, genDepth: Int, type: MineSettings, boundingBox: BoundingBox,
    ) : super(pieceType, genDepth, boundingBox) {
        this.type = type
    }

    constructor(type: StructurePieceType, tag: CompoundTag) : super(type, tag) {
        this.type = MineSettings.byId(tag.getIntOr(TAG_TYPE, 0))
    }

    override fun canBeReplaced(level: LevelReader, x: Int, y: Int, z: Int, chunkBB: BoundingBox): Boolean {
        return !type.isStructure(getBlock(level, x, y, z, chunkBB))
    }

    override fun addAdditionalSaveData(ctx: StructurePieceSerializationContext, tag: CompoundTag) {
        tag.putInt(TAG_TYPE, type.ordinal)
    }

    fun isSupportingBox(level: BlockGetter, chunkBB: BoundingBox, x0: Int, x1: Int, y1: Int, z0: Int): Boolean {
        for (x in x0..x1) {
            if (getBlock(level, x, y1 + 1, z0, chunkBB).isAir) {
                return false
            }
        }

        return true
    }

    protected fun isInInvalidLocation(level: LevelAccessor, chunkBB: BoundingBox): Boolean {
        val x0 = max(boundingBox.minX() - 1, chunkBB.minX())
        val y0 = max(boundingBox.minY() - 1, chunkBB.minY())
        val z0 = max(boundingBox.minZ() - 1, chunkBB.minZ())
        val x1 = min(boundingBox.maxX() + 1, chunkBB.maxX())
        val y1 = min(boundingBox.maxY() + 1, chunkBB.maxY())
        val z1 = min(boundingBox.maxZ() + 1, chunkBB.maxZ())
        val blockPos = MutableBlockPos((x0 + x1) / 2, (y0 + y1) / 2, (z0 + z1) / 2)
        if (level.getBiome(blockPos).`is`(BiomeTags.MINESHAFT_BLOCKING)) {
            return true
        }

        for (x in x0..x1) {
            for (z in z0..z1) {
                if (!level.getFluidState(blockPos.set(x, y0, z)).isEmpty) {
                    return true
                }

                if (!level.getFluidState(blockPos.set(x, y1, z)).isEmpty) {
                    return true
                }
            }
        }

        for (x in x0..x1) {
            for (y in y0..y1) {
                if (!level.getFluidState(blockPos.set(x, y, z0)).isEmpty) {
                    return true
                }

                if (!level.getFluidState(blockPos.set(x, y, z1)).isEmpty) {
                    return true
                }
            }
        }

        for (z in z0..z1) {
            for (y in y0..y1) {
                if (!level.getFluidState(blockPos.set(x0, y, z)).isEmpty) {
                    return true
                }

                if (!level.getFluidState(blockPos.set(x1, y, z)).isEmpty) {
                    return true
                }
            }
        }

        return false
    }

    fun setPlanksBlock(level: WorldGenLevel, chunkBB: BoundingBox, planksBlock: BlockState, x: Int, y: Int, z: Int) {
        if (!isInterior(level, x, y, z, chunkBB)) {
            return
        }
        val pos = getWorldPos(x, y, z)
        val existingState = level.getBlockState(pos)
        if (!existingState.isFaceSturdy(level, pos, Direction.UP)) {
            level.setBlock(pos, planksBlock, 2)
        }
    }

}