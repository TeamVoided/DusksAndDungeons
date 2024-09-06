package org.teamvoided.dusk_autumn.block

import net.minecraft.block.*
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

class GalleryRoseBlock(settings: Settings) : Block(settings), Waterloggable, Fertilizable {
    init {
        this.defaultState = defaultState
            .with(SECTION, TripleBlockSection.TOP)
            .with(TallCrystalBlock.WATERLOGGED, false)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val world: WorldAccess = ctx.world
        val blockPos = ctx.blockPos
        return defaultState.with(WATERLOGGED, world.getFluidState(blockPos).fluid == Fluids.WATER)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val blockPosDown = pos.down()
        val blockState = world.getBlockState(blockPosDown)
        return blockState.isOf(this) || blockState.isSideSolidFullSquare(world, blockPosDown, Direction.UP)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (direction == Direction.DOWN) {
            if (!state.canPlaceAt(world, pos)) {
                world.scheduleBlockTick(pos, this, 1)
            }
        } else if (direction == Direction.UP) {
            return if (!neighborState.isOf(this)) {
                state.with(SECTION, TripleBlockSection.TOP)
            } else if (world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)) {
                state.with(SECTION, TripleBlockSection.BOTTOM)
            } else {
                state.with(SECTION, TripleBlockSection.MIDDLE)
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true)
        }
    }

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState): Boolean {
        return true
    }

    override fun canFertilize(world: World, random: RandomGenerator, pos: BlockPos, state: BlockState): Boolean {
        return true
    }

    override fun fertilize(world: ServerWorld, random: RandomGenerator, pos: BlockPos, state: BlockState) {
        var blockPos = pos.up()
        val length: Int = random.nextInt(5)

        var loop = 0
        while (loop <= length && world.getBlockState(blockPos).isIn(BlockTags.REPLACEABLE)) {
            world.setBlockState(blockPos, state)
            blockPos = blockPos.up()
            ++loop
        }
    }

//    override fun fertilize(world: ServerWorld, random: RandomGenerator, pos: BlockPos, state: BlockState) {
//        dropStack(world, pos, ItemStack(this))
//    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    internal enum class TripleBlockSection(val direction: Direction) : StringIdentifiable {
        TOP(Direction.DOWN),
        MIDDLE(Direction.UP),
        BOTTOM(Direction.UP);

        fun getDirection(): Direction = this.direction

        override fun toString(): String = this.asString()

        override fun asString(): String {
            return if (this == TOP) "top" else if (this == MIDDLE) "middle" else "bottom"
        }

        fun getSection(): TripleBlockSection {
            return if (this == TOP) TOP else if (this == MIDDLE) MIDDLE else BOTTOM
        }
    }

    companion object {
        private val SECTION: EnumProperty<TripleBlockSection> =
            EnumProperty.of("section", TripleBlockSection::class.java)
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
    }
}