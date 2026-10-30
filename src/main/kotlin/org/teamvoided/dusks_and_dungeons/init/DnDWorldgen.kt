package org.teamvoided.dusks_and_dungeons.init

import com.mojang.serialization.MapCodec
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.worldgen.*
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.ManhattanFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.OvergrowthFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.BentTrunkPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.ThreeWideTrunkPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.WallTrunkPlacer

object DnDWorldgen {

    val THREE_WIDE_TRUNK_PLACER = registerTrunkPlacer("three_wide_trunk_placer", ThreeWideTrunkPlacer.CODEC)
    val WALL_TRUNK_PLACER = registerTrunkPlacer("wall_trunk_placer", WallTrunkPlacer.CODEC)
    val BENT_TRUNK_PLACER = registerTrunkPlacer("bent_trunk_placer", BentTrunkPlacer.CODEC)

    val CASCADE_FOLIAGE_PLACER = registerFoliagePlacer("cascade_foliage_placer", CascadeFoliagePlacer.CODEC)
    val OVERGROWTH_FOLIAGE_PLACER = registerFoliagePlacer("overgrowth_foliage_placer", OvergrowthFoliagePlacer.CODEC)
    val MANHATTAN_FOLIAGE_PLACER = registerFoliagePlacer("manhattan_foliage_placer", ManhattanFoliagePlacer.CODEC)

    val CASCADE_ROOT_PLACER = registerRootPlacer("cascade_root_placer", CascadeRootPlacer.CODEC)


    fun init() {
        DnDTreeDecoratorTypes.init()

        DnDFeatures.init()

        DnDDensityFunctionTypes.init()

        DnDStructureTypes.init()
        DnDStructurePiceTypes.init()

        DnDBiomeModifications.init()
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