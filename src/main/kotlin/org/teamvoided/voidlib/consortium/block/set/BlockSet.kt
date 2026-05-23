@file:Suppress("unused")

package org.teamvoided.voidlib.consortium.block.set

import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.StairBlock

open class BlockSet(
    val parentName: String,
    name: String, parent: Block, stairs: Block, slab: Block, wall: Block, hasStoneCutting: Boolean
) : AbstractBlockSet(name, parent, stairs, slab, wall, hasStoneCutting) {
    override val list = listOf(parent) + super.list
    override fun getIdMap() = mapOf(parentName to parent) + super.getIdMap()

    override fun headless() = list.drop(1)
}

open class HeadlessBlockSet(
    name: String, parent: Block, stairs: Block, slab: Block, wall: Block, hasStoneCutting: Boolean
) : AbstractBlockSet(name, parent, stairs, slab, wall, hasStoneCutting)

fun createBlockSet(name: String) = BlockSetBuilder(name)
fun createBlockSet(name: String, settings: Properties) = BlockSetBuilder(name).settings(settings)
fun createHeadlessSet(name: String, parent: Block) = BlockSetBuilder(name).parent(parent)

typealias BlockMaker<T> = (Block, Properties) -> T
typealias StairMaker = (BlockState, Properties) -> StairBlock
