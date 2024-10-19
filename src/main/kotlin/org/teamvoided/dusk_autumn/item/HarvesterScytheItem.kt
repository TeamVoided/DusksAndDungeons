package org.teamvoided.dusk_autumn.item

import net.minecraft.block.BlockState
import net.minecraft.block.CropBlock
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.entity.EquipmentSlotGroup
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.registry.tag.ItemTags
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.entity.projectile.FlyingPumpkinProjectile
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.util.setShootVelocity
import org.teamvoided.dusk_autumn.util.toSlot
import java.util.function.Predicate

class HarvesterScytheItem(toolMaterial: ToolMaterial, settings: Settings) : SwordItem(toolMaterial, settings) {
    constructor(settings: Settings) : this(DnDToolMaterials.HARVESTER_SCYTHE, settings)

    fun getHeldProjectiles(): Predicate<ItemStack> {
        return this.getProjectiles()
    }

    fun getProjectiles(): Predicate<ItemStack> = AMMO


    override fun canMine(state: BlockState, world: World, pos: BlockPos, miner: PlayerEntity): Boolean {
        return if (miner.isCreative)
            false
        else if (state.block is CropBlock)
            (state.block as CropBlock).isMature(state)
//        else if (!state.getOrEmpty(Properties.AGE_1).isEmpty)
//            state.get(Properties.AGE_1) >= Properties.AGE_1_MAX
//        else if (!state.getOrEmpty(Properties.AGE_2).isEmpty)
//            state.get(Properties.AGE_2) >= Properties.AGE_2_MAX
//        else if (!state.getOrEmpty(Properties.AGE_3).isEmpty)
//            state.get(Properties.AGE_3) >= Properties.AGE_3_MAX
//        else if (!state.getOrEmpty(Properties.AGE_4).isEmpty)
//            state.get(Properties.AGE_4) >= Properties.AGE_4_MAX
//        else if (!state.getOrEmpty(Properties.AGE_5).isEmpty)
//            state.get(Properties.AGE_5) >= Properties.AGE_5_MAX
//        else if (!state.getOrEmpty(Properties.AGE_7).isEmpty)
//            state.get(Properties.AGE_7) >= Properties.AGE_7_MAX
//        else if (!state.getOrEmpty(Properties.AGE_15).isEmpty)
//            state.get(Properties.AGE_15) >= Properties.AGE_15_MAX
//        else if (!state.getOrEmpty(Properties.AGE_25).isEmpty)
//            state.get(Properties.AGE_25) >= Properties.AGE_25_MAX
        else
            true
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val itemStack = user.getStackInHand(hand)
        val pumpkin = getItemsFromInventory(itemStack, user)
        if (pumpkin != ItemStack.EMPTY) {
            if (!world.isClient) {
                val flyingPumpkin = FlyingPumpkinProjectile(user, world, pumpkin, itemStack)
                flyingPumpkin.setShootVelocity(user.pitch, user.yaw, 0.0f, 3f, 0.0f)
//            flyingPumpkin.pitch = user.pitch
//            flyingPumpkin.yaw = user.yaw

                world.spawnEntity(flyingPumpkin)
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this))
            itemStack.damageEquipment(1, user, hand.toSlot())
            if (!user.isCreative) user.itemCooldownManager.set(this, 20)
            return TypedActionResult.success(itemStack, world.isClient())
        }
        return  TypedActionResult.pass(itemStack)
    }

    fun getItemsFromInventory(stack: ItemStack, entity: LivingEntity): ItemStack {
        if (entity is PlayerEntity) {
            var predicate = (stack.item as HarvesterScytheItem).getHeldProjectiles()
            val itemStack = RangedWeaponItem.getHeldProjectile(entity, predicate)
            if (!itemStack.isEmpty) {
                return itemStack
            } else {
                predicate = (stack.item as HarvesterScytheItem).getProjectiles()

                for (i in 0 until entity.inventory.size()) {
                    val itemStack2: ItemStack = entity.inventory.getStack(i)
                    if (predicate.test(itemStack2)) {
                        return itemStack2
                    }
                }
                return if (entity.abilities.creativeMode) defaultAmmo() else ItemStack.EMPTY
            }
        } else return defaultAmmo()
    }

    fun defaultAmmo(): ItemStack {
        return ItemStack(DnDFloraBlocks.SMALL_CARVED_PUMPKIN)
    }

    companion object {
        val AMMO: Predicate<ItemStack> =
            Predicate { stack: ItemStack -> stack.isIn(ItemTags.ARROWS) }

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
