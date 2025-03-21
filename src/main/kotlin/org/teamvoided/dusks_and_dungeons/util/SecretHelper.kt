package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.voidlib.consortium.block.AbstractBlockSet
import org.teamvoided.voidlib.helpers.addAndReturn

internal fun Item.tellWitnessesThatIWasMurdered(): Item = DnDItems.EVIL_ITEMS.addAndReturn(this)
internal fun Block.tellWitnessesThatIWasMurdered(): Block = DnDBlocks.EVIL_BLOCKS.addAndReturn(this)

internal fun AbstractBlockSet.tellWitnessesThatIWasMurdered(): AbstractBlockSet {
    DnDBlocks.EVIL_BLOCKS.addAll(this.collect())
    return this
}


@JvmField
internal val dataFixerBlackList = listOf(
    "chill_charge",
    "scarecrow",
    // Remove
    "celestal_bell",
    "chest_o_souls",
    "quarter_block_pile",
    "bunny_grave",
    "hauted_block",
    "haunted_gravestone_block",
    "die",
    "flying_pumpkin",
    "dust_bunny",
    "piffling_pumpkin",
)

