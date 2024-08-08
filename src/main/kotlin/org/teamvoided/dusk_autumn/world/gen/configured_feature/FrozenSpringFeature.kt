package org.teamvoided.dusk_autumn.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.StructureWorldAccess
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FrozenSpringConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

class FrozenSpringFeature(codec: Codec<FrozenSpringConfig>) : Feature<FrozenSpringConfig>(codec) {
    override fun place(context: FeatureContext<FrozenSpringConfig>): Boolean {
        val frozenSpringFeatureConfig = context.config
        val structureWorldAccess = context.world
        val blockPos = context.origin
        if (!structureWorldAccess.getBlockState(blockPos.up()).isIn(frozenSpringFeatureConfig.allowedPlacement)) {
            return false
        } else if (
            frozenSpringFeatureConfig.hasExposedDownFace &&
            !structureWorldAccess.getBlockState(blockPos.down()).isIn(frozenSpringFeatureConfig.allowedPlacement)
        ) {
            return false
        } else {
            val blockState = structureWorldAccess.getBlockState(blockPos)
            if (!blockState.isAir && !blockState.isIn(frozenSpringFeatureConfig.allowedPlacement)) {
                return false
            } else {

                val direction =
                    mutableListOf(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.DOWN)
                var isValidBlock = 0
                var isReplaceableBlock = 0
                val blockPosSet: MutableSet<BlockPos> = HashSet()

                direction.forEach {
                    val offsetPos = blockPos.offset(it)
                    if (structureWorldAccess.getBlockState(offsetPos)
                            .isIn(frozenSpringFeatureConfig.allowedPlacement)
                    ) {
                        ++isValidBlock
                    }
                    if (structureWorldAccess.getBlockState(offsetPos)
                            .isIn(frozenSpringFeatureConfig.allowedReplacement)
                    ) {
                        blockPosSet.add(offsetPos)
                        ++isReplaceableBlock
                    }
                }
                if (
                    isValidBlock == 5 - frozenSpringFeatureConfig.emptyFacesRequirement &&
                    isReplaceableBlock == frozenSpringFeatureConfig.emptyFacesRequirement
                ) {
                    structureWorldAccess.setBlockState(blockPos, frozenSpringFeatureConfig.iceBlock, 2)
                    blockPosSet.forEach {
                        placeColumnUntilBlocked(structureWorldAccess, frozenSpringFeatureConfig, it, blockPos)
                    }
                    return true
                }
                return false
            }
        }
    }

    fun placeColumnUntilBlocked(
        world: StructureWorldAccess,
        config: FrozenSpringConfig,
        blockPos: BlockPos,
        origin: BlockPos
    ) {
        val iceBlock = config.iceBlock
        world.setBlockState(blockPos, iceBlock, 2)
        val blockPosDown = blockPos.down()
        val blockStateDown = world.getBlockState(blockPosDown)
        if (blockStateDown.isIn(config.allowedReplacement) || blockStateDown == iceBlock) {
            placeColumnUntilBlocked(world, config, blockPosDown, origin)
        } else if (blockStateDown.isSolid) {
            spreadIceOrDrop(world, config, blockPos, origin)
        }
    }

    fun spreadIceOrDrop(
        world: StructureWorldAccess,
        config: FrozenSpringConfig,
        blockPos: BlockPos,
        origin: BlockPos
    ) {
        //add blocks in a diamond shape around given position until one goes over an overhang or goes out of range of config.horizontalRange, or until it loops config.spreadRange amount of times
        //if it goes over an overhang, run it back through placeColumnUntilBlocked and stop looping
        //ask ender how to add to a list unless it's a duplicate
        println("he he he haw")
    }
}