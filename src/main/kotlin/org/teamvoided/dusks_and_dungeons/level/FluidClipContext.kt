package org.teamvoided.dusks_and_dungeons.level

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Predicate

class FluidClipContext(
    from: Vec3, to: Vec3, block: Block, val fluidPredicate: Predicate<FluidState>, ctx: CollisionContext,
) : ClipContext(from, to, block, Fluid.NONE, ctx) {

    constructor(from: Vec3, to: Vec3, block: Block, fluidPredicate: Predicate<FluidState>, entity: Entity)
            : this(from, to, block, fluidPredicate, CollisionContext.of(entity))

    override fun getFluidShape(fluid: FluidState, level: BlockGetter, pos: BlockPos): VoxelShape {
        return if (fluidPredicate.test(fluid)) fluid.getShape(level, pos) else Shapes.empty()
    }

    companion object {

        fun getPlayerFluidHitResult(
            level: Level, player: Player, fluidPredicate: Predicate<FluidState>,
        ): BlockHitResult {
            val from = player.eyePosition
            val to = from.add(
                player.calculateViewVector(player.xRot, player.yRot).scale(player.blockInteractionRange())
            )
            return level.clip(FluidClipContext(from, to, Block.OUTLINE, fluidPredicate, player))
        }

    }
}