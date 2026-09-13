package org.teamvoided.dusks_and_dungeons.block.not_blocks

import net.minecraft.core.Direction
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.IronBarsBlock
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.state.BlockState

interface BlockConnection {
    //class that would override most false result block connection logic, since a few have funky rules
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
     * Allows [FenceGateBlock] to use the [FenceGateBlock.IN_WALL] state
     * @param state The Block itself
     */
    fun allowGateInWallState(state: BlockState): Boolean = false

    //bars hard code the direction property into the second field, bl
    //gates do the direction checks themselves or something? fine for blocks that connect to gates (wall) but not good for directionally challenged blocks (grave sconce)
}