package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.MushroomFeatureConfig

open class AbstractHugeMushroomFeature<T : MushroomFeatureConfig>(codec: Codec<T>) : Feature<T>(codec) {

    override fun place(ctx: FeaturePlaceContext<T>): Boolean {
        val level = ctx.level()
        val originPos = ctx.origin()
        val random = ctx.random()
        val config = ctx.config()
        val stemHeight = config.stemHeight.sample(random)
        val capHeight = config.capHeight.sample(random)
        val mutable = BlockPos.MutableBlockPos()

        if (canGenerate(level, originPos, stemHeight, capHeight, mutable, config)) {
            generateCap(level, random, originPos, stemHeight, capHeight, mutable, config)
            generateStem(level, random, originPos, stemHeight, mutable, config)
            return true
        }
        return false
    }

    open fun canGenerate(
        world: LevelAccessor,
        pos: BlockPos,
        stemHeight: Int,
        capHeight: Int,
        mutablePos: BlockPos.MutableBlockPos,
        config: T,
    ): Boolean {
        val y = pos.y
        if (y >= world.minBuildHeight + 1 && y + stemHeight + 1 < world.maxBuildHeight) {
            for (j in 0..stemHeight) {
                val blockState2 = world.getBlockState(mutablePos.setWithOffset(pos, 0, j, 0))
                if (!blockState2.`is`(config.ignores)) {
                    return false
                }
            }
            return true
        }
        return false
    }

    open fun generateCap(
        level: LevelAccessor,
        random: RandomSource,
        start: BlockPos,
        stemHeight: Int,
        capHeight: Int,
        mutablePos: BlockPos.MutableBlockPos,
        config: T
    ) {
        mutablePos.setWithOffset(start, 0, capHeight, 0)
        if (level.getBlockState(mutablePos).`is`(config.replaceable)) {
            setBlock(level, mutablePos, config.capBlock.getState(random, start))
        }
    }

    open fun generateStem(
        level: LevelAccessor,
        random: RandomSource,
        pos: BlockPos,
        capHeight: Int,
        mutablePos: BlockPos.MutableBlockPos,
        config: T
    ) {
        for (i in 0 until capHeight) {
            mutablePos.set(pos).move(Direction.UP, i)
            if (level.getBlockState(mutablePos).`is`(config.replaceable)) {
                setBlock(level, mutablePos, config.stemBlock.getState(random, pos))
            }
        }
    }

}