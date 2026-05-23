package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.PipeBlock
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.item.ItemStack
import net.minecraft.tags.ItemTags
import net.minecraft.sounds.SoundSource
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import org.teamvoided.dusks_and_dungeons.util.rotateColumn

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
        hitResult: BlockHitResult
    ): ItemInteractionResult {
        val getHit = this.getRelativeHitCoordinates(hitResult, state, pos)
        return if (getHit != null && !stack.isEmpty && entity.abilities.mayBuild && stack.`is`(ItemTags.AXES)) {
            if (state.getValue(WATERLOGGED)) world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
            setBlockState(world, pos, entity, getHit)
            world.neighborChanged(pos, this, pos)
            ItemInteractionResult.sidedSuccess(world.isClientSide)
        } else super.useItemOn(stack, state, world, pos, entity, hand, hitResult)
    }

    private fun getRelativeHitCoordinates(
        blockHitResult: BlockHitResult, state: BlockState, pos: BlockPos
    ): BlockState? {
        if (howManyTrueSides(state) <= 1) return null
        val vec3d: Vec3 = blockHitResult.location.subtract(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
        val directionAxis = state.getValue(AXIS)
        val north: Direction
        val east: Direction
        val up: Direction
        var directionHit: Direction = blockHitResult.direction
        when (directionAxis) {
            Direction.Axis.X -> {
                north = Direction.NORTH
                east = Direction.UP
                up = Direction.WEST
                if ((directionHit != east && directionHit != east.opposite) &&
                    (vec3d.y > special1 && vec3d.y < special2) &&
                    (vec3d.z > special1 && vec3d.z < special2)
                ) directionHit = directionHit.opposite
            }

            Direction.Axis.Y -> {
                north = Direction.NORTH
                east = Direction.EAST
                up = Direction.UP
                if ((directionHit != up && directionHit != up.opposite) &&
                    (vec3d.x > special1 && vec3d.x < special2) &&
                    (vec3d.z > special1 && vec3d.z < special2)
                ) directionHit = directionHit.opposite
            }

            Direction.Axis.Z -> {
                north = Direction.DOWN
                east = Direction.EAST
                up = Direction.SOUTH
                if ((directionHit != north && directionHit != north.opposite) &&
                    (vec3d.x > special1 && vec3d.x < special2) &&
                    (vec3d.y > special1 && vec3d.y < special2)
                ) directionHit = directionHit.opposite
            }

            else -> throw MatchException(
                "somehow managed to give an invalid axis for hollow logs",
                null as Throwable?
            )
        }

        when (directionHit) {
            north -> return stateOrNull(NORTH, state)
            north.opposite -> return stateOrNull(SOUTH, state)
            east -> return stateOrNull(EAST, state)
            east.opposite -> return stateOrNull(WEST, state)
            up, up.opposite -> {
                return when (directionAxis) {
                    Direction.Axis.X -> {
                        upDownSurfaceCase(vec3d.y, vec3d.z, state)
                    }

                    Direction.Axis.Y -> {
                        upDownSurfaceCase(vec3d.x, vec3d.z, state)
                    }

                    Direction.Axis.Z -> {
                        upDownSurfaceCase(vec3d.x, vec3d.y, state)
                    }
                }
            }

            else -> {
                throw MatchException(
                    "somehow managed to give an invalid side for hollow logs, thrower is $directionHit, north $north, east $east, up $up",
                    null as Throwable?
                )
            }
        }
    }

    fun upDownSurfaceCase(x: Double, z: Double, state: BlockState): BlockState? {
        return if (state.getValue(NORTH) == true && x > z && x < (1 - z)) {
            state.setValue(NORTH, false)
        } else if (state.getValue(SOUTH) == true && x < z && x > (1 - z)) {
            state.setValue(SOUTH, false)
        } else if (state.getValue(EAST) == true && x > z && x > (1 - z)) {
            state.setValue(EAST, false)
        } else if (state.getValue(WEST) == true && x < z && x < (1 - z)) {
            state.setValue(WEST, false)
        } else null
    }

    fun stateOrNull(direction: Property<Boolean>, state: BlockState): BlockState? {
        return if (state.getValue(direction) == false) null
        else {
            state.setValue(direction, false)
        }
    }

    private fun howManyTrueSides(state: BlockState): Int {
        return listOf(
            (state.getValue(NORTH) == true),
            (state.getValue(SOUTH) == true),
            (state.getValue(EAST) == true),
            (state.getValue(WEST) == true)
        ).count {
            it
        }
    }

    private fun setBlockState(
        world: Level,
        pos: BlockPos,
        player: Player,
        state: BlockState
    ) {
        if (!world.isClientSide) {
            val soundEvent = SoundEvents.AXE_STRIP
            world.setBlockAndUpdate(pos, state)
            world.playSound(null as Player?, pos, soundEvent, SoundSource.BLOCKS, 1.0f, 1.0f)
            world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos)
        }
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        var shape = Shapes.empty()
        if (state.getValue(NORTH)) shape = Shapes.or(shape, NORTH_SHAPE)
        if (state.getValue(SOUTH)) shape = Shapes.or(shape, SOUTH_SHAPE)
        if (state.getValue(EAST)) shape = Shapes.or(shape, EAST_SHAPE)
        if (state.getValue(WEST)) shape = Shapes.or(shape, WEST_SHAPE)
        return shape.rotateColumn(state.getValue(AXIS))
    }

    override fun getInteractionShape(state: BlockState, world: BlockGetter, pos: BlockPos): VoxelShape {
        return Shapes.empty()
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AXIS, NORTH, SOUTH, EAST, WEST, WATERLOGGED)
    }

    companion object {
        val NORTH = BlockStateProperties.NORTH
        val SOUTH = BlockStateProperties.SOUTH
        val EAST = BlockStateProperties.EAST
        val WEST = BlockStateProperties.WEST
        val DIRECTION_PROPERTIES = PipeBlock.PROPERTY_BY_DIRECTION
        fun getProperty(direction: Direction): BooleanProperty? {
            return DIRECTION_PROPERTIES[direction]
        }

        val NORTH_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 16.0, 2.0)
        val SOUTH_SHAPE: VoxelShape = box(0.0, 0.0, 14.0, 16.0, 16.0, 16.0)
        val EAST_SHAPE: VoxelShape = box(14.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        val WEST_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 2.0, 16.0, 16.0)
    }
}