package org.teamvoided.dusks_and_dungeons.world.gen.structures.mine

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.util.RandomSource
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.StructureManager
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.structure.BoundingBox
import net.minecraft.world.level.levelgen.structure.StructurePiece
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDStructurePiceTypes

class MineStairs : MinePiece {

    constructor(
        genDepth: Int, boundingBox: BoundingBox, direction: Direction, type: MineSettings,
    ) : super(DnDStructurePiceTypes.MINE_STAIRS, genDepth, type, boundingBox) {
        setOrientation(direction)
    }

    constructor(tag: CompoundTag) : super(DnDStructurePiceTypes.MINE_STAIRS, tag)

    override fun addChildren(startPiece: StructurePiece, builder: StructurePieceAccessor, random: RandomSource) {
        val depth = getGenDepth()
        val orientation = orientation ?: return
        when (orientation) {
            Direction.NORTH -> MinePieces.generateAndAddPiece(
                startPiece, builder, random,
                boundingBox.minX(), boundingBox.minY(), boundingBox.minZ() - 1,
                Direction.NORTH, depth
            )

            Direction.SOUTH -> MinePieces.generateAndAddPiece(
                startPiece, builder, random,
                boundingBox.minX(), boundingBox.minY(), boundingBox.maxZ() + 1,
                Direction.SOUTH, depth
            )

            Direction.WEST -> MinePieces.generateAndAddPiece(
                startPiece, builder, random,
                boundingBox.minX() - 1, boundingBox.minY(), boundingBox.minZ(),
                Direction.WEST, depth
            )

            Direction.EAST -> MinePieces.generateAndAddPiece(
                startPiece, builder, random,
                boundingBox.maxX() + 1, boundingBox.minY(), boundingBox.minZ(),
                Direction.EAST, depth
            )

            else -> MinePieces.generateAndAddPiece(
                startPiece, builder, random,
                boundingBox.minX(), boundingBox.minY(), boundingBox.minZ() - 1,
                Direction.NORTH, depth
            )
        }
    }

    override fun postProcess(
        level: WorldGenLevel, structureManager: StructureManager, generator: ChunkGenerator, random: RandomSource,
        chunkBB: BoundingBox, chunkPos: ChunkPos, referencePos: BlockPos,
    ) {
        if (isInInvalidLocation(level, chunkBB)) {
            return
        }

        generateBox(level, chunkBB, 0, 5, 0, 2, 7, 1, CAVE_AIR, CAVE_AIR, false)
        generateBox(level, chunkBB, 0, 0, 7, 2, 2, 8, CAVE_AIR, CAVE_AIR, false)

        for (i in 0..4) {
            generateBox(
                level, chunkBB,
                0,
                5 - i - (if (i < 4) 1 else 0),
                2 + i,
                2,
                7 - i,
                2 + i,
                CAVE_AIR,
                CAVE_AIR,
                false
            )
        }
    }

    companion object {

        fun findStairs(
            builder: StructurePieceAccessor, footX: Int, footY: Int, footZ: Int, dir: Direction,
        ): BoundingBox? {
            val box = when (dir) {
                Direction.SOUTH -> BoundingBox(0, -5, 0, 2, 2, 8)
                Direction.WEST -> BoundingBox(-8, -5, 0, 0, 2, 2)
                Direction.EAST -> BoundingBox(0, -5, 0, 8, 2, 2)
                else -> {
                    BoundingBox(0, -5, -8, 2, 2, 0)
                    BoundingBox(0, -5, 0, 2, 2, 8)
                    BoundingBox(-8, -5, 0, 0, 2, 2)
                    BoundingBox(0, -5, 0, 8, 2, 2)
                }
            }
            box.move(footX, footY, footZ)
            return if (builder.findCollisionPiece(box) != null) null else box
        }

    }
}