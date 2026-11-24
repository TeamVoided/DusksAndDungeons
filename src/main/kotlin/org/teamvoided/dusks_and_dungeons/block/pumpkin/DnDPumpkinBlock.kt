package org.teamvoided.dusks_and_dungeons.block.pumpkin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceKey
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CarvedPumpkinBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.voidlib.api.block.Carvable

open class DnDPumpkinBlock(private val carvedBlock: Block, properties: Properties) : Block(properties), Carvable {

    override fun getCarvingLootTable(): ResourceKey<LootTable> = Carvable.crateKey(this)

    override fun getCarvedBlock(): Block = carvedBlock

    override fun getCarvedBlockState(stack: ItemStack, state: BlockState, clickedDir: Direction, hit: BlockHitResult): BlockState {
        return getCarvedBlock().defaultBlockState().setValue(CarvedPumpkinBlock.FACING, clickedDir)
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