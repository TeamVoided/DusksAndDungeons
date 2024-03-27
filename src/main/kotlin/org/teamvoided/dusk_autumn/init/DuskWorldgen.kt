package org.teamvoided.dusk_autumn.init

import com.mojang.serialization.Codec
import net.minecraft.registry.*
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import net.minecraft.world.gen.trunk.TrunkPlacer
import net.minecraft.world.gen.trunk.TrunkPlacerType
import net.minecraft.world.gen.foliage.FoliagePlacerType
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.init.worldgen.DuskBiomes
import org.teamvoided.dusk_autumn.init.worldgen.DuskConfiguredFeature
import org.teamvoided.dusk_autumn.init.worldgen.DuskPlacedFeature
import org.teamvoided.dusk_autumn.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterOnGroundTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.trunk.ThreeWideTrunkPlacer


object DuskWorldgen {

    val ALTER_GROUND_RADIUS = registerTreeDecorator("alter_ground_radius", AlterGroundRadiusTreeDecorator.CODEC)
    val ALTER_ON_GROUND = registerTreeDecorator("alter_on_ground", AlterOnGroundTreeDecorator.CODEC)
    val THREE_WIDE_TRUNK_PLACER = registerTrunkPlacer("three_wide_trunk_placer", ThreeWideTrunkPlacer.CODEC)
    val CASCADE_FOLIAGE_PLACER = registerFoliagePlacer("cascade_foliage_placer", CascadeFoliagePlacer.CODEC)


    fun init() {
        DuskBiomes.init()
        DuskConfiguredFeature.init()
        DuskPlacedFeature.init()
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
}