package org.teamvoided.dusks_and_dungeons.block.sapling

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SaplingBlock
import net.minecraft.world.level.block.grower.TreeGrower
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature


class ThreeWideTreeSaplingBlock(generator: TreeGrower, settings: Properties) : SaplingBlock(
    generator,
    settings
) {
    override fun advanceTree(world: ServerLevel, pos: BlockPos, state: BlockState, random: RandomSource) {
        if (state.getValue(STAGE) as Int == 0) world.setBlock(pos, state.cycle(STAGE), 4)
        else generate(world, world.chunkSource.generator, pos, state, random)
    }

    fun generate(
        world: ServerLevel,
        chunkGenerator: ChunkGenerator,
        pos: BlockPos,
        state: BlockState,
        random: RandomSource
    ): Boolean {
        for (i in 1 downTo -1) {
            for (j in 1 downTo -1) {
                if (canGenerateLargeTree(state, world, pos, i, j)) {
                    return this.generateThreeWideTree(world, chunkGenerator, pos, state, random, i, j)
                }
            }
        }
        return false
    }

    fun generateThreeWideTree(
        world: ServerLevel,
        chunkGenerator: ChunkGenerator,
        pos: BlockPos,
        state: BlockState,
        random: RandomSource,
        x: Int,
        z: Int
    ): Boolean {
        val registryKey = this.getThreeWideTreeFeature(random, false)
        if (registryKey == null) {
            return false
        } else {
            val holder: Holder<ConfiguredFeature<*, *>> = world
                .registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE)
                .getHolder(registryKey)
                .orElse(null)
            if (holder == null) {
                return false
            } else {
                val configuredFeature = holder.value()
                val blockState = Blocks.AIR.defaultBlockState()
                world.setBlock(pos.offset(x - 1, 0, z - 1), blockState, UPDATE_INVISIBLE)
                world.setBlock(pos.offset(x, 0, z - 1), blockState, UPDATE_INVISIBLE)
                world.setBlock(pos.offset(x + 1, 0, z - 1), blockState, UPDATE_INVISIBLE)
                world.setBlock(pos.offset(x - 1, 0, z), blockState, UPDATE_INVISIBLE)
                world.setBlock(pos.offset(x, 0, z), blockState, UPDATE_INVISIBLE)
                world.setBlock(pos.offset(x + 1, 0, z), blockState, UPDATE_INVISIBLE)
                world.setBlock(pos.offset(x - 1, 0, z + 1), blockState, UPDATE_INVISIBLE)
                world.setBlock(pos.offset(x, 0, z + 1), blockState, UPDATE_INVISIBLE)
                world.setBlock(pos.offset(x + 1, 0, z + 1), blockState, UPDATE_INVISIBLE)
                if (configuredFeature.place(world, chunkGenerator, random, pos.offset(x, 0, z))) {
                    return true
                } else {
                    world.setBlock(pos.offset(x - 1, 0, z - 1), state, UPDATE_INVISIBLE)
                    world.setBlock(pos.offset(x, 0, z - 1), state, UPDATE_INVISIBLE)
                    world.setBlock(pos.offset(x + 1, 0, z - 1), state, UPDATE_INVISIBLE)
                    world.setBlock(pos.offset(x - 1, 0, z), state, UPDATE_INVISIBLE)
                    world.setBlock(pos.offset(x, 0, z), state, UPDATE_INVISIBLE)
                    world.setBlock(pos.offset(x + 1, 0, z), state, UPDATE_INVISIBLE)
                    world.setBlock(pos.offset(x - 1, 0, z + 1), state, UPDATE_INVISIBLE)
                    world.setBlock(pos.offset(x, 0, z + 1), state, UPDATE_INVISIBLE)
                    world.setBlock(pos.offset(x + 1, 0, z + 1), state, UPDATE_INVISIBLE)
                    return false
                }
            }
        }
    }

    fun getThreeWideTreeFeature(random: RandomSource, bees: Boolean): ResourceKey<ConfiguredFeature<*, *>> =
        if (bees) DnDConfiguredFeature.CASCADE_TREE_AUTUMN else DnDConfiguredFeature.CASCADE_TREE

    companion object {
        fun canGenerateLargeTree(state: BlockState, world: BlockGetter, pos: BlockPos, x: Int, z: Int): Boolean {
            val block = state.block
            return world.getBlockState(pos.offset(x - 1, 0, z - 1)).`is`(block) &&
                    world.getBlockState(pos.offset(x, 0, z - 1)).`is`(block) &&
                    world.getBlockState(pos.offset(x + 1, 0, z - 1)).`is`(block) &&
                    world.getBlockState(pos.offset(x - 1, 0, z)).`is`(block) &&
                    world.getBlockState(pos.offset(x, 0, z)).`is`(block) &&
                    world.getBlockState(pos.offset(x + 1, 0, z)).`is`(block) &&
                    world.getBlockState(pos.offset(x - 1, 0, z + 1)).`is`(block) &&
                    world.getBlockState(pos.offset(x, 0, z + 1)).`is`(block) &&
                    world.getBlockState(pos.offset(x + 1, 0, z + 1)).`is`(block)
        }
    }
}