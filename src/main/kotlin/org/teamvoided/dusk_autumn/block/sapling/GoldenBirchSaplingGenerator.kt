package org.teamvoided.dusk_autumn.block.sapling

import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.registry.RegistryKey
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.TreeConfiguredFeatures
import org.teamvoided.dusk_autumn.init.DuskWorldgen
import org.teamvoided.dusk_autumn.init.worldgen.DuskConfiguredFeature

class GoldenBirchSaplingGenerator : SaplingGenerator() {
    override fun getTreeFeature(random: RandomGenerator, bees: Boolean): RegistryKey<ConfiguredFeature<*, *>?> {
        return if (bees) DuskConfiguredFeature.GOLDEN_BIRCH_TALL_BEES else DuskConfiguredFeature.GOLDEN_BIRCH_TALL
    }
}