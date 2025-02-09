@file:Suppress("unused")

package org.teamvoided.voidlib.consortium.block

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import org.teamvoided.voidlib.consortium.utils.Registrable
import org.teamvoided.voidlib.helpers.block.BlockConvertable
import java.util.function.BiConsumer
import java.util.function.Supplier

abstract class AbstractBlockSet(
    val name: String,
    val parent: Block, val stairs: Block, val slab: Block, val wall: Block,
    val hasStoneCutting: Boolean
) : ItemConvertible, Supplier<Block>, BlockConvertable, Registrable<Block> {
    open fun collect(): List<Block> = listOf(stairs, slab, wall)
    open fun toIdMap(): Map<String, Block> =
        mapOf("${name}_stairs" to stairs, "${name}_slab" to slab, "${name}_wall" to wall)

    open fun forEach(consumer: (Block) -> Unit) = this.collect().forEach(consumer)
    override fun register(consumer: BiConsumer<String, Block>) = this.toIdMap().forEach(consumer)

    override fun asItem(): Item = parent.asItem()
    override fun get() = parent
    override fun asBlock(): Block = parent
    override fun getDefaultState(): BlockState = parent.defaultState
}

open class BlockSet(
    val parentName: String,
    name: String, parent: Block, stairs: Block, slab: Block, wall: Block, hasStoneCutting: Boolean
) : AbstractBlockSet(name, parent, stairs, slab, wall, hasStoneCutting) {
    override fun collect() = listOf(parent) + super.collect()
    override fun toIdMap() = mapOf(parentName to parent) + super.toIdMap()
}
open class HeadlessBlockSet(
    name: String, parent: Block, stairs: Block, slab: Block, wall: Block, hasStoneCutting: Boolean
) : AbstractBlockSet(name, parent, stairs, slab, wall, hasStoneCutting)


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

    fun parentName(parentName: String) = this.apply { this.parentName = parentName }
    fun parentSuffix(suffix: String) = this.apply { this.parentName = name + suffix }
    fun s() = this.apply { this.parentName = name + "s" }
    fun settings(settings: Settings) = this.apply { this.settings = settings }
    fun parent(parent: (Settings) -> Block) = this.apply { this.parentMaker = parent }
    fun parent(parent: Block) = this.apply { this.parent = parent; this.settings = copy(parent) }
    fun rawStairs(stairs: BlockMaker<StairsBlock>) = this.apply { stairMaker = stairs }
    fun stairs(stairs: StairMaker) = this.apply { stairMaker = { b, s -> stairs(b.defaultState, s) } }
    fun slab(slab: BlockMaker<SlabBlock>) = this.apply { slabMaker = slab }
    fun slab(slab: (Settings) -> SlabBlock) = this.apply { slabMaker = { _, s -> slab(s) } }
    fun wall(wall: BlockMaker<WallBlock>) = this.apply { wallMaker = wall }
    fun wall(wall: (Settings) -> WallBlock) = this.apply { wallMaker = { _, s -> wall(s) } }
    fun hasStoneCutting(hasStoneCutting: Boolean) = this.apply { this.hasStoneCutting = hasStoneCutting }
    fun noStoneCutting() = this.apply { this.hasStoneCutting = false }

    fun build(): BlockSet {
        parent = parentMaker(settings)
        return BlockSet(
            parentName, name, parent,
            stairMaker(parent, settings), slabMaker(parent, settings), wallMaker(parent, settings),
            hasStoneCutting
        )
    }
    fun buildHeadless(): HeadlessBlockSet = HeadlessBlockSet(
        name, parent, stairMaker(parent, settings), slabMaker(parent, settings), wallMaker(parent, settings),
        hasStoneCutting
    )
}
