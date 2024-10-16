//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package net.minecraft.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.state.StateManager
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.state.property.Property
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

open class TallPlantBlock(settings: Settings?) : AbstractPlantBlock(settings) {
    public override fun getCodec(): MapCodec<out TallPlantBlock> {
        return CODEC
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        val doubleBlockHalf = state.get(HALF) as DoubleBlockHalf
        return if ((direction.axis === Direction.Axis.Y) && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP) && (!neighborState.isOf(
                this
            ) || neighborState.get(HALF) == doubleBlockHalf)
        ) {
            Blocks.AIR.defaultState
        } else {
            if (doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(
                    world,
                    pos
                )
            ) Blocks.AIR.defaultState else super.getStateForNeighborUpdate(
                state,
                direction,
                neighborState,
                world,
                pos,
                neighborPos
            )
        }
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val blockPos = ctx.blockPos
        val world = ctx.world
        return if (blockPos.y < world.topY - 1 && world.getBlockState(blockPos.up())
                .canReplace(ctx)
        ) super.getPlacementState(ctx) else null
    }

    override fun onPlaced(world: World, pos: BlockPos, state: BlockState, placer: LivingEntity?, itemStack: ItemStack) {
        val blockPos = pos.up()
        world.setBlockState(
            blockPos, withWaterloggedState(
                world, blockPos,
                defaultState.with(HALF, DoubleBlockHalf.UPPER) as BlockState
            ), 3
        )
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        if (state.get(HALF) != DoubleBlockHalf.UPPER) {
            return super.canPlaceAt(state, world, pos)
        } else {
            val blockState = world.getBlockState(pos.down())
            return blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER
        }
    }

    override fun onBreak(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity): BlockState {
        if (!world.isClient) {
            if (player.isCreative) {
                breakBottomHalf(world, pos, state, player)
            } else {
                dropStacks(state, world, pos, null as BlockEntity?, player, player.mainHandStack)
            }
        }

        return super.onBreak(world, pos, state, player)
    }

    override fun afterBreak(
        world: World,
        player: PlayerEntity,
        pos: BlockPos,
        state: BlockState,
        blockEntity: BlockEntity?,
        stack: ItemStack
    ) {
        super.afterBreak(world, player, pos, Blocks.AIR.defaultState, blockEntity, stack)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(*arrayOf<Property<*>>(HALF))
    }

    override fun getRenderingSeed(state: BlockState, pos: BlockPos): Long {
        return MathHelper.hashCode(pos.x, pos.down(if (state.get(HALF) == DoubleBlockHalf.LOWER) 0 else 1).y, pos.z)
    }

    init {
        this.defaultState = (stateManager.defaultState as BlockState).with(HALF, DoubleBlockHalf.LOWER) as BlockState
    }

    companion object {
        val CODEC: MapCodec<TallPlantBlock> = createCodec { settings: Settings? ->
            TallPlantBlock(
                settings
            )
        }
        val HALF: EnumProperty<DoubleBlockHalf> = Properties.DOUBLE_BLOCK_HALF

        fun placeAt(world: WorldAccess, state: BlockState, pos: BlockPos, flags: Int) {
            val blockPos = pos.up()
            world.setBlockState(
                pos,
                withWaterloggedState(world, pos, state.with(HALF, DoubleBlockHalf.LOWER) as BlockState),
                flags
            )
            world.setBlockState(
                blockPos,
                withWaterloggedState(world, blockPos, state.with(HALF, DoubleBlockHalf.UPPER) as BlockState),
                flags
            )
        }

        fun withWaterloggedState(world: WorldView, pos: BlockPos?, state: BlockState): BlockState {
            return if (state.contains(Properties.WATERLOGGED)) state.with(
                Properties.WATERLOGGED,
                world.isWater(pos)
            ) as BlockState else state
        }

        protected fun breakBottomHalf(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity?) {
            val doubleBlockHalf = state.get(HALF) as DoubleBlockHalf
            if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
                val blockPos = pos.down()
                val blockState = world.getBlockState(blockPos)
                if (blockState.isOf(state.block) && blockState.get(HALF) == DoubleBlockHalf.LOWER) {
                    val blockState2 =
                        if (blockState.fluidState.isOf(Fluids.WATER)) Blocks.WATER.defaultState else Blocks.AIR.defaultState
                    world.setBlockState(blockPos, blockState2, 35)
                    world.syncWorldEvent(player, 2001, blockPos, getRawIdFromState(blockState))
                }
            }
        }
    }
}