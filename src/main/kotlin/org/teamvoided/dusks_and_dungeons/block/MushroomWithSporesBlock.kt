package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.HugeMushroomBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect

class MushroomWithSporesBlock(private val color: Int, private val particleChance: Double, settings: Properties) :
    HugeMushroomBlock(settings) {
    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, world, pos, random)
        if (random.nextDouble() < particleChance) return

        val direction = getValidDir(state).randomOrNull() ?: return
        val blockPos = pos.relative(direction)
        val blockState = world.getBlockState(blockPos)
        if (!state.canOcclude() || !blockState.isFaceSturdy(world, blockPos, direction.opposite)) {
            val d = if (direction.stepX == 0) random.nextDouble() else 0.5 + direction.stepX.toDouble() * 0.65
            val e = if (direction.stepY == 0) random.nextDouble() else 0.5 + direction.stepY.toDouble() * 0.65
            val f = if (direction.stepZ == 0) random.nextDouble() else 0.5 + direction.stepZ.toDouble() * 0.65
            world.addParticle(
                ColorableParticleEffect(color),
                pos.x.toDouble() + d, pos.y.toDouble() + e, pos.z.toDouble() + f,
                0.0, 0.0, 0.0
            )
        }
    }

    fun getValidDir(state: BlockState): List<Direction> = buildList {
        if (state.getValue(UP)) add(Direction.UP)
        if (state.getValue(DOWN)) add(Direction.DOWN)
        if (state.getValue(NORTH)) add(Direction.NORTH)
        if (state.getValue(EAST)) add(Direction.EAST)
        if (state.getValue(SOUTH)) add(Direction.SOUTH)
        if (state.getValue(WEST)) add(Direction.WEST)
    }
}