package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.core.Direction
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration

class CaveSurfaceFeature(codec: Codec<BlockStateConfiguration>) : Feature<BlockStateConfiguration>(codec) {
    override fun place(featurePlaceContext: FeaturePlaceContext<BlockStateConfiguration>): Boolean {
        val config = featurePlaceContext.config()
        val rand = featurePlaceContext.random()
        val blockPos = featurePlaceContext.origin()
        val world = featurePlaceContext.level()
        var success = false
        val spread = 6
        for (t in 0..95) {
            val pos = blockPos.offset(
                rand.nextInt(spread) * 2 - spread,
                rand.nextInt(spread) * 2 - spread,
                rand.nextInt(spread) * 2 - spread
            )
            if (world.getBlockState(pos).isAir) {
                for (dir in Direction.entries.shuffled()) {
                    if (world.getBlockState(pos.offset(dir.normal)).`is`(BlockTags.SCULK_REPLACEABLE_WORLD_GEN)) {
                        world.setBlock(pos, config.state.trySetValue(BlockStateProperties.FACING, dir.opposite), 3)
                        success = true
                        break
                    }
                }
            }
        }
        return success
    }
}