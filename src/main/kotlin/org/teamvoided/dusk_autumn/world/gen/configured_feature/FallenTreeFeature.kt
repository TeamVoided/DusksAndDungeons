//package org.teamvoided.dusk_autumn.world.gen.configured_feature
//
//import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FallenTreeConfig
//import com.mojang.serialization.Codec
//import net.minecraft.world.gen.feature.Feature
//import net.minecraft.world.gen.feature.util.FeatureContext
//import kotlin.math.min
//
//open class FallenTreeFeature(codec: Codec<FallenTreeConfig>) :
//    Feature<FallenTreeConfig>(codec) {
//    override fun place(context: FeatureContext<FallenTreeConfig>): Boolean {
//        val structureWorldAccess = context.world
//        val fallenTreeConfig = context.config
//        val randomGenerator = context.random
//        val trunkLength = fallenTreeConfig.trunkLength()
//        val `is` = IntArray(trunkLength)
//        var j = 0
//
//        for (k in 0 until trunkLength) {
//            `is`[k] = (fallenTreeConfig.layers()[k]).height()[randomGenerator]
//            j += `is`[k]
//        }
//
//        if (j == 0) {
//            return false
//        } else {
//            val mutable = context.origin.mutableCopy()
//            val mutable2 = mutable.mutableCopy().move(fallenTreeConfig.direction())
//            var l = 0
//            while (l < j) {
//                if (!structureWorldAccess.testBlockState(mutable) { it.isIn(fallenTreeConfig.allowedPlacement) }) {
//                    truncate(`is`, j, l)
//                    break
//                }
//
//                mutable2.move(fallenTreeConfig.direction())
//                ++l
//            }
//
//            l = 0
//            while (l < trunkLength) {
//                val m = `is`[l]
//                if (m != 0) {
//                    val layer = fallenTreeConfig.layers()[l]
//
//                    for (n in 0 until m) {
//                        structureWorldAccess.setBlockState(
//                            mutable,
//                            layer.state().getBlockState(randomGenerator, mutable),
//                            2
//                        )
//                        mutable.move(fallenTreeConfig.direction())
//                    }
//                }
//                ++l
//            }
//
//            return true
//        }
//    }
//
//    companion object {
//        private fun truncate(layerHeights: IntArray, columnHeight: Int, height: Int) {
//            var i = columnHeight - height
//            val j = -1
//            val k = layerHeights.size - 1
//            val l = -1
//
//            var m = k
//            while (m != l && i > 0) {
//                val n = layerHeights[m]
//                val o = min(n.toDouble(), i.toDouble()).toInt()
//                i -= o
//                layerHeights[m] -= o
//                m += j
//            }
//        }
//    }
//}