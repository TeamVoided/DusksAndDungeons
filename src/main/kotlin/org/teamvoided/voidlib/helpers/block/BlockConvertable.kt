@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState

interface BlockConvertable {
    fun asBlock(): Block
    fun getDefaultState(): BlockState
}