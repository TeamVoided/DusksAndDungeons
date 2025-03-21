package org.teamvoided.voidlib.consortium.block.set

import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.SlabBlock
import net.minecraft.block.StairsBlock
import net.minecraft.block.WallBlock

open class BlockSetBuilder(var name: String) {
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