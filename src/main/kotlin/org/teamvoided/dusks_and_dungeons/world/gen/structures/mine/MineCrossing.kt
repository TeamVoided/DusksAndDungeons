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
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDStructurePiceTypes
import org.teamvoided.dusks_and_dungeons.util.getBooleanOr
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MinePieces.TAG_DIR
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MinePieces.TAG_TWO_FLOORED

class MineCrossing : MinePiece {

    private val direction: Direction
    private val isTwoFloored: Boolean

    constructor(genDepth: Int, boundingBox: BoundingBox, dir: Direction, type: MineSettings) : super(
        DnDStructurePiceTypes.MINE_CROSSING, genDepth, type, boundingBox
    ) {
        direction = dir
        isTwoFloored = boundingBox.ySpan > 3
    }

    constructor(tag: CompoundTag) : super(DnDStructurePiceTypes.MINE_CROSSING, tag) {
        isTwoFloored = tag.getBooleanOr(TAG_TWO_FLOORED, false)
        direction = Direction.from2DDataValue(tag.getInt(TAG_DIR))
    }

    override fun addAdditionalSaveData(ctx: StructurePieceSerializationContext, tag: CompoundTag) {
        super.addAdditionalSaveData(ctx, tag)
        tag.putBoolean(TAG_TWO_FLOORED, isTwoFloored)
        tag.putInt(TAG_DIR, direction.get2DDataValue())
    }

    override fun addChildren(startPiece: StructurePiece, builder: StructurePieceAccessor, random: RandomSource) {
        val depth = getGenDepth()
        when (direction) {
            Direction.NORTH -> {
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() + 1, boundingBox.minY(), boundingBox.minZ() - 1,
                    Direction.NORTH, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() - 1, boundingBox.minY(), boundingBox.minZ() + 1,
                    Direction.WEST, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.maxX() + 1, boundingBox.minY(), boundingBox.minZ() + 1,
                    Direction.EAST, depth
                )
            }

            Direction.SOUTH -> {
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() + 1, boundingBox.minY(), boundingBox.maxZ() + 1,
                    Direction.SOUTH, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() - 1, boundingBox.minY(), boundingBox.minZ() + 1,
                    Direction.WEST, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.maxX() + 1, boundingBox.minY(), boundingBox.minZ() + 1,
                    Direction.EAST, depth
                )
            }

            Direction.WEST -> {
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() + 1, boundingBox.minY(), boundingBox.minZ() - 1,
                    Direction.NORTH, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() + 1, boundingBox.minY(), boundingBox.maxZ() + 1,
                    Direction.SOUTH, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() - 1, boundingBox.minY(), boundingBox.minZ() + 1,
                    Direction.WEST, depth
                )
            }

            Direction.EAST -> {
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() + 1, boundingBox.minY(), boundingBox.minZ() - 1,
                    Direction.NORTH, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() + 1, boundingBox.minY(), boundingBox.maxZ() + 1,
                    Direction.SOUTH, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.maxX() + 1, boundingBox.minY(), boundingBox.minZ() + 1,
                    Direction.EAST, depth
                )
            }

            else -> {
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() + 1, boundingBox.minY(), boundingBox.minZ() - 1,
                    Direction.NORTH, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() - 1, boundingBox.minY(), boundingBox.minZ() + 1,
                    Direction.WEST, depth
                )
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.maxX() + 1, boundingBox.minY(), boundingBox.minZ() + 1,
                    Direction.EAST, depth
                )
            }
        }

        if (isTwoFloored) {
            if (random.nextBoolean()) {
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() + 1, boundingBox.minY() + 3 + 1, boundingBox.minZ() - 1,
                    Direction.NORTH, depth
                )
            }

            if (random.nextBoolean()) {
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() - 1, boundingBox.minY() + 3 + 1, boundingBox.minZ() + 1,
                    Direction.WEST, depth
                )
            }

            if (random.nextBoolean()) {
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.maxX() + 1, boundingBox.minY() + 3 + 1, boundingBox.minZ() + 1,
                    Direction.EAST, depth
                )
            }

            if (random.nextBoolean()) {
                MinePieces.generateAndAddPiece(
                    startPiece, builder, random,
                    boundingBox.minX() + 1, boundingBox.minY() + 3 + 1, boundingBox.maxZ() + 1,
                    Direction.SOUTH, depth
                )
            }
        }
    }

    override fun postProcess(
        level: WorldGenLevel, structureManager: StructureManager, generator: ChunkGenerator, random: RandomSource,
        chunkBB: BoundingBox, chunkPos: ChunkPos, referencePos: BlockPos,
    ) {
        if (isInInvalidLocation(level, chunkBB)) {
            return
        }
        val planks = type.planksState
        if (isTwoFloored) {
            generateBox(
                level, chunkBB,
                boundingBox.minX() + 1, boundingBox.minY(), boundingBox.minZ(),
                boundingBox.maxX() - 1, boundingBox.minY() + 3 - 1, boundingBox.maxZ(),
                CAVE_AIR, CAVE_AIR, false
            )
            generateBox(
                level, chunkBB,
                boundingBox.minX(), boundingBox.minY(), boundingBox.minZ() + 1,
                boundingBox.maxX(), boundingBox.minY() + 3 - 1, boundingBox.maxZ() - 1,
                CAVE_AIR, CAVE_AIR, false
            )
            generateBox(
                level, chunkBB,
                boundingBox.minX() + 1, boundingBox.maxY() - 2, boundingBox.minZ(),
                boundingBox.maxX() - 1, boundingBox.maxY(), boundingBox.maxZ(),
                CAVE_AIR, CAVE_AIR, false
            )
            generateBox(
                level, chunkBB,
                boundingBox.minX(), boundingBox.maxY() - 2, boundingBox.minZ() + 1,
                boundingBox.maxX(), boundingBox.maxY(), boundingBox.maxZ() - 1,
                CAVE_AIR, CAVE_AIR, false
            )
            generateBox(
                level, chunkBB,
                boundingBox.minX() + 1, boundingBox.minY() + 3, boundingBox.minZ() + 1,
                boundingBox.maxX() - 1, boundingBox.minY() + 3, boundingBox.maxZ() - 1,
                CAVE_AIR, CAVE_AIR, false
            )
        } else {
            generateBox(
                level, chunkBB,
                boundingBox.minX() + 1, boundingBox.minY(), boundingBox.minZ(),
                boundingBox.maxX() - 1, boundingBox.maxY(), boundingBox.maxZ(),
                CAVE_AIR, CAVE_AIR, false
            )
            generateBox(
                level, chunkBB,
                boundingBox.minX(), boundingBox.minY(), boundingBox.minZ() + 1,
                boundingBox.maxX(), boundingBox.maxY(), boundingBox.maxZ() - 1,
                CAVE_AIR, CAVE_AIR, false
            )
        }

        placeSupportPillar(
            level, chunkBB,
            boundingBox.minX() + 1, boundingBox.minY(), boundingBox.minZ() + 1,
            boundingBox.maxY()
        )
        placeSupportPillar(
            level, chunkBB,
            boundingBox.minX() + 1, boundingBox.minY(), boundingBox.maxZ() - 1,
            boundingBox.maxY()
        )
        placeSupportPillar(
            level, chunkBB,
            boundingBox.maxX() - 1, boundingBox.minY(), boundingBox.minZ() + 1,
            boundingBox.maxY()
        )
        placeSupportPillar(
            level, chunkBB,
            boundingBox.maxX() - 1, boundingBox.minY(), boundingBox.maxZ() - 1,
            boundingBox.maxY()
        )
        val y = boundingBox.minY() - 1

        for (x in boundingBox.minX()..boundingBox.maxX()) {
            for (z in boundingBox.minZ()..boundingBox.maxZ()) {
                setPlanksBlock(level, chunkBB, planks, x, y, z)
            }
        }
    }

    fun placeSupportPillar(level: WorldGenLevel, chunkBB: BoundingBox, x: Int, y0: Int, z: Int, y1: Int) {
        if (getBlock(level, x, y1 + 1, z, chunkBB).isAir) {
            return
        }
        generateBox(level, chunkBB, x, y0, z, x, y1, z, type.planksState, CAVE_AIR, false)
    }

    companion object {

        fun findCrossing(
            builder: StructurePieceAccessor, random: RandomSource, footX: Int, footY: Int, footZ: Int, dir: Direction,
        ): BoundingBox? {
            val y1 = if (random.nextInt(4) == 0) 6 else 2

            val box = when (dir) {
                Direction.SOUTH -> BoundingBox(-1, 0, 0, 3, y1, 4)
                Direction.WEST -> BoundingBox(-4, 0, -1, 0, y1, 3)
                Direction.EAST -> BoundingBox(0, 0, -1, 4, y1, 3)
                else -> {
                    BoundingBox(-1, 0, -4, 3, y1, 0)
                    BoundingBox(-1, 0, 0, 3, y1, 4)
                    BoundingBox(-4, 0, -1, 0, y1, 3)
                    BoundingBox(0, 0, -1, 4, y1, 3)
                }
            }
            box.move(footX, footY, footZ)
            return if (builder.findCollisionPiece(box) != null) null else box
        }

    }
}