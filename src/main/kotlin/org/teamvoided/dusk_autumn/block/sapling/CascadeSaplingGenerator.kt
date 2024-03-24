package org.teamvoided.dusk_autumn.block.sapling

import net.minecraft.block.sapling.LargeTreeSaplingGenerator
import net.minecraft.block.sapling.ThreeWideTreeSaplingGenerator
import net.minecraft.registry.RegistryKey
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.TreeConfiguredFeatures
import org.teamvoided.dusk_autumn.init.worldgen.DuskConfiguredFeature

class CascadeSaplingGenerator : ThreeWideTreeSaplingGenerator() {
    override fun getTreeFeature(random: RandomGenerator?, bees: Boolean): RegistryKey<ConfiguredFeature<*, *>?>? {
        return null
    }

    override fun getThreeWideTreeFeature(
        random: RandomGenerator?,
        bees: Boolean
    ): RegistryKey<ConfiguredFeature<*, *>?>? {
        return if (bees) DuskConfiguredFeature.AUTUMN_TREE_BEES else DuskConfiguredFeature.AUTUMN_TREE
    }
}