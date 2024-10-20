package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.state.StateManager
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import org.teamvoided.dusk_autumn.block.not_blocks.TripleBlockSection

open class TripleTallPlantBlock(settings: Settings) : AbstractPlantBlock(settings) {

    init {
        this.defaultState = stateManager.defaultState.with(SECTION, TripleBlockSection.BOTTOM)
    }

    override fun getCodec(): MapCodec<out AbstractPlantBlock> = CODEC

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(SECTION)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val blockPos = ctx.blockPos
        val world = ctx.world
        if (
            blockPos.y < world.topY - 2 &&
            world.getBlockState(blockPos.up()).canReplace(ctx) &&
            world.getBlockState(blockPos.up(2)).canReplace(ctx)
        ) return super.getPlacementState(ctx)
        else {
            return null
        }
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val blockstate = state.get(SECTION)
        if (blockstate == TripleBlockSection.BOTTOM) {
            return super.canPlaceAt(state, world, pos)
        } else {
            val blockDown = world.getBlockState(pos.down())
            return blockDown.isOf(this) && blockDown.get(SECTION) == TripleBlockSection.getBelowSection(blockstate)
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
        val blockSection = state.get(SECTION)
        return if (
            (direction.axis == Direction.Axis.Y) &&
            !state.canPlaceAt(world, pos)
        ) Blocks.AIR.defaultState
        else super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun onPlaced(world: World, pos: BlockPos, state: BlockState, placer: LivingEntity?, itemStack: ItemStack) {
        val blockPosUp = pos.up()
        val blockPosUp2 = pos.up(2)
        world.setBlockState(
            blockPosUp, withWaterloggedState(
                world, blockPosUp,
                defaultState.with(SECTION, TripleBlockSection.MIDDLE)
            ), 3
        )
        world.setBlockState(
            blockPosUp2, withWaterloggedState(
                world, blockPosUp2,
                defaultState.with(SECTION, TripleBlockSection.TOP)
            ), 3
        )
    }

    override fun onBreak(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity): BlockState {
        if (!world.isClient) {
            if (!player.isCreative) {
                dropStacks(state, world, pos, null, player, player.mainHandStack)
            }
            breakOthers(world, pos, state, player)
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

    override fun getRenderingSeed(state: BlockState, pos: BlockPos): Long {
        return MathHelper.hashCode(
            pos.x,
            pos.down(if (state.get(SECTION) == TripleBlockSection.BOTTOM) 0 else 1).y,
            pos.z
        )
    }

    companion object {
        private val CODEC: MapCodec<TripleTallPlantBlock> = createCodec(::TripleTallPlantBlock)
        val SECTION: EnumProperty<TripleBlockSection> = EnumProperty.of("section", TripleBlockSection::class.java)

        fun withWaterloggedState(world: WorldView, pos: BlockPos, state: BlockState): BlockState {
            return if (state.contains(Properties.WATERLOGGED))
                state.with(Properties.WATERLOGGED, world.isWater(pos))
            else state
        }

        fun breakOthers(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity?) {
            val blockSection = state.get(SECTION)
            when (blockSection) {

                TripleBlockSection.TOP -> {
                    val posBottom = pos.down(2)
                    val stateBottom = world.getBlockState(posBottom)
                    breakOther(world, pos.down(), world.getBlockState(pos.down()), player)
                    breakOther(world,posBottom, stateBottom, player)
                    if (player != null && !player.isCreative) {
                        dropStacks(stateBottom, world, posBottom, null, player, player.mainHandStack)
                    }
                }

                TripleBlockSection.MIDDLE -> {
                    val posBottom = pos.down()
                    val stateBottom = world.getBlockState(posBottom)
                    breakOther(world, pos.up(), world.getBlockState(pos.up()), player)
                    breakOther(world, pos.down(), world.getBlockState(pos.down()), player)
                    if (player != null && !player.isCreative) {
                        dropStacks(stateBottom, world, posBottom, null, player, player.mainHandStack)
                    }
                }

                TripleBlockSection.BOTTOM -> {
                    breakOther(world, pos.up(2), world.getBlockState(pos.up(2)), player)
                    breakOther(world, pos.up(), world.getBlockState(pos.up()), player)
                }
            }
        }

        private fun breakOther(world: World, breakPos: BlockPos, state: BlockState, player: PlayerEntity?) {
            val blockState = world.getBlockState(breakPos)
            if (blockState.isOf(state.block)) {
                val afterState =
                    if (blockState.fluidState.isOf(Fluids.WATER)) Blocks.WATER.defaultState
                    else Blocks.AIR.defaultState
                world.setBlockState(breakPos, afterState, 35)
                world.syncWorldEvent(player, 2001, breakPos, getRawIdFromState(blockState))
            }
        }
    }
}