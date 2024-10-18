package org.teamvoided.dusk_autumn.item

import net.minecraft.block.dispenser.DispenserBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ProjectileItem
import net.minecraft.item.ProjectileItem.DispenserConfig
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Position
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.entity.ChillChargeEntity

class ChillChargeItem(settings: Settings) : Item(settings), ProjectileItem {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (!world.isClient()) {
            val chillChargeEntity = ChillChargeEntity(user, world, user.pos.getX(), user.eyePos.getY(), user.pos.getZ())
            chillChargeEntity.setProperties(user, user.pitch, user.yaw, 0.0f, 1.5f, 1.0f)
            world.spawnEntity(chillChargeEntity)
        }

        world.playSound(
            null as PlayerEntity?,
            user.x,
            user.y,
            user.z,
            SoundEvents.ENTITY_WIND_CHARGE_THROW,
            SoundCategory.NEUTRAL,
            0.5f,
            0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f)
        )
        val itemStack = user.getStackInHand(hand)
        user.itemCooldownManager[this] = cooldown
        user.incrementStat(Stats.USED.getOrCreateStat(this))
        itemStack.consume(1, user)
        return TypedActionResult.success(itemStack, world.isClient())
    }

    override fun createEntity(world: World, pos: Position, stack: ItemStack, direction: Direction): ProjectileEntity {
        val randomGenerator = world.getRandom()
        val offsetX = randomGenerator.nextTriangular(direction.offsetX.toDouble(), 0.11485)
        val offsetY = randomGenerator.nextTriangular(direction.offsetY.toDouble(), 0.11485)
        val offsetZ = randomGenerator.nextTriangular(direction.offsetZ.toDouble(), 0.11485)
        val vec3d = Vec3d(offsetX, offsetY, offsetZ)
        val chillChargeEntity = ChillChargeEntity(world, pos.x, pos.y, pos.z, vec3d)
        chillChargeEntity.velocity = vec3d
        return chillChargeEntity
    }

    override fun initializeProjectile(
        projectile: ProjectileEntity,
        x: Double,
        y: Double,
        z: Double,
        speed: Float,
        divergence: Float
    ) {
    }

    override fun createDispenserConfig(): DispenserConfig {
        return DispenserConfig.builder().positionFunction { blockPointer: BlockPointer, direction: Direction ->
            DispenserBlock.getDispensePos(blockPointer, 1.0, Vec3d.ZERO)
        }.uncertainty(6.6666665f).power(1.0f).overrideDispenseEvent(1051).build()
    }

    companion object {
        private const val cooldown = 10
    }
}