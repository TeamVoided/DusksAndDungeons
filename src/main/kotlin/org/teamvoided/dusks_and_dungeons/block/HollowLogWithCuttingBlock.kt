package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.ItemTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.PipeBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.EntityCollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.util.rotateColumn
import org.teamvoided.voidlib.helpers.mc.isZ
import org.teamvoided.voidlib.helpers.mc.opposite
import org.teamvoided.voidlib.helpers.mc.rotateFlat90
import org.teamvoided.voidlib.helpers.mc.rotateOnAxis
import kotlin.math.round

open class HollowLogWithCuttingBlock(settings: Properties) : HollowLogBlock(settings) {
    open val special1: Double = 0.0625
    open val special2: Double = 0.9375

    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(AXIS, Direction.Axis.X)
                .setValue(NORTH, true)
                .setValue(SOUTH, true)
                .setValue(EAST, true)
                .setValue(WEST, true)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        world: Level,
        pos: BlockPos,
        entity: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult,
    ): ItemInteractionResult {
        // TODO rewrite this so you do the less expense checks first
        val hitState = this.getHitState(hitResult, state, pos)
        return if (hitState != null && !stack.isEmpty && entity.abilities.mayBuild && stack.`is`(ItemTags.AXES)) {
            if (state.getValue(WATERLOGGED)) world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
            setBlockState(world, pos, entity, hitState)
            world.neighborChanged(pos, this, pos)
            ItemInteractionResult.sidedSuccess(world.isClientSide)
        } else super.useItemOn(stack, state, world, pos, entity, hand, hitResult)
    }

    fun getHitState(hit: BlockHitResult, state: BlockState, pos: BlockPos): BlockState? {
        if (howManySidesExist(state) <= 1) return null
        var vec3 = hit.location.subtract(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
        val axis = state.getValue(AXIS)
        var direction = hit.direction

        // turns sideways logs upright
        // assume default state is y-axis
        if (axis.isHorizontal) {
            direction = direction.getClockWise(axis.opposite())
            vec3 = vec3.rotateOnAxis(axis)
        }

        val dirAxis = direction.axis

        var keyVec: Vec3

        // We round these so that we don't have to check for side length
        val edgeZ = round(vec3.z)
        val edgeX = round(vec3.x)

        if (dirAxis.isHorizontal) {
            // when horizontal keyVec is easy since we just check what direction is clicked
            // and based on what axis its on we either set x to 0.5 or the edge value
            keyVec = Vec3(
                if (dirAxis.isZ()) 0.5 else edgeX,
                0.5,
                if (dirAxis.isZ()) edgeZ else 0.5,
            )
        } else {
            // for vertical keyVec we do an X chart and add values to x and z based if it falls in one of the 4 sectors
            var x = 0.0
            var z = 0.0

            if (vec3.x > vec3.z) x += 0.5
            if (vec3.x > 1 - vec3.z) x += 0.5

            if (vec3.z > vec3.x) z += 0.5
            if (vec3.z > 1 - vec3.x) z += 0.5

            keyVec = Vec3(x, 0.5, z)
        }

        // An early escape
        // we check if the easy options have been taken already and if they have we simply exit
        // If not we assume that they cant be taken
        var side = SIDE_MAP[keyVec]
        if (side != null && state.getValue(side)) {
            return state.trySetValue(side, false)
        }

        if (dirAxis.isHorizontal) {
            // Basically the same check for faces but its now for edges of side faces
            keyVec = Vec3(
                if (dirAxis.isZ()) edgeX else 0.5,
                0.5,
                if (dirAxis.isZ()) 0.5 else edgeZ,
            )
        } else {
            // This is the biggest mess of the whole code
            // the point of it is to fix the problem of X shape leave tiny part of the corners not covered when the face is missing
            // this should be rewritten after I get a break
            val rots = if (keyVec.x == edgeX) {
                if (keyVec.x > 0.5) {
                    if (edgeZ > keyVec.z) 1 else 3
                } else {
                    if (edgeZ < keyVec.z) 1 else 3
                }
            } else if (keyVec.z == edgeZ) {
                if (keyVec.z > 0.5) {
                    if (edgeX > keyVec.x) 3 else 1
                } else {
                    if (edgeX < keyVec.x) 3 else 1
                }
            } else 0

            keyVec = keyVec.rotateFlat90(rots)
        }

        side = SIDE_MAP[keyVec]
        if (side != null && state.getValue(side)) {
            return state.trySetValue(side, false)
        }

        // This should never run so :)
        return null
    }

    fun howManySidesExist(state: BlockState): Int {
        return listOf(state.getValue(NORTH), state.getValue(SOUTH), state.getValue(EAST), state.getValue(WEST))
            .count { it }
    }

    private fun setBlockState(
        world: Level, pos: BlockPos, player: Player, state: BlockState,
    ) {
        if (!world.isClientSide) {
            val soundEvent = SoundEvents.AXE_STRIP
            world.setBlockAndUpdate(pos, state)
            world.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1.0f, 1.0f)
            world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos)
        }
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        if (context is EntityCollisionContext) {
            // TODO add custom hollow log item tags
//            if (context.isHoldingItem(DnDItemTags.HOLLOW_LOGS)){
            val entity = context.entity
            if (entity is LivingEntity) {
                val item = entity.mainHandItem.item
                if (item is BlockItem && item.block.defaultBlockState().`is`(DnDBlockTags.HOLLOW_LOGS)) {
                    return Shapes.block()
                }
            }
            //}
        }

        var shape = Shapes.empty()
        // TODO turn this in to a map lookup with binary keys
        if (state.getValue(NORTH)) shape = Shapes.or(shape, NORTH_SHAPE)
        if (state.getValue(SOUTH)) shape = Shapes.or(shape, SOUTH_SHAPE)
        if (state.getValue(EAST)) shape = Shapes.or(shape, EAST_SHAPE)
        if (state.getValue(WEST)) shape = Shapes.or(shape, WEST_SHAPE)
        if (shape.isEmpty) return Shapes.block()
        return shape.rotateColumn(state.getValue(AXIS))
    }

    override fun getInteractionShape(state: BlockState, world: BlockGetter, pos: BlockPos): VoxelShape = Shapes.empty()

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AXIS, NORTH, SOUTH, EAST, WEST, WATERLOGGED)
    }

    companion object {

        val NORTH: BooleanProperty = BlockStateProperties.NORTH
        val SOUTH: BooleanProperty = BlockStateProperties.SOUTH
        val EAST: BooleanProperty = BlockStateProperties.EAST
        val WEST: BooleanProperty = BlockStateProperties.WEST
        val DIRECTION_PROPERTIES: Map<Direction, BooleanProperty> = PipeBlock.PROPERTY_BY_DIRECTION
        fun getProperty(direction: Direction): BooleanProperty? = DIRECTION_PROPERTIES[direction]

        val SIDE_MAP = mapOf(
            Vec3(1.0, 0.5, 0.5) to EAST,
            Vec3(0.0, 0.5, 0.5) to WEST,
            Vec3(0.5, 0.5, 1.0) to SOUTH,
            Vec3(0.5, 0.5, 0.0) to NORTH,
        )

        val NORTH_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 16.0, 2.0)
        val SOUTH_SHAPE: VoxelShape = box(0.0, 0.0, 14.0, 16.0, 16.0, 16.0)
        val EAST_SHAPE: VoxelShape = box(14.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        val WEST_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 2.0, 16.0, 16.0)

    }
}