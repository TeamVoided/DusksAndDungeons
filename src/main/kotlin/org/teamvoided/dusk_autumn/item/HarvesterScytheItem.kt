package org.teamvoided.dusk_autumn.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.util.toSlot

class HarvesterScytheItem(toolMaterial: ToolMaterial, settings: Settings) : SwordItem(toolMaterial, settings) {
    constructor(settings: Settings) : this(DnDToolMaterials.HARVESTER_SCYTHE, settings)


    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val itemStack = user.getStackInHand(hand)
        if (!world.isClient) println("gay")
        user.incrementStat(Stats.USED.getOrCreateStat(this))
        itemStack.damageEquipment(1, user, hand.toSlot())
        return TypedActionResult.success(itemStack, world.isClient())
    }

}