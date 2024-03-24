package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.registry.Holder
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.BlockView
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.ConfiguredFeature

abstract class TEMP : SaplingGenerator() {
    override fun generate(
        world: ServerWorld,
        chunkGenerator: ChunkGenerator,
        pos: BlockPos,
        state: BlockState,
        random: RandomGenerator
    ): Boolean {
        for (i in 0 downTo -1) {
            for (j in 0 downTo -1) {
                if (canGenerateLargeTree(state, world, pos, i, j)) {
                    return this.generateLargeTree(world, chunkGenerator, pos, state, random, i, j)
                }
            }
        }

        return super.generate(world, chunkGenerator, pos, state, random)
    }

    protected abstract fun getLargeTreeFeature(random: RandomGenerator?): RegistryKey<ConfiguredFeature<*, *>>?

    fun generateLargeTree(
        world: ServerWorld,
        chunkGenerator: ChunkGenerator?,
        pos: BlockPos,
        state: BlockState?,
        random: RandomGenerator?,
        x: Int,
        z: Int
    ): Boolean {
        val registryKey = this.getLargeTreeFeature(random)
        if (registryKey == null) {
            return false
        } else {
            val holder: Holder<ConfiguredFeature<*, *>> =
                world.registryManager.get(RegistryKeys.CONFIGURED_FEATURE).getHolder(registryKey)
                    .orElse(null)
            if (holder == null) {
                return false
            } else {
                val configuredFeature = holder.value() as ConfiguredFeature<*, *>
                val blockState = Blocks.AIR.defaultState
                world.setBlockState(pos.add(x, 0, z), blockState, Block.NO_REDRAW)
                world.setBlockState(pos.add(x + 1, 0, z), blockState, Block.NO_REDRAW)
                world.setBlockState(pos.add(x, 0, z + 1), blockState, Block.NO_REDRAW)
                world.setBlockState(pos.add(x + 1, 0, z + 1), blockState, Block.NO_REDRAW)
                if (configuredFeature.generate(world, chunkGenerator, random, pos.add(x, 0, z))) {
                    return true
                } else {
                    world.setBlockState(pos.add(x, 0, z), state, Block.NO_REDRAW)
                    world.setBlockState(pos.add(x + 1, 0, z), state, Block.NO_REDRAW)
                    world.setBlockState(pos.add(x, 0, z + 1), state, Block.NO_REDRAW)
                    world.setBlockState(pos.add(x + 1, 0, z + 1), state, Block.NO_REDRAW)
                    return false
                }
            }
        }
    }

    companion object {
        fun canGenerateLargeTree(state: BlockState, world: BlockView, pos: BlockPos, x: Int, z: Int): Boolean {
            val block = state.block
            return world.getBlockState(pos.add(x, 0, z)).isOf(block) && world.getBlockState(pos.add(x + 1, 0, z))
                .isOf(block) && world.getBlockState(pos.add(x, 0, z + 1))
                .isOf(block) && world.getBlockState(pos.add(x + 1, 0, z + 1)).isOf(block)
        }
    }
}