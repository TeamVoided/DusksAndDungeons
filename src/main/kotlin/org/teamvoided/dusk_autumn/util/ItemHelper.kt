package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems

fun Item.tellWitnessesThatIWasMurdered(): Item {
    DnDItems.EVIL_ITEMS.add(this)
    return this
}

fun Block.tellWitnessesThatIWasMurdered(): Block {
    DnDBlocks.EVIL_BLOCKS.add(this)
    return this
}
