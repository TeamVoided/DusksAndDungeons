package org.teamvoided.dusks_and_dungeons.init

import com.mojang.serialization.MapCodec
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.Registry
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDFeatures
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.ManhattanFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.OvergrowthFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.AlterOnGroundTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.AttachedToTrunkTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.BeehiveBigTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.FeatureAtBaseTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.FeatureAtTopTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.BentTrunkPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.WallTrunkPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.ThreeWideTrunkPlacer

@Suppress("SameParameterValue")
object DnDWorldgen {

    val ALTER_GROUND_RADIUS = registerTreeDecorator("alter_ground_radius", AlterGroundRadiusTreeDecorator.CODEC)
    val ALTER_ON_GROUND = registerTreeDecorator("alter_on_ground", AlterOnGroundTreeDecorator.CODEC)
    val ATTACHED_TO_TRUNK = registerTreeDecorator("attached_to_trunk", AttachedToTrunkTreeDecorator.CODEC)
    val BEEHIVE_BIG_TREE_DECORATOR =
        registerTreeDecorator("beehive_big_tree_decorator", BeehiveBigTreeDecorator.CODEC)
    val FEATURE_AT_BASE =
        registerTreeDecorator("feature_at_base", FeatureAtBaseTreeDecorator.CODEC)
    val FEATURE_AT_TOP =
        registerTreeDecorator("feature_at_top", FeatureAtTopTreeDecorator.CODEC)

    val THREE_WIDE_TRUNK_PLACER = registerTrunkPlacer("three_wide_trunk_placer", ThreeWideTrunkPlacer.CODEC)
    val WALL_TRUNK_PLACER = registerTrunkPlacer("wall_trunk_placer", WallTrunkPlacer.CODEC)
    val BENT_TRUNK_PLACER = registerTrunkPlacer("bent_trunk_placer", BentTrunkPlacer.CODEC)

    val CASCADE_FOLIAGE_PLACER = registerFoliagePlacer("cascade_foliage_placer", CascadeFoliagePlacer.CODEC)
    val OVERGROWTH_FOLIAGE_PLACER = registerFoliagePlacer("overgrowth_foliage_placer", OvergrowthFoliagePlacer.CODEC)
    val MANHATTAN_FOLIAGE_PLACER = registerFoliagePlacer("manhattan_foliage_placer", ManhattanFoliagePlacer.CODEC)

    val CASCADE_ROOT_PLACER = registerRootPlacer("cascade_root_placer", CascadeRootPlacer.CODEC)


    fun init() {
        DnDBiomes.init()
        DnDFeatures.init()

//        if (isDev()) DnDSurfaceBuilders.init()
    }

    private fun <P : TreeDecorator> registerTreeDecorator(id: String, codec: MapCodec<P>): TreeDecoratorType<P> {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, id(id), TreeDecoratorType(codec))
    }

    private fun <P : TrunkPlacer> registerTrunkPlacer(id: String, codec: MapCodec<P>): TrunkPlacerType<P> {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, id(id), TrunkPlacerType(codec))
    }

    private fun <P : FoliagePlacer> registerFoliagePlacer(id: String, codec: MapCodec<P>): FoliagePlacerType<P> {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, id(id), FoliagePlacerType(codec))
    }

    private fun <P : RootPlacer> registerRootPlacer(id: String, codec: MapCodec<P>): RootPlacerType<P> {
        return Registry.register(BuiltInRegistries.ROOT_PLACER_TYPE, id(id), RootPlacerType(codec))
    }
}
