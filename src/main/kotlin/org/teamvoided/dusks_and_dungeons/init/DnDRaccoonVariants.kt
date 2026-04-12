package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.registry.RegistryKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant

object DnDRaccoonVariants {

    val DEFAULT = create("default")

    fun create(path: String): RegistryKey<RaccoonVariant> = RegistryKey.of(DnDRegistryKeys.RACCOON_VARIANT, id(path))

}
