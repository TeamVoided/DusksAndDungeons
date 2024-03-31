//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package net.minecraft.world.gen.feature

import com.google.common.collect.ImmutableList
import com.mojang.serialization.Codec
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.feature.util.FeatureContext
import kotlin.math.max

class DeltaFeature(codec: Codec<DeltaFeatureConfig?>?) :
    Feature<DeltaFeatureConfig>(codec) {
    override fun place(context: FeatureContext<DeltaFeatureConfig>): Boolean {
        var bl = false
        val randomGenerator = context.random
        val structureWorldAccess = context.world
        val deltaFeatureConfig = context.config as DeltaFeatureConfig
        val blockPos = context.origin
        val bl2 = randomGenerator.nextDouble() < 0.9
        val i = if (bl2) deltaFeatureConfig.rimSize[randomGenerator] else 0
        val j = if (bl2) deltaFeatureConfig.rimSize[randomGenerator] else 0
        val bl3 = bl2 && i != 0 && j != 0
        val k = deltaFeatureConfig.size[randomGenerator]
        val l = deltaFeatureConfig.size[randomGenerator]
        val m = max(k.toDouble(), l.toDouble()).toInt()
        val var14: Iterator<*> = BlockPos.iterateOutwards(blockPos, k, 0, l).iterator()

        while (var14.hasNext()) {
            val blockPos2 = var14.next() as BlockPos
            if (blockPos2.getManhattanDistance(blockPos) > m) {
                break
            }

            if (canPlace(structureWorldAccess, blockPos2, deltaFeatureConfig)) {
                if (bl3) {
                    bl = true
                    this.setBlockState(structureWorldAccess, blockPos2, deltaFeatureConfig.rim)
                }

                val blockPos3 = blockPos2.add(i, 0, j)
                if (canPlace(structureWorldAccess, blockPos3, deltaFeatureConfig)) {
                    bl = true
                    this.setBlockState(structureWorldAccess, blockPos3, deltaFeatureConfig.contents)
                }
            }
        }

        return bl
    }

    companion object {
        private val BLOCKS: ImmutableList<Block> = ImmutableList.of(
            Blocks.BEDROCK,
            Blocks.NETHER_BRICKS,
            Blocks.NETHER_BRICK_FENCE,
            Blocks.NETHER_BRICK_STAIRS,
            Blocks.NETHER_WART,
            Blocks.CHEST,
            Blocks.SPAWNER
        )
        private val DIRECTIONS = Direction.entries.toTypedArray()
        private const val RIM_SPAWN_CHANCE = 0.9

        private fun canPlace(world: WorldAccess, pos: BlockPos, config: DeltaFeatureConfig): Boolean {
            val blockState = world.getBlockState(pos)
            if (blockState.isOf(config.contents.block)) {
                return false
            } else if (BLOCKS.contains(blockState.block)) {
                return false
            } else {
                val var4 = DIRECTIONS
                val var5 = var4.size

                for (var6 in 0 until var5) {
                    val direction = var4[var6]
                    val bl = world.getBlockState(pos.offset(direction)).isAir
                    if (bl && direction != Direction.UP || !bl && direction == Direction.UP) {
                        return false
                    }
                }

                return true
            }
        }
    }
}