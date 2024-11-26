package org.teamvoided.voidlib.helpers.item

import net.minecraft.block.Block
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Equippable
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class EquipableBlockItem(block: Block, settings: Settings, val slot: EquipmentSlot = EquipmentSlot.HEAD) :
    BlockItem(block, settings), Equippable {
    override fun getPreferredSlot(): EquipmentSlot = slot
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> =
        this.use(this, world, user, hand)
}
