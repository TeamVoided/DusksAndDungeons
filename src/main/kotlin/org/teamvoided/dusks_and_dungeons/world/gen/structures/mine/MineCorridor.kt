package org.teamvoided.dusks_and_dungeons.world.gen.structures.mine

import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceKey
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.vehicle.MinecartChest
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.StructureManager
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.SpawnerBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.RailShape
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.structure.BoundingBox
import net.minecraft.world.level.levelgen.structure.StructurePiece
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootTable
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDStructurePiceTypes
import org.teamvoided.dusks_and_dungeons.util.getBooleanOr
import org.teamvoided.dusks_and_dungeons.util.getIntOr
import org.teamvoided.dusks_and_dungeons.util.getMaxY
import org.teamvoided.dusks_and_dungeons.util.getMinY
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MinePieces.TAG_HAS_PLACED_SPIDER
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MinePieces.TAG_HAS_RAILS
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MinePieces.TAG_HAS_SPIDERS
import org.teamvoided.dusks_and_dungeons.world.gen.structures.mine.MinePieces.TAG_NUM

@Suppress("SameParameterValue")
class MineCorridor : MinePiece {

    private val hasRails: Boolean
    private val spiderCorridor: Boolean
    private var hasPlacedSpider = false
    private val numSections: Int

    constructor(
        genDepth: Int, random: RandomSource, boundingBox: BoundingBox, direction: Direction, type: MineSettings,
    ) : super(DnDStructurePiceTypes.MINE_CORRIDOR, genDepth, type, boundingBox) {
        setOrientation(direction)
        hasRails = random.nextInt(3) == 0
        spiderCorridor = !hasRails && random.nextInt(23) == 0
        numSections = if (orientation!!.axis === Direction.Axis.Z)
            boundingBox.zSpan / MinePieces.DEFAULT_SHAFT_LENGTH
        else
            boundingBox.xSpan / MinePieces.DEFAULT_SHAFT_LENGTH
    }

    constructor(tag: CompoundTag) : super(DnDStructurePiceTypes.MINE_CORRIDOR, tag) {
        hasRails = tag.getBooleanOr(TAG_HAS_RAILS, false)
        spiderCorridor = tag.getBooleanOr(TAG_HAS_SPIDERS, false)
        hasPlacedSpider = tag.getBooleanOr(TAG_HAS_PLACED_SPIDER, false)
        numSections = tag.getIntOr(TAG_NUM, 0)
    }

    override fun addAdditionalSaveData(ctx: StructurePieceSerializationContext, tag: CompoundTag) {
        super.addAdditionalSaveData(ctx, tag)
        tag.putBoolean(TAG_HAS_RAILS, hasRails)
        tag.putBoolean(TAG_HAS_SPIDERS, spiderCorridor)
        tag.putBoolean(TAG_HAS_PLACED_SPIDER, hasPlacedSpider)
        tag.putInt(TAG_NUM, numSections)
    }

    override fun addChildren(startPiece: StructurePiece, builder: StructurePieceAccessor, random: RandomSource) {
        val depth = getGenDepth()
        val endSelection = random.nextInt(4)
        val orientation = orientation
        if (orientation != null) {
            when (orientation) {
                Direction.NORTH -> if (endSelection <= 1) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.minX(),
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ() - 1,
                        orientation, depth
                    )
                } else if (endSelection == 2) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.minX() - 1,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ(),
                        Direction.WEST, depth
                    )
                } else {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.maxX() + 1,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ(),
                        Direction.EAST, depth
                    )
                }

                Direction.SOUTH -> if (endSelection <= 1) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.minX(),
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.maxZ() + 1,
                        orientation, depth
                    )
                } else if (endSelection == 2) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.minX() - 1,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.maxZ() - 3,
                        Direction.WEST, depth
                    )
                } else {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.maxX() + 1,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.maxZ() - 3,
                        Direction.EAST, depth
                    )
                }

                Direction.WEST -> if (endSelection <= 1) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.minX() - 1,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ(),
                        orientation, depth
                    )
                } else if (endSelection == 2) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.minX(),
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ() - 1,
                        Direction.NORTH, depth
                    )
                } else {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.minX(),
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.maxZ() + 1,
                        Direction.SOUTH, depth
                    )
                }

                Direction.EAST -> if (endSelection <= 1) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.maxX() + 1,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ(),
                        orientation, depth
                    )
                } else if (endSelection == 2) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.maxX() - 3,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ() - 1,
                        Direction.NORTH, depth
                    )
                } else {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.maxX() - 3,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.maxZ() + 1,
                        Direction.SOUTH, depth
                    )
                }

                else -> if (endSelection <= 1) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.minX(),
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ() - 1,
                        orientation, depth
                    )
                } else if (endSelection == 2) {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.minX() - 1,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ(),
                        Direction.WEST, depth
                    )
                } else {
                    MinePieces.generateAndAddPiece(
                        startPiece, builder, random,
                        boundingBox.maxX() + 1,
                        boundingBox.minY() - 1 + random.nextInt(3),
                        boundingBox.minZ(),
                        Direction.EAST, depth
                    )
                }
            }
        }

        if (depth < MinePieces.MAX_DEPTH) {
            if (orientation != Direction.NORTH && orientation != Direction.SOUTH) {
                var x = boundingBox.minX() + 3
                while (x + 3 <= boundingBox.maxX()) {
                    val selection = random.nextInt(5)
                    if (selection == 0) {
                        MinePieces.generateAndAddPiece(
                            startPiece, builder, random,
                            x, boundingBox.minY(), boundingBox.minZ() - 1,
                            Direction.NORTH, depth + 1
                        )
                    } else if (selection == 1) {
                        MinePieces.generateAndAddPiece(
                            startPiece, builder, random,
                            x, boundingBox.minY(), boundingBox.maxZ() + 1,
                            Direction.SOUTH, depth + 1
                        )
                    }
                    x += 5
                }
            } else {
                var z = boundingBox.minZ() + 3
                while (z + 3 <= boundingBox.maxZ()) {
                    val selection = random.nextInt(5)
                    if (selection == 0) {
                        MinePieces.generateAndAddPiece(
                            startPiece, builder, random,
                            boundingBox.minX() - 1, boundingBox.minY(), z,
                            Direction.WEST, depth + 1
                        )
                    } else if (selection == 1) {
                        MinePieces.generateAndAddPiece(
                            startPiece, builder, random,
                            boundingBox.maxX() + 1, boundingBox.minY(), z,
                            Direction.EAST, depth + 1
                        )
                    }
                    z += 5
                }
            }
        }
    }

    override fun createChest(
        level: WorldGenLevel, chunkBB: BoundingBox, random: RandomSource, x: Int, y: Int, z: Int,
        lootTable: ResourceKey<LootTable>,
    ): Boolean {
        val pos = getWorldPos(x, y, z)
        if (chunkBB.isInside(pos) && level.getBlockState(pos).isAir && !level.getBlockState(pos.below()).isAir) {
            val state = Blocks.RAIL.defaultBlockState()
                .setValue(RailBlock.SHAPE, if (random.nextBoolean()) RailShape.NORTH_SOUTH else RailShape.EAST_WEST)
            placeBlock(level, state, x, y, z, chunkBB)
            val minecartChest = MinecartChest(level.level, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5)
            minecartChest.setLootTable(lootTable, random.nextLong())
            level.addFreshEntity(minecartChest)

            return true
        }
        return false
    }

    override fun postProcess(
        level: WorldGenLevel, structureManager: StructureManager, generator: ChunkGenerator, random: RandomSource,
        chunkBB: BoundingBox, chunkPos: ChunkPos, referencePos: BlockPos,
    ) {
        if (!isInInvalidLocation(level, chunkBB)) {
            val x0 = 0
            val x1 = 2
            val y0 = 0
            val y1 = 2
            val length = numSections * MinePieces.DEFAULT_SHAFT_LENGTH - 1
            val planks: BlockState = type.planksState
            generateBox(level, chunkBB, 0, 0, 0, 2, 1, length, CAVE_AIR, CAVE_AIR, false)
            generateMaybeBox(level, chunkBB, random, 0.8f, 0, 2, 0, 2, 2, length, CAVE_AIR, CAVE_AIR, false, false)
            if (spiderCorridor) {
                generateMaybeBox(
                    level, chunkBB, random, 0.6f, 0, 0, 0, 2, 1, length,
                    Blocks.COBWEB.defaultBlockState(), CAVE_AIR, false, true
                )
            }

            for (section in 0..<numSections) {
                val z = 2 + section * MinePieces.DEFAULT_SHAFT_LENGTH
                placeSupport(level, chunkBB, 0, 0, z, 2, 2, random)
                maybePlaceCobWeb(level, chunkBB, random, 0.1f, 0, 2, z - 1)
                maybePlaceCobWeb(level, chunkBB, random, 0.1f, 2, 2, z - 1)
                maybePlaceCobWeb(level, chunkBB, random, 0.1f, 0, 2, z + 1)
                maybePlaceCobWeb(level, chunkBB, random, 0.1f, 2, 2, z + 1)
                maybePlaceCobWeb(level, chunkBB, random, 0.05f, 0, 2, z - 2)
                maybePlaceCobWeb(level, chunkBB, random, 0.05f, 2, 2, z - 2)
                maybePlaceCobWeb(level, chunkBB, random, 0.05f, 0, 2, z + 2)
                maybePlaceCobWeb(level, chunkBB, random, 0.05f, 2, 2, z + 2)
                if (random.nextInt(100) == 0) {
                    createChest(level, chunkBB, random, 2, 0, z - 1, BuiltInLootTables.ABANDONED_MINESHAFT)
                }

                if (random.nextInt(100) == 0) {
                    createChest(level, chunkBB, random, 0, 0, z + 1, BuiltInLootTables.ABANDONED_MINESHAFT)
                }

                if (spiderCorridor && !hasPlacedSpider) {
                    val newX = 1
                    val newZ = z - 1 + random.nextInt(3)
                    val pos = getWorldPos(newX, 0, newZ)
                    if (chunkBB.isInside(pos) && isInterior(level, newX, 0, newZ, chunkBB)) {
                        hasPlacedSpider = true
                        level.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2)
                        val spawner = level.getBlockEntity(pos)
                        if (spawner is SpawnerBlockEntity) {
                            spawner.setEntityId(EntityType.CAVE_SPIDER, random)
                        }
                    }
                }
            }

            for (x in 0..2) {
                for (zx in 0..length) {
                    setPlanksBlock(level, chunkBB, planks, x, -1, zx)
                }
            }

            val supportPillarIndent = 2
            placeDoubleLowerOrUpperSupport(level, chunkBB, 0, -1, 2)
            if (numSections > 1) {
                val lastSupportPillar = length - 2
                placeDoubleLowerOrUpperSupport(level, chunkBB, 0, -1, lastSupportPillar)
            }

            if (hasRails) {
                val state = Blocks.RAIL.defaultBlockState().setValue(RailBlock.SHAPE, RailShape.NORTH_SOUTH)

                for (zx in 0..length) {
                    val floor = getBlock(level, 1, -1, zx, chunkBB)
                    if (!floor.isAir && floor.isSolidRender(level, getWorldPos(1, -1, zx))) {
                        val probability = if (isInterior(level, 1, 0, zx, chunkBB)) 0.7f else 0.9f
                        maybeGenerateBlock(level, chunkBB, random, probability, 1, 0, zx, state)
                    }
                }
            }
        }
    }

    fun placeDoubleLowerOrUpperSupport(level: WorldGenLevel, chunkBB: BoundingBox, x: Int, y: Int, z: Int) {
        val woodBlock = type.woodState
        val plankBlock = type.planksState
        if (getBlock(level, x, y, z, chunkBB).`is`(plankBlock.block)) {
            fillPillarDownOrChainUp(level, woodBlock, x, y, z, chunkBB)
        }

        if (getBlock(level, x + 2, y, z, chunkBB).`is`(plankBlock.block)) {
            fillPillarDownOrChainUp(level, woodBlock, x + 2, y, z, chunkBB)
        }
    }

    override fun fillColumnDown(
        level: WorldGenLevel, columnState: BlockState, x: Int, startY: Int, z: Int, chunkBB: BoundingBox,
    ) {
        val pos = getWorldPos(x, startY, z)
        if (chunkBB.isInside(pos)) {
            val worldY = pos.y

            while (isReplaceableByStructures(level.getBlockState(pos)) && pos.y > level.getMinY() + 1) {
                pos.move(Direction.DOWN)
            }

            if (canPlaceColumnOnTopOf(level, pos, level.getBlockState(pos))) {
                while (pos.y < worldY) {
                    pos.move(Direction.UP)
                    level.setBlock(pos, columnState, 2)
                }
            }
        }
    }

    fun fillPillarDownOrChainUp(
        level: WorldGenLevel, pillarState: BlockState, x: Int, y: Int, z: Int, chunkBB: BoundingBox,
    ) {
        val pos = getWorldPos(x, y, z)
        if (chunkBB.isInside(pos)) {
            val worldY = pos.y
            var distanceFromWorldY = 1
            var checkBelow = true

            var checkAbove = true
            while (checkBelow || checkAbove) {
                if (checkBelow) {
                    pos.setY(worldY - distanceFromWorldY)
                    val belowState = level.getBlockState(pos)
                    val emptyBelow = isReplaceableByStructures(belowState) && !belowState.`is`(Blocks.LAVA)
                    if (!emptyBelow && canPlaceColumnOnTopOf(level, pos, belowState)) {
                        fillColumnBetween(level, pillarState, pos, worldY - distanceFromWorldY + 1, worldY)
                        return
                    }

                    checkBelow =
                        distanceFromWorldY <= MinePieces.MAX_PILLAR_HEIGHT && emptyBelow && pos.y > level.getMinY() + 1
                }

                if (checkAbove) {
                    pos.setY(worldY + distanceFromWorldY)
                    val aboveState = level.getBlockState(pos)
                    val emptyAbove = isReplaceableByStructures(aboveState)
                    if (!emptyAbove && canHangChainBelow(level, pos, aboveState)) {
                        level.setBlock(pos.setY(worldY + 1), type.fenceState, 2)
                        fillColumnBetween(level, type.chainState, pos, worldY + 2, worldY + distanceFromWorldY)
                        return
                    }

                    checkAbove =
                        distanceFromWorldY <= MinePieces.MAX_CHAIN_HEIGHT && emptyAbove && pos.y < level.getMaxY()
                }
                distanceFromWorldY++
            }
        }
    }

    fun canPlaceColumnOnTopOf(level: LevelReader, posBelow: BlockPos, stateBelow: BlockState): Boolean {
        return stateBelow.isFaceSturdy(level, posBelow, Direction.UP)
    }

    fun canHangChainBelow(level: LevelReader, posAbove: BlockPos, stateAbove: BlockState): Boolean {
        return Block.canSupportCenter(level, posAbove, Direction.DOWN) && stateAbove.block !is FallingBlock
    }

    fun placeSupport(
        level: WorldGenLevel, chunkBB: BoundingBox, x0: Int, y0: Int, z: Int, y1: Int, x1: Int, random: RandomSource,
    ) {
        if (isSupportingBox(level, chunkBB, x0, x1, y1, z)) {
            val planksBlock: BlockState = type.planksState
            val fenceBlock: BlockState = type.fenceState
            generateBox(
                level, chunkBB, x0, y0, z, x0, y1 - 1, z, fenceBlock.setValue(FenceBlock.WEST, true), CAVE_AIR, false
            )
            generateBox(
                level, chunkBB, x1, y0, z, x1, y1 - 1, z, fenceBlock.setValue(FenceBlock.EAST, true), CAVE_AIR, false
            )
            if (random.nextInt(4) == 0) {
                generateBox(level, chunkBB, x0, y1, z, x0, y1, z, planksBlock, CAVE_AIR, false)
                generateBox(level, chunkBB, x1, y1, z, x1, y1, z, planksBlock, CAVE_AIR, false)
            } else {
                generateBox(level, chunkBB, x0, y1, z, x1, y1, z, planksBlock, CAVE_AIR, false)
                maybeGenerateBlock(
                    level, chunkBB, random, 0.05f, x0 + 1, y1, z - 1,
                    Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.SOUTH)
                )
                maybeGenerateBlock(
                    level, chunkBB, random, 0.05f, x0 + 1, y1, z + 1,
                    Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.NORTH)
                )
            }
        }
    }

    fun maybePlaceCobWeb(
        level: WorldGenLevel, chunkBB: BoundingBox, random: RandomSource, probability: Float, x: Int, y: Int, z: Int,
    ) {
        if (isInterior(level, x, y, z, chunkBB) && random.nextFloat() < probability &&
            hasSturdyNeighbours(level, chunkBB, x, y, z, 2)
        ) {
            placeBlock(level, Blocks.COBWEB.defaultBlockState(), x, y, z, chunkBB)
        }
    }

    fun hasSturdyNeighbours(level: WorldGenLevel, chunkBB: BoundingBox, x: Int, y: Int, z: Int, count: Int): Boolean {
        val worldPos = getWorldPos(x, y, z)
        var sturdyNeighbours = 0

        for (direction in Direction.entries) {
            worldPos.move(direction)
            if (chunkBB.isInside(worldPos) &&
                level.getBlockState(worldPos).isFaceSturdy(level, worldPos, direction.opposite)
            ) {
                if (++sturdyNeighbours >= count) {
                    return true
                }
            }

            worldPos.move(direction.opposite)
        }

        return false
    }

    companion object {

        fun findCorridorSize(
            builder: StructurePieceAccessor, random: RandomSource, footX: Int, footY: Int, footZ: Int, dir: Direction,
        ): BoundingBox? {
            for (corridorLength in random.nextInt(3) + 2 downTo 1) {
                val blockLength = corridorLength * MinePieces.DEFAULT_SHAFT_LENGTH

                val box = when (dir) {
                    Direction.SOUTH -> BoundingBox(0, 0, 0, 2, 2, blockLength - 1)
                    Direction.WEST -> BoundingBox(-(blockLength - 1), 0, 0, 0, 2, 2)
                    Direction.EAST -> BoundingBox(0, 0, 0, blockLength - 1, 2, 2)
                    else -> {
                        BoundingBox(0, 0, -(blockLength - 1), 2, 2, 0)
                        BoundingBox(0, 0, 0, 2, 2, blockLength - 1)
                        BoundingBox(-(blockLength - 1), 0, 0, 0, 2, 2)
                        BoundingBox(0, 0, 0, blockLength - 1, 2, 2)
                    }
                }
                box.move(footX, footY, footZ)
                if (builder.findCollisionPiece(box) == null) {
                    return box
                }
            }

            return null
        }

        fun fillColumnBetween(
            level: WorldGenLevel, pillarState: BlockState,
            pos: MutableBlockPos, bottomInclusive: Int, topExclusive: Int,
        ) {
            for (pillarY in bottomInclusive..<topExclusive) {
                level.setBlock(pos.setY(pillarY), pillarState, 2)
            }
        }

    }
}