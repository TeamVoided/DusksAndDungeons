package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry.PendingParticleFactory
import net.minecraft.client.particle.FlameParticle
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleType
import org.teamvoided.dusks_and_dungeons.particle.*

object DnDParticlesClient {
    fun init() {
        register(DnDParticles.AUTUMN_LEAF_PARTICLE, AutumnLeafParticle::Factory)
        register(DnDParticles.CASCADE_LEAF_PARTICLE, FallingLeafParticle.Companion::FallingLeafFactory)
        register(DnDParticles.SMALL_SOUL_FLAME_PARTICLE, FlameParticle::SmallFactory)
        register(DnDParticles.SNOWFLAKE, SnowflakeParticle::Factory)
        register(DnDParticles.COLORABLE_OMINOUS_PARTICLE, ColorableOminousParticle::Factory)

    }

    fun <T : ParticleEffect> register(type: ParticleType<T>, constructor: PendingParticleFactory<T>) =
        ParticleFactoryRegistry.getInstance().register(type, constructor)
}
