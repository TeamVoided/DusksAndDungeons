package org.teamvoided.dusks_and_dungeons.block.sapling

import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SaplingBlock
import net.minecraft.world.level.block.grower.TreeGrower
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature

open class ThreeWideTreeSaplingBlock(grower: TreeGrower, settings: Properties) : SaplingBlock(grower, settings) {

    override fun advanceTree(level: ServerLevel, pos: BlockPos, state: BlockState, random: RandomSource) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4)
        } else {
            generate(level, level.chunkSource.generator, pos, state, random)
        }
    }

    fun generate(
        level: ServerLevel, generator: ChunkGenerator, pos: BlockPos, state: BlockState, random: RandomSource,
    ): Boolean {
        for (x in 1 downTo -1) {
            for (y in 1 downTo -1) {
                if (canGenerateLargeTree(state, level, pos, x, y)) {
                    return generateThreeWideTree(level, generator, pos, state, random, x, y)
                }
            }
        }
        return false
    }

    fun generateThreeWideTree(
        level: ServerLevel, generator: ChunkGenerator,
        pos: BlockPos, state: BlockState, random: RandomSource, x: Int, z: Int,
    ): Boolean {
        val key = getThreeWideTreeFeature(random, false)
        val holder = level
            .registryAccess()
            .registryOrThrow(Registries.CONFIGURED_FEATURE)
            .getHolder(key)
            .orElse(null)
            ?: return false

        val configuredFeature = holder.value()
        val blockState = Blocks.AIR.defaultBlockState()
        level.setBlock(pos.offset(x - 1, 0, z - 1), blockState, UPDATE_INVISIBLE)
        level.setBlock(pos.offset(x, 0, z - 1), blockState, UPDATE_INVISIBLE)
        level.setBlock(pos.offset(x + 1, 0, z - 1), blockState, UPDATE_INVISIBLE)
        level.setBlock(pos.offset(x - 1, 0, z), blockState, UPDATE_INVISIBLE)
        level.setBlock(pos.offset(x, 0, z), blockState, UPDATE_INVISIBLE)
        level.setBlock(pos.offset(x + 1, 0, z), blockState, UPDATE_INVISIBLE)
        level.setBlock(pos.offset(x - 1, 0, z + 1), blockState, UPDATE_INVISIBLE)
        level.setBlock(pos.offset(x, 0, z + 1), blockState, UPDATE_INVISIBLE)
        level.setBlock(pos.offset(x + 1, 0, z + 1), blockState, UPDATE_INVISIBLE)
        if (configuredFeature.place(level, generator, random, pos.offset(x, 0, z))) {
            return true
        } else {
            level.setBlock(pos.offset(x - 1, 0, z - 1), state, UPDATE_INVISIBLE)
            level.setBlock(pos.offset(x, 0, z - 1), state, UPDATE_INVISIBLE)
            level.setBlock(pos.offset(x + 1, 0, z - 1), state, UPDATE_INVISIBLE)
            level.setBlock(pos.offset(x - 1, 0, z), state, UPDATE_INVISIBLE)
            level.setBlock(pos.offset(x, 0, z), state, UPDATE_INVISIBLE)
            level.setBlock(pos.offset(x + 1, 0, z), state, UPDATE_INVISIBLE)
            level.setBlock(pos.offset(x - 1, 0, z + 1), state, UPDATE_INVISIBLE)
            level.setBlock(pos.offset(x, 0, z + 1), state, UPDATE_INVISIBLE)
            level.setBlock(pos.offset(x + 1, 0, z + 1), state, UPDATE_INVISIBLE)
            return false
        }
    }

    open fun getThreeWideTreeFeature(random: RandomSource, bees: Boolean): ResourceKey<ConfiguredFeature<*, *>> {
        return if (bees) DnDConfiguredFeature.CASCADE_TREE_AUTUMN else DnDConfiguredFeature.CASCADE_TREE
    }

    companion object {

        fun canGenerateLargeTree(state: BlockState, level: BlockGetter, pos: BlockPos, x: Int, z: Int): Boolean {
            val block = state.block
            return level.getBlockState(pos.offset(x - 1, 0, z - 1)).`is`(block) &&
                    level.getBlockState(pos.offset(x, 0, z - 1)).`is`(block) &&
                    level.getBlockState(pos.offset(x + 1, 0, z - 1)).`is`(block) &&
                    level.getBlockState(pos.offset(x - 1, 0, z)).`is`(block) &&
                    level.getBlockState(pos.offset(x, 0, z)).`is`(block) &&
                    level.getBlockState(pos.offset(x + 1, 0, z)).`is`(block) &&
                    level.getBlockState(pos.offset(x - 1, 0, z + 1)).`is`(block) &&
                    level.getBlockState(pos.offset(x, 0, z + 1)).`is`(block) &&
                    level.getBlockState(pos.offset(x + 1, 0, z + 1)).`is`(block)
        }

    }
}