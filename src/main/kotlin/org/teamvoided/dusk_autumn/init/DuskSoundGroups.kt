package org.teamvoided.dusk_autumn.init

import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier

object DuskSoundGroups {
    val CHAIN = BlockSoundGroup(
        1f,
        0f,
        SoundEvents.BLOCK_CHAIN_BREAK,
        SoundEvents.BLOCK_CHAIN_STEP,
        SoundEvents.BLOCK_CHAIN_PLACE,
        SoundEvents.BLOCK_CHAIN_HIT,
        SoundEvents.BLOCK_CHAIN_FALL
    )

}