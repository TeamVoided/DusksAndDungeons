package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.Registry
import net.minecraft.sounds.SoundEvent
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

@Suppress("unused")
object DnDSoundEvents {
    fun init() = Unit

    @Suppress("SameParameterValue")
    private fun register(id: String): SoundEvent = register(id(id))
    private fun register(id: ResourceLocation): SoundEvent = register(id, id)
    private fun register(id: ResourceLocation, soundId: ResourceLocation): SoundEvent =
        Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId))

    private fun registerHolder(id: ResourceLocation): Holder.Reference<SoundEvent> = registerHolder(id, id)
    private fun registerHolder(id: ResourceLocation, soundId: ResourceLocation): Holder.Reference<SoundEvent> =
        Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId))

    private fun registerHolder(id: ResourceLocation, soundId: ResourceLocation, range: Float): Holder<SoundEvent> =
        Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createFixedRangeEvent(soundId, range))
}
