package org.teamvoided.dusk_autumn.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffectType
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleEffect
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.Difficulty

class MadnessEffect : DnDStatusEffect {
    constructor(type: StatusEffectType, color: Int) : super(type, color)
    constructor(type: StatusEffectType, color: Int, particle: ParticleEffect) : super(type, color, particle)

    override fun shouldApplyUpdateEffect(tick: Int, amplifier: Int): Boolean = true

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int): Boolean {
        if (entity is PlayerEntity) {

        }

        return true
    }
}