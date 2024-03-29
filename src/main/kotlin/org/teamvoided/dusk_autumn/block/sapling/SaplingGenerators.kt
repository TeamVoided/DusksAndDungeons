package net.minecraft.world.gen.foliage.org.teamvoided.dusk_autumn.block.sapling

import net.minecraft.block.WoodTypes
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.init.worldgen.DuskConfiguredFeature
import java.util.*

object SaplingGenerators {
    val CASCADE = WoodTypes(
        id("cascade").toString(),
        Optional.of(DuskConfiguredFeature.CASCADE_TREE),
        Optional.empty(),
        Optional.of(DuskConfiguredFeature.CASCADE_TREE_BEES)
    )
    val GOLDEN_BIRCH = WoodTypes(
        id("cascade").toString(),
        Optional.empty(),
        Optional.of(DuskConfiguredFeature.GOLDEN_BIRCH_TALL),
        Optional.of(DuskConfiguredFeature.GOLDEN_BIRCH_TALL_BEES)
    )
}