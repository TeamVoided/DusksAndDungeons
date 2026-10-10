/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.BoulderConfig
import kotlin.math.max

class BoulderFeature(codec: Codec<BoulderConfig>) : Feature<BoulderConfig>(codec) {

    override fun place(ctx: FeaturePlaceContext<BoulderConfig>): Boolean {
        var origin = ctx.origin()
        val level = ctx.level()
        val random = ctx.random()
        val config = ctx.config()

        var size = config.size.sample(random)
        val boulderCount = config.boulderCount.sample(random)

        if (origin.y <= level.minBuildHeight + 1 + size) {
            return false
        }

        if (!level.getBlockState(origin).`is`(BlockTags.FEATURES_CANNOT_REPLACE)) {
            level.setBlock(origin, config.block.getState(random, origin), 3)
        }
        repeat(boulderCount) {
            size = config.size.sample(random)
            val x = max(random.nextInt(size), 1)
            val y = random.nextInt(size)
            val z = max(random.nextInt(size), 1)
            val radius = (x + y + z) * 0.333 + 0.5

            val smallSmoother = if (x + y + z < 9) 0.45 else 1.0
            val boulderArea =
                BlockPos.betweenClosed(origin.offset(-x, -y, -z), origin.offset(x, y, z)).iterator()
            for (pos in boulderArea) {
                val xOffset = origin.x - pos.x
                val yOffset = origin.y - pos.y
                val zOffset = origin.z - pos.z
                val distance = (config.weirdness.sample(random) * smallSmoother) *
                        (xOffset * xOffset) +
                        (zOffset * zOffset) +
                        (yOffset * yOffset)

                if (distance <= (radius * radius) && !level.getBlockState(pos)
                        .`is`(BlockTags.FEATURES_CANNOT_REPLACE)
                ) {
                    level.setBlock(pos, config.block.getState(random, pos), Block.UPDATE_ALL)
                }
            }
            origin = origin.offset(
                config.otherBoulderOffset.sample(random) - config.otherBoulderOffset.sample(random),
                random.nextInt(size) - random.nextInt(size),
                config.otherBoulderOffset.sample(random) - config.otherBoulderOffset.sample(random)
            )
            if (config.moveDownIfReplaceable) {
                for (i in 0..size) {
                    if (level.getBlockState(origin).`is`(BlockTags.REPLACEABLE)) {
                        origin = origin.below()
                    }
                    else {
                        break
                    }
                }
            }
        }

        return true
    }

}