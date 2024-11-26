package org.teamvoided.voidlib.helpers.effects

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectType
import net.minecraft.particle.ParticleEffect

open class VoidStatusEffect : StatusEffect {
    constructor(type: StatusEffectType, color: Int) : super(type, color)
    constructor(type: StatusEffectType, color: Int, particle: ParticleEffect) : super(type, color, particle)
}