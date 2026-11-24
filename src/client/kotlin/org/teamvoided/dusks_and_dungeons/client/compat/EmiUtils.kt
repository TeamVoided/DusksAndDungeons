package org.teamvoided.dusks_and_dungeons.client.compat

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionContents
import kotlin.jvm.optionals.getOrNull


fun setPotion(stack: ItemStack, potion: Potion): ItemStack {
    stack.update(
        DataComponents.POTION_CONTENTS, PotionContents.EMPTY,
        BuiltInRegistries.POTION.wrapAsHolder(potion), PotionContents::withPotion
    )
    return stack
}


fun hiddenFromRecipeViewers(): Set<Item> {
    return BuiltInRegistries.ITEM.getTag(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS)
        .getOrNull()
        ?.stream()
        ?.map { it.value() }
        ?.toList()
        ?.toSet() ?: setOf()
}