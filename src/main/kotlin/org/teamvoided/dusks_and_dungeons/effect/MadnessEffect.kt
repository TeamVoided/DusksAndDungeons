package org.teamvoided.dusks_and_dungeons.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectType
import net.minecraft.particle.ParticleEffect
import org.teamvoided.voidlib.helpers.effects.VoidStatusEffect

class MadnessEffect : VoidStatusEffect {
    constructor(type: StatusEffectType, color: Int) : super(type, color)
    constructor(type: StatusEffectType, color: Int, particle: ParticleEffect) : super(type, color, particle)

    override fun shouldApplyUpdateEffect(tick: Int, amplifier: Int): Boolean = true
    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int): Boolean = true
}
