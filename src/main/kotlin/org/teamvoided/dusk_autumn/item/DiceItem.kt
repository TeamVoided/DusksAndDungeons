package org.teamvoided.dusk_autumn.item

import net.minecraft.client.util.ColorUtil
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ProjectileItem
import net.minecraft.registry.tag.ItemTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Position
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.entity.DiceEntity

class DiceItem(settings: Settings) : Item(settings), ProjectileItem {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val itemStack = user.getStackInHand(hand)
        world.playSound(
            null as PlayerEntity?,
            user.x,
            user.y,
            user.z,
            SoundEvents.ENTITY_SNOWBALL_THROW,
            SoundCategory.NEUTRAL,
            0.5f,
            0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f)
        )
        if (!world.isClient) {
            val diceEntity = DiceEntity(world, user, getDiceColor(itemStack))
            diceEntity.setItem(itemStack)
            diceEntity.setProperties(user, user.pitch, user.yaw, 0.0f, 1.5f, 1.0f)
            world.spawnEntity(diceEntity)
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this))
        itemStack.consume(1, user)
        return TypedActionResult.success(itemStack, world.isClient())
    }

    override fun createEntity(world: World, pos: Position, stack: ItemStack, direction: Direction): ProjectileEntity {
        val diceEntity = DiceEntity(world, pos.x, pos.y, pos.z, getDiceColor(stack))
        diceEntity.setItem(stack)
        return diceEntity
    }

    private fun getDiceColor(itemStack: ItemStack):Int{
        return ColorUtil.Argb32.toOpaque(
            DyedColorComponent.getColorOrDefault(
                itemStack,
                0xFFFFFF
            )
        )
    }
}