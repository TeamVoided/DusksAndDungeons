/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.block.big.jar

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.symmetricalBoxY

open class BigJarBlock(settings: Properties) : Block(settings) {

    //override fun useItemOn(
    //    stack: ItemStack,
    //    state: BlockState,
    //    world: Level,
    //    pos: BlockPos,
    //    entity: Player,
    //    hand: InteractionHand,
    //    hitResult: BlockHitResult,
    //): ItemInteractionResult? {
    //    return super.useItemOn(stack, state, world, pos, entity, hand, hitResult)
    //}

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext) = SHAPE_HOLLOW
    override fun isPathfindable(blockState: BlockState, pathComputationType: PathComputationType): Boolean = false
    open fun isFull(state: BlockState): Boolean = false
    open fun getFluidHeight(state: BlockState): Double = 0.0
    fun isEntityTouchingFluid(state: BlockState, pos: BlockPos, entity: Entity): Boolean {
        //Don't know why I have this since the entire thing is enclosed but cool I guess
        if (!(entity.y < pos.y + getFluidHeight(state) && entity.boundingBox.maxY > pos.y + Y_MIN))
            return false
        val width = entity.bbWidth / 2
        if (!(entity.x + width > pos.x + XZ_MIN || entity.x - width < pos.x + XZ_MAX))
            return false
        return !(entity.z + width > pos.z + XZ_MIN || entity.z - width < pos.z + XZ_MAX)
    }

    companion object {
        private const val Y_MIN = 1 / 16.0
        private const val XZ_MIN = 2 / 16.0
        private const val XZ_MAX = 1 - XZ_MIN
        val BODY: VoxelShape = symmetricalBoxY(1.0, 0.0, 14.0)
        val NECK: VoxelShape = symmetricalBoxY(5.0, 14.0, 16.0)
        val CAVITY: VoxelShape = symmetricalBoxY(2.0, 1.0, 13.0)
        val SHAPE_HOLLOW: VoxelShape = Shapes.join(
            Shapes.or(BODY, NECK),
            Shapes.or(CAVITY),
            BooleanOp.ONLY_FIRST
        )
    }
}
