package org.teamvoided.dusks_and_dungeons.world.gen.structures

import com.google.common.collect.Lists
import com.mojang.logging.LogUtils
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.nbt.Tag
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.vehicle.MinecartChest
import net.minecraft.world.level.*
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.SpawnerBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.RailShape
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.structure.BoundingBox
import net.minecraft.world.level.levelgen.structure.StructurePiece
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootTable
import org.slf4j.Logger
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDStructurePiceTypes
import org.teamvoided.dusks_and_dungeons.world.gen.structures.DnDMineshaftStructure.Type.Companion.byId
import java.util.function.Consumer
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object DnDMineshaftPieces {

    val LOGGER: Logger = LogUtils.getLogger()
    private const val DEFAULT_SHAFT_WIDTH = 3
    private const val DEFAULT_SHAFT_HEIGHT = 3
    private const val DEFAULT_SHAFT_LENGTH = 5
    private const val MAX_PILLAR_HEIGHT = 20
    private const val MAX_CHAIN_HEIGHT = 50
    private const val MAX_DEPTH = 8
    const val MAGIC_START_Y: Int = 50

    private fun createRandomShaftPiece(
        structurePieceAccessor: StructurePieceAccessor,
        randomSource: RandomSource,
        i: Int,
        j: Int,
        k: Int,
        direction: Direction,
        l: Int,
        type: DnDMineshaftStructure.Type
    ): MineShaftPiece? {
        val m = randomSource.nextInt(10)
        if (m >= 8) {
            val boundingBox: BoundingBox? =
                MineShaftCrossing.findCrossing(structurePieceAccessor, randomSource, i, j, k, direction)
            if (boundingBox != null) {
                return MineShaftCrossing(l, boundingBox, direction, type)
            }
        } else if (m >= 7) {
            val boundingBox: BoundingBox? =
                MineShaftStairs.findStairs(structurePieceAccessor, randomSource, i, j, k, direction)
            if (boundingBox != null) {
                return MineShaftStairs(l, boundingBox, direction, type)
            }
        } else {
            val boundingBox: BoundingBox? =
                MineShaftCorridor.findCorridorSize(structurePieceAccessor, randomSource, i, j, k, direction)
            if (boundingBox != null) {
                return MineShaftCorridor(l, randomSource, boundingBox, direction, type)
            }
        }

        return null
    }

    fun generateAndAddPiece(
        structurePiece: StructurePiece,
        structurePieceAccessor: StructurePieceAccessor,
        randomSource: RandomSource,
        i: Int,
        j: Int,
        k: Int,
        direction: Direction,
        l: Int
    ): MineShaftPiece? {
        if (l > 8) {
            return null
        }

        if (abs(i - structurePiece.getBoundingBox().minX()) <= 80 && abs(
                k - structurePiece.getBoundingBox().minZ()
            ) <= 80
        ) {
            val type = (structurePiece as MineShaftPiece).type
            val mineShaftPiece =
                createRandomShaftPiece(structurePieceAccessor, randomSource, i, j, k, direction, l + 1, type)
            if (mineShaftPiece != null) {
                structurePieceAccessor.addPiece(mineShaftPiece)
                mineShaftPiece.addChildren(structurePiece, structurePieceAccessor, randomSource)
            }

            return mineShaftPiece
        } else {
            return null
        }
    }

    class MineShaftCorridor : MineShaftPiece {
        private val hasRails: Boolean
        private val spiderCorridor: Boolean
        private var hasPlacedSpider = false
        private val numSections: Int

        constructor(compoundTag: CompoundTag) : super(DnDStructurePiceTypes.MINESHAFT_CORRIDOR, compoundTag) {
            this.hasRails = compoundTag.getBoolean("hr")
            this.spiderCorridor = compoundTag.getBoolean("sc")
            this.hasPlacedSpider = compoundTag.getBoolean("hps")
            this.numSections = compoundTag.getInt("Num")
        }

        protected override fun addAdditionalSaveData(
            structurePieceSerializationContext: StructurePieceSerializationContext,
            compoundTag: CompoundTag
        ) {
            super.addAdditionalSaveData(structurePieceSerializationContext, compoundTag)
            compoundTag.putBoolean("hr", this.hasRails)
            compoundTag.putBoolean("sc", this.spiderCorridor)
            compoundTag.putBoolean("hps", this.hasPlacedSpider)
            compoundTag.putInt("Num", this.numSections)
        }

        constructor(
            i: Int,
            randomSource: RandomSource,
            boundingBox: BoundingBox,
            direction: Direction,
            type: DnDMineshaftStructure.Type
        ) : super(DnDStructurePiceTypes.MINESHAFT_CORRIDOR, i, type, boundingBox) {
            this.setOrientation(direction)
            this.hasRails = randomSource.nextInt(3) == 0
            this.spiderCorridor = !this.hasRails && randomSource.nextInt(23) == 0
            if (this.orientation!!.axis == Direction.Axis.Z) {
                this.numSections = boundingBox.zSpan / 5
            } else {
                this.numSections = boundingBox.xSpan / 5
            }
        }

        override fun addChildren(
            structurePiece: StructurePiece,
            structurePieceAccessor: StructurePieceAccessor,
            randomSource: RandomSource
        ) {
            val i = this.getGenDepth()
            val j = randomSource.nextInt(4)
            val direction = this.orientation
            if (direction != null) {
                when (direction) {
                    Direction.NORTH -> if (j <= 1) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.minX(),
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ() - 1,
                            direction,
                            i
                        )
                    } else if (j == 2) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.minX() - 1,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ(),
                            Direction.WEST,
                            i
                        )
                    } else {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.maxX() + 1,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ(),
                            Direction.EAST,
                            i
                        )
                    }

                    Direction.SOUTH -> if (j <= 1) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.minX(),
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.maxZ() + 1,
                            direction,
                            i
                        )
                    } else if (j == 2) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.minX() - 1,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.maxZ() - 3,
                            Direction.WEST,
                            i
                        )
                    } else {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.maxX() + 1,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.maxZ() - 3,
                            Direction.EAST,
                            i
                        )
                    }

                    Direction.WEST -> if (j <= 1) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.minX() - 1,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ(),
                            direction,
                            i
                        )
                    } else if (j == 2) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.minX(),
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ() - 1,
                            Direction.NORTH,
                            i
                        )
                    } else {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.minX(),
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.maxZ() + 1,
                            Direction.SOUTH,
                            i
                        )
                    }

                    Direction.EAST -> if (j <= 1) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.maxX() + 1,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ(),
                            direction,
                            i
                        )
                    } else if (j == 2) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.maxX() - 3,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ() - 1,
                            Direction.NORTH,
                            i
                        )
                    } else {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.maxX() - 3,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.maxZ() + 1,
                            Direction.SOUTH,
                            i
                        )
                    }

                    else -> if (j <= 1) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.minX(),
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ() - 1,
                            direction,
                            i
                        )
                    } else if (j == 2) {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.minX() - 1,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ(),
                            Direction.WEST,
                            i
                        )
                    } else {
                        generateAndAddPiece(
                            structurePiece,
                            structurePieceAccessor,
                            randomSource,
                            this.boundingBox.maxX() + 1,
                            this.boundingBox.minY() - 1 + randomSource.nextInt(3),
                            this.boundingBox.minZ(),
                            Direction.EAST,
                            i
                        )
                    }
                }
            }

            if (i < 8) {
                if (direction != Direction.NORTH && direction != Direction.SOUTH) {
                    var k = this.boundingBox.minX() + 3
                    while (k + 3 <= this.boundingBox.maxX()) {
                        val l = randomSource.nextInt(5)
                        if (l == 0) {
                            generateAndAddPiece(
                                structurePiece,
                                structurePieceAccessor,
                                randomSource,
                                k,
                                this.boundingBox.minY(),
                                this.boundingBox.minZ() - 1,
                                Direction.NORTH,
                                i + 1
                            )
                        } else if (l == 1) {
                            generateAndAddPiece(
                                structurePiece,
                                structurePieceAccessor,
                                randomSource,
                                k,
                                this.boundingBox.minY(),
                                this.boundingBox.maxZ() + 1,
                                Direction.SOUTH,
                                i + 1
                            )
                        }
                        k += 5
                    }
                } else {
                    var k = this.boundingBox.minZ() + 3
                    while (k + 3 <= this.boundingBox.maxZ()) {
                        val l = randomSource.nextInt(5)
                        if (l == 0) {
                            generateAndAddPiece(
                                structurePiece,
                                structurePieceAccessor,
                                randomSource,
                                this.boundingBox.minX() - 1,
                                this.boundingBox.minY(),
                                k,
                                Direction.WEST,
                                i + 1
                            )
                        } else if (l == 1) {
                            generateAndAddPiece(
                                structurePiece,
                                structurePieceAccessor,
                                randomSource,
                                this.boundingBox.maxX() + 1,
                                this.boundingBox.minY(),
                                k,
                                Direction.EAST,
                                i + 1
                            )
                        }
                        k += 5
                    }
                }
            }
        }

        override fun createChest(
            worldGenLevel: WorldGenLevel,
            boundingBox: BoundingBox,
            randomSource: RandomSource,
            i: Int,
            j: Int,
            k: Int,
            resourceKey: ResourceKey<LootTable>
        ): Boolean {
            val blockPos: BlockPos = this.getWorldPos(i, j, k)
            if (boundingBox.isInside(blockPos) && worldGenLevel.getBlockState(blockPos)
                    .isAir && !worldGenLevel.getBlockState(blockPos.below()).isAir
            ) {
                val blockState = Blocks.RAIL.defaultBlockState().setValue(
                    RailBlock.SHAPE,
                    if (randomSource.nextBoolean()) RailShape.NORTH_SOUTH else RailShape.EAST_WEST
                )
                this.placeBlock(worldGenLevel, blockState, i, j, k, boundingBox)
                val minecartChest = MinecartChest(
                    worldGenLevel.level,
                    blockPos.x + 0.5,
                    blockPos.y + 0.5,
                    blockPos.z + 0.5
                )
                minecartChest.setLootTable(resourceKey, randomSource.nextLong())
                worldGenLevel.addFreshEntity(minecartChest)
                return true
            } else {
                return false
            }
        }

        override fun postProcess(
            worldGenLevel: WorldGenLevel,
            structureManager: StructureManager,
            chunkGenerator: ChunkGenerator,
            randomSource: RandomSource,
            boundingBox: BoundingBox,
            chunkPos: ChunkPos,
            blockPos: BlockPos
        ) {
            if (!this.isInInvalidLocation(worldGenLevel, boundingBox)) {
                val i = 0
                val j = 2
                val k = 0
                val l = 2
                val m = this.numSections * 5 - 1
                val blockState: BlockState = this.type.planksState
                this.generateBox(worldGenLevel, boundingBox, 0, 0, 0, 2, 1, m, CAVE_AIR, CAVE_AIR, false)
                this.generateMaybeBox(
                    worldGenLevel,
                    boundingBox,
                    randomSource,
                    0.8f,
                    0,
                    2,
                    0,
                    2,
                    2,
                    m,
                    CAVE_AIR,
                    CAVE_AIR,
                    false,
                    false
                )
                if (this.spiderCorridor) {
                    this.generateMaybeBox(
                        worldGenLevel,
                        boundingBox,
                        randomSource,
                        0.6f,
                        0,
                        0,
                        0,
                        2,
                        1,
                        m,
                        Blocks.COBWEB.defaultBlockState(),
                        CAVE_AIR,
                        false,
                        true
                    )
                }

                for (n in 0..<this.numSections) {
                    val o = 2 + n * 5
                    this.placeSupport(worldGenLevel, boundingBox, 0, 0, o, 2, 2, randomSource)
                    this.maybePlaceCobWeb(worldGenLevel, boundingBox, randomSource, 0.1f, 0, 2, o - 1)
                    this.maybePlaceCobWeb(worldGenLevel, boundingBox, randomSource, 0.1f, 2, 2, o - 1)
                    this.maybePlaceCobWeb(worldGenLevel, boundingBox, randomSource, 0.1f, 0, 2, o + 1)
                    this.maybePlaceCobWeb(worldGenLevel, boundingBox, randomSource, 0.1f, 2, 2, o + 1)
                    this.maybePlaceCobWeb(worldGenLevel, boundingBox, randomSource, 0.05f, 0, 2, o - 2)
                    this.maybePlaceCobWeb(worldGenLevel, boundingBox, randomSource, 0.05f, 2, 2, o - 2)
                    this.maybePlaceCobWeb(worldGenLevel, boundingBox, randomSource, 0.05f, 0, 2, o + 2)
                    this.maybePlaceCobWeb(worldGenLevel, boundingBox, randomSource, 0.05f, 2, 2, o + 2)
                    if (randomSource.nextInt(100) == 0) {
                        this.createChest(
                            worldGenLevel,
                            boundingBox,
                            randomSource,
                            2,
                            0,
                            o - 1,
                            BuiltInLootTables.ABANDONED_MINESHAFT
                        )
                    }

                    if (randomSource.nextInt(100) == 0) {
                        this.createChest(
                            worldGenLevel,
                            boundingBox,
                            randomSource,
                            0,
                            0,
                            o + 1,
                            BuiltInLootTables.ABANDONED_MINESHAFT
                        )
                    }

                    if (this.spiderCorridor && !this.hasPlacedSpider) {
                        val p = 1
                        val q = o - 1 + randomSource.nextInt(3)
                        val blockPos2: BlockPos = this.getWorldPos(1, 0, q)
                        if (boundingBox.isInside(blockPos2) && this.isInterior(worldGenLevel, 1, 0, q, boundingBox)) {
                            this.hasPlacedSpider = true
                            worldGenLevel.setBlock(blockPos2, Blocks.SPAWNER.defaultBlockState(), 2)
                            val blockEntity = worldGenLevel.getBlockEntity(blockPos2)
                            if (blockEntity is SpawnerBlockEntity) {
                                blockEntity.setEntityId(EntityType.CAVE_SPIDER, randomSource)
                            }
                        }
                    }
                }

                for (n in 0..2) {
                    for (o in 0..m) {
                        this.setPlanksBlock(worldGenLevel, boundingBox, blockState, n, -1, o)
                    }
                }

                val n = 2
                this.placeDoubleLowerOrUpperSupport(worldGenLevel, boundingBox, 0, -1, 2)
                if (this.numSections > 1) {
                    val o = m - 2
                    this.placeDoubleLowerOrUpperSupport(worldGenLevel, boundingBox, 0, -1, o)
                }

                if (this.hasRails) {
                    val blockState2 = Blocks.RAIL.defaultBlockState()
                        .setValue(RailBlock.SHAPE, RailShape.NORTH_SOUTH)

                    for (p in 0..m) {
                        val blockState3 = this.getBlock(worldGenLevel, 1, -1, p, boundingBox)
                        if (!blockState3.isAir && blockState3.isSolidRender(
                                worldGenLevel,
                                this.getWorldPos(1, -1, p)
                            )
                        ) {
                            val f = if (this.isInterior(worldGenLevel, 1, 0, p, boundingBox)) 0.7f else 0.9f
                            this.maybeGenerateBlock(worldGenLevel, boundingBox, randomSource, f, 1, 0, p, blockState2)
                        }
                    }
                }
            }
        }

        private fun placeDoubleLowerOrUpperSupport(
            worldGenLevel: WorldGenLevel,
            boundingBox: BoundingBox,
            i: Int,
            j: Int,
            k: Int
        ) {
            val blockState: BlockState = this.type.woodState
            val blockState2: BlockState = this.type.planksState
            if (this.getBlock(worldGenLevel, i, j, k, boundingBox).`is`(blockState2.block)) {
                this.fillPillarDownOrChainUp(worldGenLevel, blockState, i, j, k, boundingBox)
            }

            if (this.getBlock(worldGenLevel, i + 2, j, k, boundingBox).`is`(blockState2.block)) {
                this.fillPillarDownOrChainUp(worldGenLevel, blockState, i + 2, j, k, boundingBox)
            }
        }

        override fun fillColumnDown(
            worldGenLevel: WorldGenLevel,
            blockState: BlockState,
            i: Int,
            j: Int,
            k: Int,
            boundingBox: BoundingBox
        ) {
            val mutableBlockPos = this.getWorldPos(i, j, k)
            if (boundingBox.isInside(mutableBlockPos)) {
                val l = mutableBlockPos.y

                while (this.isReplaceableByStructures(worldGenLevel.getBlockState(mutableBlockPos)) && mutableBlockPos.y > worldGenLevel.minBuildHeight + 1) {
                    mutableBlockPos.move(Direction.DOWN)
                }

                if (this.canPlaceColumnOnTopOf(
                        worldGenLevel,
                        mutableBlockPos,
                        worldGenLevel.getBlockState(mutableBlockPos)
                    )
                ) {
                    while (mutableBlockPos.y < l) {
                        mutableBlockPos.move(Direction.UP)
                        worldGenLevel.setBlock(mutableBlockPos, blockState, 2)
                    }
                }
            }
        }

        protected fun fillPillarDownOrChainUp(
            worldGenLevel: WorldGenLevel,
            blockState: BlockState,
            i: Int,
            j: Int,
            k: Int,
            boundingBox: BoundingBox
        ) {
            val mutableBlockPos = this.getWorldPos(i, j, k)
            if (boundingBox.isInside(mutableBlockPos)) {
                val l = mutableBlockPos.y
                var m = 1
                var bl = true

                var bl2 = true
                while (bl || bl2) {
                    if (bl) {
                        mutableBlockPos.setY(l - m)
                        val blockState2 = worldGenLevel.getBlockState(mutableBlockPos)
                        val bl3 = this.isReplaceableByStructures(blockState2) && !blockState2.`is`(Blocks.LAVA)
                        if (!bl3 && this.canPlaceColumnOnTopOf(worldGenLevel, mutableBlockPos, blockState2)) {
                            fillColumnBetween(worldGenLevel, blockState, mutableBlockPos, l - m + 1, l)
                            return
                        }

                        bl = m <= 20 && bl3 && mutableBlockPos.y > worldGenLevel.minBuildHeight + 1
                    }

                    if (bl2) {
                        mutableBlockPos.setY(l + m)
                        val blockState2 = worldGenLevel.getBlockState(mutableBlockPos)
                        val bl3 = this.isReplaceableByStructures(blockState2)
                        if (!bl3 && this.canHangChainBelow(worldGenLevel, mutableBlockPos, blockState2)) {
                            worldGenLevel.setBlock(mutableBlockPos.setY(l + 1), this.type.fenceState, 2)
                            fillColumnBetween(
                                worldGenLevel,
                                CHAIN_TYPE.defaultBlockState(),
                                mutableBlockPos,
                                l + 2,
                                l + m
                            )
                            return
                        }

                        bl2 = m <= 50 && bl3 && mutableBlockPos.y < worldGenLevel.maxBuildHeight - 1
                    }
                    m++
                }
            }
        }

        private fun canPlaceColumnOnTopOf(
            levelReader: LevelReader,
            blockPos: BlockPos,
            blockState: BlockState
        ): Boolean {
            return blockState.isFaceSturdy(levelReader, blockPos, Direction.UP)
        }

        private fun canHangChainBelow(levelReader: LevelReader, blockPos: BlockPos, blockState: BlockState): Boolean {
            return Block.canSupportCenter(
                levelReader,
                blockPos,
                Direction.DOWN
            ) && blockState.block !is FallingBlock
        }

        private fun placeSupport(
            worldGenLevel: WorldGenLevel,
            boundingBox: BoundingBox,
            i: Int,
            j: Int,
            k: Int,
            l: Int,
            m: Int,
            randomSource: RandomSource
        ) {
            if (this.isSupportingBox(worldGenLevel, boundingBox, i, m, l, k)) {
                val blockState: BlockState = this.type.planksState
                val blockState2: BlockState = this.type.fenceState
                this.generateBox(
                    worldGenLevel,
                    boundingBox,
                    i,
                    j,
                    k,
                    i,
                    l - 1,
                    k,
                    blockState2.setValue(FenceBlock.WEST, true),
                    CAVE_AIR,
                    false
                )
                this.generateBox(
                    worldGenLevel,
                    boundingBox,
                    m,
                    j,
                    k,
                    m,
                    l - 1,
                    k,
                    blockState2.setValue(FenceBlock.EAST, true),
                    CAVE_AIR,
                    false
                )
                if (randomSource.nextInt(4) == 0) {
                    this.generateBox(worldGenLevel, boundingBox, i, l, k, i, l, k, blockState, CAVE_AIR, false)
                    this.generateBox(worldGenLevel, boundingBox, m, l, k, m, l, k, blockState, CAVE_AIR, false)
                } else {
                    this.generateBox(worldGenLevel, boundingBox, i, l, k, m, l, k, blockState, CAVE_AIR, false)
                    this.maybeGenerateBlock(
                        worldGenLevel,
                        boundingBox,
                        randomSource,
                        0.05f,
                        i + 1,
                        l,
                        k - 1,
                        Blocks.WALL_TORCH.defaultBlockState()
                            .setValue(WallTorchBlock.FACING, Direction.SOUTH)
                    )
                    this.maybeGenerateBlock(
                        worldGenLevel,
                        boundingBox,
                        randomSource,
                        0.05f,
                        i + 1,
                        l,
                        k + 1,
                        Blocks.WALL_TORCH.defaultBlockState()
                            .setValue(WallTorchBlock.FACING, Direction.NORTH)
                    )
                }
            }
        }

        private fun maybePlaceCobWeb(
            worldGenLevel: WorldGenLevel,
            boundingBox: BoundingBox,
            randomSource: RandomSource,
            f: Float,
            i: Int,
            j: Int,
            k: Int
        ) {
            if (this.isInterior(
                    worldGenLevel,
                    i,
                    j,
                    k,
                    boundingBox
                ) && randomSource.nextFloat() < f && this.hasSturdyNeighbours(worldGenLevel, boundingBox, i, j, k, 2)
            ) {
                this.placeBlock(worldGenLevel, Blocks.COBWEB.defaultBlockState(), i, j, k, boundingBox)
            }
        }

        private fun hasSturdyNeighbours(
            worldGenLevel: WorldGenLevel,
            boundingBox: BoundingBox,
            i: Int,
            j: Int,
            k: Int,
            l: Int
        ): Boolean {
            val mutableBlockPos = this.getWorldPos(i, j, k)
            var m = 0

            for (direction in Direction.entries) {
                mutableBlockPos.move(direction)
                if (boundingBox.isInside(mutableBlockPos)
                    && worldGenLevel.getBlockState(mutableBlockPos)
                        .isFaceSturdy(worldGenLevel, mutableBlockPos, direction.opposite)
                ) {
                    if (++m >= l) {
                        return true
                    }
                }

                mutableBlockPos.move(direction.opposite)
            }

            return false
        }

        companion object {
            fun findCorridorSize(
                structurePieceAccessor: StructurePieceAccessor,
                randomSource: RandomSource,
                i: Int,
                j: Int,
                k: Int,
                direction: Direction
            ): BoundingBox? {
                for (l in randomSource.nextInt(3) + 2 downTo 1) {
                    val m = l * 5

                    val boundingBox = when (direction) {
                        Direction.SOUTH -> BoundingBox(0, 0, 0, 2, 2, m - 1)
                        Direction.WEST -> BoundingBox(-(m - 1), 0, 0, 0, 2, 2)
                        Direction.EAST -> BoundingBox(0, 0, 0, m - 1, 2, 2)
                        else -> {
                            BoundingBox(0, 0, -(m - 1), 2, 2, 0)
                            BoundingBox(0, 0, 0, 2, 2, m - 1)
                            BoundingBox(-(m - 1), 0, 0, 0, 2, 2)
                            BoundingBox(0, 0, 0, m - 1, 2, 2)
                        }
                    }
                    boundingBox.move(i, j, k)
                    if (structurePieceAccessor.findCollisionPiece(boundingBox) == null) {
                        return boundingBox
                    }
                }

                return null
            }

            private fun fillColumnBetween(
                worldGenLevel: WorldGenLevel,
                blockState: BlockState,
                mutableBlockPos: MutableBlockPos,
                i: Int,
                j: Int
            ) {
                for (k in i..<j) {
                    worldGenLevel.setBlock(mutableBlockPos.setY(k), blockState, 2)
                }
            }
        }
    }

    class MineShaftCrossing : MineShaftPiece {
        private val direction: Direction
        private val isTwoFloored: Boolean

        constructor(compoundTag: CompoundTag) : super(DnDStructurePiceTypes.MINESHAFT_CROSSING, compoundTag) {
            this.isTwoFloored = compoundTag.getBoolean("tf")
            this.direction = Direction.from2DDataValue(compoundTag.getInt("D"))
        }

        protected override fun addAdditionalSaveData(
            structurePieceSerializationContext: StructurePieceSerializationContext,
            compoundTag: CompoundTag
        ) {
            super.addAdditionalSaveData(structurePieceSerializationContext, compoundTag)
            compoundTag.putBoolean("tf", this.isTwoFloored)
            compoundTag.putInt("D", this.direction.get2DDataValue())
        }

        constructor(i: Int, boundingBox: BoundingBox, direction: Direction, type: DnDMineshaftStructure.Type) : super(
            DnDStructurePiceTypes.MINESHAFT_CROSSING,
            i,
            type,
            boundingBox
        ) {
            this.direction = direction
            this.isTwoFloored = boundingBox.ySpan > 3
        }

        override fun addChildren(
            structurePiece: StructurePiece,
            structurePieceAccessor: StructurePieceAccessor,
            randomSource: RandomSource
        ) {
            val i = this.getGenDepth()
            when (this.direction) {
                Direction.NORTH -> {
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() - 1,
                        Direction.NORTH,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() - 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        Direction.WEST,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.maxX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        Direction.EAST,
                        i
                    )
                }

                Direction.SOUTH -> {
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.maxZ() + 1,
                        Direction.SOUTH,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() - 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        Direction.WEST,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.maxX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        Direction.EAST,
                        i
                    )
                }

                Direction.WEST -> {
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() - 1,
                        Direction.NORTH,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.maxZ() + 1,
                        Direction.SOUTH,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() - 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        Direction.WEST,
                        i
                    )
                }

                Direction.EAST -> {
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() - 1,
                        Direction.NORTH,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.maxZ() + 1,
                        Direction.SOUTH,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.maxX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        Direction.EAST,
                        i
                    )
                }

                else -> {
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() - 1,
                        Direction.NORTH,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() - 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        Direction.WEST,
                        i
                    )
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.maxX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        Direction.EAST,
                        i
                    )
                }
            }

            if (this.isTwoFloored) {
                if (randomSource.nextBoolean()) {
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY() + 3 + 1,
                        this.boundingBox.minZ() - 1,
                        Direction.NORTH,
                        i
                    )
                }

                if (randomSource.nextBoolean()) {
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() - 1,
                        this.boundingBox.minY() + 3 + 1,
                        this.boundingBox.minZ() + 1,
                        Direction.WEST,
                        i
                    )
                }

                if (randomSource.nextBoolean()) {
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.maxX() + 1,
                        this.boundingBox.minY() + 3 + 1,
                        this.boundingBox.minZ() + 1,
                        Direction.EAST,
                        i
                    )
                }

                if (randomSource.nextBoolean()) {
                    generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY() + 3 + 1,
                        this.boundingBox.maxZ() + 1,
                        Direction.SOUTH,
                        i
                    )
                }
            }
        }

        override fun postProcess(
            worldGenLevel: WorldGenLevel,
            structureManager: StructureManager,
            chunkGenerator: ChunkGenerator,
            randomSource: RandomSource,
            boundingBox: BoundingBox,
            chunkPos: ChunkPos,
            blockPos: BlockPos
        ) {
            if (!this.isInInvalidLocation(worldGenLevel, boundingBox)) {
                val blockState: BlockState = this.type.planksState
                if (this.isTwoFloored) {
                    this.generateBox(
                        worldGenLevel,
                        boundingBox,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ(),
                        this.boundingBox.maxX() - 1,
                        this.boundingBox.minY() + 3 - 1,
                        this.boundingBox.maxZ(),
                        CAVE_AIR,
                        CAVE_AIR,
                        false
                    )
                    this.generateBox(
                        worldGenLevel,
                        boundingBox,
                        this.boundingBox.minX(),
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        this.boundingBox.maxX(),
                        this.boundingBox.minY() + 3 - 1,
                        this.boundingBox.maxZ() - 1,
                        CAVE_AIR,
                        CAVE_AIR,
                        false
                    )
                    this.generateBox(
                        worldGenLevel,
                        boundingBox,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.maxY() - 2,
                        this.boundingBox.minZ(),
                        this.boundingBox.maxX() - 1,
                        this.boundingBox.maxY(),
                        this.boundingBox.maxZ(),
                        CAVE_AIR,
                        CAVE_AIR,
                        false
                    )
                    this.generateBox(
                        worldGenLevel,
                        boundingBox,
                        this.boundingBox.minX(),
                        this.boundingBox.maxY() - 2,
                        this.boundingBox.minZ() + 1,
                        this.boundingBox.maxX(),
                        this.boundingBox.maxY(),
                        this.boundingBox.maxZ() - 1,
                        CAVE_AIR,
                        CAVE_AIR,
                        false
                    )
                    this.generateBox(
                        worldGenLevel,
                        boundingBox,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY() + 3,
                        this.boundingBox.minZ() + 1,
                        this.boundingBox.maxX() - 1,
                        this.boundingBox.minY() + 3,
                        this.boundingBox.maxZ() - 1,
                        CAVE_AIR,
                        CAVE_AIR,
                        false
                    )
                } else {
                    this.generateBox(
                        worldGenLevel,
                        boundingBox,
                        this.boundingBox.minX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ(),
                        this.boundingBox.maxX() - 1,
                        this.boundingBox.maxY(),
                        this.boundingBox.maxZ(),
                        CAVE_AIR,
                        CAVE_AIR,
                        false
                    )
                    this.generateBox(
                        worldGenLevel,
                        boundingBox,
                        this.boundingBox.minX(),
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() + 1,
                        this.boundingBox.maxX(),
                        this.boundingBox.maxY(),
                        this.boundingBox.maxZ() - 1,
                        CAVE_AIR,
                        CAVE_AIR,
                        false
                    )
                }

                this.placeSupportPillar(
                    worldGenLevel,
                    boundingBox,
                    this.boundingBox.minX() + 1,
                    this.boundingBox.minY(),
                    this.boundingBox.minZ() + 1,
                    this.boundingBox.maxY()
                )
                this.placeSupportPillar(
                    worldGenLevel,
                    boundingBox,
                    this.boundingBox.minX() + 1,
                    this.boundingBox.minY(),
                    this.boundingBox.maxZ() - 1,
                    this.boundingBox.maxY()
                )
                this.placeSupportPillar(
                    worldGenLevel,
                    boundingBox,
                    this.boundingBox.maxX() - 1,
                    this.boundingBox.minY(),
                    this.boundingBox.minZ() + 1,
                    this.boundingBox.maxY()
                )
                this.placeSupportPillar(
                    worldGenLevel,
                    boundingBox,
                    this.boundingBox.maxX() - 1,
                    this.boundingBox.minY(),
                    this.boundingBox.maxZ() - 1,
                    this.boundingBox.maxY()
                )
                val i = this.boundingBox.minY() - 1

                for (j in this.boundingBox.minX()..this.boundingBox.maxX()) {
                    for (k in this.boundingBox.minZ()..this.boundingBox.maxZ()) {
                        this.setPlanksBlock(worldGenLevel, boundingBox, blockState, j, i, k)
                    }
                }
            }
        }

        private fun placeSupportPillar(
            worldGenLevel: WorldGenLevel,
            boundingBox: BoundingBox,
            i: Int,
            j: Int,
            k: Int,
            l: Int
        ) {
            if (!this.getBlock(worldGenLevel, i, l + 1, k, boundingBox).isAir) {
                this.generateBox(worldGenLevel, boundingBox, i, j, k, i, l, k, this.type.planksState, CAVE_AIR, false)
            }
        }

        companion object {
            fun findCrossing(
                structurePieceAccessor: StructurePieceAccessor,
                randomSource: RandomSource,
                i: Int,
                j: Int,
                k: Int,
                direction: Direction
            ): BoundingBox? {
                val l: Int
                if (randomSource.nextInt(4) == 0) {
                    l = 6
                } else {
                    l = 2
                }
                val boundingBox = when (direction) {
                    Direction.SOUTH -> BoundingBox(-1, 0, 0, 3, l, 4)
                    Direction.WEST -> BoundingBox(-4, 0, -1, 0, l, 3)
                    Direction.EAST -> BoundingBox(0, 0, -1, 4, l, 3)
                    else -> {
                        BoundingBox(-1, 0, -4, 3, l, 0)
                        BoundingBox(-1, 0, 0, 3, l, 4)
                        BoundingBox(-4, 0, -1, 0, l, 3)
                        BoundingBox(0, 0, -1, 4, l, 3)
                    }
                }
                boundingBox.move(i, j, k)
                return if (structurePieceAccessor.findCollisionPiece(boundingBox) != null) null else boundingBox
            }
        }
    }

    abstract class MineShaftPiece : StructurePiece {
        var type: DnDMineshaftStructure.Type

        constructor(
            structurePieceType: StructurePieceType,
            i: Int,
            type: DnDMineshaftStructure.Type,
            boundingBox: BoundingBox
        ) : super(structurePieceType, i, boundingBox) {
            this.type = type
        }

        constructor(structurePieceType: StructurePieceType, compoundTag: CompoundTag) : super(
            structurePieceType,
            compoundTag
        ) {
            this.type = byId(compoundTag.getInt("MST"))
        }

        override fun canBeReplaced(
            levelReader: LevelReader,
            i: Int,
            j: Int,
            k: Int,
            boundingBox: BoundingBox
        ): Boolean {
            val blockState = this.getBlock(levelReader, i, j, k, boundingBox)
            return !blockState.`is`(this.type.planksState.block) && !blockState.`is`(this.type.woodState.block) && !blockState.`is`(
                this.type.fenceState.block
            ) && !blockState.`is`(CHAIN_TYPE)
        }

        override fun addAdditionalSaveData(
            structurePieceSerializationContext: StructurePieceSerializationContext,
            compoundTag: CompoundTag
        ) {
            compoundTag.putInt("MST", this.type.ordinal)
        }

        protected fun isSupportingBox(
            blockGetter: BlockGetter,
            boundingBox: BoundingBox,
            i: Int,
            j: Int,
            k: Int,
            l: Int
        ): Boolean {
            for (m in i..j) {
                if (this.getBlock(blockGetter, m, k + 1, l, boundingBox).isAir) {
                    return false
                }
            }

            return true
        }

        protected fun isInInvalidLocation(levelAccessor: LevelAccessor, boundingBox: BoundingBox): Boolean {
            val i = max(this.boundingBox.minX() - 1, boundingBox.minX())
            val j = max(this.boundingBox.minY() - 1, boundingBox.minY())
            val k = max(this.boundingBox.minZ() - 1, boundingBox.minZ())
            val l = min(this.boundingBox.maxX() + 1, boundingBox.maxX())
            val m = min(this.boundingBox.maxY() + 1, boundingBox.maxY())
            val n = min(this.boundingBox.maxZ() + 1, boundingBox.maxZ())
            val mutableBlockPos = MutableBlockPos((i + l) / 2, (j + m) / 2, (k + n) / 2)
            if (levelAccessor.getBiome(mutableBlockPos).`is`(BiomeTags.MINESHAFT_BLOCKING)) {
                return true
            }

            for (o in i..l) {
                for (p in k..n) {
                    if (levelAccessor.getBlockState(mutableBlockPos.set(o, j, p)).liquid()) {
                        return true
                    }

                    if (levelAccessor.getBlockState(mutableBlockPos.set(o, m, p)).liquid()) {
                        return true
                    }
                }
            }

            for (o in i..l) {
                for (p in j..m) {
                    if (levelAccessor.getBlockState(mutableBlockPos.set(o, p, k)).liquid()) {
                        return true
                    }

                    if (levelAccessor.getBlockState(mutableBlockPos.set(o, p, n)).liquid()) {
                        return true
                    }
                }
            }

            for (o in k..n) {
                for (p in j..m) {
                    if (levelAccessor.getBlockState(mutableBlockPos.set(i, p, o)).liquid()) {
                        return true
                    }

                    if (levelAccessor.getBlockState(mutableBlockPos.set(l, p, o)).liquid()) {
                        return true
                    }
                }
            }

            return false
        }

        protected fun setPlanksBlock(
            worldGenLevel: WorldGenLevel,
            boundingBox: BoundingBox,
            blockState: BlockState,
            i: Int,
            j: Int,
            k: Int
        ) {
            if (this.isInterior(worldGenLevel, i, j, k, boundingBox)) {
                val blockPos: BlockPos = this.getWorldPos(i, j, k)
                val blockState2 = worldGenLevel.getBlockState(blockPos)
                if (!blockState2.isFaceSturdy(worldGenLevel, blockPos, Direction.UP)) {
                    worldGenLevel.setBlock(blockPos, blockState, 2)
                }
            }
        }
    }

    class MineShaftRoom : MineShaftPiece {
        private val childEntranceBoxes: MutableList<BoundingBox> = Lists.newLinkedList<BoundingBox>()

        constructor(i: Int, randomSource: RandomSource, j: Int, k: Int, type: DnDMineshaftStructure.Type) : super(
            DnDStructurePiceTypes.MINESHAFT_ROOM,
            i,
            type,
            BoundingBox(
                j,
                50,
                k,
                j + 7 + randomSource.nextInt(6),
                54 + randomSource.nextInt(6),
                k + 7 + randomSource.nextInt(6)
            )
        ) {
            this.type = type
        }

        constructor(compoundTag: CompoundTag) : super(DnDStructurePiceTypes.MINESHAFT_ROOM, compoundTag) {
            BoundingBox.CODEC
                .listOf()
                .parse(NbtOps.INSTANCE, compoundTag.getList("Entrances", 11))
                .resultOrPartial { msg: String -> LOGGER.error(msg) }
                .ifPresent({ c: MutableList<BoundingBox> -> this.childEntranceBoxes.addAll(c) })
        }

        override fun addChildren(
            structurePiece: StructurePiece,
            structurePieceAccessor: StructurePieceAccessor,
            randomSource: RandomSource
        ) {
            val i = this.getGenDepth()
            var j = this.boundingBox.ySpan - 3 - 1
            if (j <= 0) {
                j = 1
            }

            var k = 0

            while (k < this.boundingBox.xSpan) {
                k += randomSource.nextInt(this.boundingBox.xSpan)
                if (k + 3 > this.boundingBox.xSpan) {
                    break
                }

                val mineShaftPiece = generateAndAddPiece(
                    structurePiece,
                    structurePieceAccessor,
                    randomSource,
                    this.boundingBox.minX() + k,
                    this.boundingBox.minY() + randomSource.nextInt(j) + 1,
                    this.boundingBox.minZ() - 1,
                    Direction.NORTH,
                    i
                )
                if (mineShaftPiece != null) {
                    val boundingBox = mineShaftPiece.getBoundingBox()
                    this.childEntranceBoxes
                        .add(
                            BoundingBox(
                                boundingBox.minX(),
                                boundingBox.minY(),
                                this.boundingBox.minZ(),
                                boundingBox.maxX(),
                                boundingBox.maxY(),
                                this.boundingBox.minZ() + 1
                            )
                        )
                }

                k += 4
            }

            k = 0

            while (k < this.boundingBox.xSpan) {
                k += randomSource.nextInt(this.boundingBox.xSpan)
                if (k + 3 > this.boundingBox.xSpan) {
                    break
                }

                val mineShaftPiece = generateAndAddPiece(
                    structurePiece,
                    structurePieceAccessor,
                    randomSource,
                    this.boundingBox.minX() + k,
                    this.boundingBox.minY() + randomSource.nextInt(j) + 1,
                    this.boundingBox.maxZ() + 1,
                    Direction.SOUTH,
                    i
                )
                if (mineShaftPiece != null) {
                    val boundingBox = mineShaftPiece.getBoundingBox()
                    this.childEntranceBoxes
                        .add(
                            BoundingBox(
                                boundingBox.minX(),
                                boundingBox.minY(),
                                this.boundingBox.maxZ() - 1,
                                boundingBox.maxX(),
                                boundingBox.maxY(),
                                this.boundingBox.maxZ()
                            )
                        )
                }

                k += 4
            }

            k = 0

            while (k < this.boundingBox.zSpan) {
                k += randomSource.nextInt(this.boundingBox.zSpan)
                if (k + 3 > this.boundingBox.zSpan) {
                    break
                }

                val mineShaftPiece = generateAndAddPiece(
                    structurePiece,
                    structurePieceAccessor,
                    randomSource,
                    this.boundingBox.minX() - 1,
                    this.boundingBox.minY() + randomSource.nextInt(j) + 1,
                    this.boundingBox.minZ() + k,
                    Direction.WEST,
                    i
                )
                if (mineShaftPiece != null) {
                    val boundingBox = mineShaftPiece.getBoundingBox()
                    this.childEntranceBoxes
                        .add(
                            BoundingBox(
                                this.boundingBox.minX(),
                                boundingBox.minY(),
                                boundingBox.minZ(),
                                this.boundingBox.minX() + 1,
                                boundingBox.maxY(),
                                boundingBox.maxZ()
                            )
                        )
                }

                k += 4
            }

            k = 0

            while (k < this.boundingBox.zSpan) {
                k += randomSource.nextInt(this.boundingBox.zSpan)
                if (k + 3 > this.boundingBox.zSpan) {
                    break
                }

                val structurePiece2: StructurePiece? = generateAndAddPiece(
                    structurePiece,
                    structurePieceAccessor,
                    randomSource,
                    this.boundingBox.maxX() + 1,
                    this.boundingBox.minY() + randomSource.nextInt(j) + 1,
                    this.boundingBox.minZ() + k,
                    Direction.EAST,
                    i
                )
                if (structurePiece2 != null) {
                    val boundingBox = structurePiece2.getBoundingBox()
                    this.childEntranceBoxes
                        .add(
                            BoundingBox(
                                this.boundingBox.maxX() - 1,
                                boundingBox.minY(),
                                boundingBox.minZ(),
                                this.boundingBox.maxX(),
                                boundingBox.maxY(),
                                boundingBox.maxZ()
                            )
                        )
                }

                k += 4
            }
        }

        override fun postProcess(
            worldGenLevel: WorldGenLevel,
            structureManager: StructureManager,
            chunkGenerator: ChunkGenerator,
            randomSource: RandomSource,
            boundingBox: BoundingBox,
            chunkPos: ChunkPos,
            blockPos: BlockPos
        ) {
            if (!this.isInInvalidLocation(worldGenLevel, boundingBox)) {
                this.generateBox(
                    worldGenLevel,
                    boundingBox,
                    this.boundingBox.minX(),
                    this.boundingBox.minY() + 1,
                    this.boundingBox.minZ(),
                    this.boundingBox.maxX(),
                    min(this.boundingBox.minY() + 3, this.boundingBox.maxY()),
                    this.boundingBox.maxZ(),
                    CAVE_AIR,
                    CAVE_AIR,
                    false
                )

                for (boundingBox2 in this.childEntranceBoxes) {
                    this.generateBox(
                        worldGenLevel,
                        boundingBox,
                        boundingBox2.minX(),
                        boundingBox2.maxY() - 2,
                        boundingBox2.minZ(),
                        boundingBox2.maxX(),
                        boundingBox2.maxY(),
                        boundingBox2.maxZ(),
                        CAVE_AIR,
                        CAVE_AIR,
                        false
                    )
                }

                this.generateUpperHalfSphere(
                    worldGenLevel,
                    boundingBox,
                    this.boundingBox.minX(),
                    this.boundingBox.minY() + 4,
                    this.boundingBox.minZ(),
                    this.boundingBox.maxX(),
                    this.boundingBox.maxY(),
                    this.boundingBox.maxZ(),
                    CAVE_AIR,
                    false
                )
            }
        }

        override fun move(i: Int, j: Int, k: Int) {
            super.move(i, j, k)

            for (boundingBox in this.childEntranceBoxes) {
                boundingBox.move(i, j, k)
            }
        }

        protected override fun addAdditionalSaveData(
            structurePieceSerializationContext: StructurePieceSerializationContext,
            compoundTag: CompoundTag
        ) {
            super.addAdditionalSaveData(structurePieceSerializationContext, compoundTag)
            BoundingBox.CODEC
                .listOf()
                .encodeStart(NbtOps.INSTANCE, this.childEntranceBoxes)
                .resultOrPartial { msg: String -> LOGGER.error(msg) }
                .ifPresent(Consumer { tag: Tag -> compoundTag.put("Entrances", tag) })
        }
    }

    class MineShaftStairs : MineShaftPiece {
        constructor(i: Int, boundingBox: BoundingBox, direction: Direction, type: DnDMineshaftStructure.Type) : super(
            DnDStructurePiceTypes.MINESHAFT_STAIRS,
            i,
            type,
            boundingBox
        ) {
            this.setOrientation(direction)
        }

        constructor(compoundTag: CompoundTag) : super(DnDStructurePiceTypes.MINESHAFT_STAIRS, compoundTag)

        override fun addChildren(
            structurePiece: StructurePiece,
            structurePieceAccessor: StructurePieceAccessor,
            randomSource: RandomSource
        ) {
            val i = this.getGenDepth()
            val direction = this.orientation
            if (direction != null) {
                when (direction) {
                    Direction.NORTH -> generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX(),
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() - 1,
                        Direction.NORTH,
                        i
                    )

                    Direction.SOUTH -> generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX(),
                        this.boundingBox.minY(),
                        this.boundingBox.maxZ() + 1,
                        Direction.SOUTH,
                        i
                    )

                    Direction.WEST -> generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX() - 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ(),
                        Direction.WEST,
                        i
                    )

                    Direction.EAST -> generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.maxX() + 1,
                        this.boundingBox.minY(),
                        this.boundingBox.minZ(),
                        Direction.EAST,
                        i
                    )

                    else -> generateAndAddPiece(
                        structurePiece,
                        structurePieceAccessor,
                        randomSource,
                        this.boundingBox.minX(),
                        this.boundingBox.minY(),
                        this.boundingBox.minZ() - 1,
                        Direction.NORTH,
                        i
                    )
                }
            }
        }

        override fun postProcess(
            worldGenLevel: WorldGenLevel,
            structureManager: StructureManager,
            chunkGenerator: ChunkGenerator,
            randomSource: RandomSource,
            boundingBox: BoundingBox,
            chunkPos: ChunkPos,
            blockPos: BlockPos
        ) {
            if (!this.isInInvalidLocation(worldGenLevel, boundingBox)) {
                this.generateBox(worldGenLevel, boundingBox, 0, 5, 0, 2, 7, 1, CAVE_AIR, CAVE_AIR, false)
                this.generateBox(worldGenLevel, boundingBox, 0, 0, 7, 2, 2, 8, CAVE_AIR, CAVE_AIR, false)

                for (i in 0..4) {
                    this.generateBox(
                        worldGenLevel,
                        boundingBox,
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
        }

        companion object {
            fun findStairs(
                structurePieceAccessor: StructurePieceAccessor,
                randomSource: RandomSource,
                i: Int,
                j: Int,
                k: Int,
                direction: Direction
            ): BoundingBox? {
                val boundingBox = when (direction) {
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
                boundingBox.move(i, j, k)
                return if (structurePieceAccessor.findCollisionPiece(boundingBox) != null) null else boundingBox
            }
        }
    }

    val CHAIN_TYPE: Block = Blocks.CHAIN
}
