package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.MushroomPlantBlock
import net.minecraft.block.ShapeContext
import net.minecraft.registry.RegistryKey
import net.minecraft.util.math.BlockPos
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
    ): VoxelShape = LARGER_SHAPE

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val blockPos = pos.down()
        val blockState = world.getBlockState(blockPos)
        return this.canPlantOnTop(blockState, world, blockPos)
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
        if (random.nextDouble() >= particleChance) {
            world.addParticle(
                ColorableParticleEffect(color),
                pos.x + random.nextDouble(),
                pos.y + random.nextDouble(),
                pos.z + random.nextDouble(),
                (random.nextDouble() - random.nextDouble()) * 0.125,
                (random.nextDouble() * -0.1) - 0.1,
                (random.nextDouble() - random.nextDouble()) * 0.125
            )
        }
    }

    companion object {
        val LARGER_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 9.0, 8.0, 9.0)
    }
}