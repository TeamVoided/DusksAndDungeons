package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.ShapeContext
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.item.ItemPlacementContext
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.LightType
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import kotlin.math.min

class TEMP(settings: Settings?) : Block(settings) {
    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return when (type) {
            NavigationType.LAND -> state.get(LAYERS) as Int > 5
            NavigationType.WATER -> false
            NavigationType.AIR -> false
            else -> false
        }
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return LAYERS_TO_SHAPE[(state.get(LAYERS) as Int)]
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return LAYERS_TO_SHAPE[state.get(LAYERS) as Int - 1]
    }

    override fun getSidesShape(state: BlockState, world: BlockView, pos: BlockPos): VoxelShape {
        return LAYERS_TO_SHAPE[(state.get(LAYERS) as Int)]
    }

    override fun getCameraCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return LAYERS_TO_SHAPE[(state.get(LAYERS) as Int)]
    }

    override fun hasSidedTransparency(state: BlockState): Boolean {
        return true
    }

    override fun getAmbientOcclusionLightLevel(state: BlockState, world: BlockView, pos: BlockPos): Float {
        return if (state.get(LAYERS) as Int == MAX_LAYERS) 0.2f else 1.0f
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val blockState = world.getBlockState(pos.down())
        return if (blockState.isIn(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON)) {
            false
        } else if (blockState.isIn(BlockTags.SNOW_LAYER_CAN_SURVIVE_ON)) {
            true
        } else {
            isFaceFullSquare(blockState.getCollisionShape(world, pos.down()), Direction.UP) || blockState.isOf(
                this
            ) && blockState.get(LAYERS) as Int == MAX_LAYERS
        }
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        return if (!state.canPlaceAt(world, pos)) Blocks.AIR.defaultState else super.getStateForNeighborUpdate(
            state,
            direction,
            neighborState,
            world,
            pos,
            neighborPos
        )
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        if (world.getLightLevel(LightType.BLOCK, pos) > 11) {
            dropStacks(state, world, pos)
            world.removeBlock(pos, false)
        }
    }

    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        val i = state.get(LAYERS) as Int
        return if (context.stack.isOf(this.asItem()) && i < MAX_LAYERS) {
            if (context.canReplaceExisting()) {
                context.side == Direction.UP
            } else {
                true
            }
        } else {
            i == 1
        }
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val blockState = ctx.world.getBlockState(ctx.blockPos)
        if (blockState.isOf(this)) {
            val i = blockState.get(LAYERS)
            return blockState.with(
                LAYERS, (i + 1)
            )
        } else {
            return super.getPlacementState(ctx)
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(LAYERS)
    }

    init {
        this.defaultState = (stateManager.defaultState).with(LAYERS, 1)
    }

    companion object {
        const val MAX_LAYERS: Int = 8
        val LAYERS: IntProperty = Properties.LAYERS
        protected val LAYERS_TO_SHAPE: Array<VoxelShape> = arrayOf(
            VoxelShapes.empty(),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        )
        const val IMPASSABLE_HEIGHT: Int = 5
    }
}