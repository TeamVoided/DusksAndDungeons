package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.HugeMushroomBlock
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect

class MushroomWithSporesBlock(private val color: Int, private val particleChance: Double, settings: Properties) :
    HugeMushroomBlock(settings) {
    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, world, pos, random)
        if (random.nextDouble() < particleChance) return

        val side = getOpenSides(state).randomOrNull() ?: return
        val blockPos = pos.relative(side)
        val blockState = world.getBlockState(blockPos)
        if (!state.canOcclude() || !blockState.isFaceSturdy(world, blockPos, side.opposite)) {
            val xOffset = if (side.stepX == 0) random.nextDouble() else 0.5 + side.stepX * 0.65
            val yOffset = if (side.stepY == 0) random.nextDouble() else 0.5 + side.stepY * 0.65
            val zOffset = if (side.stepZ == 0) random.nextDouble() else 0.5 + side.stepZ * 0.65
            world.addParticle(
                ColorableParticleEffect(color),
                pos.x + xOffset, pos.y + yOffset, pos.z + zOffset,
                (random.nextDouble() - random.nextDouble()) * 0.125,
                (random.nextDouble() * -0.1) - 0.1,
                (random.nextDouble() - random.nextDouble()) * 0.125
            )
        }
    }

    companion object {

        val SIDE_LIST_CACHE = mutableMapOf<BlockState, List<Direction>>()

        fun getOpenSides(state: BlockState): List<Direction> {
            var dirList = SIDE_LIST_CACHE[state]
            if (dirList == null) {
                dirList = buildList {
                    if (state.getValue(UP)) add(Direction.UP)
                    if (state.getValue(DOWN)) add(Direction.DOWN)
                    if (state.getValue(NORTH)) add(Direction.NORTH)
                    if (state.getValue(EAST)) add(Direction.EAST)
                    if (state.getValue(SOUTH)) add(Direction.SOUTH)
                    if (state.getValue(WEST)) add(Direction.WEST)
                }
                SIDE_LIST_CACHE[state] = dirList
            }
            return dirList
        }

    }
}