package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.MushroomBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxY

class MushroomWithSporesPlantBlock(
    val color: Int, val particleChance: Double,
    registryKey: ResourceKey<ConfiguredFeature<*, *>>, properties: Properties,
) : MushroomBlock(registryKey, properties) {

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        val offset = state.getOffset(level, pos)
        return LARGER_SHAPE.move(offset.x, 0.0, offset.z)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return canSupportCenter(level, pos.below(), Direction.UP)
    }

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        return level.getBlockState(pos.below()).`is`(DnDBlockTags.GOLD_MUSH_GROW_ON)
    }

    override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (level.getBlockState(pos.below()).`is`(DnDBlockTags.GOLD_MUSH_GROW_FROM)) {
            super.randomTick(state, level, pos, random)
        }
    }

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, level, pos, random)
        if (random.nextDouble() >= particleChance) {
            val offset = state.getOffset(level, pos)
            level.addParticle(
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
        val LARGER_SHAPE: VoxelShape = symmetricalBoxY(5.0, 0.0, 9.0)
    }
}