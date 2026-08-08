package org.teamvoided.dusks_and_dungeons.world.gen.trunk

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.FloatProvider
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen
import java.util.function.BiConsumer
import java.util.function.Function

class BentTrunkPlacer(
    baseHeight: Int,
    firstRandomHeight: Int,
    secondRandomHeight: Int,
    private val sect: IntProvider,
    private val rootChance: Float,
    private val rootHeight: IntProvider
) : TrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight) {

    override fun type(): TrunkPlacerType<*> {
        return DnDWorldgen.BENT_TRUNK_PLACER
    }

    override fun validTreePos(levelSimulatedReader: LevelSimulatedReader, blockPos: BlockPos): Boolean {
        return super.validTreePos(levelSimulatedReader, blockPos)
    }

    override fun placeTrunk(
        w: LevelSimulatedReader,
        replacer: BiConsumer<BlockPos, BlockState>,
        r: RandomSource,
        height: Int,
        startPos: BlockPos,
        c: TreeConfiguration
    ): List<FoliageAttachment> {
        val mutable = startPos.mutable()
        setDirtAt(w, replacer, r, startPos.below(), c)

        val dir = Direction.Plane.HORIZONTAL.getRandomDirection(r)
        val function = Function { state: BlockState -> state.trySetValue(RotatedPillarBlock.AXIS, dir.axis) }
        val sect = sect.sample(r)

        for (i in 0..height) {
            mutable.above()
            this.placeLog(w, replacer, r, mutable, c)
            if ((i + 1) % sect == 0) {
                if (height - i < sect) break
                mutable.relative(dir)
                this.placeLog(w, replacer, r, mutable, c, function)
            }
        }

        val list: MutableList<FoliageAttachment> = ArrayList()
        //list.add(FoliageAttachment(mutable.above(), 0, false))

        //roots
        Direction.Plane.HORIZONTAL.forEach {
            if (r.nextFloat() <= rootChance) {
                mutable.set(startPos.relative(it).below(2))
                val rootHeight = rootHeight.sample(r)
                for (i in 0..rootHeight) {
                    mutable.above()
                    if (i == rootHeight)
                        this.placeLog(w, replacer, r, mutable, c)
                        { state: BlockState -> state.trySetValue(RotatedPillarBlock.AXIS, it.axis) }
                    else
                        this.placeLog(w, replacer, r, mutable, c)
                }
            }
        }

        return list
    }

    companion object {
        //val CODEC: MapCodec<AngledTrunkPlacer> =
        //    RecordCodecBuilder.mapCodec { trunkPlacerParts(it).apply(it, ::AngledTrunkPlacer) }

        val CODEC: MapCodec<BentTrunkPlacer> = RecordCodecBuilder.mapCodec { instance ->
            trunkPlacerParts(instance)
                .and(IntProvider.codec(1, 15).fieldOf("section_size").forGetter { it.sect })
                .and(Codec.floatRange(0f, 1f).fieldOf("root_chance").forGetter { it.rootChance })
                .and(IntProvider.codec(1, 15).fieldOf("root_height").forGetter { it.rootHeight })
                .apply(instance, ::BentTrunkPlacer)
        }
    }
}
