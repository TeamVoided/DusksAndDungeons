package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.util.hasEnchantment

class MoltenBlock(settings: Settings, val turnsInTo: Block) : Block(settings) {
    override fun afterBreak(
        world: World, player: PlayerEntity, pos: BlockPos, state: BlockState, blockEntity: BlockEntity?,
        stack: ItemStack
    ) {
        super.afterBreak(world, player, pos, state, blockEntity, stack)
        if (!stack.hasEnchantment(Enchantments.SILK_TOUCH))
            world.setBlockState(pos, turnsInTo.defaultState, NOTIFY_ALL)
    }
}
