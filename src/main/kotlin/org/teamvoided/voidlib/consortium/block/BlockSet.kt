@file:Suppress("unused")

package org.teamvoided.voidlib.consortium.block

import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.SlabBlock
import net.minecraft.block.StairsBlock
import net.minecraft.block.WallBlock
import net.minecraft.item.ItemConvertible
import java.util.function.Supplier

open class HeadlessBlockSet(
    val name: String, val parent: Block, val stairs: Block, val slab: Block, val wall: Block,
    val hasStoneCutting: Boolean = true
) {
    open fun collect() = listOf(stairs, slab, wall)
    open fun toIdMap() = mapOf("${name}_stairs" to stairs, "${name}_slab" to slab, "${name}_wall" to wall)
    fun forEach(consumer: (Block) -> Unit) = this.collect().forEach(consumer)
    fun register(consumer: (String, Block) -> Unit) = this.toIdMap().forEach(consumer)
}

open class BlockSet(
    val parentName: String, name: String, parent: Block, stairs: Block, slab: Block, wall: Block,
    hasStoneCutting: Boolean = true
) :
    HeadlessBlockSet(name, parent, stairs, slab, wall, hasStoneCutting), ItemConvertible, Supplier<Block> {
    override fun collect() = listOf(parent, stairs, slab, wall)
    override fun toIdMap() =
        mapOf(parentName to parent, "${name}_stairs" to stairs, "${name}_slab" to slab, "${name}_wall" to wall)

    override fun asItem() = parent.asItem()
    override fun get() = parent
}

fun createBlockSet(name: String) = BlockSetBuilder(name)
fun createBlockSet(name: String, settings: Settings) = BlockSetBuilder(name).settings(settings)
fun createHeadlessSet(name: String, parent: Block) = BlockSetBuilder(name).parent(parent)

typealias BlockMaker<T> = (Block, Settings) -> T

open class BlockSetBuilder(var name: String) {
    var parentName: String = name
    lateinit var settings: Settings
    lateinit var parent: Block
    var hasStoneCutting: Boolean = true

    var parentMaker: (Settings) -> Block = { s -> Block(s) }
    var stairMaker: BlockMaker<StairsBlock> = { block, s -> StairsBlock(block.defaultState, s) }
    var slabMaker: BlockMaker<SlabBlock> = { _, s -> SlabBlock(s) }
    var wallMaker: BlockMaker<WallBlock> = { _, s -> WallBlock(s) }

    fun parentName(parentName: String): BlockSetBuilder = this.apply { this.parentName = parentName }
    fun settings(settings: Settings): BlockSetBuilder = this.apply { this.settings = settings }
    fun parent(parent: (Settings) -> Block): BlockSetBuilder = this.apply { this.parentMaker = parent }
    fun parent(parent: Block): BlockSetBuilder = this.apply { this.parent = parent; this.settings = copy(parent) }
    fun stairs(stairs: BlockMaker<StairsBlock>): BlockSetBuilder = this.apply { stairMaker = stairs }
    fun slab(slab: BlockMaker<SlabBlock>): BlockSetBuilder = this.apply { slabMaker = slab }
    fun wall(wall: BlockMaker<WallBlock>): BlockSetBuilder = this.apply { wallMaker = wall }
    fun hasStoneCutting(hasStoneCutting: Boolean): BlockSetBuilder =
        this.apply { this.hasStoneCutting = hasStoneCutting }

    fun build(): BlockSet {
        parent = parentMaker(settings)
        return BlockSet(
            parentName, name,
            parent, stairMaker(parent, settings), slabMaker(parent, settings), wallMaker(parent, settings),
            hasStoneCutting
        )
    }

    fun buildHeadless(): HeadlessBlockSet = HeadlessBlockSet(
        name, parent,
        stairMaker(parent, settings), slabMaker(parent, settings), wallMaker(parent, settings),
        hasStoneCutting
    )
}
