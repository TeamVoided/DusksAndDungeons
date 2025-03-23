package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

@Suppress("unused")
object DnDSoundEvents {
    fun init() = Unit
    @Suppress("SameParameterValue")
    private fun register(id: String): SoundEvent = register(id(id))
    private fun register(id: Identifier): SoundEvent = register(id, id)
    private fun register(id: Identifier, soundId: Identifier): SoundEvent =
        Registry.register(Registries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId))

    private fun registerHolder(id: Identifier): Holder.Reference<SoundEvent> = registerHolder(id, id)
    private fun registerHolder(id: Identifier, soundId: Identifier): Holder.Reference<SoundEvent> =
        Registry.registerHolder(Registries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId))

    private fun registerHolder(id: Identifier, soundId: Identifier, range: Float): Holder<SoundEvent> =
        Registry.registerHolder(Registries.SOUND_EVENT, id, SoundEvent.createFixedRangeEvent(soundId, range))
}
