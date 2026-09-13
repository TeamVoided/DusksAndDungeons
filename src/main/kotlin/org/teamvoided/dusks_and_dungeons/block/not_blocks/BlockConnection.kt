package org.teamvoided.dusks_and_dungeons.block.not_blocks

import net.minecraft.core.Direction
import net.minecraft.world.level.block.state.BlockState

interface BlockConnection {
    //class that would override most false result block connection logic, since a few have funky rules

    //state is block itself, like a gravestone
    //dir is the direction the block is wishing to connect from

    fun allConnect(state: BlockState, dir: Direction): Boolean = false
    fun wallsConnect(state: BlockState, dir: Direction): Boolean = allConnect(state, dir)
    fun fencesConnect(state: BlockState, dir: Direction): Boolean = allConnect(state, dir)
    fun barsAndPanesConnect(state: BlockState, dir: Direction): Boolean = allConnect(state, dir) //heretical classes
    fun gateInWall(state: BlockState): Boolean = false

    //all connect will override walls and fences

    //bars hard code the direction property into the second field, bl
    //gates do the direction checks themselves or something? fine for blocks that connect to gates (wall) but not good for directionally challenged blocks (grave sconce)
}