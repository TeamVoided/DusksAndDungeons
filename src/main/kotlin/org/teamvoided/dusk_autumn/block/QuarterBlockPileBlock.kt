package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.state.StateManager
import net.minecraft.util.Hand
import net.minecraft.util.ItemInteractionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.block.entity.QuarterBlockPileBlockEntity

class QuarterBlockPileBlock(settings: Settings?) : HorizontalFacingBlock(settings), BlockEntityProvider {

    init {
        defaultState = stateManager.defaultState.with(FACING, Direction.NORTH)
    }

    override fun onInteract(
        stack: ItemStack,
        state: BlockState?,
        world: World,
        pos: BlockPos?,
        entity: PlayerEntity?,
        hand: Hand?,
        hitResult: BlockHitResult?
    ): ItemInteractionResult {
        val item = stack.item
        if (item is BlockItem) {
            val block = item.block
            if (block is SmallPumpkinBlock || block is SmallCarvedPumpkinBlock) {
                val blockEntity = world.getBlockEntity(pos)
                if (blockEntity is QuarterBlockPileBlockEntity) {
                    if (world.isClient) {
                        return ItemInteractionResult.CONSUME
                    }

                    return blockEntity.place(block)
                }
            }
        }
        return super.onInteract(stack, state, world, pos, entity, hand, hitResult)
    }

    override fun getCodec(): MapCodec<out HorizontalFacingBlock> = CODEC

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity =
        QuarterBlockPileBlockEntity(pos, state)

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    companion object {
        val CODEC: MapCodec<QuarterBlockPileBlock> = createCodec(::QuarterBlockPileBlock)
    }
}
