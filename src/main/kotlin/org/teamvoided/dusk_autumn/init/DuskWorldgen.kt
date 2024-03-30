package org.teamvoided.dusk_autumn.init

import com.mojang.serialization.Codec
import net.minecraft.registry.*
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import net.minecraft.world.gen.trunk.TrunkPlacer
import net.minecraft.world.gen.trunk.TrunkPlacerType
import net.minecraft.world.gen.foliage.FoliagePlacerType
import net.minecraft.world.gen.root.RootPlacer
import net.minecraft.world.gen.root.RootPlacerType
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.init.worldgen.DuskBiomes
import org.teamvoided.dusk_autumn.init.worldgen.DuskConfiguredFeature
import org.teamvoided.dusk_autumn.init.worldgen.DuskFeatures
import org.teamvoided.dusk_autumn.init.worldgen.DuskPlacedFeature
import org.teamvoided.dusk_autumn.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusk_autumn.world.gen.root.CascadeRootPlacer
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterOnGroundTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AttachedToTrunkTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.BeehiveBigTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.trunk.ThreeWideTrunkPlacer


object DuskWorldgen {

    val ALTER_GROUND_RADIUS = registerTreeDecorator("alter_ground_radius", AlterGroundRadiusTreeDecorator.CODEC)
    val ALTER_ON_GROUND = registerTreeDecorator("alter_on_ground", AlterOnGroundTreeDecorator.CODEC)
    val ATTACHED_TO_TRUNK = registerTreeDecorator("attached_to_trunk", AttachedToTrunkTreeDecorator.CODEC)
    val BEEHIVE_TREE_DECORATOR_NOT_NULL =
        registerTreeDecorator("beehive_tree_decorator_not_null", BeehiveBigTreeDecorator.CODEC)

    val THREE_WIDE_TRUNK_PLACER = registerTrunkPlacer("three_wide_trunk_placer", ThreeWideTrunkPlacer.CODEC)

    val CASCADE_FOLIAGE_PLACER = registerFoliagePlacer("cascade_foliage_placer", CascadeFoliagePlacer.CODEC)

    val CASCADE_ROOT_PLACER = registerRootPlacer("cascade_root_placer", CascadeRootPlacer.CODEC)


    fun init() {
        DuskBiomes.init()
        DuskConfiguredFeature.init()
        DuskPlacedFeature.init()
        DuskFeatures.init()
    }

    private fun <P : TreeDecorator> registerTreeDecorator(id: String, codec: Codec<P>): TreeDecoratorType<P> {
        return Registry.register(Registries.TREE_DECORATOR_TYPE, id(id), TreeDecoratorType(codec))
    }

    private fun <P : TrunkPlacer> registerTrunkPlacer(id: String, codec: Codec<P>): TrunkPlacerType<P> {
        return Registry.register(Registries.TRUNK_PLACER_TYPE, id(id), TrunkPlacerType(codec))
    }

    private fun <P : FoliagePlacer> registerFoliagePlacer(id: String, codec: Codec<P>): FoliagePlacerType<P> {
        return Registry.register(Registries.FOLIAGE_PLACER_TYPE, id(id), FoliagePlacerType(codec))
    }

    private fun <P : RootPlacer> registerRootPlacer(id: String, codec: Codec<P>): RootPlacerType<P> {
        return Registry.register(Registries.ROOT_PLACER_TYPE, id(id), RootPlacerType(codec))
    }
}