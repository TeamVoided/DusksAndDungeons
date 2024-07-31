package org.teamvoided.dusk_autumn.data

import net.minecraft.entity.passive.WolfVariant
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DnDWolfVariants {
    val AUTUMN = create("autumn")
    fun create(id: String): RegistryKey<WolfVariant> =
        RegistryKey.of(RegistryKeys.WOLF_VARIANT, id(id))
}