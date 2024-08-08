package org.teamvoided.dusk_autumn.world.gen.configured_feature

import com.mojang.serialization.Codec
import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap
import net.minecraft.block.Block.NeighborGroup
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.DoorBlock
import net.minecraft.block.FluidFillable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FrozenSpringConfig

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
            spreadIceOrDrop(world, config, blockPos, origin, 0)
        }
    }

    fun spreadIceOrDrop(
        world: StructureWorldAccess,
        config: FrozenSpringConfig,
        blockPos: BlockPos,
        columnOrigin: BlockPos,
        distanceFromColumn: Int
    ) {
        //add blocks in a diamond shape around given position until one goes over an overhang or goes out of range of config.horizontalRange, or until it loops config.spreadRange amount of times
        //if it goes over an overhang, run it back through placeColumnUntilBlocked and stop looping
        //ask ender how to add to a list unless it's a duplicate
        println("he he he haw")
    }

    private fun canFlowDownTo(
        world: BlockView,
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
                return if (!state.isIn(BlockTags.FEATURES_CANNOT_REPLACE)) {
                    !state.blocksMovement()
                } else {
                    false
                }
        }
    }

    private fun receivesFlow(
        face: Direction,
        world: BlockView,
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