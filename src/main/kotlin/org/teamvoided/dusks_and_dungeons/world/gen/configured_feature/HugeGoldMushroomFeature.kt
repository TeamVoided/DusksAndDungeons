package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.MushroomFeatureConfig

open class HugeGoldMushroomFeature(codec: Codec<MushroomFeatureConfig>) :
    AbstractHugeMushroomFeature<MushroomFeatureConfig>(codec) {

    override fun generateCap(
        level: LevelAccessor,
        random: RandomSource,
        start: BlockPos,
        stemHeight: Int,
        capHeight: Int,
        mutablePos: BlockPos.MutableBlockPos,
        config: MushroomFeatureConfig,
    ) {
        val isBig = capHeight < 4 && random.nextBoolean()
        val radius = if (isBig) 2 else 1

        for (x in -radius..radius) {
            val edgePosX = x == radius
            val edgeNegX = x == -radius
            val edgeX = edgeNegX || edgePosX
            for (z in -radius..radius) {
                val edgePosZ = z == radius
                val edgeNegZ = z == -radius
                val edgeZ = edgeNegZ || edgePosZ
                for (y in 0..capHeight) {
                    val edgePosY = y == capHeight
                    mutablePos.setWithOffset(start, x, y + stemHeight, z)
                    if (level.getBlockState(mutablePos).`is`(config.replaceable)) {
                        if (!isBig || !edgePosY) {
                            val cornerAbove = edgePosY || (isBig && (edgeX && edgeZ) && y == capHeight - 1)

                            val capState = config.capBlock.getState(random, start)
                                .trySetValue(BlockStateProperties.WEST, edgeNegX)
                                .trySetValue(BlockStateProperties.EAST, edgePosX)
                                .trySetValue(BlockStateProperties.NORTH, edgeNegZ)
                                .trySetValue(BlockStateProperties.SOUTH, edgePosZ)
                                .trySetValue(BlockStateProperties.UP, cornerAbove)
                                .trySetValue(BlockStateProperties.DOWN, false)
                            setBlock(level, mutablePos, capState)
                        } else if (!(edgeX && edgeZ)) {
                            val corner1 = edgeNegX || edgeZ && x == 1 - radius
                            val corner2 = edgePosX || edgeZ && x == radius - 1
                            val corner3 = edgeNegZ || edgeX && z == 1 - radius
                            val corner4 = edgePosZ || edgeX && z == radius - 1

                            val state = config.capBlock.getState(random, start)
                                .trySetValue(BlockStateProperties.WEST, corner1)
                                .trySetValue(BlockStateProperties.EAST, corner2)
                                .trySetValue(BlockStateProperties.NORTH, corner3)
                                .trySetValue(BlockStateProperties.SOUTH, corner4)
                                .trySetValue(BlockStateProperties.UP, true)
                                .trySetValue(BlockStateProperties.DOWN, false)
                            setBlock(level, mutablePos, state)
                        }
                    }
                }
            }
        }
    }

    override fun canGenerate(
        world: LevelAccessor,
        pos: BlockPos,
        stemHeight: Int,
        capHeight: Int,
        mutablePos: BlockPos.MutableBlockPos,
        config: MushroomFeatureConfig
    ): Boolean {
        if (super.canGenerate(world, pos, stemHeight, capHeight, mutablePos, config)) return true

        if (pos.y >= world.minBuildHeight + 1 && pos.y + stemHeight + capHeight + 1 < world.maxBuildHeight) {
            for (y in 0..capHeight) {
                for (x in -1..1) {
                    for (z in -1..1) {
                        val blockState2 = world.getBlockState(mutablePos.setWithOffset(pos, x, y + stemHeight, z))
                        if (!blockState2.`is`(config.ignores)) {
                            return false
                        }
                    }
                }
            }
            return true
        }
        return false
    }
}