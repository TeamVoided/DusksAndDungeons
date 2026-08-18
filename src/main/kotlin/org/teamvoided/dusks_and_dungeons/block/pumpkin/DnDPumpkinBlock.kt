package org.teamvoided.dusks_and_dungeons.block.pumpkin

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.CarvedPumpkinBlock
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.sounds.SoundSource
import net.minecraft.sounds.SoundEvents
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import org.teamvoided.dusks_and_dungeons.util.isShears

open class DnDPumpkinBlock(private val carvedBlock: Block, settings: Properties) : Block(settings) {
    private var seedsItem = Items.PUMPKIN_SEEDS
    open val seeds = 4
    override fun useItemOn(
        stack: ItemStack, state: BlockState, world: Level,
        pos: BlockPos, entity: Player, hand: InteractionHand, hitResult: BlockHitResult
    ): ItemInteractionResult {
        return if (!stack.isShears()) super.useItemOn(stack, state, world, pos, entity, hand, hitResult)
        else if (world.isClientSide) ItemInteractionResult.sidedSuccess(world.isClientSide)
        else {
            val direction = hitResult.direction
            val direction2 = if (direction.axis == Direction.Axis.Y) entity.direction.opposite else direction
            world.playSound(
                null,
                pos,
                SoundEvents.PUMPKIN_CARVE,
                SoundSource.BLOCKS,
                1.0f,
                1.0f
            )
            world.setBlock(
                pos,
                carvedBlock.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, direction2),
                11
            )
            val itemEntity = ItemEntity(
                world,
                pos.x.toDouble() + 0.5 + direction2.stepX.toDouble() * 0.65, pos.y.toDouble() + 0.1,
                pos.z.toDouble() + 0.5 + direction2.stepZ.toDouble() * 0.65, ItemStack(seedsItem, seeds)
            )
            itemEntity.setDeltaMovement(
                0.05 * direction2.stepX.toDouble() + world.random.nextDouble() * 0.02,
                0.05,
                0.05 * direction2.stepZ.toDouble() + world.random.nextDouble() * 0.02
            )
            world.addFreshEntity(itemEntity)
            stack.hurtAndBreak(1, entity, LivingEntity.getSlotForHand(hand))
            world.gameEvent(entity, GameEvent.SHEAR, pos)
            entity.awardStat(Stats.ITEM_USED.get(stack.item))
            ItemInteractionResult.sidedSuccess(world.isClientSide)
        }
    }

    fun setSeeds(item: Item) {
        seedsItem = item
    }

    companion object {
        fun Block.setSeeds(item: Item) =
            if (this is DnDPumpkinBlock) this.setSeeds(item) else error("Block [$this] is not a DnDPumpkinBlock")
    }
}