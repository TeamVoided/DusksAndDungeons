package org.teamvoided.dusks_and_dungeons.world.gen.treedcorator

import com.mojang.serialization.MapCodec
import net.minecraft.core.Holder
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_and_dungeons.init.DnDWorldgen


class FeatureAtTopTreeDecorator(private val feature: Holder<PlacedFeature>) : TreeDecorator() {
    override fun type(): TreeDecoratorType<FeatureAtTopTreeDecorator> = DnDWorldgen.FEATURE_AT_TOP

    //honestly gonna make a hardcoded thing cause this class be stupid and only works for the last logs so it be finniky

    override fun place(placer: Context) {
        val worldGenLevel = placer.level() as WorldGenLevel
        feature.value().place(
            worldGenLevel,
            worldGenLevel.level.chunkSource.generator,
            placer.random(),
            placer.logs().last()
        )
    }


    companion object {
        val CODEC: MapCodec<FeatureAtTopTreeDecorator> =
            PlacedFeature.CODEC.fieldOf("feature").xmap(::FeatureAtTopTreeDecorator) { it.feature }
    }
}
