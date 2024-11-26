package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDEffects {
    fun init() = Unit
   /* val JUMP_REDUCE = register(
        "jump_reduce", DnDStatusEffect(StatusEffectType.HARMFUL, 0x99990F)
            .addAttributeModifier(
                EntityAttributes.GENERIC_SAFE_FALL_DISTANCE,
                DusksAndDungeons.id("effect.jump_reduce"),
                -1.0, EntityAttributeModifier.Operation.ADD_VALUE
            )
            .addAttributeModifier(
                EntityAttributes.GENERIC_JUMP_STRENGTH,
                DusksAndDungeons.id("effect.jump_reduce"),
                0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            )
    )*/
//    val MADNESS = register("madness", MadnessEffect(StatusEffectType.HARMFUL, 0x3E1663))

    private fun register(id: String, entry: StatusEffect): Holder<StatusEffect> {
        return Registry.registerHolder(Registries.STATUS_EFFECT, id(id), entry)
    }
}