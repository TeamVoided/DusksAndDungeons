package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import org.teamvoided.dusk_autumn.init.DnDItems

fun <T : ItemConvertible> T.tellWitnessesThatIWasMurdered(): T {
    DnDItems.EVIL_ITEMS.add(this.asItem())
    return this
}