package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant

object DnDRaccoonVariants {

    val DEFAULT = create("default")

    fun create(path: String): ResourceKey<RaccoonVariant> = ResourceKey.create(DnDRegistryKeys.RACCOON_VARIANT, id(path))

}
