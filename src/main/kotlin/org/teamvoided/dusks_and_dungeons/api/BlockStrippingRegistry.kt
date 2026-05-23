package org.teamvoided.dusks_and_dungeons.api

import com.mojang.text2speech.Narrator
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.impl.BlockStrippingRegistryIml

object BlockStrippingRegistry {
    fun register(input: Block, stripped: Block) {
        val old = BlockStrippingRegistryIml.BLOCK_STATE_MAP.put(input, stripped)
        if (old != null) {
            Narrator.LOGGER.debug("Replaced old stripping mapping from {} to {} with {}", input, old, stripped)
        }
    }
}
