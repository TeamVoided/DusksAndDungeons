package org.teamvoided.dusks_and_dungeons.world.gen.structures.mine

import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.levelgen.structure.StructurePiece
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor
import kotlin.math.abs

object MinePieces {

    const val TAG_TYPE = "mines_type"
    const val TAG_DIR = "dir"
    const val TAG_TWO_FLOORED = "two_floored"
    const val TAG_HAS_RAILS = "has_rails"
    const val TAG_HAS_SPIDERS = "has_spiders"
    const val TAG_HAS_PLACED_SPIDER = "has_placed_spider"
    const val TAG_NUM = "num"
    const val TAG_ENTRANCES = "entrances"

    const val DEFAULT_SHAFT_WIDTH = 3
    const val DEFAULT_SHAFT_HEIGHT = 3
    const val DEFAULT_SHAFT_LENGTH = 5
    const val MAX_PILLAR_HEIGHT = 20
    const val MAX_CHAIN_HEIGHT = 50
    const val MAX_DEPTH = 8
    const val MAGIC_START_Y = 50

    fun createRandomShaftPiece(
        builder: StructurePieceAccessor, random: RandomSource,
        footX: Int, footY: Int, footZ: Int, dir: Direction, genDepth: Int,
        type: MineSettings,
    ): MinePiece? {
        val randomSelection = random.nextInt(100)
        if (randomSelection >= 80) {
            val crossingBox = MineCrossing.findCrossing(builder, random, footX, footY, footZ, dir)
            if (crossingBox != null) {
                return MineCrossing(genDepth, crossingBox, dir, type)
            }
        } else if (randomSelection >= 70) {
            val stairsBox = MineStairs.findStairs(builder, footX, footY, footZ, dir)
            if (stairsBox != null) {
                return MineStairs(genDepth, stairsBox, dir, type)
            }
        } else {
            val corridorBox = MineCorridor.findCorridorSize(builder, random, footX, footY, footZ, dir)
            if (corridorBox != null) {
                return MineCorridor(genDepth, random, corridorBox, dir, type)
            }
        }

        return null
    }

    fun generateAndAddPiece(
        startPiece: StructurePiece, builder: StructurePieceAccessor, random: RandomSource,
        footX: Int, footY: Int, footZ: Int, dir: Direction, depth: Int,
    ): MinePiece? {
        if (depth > MAX_DEPTH) {
            return null
        }
        if (abs(footX - startPiece.getBoundingBox().minX()) <= 80 &&
            abs(footZ - startPiece.getBoundingBox().minZ()) <= 80
        ) {
            val type = (startPiece as MinePiece).type
            val newPiece = createRandomShaftPiece(builder, random, footX, footY, footZ, dir, depth + 1, type)
            if (newPiece != null) {
                builder.addPiece(newPiece)
                newPiece.addChildren(startPiece, builder, random)
            }

            return newPiece
        }

        return null
    }

}