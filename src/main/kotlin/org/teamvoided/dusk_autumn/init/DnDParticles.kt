package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.particle.ColorableParticleEffect
import org.teamvoided.dusk_autumn.particle.DustBunnyParticleEffect
import org.teamvoided.dusk_autumn.particle.SpiralParticleEffect


object DnDParticles {
    val SMALL_SOUL_FLAME_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()

    //    val AUTUMN_LEAF_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()
    val CASCADE_LEAF_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()
    val COLORABLE_OMINOUS_PARTICLE: ParticleType<ColorableParticleEffect> =
        FabricParticleTypes.complex(ColorableParticleEffect.CODEC, ColorableParticleEffect.PACKET_CODEC)
    val SPIDERLILY: DefaultParticleType = FabricParticleTypes.simple()
    val SNOWFLAKE: DefaultParticleType = FabricParticleTypes.simple()
    val MUSHROOM_LAUNCH: DefaultParticleType = FabricParticleTypes.simple()
    val DUST_BUNNY: ParticleType<DustBunnyParticleEffect> =
        FabricParticleTypes.complex(DustBunnyParticleEffect.CODEC, DustBunnyParticleEffect.PACKET_CODEC)

    val SPIRAL: ParticleType<SpiralParticleEffect> =
        FabricParticleTypes.complex(SpiralParticleEffect.CODEC, SpiralParticleEffect.PACKET_CODEC)

    fun init() {
        register("small_soul_flame", SMALL_SOUL_FLAME_PARTICLE)
//        register("autumn_leaf", AUTUMN_LEAF_PARTICLE)
        register("cascade_leaf", CASCADE_LEAF_PARTICLE)
        register("colorable_ominous_spawning", COLORABLE_OMINOUS_PARTICLE)
        register("spiderlily", SPIDERLILY)
        register("snowflake", SNOWFLAKE)
        register("mushroom_launch", MUSHROOM_LAUNCH)
        register("dust_bunny", DUST_BUNNY)

        register("spiral", SPIRAL)
    }

    fun register(id: String, particleType: ParticleType<*>) =
        Registry.register(Registries.PARTICLE_TYPE, id(id), particleType)
}