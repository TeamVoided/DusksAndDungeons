package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ConnectingBlock
import net.minecraft.block.ShapeContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.ItemTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.state.property.Property
import net.minecraft.util.Hand
import net.minecraft.util.ItemInteractionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import org.teamvoided.dusk_autumn.util.rotateColumn

open class HollowLogBlockWithCutting(settings: Settings) : HollowLogBlock(settings) {
    open val special1: Double = 0.0625
    open val special2: Double = 0.9375

    init {
        this.defaultState = stateManager.defaultState
            .with(AXIS, Direction.Axis.X)
            .with(NORTH, true)
            .with(SOUTH, true)
            .with(EAST, true)
            .with(WEST, true)
            .with(WATERLOGGED, false)
    }

    override fun onInteract(
        stack: ItemStack,
        state: BlockState,
        world: World,
        pos: BlockPos,
        entity: PlayerEntity,
        hand: Hand,
        hitResult: BlockHitResult
    ): ItemInteractionResult {
        val getHit = this.getRelativeHitCoordinates(hitResult, state, pos)
        if (getHit != null && !stack.isEmpty && entity.abilities.allowModifyWorld && stack.isIn(ItemTags.AXES)) {
            println(state)
            println(getHit)
            setBlockState(world, pos, entity, getHit)
            return ItemInteractionResult.success(world.isClient)
        } else {
            return super.onInteract(stack, state, world, pos, entity, hand, hitResult)
        }
    }

    private fun getRelativeHitCoordinates(
        blockHitResult: BlockHitResult,
        state: BlockState,
        pos: BlockPos
    ): BlockState? {
        if (howManyTrueSides(state) <= 1) {
            return null
        }
        val vec3d: Vec3d = blockHitResult.pos.subtract(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
        val directionAxis = state.get(AXIS)
        val north: Direction
        val east: Direction
        val up: Direction
        var directionHit: Direction = blockHitResult.side
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
        return if (state.get(NORTH) == true && x > z && x < (1 - z)) {
            state.with(NORTH, false)
        } else if (state.get(SOUTH) == true && x < z && x > (1 - z)) {
            state.with(SOUTH, false)
        } else if (state.get(EAST) == true && x > z && x > (1 - z)) {
            state.with(EAST, false)
        } else if (state.get(WEST) == true && x < z && x < (1 - z)) {
            state.with(WEST, false)
        } else null
    }

    fun stateOrNull(direction: Property<Boolean>, state: BlockState): BlockState? {
        return if (state.get(direction) == false) null
        else state.with(direction, false)
    }

    private fun howManyTrueSides(state: BlockState): Int {
        return listOf(
            (state.get(NORTH) == true),
            (state.get(SOUTH) == true),
            (state.get(EAST) == true),
            (state.get(WEST) == true)
        ).count {
            it
        }
    }

    private fun setBlockState(
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        state: BlockState
    ) {
        if (!world.isClient) {
            val soundEvent = SoundEvents.ITEM_AXE_STRIP
            world.setBlockState(pos, state)
            world.playSound(null as PlayerEntity?, pos, soundEvent, SoundCategory.BLOCKS, 1.0f, 1.0f)
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos)
        }
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        var shape = VoxelShapes.empty()
        if (state.get(NORTH)) shape = VoxelShapes.union(shape, NORTH_SHAPE)
        if (state.get(SOUTH)) shape = VoxelShapes.union(shape, SOUTH_SHAPE)
        if (state.get(EAST)) shape = VoxelShapes.union(shape, EAST_SHAPE)
        if (state.get(WEST)) shape = VoxelShapes.union(shape, WEST_SHAPE)
        return shape.rotateColumn(state.get(AXIS))
    }

    override fun getRaycastShape(state: BlockState, world: BlockView, pos: BlockPos): VoxelShape {
        return if (howManyTrueSides(state) <= 4) super.getRaycastShape(state, world, pos)
        else VoxelShapes.empty()
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(AXIS, NORTH, SOUTH, EAST, WEST, WATERLOGGED)
    }

    companion object {
        val NORTH: BooleanProperty = Properties.NORTH
        val SOUTH: BooleanProperty = Properties.SOUTH
        val EAST: BooleanProperty = Properties.EAST
        val WEST: BooleanProperty = Properties.WEST
        val DIRECTION_PROPERTIES = ConnectingBlock.FACING_PROPERTIES
        fun getProperty(direction: Direction): BooleanProperty? {
            return DIRECTION_PROPERTIES[direction]
        }

        val NORTH_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 2.0)
        val SOUTH_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 14.0, 16.0, 16.0, 16.0)
        val EAST_SHAPE: VoxelShape = createCuboidShape(14.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        val WEST_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 2.0, 16.0, 16.0)
    }
}