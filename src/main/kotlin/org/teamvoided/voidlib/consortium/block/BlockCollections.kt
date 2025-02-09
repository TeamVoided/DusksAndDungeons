@file:Suppress("unused")

package org.teamvoided.voidlib.consortium.block

import net.minecraft.block.Block
import net.minecraft.block.Blocks.AIR

open class FullColorCollections(
    private val uncolored: Block,
    white: Block, orange: Block,
    magenta: Block, lightBlue: Block,
    yellow: Block, lime: Block,
    pink: Block, gray: Block,
    lightGray: Block, cyan: Block,
    purple: Block, blue: Block,
    brown: Block, green: Block,
    red: Block, black: Block,
) : ColorCollection(
    white, orange, magenta, lightBlue, yellow, lime, pink, gray, lightGray, cyan, purple, blue, brown, green, red, black
) {
    override val size: Int = 17
    override val list = listOf(uncolored) + super.list

    override fun hasUncolored() = true
    override fun uncolored(): Block = uncolored
}

open class ColorCollection(
    val white: Block, val orange: Block,
    val magenta: Block, val lightBlue: Block,
    val yellow: Block, val lime: Block,
    val pink: Block, val gray: Block,
    val lightGray: Block, val cyan: Block,
    val purple: Block, val blue: Block,
    val brown: Block, val green: Block,
    val red: Block, val black: Block
) : Collection<Block> {
    override val size: Int = 16
    open val list = listOf(
        white, orange, magenta, lightBlue, yellow, lime, pink, gray,
        lightGray, cyan, purple, blue, brown, green, red, black
    )

    override fun isEmpty(): Boolean = false
    override fun iterator(): Iterator<Block> = list.iterator()
    override fun contains(element: Block): Boolean = list.contains(element)
    override fun containsAll(elements: Collection<Block>): Boolean = list.containsAll(elements)

    open fun hasUncolored() = false
    open fun uncolored(): Block = AIR
}


