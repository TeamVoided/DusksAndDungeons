package org.teamvoided.dusks_and_dungeons.block.sapling

import net.minecraft.block.sapling.TreeGrower
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature
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