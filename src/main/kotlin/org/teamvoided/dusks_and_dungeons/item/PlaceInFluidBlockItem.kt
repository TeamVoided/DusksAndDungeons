package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.tags.FluidTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.FluidState
import org.teamvoided.dusks_and_dungeons.level.FluidClipContext.Companion.getPlayerFluidHitResult
import org.teamvoided.dusks_and_dungeons.util.blockPos
import java.util.function.Predicate

class PlaceInFluidBlockItem(
    val fluidPredicate: Predicate<FluidState>, block: Block, properties: Properties,
) : BlockItem(block, properties) {

    override fun useOn(useOnContext: UseOnContext): InteractionResult = InteractionResult.PASS

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        val headFluid = level.getFluidState(player.eyePosition.blockPos())
        val hit = getPlayerFluidHitResult(level, player, if (fluidPredicate.test(headFluid)) NONE else fluidPredicate)
        val result = super.useOn(UseOnContext(player, hand, hit))
        return InteractionResultHolder(result, player.getItemInHand(hand))
    }

    companion object {

        val LAVA = Predicate<FluidState> { fluid -> fluid.`is`(FluidTags.LAVA) }
        val NONE = Predicate<FluidState> { false }

    }
}