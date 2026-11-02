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
import org.teamvoided.dusks_and_dungeons.util.read
import org.teamvoided.dusks_and_dungeons.util.store
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MinePieces.TAG_ENTRANCES
import kotlin.jvm.optionals.getOrNull
import kotlin.math.min

class MineRoom : MinePiece {

    private val childEntranceBoxes = mutableListOf<BoundingBox>()

    constructor(genDepth: Int, random: RandomSource, west: Int, north: Int, type: MineSettings) : super(
        DnDStructurePiceTypes.MINE_ROOM, genDepth, type, BoundingBox(
            west,
            MinePieces.MAGIC_START_Y, //TODO figure out if this is the right place for this const
            north,
            west + 7 + random.nextInt(6),
            54 + random.nextInt(6),
            north + 7 + random.nextInt(6)
        )
    ) {
        this.type = type
    }

    constructor(tag: CompoundTag) : super(DnDStructurePiceTypes.MINE_ROOM, tag) {
        childEntranceBoxes.addAll(tag.read(TAG_ENTRANCES, BoundingBox.CODEC.listOf()).getOrNull() ?: listOf())
    }

    override fun addAdditionalSaveData(ctx: StructurePieceSerializationContext, tag: CompoundTag) {
        super.addAdditionalSaveData(ctx, tag)
        tag.store(TAG_ENTRANCES, BoundingBox.CODEC.listOf(), childEntranceBoxes)
    }

    override fun addChildren(startPiece: StructurePiece, builder: StructurePieceAccessor, random: RandomSource) {
        val depth = getGenDepth()
        var heightSpace = boundingBox.ySpan - 3 - 1
        if (heightSpace <= 0) {
            heightSpace = 1
        }

        var pos = 0

        while (pos < boundingBox.xSpan) {
            pos += random.nextInt(boundingBox.xSpan)
            if (pos + 3 > boundingBox.xSpan) {
                break
            }

            val child = MinePieces.generateAndAddPiece(
                startPiece,
                builder,
                random,
                boundingBox.minX() + pos,
                boundingBox.minY() + random.nextInt(heightSpace) + 1,
                boundingBox.minZ() - 1,
                Direction.NORTH,
                depth
            )
            if (child != null) {
                val childBox = child.getBoundingBox()
                childEntranceBoxes.add(
                    BoundingBox(
                        childBox.minX(), childBox.minY(), boundingBox.minZ(),
                        childBox.maxX(), childBox.maxY(), boundingBox.minZ() + 1
                    )
                )
            }

            pos += 4
        }

        pos = 0

        while (pos < boundingBox.xSpan) {
            pos += random.nextInt(boundingBox.xSpan)
            if (pos + 3 > boundingBox.xSpan) {
                break
            }

            val child = MinePieces.generateAndAddPiece(
                startPiece,
                builder,
                random,
                boundingBox.minX() + pos,
                boundingBox.minY() + random.nextInt(heightSpace) + 1,
                boundingBox.maxZ() + 1,
                Direction.SOUTH,
                depth
            )
            if (child != null) {
                val childBox = child.getBoundingBox()
                childEntranceBoxes.add(
                    BoundingBox(
                        childBox.minX(), childBox.minY(), boundingBox.maxZ() - 1,
                        childBox.maxX(), childBox.maxY(), boundingBox.maxZ()
                    )
                )
            }

            pos += 4
        }

        pos = 0

        while (pos < boundingBox.zSpan) {
            pos += random.nextInt(boundingBox.zSpan)
            if (pos + 3 > boundingBox.zSpan) {
                break
            }

            val child = MinePieces.generateAndAddPiece(
                startPiece,
                builder,
                random,
                boundingBox.minX() - 1,
                boundingBox.minY() + random.nextInt(heightSpace) + 1,
                boundingBox.minZ() + pos,
                Direction.WEST,
                depth
            )
            if (child != null) {
                val childBox = child.getBoundingBox()
                childEntranceBoxes.add(
                    BoundingBox(
                        boundingBox.minX(), childBox.minY(), childBox.minZ(),
                        boundingBox.minX() + 1, childBox.maxY(), childBox.maxZ()
                    )
                )
            }

            pos += 4
        }

        pos = 0

        while (pos < boundingBox.zSpan) {
            pos += random.nextInt(boundingBox.zSpan)
            if (pos + 3 > boundingBox.zSpan) {
                break
            }

            val child = MinePieces.generateAndAddPiece(
                startPiece,
                builder,
                random,
                boundingBox.maxX() + 1,
                boundingBox.minY() + random.nextInt(heightSpace) + 1,
                boundingBox.minZ() + pos,
                Direction.EAST,
                depth
            )
            if (child != null) {
                val childBox = child.getBoundingBox()
                childEntranceBoxes.add(
                    BoundingBox(
                        boundingBox.maxX() - 1, childBox.minY(), childBox.minZ(),
                        boundingBox.maxX(), childBox.maxY(), childBox.maxZ()
                    )
                )
            }

            pos += 4
        }
    }

    override fun postProcess(
        level: WorldGenLevel,
        structureManager: StructureManager,
        generator: ChunkGenerator,
        random: RandomSource,
        chunkBB: BoundingBox,
        chunkPos: ChunkPos,
        referencePos: BlockPos,
    ) {
        if (!isInInvalidLocation(level, chunkBB)) {
            generateBox(
                level,
                chunkBB,
                boundingBox.minX(),
                boundingBox.minY() + 1,
                boundingBox.minZ(),
                boundingBox.maxX(),
                min(boundingBox.minY() + 3, boundingBox.maxY()),
                boundingBox.maxZ(),
                CAVE_AIR,
                CAVE_AIR,
                false
            )

            for (entranceBox in childEntranceBoxes) {
                generateBox(
                    level,
                    chunkBB,
                    entranceBox.minX(), entranceBox.maxY() - 2, entranceBox.minZ(),
                    entranceBox.maxX(), entranceBox.maxY(), entranceBox.maxZ(),
                    CAVE_AIR,
                    CAVE_AIR,
                    false
                )
            }

            generateUpperHalfSphere(
                level,
                chunkBB,
                boundingBox.minX(), boundingBox.minY() + 4, boundingBox.minZ(),
                boundingBox.maxX(), boundingBox.maxY(), boundingBox.maxZ(),
                CAVE_AIR,
                false
            )
        }
    }

    override fun move(dx: Int, dy: Int, dz: Int) {
        super.move(dx, dy, dz)

        for (bb in childEntranceBoxes) {
            @Suppress("DEPRECATION")
            bb.move(dx, dy, dz)
        }
    }

}