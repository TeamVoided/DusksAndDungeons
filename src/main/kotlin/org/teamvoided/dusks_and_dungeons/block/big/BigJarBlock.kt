package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

open class BigJarBlock(settings: Properties) : Block(settings) {

    open fun getFluidHeight(state: BlockState): Double = 0.0

    fun isEntityTouchingFluid(state: BlockState, pos: BlockPos, entity: Entity): Boolean {
        return entity.y < pos.y + getFluidHeight(state) && entity.boundingBox.maxY > pos.y + 0.25
    }

    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        world: Level,
        pos: BlockPos,
        entity: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult,
    ): ItemInteractionResult? {
        return super.useItemOn(stack, state, world, pos, entity, hand, hitResult)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext) = SHAPE

    override fun isPathfindable(blockState: BlockState, pathComputationType: PathComputationType): Boolean = false

    open fun isFull(state: BlockState): Boolean = false

    companion object {
        val SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0)
    }
}
