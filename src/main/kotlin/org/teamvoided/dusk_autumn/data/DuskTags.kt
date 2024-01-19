package org.teamvoided.dusk_autumn.data

import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DuskAutumns

object DuskTags {
    val HAS_ERODED_PILLAR = TagKey.of(RegistryKeys.BIOME, DuskAutumns.id("has_eroded_pillar"))
}