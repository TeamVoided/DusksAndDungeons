package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.entity.Entity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.ItemInteractionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

open class BigJarBlock(settings: Settings) : Block(settings) {

    //override fun getCodec(): MapCodec<out BigJarBlock>

    open fun getFluidHeight(state: BlockState): Double = 0.0

    fun isEntityTouchingFluid(state: BlockState, pos: BlockPos, entity: Entity): Boolean {
        return entity.y < pos.y + this.getFluidHeight(state) && entity.bounds.maxY > pos.y + 0.25
    }

    override fun onInteract(
        stack: ItemStack,
        state: BlockState,
        world: World,
        pos: BlockPos,
        entity: PlayerEntity,
        hand: Hand,
        hitResult: BlockHitResult
    ): ItemInteractionResult? {
        return super.onInteract(stack, state, world, pos, entity, hand, hitResult)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape = SHAPE

    override fun canPathfindThrough(state: BlockState, navigationType: NavigationType): Boolean = false

    open fun isFull(state: BlockState): Boolean = false

    companion object {
        private val SHAPE: VoxelShape = createCuboidShape(1.0, 0.0, 1.0, 15.0, 14.0, 15.0)
    }
}
