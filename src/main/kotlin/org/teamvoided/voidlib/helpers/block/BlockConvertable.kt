@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.block

import net.minecraft.block.Block

interface BlockConvertable {
    fun asBlock(): Block
}