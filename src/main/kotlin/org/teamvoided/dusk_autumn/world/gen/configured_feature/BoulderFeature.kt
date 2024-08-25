package org.teamvoided.dusk_autumn.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.BoulderConfig

class BoulderFeature(codec: Codec<BoulderConfig>) :
    Feature<BoulderConfig>(codec) {
    override fun place(context: FeatureContext<BoulderConfig>): Boolean {
        var blockPos = context.origin
        val structureWorldAccess = context.world
        val randomGenerator = context.random
        val config = context.config as BoulderConfig

        val size = config.size[randomGenerator]
        val boulderCount = config.boulderCount[randomGenerator]
        val weirdness = config.weirdness[randomGenerator]
        val otherBoulderOffset = config.otherBoulderOffset[randomGenerator]

        if (blockPos.y <= structureWorldAccess.bottomY + 1 + size) {
            return false
        } else {
            if (!structureWorldAccess.getBlockState(blockPos).isIn(BlockTags.FEATURES_CANNOT_REPLACE))
                structureWorldAccess.setBlockState(blockPos, config.block.getBlockState(randomGenerator, blockPos), 3)
            for (i in 0..boulderCount) {
                val x = randomGenerator.nextInt(size)
                val y = randomGenerator.nextInt(size)
                val z = randomGenerator.nextInt(size)
                val f = (x + y + z) * 0.333 + 0.5
                val var11: Iterator<*> =
                    BlockPos.iterate(blockPos.add(-x, -y, -z), blockPos.add(x, y, z)).iterator()

                while (var11.hasNext()) {
                    val blockPosPlace = var11.next() as BlockPos
                    val xOffset = blockPos.x - blockPosPlace.x
                    val yOffset = blockPos.y - blockPosPlace.y
                    val zOffset = blockPos.z - blockPosPlace.z
                    val distance =
                        ((randomGenerator.nextInt(weirdness) + 1) * xOffset * xOffset) +
                                ((randomGenerator.nextInt(weirdness) + 1) * zOffset * zOffset) +
                                ((randomGenerator.nextInt(weirdness) + 1) * yOffset * yOffset)
                    if (distance <= (f * f) &&
                        !structureWorldAccess.getBlockState(blockPosPlace).isIn(BlockTags.FEATURES_CANNOT_REPLACE)
                    ) {
                        structureWorldAccess.setBlockState(
                            blockPosPlace,
                            config.block.getBlockState(randomGenerator, blockPosPlace),
                            3
                        )
                    }
                }

                blockPos = blockPos.add(
                    randomGenerator.nextInt(otherBoulderOffset) - randomGenerator.nextInt(otherBoulderOffset),
                    -randomGenerator.nextInt(otherBoulderOffset),
                    randomGenerator.nextInt(otherBoulderOffset) - randomGenerator.nextInt(otherBoulderOffset)
                )
            }

            return true
        }
    }
}