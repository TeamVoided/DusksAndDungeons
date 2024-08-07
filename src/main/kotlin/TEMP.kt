package net.minecraft.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.world.gen.feature.util.FeatureContext

class SpringFeature(codec: Codec<SpringFeatureConfig>) :
    Feature<SpringFeatureConfig>(codec) {
    override fun place(context: FeatureContext<SpringFeatureConfig>): Boolean {
        val springFeatureConfig = context.config
        val structureWorldAccess = context.world
        val blockPos = context.origin
        if (!structureWorldAccess.getBlockState(blockPos.up()).isIn(springFeatureConfig.validBlocks)) {
            return false
        } else if (springFeatureConfig.requiresBlockBelow && !structureWorldAccess.getBlockState(blockPos.down())
                .isIn(springFeatureConfig.validBlocks)
        ) {
            return false
        } else {
            val blockState = structureWorldAccess.getBlockState(blockPos)
            if (!blockState.isAir && !blockState.isIn(springFeatureConfig.validBlocks)) {
                return false
            } else {
                var i = 0
                var j = 0
                if (structureWorldAccess.getBlockState(blockPos.west()).isIn(springFeatureConfig.validBlocks)) {
                    ++j
                }

                if (structureWorldAccess.getBlockState(blockPos.east()).isIn(springFeatureConfig.validBlocks)) {
                    ++j
                }

                if (structureWorldAccess.getBlockState(blockPos.north()).isIn(springFeatureConfig.validBlocks)) {
                    ++j
                }

                if (structureWorldAccess.getBlockState(blockPos.south()).isIn(springFeatureConfig.validBlocks)) {
                    ++j
                }

                if (structureWorldAccess.getBlockState(blockPos.down()).isIn(springFeatureConfig.validBlocks)) {
                    ++j
                }

                var k = 0
                if (structureWorldAccess.isAir(blockPos.west())) {
                    ++k
                }
                if (structureWorldAccess.isAir(blockPos.east())) {
                    ++k
                }
                if (structureWorldAccess.isAir(blockPos.north())) {
                    ++k
                }
                if (structureWorldAccess.isAir(blockPos.south())) {
                    ++k
                }
                if (structureWorldAccess.isAir(blockPos.down())) {
                    ++k
                }

                if (j == springFeatureConfig.rockCount && k == springFeatureConfig.holeCount) {
                    structureWorldAccess.setBlockState(blockPos, springFeatureConfig.state.blockState, 2)
                    structureWorldAccess.scheduleFluidTick(blockPos, springFeatureConfig.state.fluid, 0)
                    ++i
                }

                return i > 0
            }
        }
    }
}