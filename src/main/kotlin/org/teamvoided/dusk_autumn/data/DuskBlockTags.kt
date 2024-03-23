package org.teamvoided.dusk_autumn.data

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskBlockTags {
    val CASCADE_LOGS = create("cascade_logs")
    val LEAF_PILES = create("leaf_piles")

    fun create(id: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id(id))
}