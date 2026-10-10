/*
 * TODO(cleanup)
 */

package org.teamvoided.voidlib.impl

import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import org.jetbrains.annotations.ApiStatus

// TODO(lib) move to voidlib
object PrimaryModifierRegistryImpl {

    @ApiStatus.Internal
    val MODIFIER_IDS = mutableSetOf<ResourceLocation>()
    val DEFAULTS = setOf(Item.BASE_ATTACK_DAMAGE_ID, Item.BASE_ATTACK_SPEED_ID)

    // debug value : `mc("enchantment.efficiency/mainhand")`
    @JvmStatic
    fun tryApply(
        player: Player?,
        modifier: AttributeModifier, attribute: Holder<Attribute>,
        amount: LocalDoubleRef, isPrimary: LocalBooleanRef,
    ) {
        player ?: return
        if (!DEFAULTS.contains(modifier.id) && MODIFIER_IDS.contains(modifier.id())) {
            amount.set(amount.get() + player.getAttributeBaseValue(attribute))
            isPrimary.set(true)
        }
    }

}