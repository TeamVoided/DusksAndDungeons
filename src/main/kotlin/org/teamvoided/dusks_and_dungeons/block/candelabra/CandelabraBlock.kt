package org.teamvoided.dusks_and_dungeons.block.candelabra

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.BlockItemStateProperties
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
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.DnDBlockStateProperties
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.canAddToCandelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.getRotations
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.spawnCandelabraParticles
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.tryAddToCandelabra
import org.teamvoided.dusks_and_dungeons.block.entity.CandelabraBlockEntity
import org.teamvoided.dusks_and_dungeons.block.entity.createTicker
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities
import org.teamvoided.dusks_and_dungeons.util.spawnCandleParticles
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootPlacer.Companion.invert
import org.teamvoided.voidlib.helpers.mc.rotateFlat90
import kotlin.jvm.optionals.getOrNull

open class CandelabraBlock(properties: Properties) : AbstractCandleBlock(properties),
    SimpleWaterloggedBlock, EntityBlock {

    override fun codec(): MapCodec<out AbstractCandleBlock> = CODEC

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(HORIZONTAL_AXIS, Direction.Axis.X)
                .setValue(CANDLES, 1)
                .setValue(LIT, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, HORIZONTAL_AXIS, CANDLES, LIT)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        level.getBlockEntity(pos, DnDBlockEntities.CANDELABRA).getOrNull()?.let { be ->
            return be.dynamicShape
        }
        return Candelabra.getBaseShape(state)
    }

    override fun getCollisionShape(
        state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext,
    ): VoxelShape {
        level.getBlockEntity(pos, DnDBlockEntities.CANDELABRA).getOrNull()?.let { be ->
            return be.dynamicCollisionShape
        }
        return Candelabra.getBaseShape(state)
    }

    // Particles
    override fun getParticleOffsets(state: BlockState): Iterable<Vec3> {
        return CANDELABRA_PARTICLE_OFFSETS[state.getValue(HORIZONTAL_AXIS)]?.get(state.getValue(CANDLES))
            ?: RAW_OFFSETS[0]
    }

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        if (!state.getValue(AbstractCandleBlock.LIT)) return

        val be = level.getBlockEntity(pos, DnDBlockEntities.CANDELABRA).getOrNull()
        if (be == null) {
            for (it in getParticleOffsets(state)) {
                spawnParticles(level, it.add(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()), random)
            }
            return
        }
        spawnCandelabraParticles(be, Vec3(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()), level, random, state)
    }

    private fun spawnParticles(level: Level, offset: Vec3, random: RandomSource) {
        level.spawnCandleParticles(offset, random)
    }

    // Waterlogging
    override fun updateShape(
        state: BlockState, dir: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }
        level.updateBECache(pos)
        return super.updateShape(state, dir, neighborState, level, pos, neighborPos)
    }

    fun LevelAccessor.updateBECache(pos: BlockPos) {
        getBlockEntity(pos, DnDBlockEntities.CANDELABRA)?.getOrNull()?.updateStateCache()
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
    override fun canBeReplaced(state: BlockState, context: BlockPlaceContext): Boolean {
        return (!context.isSecondaryUseActive && context.itemInHand.item === asItem() && state.getValue(CANDLES) < 5) ||
                super.canBeReplaced(state, context)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        return canSupportCenter(world, pos.below(), Direction.UP) && !world.getBlockState(pos.below()).`is`(this)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val state = ctx.level.getBlockState(ctx.clickedPos)
        if (state.`is`(this)) {
            return state.cycle(CANDLES)
        }
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos).type === Fluids.WATER
        return super.getStateForPlacement(ctx)
            ?.setValue(CANDLES, 1)
            ?.setValue(WATERLOGGED, waterlogged)
            ?.setValue(HORIZONTAL_AXIS, ctx.horizontalDirection.axis.invert())
    }

    override fun useItemOn(
        stack: ItemStack, state: BlockState, level: Level, pos: BlockPos,
        player: Player, hand: InteractionHand, hit: BlockHitResult,
    ): ItemInteractionResult {
        if (stack.isEmpty && player.abilities.mayBuild && state.getValue(CandleBlock.LIT)) {
            extinguish(player, state, level, pos)
            return ItemInteractionResult.sidedSuccess(level.isClientSide)
        }

        if (canAddToCandelabra(stack) && tryAddToCandelabra(level, pos, stack, player)) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide)
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hit)
    }

    override fun getCloneItemStack(level: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        val stack = super.getCloneItemStack(level, pos, state)
        if (stack.isEmpty) {
            return stack
        }
        if (state.getValue(CANDLES) > 1) {
            stack.set(
                DataComponents.BLOCK_STATE,
                stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties(mapOf())).with(CANDLES, state)
            )
        }
        return stack
    }

    override fun canBeLit(state: BlockState): Boolean = !state.getValue(WATERLOGGED) && super.canBeLit(state)

    override fun onRemove(
        state: BlockState, level: Level, pos: BlockPos, otherState: BlockState, movedByPiston: Boolean,
    ) {
        Candelabra.dropContentsOnDestroy(state, otherState, level, pos)
        super.onRemove(state, level, pos, otherState, movedByPiston)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return when (rotation) {
            Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 -> when (state.getValue(HORIZONTAL_AXIS)) {
                Direction.Axis.Z -> state.setValue(HORIZONTAL_AXIS, Direction.Axis.X)
                Direction.Axis.X -> state.setValue(HORIZONTAL_AXIS, Direction.Axis.Z)
                else -> state
            }

            else -> state
        }
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

    companion object {

        val CODEC: MapCodec<CandelabraBlock> = simpleCodec(::CandelabraBlock)

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val HORIZONTAL_AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.HORIZONTAL_AXIS
        val CANDLES = DnDBlockStateProperties.CANDLES
        val LIT: BooleanProperty = BlockStateProperties.LIT

        val RAW_OFFSETS = listOf(
            listOf(Vec3(0.5, 1.0, 0.5)),
            listOf(Vec3(0.25, 1.0, 0.5), Vec3(0.75, 1.0, 0.5)),
            listOf(Vec3(0.5, 1.125, 0.5), Vec3(0.1875, 1.0, 0.5), Vec3(0.8125, 1.0, 0.5)),
            listOf(Vec3(0.1875, 1.0, 0.5), Vec3(0.8125, 1.0, 0.5), Vec3(0.5, 1.0, 0.1875), Vec3(0.5, 1.0, 0.8125)),
            listOf(
                Vec3(0.1875, 1.0, 0.5), Vec3(0.8125, 1.0, 0.5),
                Vec3(0.5, 1.125, 0.5),
                Vec3(0.5, 1.0, 0.1875), Vec3(0.5, 1.0, 0.8125)
            )
        )

        val CANDELABRA_PARTICLE_OFFSETS = HORIZONTAL_AXIS.possibleValues.associateWith { dir ->
            CANDLES.possibleValues.associateWith { count -> RAW_OFFSETS[count - 1].rotateFlat90(dir.getRotations()) }
        }

        @JvmStatic
        fun canLiteCandelabra(state: BlockState): Boolean {
            return state.`is`(DnDBlockTags.CANDELABRAS) { it.hasProperty(LIT) && it.hasProperty(WATERLOGGED) }
                    && !state.getValue(LIT) && !state.getValue(WATERLOGGED)
        }

    }
}
