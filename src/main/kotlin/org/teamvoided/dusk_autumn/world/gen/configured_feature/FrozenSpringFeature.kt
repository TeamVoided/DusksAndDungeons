//package org.teamvoided.dusk_autumn.world.gen.configured_feature
//
//import com.mojang.serialization.Codec
//import net.minecraft.util.math.Direction
//import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FrozenSpringConfig
//import net.minecraft.world.gen.feature.Feature
//import net.minecraft.world.gen.feature.util.FeatureContext
//
//class FrozenSpringFeature(codec: Codec<FrozenSpringConfig>) : Feature<FrozenSpringConfig>(codec) {
//    override fun place(context: FeatureContext<FrozenSpringConfig>): Boolean {
//        val frozenSpringFeatureConfig = context.config
//        val structureWorldAccess = context.world
//        val blockPos = context.origin
//        if (!structureWorldAccess.getBlockState(blockPos.up()).isIn(frozenSpringFeatureConfig.allowedPlace)) {
//            return false
//        } else if (
//            frozenSpringFeatureConfig.hasExposedDownFace &&
//            !structureWorldAccess.getBlockState(blockPos.down()).isIn(frozenSpringFeatureConfig.allowedPlace)
//        ) {
//            return false
//        } else {
//            val blockState = structureWorldAccess.getBlockState(blockPos)
//            if (!blockState.isAir && !blockState.isIn(frozenSpringFeatureConfig.allowedPlace)) {
//                return false
//            } else {
//                val direction = mutableListOf(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST)
//                var isValidBlock = 0
//                var isReplaceableBlock: Int = direction.size
//                direction.forEach {
//                    if (structureWorldAccess.getBlockState(blockPos.offset(it))
//                            .isIn(frozenSpringFeatureConfig.allowedPlace)
//                    ) {
//                        ++isValidBlock
//                    }
//                    if (structureWorldAccess.getBlockState(blockPos.offset(it))
//                            .isIn(frozenSpringFeatureConfig.allowedReplacement)
//                    ) {
//                        --isReplaceableBlock
//                    }
//                }
//                if (isValidBlock == frozenSpringFeatureConfig.emptyFacesRequirement &&
//                    isReplaceableBlock == frozenSpringFeatureConfig.emptyFacesRequirement
//                ) {
//                    structureWorldAccess.setBlockState(blockPos, frozenSpringFeatureConfig.iceBlock, 2)
//                    return true
//                }
//                return false
//            }
//        }
//    }
//}