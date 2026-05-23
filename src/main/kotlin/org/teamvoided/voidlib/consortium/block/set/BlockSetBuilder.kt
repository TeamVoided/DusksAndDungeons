package org.teamvoided.voidlib.consortium.block.set

import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.WallBlock

open class BlockSetBuilder(var name: String) {
    var parentName: String = name
    lateinit var settings: Properties
    lateinit var parent: Block
    var hasStoneCutting: Boolean = true

    var parentMaker: (Properties) -> Block = { s -> Block(s) }
    var stairMaker: BlockMaker<StairBlock> = { block, s -> StairBlock(block.defaultBlockState(), s) }
    var slabMaker: BlockMaker<SlabBlock> = { _, s -> SlabBlock(s) }
    var wallMaker: BlockMaker<WallBlock> = { _, s -> WallBlock(s) }

    fun parentName(parentName: String) = this.apply { this.parentName = parentName }
    fun parentSuffix(suffix: String) = this.apply { this.parentName = name + suffix }
    fun s() = this.apply { this.parentName = name + "s" }
    fun settings(settings: Properties) = this.apply { this.settings = settings }
    fun parent(parent: (Properties) -> Block) = this.apply { this.parentMaker = parent }
    fun parent(parent: Block) = this.apply { this.parent = parent; this.settings = ofFullCopy(parent) }
    fun rawStairs(stairs: BlockMaker<StairBlock>) = this.apply { stairMaker = stairs }
    fun stairs(stairs: StairMaker) = this.apply { stairMaker = { b, s -> stairs(b.defaultBlockState(), s) } }
    fun slab(slab: BlockMaker<SlabBlock>) = this.apply { slabMaker = slab }
    fun slab(slab: (Properties) -> SlabBlock) = this.apply { slabMaker = { _, s -> slab(s) } }
    fun wall(wall: BlockMaker<WallBlock>) = this.apply { wallMaker = wall }
    fun wall(wall: (Properties) -> WallBlock) = this.apply { wallMaker = { _, s -> wall(s) } }
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