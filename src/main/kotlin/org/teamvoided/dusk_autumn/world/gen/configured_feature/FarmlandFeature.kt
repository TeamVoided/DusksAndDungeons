package org.teamvoided.dusk_autumn.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.VegetationPatchFeatureConfig
import net.minecraft.world.gen.feature.util.FeatureContext
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FarmlandConfig
import java.util.function.Predicate
import kotlin.math.abs
import kotlin.math.max

open class FarmlandFeature(codec: Codec<FarmlandConfig>) :
    Feature<FarmlandConfig>(codec) {
    override fun place(context: FeatureContext<FarmlandConfig>): Boolean {
        val structureWorldAccess = context.world
        val chunkPos = ChunkPos(context.origin)
        val farmlandConfig = context.config
        val randomGenerator = context.random
        val blockPos = context.origin
        val predicate = Predicate { state: BlockState -> state.isIn(farmlandConfig.replaceable) }
        if (getDistance(chunkPos.x, chunkPos.z, START_CHUNK.x, START_CHUNK.z) > 1) {
            return true
        } else {
            val blockPos2 = START_BLOCK.withY(context.origin.y + START_BLOCK.y)
            val mutable = BlockPos.Mutable()

            for (i in chunkPos.startZ..chunkPos.endZ) {
                for (j in chunkPos.startX..chunkPos.endX) {
                    if (getDistance(blockPos2.x, blockPos2.z, j, i) <= PLATFORM_RADIUS) {
                        mutable[j, blockPos2.y] = i
                        structureWorldAccess.setBlockState(mutable, Blocks.STONE.defaultState, 2)
                    }
                }
            }

            return true
        }
    }

    protected open fun generateCropFeature(
        world: StructureWorldAccess,
        config: FarmlandConfig,
        generator: ChunkGenerator,
        random: RandomGenerator,
        pos: BlockPos
    ): Boolean {
        return config.cropFeature.value().place(
            world,
            generator,
            random,
            pos.offset(Direction.UP)
        )
    }

    protected fun placeFarmland(
        world: StructureWorldAccess,
        config: FarmlandConfig,
        replaceable: Predicate<BlockState>,
        random: RandomGenerator,
        pos: BlockPos.Mutable,
        depth: Int
    ): Boolean {
        for (i in 0 until depth) {
            val blockState = config.farmlandBlock.getBlockState(random, pos)
            val blockState2 = world.getBlockState(pos)
            if (!blockState.isOf(blockState2.block)) {
                if (!replaceable.test(blockState2)) {
                    return i != 0
                }

                world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS)
                pos.move(Direction.DOWN)
            }
        }
        return true
    }


    companion object {
        private val START_BLOCK = BlockPos(8, 3, 8)
        private val START_CHUNK = ChunkPos(START_BLOCK)
        private const val PLATFORM_RADIUS = 16
        private const val PLATFORM_RADIUS_CHUNKS = 1

        private fun getDistance(x1: Int, z1: Int, x2: Int, z2: Int): Int {
            return max(abs((x1 - x2).toDouble()), abs((z1 - z2).toDouble())).toInt()
        }
    }
}