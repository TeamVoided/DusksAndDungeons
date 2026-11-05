package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.HugeMushroomBlock
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleOption

class MushroomWithSporesBlock(
    val color: Int, val particleChance: Double,
    properties: Properties,
) : HugeMushroomBlock(properties) {

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, level, pos, random)
        if (random.nextDouble() < particleChance) return

        val side = getOpenSides(state).randomOrNull() ?: return
        val relativePos = pos.relative(side)
        val relativeState = level.getBlockState(relativePos)
        if (!state.canOcclude() || !relativeState.isFaceSturdy(level, relativePos, side.opposite)) {
            val xOffset = if (side.stepX == 0) random.nextDouble() else 0.5 + side.stepX * 0.65
            val yOffset = if (side.stepY == 0) random.nextDouble() else 0.5 + side.stepY * 0.65
            val zOffset = if (side.stepZ == 0) random.nextDouble() else 0.5 + side.stepZ * 0.65
            level.addParticle(
                ColorableParticleOption(color),
                pos.x + xOffset, pos.y + yOffset, pos.z + zOffset,
                (random.nextDouble() - random.nextDouble()) * 0.125,
                (random.nextDouble() * -0.1) - 0.1,
                (random.nextDouble() - random.nextDouble()) * 0.125
            )
        }
    }

    companion object {

        val SIDE_CACHE = mutableMapOf<BlockState, List<Direction>>()

        fun getOpenSides(state: BlockState): List<Direction> {
            var dirList = SIDE_CACHE[state]
            if (dirList == null) {
                dirList = buildList {
                    if (state.getValue(UP)) add(Direction.UP)
                    if (state.getValue(DOWN)) add(Direction.DOWN)
                    if (state.getValue(NORTH)) add(Direction.NORTH)
                    if (state.getValue(EAST)) add(Direction.EAST)
                    if (state.getValue(SOUTH)) add(Direction.SOUTH)
                    if (state.getValue(WEST)) add(Direction.WEST)
                }
                SIDE_CACHE[state] = dirList
            }
            return dirList
        }

    }
}