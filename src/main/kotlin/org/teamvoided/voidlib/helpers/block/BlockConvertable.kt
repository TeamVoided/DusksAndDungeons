@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.item.BlockItem

interface BlockConvertable {
    fun asBlock(): Block
    fun getDefaultState(): BlockState
}

fun Block.asBlock(): Block = this
fun BlockItem.asBlock(): Block = this.block
fun BlockItem.getDefaultState(): BlockState = this.block.defaultBlockState()
fun BlockState.asBlock(): Block = this.block
fun BlockState.getDefaultState(): BlockState = this.block.defaultBlockState()
