package org.teamvoided.dusk_autumn.block.meltable

import net.minecraft.block.BlockState
import net.minecraft.block.SlabBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World

class MeltableSlabBlock(settings: Settings) : SlabBlock(settings) {
    override fun isSideInvisible(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {

        return if (stateFrom.isOf(this)) true else super.isSideInvisible(state, stateFrom, direction)
    }

    override fun afterBreak(
        world: World,
        player: PlayerEntity,
        pos: BlockPos,
        state: BlockState,
        blockEntity: BlockEntity?,
        stack: ItemStack
    ) {
        super.afterBreak(world, player, pos, state, blockEntity, stack)
        Meltable.meltAfterBreak(world, pos, stack)
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        Meltable.meltFromLight(state, world, pos)
    }
}