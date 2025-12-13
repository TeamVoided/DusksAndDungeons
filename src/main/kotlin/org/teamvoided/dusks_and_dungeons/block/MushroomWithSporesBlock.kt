package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.block.BlockState
import net.minecraft.block.MushroomBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect

class MushroomWithSporesBlock(private val color: Int, private val particleChance: Double, settings: Settings) :
    MushroomBlock(settings) {
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
        if (random.nextDouble() < particleChance) return

        val direction = getValidDir(state).randomOrNull() ?: return
        val blockPos = pos.offset(direction)
        val blockState = world.getBlockState(blockPos)
        if (!state.isOpaque || !blockState.isSideSolidFullSquare(world, blockPos, direction.opposite)) {
            val d = if (direction.offsetX == 0) random.nextDouble() else 0.5 + direction.offsetX.toDouble() * 0.65
            val e = if (direction.offsetY == 0) random.nextDouble() else 0.5 + direction.offsetY.toDouble() * 0.65
            val f = if (direction.offsetZ == 0) random.nextDouble() else 0.5 + direction.offsetZ.toDouble() * 0.65
            world.addParticle(
                ColorableParticleEffect(color),
                pos.x.toDouble() + d, pos.y.toDouble() + e, pos.z.toDouble() + f,
                0.0, 0.0, 0.0
            )
        }
    }

    fun getValidDir(state: BlockState): List<Direction> = buildList {
        if (state.get(UP)) add(Direction.UP)
        if (state.get(DOWN)) add(Direction.DOWN)
        if (state.get(NORTH)) add(Direction.NORTH)
        if (state.get(EAST)) add(Direction.EAST)
        if (state.get(SOUTH)) add(Direction.SOUTH)
        if (state.get(WEST)) add(Direction.WEST)
    }
}