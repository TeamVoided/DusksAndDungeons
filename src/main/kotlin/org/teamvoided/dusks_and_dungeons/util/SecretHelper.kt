package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems

val SECRET_BLOCKS = mutableSetOf<Block>()
val SECRET_ITEMS = mutableSetOf<Item>()
internal fun mergeSecrets() = SECRET_ITEMS.addAll(SECRET_BLOCKS.map { it.asItem() })
internal fun Block.shh(): Block {
    SECRET_BLOCKS.add(this)
    return this
}

internal fun Item.shh(): Item {
    SECRET_ITEMS.add(this)
    return this
}

internal fun Item.tellWitnessesThatIWasMurdered(): Item {
    DnDItems.EVIL_ITEMS.add(this)
    return this
}

internal fun Block.tellWitnessesThatIWasMurdered(): Block {
    DnDBlocks.EVIL_BLOCKS.add(this)
    return this
}


@JvmField
internal val dataFixerBlackList = listOf(
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

