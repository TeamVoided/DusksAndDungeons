package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemInteractionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import net.minecraft.world.event.GameEvent
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems

class MoonberryVineBlock(settings: Settings) : AbstractLichenBlock(settings), Waterloggable, Fertilizable {

    public override fun getCodec(): MapCodec<MoonberryVineBlock> = CODEC


    init {
        this.defaultState = defaultState.with(WATERLOGGED, false).with(BERRIES, 0)
    }


    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(WATERLOGGED, BERRIES)
    }

    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean =
        context.stack.isOf(DnDBlocks.MOONBERRY_VINE.asItem())

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState): Boolean = state.get(BERRIES) < 2


    override fun canFertilize(world: World, random: RandomGenerator, pos: BlockPos, state: BlockState): Boolean =
        world.isNight


    override fun fertilize(world: ServerWorld, random: RandomGenerator, pos: BlockPos, state: BlockState) {
        world.setBlockState(pos, state.with(BERRIES, state.get(BERRIES) + 1), 2)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: WorldAccess, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }
        return if (!hasAnyDirection(state)) {
            Blocks.AIR.defaultState
        } else {
            if (hasDirection(state, direction) &&
                !canGrowOnOrOveride(world, direction, neighborPos, neighborState)
            ) disableDirection(state, getProperty(direction)) else state
        }
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        var bl = false
        val var5 = DIRECTIONS
        val var6 = var5.size

        for (var7 in 0 until var6) {
            val direction = var5[var7]
            if (hasDirection(state, direction)) {
                val blockPos = pos.offset(direction)
                if (!canGrowOnOrOveride(world, direction, blockPos, world.getBlockState(blockPos))
                ) {
                    return false
                }

                bl = true
            }
        }

        return bl
    }

    override fun canPlace(view: BlockView, state: BlockState, pos: BlockPos, dir: Direction): Boolean {
        if (this.canHaveDirection(dir) && (!state.isOf(this) || !hasDirection(state, dir))) {
            val blockPos = pos.offset(dir)
            return canGrowOnOrOveride(view, dir, blockPos, view.getBlockState(blockPos))
        } else {
            return false
        }
    }

    private fun canGrowOnOrOveride(world: BlockView, direction: Direction, pos: BlockPos, state: BlockState): Boolean {
        return (isFaceFullSquare(state.getSidesShape(world, pos), direction.opposite)
                || isFaceFullSquare(state.getCollisionShape(world, pos), direction.opposite)
                || world.getBlockState(pos).isIn(DnDBlockTags.MOONBERRY_CAN_PLACE_ON))
    }

    override fun getFluidState(state: BlockState): FluidState =
        if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)


    override fun onInteract(
        stack: ItemStack,
        state: BlockState,
        world: World,
        pos: BlockPos,
        entity: PlayerEntity,
        hand: Hand,
        hitResult: BlockHitResult
    ): ItemInteractionResult {
        val bl = state.get(BERRIES) == 2
        return if (!bl && stack.isOf(Items.BONE_MEAL)) ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION
        else super.onInteract(stack, state, world, pos, entity, hand, hitResult)
    }

    override fun onUse(
        state: BlockState, world: World, pos: BlockPos, entity: PlayerEntity, hitResult: BlockHitResult
    ): ActionResult {
        val i = state.get(BERRIES)
        val bl = i == 3
        if (i > 1) {
            val j = 1 + world.random.nextInt(2)
            dropStack(world, pos, ItemStack(DnDItems.MOONBERRIES, j + (if (bl) 1 else 0)))
            world.playSound(
                null,
                pos,
                SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,
                SoundCategory.BLOCKS,
                1.0f,
                0.8f + world.random.nextFloat() * 0.4f
            )
            val blockState = state.with(BERRIES, 0)
            world.setBlockState(pos, blockState, 2)
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.create(entity, blockState))
            return ActionResult.success(world.isClient)
        } else {
            return super.onUse(state, world, pos, entity, hitResult)
        }
    }

    override fun getRandomTicks(state: BlockState): Boolean {
        return state.get(BERRIES) < 2
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        val berries = state.get(BERRIES)
        if (berries < 3 && random.nextInt(5) == 0 && world.isNight) {
            val blockState = state.with(BERRIES, berries + 1)
            world.setBlockState(pos, blockState, 2)
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.create(blockState))
        }
    }

    override fun getLichenSpreadBehavior(): LichenSpreadBehavior {
        return LichenSpreadBehavior(this)
    }

    companion object {
        val CODEC: MapCodec<MoonberryVineBlock> = createCodec(::MoonberryVineBlock)
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        val BERRIES: IntProperty = IntProperty.of("berries", 0, 2)
        fun getLuminanceSupplier(luminanceLow: Int, luminance: Int): (BlockState) -> Int {
            return { state ->
                if (hasAnyDirection(state) && state.get(BERRIES) > 0) {
                    if (state.get(BERRIES) > 1) luminance
                    else luminanceLow
                } else 0
            }
        }
    }
}
