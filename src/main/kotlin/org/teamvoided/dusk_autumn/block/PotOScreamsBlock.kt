package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.DecoratedPotBlock
import net.minecraft.block.entity.DecoratedPotBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemInteractionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import net.minecraft.world.WorldView
import org.teamvoided.dusk_autumn.init.DnDBlocks

class PotOScreamsBlock(settings: Settings) : DecoratedPotBlock(settings) {

    override fun onInteract(
        stack: ItemStack?,
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        entity: PlayerEntity?,
        hand: Hand?,
        hitResult: BlockHitResult?
    ): ItemInteractionResult {
        scare(world, pos)
        return super.onInteract(stack, state, world, pos, entity, hand, hitResult)
    }

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        entity: PlayerEntity?,
        hitResult: BlockHitResult?
    ): ActionResult {
        scare(world, pos)
        return super.onUse(state, world, pos, entity, hitResult)
    }

    private fun scare(world: World?, pos: BlockPos?) {
        if (world == null || pos == null) {
            return
        }

        val random = world.getRandom();
        world.playSound(null, pos, SoundEvents.ENTITY_HORSE_DEATH, SoundCategory.BLOCKS)

        for (i in 0..random.rangeInclusive(1, 3)) {
            world.addParticle(
                ParticleTypes.SOUL,
                pos.x.toDouble() + 0.5 + (MathHelper.nextDouble(random, 0.0, 0.125) * if (random.nextBoolean()) 1.0 else -1.0),
                pos.y.toDouble() + 1.25,
                pos.z.toDouble() + 0.5 + (MathHelper.nextDouble(random, 0.0, 0.125) * if (random.nextBoolean()) 1.0 else -1.0),
                0.0, MathHelper.nextDouble(random, 0.01, 0.1), 0.0
            )
        }
    }

    override fun getPickStack(world: WorldView?, pos: BlockPos?, state: BlockState?): ItemStack {
        val stack = ItemStack(DnDBlocks.POT_O_SCREAMS)
        stack.applyComponents(super.getPickStack(world, pos, state).components)
        return stack
    }
}