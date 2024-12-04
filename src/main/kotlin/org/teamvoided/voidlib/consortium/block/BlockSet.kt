@file:Suppress("unused")

package org.teamvoided.voidlib.consortium.block

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import java.util.function.Supplier

open class HeadlessBlockSet(
    val name: String, val parent: Block, val stairs: Block, val slab: Block, val wall: Block,
    val hasStoneCutting: Boolean = true
): ItemConvertible, Supplier<Block> {
    open fun collect() = listOf(stairs, slab, wall)
    open fun toIdMap() = mapOf("${name}_stairs" to stairs, "${name}_slab" to slab, "${name}_wall" to wall)
    fun forEach(consumer: (Block) -> Unit) = this.collect().forEach(consumer)
    fun register(consumer: (String, Block) -> Unit) = this.toIdMap().forEach(consumer)
    override fun asItem(): Item = parent.asItem()
    override fun get() = parent
}

open class BlockSet(
    val parentName: String, name: String, parent: Block, stairs: Block, slab: Block, wall: Block,
    hasStoneCutting: Boolean = true
) : HeadlessBlockSet(name, parent, stairs, slab, wall, hasStoneCutting) {
    override fun collect() = listOf(parent, stairs, slab, wall)
    override fun toIdMap() =
        mapOf(parentName to parent, "${name}_stairs" to stairs, "${name}_slab" to slab, "${name}_wall" to wall)
}

fun createBlockSet(name: String) = BlockSetBuilder(name)
fun createBlockSet(name: String, settings: Settings) = BlockSetBuilder(name).settings(settings)
fun createHeadlessSet(name: String, parent: Block) = BlockSetBuilder(name, true).parent(parent)

typealias BlockMaker<T> = (Block, Settings) -> T
typealias StairMaker = (BlockState, Settings) -> StairsBlock

open class BlockSetBuilder(var name: String, val headless: Boolean = false) {
    var parentName: String = name
    lateinit var settings: Settings
    lateinit var parent: Block
    var hasStoneCutting: Boolean = true

    var parentMaker: (Settings) -> Block = { s -> Block(s) }
    var stairMaker: BlockMaker<StairsBlock> = { block, s -> StairsBlock(block.defaultState, s) }
    var slabMaker: BlockMaker<SlabBlock> = { _, s -> SlabBlock(s) }
    var wallMaker: BlockMaker<WallBlock> = { _, s -> WallBlock(s) }

    fun parentName(parentName: String): BlockSetBuilder = this.apply { this.parentName = parentName }
    fun parentSuffix(suffix: String): BlockSetBuilder = this.apply { this.parentName = name + suffix }
    fun s(): BlockSetBuilder = this.apply { this.parentName = name + "s" }
    fun settings(settings: Settings): BlockSetBuilder = this.apply { this.settings = settings }
    fun parent(parent: (Settings) -> Block): BlockSetBuilder = this.apply { this.parentMaker = parent }
    fun parent(parent: Block): BlockSetBuilder = this.apply { this.parent = parent; this.settings = copy(parent) }
    fun rawStairs(stairs: BlockMaker<StairsBlock>): BlockSetBuilder = this.apply { stairMaker = stairs }
    fun stairs(stairs: StairMaker): BlockSetBuilder = this.apply { stairMaker = { b, s -> stairs(b.defaultState, s) } }
    fun slab(slab: BlockMaker<SlabBlock>): BlockSetBuilder = this.apply { slabMaker = slab }
    fun slab(slab: (Settings) -> SlabBlock): BlockSetBuilder = this.apply { slabMaker = { _, s -> slab(s) } }
    fun wall(wall: BlockMaker<WallBlock>): BlockSetBuilder = this.apply { wallMaker = wall }
    fun wall(wall: (Settings) -> WallBlock): BlockSetBuilder = this.apply { wallMaker = { _, s -> wall(s) } }
    fun hasStoneCutting(hasStoneCutting: Boolean): BlockSetBuilder =
        this.apply { this.hasStoneCutting = hasStoneCutting }

    fun noStoneCutting(): BlockSetBuilder = this.apply { this.hasStoneCutting = false }

    fun build(): HeadlessBlockSet {
        if (headless) return HeadlessBlockSet(
            name, parent,
            stairMaker(parent, settings), slabMaker(parent, settings), wallMaker(parent, settings),
            hasStoneCutting
        )

        parent = parentMaker(settings)
        return BlockSet(
            parentName, name,
            parent, stairMaker(parent, settings), slabMaker(parent, settings), wallMaker(parent, settings),
            hasStoneCutting
        )
    }
}
