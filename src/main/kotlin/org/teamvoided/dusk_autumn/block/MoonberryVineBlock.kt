package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.function.ToIntFunction

class MoonberryVineBlock(settings: Settings) : AbstractLichenBlock(settings), Waterloggable, Fertilizable {
    private val spreadBehavior = LichenSpreadBehavior(this)

    public override fun getCodec(): MapCodec<MoonberryVineBlock> {
        return moonberryVineBlockMapCodec
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(WATERLOGGED, BERRIES)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        return context.stack.isOf(DuskItems.MOONBERRY_VINE)
    }

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState): Boolean {
        return state.get(BERRIES) < 2
    }

    override fun canFertilize(world: World, random: RandomGenerator, pos: BlockPos, state: BlockState): Boolean {
        return true
    }

    override fun fertilize(world: ServerWorld, random: RandomGenerator, pos: BlockPos, state: BlockState) {
        world.setBlockState(pos, state.with(BERRIES, state.get(BERRIES) + 1), 2)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun isTranslucent(state: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return state.fluidState.isEmpty
    }

    override fun getLichenSpreadBehavior(): LichenSpreadBehavior {
        return this.spreadBehavior
    }


    init {
        this.defaultState =
            defaultState.with(WATERLOGGED, false).with(BERRIES, 0)
    }

    companion object {
        val moonberryVineBlockMapCodec: MapCodec<MoonberryVineBlock> = method_54094 { settings: Settings ->
            MoonberryVineBlock(
                settings
            )
        }
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        val BERRIES: IntProperty = IntProperty.of("berries", 0, 2)
        fun getLuminanceSupplier(luminanceLow: Int, luminance: Int): ToIntFunction<BlockState> {
            return ToIntFunction { state: BlockState ->
                if (hasAnyDirection(state) && state.get(BERRIES) > 0) {
                    if (state.get(BERRIES) > 1) luminance
                    else luminanceLow
                } else 0
            }
        }
    }
}
