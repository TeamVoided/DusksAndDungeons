package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.registerHolder

object DnDSoundEvents {

    fun init() = Unit

    fun registerVariableRange(name: String, soundId: String = name): Holder.Reference<SoundEvent> {
        return registerVariableRange(id(name), id(soundId))
    }

    fun registerVariableRange(id: ResourceLocation, soundId: ResourceLocation = id): Holder.Reference<SoundEvent> {
        return BuiltInRegistries.SOUND_EVENT.registerHolder(id, SoundEvent.createVariableRangeEvent(soundId))
    }

    fun registerFixedRange(name: String, range: Float, soundId: String = name): Holder<SoundEvent> {
        return registerFixedRange(id(name), range, id(soundId))
    }

    fun registerFixedRange(id: ResourceLocation, range: Float, soundId: ResourceLocation = id): Holder<SoundEvent> {
        return BuiltInRegistries.SOUND_EVENT.registerHolder(id, SoundEvent.createFixedRangeEvent(soundId, range))
    }

}