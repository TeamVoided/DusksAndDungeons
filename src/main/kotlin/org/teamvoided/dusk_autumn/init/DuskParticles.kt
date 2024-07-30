package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DuskAutumns.id


object DuskParticles {
    val AUTUMN_BIOME_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()
    val CASCADE_LEAF_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()
    val SMALL_SOUL_FLAME_PARTICLE: DefaultParticleType = FabricParticleTypes.simple()

    fun init() {
        register("cascade_leaf", CASCADE_LEAF_PARTICLE)
        register("small_soul_flame", SMALL_SOUL_FLAME_PARTICLE)
    }


    fun register(id: String, particleType: ParticleType<*>) =
        Registry.register(Registries.PARTICLE_TYPE, id(id), particleType)
}
