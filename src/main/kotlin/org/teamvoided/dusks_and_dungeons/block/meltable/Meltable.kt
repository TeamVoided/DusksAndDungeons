package org.teamvoided.dusks_and_dungeons.block.meltable

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.ItemStack
import net.minecraft.tags.EnchantmentTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.LightLayer
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags

object Meltable {

    fun iceNeighborHasSolidFace(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        if (stateFrom.`is`(DnDBlockTags.ICE_BLOCK_TRANSLUCENT)) {
//            if (state.isOf()) {
//                return true
//            } else
            return false
        } else {
            return false
        }
    }

    fun meltAfterBreak(world: Level, pos: BlockPos, stack: ItemStack) {
        if (!EnchantmentHelper.hasTag(stack, EnchantmentTags.PREVENTS_ICE_MELTING))
            meltWithCheck(world, pos)
    }

    fun meltFromLight(state: BlockState, world: Level, pos: BlockPos) {
        if (world.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(world, pos))
            this.meltWithAlwaysWater(world, pos)
    }

    fun meltWithCheck(world: Level, pos: BlockPos) {
        if (world.dimensionType().ultraWarm()) {
            world.removeBlock(pos, false)
            return
        }
        val blockState = world.getBlockState(pos.below())
        if (blockState.blocksMotion() || blockState.liquid())
            world.setBlockAndUpdate(pos, waterState)
    }

    fun meltWithAlwaysWater(world: Level, pos: BlockPos) {
        if (world.dimensionType().ultraWarm()) world.removeBlock(pos, false)
        else {
            world.setBlockAndUpdate(pos, waterState)
            world.neighborChanged(pos, waterState.block, pos)
        }
    }

    val waterState: BlockState
        get() = Blocks.WATER.defaultBlockState()
}