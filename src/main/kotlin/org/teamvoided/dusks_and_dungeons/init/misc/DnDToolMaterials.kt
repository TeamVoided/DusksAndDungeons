package org.teamvoided.dusks_and_dungeons.init.misc

import net.minecraft.world.item.Tiers
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.voidlib.helpers.item.VoidToolMaterial

object DnDToolMaterials {
    val BLACKSTONE = VoidToolMaterial.copyOf(Tiers.STONE, DnDItemTags.BLACKSTONE_TOOL_MATERIALS)
}