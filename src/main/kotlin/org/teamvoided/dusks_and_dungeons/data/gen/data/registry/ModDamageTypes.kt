package org.teamvoided.dusks_and_dungeons.data.gen.data.registry

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.damagesource.DamageEffects
import net.minecraft.world.damagesource.DamageScaling
import net.minecraft.world.damagesource.DamageType
import org.teamvoided.dusks_and_dungeons.data.gen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.registry.DnDDamageTypes
import org.teamvoided.dusks_and_dungeons.util.toLangKey

object ModDamageTypes : RegistryBootstrapper<DamageType> {

    override fun BootstrapContext<DamageType>.init() {
        damage(DnDDamageTypes.THROWN_BRICK, 0.1f)
    }

    fun BootstrapContext<DamageType>.damage(
        key: ResourceKey<DamageType>,
        exhaustion: Float = 0.1f, //if consistant like fire or poison, 0f
        effect: DamageEffects = DamageEffects.HURT,
    ) {
        register(key, DamageType(key.toLangKey(), DamageScaling.NEVER, exhaustion, effect))
    }

}