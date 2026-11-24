package org.teamvoided.voidlib.api

import net.minecraft.core.Direction
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.IronBarsBlock
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.state.BlockState

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
interface BlockConnection {
    /**
     * Allow all defined blocks to connect this block
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */

// TODO(del) Update Voidlib Delete this
    @Deprecated("Use Voidlib")
    fun allowAllConnections(state: BlockState, dir: Direction): Boolean

    /**
     * Allow [WallBlock] to connect this block
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */

// TODO(del) Update Voidlib Delete this
    @Deprecated("Use Voidlib")
    fun allowWallsToConnect(state: BlockState, dir: Direction): Boolean = allowAllConnections(state, dir)

    /**
     * Allow [FenceBlock] to connect this block
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */

// TODO(del) Update Voidlib Delete this
    @Deprecated("Use Voidlib")
    fun allowFencesToConnect(state: BlockState, dir: Direction): Boolean = allowAllConnections(state, dir)


    /**
     * Allows for [IronBarsBlock] to connect to this block
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */

// TODO(del) Update Voidlib Delete this
    @Deprecated("Use Voidlib")
    fun allowBarsToConnect(state: BlockState, dir: Direction): Boolean = allowAllConnections(state, dir)

    /**
     * Allows for connections to other modded blocks (This has to be implemented the other mods or with mixins)
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */

// TODO(del) Update Voidlib Delete this
    @Deprecated("Use Voidlib")
    fun allowModdedToConnect(state: BlockState, dir: Direction): Boolean = allowAllConnections(state, dir)

    /**
     * Allows [FenceGateBlock] to use the [FenceGateBlock.IN_WALL] state
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */

// TODO(del) Update Voidlib Delete this
    @Deprecated("Use Voidlib")
    fun allowGateInWallState(state: BlockState, dir: Direction): Boolean = false

}