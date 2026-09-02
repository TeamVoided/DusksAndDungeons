package org.teamvoided.dusks_and_dungeons.util.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy
import org.teamvoided.dusks_and_dungeons.block.CuttableHollowLogBlock
import org.teamvoided.dusks_and_dungeons.block.GravestoneBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.COLOR_CONSORTIUM
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.register
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.registerNoItemOld
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.registerOld
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.voidlib.consortium.block.color.ColorConsortium
import org.teamvoided.voidlib.consortium.block.color.FullColorCollections
import org.teamvoided.voidlib.consortium.block.color.FullColorConsortium
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.set.createBlockSet
import org.teamvoided.voidlib.consortium.block.set.createHeadlessSet
import org.teamvoided.voidlib.helpers.item.EquipableBlockItem

// TODO delete this, it will case problems later
fun registerHeadEquipable(id: String, block: Block): Block {
    val regBlock = DnDBlocks.registerNoItem(id, { block }, Properties.of())
    DnDItems.register(id, { EquipableBlockItem(regBlock, it) })
    return regBlock
}

// region Color Consortiums
typealias BlockMaker<T> = (coloredBlock: Block) -> T

fun <T, C> register(consortium: C): C where C : ColorConsortium<T>, T : Block {
    COLOR_CONSORTIUM.add(consortium)
    consortium.register(::registerOld)
    return consortium
}

fun <T, C> register(
    name: String, provider: FullColorCollections, block: BlockMaker<T>,
): FullColorConsortium<T> where C : ColorConsortium<T>, T : Block {
    return register(FullColorConsortium(name, provider, block))
}

fun <T, C> register(
    prefix: String, name: String, provider: FullColorCollections, block: BlockMaker<T>,
): FullColorConsortium<T> where C : ColorConsortium<T>, T : Block {
    val color = FullColorConsortium(name, provider, block)
    color.prefix = prefix
    return register(color)
}

// Register No Item
fun <T, C> registerNoItem(consortium: C): C where C : ColorConsortium<T>, T : Block {
    /*COLORS.add(consortium)*/
    consortium.register(::registerNoItemOld)
    return consortium
}

fun <T, C> registerNoItem(
    name: String, provider: FullColorCollections, block: BlockMaker<T>,
): FullColorConsortium<T> where C : ColorConsortium<T>, T : Block {
    return registerNoItem(FullColorConsortium(name, provider, block))
}

fun <T, C> registerNoItem(
    prefix: String, name: String, provider: FullColorCollections, block: BlockMaker<T>,
): FullColorConsortium<T> where C : ColorConsortium<T>, T : Block {
    val color = FullColorConsortium(name, provider, block)
    color.prefix = prefix
    return registerNoItem(color)
}
// endregion

// region Block Sets
fun <T : AbstractBlockSet> register(set: T): T {
    SETS.add(set)
    set.register(::registerOld)
    return set
}

fun registerSet(name: String, settings: Properties) =
    register(createBlockSet(name, settings).build())

fun registerSet(name: String, settings: Properties, sfx: String) =
    register(createBlockSet(name, settings).parentSuffix(sfx).build())

fun registerHeadlessSet(name: String, parent: Block) =
    register(createHeadlessSet(name, parent).buildHeadless())

fun registerHeadlessSet(name: String, parent: Block, properties: Properties) =
    register(createHeadlessSet(name, parent).settings(properties).buildHeadless())

fun registerWoodenSet(name: String, parent: Block) =
    register(createHeadlessSet(name, parent).noStoneCutting().buildHeadless()).woodSet()
// endregion

// region Shaped Registers

internal fun registerStairs(name: String, block: Block): Block {
    return register(name, { StairBlock(block.defaultBlockState(), it) }, ofFullCopy(block))
}

internal fun registerWall(name: String, block: Block): Block {
    return register(name, ::WallBlock, ofFullCopy(block).forceSolidOn())
}

internal fun registerHollowLog(name: String, block: Block): Block {
    return register(name, ::CuttableHollowLogBlock, ofFullCopy(block))
}

// Gravestones
internal fun registerGravestone(name: String, block: Block): Block {
    return register(name, GravestoneBlock::newGrave, ofFullCopy(block).forceSolidOn()).pickaxe()
}

internal fun registerSmallGravestone(name: String, block: Block): Block {
    return register(name, GravestoneBlock::newSmallGrave, ofFullCopy(block)).pickaxe()
}

// endregion
