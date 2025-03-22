package org.teamvoided.voidlib.consortium.block.color

import net.minecraft.block.Block

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