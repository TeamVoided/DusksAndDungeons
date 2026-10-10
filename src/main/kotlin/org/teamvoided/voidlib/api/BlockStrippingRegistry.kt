/*
 * TODO(cleanup)
 */

package org.teamvoided.voidlib.api

import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.voidlib.impl.BlockStrippingRegistryIml

// TODO(lib) move to voidlib
object BlockStrippingRegistry {

    @JvmStatic
    fun register(input: Block, stripped: Block) {
        val old = BlockStrippingRegistryIml.BLOCK_STATE_MAP.put(input, stripped)
        if (old != null) {
            log.debug("Replaced old stripping mapping from {} to {} with {}", input, old, stripped)
        }
    }

}