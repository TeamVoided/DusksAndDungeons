package org.teamvoided.dusk_autumn.init

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectType
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DuskAutumns
import org.teamvoided.dusk_autumn.effect.DnDStatusEffect

object DnDEffects {
    fun init() = Unit

    //    val SLAM_JUMP = register(
//        "slam_jump", AstralStatusEffect(StatusEffectType.BENEFICIAL, 6684672)
//            .addAttributeModifier(
//                EntityAttributes.GENERIC_JUMP_STRENGTH, id("effect.jump"),
//                0.1, EntityAttributeModifier.Operation.ADD_VALUE
//            )
//    )
//    val UNHEALABLE_DAMAGE = register(
//        "unhealable_damage", AstralStatusEffect(StatusEffectType.NEUTRAL, 6684672)
//            .addAttributeModifier(
//                EntityAttributes.GENERIC_MAX_HEALTH, id("effect.unhealable"),
//                -0.5, EntityAttributeModifier.Operation.ADD_VALUE
//            )
//    )
//    val HARD_DAMAGE = register(
//        "hard_damage", AstralStatusEffect(StatusEffectType.NEUTRAL, 6684672)
//            .addAttributeModifier(
//                EntityAttributes.GENERIC_MAX_HEALTH, id("effect.weak_hard"),
//                -0.05, EntityAttributeModifier.Operation.ADD_VALUE
//            )
//    )
//    val OVERHEAL = register(
//        "overheal", AstralStatusEffect(StatusEffectType.BENEFICIAL, 6684672)
//            .addAttributeModifier(
//                EntityAttributes.GENERIC_MAX_ABSORPTION, id("effect.overheal"),
//                0.2, EntityAttributeModifier.Operation.ADD_VALUE
//            )
//    )
//    val BLEED = register(
//        "bleed", BleedStatusEffect(0x660000)
//    )
//    val JUMP_REDUCE = register(
//        "jump_reduce", DnDStatusEffect(StatusEffectType.HARMFUL, 0x660000)
//    )
    val JUMP_REDUCE = register(
        "jump_reduce", DnDStatusEffect(StatusEffectType.HARMFUL, 0xC6C62B)
            .addAttributeModifier(
                EntityAttributes.GENERIC_SAFE_FALL_DISTANCE,
                DuskAutumns.id("effect.jump_reduce"),
                -1.0, EntityAttributeModifier.Operation.ADD_VALUE
            )
    )

    private fun register(id: String, entry: StatusEffect): Holder<StatusEffect> {
        return Registry.registerHolder(Registries.STATUS_EFFECT, DuskAutumns.id(id), entry)
    }
//    fun modifyDamage(entity: LivingEntity, damage: Float): Float {
//        var output = damage
//        if (entity.hasStatusEffect(REDUCE))
//            output *= 1.3f
//        return output
//    }
}