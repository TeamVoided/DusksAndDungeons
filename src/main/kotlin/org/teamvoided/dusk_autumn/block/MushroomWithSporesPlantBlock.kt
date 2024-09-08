package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.MushroomPlantBlock
import net.minecraft.block.ShapeContext
import net.minecraft.registry.RegistryKey
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView
import net.minecraft.world.gen.feature.ConfiguredFeature
import org.teamvoided.dusk_autumn.particle.ColorableParticleEffect

class MushroomWithSporesPlantBlock(
    registryKey: RegistryKey<ConfiguredFeature<*, *>>,
    private val color: Int,
    private val particleChance: Double,
    settings: Settings
) : MushroomPlantBlock(registryKey, settings) {

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val offset = state.getModelOffset(world, pos)
        return LARGER_SHAPE.offset(offset.x, offset.y, offset.z)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        return sideCoversSmallSquare(world, pos.down(), Direction.UP)
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
        if (random.nextDouble() >= particleChance) {
            val offset = state.getModelOffset(world, pos)
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
        val LARGER_SHAPE = createCuboidShape(5.0, 0.0, 5.0, 11.0, 8.0, 11.0)
    }
}