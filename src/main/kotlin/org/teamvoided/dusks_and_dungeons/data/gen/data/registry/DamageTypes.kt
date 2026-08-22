package org.teamvoided.dusks_and_dungeons.data.gen.data.registry

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.damagesource.DamageEffects
import net.minecraft.world.damagesource.DamageScaling
import net.minecraft.world.damagesource.DamageType
import org.teamvoided.dusks_and_dungeons.data.registry.DnDDamageTypes

object DamageTypes : RegistryBootstrapper<DamageType> {

    override fun BootstrapContext<DamageType>.init() {
        damage(DnDDamageTypes.FISSURE, 0.0f)
    }

    fun BootstrapContext<DamageType>.damage(
        key: ResourceKey<DamageType>,
        exhaustion: Float,
        effect: DamageEffects = DamageEffects.HURT,
    ) {
        register(key, DamageType(key.location().path, DamageScaling.NEVER, exhaustion, effect))
    }

}