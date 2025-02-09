@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.BlockItem

interface BlockConvertable {
    fun asBlock(): Block
    fun getDefaultState(): BlockState
}

fun Block.asBlock(): Block = this
fun BlockItem.asBlock(): Block = this.block
fun BlockItem.getDefaultState(): BlockState = this.block.defaultState
fun BlockState.asBlock(): Block = this.block
fun BlockState.getDefaultState(): BlockState = this.block.defaultState
