package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

class CornMazeBlock(settings: Settings) : TripleTallPlantBlock(settings) {
    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (entity is PlayerEntity && !entity.isCreative) {
            entity.setMovementMultiplier(state, cornMovementMultiplier)
        }
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

    companion object {
        val cornMovementMultiplier = Vec3d(0.1, 1.0, 0.1)
    }
}