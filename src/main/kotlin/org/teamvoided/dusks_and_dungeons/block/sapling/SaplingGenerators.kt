package org.teamvoided.dusks_and_dungeons.block.sapling

import net.minecraft.core.Direction
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.grower.TreeGrower
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
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


    val OVERGROWTH_DOWN = singleTree("overgrowth_down", DnDConfiguredFeature.OVERGROWTH_TREE_DOWN)
    val OVERGROWTH = Direction.entries.associateWith { dir ->
        when (dir.get3DDataValue()) {
            1 -> OVERGROWTH_DOWN
            3 -> singleTree("overgrowth_north", DnDConfiguredFeature.OVERGROWTH_TREE_NORTH)
            4 ->  singleTree("overgrowth_south", DnDConfiguredFeature.OVERGROWTH_TREE_SOUTH)
            5 -> singleTree("overgrowth_west", DnDConfiguredFeature.OVERGROWTH_TREE_WEST)
            6 -> singleTree("overgrowth_east", DnDConfiguredFeature.OVERGROWTH_TREE_EAST)
            else -> OVERGROWTH_DOWN
        }
    }

    internal fun singleTree(id: String, tree: ResourceKey<ConfiguredFeature<*, *>>): TreeGrower {
        return TreeGrower(
            id(id).toString(),
            Optional.empty(),
            Optional.of(tree),
            Optional.empty()
        )
    }
}