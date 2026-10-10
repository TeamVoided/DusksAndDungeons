/*
 * TODO(cleanup)
 */

package org.teamvoided.voidlib.api

import net.minecraft.core.Direction
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.IronBarsBlock
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.state.BlockState

// TODO(lib) move to voidlib
@Suppress("unused")
interface BlockConnection {
    /**
     * Allow all defined blocks to connect this block
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */
    fun allowAllConnections(state: BlockState, dir: Direction): Boolean

    /**
     * Allow [WallBlock] to connect this block
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */
    fun allowWallsToConnect(state: BlockState, dir: Direction): Boolean = allowAllConnections(state, dir)

    /**
     * Allow [FenceBlock] to connect this block
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */
    fun allowFencesToConnect(state: BlockState, dir: Direction): Boolean = allowAllConnections(state, dir)

    /**
     * Allows for [IronBarsBlock] to connect to this block
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */
    fun allowBarsToConnect(state: BlockState, dir: Direction): Boolean = allowAllConnections(state, dir)

    /**
     * Allows for connections to other modded blocks (This has to be implemented the other mods or with mixins)
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */
    fun allowModdedToConnect(state: BlockState, dir: Direction): Boolean = allowAllConnections(state, dir)

    /**
     * Allows [FenceGateBlock] to use the [FenceGateBlock.IN_WALL] state
     * @param state The Block itself
     * @param dir The direction the block is wishing to connect from
     */
    fun allowGateInWallState(state: BlockState, dir: Direction): Boolean = false

    //gates do the direction checks themselves or something? fine for blocks that connect to gates (wall) but not good for directionally challenged blocks (grave sconce)
}