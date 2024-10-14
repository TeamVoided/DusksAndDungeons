package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.CarvedPumpkinBlock
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.ItemInteractionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent

open class Pumpkin2Block(private val carvedBlock:Block, private val seedsItem: Item, settings: Settings) : Block(settings) {
    open val seeds = 4
    override fun onInteract(
        stack: ItemStack,
        state: BlockState,
        world: World,
        pos: BlockPos,
        entity: PlayerEntity,
        hand: Hand,
        hitResult: BlockHitResult
    ): ItemInteractionResult {
        if (!stack.isOf(Items.SHEARS)) {
            return super.onInteract(stack, state, world, pos, entity, hand, hitResult)
        } else if (world.isClient) {
            return ItemInteractionResult.success(world.isClient)
        } else {
            val direction = hitResult.side
            val direction2 = if (direction.axis == Direction.Axis.Y) entity.horizontalFacing.opposite else direction
            world.playSound(
                null as PlayerEntity?,
                pos,
                SoundEvents.BLOCK_PUMPKIN_CARVE,
                SoundCategory.BLOCKS,
                1.0f,
                1.0f
            )
            world.setBlockState(
                pos,
                carvedBlock.defaultState.with(CarvedPumpkinBlock.FACING, direction2) as BlockState,
                11
            )
            val itemEntity = ItemEntity(
                world,
                pos.x.toDouble() + 0.5 + direction2.offsetX.toDouble() * 0.65, pos.y.toDouble() + 0.1,
                pos.z.toDouble() + 0.5 + direction2.offsetZ.toDouble() * 0.65, ItemStack(seedsItem, seeds)
            )
            itemEntity.setVelocity(
                0.05 * direction2.offsetX.toDouble() + world.random.nextDouble() * 0.02,
                0.05,
                0.05 * direction2.offsetZ.toDouble() + world.random.nextDouble() * 0.02
            )
            world.spawnEntity(itemEntity)
            stack.damageEquipment(1, entity, LivingEntity.getHand(hand))
            world.emitGameEvent(entity, GameEvent.SHEAR, pos)
            entity.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS))
            return ItemInteractionResult.success(world.isClient)
        }
    }
}