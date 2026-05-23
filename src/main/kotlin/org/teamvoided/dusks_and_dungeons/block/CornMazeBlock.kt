package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level

class CornMazeBlock(settings: Properties) : TripleTallPlantBlock(settings) {
    override fun entityInside(state: BlockState, world: Level, pos: BlockPos, entity: Entity) {
        if (entity is Player && !entity.isCreative) {
            entity.makeStuckInBlock(
                state, if (entity.isSprinting) cornMovementMultiplier else cornSprintMovementMultiplier
            )
        }
    }

    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ): VoxelShape {
        val voxelShape = super.getShape(state, world, pos, context)
        val vec3d = state.getOffset(world, pos)
        return voxelShape.move(vec3d.x, vec3d.y, vec3d.z)
    }

    companion object {
        val cornMovementMultiplier = Vec3(0.1, 1.0, 0.1)
        val cornSprintMovementMultiplier = Vec3(0.0, 1.0, 0.0)
    }
}