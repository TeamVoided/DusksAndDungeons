package org.teamvoided.dusks_and_dungeons.util.block

import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Block
import net.minecraft.item.Item
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.block.GravestoneBlock
import org.teamvoided.dusks_and_dungeons.block.HauntedGravestoneBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.BLOCK_ITEMS
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.register
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.registerNoItem
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.shh
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.voidlib.consortium.block.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.createBlockSet
import org.teamvoided.voidlib.consortium.block.createHeadlessSet
import org.teamvoided.voidlib.helpers.item.EquipableBlockItem

fun registerHeadEquipable(id: String, block: Block): Block {
    val regBlock = registerNoItem(id, block)
//    BLOCK_ITEMS[id]?.let { error("Id $it already exists in BLOCK_ITEMS") }
    if (isDev()) log.warn("Fix registerHeadEquipable in the near future!")
    DnDItems.register( id, EquipableBlockItem(regBlock, Item.Settings()))
    return regBlock
}

//fun registerEdible(id: String, foodComponent: FoodComponent, block: Block): Block {
//    val regBlock = registerNoItem(id, block)
//    DnDItems.register(id, BlockItem(regBlock, Item.Settings().food(foodComponent)))
//    return regBlock
//}

fun <T : AbstractBlockSet> register(set: T): T {
    SETS.add(set)
    set.register(::register)
    return set
}

fun registerSet(name: String, settings: Settings) = register(createBlockSet(name, settings).build())
fun registerSet(name: String, settings: Settings, sfx: String) =
    register(createBlockSet(name, settings).parentSuffix(sfx).build())

fun registerHeadlessSet(name: String, parent: Block) = register(createHeadlessSet(name, parent).buildHeadless())

internal fun registerGravestone(name: String, block: Block) =
    register(name, GravestoneBlock(gravestoneShape, centerGravestoneShape, Settings.copy(block).solid()))
        .pickaxe()

internal fun registerSmallGravestone(name: String, block: Block) =
    register(name, GravestoneBlock(smallGravestoneShape, centerSmallGravestoneShape, Settings.copy(block)))
        .pickaxe()

internal fun registerHGravestone(name: String, block: Block) =
    register(
        name, HauntedGravestoneBlock(gravestoneShape, centerGravestoneShape, Settings.copy(block).solid())
    ).pickaxe().shh().tellWitnessesThatIWasMurdered()

internal fun registerSmallHGravestone(name: String, block: Block) =
    register(
        name, HauntedGravestoneBlock(smallGravestoneShape, centerSmallGravestoneShape, Settings.copy(block))
    ).pickaxe().shh().tellWitnessesThatIWasMurdered()