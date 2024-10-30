package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Block
import net.minecraft.item.Item

val SECRET_BLOCKS = mutableSetOf<Block>()
val SECRET_ITEMS = mutableSetOf<Item>()
fun mergeSecrets() = SECRET_ITEMS.addAll(SECRET_BLOCKS.map { it.asItem() })
fun Block.shh(): Block {
    SECRET_BLOCKS.add(this)
    return this
}

fun Item.shh(): Item {
    SECRET_ITEMS.add(this)
    return this
}


@JvmField
val dataFixerBlackList = listOf(
    "celestal_bell",
    "chest_o_souls",
    "quarter_block_pile",
    "bunny_grave",
    "hauted_block",
    "haunted_gravestone_block",
    "chill_charge",
    "scarecrow",
    "die",
    "flying_pumpkin",
    "dust_bunny",
    "piffling_pumpkin",
)

