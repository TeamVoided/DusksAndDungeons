package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.block.DoubleBlockProperties.PropertySource
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.entity.ChestBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.block.entity.ChestOSoulsBlockEntity
import org.teamvoided.dusk_autumn.init.DnDBlockEntities

class ChestOSoulsBlock(
    settings: Settings?,
) :
    AbstractChestBlock<ChestOSoulsBlockEntity>(settings, { DnDBlockEntities.CHEST_O_SOULS }) {

    init {
        defaultState = stateManager.defaultState.with(FACING, Direction.NORTH)
    }

    override fun getCodec(): MapCodec<out AbstractChestBlock<ChestOSoulsBlockEntity>> = CODEC

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return defaultState.with(EnderChestBlock.FACING, ctx.playerFacing.opposite)
    }

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        entity: PlayerEntity?,
        hitResult: BlockHitResult?
    ): ActionResult {
//        val block
//        if () {
//
//        }
        return super.onUse(state, world, pos, entity, hitResult)
    }

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return ChestOSoulsBlockEntity(pos, state)
    }

    override fun <T : BlockEntity?> getTicker(
        world: World,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? =
        checkType(type, DnDBlockEntities.CHEST_O_SOULS, ChestOSoulsBlockEntity::tick)

    override fun getBlockEntitySource(
        state: BlockState, world: World, pos: BlockPos, ignoreBlocked: Boolean
    ): PropertySource<out ChestBlockEntity>? {
        return null
//        return object : PropertySource<ChestBlockEntity> {
//
//            override fun <T : Any?> apply(propertyRetriever: DoubleBlockProperties.PropertyRetriever<in ChestBlockEntity, T>?): T {
//                TODO("Not yet implemented")
//            }
//        }
//        return PropertySource.Single(BlockEntityType.CHEST.get(world, pos))
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.ANIMATED
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING)))
    }

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState {
        return state.rotate(mirror.getRotation(state.get(FACING)))
    }

    override fun appendProperties(builder: StateManager.Builder<Block?, BlockState?>) {
        builder.add(FACING)
    }

    companion object {
        val FACING: DirectionProperty = HorizontalFacingBlock.FACING;
        val CODEC: MapCodec<ChestOSoulsBlock> = createCodec(::ChestOSoulsBlock)
    }
}
