package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.MushroomBlock
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.resources.ResourceKey
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect

class MushroomWithSporesPlantBlock(
    registryKey: ResourceKey<ConfiguredFeature<*, *>>,
    private val color: Int,
    private val particleChance: Double,
    settings: Properties
) : MushroomBlock(registryKey, settings) {

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        val offset = state.getOffset(world, pos)
        return LARGER_SHAPE.move(offset.x, 0.0, offset.z)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        return canSupportCenter(world, pos.below(), Direction.UP)
    }

    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, world, pos, random)
        if (random.nextDouble() >= particleChance) {
            val offset = state.getOffset(world, pos)
            world.addParticle(
                ColorableParticleEffect(color),
                pos.x + offset.x + (random.nextDouble() * 0.6 + 0.2),
                pos.y + offset.y + (random.nextDouble() * 0.7 - 0.1),
                pos.z + offset.z + (random.nextDouble() * 0.6 + 0.2),
                (random.nextDouble() - random.nextDouble()) * 0.125,
                (random.nextDouble() * -0.1) - 0.1,
                (random.nextDouble() - random.nextDouble()) * 0.125
            )
        }
    }

    companion object {
        val LARGER_SHAPE: VoxelShape = box(5.0, 0.0, 5.0, 11.0, 9.0, 11.0)
    }
}