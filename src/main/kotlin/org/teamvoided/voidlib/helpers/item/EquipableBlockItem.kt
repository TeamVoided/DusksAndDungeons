package org.teamvoided.voidlib.helpers.item

import net.minecraft.world.level.block.Block
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Equipable
import net.minecraft.world.item.ItemStack
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.level.Level

class EquipableBlockItem(block: Block, settings: Properties, val slot: EquipmentSlot = EquipmentSlot.HEAD) :
    BlockItem(block, settings), Equipable {
    override fun getEquipmentSlot(): EquipmentSlot = slot
    override fun use(world: Level, user: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> =
        this.swapWithEquipmentSlot(this, world, user, hand)
}
