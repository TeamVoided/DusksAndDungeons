package org.teamvoided.dusk_autumn.init

import com.mojang.serialization.Codec
import net.minecraft.registry.*
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer
import net.minecraft.world.gen.trunk.TrunkPlacer
import net.minecraft.world.gen.trunk.TrunkPlacerType
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.init.worldgen.DuskBiomes
import org.teamvoided.dusk_autumn.init.worldgen.DuskConfiguredFeature
import org.teamvoided.dusk_autumn.init.worldgen.DuskPlacedFeature
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterOnGroundTreeDecorator


object DuskWorldgen {

    val ALTER_GROUND_RADIUS = registerTreeDecorator("alter_ground_radius", AlterGroundRadiusTreeDecorator.CODEC)
    val ALTER_ON_GROUND = registerTreeDecorator("alter_on_ground", AlterOnGroundTreeDecorator.CODEC)
    val THREE_WIDE_TRUNK_PLACER = registerTrunkPlacer("three_wide_trunk_placer", DarkOakTrunkPlacer.CODEC)


    fun init() {
        DuskBiomes.init()
        DuskConfiguredFeature.init()
        DuskPlacedFeature.init()
    }

    private fun <P : TreeDecorator> registerTreeDecorator(id: String, codec: Codec<P>): TreeDecoratorType<P> {
        return Registry.register(Registries.TREE_DECORATOR_TYPE, id(id), TreeDecoratorType<P>(codec))
    }
    private fun <P : TrunkPlacer?> registerTrunkPlacer(id: String, codec: Codec<P?>): TrunkPlacerType<P> {
        return Registry.register(Registries.TRUNK_PLACER_TYPE, id(id), TrunkPlacerType<P>(codec))
    }
}