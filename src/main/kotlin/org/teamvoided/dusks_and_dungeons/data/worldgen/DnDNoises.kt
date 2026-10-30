package org.teamvoided.dusks_and_dungeons.data.worldgen

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.synth.NormalNoise
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDNoises {

    val AUTUMN = create("biome_parameter/autumn")
    val OVERGROWN_GROTTO = create("biome_parameter/overgrown_grotto")

    fun create(id: String): ResourceKey<NormalNoise.NoiseParameters> = Registries.NOISE.key(id(id))

}