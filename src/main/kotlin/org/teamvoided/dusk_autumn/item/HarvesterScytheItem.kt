package org.teamvoided.dusk_autumn.item

import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.entity.EquipmentSlotGroup
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.projectile.FlyingPumpkinProjectile
import org.teamvoided.dusk_autumn.init.DnDEntities
import org.teamvoided.dusk_autumn.util.setShootVelocity
import org.teamvoided.dusk_autumn.util.toSlot

class HarvesterScytheItem(toolMaterial: ToolMaterial, settings: Settings) : SwordItem(toolMaterial, settings) {
    constructor(settings: Settings) : this(DnDToolMaterials.HARVESTER_SCYTHE, settings)


    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val itemStack = user.getStackInHand(hand)
        if (!world.isClient) {
            val flyingPumpkin = FlyingPumpkinProjectile(user, world, itemStack, null)
            flyingPumpkin.setShootVelocity(user.pitch, user.yaw, 0.0f, 1f, 0.0f)
//            flyingPumpkin.pitch = user.pitch
//            flyingPumpkin.yaw = user.yaw

            world.spawnEntity(flyingPumpkin)
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this))
        itemStack.damageEquipment(1, user, hand.toSlot())
        if (!user.isCreative) user.itemCooldownManager.set(this, 20)
        return TypedActionResult.success(itemStack, world.isClient())
    }

    companion object {
        fun makeAttributes(): AttributeModifiersComponent =
            createAttributes(DnDToolMaterials.HARVESTER_SCYTHE, 3, -2.4f)
                .with(
                    EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, EntityAttributeModifier(
                        id("base_block_range"), 3.0, EntityAttributeModifier.Operation.ADD_VALUE
                    ), EquipmentSlotGroup.MAINHAND
                )
                .with(
                    EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, EntityAttributeModifier(
                        id("base_entity_range"), 1.5, EntityAttributeModifier.Operation.ADD_VALUE
                    ), EquipmentSlotGroup.MAINHAND
                )
    }
}
