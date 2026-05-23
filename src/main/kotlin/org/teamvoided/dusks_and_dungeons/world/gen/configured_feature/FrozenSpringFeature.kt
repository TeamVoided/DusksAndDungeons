package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.tags.BlockTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FrozenSpringConfig

class FrozenSpringFeature(codec: Codec<FrozenSpringConfig>) : Feature<FrozenSpringConfig>(codec) {
    override fun place(context: FeaturePlaceContext<FrozenSpringConfig>): Boolean {
        val frozenSpringFeatureConfig = context.config()
        val structureWorldAccess = context.level()
        val blockPos = context.origin()
        if (!structureWorldAccess.getBlockState(blockPos.above()).`is`(frozenSpringFeatureConfig.allowedPlacement)) {
            return false
        } else if (
            frozenSpringFeatureConfig.hasExposedDownFace &&
            !structureWorldAccess.getBlockState(blockPos.below()).`is`(frozenSpringFeatureConfig.allowedPlacement)
        ) {
            return false
        } else {
            val blockState = structureWorldAccess.getBlockState(blockPos)
            if (!blockState.isAir && !blockState.`is`(frozenSpringFeatureConfig.allowedPlacement)) {
                return false
            } else {

                val direction =
                    mutableListOf(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.DOWN)
                var isValidBlock = 0
                var isReplaceableBlock = 0
                val blockPosSet: MutableSet<BlockPos> = HashSet()

                direction.forEach {
                    val offsetPos = blockPos.relative(it)
                    if (structureWorldAccess.getBlockState(offsetPos)
                            .`is`(frozenSpringFeatureConfig.allowedPlacement)
                    ) {
                        ++isValidBlock
                    }
                    if (structureWorldAccess.getBlockState(offsetPos)
                            .`is`(frozenSpringFeatureConfig.allowedReplacement)
                    ) {
                        blockPosSet.add(offsetPos)
                        ++isReplaceableBlock
                    }
                }
                if (
                    isValidBlock == 5 - frozenSpringFeatureConfig.emptyFacesRequirement &&
                    isReplaceableBlock == frozenSpringFeatureConfig.emptyFacesRequirement
                ) {
                    structureWorldAccess.setBlock(blockPos, frozenSpringFeatureConfig.iceBlock, 2)
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
        world: WorldGenLevel,
        config: FrozenSpringConfig,
        blockPos: BlockPos,
        origin: BlockPos
    ) {
        val iceBlock = config.iceBlock
        world.setBlock(blockPos, iceBlock, 2)
        val blockPosDown = blockPos.below()
        val blockStateDown = world.getBlockState(blockPosDown)
        if (blockStateDown.`is`(config.allowedReplacement) || blockStateDown == iceBlock) {
            placeColumnUntilBlocked(world, config, blockPosDown, origin)
        } else if (blockStateDown.isSolid) {
            spreadIceOrDrop(world, config, blockPos, origin, 0)
        }
    }

    fun spreadIceOrDrop(
        world: WorldGenLevel,
        config: FrozenSpringConfig,
        blockPos: BlockPos,
        columnOrigin: BlockPos,
        distanceFromColumn: Int
    ) {
        //add blocks in a diamond shape around given position until one goes over an overhang or goes out of range of config.horizontalRange, or until it loops config.spreadRange amount of times
        //if it goes over an overhang, run it back through placeColumnUntilBlocked and stop looping
        //ask ender how to add to a list unless it's a duplicate

        //(ender) what ur looking for is a set its like a list but with no duplicates
        println("he he he haw")
    }

    private fun canFlowDownTo(
        world: BlockGetter,
        config: FrozenSpringConfig,
        blockPos: BlockPos,
        columnOrigin: BlockPos,
        distanceFromColumn: Int
    ): Boolean {
        val state = world.getBlockState(blockPos)
        return if (!this.receivesFlow(
                Direction.DOWN,
                world,
                blockPos,
                columnOrigin,
                distanceFromColumn
            )
        ) {
            false
        } else {
            if (state == config.iceBlock) true else
                return if (!state.`is`(BlockTags.FEATURES_CANNOT_REPLACE)) {
                    !state.blocksMotion()
                } else {
                    false
                }
        }
    }

    private fun receivesFlow(
        face: Direction,
        world: BlockGetter,
        pos: BlockPos,
        fromPos: BlockPos,
        distanceFromColumn: Int //was fromState:BlockState
    ): Boolean {
//        val state = world.getBlockState(pos)
//        val object2ByteLinkedOpenHashMap = if (!state.block.hasDynamicBounds() && !fromState.block.hasDynamicBounds()) {
//            FlowableFluid.OCCLUSION_CACHE.get()
//        } else {
//            null
//        }
//
//        val neighborGroup: NeighborGroup?
//        if (object2ByteLinkedOpenHashMap != null) {
//            neighborGroup = NeighborGroup(state, fromState, face)
//            val b = object2ByteLinkedOpenHashMap.getAndMoveToFirst(neighborGroup)
//            if (b.toInt() != 127) {
//                return b.toInt() != 0
//            }
//        } else {
//            neighborGroup = null
//        }
//
//        val voxelShape = state.getCollisionShape(world, pos)
//        val voxelShape2 = fromState.getCollisionShape(world, fromPos)
//        val bl = !VoxelShapes.adjacentSidesCoverSquare(voxelShape, voxelShape2, face)
//        if (object2ByteLinkedOpenHashMap != null) {
//            if (object2ByteLinkedOpenHashMap.size() == 200) {
//                object2ByteLinkedOpenHashMap.removeLastByte()
//            }
//
//            object2ByteLinkedOpenHashMap.putAndMoveToFirst(neighborGroup, (if (bl) 1 else 0).toByte())
//        }
//
//        return bl
        return false
    }
}