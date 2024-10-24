package org.teamvoided.dusk_autumn.item

import net.minecraft.block.Block
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Equippable
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class HeadDecorationBlockItem(block: Block, settings: Settings) : BlockItem(block, settings), Equippable {
    override fun getPreferredSlot(): EquipmentSlot {
        return EquipmentSlot.HEAD
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        return this.use(this, world, user, hand)
    }
}