package org.teamvoided.dusks_and_dungeons.block.pumpkin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CarvedPumpkinBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.dusks_and_dungeons.util.block.getId

open class DnDPumpkinBlock(val carvedBlock: Block, settings: Properties) : Block(settings), CarvableBlock {

    override fun getId(): ResourceLocation = getId(this)

    override fun getCarvedBlockState(stack: ItemStack, state: BlockState, clickedDir: Direction, hit: BlockHitResult): BlockState {
        return carvedBlock.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, clickedDir)
    }

    override fun useItemOn(
        stack: ItemStack, state: BlockState, level: Level, pos: BlockPos,
        player: Player, hand: InteractionHand, hit: BlockHitResult,
    ): ItemInteractionResult {
        return if (tryCarve(stack, state, level, pos, player, hand, hit))
            ItemInteractionResult.sidedSuccess(level.isClientSide)
        else
            super.useItemOn(stack, state, level, pos, player, hand, hit)
    }

}