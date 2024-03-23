package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.particle.FallingLeafParticle


object DuskParticles {
    val AUTUMN_BIOME_PARTICLE = FabricParticleTypes.simple()
    val CASCADE_LEAF_PARTICLE = FabricParticleTypes.simple()

    fun init() {
        Registry.register(Registries.PARTICLE_TYPE, id("cascade_leaf_particle"), CASCADE_LEAF_PARTICLE)
    }

    fun initClient() {
        ParticleFactoryRegistry.getInstance()
            .register(CASCADE_LEAF_PARTICLE, ParticleFactoryRegistry.PendingParticleFactory {
                ParticleFactory { _, world, x, y, z, _, _, _ ->
                    FallingLeafParticle(
                        world, x, y, z, it
                    )
                }
            })
    }
}