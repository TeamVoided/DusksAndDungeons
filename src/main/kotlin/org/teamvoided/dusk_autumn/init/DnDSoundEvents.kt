package org.teamvoided.dusk_autumn.init

import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DusksAndDungeons.id

object DnDSoundEvents {
    val BLOCK_CELESTAL_BELL_USE = register("block.celestal_bell.use")

    fun init() {
    }

    private fun registerHolder(id: Identifier, soundId: Identifier, range: Float): Holder<SoundEvent> {
        return Registry.registerHolder(Registries.SOUND_EVENT, id, SoundEvent.createFixedRangeEvent(soundId, range))
    }

    private fun register(id: String): SoundEvent {
        return register(id(id))
    }

    private fun register(id: Identifier): SoundEvent {
        return register(id, id)
    }

    private fun registerHolder(id: String): Holder.Reference<SoundEvent> {
        return registerHolder(id(id))
    }

    private fun registerHolder(id: Identifier): Holder.Reference<SoundEvent> {
        return registerHolder(id, id)
    }

    private fun register(id: Identifier, soundId: Identifier): SoundEvent {
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId))
    }

    private fun registerHolder(id: Identifier, soundId: Identifier): Holder.Reference<SoundEvent> {
        return Registry.registerHolder(Registries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId))
    }
}