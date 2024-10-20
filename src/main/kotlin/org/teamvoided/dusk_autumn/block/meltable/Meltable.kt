package org.teamvoided.dusk_autumn.block.meltable

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SlabBlock
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.EnchantmentTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.LightType
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags

object Meltable {

    fun iceNeighborHasSolidFace(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        if (stateFrom.isIn(DnDBlockTags.ICE_BLOCK_TRANSLUCENT)) {
//            if (state.isOf()) {
//                return true
//            } else
                return false
        } else {
            return false
        }
    }

    fun meltAfterBreak(world: World, pos: BlockPos, stack: ItemStack) {
        if (!EnchantmentHelper.hasTag(stack, EnchantmentTags.PREVENTS_ICE_MELTING)) {
            meltWithCheck(world, pos)
        }
    }

    fun meltFromLight(state: BlockState, world: World, pos: BlockPos) {
        if (world.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(world, pos)) {
            this.meltWithAlwaysWater(world, pos)
        }
    }

    fun meltWithCheck(world: World, pos: BlockPos) {
        if (world.dimension.ultraWarm()) {
            world.removeBlock(pos, false)
            return
        }
        val blockState = world.getBlockState(pos.down())
        if (blockState.blocksMovement() || blockState.isLiquid) {
            world.setBlockState(pos, waterState)
        }
    }

    fun meltWithAlwaysWater(world: World, pos: BlockPos) {
        if (world.dimension.ultraWarm()) {
            world.removeBlock(pos, false)
        } else {
            world.setBlockState(pos, waterState)
            world.updateNeighbor(pos, waterState.block, pos)
        }
    }

    val waterState: BlockState
        get() = Blocks.WATER.defaultState
}