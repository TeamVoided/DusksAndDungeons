package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.item.component.Tool
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class FunnyIceSword(settings: Properties) : Item(settings) {

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

    override fun canAttackBlock(state: BlockState, world: Level, pos: BlockPos, miner: Player): Boolean {
        return !miner.isCreative
    }

    override fun hurtEnemy(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        return true
    }

//    override fun method_59978(stack: ItemStack, livingEntity: LivingEntity, livingEntity2: LivingEntity) {
//        stack.damageEquipment(1, livingEntity2, EquipmentSlot.MAINHAND)
//    }

    override fun getEnchantmentValue(): Int {
        return 15
    }

    override fun isValidRepairItem(stack: ItemStack, ingredient: ItemStack): Boolean {
        return false//ingredient.isOf(DnDItems.FREEZE_ROD)
    }


    companion object {
        fun createToolComponent(): Tool {
            return Tool(listOf(), 1.0f, 2)
        }

        fun createAttributes(): ItemAttributeModifiers {
            return ItemAttributeModifiers.builder()
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