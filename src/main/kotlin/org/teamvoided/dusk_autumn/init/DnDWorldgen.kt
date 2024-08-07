package org.teamvoided.dusk_autumn.init

import com.mojang.serialization.MapCodec
import net.minecraft.block.Blocks
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.feature.org.teamvoided.dusk_autumn.init.worldgen.DnDSurfaceBuilders
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import net.minecraft.world.gen.root.RootPlacer
import net.minecraft.world.gen.root.RootPlacerType
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import net.minecraft.world.gen.trunk.TrunkPlacer
import net.minecraft.world.gen.trunk.TrunkPlacerType
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.init.worldgen.DnDBiomes
import org.teamvoided.dusk_autumn.init.worldgen.DnDFeatures
import org.teamvoided.dusk_autumn.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusk_autumn.world.gen.foliage.ManhattanFoliagePlacer
import org.teamvoided.dusk_autumn.world.gen.root.CascadeRootPlacer
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterOnGroundTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AttachedToTrunkTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.BeehiveBigTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.trunk.ThreeWideTrunkPlacer
import org.teamvoided.reef.events.CustomSurfaceBuilder


object DnDWorldgen {

    val ALTER_GROUND_RADIUS = registerTreeDecorator("alter_ground_radius", AlterGroundRadiusTreeDecorator.CODEC)
    val ALTER_ON_GROUND = registerTreeDecorator("alter_on_ground", AlterOnGroundTreeDecorator.CODEC)
    val ATTACHED_TO_TRUNK = registerTreeDecorator("attached_to_trunk", AttachedToTrunkTreeDecorator.CODEC)
    val BEEHIVE_BIG_TREE_DECORATOR =
        registerTreeDecorator("beehive_big_tree_decorator", BeehiveBigTreeDecorator.CODEC)

    val THREE_WIDE_TRUNK_PLACER = registerTrunkPlacer("three_wide_trunk_placer", ThreeWideTrunkPlacer.CODEC)

    val CASCADE_FOLIAGE_PLACER = registerFoliagePlacer("cascade_foliage_placer", CascadeFoliagePlacer.CODEC)
    val MANHATTAN_FOLIAGE_PLACER = registerFoliagePlacer("manhattan_foliage_placer", ManhattanFoliagePlacer.CODEC)

    val CASCADE_ROOT_PLACER = registerRootPlacer("cascade_root_placer", CascadeRootPlacer.CODEC)


    fun init() {
        DnDBiomes.init()
        DnDFeatures.init()
        DnDSurfaceBuilders.init()
    }

    private fun <P : TreeDecorator> registerTreeDecorator(id: String, codec: MapCodec<P>): TreeDecoratorType<P> {
        return Registry.register(Registries.TREE_DECORATOR_TYPE, id(id), TreeDecoratorType(codec))
    }

    private fun <P : TrunkPlacer> registerTrunkPlacer(id: String, codec: MapCodec<P>): TrunkPlacerType<P> {
        return Registry.register(Registries.TRUNK_PLACER_TYPE, id(id), TrunkPlacerType(codec))
    }

    private fun <P : FoliagePlacer> registerFoliagePlacer(id: String, codec: MapCodec<P>): FoliagePlacerType<P> {
        return Registry.register(Registries.FOLIAGE_PLACER_TYPE, id(id), FoliagePlacerType(codec))
    }

    private fun <P : RootPlacer> registerRootPlacer(id: String, codec: MapCodec<P>): RootPlacerType<P> {
        return Registry.register(Registries.ROOT_PLACER_TYPE, id(id), RootPlacerType(codec))
    }
}
