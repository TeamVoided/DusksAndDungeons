package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.tags.BlockTags
import net.minecraft.core.BlockPos
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.BoulderConfig

class BoulderFeature(codec: Codec<BoulderConfig>) :
    Feature<BoulderConfig>(codec) {
    override fun place(context: FeaturePlaceContext<BoulderConfig>): Boolean {
        var blockPos = context.origin()
        val structureWorldAccess = context.level()
        val randomGenerator = context.random()
        val config = context.config() as BoulderConfig

        var size = config.size.sample(randomGenerator)
        val boulderCount = config.boulderCount.sample(randomGenerator)

        if (blockPos.y <= structureWorldAccess.minBuildHeight + 1 + size) {
            return false
        } else {
            if (!structureWorldAccess.getBlockState(blockPos).`is`(BlockTags.FEATURES_CANNOT_REPLACE)) {
                structureWorldAccess.setBlock(blockPos, config.block.getState(randomGenerator, blockPos), 3)
            }
            for (i in 0..boulderCount) {
                size = config.size.sample(randomGenerator)
                val x = randomGenerator.nextInt(size)
                val y = randomGenerator.nextInt(size)
                val z = randomGenerator.nextInt(size)
                val f = (x + y + z) * 0.333 + 0.5
                val var11: Iterator<*> =
                    BlockPos.betweenClosed(blockPos.offset(-x, -y, -z), blockPos.offset(x, y, z)).iterator()

                while (var11.hasNext()) {
                    val blockPosPlace = var11.next() as BlockPos
                    val xOffset = blockPos.x - blockPosPlace.x
                    val yOffset = blockPos.y - blockPosPlace.y
                    val zOffset = blockPos.z - blockPosPlace.z
                    val distance =
                        (config.weirdness.sample(randomGenerator) * xOffset * xOffset) +
                                (config.weirdness.sample(randomGenerator) * zOffset * zOffset) +
                                (config.weirdness.sample(randomGenerator) * yOffset * yOffset)
                    if (distance <= (f * f) &&
                        !structureWorldAccess.getBlockState(blockPosPlace).`is`(BlockTags.FEATURES_CANNOT_REPLACE)
                    ) {
                        structureWorldAccess.setBlock(
                            blockPosPlace,
                            config.block.getState(randomGenerator, blockPosPlace),
                            3
                        )
                    }
                }
                blockPos = blockPos.offset(
                    config.otherBoulderOffset.sample(randomGenerator) - config.otherBoulderOffset.sample(randomGenerator),
                    randomGenerator.nextInt(size) - randomGenerator.nextInt(size),
                    config.otherBoulderOffset.sample(randomGenerator) - config.otherBoulderOffset.sample(randomGenerator)
                )
                if (config.moveDownIfReplaceable)
                    for (i in 0..size) {
                        if (structureWorldAccess.getBlockState(blockPos).`is`(BlockTags.REPLACEABLE)) {
                            blockPos.below()
                        }
                    }
            }

            return true
        }
    }
}