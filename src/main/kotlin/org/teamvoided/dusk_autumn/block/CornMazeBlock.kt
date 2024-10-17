package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

class CornMazeBlock(settings: Settings) : TripleTallPlantBlock(settings) {
    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        val y = if (entity.velocity.y > 0) 0.9 else 1.0
        val vec3d = Vec3d(0.2, y, 0.2)
        entity.setMovementMultiplier(state, vec3d)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val voxelShape = super.getOutlineShape(state, world, pos, context)
        val vec3d = state.getModelOffset(world, pos)
        return voxelShape.offset(vec3d.x, vec3d.y, vec3d.z)
    }
}