/*
 * TODO(cleanup)
 */

package org.teamvoided.voidlib.impl

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import org.jetbrains.annotations.ApiStatus
import java.util.*

// TODO(lib) move to voidlib
object BlockStrippingRegistryIml {

    @ApiStatus.Internal
    val BLOCK_STATE_MAP = mutableMapOf<Block, Block>()

    @JvmStatic
    fun getPossibleStrippedState(original: Optional<BlockState>, originalState: BlockState): Optional<BlockState> {
        return if (original.isEmpty) {
            Optional.ofNullable(BLOCK_STATE_MAP[originalState.block]?.withPropertiesOf(originalState))
        } else {
            original
        }
    }

}