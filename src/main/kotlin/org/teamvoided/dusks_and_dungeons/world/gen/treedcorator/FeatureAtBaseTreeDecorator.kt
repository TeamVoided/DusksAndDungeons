package org.teamvoided.dusks_and_dungeons.world.gen.treedcorator

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen


class FeatureAtBaseTreeDecorator(private val feature: Holder<PlacedFeature>) : TreeDecorator() {
    override fun type(): TreeDecoratorType<FeatureAtBaseTreeDecorator> = DnDWorldgen.FEATURE_AT_BASE

    override fun place(placer: Context) {
        val roots: MutableList<BlockPos> = placer.roots()
        val logs: MutableList<BlockPos> = placer.logs()
        val pos = if (roots.isEmpty() || !logs.isEmpty() && roots[0].y == logs[0].y) {
            logs[0]
        } else {
            roots[0]
        }

        val worldGenLevel = placer.level() as WorldGenLevel
        feature.value().place(
            worldGenLevel,
            worldGenLevel.level.chunkSource.generator,
            placer.random(),
            pos
        )
    }


    companion object {
        val CODEC: MapCodec<FeatureAtBaseTreeDecorator> =
            PlacedFeature.CODEC.fieldOf("feature").xmap(::FeatureAtBaseTreeDecorator) { it.feature }
    }
}
