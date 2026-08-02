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
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.EntityCollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.misc.DnDLevelEvents.CUT_HOLLOW_LOG
import org.teamvoided.dusks_and_dungeons.util.dndLevelEvent
import org.teamvoided.dusks_and_dungeons.util.rotateColumn
import org.teamvoided.voidlib.helpers.mc.isZ
import org.teamvoided.voidlib.helpers.mc.opposite
import org.teamvoided.voidlib.helpers.mc.rotateFlat90
import org.teamvoided.voidlib.helpers.mc.rotateOnAxis
import kotlin.math.round

open class CuttableHollowLogBlock(settings: Properties) : HollowLogBlock(settings) {
    open val shapeMap: Map<Direction.Axis, Array<VoxelShape>> =
        crateShapeMap(NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE)

    init {
        registerDefaultState(
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
        stack: ItemStack, state: BlockState, level: Level, pos: BlockPos,
        player: Player, hand: InteractionHand, hit: BlockHitResult,
    ): ItemInteractionResult {
        if (!stack.isEmpty && player.abilities.mayBuild && stack.`is`(ItemTags.AXES)) {
            val hitData = getHitState(hit, state, pos)
            if (hitData != null) {
                if (state.getValue(WATERLOGGED)) {
                    level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
                }
                level.updateBlock(pos, hitData, player)
                level.neighborChanged(pos, this, pos)
                return ItemInteractionResult.sidedSuccess(level.isClientSide)
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hit)
    }

    fun getHitState(hit: BlockHitResult, state: BlockState, pos: BlockPos): Pair<BlockState, BooleanProperty>? {
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
            return state.trySetValue(side, false) to side
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
            return state.trySetValue(side, false) to side
        }

        // This should never run so :)
        return null
    }

    fun howManySidesExist(state: BlockState): Int {
        return listOf(
            state.getValue(NORTH), state.getValue(SOUTH), state.getValue(EAST), state.getValue(WEST)
        ).count { it }
    }

    fun Level.updateBlock(pos: BlockPos, hitData: Pair<BlockState, BooleanProperty>, player: Player?) {
        if (!isClientSide) {
            setBlockAndUpdate(pos, hitData.first)
            playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f)
            gameEvent(player, GameEvent.BLOCK_CHANGE, pos)
            dndLevelEvent(CUT_HOLLOW_LOG, pos, PROP_ARR[hitData.second]!!)
        }
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        if (ctx is EntityCollisionContext) {
            // TODO add hollow log item tags
//            if (context.isHoldingItem(DnDItemTags.HOLLOW_LOGS)){
            val entity = ctx.entity
            if (entity is LivingEntity) {
                val item = entity.mainHandItem.item
                if (item is BlockItem && item.block.defaultBlockState().`is`(DnDBlockTags.HOLLOW_LOGS)) {
                    return Shapes.block()
                }
            }
            //}
        }

        var bitKey = 0
        for ((idx, dir) in DIRECTIONS.withIndex()) {
            if (state.getValue(DIRECTION_PROPERTIES[dir]!!)) {
                bitKey = bitKey or (1 shl idx)
            }
        }
        if (bitKey == 0) return Shapes.block()

        return shapeMap[state.getValue(AXIS)]?.get(bitKey) ?: Shapes.block()
    }

    override fun getInteractionShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape = Shapes.empty()

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AXIS, NORTH, SOUTH, EAST, WEST, WATERLOGGED)
    }

    companion object {

        val NORTH: BooleanProperty = BlockStateProperties.NORTH
        val SOUTH: BooleanProperty = BlockStateProperties.SOUTH
        val EAST: BooleanProperty = BlockStateProperties.EAST
        val WEST: BooleanProperty = BlockStateProperties.WEST
        val PROP_ARR = mapOf(NORTH to 1, EAST to 2, SOUTH to 4, WEST to 8)

        val AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.AXIS
        val DIRECTION_PROPERTIES: Map<Direction, BooleanProperty> = PipeBlock.PROPERTY_BY_DIRECTION
        val DIRECTIONS = Direction.Plane.HORIZONTAL.stream().toList().toList()
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

        fun crateShapeMap(
            north: VoxelShape, east: VoxelShape, south: VoxelShape, west: VoxelShape,
        ): Map<Direction.Axis, Array<VoxelShape>> {
            val array = arrayOfNulls<VoxelShape>(32)

            var shape: VoxelShape
            for (idx in 0..<32) {
                shape = Shapes.empty()
                if ((idx and 1) == 1) shape = Shapes.or(shape, north)
                if (((idx shr 1) and 1) == 1) shape = Shapes.or(shape, east)
                if (((idx shr 2) and 1) == 1) shape = Shapes.or(shape, south)
                if (((idx shr 3) and 1) == 1) shape = Shapes.or(shape, west)

                array[idx] = shape
            }

            return Direction.Axis.entries.associateWith {
                array.mapNotNull { shape -> shape?.rotateColumn(it) }.toTypedArray()
            }
        }
    }
}