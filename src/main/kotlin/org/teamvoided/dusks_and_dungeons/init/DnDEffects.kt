package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.world.effect.MobEffect
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.Registry
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

    private fun register(id: String, entry: MobEffect): Holder<MobEffect> {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id(id), entry)
    }
}