package org.teamvoided.dusks_and_dungeons.util.block

import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.Block
import net.minecraft.world.item.Item
import org.teamvoided.dusks_and_dungeons.block.GravestoneBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.COLOR_CONSORTIUM
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.register
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.registerNoItem
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.voidlib.consortium.block.color.ColorConsortium
import org.teamvoided.voidlib.consortium.block.color.FullColorCollections
import org.teamvoided.voidlib.consortium.block.color.FullColorConsortium
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.set.createBlockSet
import org.teamvoided.voidlib.consortium.block.set.createHeadlessSet
import org.teamvoided.voidlib.helpers.item.EquipableBlockItem

fun registerHeadEquipable(id: String, block: Block): Block {
    val regBlock = registerNoItem(id, block)
    DnDItems.register(id, EquipableBlockItem(regBlock, Item.Properties()))
    return regBlock
}

/*fun registerEdible(id: String, foodComponent: FoodComponent, block: Block): Block {
    val regBlock = registerNoItem(id, block)
    DnDItems.register(id, BlockItem(regBlock, Item.Settings().food(foodComponent)))
    return regBlock
}*/

// region Color Consortiums
typealias BlockMaker<T> = (coloredBlock: Block) -> T

fun <T, C> register(consortium: C): C where C : ColorConsortium<T>, T : Block {
    COLOR_CONSORTIUM.add(consortium)
    consortium.register(::register)
    return consortium
}

fun <T, C> register(name: String, provider: FullColorCollections, block: BlockMaker<T>): FullColorConsortium<T>
        where C : ColorConsortium<T>, T : Block = register(FullColorConsortium(name, provider, block))

fun <T, C> register(prefix: String, name: String, provider: FullColorCollections, block: BlockMaker<T>)
        : FullColorConsortium<T> where C : ColorConsortium<T>, T : Block {
    val color = FullColorConsortium(name, provider, block)
    color.prefix = prefix
    return register(color)
}

// Register No Item
fun <T, C> registerNoItem(consortium: C): C where C : ColorConsortium<T>, T : Block {
    /*COLORS.add(consortium)*/
    consortium.register(::registerNoItem)
    return consortium
}

fun <T, C> registerNoItem(name: String, provider: FullColorCollections, block: BlockMaker<T>): FullColorConsortium<T>
        where C : ColorConsortium<T>, T : Block = registerNoItem(FullColorConsortium(name, provider, block))

fun <T, C> registerNoItem(prefix: String, name: String, provider: FullColorCollections, block: BlockMaker<T>)
        : FullColorConsortium<T> where C : ColorConsortium<T>, T : Block {
    val color = FullColorConsortium(name, provider, block)
    color.prefix = prefix
    return registerNoItem(color)
}
// endregion

// region Block Sets
fun <T : AbstractBlockSet> register(set: T): T {
    SETS.add(set)
    set.register(::register)
    return set
}

fun registerSet(name: String, settings: Properties) =
    register(createBlockSet(name, settings).build())

fun registerSet(name: String, settings: Properties, sfx: String) =
    register(createBlockSet(name, settings).parentSuffix(sfx).build())

fun registerHeadlessSet(name: String, parent: Block) =
    register(createHeadlessSet(name, parent).buildHeadless())

fun registerWoodenSet(name: String, parent: Block) =
    register(createHeadlessSet(name, parent).noStoneCutting().buildHeadless()).woodSet()
// endregion

// Gravestones
internal fun registerGravestone(name: String, block: Block) =
    register(name, GravestoneBlock(gravestoneShape, centerGravestoneShape, Properties.ofFullCopy(block).forceSolidOn()))
        .pickaxe()

internal fun registerSmallGravestone(name: String, block: Block) =
    register(name, GravestoneBlock(smallGravestoneShape, centerSmallGravestoneShape, Properties.ofFullCopy(block)))
        .pickaxe()
