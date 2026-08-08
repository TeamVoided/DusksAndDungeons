package org.teamvoided.dusks_and_dungeons.world.gen.trunk

import com.google.common.collect.ImmutableList
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
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

class WallTrunkPlacer(
    i: Int,
    j: Int,
    k: Int,
    private val direction: Direction,
    private val sect: IntProvider,
    private val rootChance: Float,
    private val rootHeight: IntProvider
) : TrunkPlacer(i, j, k) {
    override fun type(): TrunkPlacerType<*> {
        return DnDWorldgen.WALL_TRUNK_PLACER
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

        val dirtPos = mutable.move(direction)
        setDirtAt(w, replacer, r, dirtPos, c)

        for (i in 0..height) {
            if (r.nextFloat() <= (i / height)) {//going up
                mutable.above()
                this.placeLog(w, replacer, r, mutable, c)
            } else {//going sideways
                val dir = direction.opposite
                mutable.relative(dir)
                val function = Function { state: BlockState -> state.trySetValue(RotatedPillarBlock.AXIS, dir.axis) }
                this.placeLog(w, replacer, r, mutable, c, function)
            }
        }
        val list: MutableList<FoliageAttachment> = ArrayList()
        //list.add(FoliageAttachment(mutable.above(), 0, false))

        //roots
        direction.axis.plane.forEach {
            if (r.nextFloat() <= rootChance) {
                mutable.set(startPos.relative(it).relative(direction, 2))
                val rootHeight = rootHeight.sample(r)
                for (i in 0..rootHeight) {
                    mutable.relative(direction.opposite)
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

        val CODEC: MapCodec<WallTrunkPlacer> = RecordCodecBuilder.mapCodec { instance ->
            trunkPlacerParts(instance) //add validator to make it horizon only
                .and(Direction.CODEC.fieldOf("direction").forGetter { it.direction })
                .and(IntProvider.codec(1, 15).fieldOf("section_size").forGetter { it.sect })
                .and(Codec.floatRange(0f, 1f).fieldOf("root_chance").forGetter { it.rootChance })
                .and(IntProvider.codec(1, 15).fieldOf("root_height").forGetter { it.rootHeight })
                .apply(instance, ::WallTrunkPlacer)
        }
    }
}
