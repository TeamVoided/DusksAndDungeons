package org.teamvoided.dusk_autumn.item

import net.minecraft.block.BlockState
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.ToolComponent
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class FunnyIceSword(settings: Settings) : Item(settings) {

//    main click - normal attack
//    hold secondary click, then release - spin-around attack
//    hold secondary click, then main click - projectile slash attack
    //lmao clownpierce scythe

//    dash attack?
//    groundslam slash? - hit ground, projectile runs forward emitted from ground
//    snowgrave? - imprison in ice
//    HELICOPTER HELICOPTER - spin attack
//    throw sword and it returns? - idea already implemented with the trident and loyalty, could fly like boomerang instead
//    lifesteal? - no not the survival server type, life leach


    //damage based on speed, ice skating?

    override fun canMine(state: BlockState, world: World, pos: BlockPos, miner: PlayerEntity): Boolean {
        return !miner.isCreative
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        return true
    }

//    override fun method_59978(stack: ItemStack, livingEntity: LivingEntity, livingEntity2: LivingEntity) {
//        stack.damageEquipment(1, livingEntity2, EquipmentSlot.MAINHAND)
//    }

    override fun getEnchantability(): Int {
        return 15
    }

    override fun canRepair(stack: ItemStack, ingredient: ItemStack): Boolean {
        return false//ingredient.isOf(DnDItems.FREEZE_ROD)
    }


    companion object {
        fun createToolComponent(): ToolComponent {
            return ToolComponent(listOf(), 1.0f, 2)
        }

        fun createAttributes(): AttributeModifiersComponent {
            return AttributeModifiersComponent.builder()
//                .add(
//                    EntityAttributes.GENERIC_ATTACK_DAMAGE, EntityAttributeModifier(
//                        field_8006, 6.5, EntityAttributeModifier.Operation.ADD_VALUE
//                    ), EquipmentSlotGroup.MAINHAND
//                ).add(
//                    EntityAttributes.GENERIC_ATTACK_SPEED, EntityAttributeModifier(
//                        field_8001, -2.5, EntityAttributeModifier.Operation.ADD_VALUE
//                    ), EquipmentSlotGroup.MAINHAND
//                ).add(
//                    EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, EntityAttributeModifier(
//                        id("attack_reach"), 2.0, EntityAttributeModifier.Operation.ADD_VALUE
//                    ), EquipmentSlotGroup.MAINHAND
//                )
                .build()
        }

    }
}