package org.teamvoided.dusks_and_dungeons.effect

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.core.particles.ParticleOptions
import org.teamvoided.voidlib.helpers.effects.VoidStatusEffect

class MadnessEffect : VoidStatusEffect {
    constructor(type: MobEffectCategory, color: Int) : super(type, color)
    constructor(type: MobEffectCategory, color: Int, particle: ParticleOptions) : super(type, color, particle)

    override fun shouldApplyEffectTickThisTick(tick: Int, amplifier: Int): Boolean = true
    override fun applyEffectTick(entity: LivingEntity, amplifier: Int): Boolean = true
}
