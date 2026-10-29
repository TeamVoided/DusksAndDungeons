package org.teamvoided.voidlib.helpers.item

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Equipable
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block

class EquipableBlockItem(
    block: Block, properties: Properties,
    val slot: EquipmentSlot = EquipmentSlot.HEAD,
) : BlockItem(block, properties), Equipable {

    override fun getEquipmentSlot(): EquipmentSlot = slot

    override fun use(world: Level, user: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        return swapWithEquipmentSlot(this, world, user, hand)
    }

}