package org.teamvoided.dusks_and_dungeons.block.candelabra

import com.mojang.serialization.MapCodec
import net.fabricmc.fabric.api.block.BlockPickInteractionAware
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.Containers.dropContents
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.DnDBlockStateProperties
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.canAddToCandelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.getCandelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.getSlotCount
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.spawnCandelabraParticles
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.tryAddToCandelabra
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity
import org.teamvoided.dusks_and_dungeons.block.entity.createTicker
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities

open class CandelabraBlock(properties: Properties) : AbstractCandleBlock(properties),
    SimpleWaterloggedBlock, EntityBlock, BlockPickInteractionAware {

    override fun codec(): MapCodec<out AbstractCandleBlock> = CODEC

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(CANDLES, 1)
                .setValue(LIT, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, FACING, CANDLES, LIT)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        level.getCandelabra(pos)?.let { be ->
            return be.dynamicShape
        }
        return Candelabra.getBaseShape(state)
    }

    override fun getCollisionShape(
        state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext,
    ): VoxelShape {
        level.getCandelabra(pos)?.let { be ->
            return be.dynamicCollisionShape
        }
        return Candelabra.getBaseShape(state)
    }

    // Particles
    override fun getParticleOffsets(state: BlockState): Iterable<Vec3> = EMPTY_OFFSETS

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        if (!state.getValue(AbstractCandleBlock.LIT)) return
        val be = level.getCandelabra(pos) ?: return

        spawnCandelabraParticles(be, Vec3(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()), level, random, state)
    }

    // Waterlogging
    override fun updateShape(
        state: BlockState, dir: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }
        return super.updateShape(state, dir, neighborState, level, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false)
        else super.getFluidState(state)
    }

    override fun placeLiquid(
        level: LevelAccessor, pos: BlockPos, block: BlockState, fluid: FluidState,
    ): Boolean {
        return if (!block.getValue(WATERLOGGED) && fluid.type === Fluids.WATER) {
            val state = block.setValue(WATERLOGGED, true)
            if (block.getValue(LIT)) {
                extinguish(null, state, level, pos)
            } else {
                level.setBlock(pos, state, UPDATE_ALL)
            }

            level.scheduleTick(pos, fluid.type, fluid.type.getTickDelay(level))
            true
        } else false
    }

    // Logic
    override fun canBeReplaced(state: BlockState, ctx: BlockPlaceContext): Boolean {
        return Candelabra.canReplace(ctx, state, this) || super.canBeReplaced(state, ctx)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        return canSupportCenter(world, pos.below(), Direction.UP)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val state = ctx.level.getBlockState(ctx.clickedPos)
        if (state.`is`(this) || state.`is`(getEmpty())) {
            return Candelabra.cycleShapedFromItem(withPropertiesOf(state), ctx.itemInHand)
        }
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos).type === Fluids.WATER
        return super.getStateForPlacement(ctx)
            ?.setValue(CANDLES, getSlotCount(ctx.itemInHand))
            ?.setValue(WATERLOGGED, waterlogged)
            ?.setValue(FACING, ctx.horizontalDirection.opposite)
    }

    override fun useItemOn(
        stack: ItemStack, state: BlockState, level: Level, pos: BlockPos,
        player: Player, hand: InteractionHand, hit: BlockHitResult,
    ): ItemInteractionResult {
        if (stack.isEmpty && player.abilities.mayBuild && state.getValue(CandleBlock.LIT)) {
            extinguish(player, state, level, pos)
            return ItemInteractionResult.sidedSuccess(level.isClientSide)
        }

        if (canAddToCandelabra(stack) && tryAddToCandelabra(level, pos, state, stack, player, hit)) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide)
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hit)
    }

    override fun getPickedStack(
        state: BlockState, level: BlockGetter, pos: BlockPos, player: Player, hit: HitResult,
    ): ItemStack {
        return Candelabra.getPickedBlock(player, state, getCloneItemStack(level as LevelReader, pos, state))
    }

    override fun canBeLit(state: BlockState): Boolean = !state.getValue(WATERLOGGED) && super.canBeLit(state)

    override fun onRemove(
        state: BlockState, level: Level, pos: BlockPos, otherState: BlockState, movedByPiston: Boolean,
    ) {
        if (!state.`is`(otherState.block)) {
            level.getCandelabra(pos)?.let { be ->
                dropContents(level, pos, be.getCandles())
            }
        }
        super.onRemove(state, level, pos, otherState, movedByPiston)
    }

    override fun setPlacedBy(
        level: Level, pos: BlockPos, state: BlockState, entity: LivingEntity?, stack: ItemStack,
    ) {
        super.setPlacedBy(level, pos, state, entity, stack)
        level.getCandelabra(pos)?.updateStateCache(level)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
    }

    override fun triggerEvent(state: BlockState, level: Level, pos: BlockPos, id: Int, data: Int): Boolean {
        super.triggerEvent(state, level, pos, id, data)
        return level.getBlockEntity(pos)?.triggerEvent(id, data) ?: false
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity = CandelabraBlockEntity(pos, state)

    override fun <T : BlockEntity> getTicker(
        level: Level, state: BlockState, be: BlockEntityType<T>,
    ): BlockEntityTicker<T>? {
        return createTicker(be, DnDBlockEntities.CANDELABRA, CandelabraBlockEntity::tick)
    }

    private var empty: EmptyCandelabraBlock? = null

    @Suppress("DEPRECATION")
    fun getEmpty(): EmptyCandelabraBlock {
        if (empty == null) {
            empty = EmptyCandelabraBlock.FULL_TO_EMPTY[this]
        }
        return empty ?: error("Candelabra(${builtInRegistryHolder()}) does not have empty variant registered!")
    }

    companion object {

        val CODEC: MapCodec<CandelabraBlock> = simpleCodec(::CandelabraBlock)

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val CANDLES = DnDBlockStateProperties.CANDLES
        val LIT: BooleanProperty = BlockStateProperties.LIT

        val EMPTY_OFFSETS = listOf<Vec3>()

        @JvmStatic
        fun canLiteCandelabra(state: BlockState): Boolean {
            return state.`is`(DnDBlockTags.CANDELABRAS) { it.hasProperty(LIT) && it.hasProperty(WATERLOGGED) }
                    && !state.getValue(LIT) && !state.getValue(WATERLOGGED)
        }

    }
}
