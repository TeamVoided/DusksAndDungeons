package org.teamvoided.voidlib.api

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.voidlib.impl.PrimaryModifierRegistryImpl

/**
 * PrimaryModifierRegistry allows for registering Attribute Modifier Ids
 * so they display in the same way the vanilla attributes [Item.BASE_ATTACK_DAMAGE_ID], [Item.BASE_ATTACK_SPEED_ID] do in [ItemStack.addModifierTooltip].
 */
// TODO(lib) move to voidlib
object PrimaryModifierRegistry {

    @JvmStatic
    fun register(id: ResourceLocation) {
        if (!PrimaryModifierRegistryImpl.MODIFIER_IDS.add(id)) {
            log.error("Attribute with id {} was already register as primary", id)
        }
    }

}