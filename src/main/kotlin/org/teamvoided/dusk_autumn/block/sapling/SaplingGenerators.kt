package org.teamvoided.dusk_autumn.block.sapling

import net.minecraft.block.sapling.TreeGrower
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.data.worldgen.DnDConfiguredFeature
import java.util.*

object SaplingGenerators {

    val CASCADE = TreeGrower(
        id("cascade").toString(),
        Optional.of(DnDConfiguredFeature.CASCADE_TREE),
        Optional.empty(),
        Optional.of(DnDConfiguredFeature.CASCADE_TREE_BEES)
    )
    val GOLDEN_BIRCH = TreeGrower(
        id("golden_birch").toString(),
        Optional.empty(),
        Optional.of(DnDConfiguredFeature.GOLDEN_BIRCH_TALL),
        Optional.of(DnDConfiguredFeature.GOLDEN_BIRCH_TALL_BEES)
    )
}