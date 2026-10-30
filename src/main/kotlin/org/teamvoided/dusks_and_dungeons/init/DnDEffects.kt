package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.effect.MobEffect
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.registerHolder

object DnDEffects {

    /* val JUMP_REDUCE = register(
         "jump_reduce", VoidStatusEffect(MobEffectCategory.HARMFUL, 0x99990F)
             .addAttributeModifier(
                 Attributes.SAFE_FALL_DISTANCE,
                 id("effect.jump_reduce"),
                 -1.0, AttributeModifier.Operation.ADD_VALUE
             )
             .addAttributeModifier(
                 Attributes.JUMP_STRENGTH,
                 id("effect.jump_reduce"),
                 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
             )
     )

     val MADNESS = register("madness", MadnessEffect(MobEffectCategory.HARMFUL, 0x3E1663))*/

    fun init() = Unit


    fun register(id: String, effect: MobEffect): Holder<MobEffect> {
        return BuiltInRegistries.MOB_EFFECT.registerHolder(id(id), effect)
    }
}