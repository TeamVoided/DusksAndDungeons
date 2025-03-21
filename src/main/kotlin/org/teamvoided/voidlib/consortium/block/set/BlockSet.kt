@file:Suppress("unused")

package org.teamvoided.voidlib.consortium.block.set

import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.StairsBlock

open class BlockSet(
    val parentName: String,
    name: String, parent: Block, stairs: Block, slab: Block, wall: Block, hasStoneCutting: Boolean
) : AbstractBlockSet(name, parent, stairs, slab, wall, hasStoneCutting) {
    override val list = listOf(parent) + super.list
    override fun getIdMap() = mapOf(parentName to parent) + super.getIdMap()
}

open class HeadlessBlockSet(
    name: String, parent: Block, stairs: Block, slab: Block, wall: Block, hasStoneCutting: Boolean
) : AbstractBlockSet(name, parent, stairs, slab, wall, hasStoneCutting)

fun createBlockSet(name: String) = BlockSetBuilder(name)
fun createBlockSet(name: String, settings: Settings) = BlockSetBuilder(name).settings(settings)
fun createHeadlessSet(name: String, parent: Block) = BlockSetBuilder(name).parent(parent)

typealias BlockMaker<T> = (Block, Settings) -> T
typealias StairMaker = (BlockState, Settings) -> StairsBlock
