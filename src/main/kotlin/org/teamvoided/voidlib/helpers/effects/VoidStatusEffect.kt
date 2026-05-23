package org.teamvoided.voidlib.helpers.effects

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.core.particles.ParticleOptions

open class VoidStatusEffect : MobEffect {
    constructor(type: MobEffectCategory, color: Int) : super(type, color)
    constructor(type: MobEffectCategory, color: Int, particle: ParticleOptions) : super(type, color, particle)
}