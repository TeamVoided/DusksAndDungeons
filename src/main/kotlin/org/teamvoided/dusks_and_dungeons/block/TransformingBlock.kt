package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import org.teamvoided.voidlib.helpers.mc.hasEnchantment

class TransformingBlock(settings: Properties, val turnsInTo: Block) : Block(settings) {
    override fun playerDestroy(
        world: Level, player: Player, pos: BlockPos, state: BlockState,
        blockEntity: BlockEntity?, stack: ItemStack
    ) {
        super.playerDestroy(world, player, pos, state, blockEntity, stack)
        if (!stack.hasEnchantment(Enchantments.SILK_TOUCH))
            world.setBlock(pos, turnsInTo.defaultBlockState(), UPDATE_ALL)
    }
}
