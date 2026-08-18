package org.teamvoided.dusks_and_dungeons.world.gen.trunk

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
    baseHeight: Int,
    firstRandomHeight: Int,
    secondRandomHeight: Int,
    private val rootChance: Float,
    private val rootHeight: IntProvider,
    private val direction: Direction
) : TrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight) {
    override fun type(): TrunkPlacerType<*> = DnDWorldgen.WALL_TRUNK_PLACER


    override fun placeTrunk(
        w: LevelSimulatedReader,
        replacer: BiConsumer<BlockPos, BlockState>,
        r: RandomSource,
        height: Int,
        startPos: BlockPos,
        c: TreeConfiguration
    ): List<FoliageAttachment> {

        val dirtPos = startPos.relative(direction)
        setDirtAt(w, replacer, r, dirtPos, c)

        val trunkPredicate =
            Function { state: BlockState -> state.trySetValue(RotatedPillarBlock.AXIS, direction.axis) }
        this.placeLog(w, replacer, r, startPos, c, trunkPredicate)

        //roots
        direction.axis.plane.forEach { rootSide ->
            if (r.nextFloat() <= rootChance) {
                val rootHeight = rootHeight.sample(r)
                val rootPredicate = { state: BlockState -> state.trySetValue(RotatedPillarBlock.AXIS, rootSide.axis) }
                this.placeLog(w, replacer, r, startPos.relative(rootSide).relative(direction), c, rootPredicate)
                for (i in rootHeight downTo -rootHeight) {
                    val blockPos = startPos.relative(rootSide, 1).relative(direction, i - 1)
                    if (!validTreePos(w, blockPos)) break
                    this.placeLog(w, replacer, r, blockPos, c)
                }
            }
        }

        //trunk
        var trunkPos: BlockPos = startPos
        for (i in 0..height) {
            if (r.nextFloat() <= (i.toFloat() / height) * 1.3f) {   //going up
                trunkPos = trunkPos.above()
                this.placeLog(w, replacer, r, trunkPos, c)
            } else {                                                //going sideways
                trunkPos = trunkPos.relative(direction.opposite)
                this.placeLog(w, replacer, r, trunkPos, c, trunkPredicate)
            }
        }
        val list: MutableList<FoliageAttachment> = ArrayList()
        list.add(FoliageAttachment(trunkPos.above(), 0, false))
        return list
    }

    companion object {
        val CODEC: MapCodec<WallTrunkPlacer> = RecordCodecBuilder.mapCodec { instance ->
            trunkPlacerParts(instance) //add validator to make it horizon only
                .and(Codec.floatRange(0f, 1f).fieldOf("root_chance").forGetter { it.rootChance })
                .and(IntProvider.codec(1, 15).fieldOf("root_height").forGetter { it.rootHeight })
                .and(Direction.CODEC.fieldOf("direction").forGetter { it.direction })
                .apply(instance, ::WallTrunkPlacer)
        }
    }
}
