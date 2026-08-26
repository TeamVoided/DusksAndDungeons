package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes.complex
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes.simple
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.BuiltInRegistries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleEffect
import org.teamvoided.dusks_and_dungeons.particle.ShriekDirectionalParticleEffect
import org.teamvoided.dusks_and_dungeons.util.register


// TODO(1.0) nuke this out of the orbit (the code is so bad that ill die)
object DnDParticles {

    val SMALL_SOUL_FLAME_PARTICLE = simple()
    val AUTUMN_LEAF_PARTICLE: SimpleParticleType = simple()
    val CASCADE_LEAF_PARTICLE = simple()
    val COLORABLE_OMINOUS_PARTICLE = complex(ColorableParticleEffect.CODEC, ColorableParticleEffect.PACKET_CODEC)
    val SNOWFLAKE = simple()

    val SHRIEK_DIRECTIONAL = ShriekDirectionalParticleEffect.REGISTER


    fun init() {
        register("small_soul_flame", SMALL_SOUL_FLAME_PARTICLE)
        register("autumn_leaf", AUTUMN_LEAF_PARTICLE)
        register("cascade_leaf", CASCADE_LEAF_PARTICLE)
        register("colorable_ominous_spawning", COLORABLE_OMINOUS_PARTICLE)
        register("snowflake", SNOWFLAKE)

        register("shriek_directional", SHRIEK_DIRECTIONAL)

    }

    fun register(id: String, particleType: ParticleType<*>): ParticleType<*> {
        return BuiltInRegistries.PARTICLE_TYPE.register(id(id), particleType)
    }

}