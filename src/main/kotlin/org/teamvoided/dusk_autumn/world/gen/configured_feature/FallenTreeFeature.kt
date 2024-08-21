package org.teamvoided.dusk_autumn.world.gen.configured_feature

import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FallenTreeConfig
import com.mojang.serialization.Codec
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

open class FallenTreeFeature(codec: Codec<FallenTreeConfig>) :
    Feature<FallenTreeConfig>(codec) {
    override fun place(context: FeatureContext<FallenTreeConfig>): Boolean {
        val structureWorldAccess = context.world
        val randomGenerator = context.random
        val origin = context.origin
        val direction = Direction.Type.HORIZONTAL.random(randomGenerator)
        val fallenTreeConfig = context.config
        val trunkLength = fallenTreeConfig.trunkLength.get(randomGenerator)
        val trunkDistance = fallenTreeConfig.trunkDistanceFromStump.get(randomGenerator)

        canPlace(trunkLength, trunkDistance, direction, fallenTreeConfig, structureWorldAccess, origin, randomGenerator)

        return true
    }

    fun canPlace(
        trunkLength: Int,
        extraDistance: Int,
        direction: Direction,
        config: FallenTreeConfig,
        world: StructureWorldAccess,
        origin: BlockPos,
        random: RandomGenerator
    ) {
        val fall = config.trunkVerticalRange
        if (world.getBlockState(origin).isIn(config.allowedPlacement)) {
            val bottomBase = origin.offset(direction, extraDistance)
            val bottomTop = origin.offset(direction, trunkLength + extraDistance)
        }

//            return null
    }

    companion object {}
}