package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.DefaultParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DuskAutumns.id


object DuskParticles {
    val AUTUMN_BIOME_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()
    val CASCADE_LEAF_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()
    val SMALL_SOUL_FLAME_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()

    fun init() {
        Registry.register(Registries.PARTICLE_TYPE, id("cascade_leaf_particle"), CASCADE_LEAF_PARTICLE)
    }
}