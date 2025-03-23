package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes.complex
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes.simple
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect
import org.teamvoided.dusks_and_dungeons.particle.DustBunnyParticleEffect
import org.teamvoided.dusks_and_dungeons.particle.SpiralParticleEffect


object DnDParticles {
    val SMALL_SOUL_FLAME_PARTICLE = simple()
    val AUTUMN_LEAF_PARTICLE: DefaultParticleType = simple()
    val CASCADE_LEAF_PARTICLE = simple()
    val COLORABLE_OMINOUS_PARTICLE = complex(ColorableParticleEffect.CODEC, ColorableParticleEffect.PACKET_CODEC)
    val SNOWFLAKE = simple()

    // experimental
    val SPIDERLILY = simple()
    val MUSHROOM_LAUNCH = simple()
    val DUST_BUNNY = complex(DustBunnyParticleEffect.CODEC, DustBunnyParticleEffect.PACKET_CODEC)
    val SPIRAL = complex(SpiralParticleEffect.CODEC, SpiralParticleEffect.PACKET_CODEC)

    fun init() {
        register("small_soul_flame", SMALL_SOUL_FLAME_PARTICLE)
        register("autumn_leaf", AUTUMN_LEAF_PARTICLE)
        register("cascade_leaf", CASCADE_LEAF_PARTICLE)
        register("colorable_ominous_spawning", COLORABLE_OMINOUS_PARTICLE)
        register("snowflake", SNOWFLAKE)

        register("spiderlily", SPIDERLILY)
        register("mushroom_launch", MUSHROOM_LAUNCH)
        register("dust_bunny", DUST_BUNNY)
        register("spiral", SPIRAL)
    }

    fun register(id: String, particleType: ParticleType<*>) =
        Registry.register(Registries.PARTICLE_TYPE, id(id), particleType)
}