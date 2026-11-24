@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.mc

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.level.ItemLike
import java.util.function.Function


// Builder
// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun CreativeModeTab.Builder.icon(item: ItemLike): CreativeModeTab.Builder {
    return icon { ItemStack(item) }
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun CreativeModeTab.Builder.title(translation: String): CreativeModeTab.Builder {
    return title(Component.translatable(translation))
}

// Tab Modification
// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun modifyTab(tab: ResourceKey<CreativeModeTab>, entriesModifier: FabricItemGroupEntries.() -> Unit) {
    ItemGroupEvents.modifyEntriesEvent(tab).register(entriesModifier)
}

// Display Items
// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun CreativeModeTab.Output.acceptAll(list: Collection<ItemLike>) {
    acceptAll(list.mapToStack())
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun CreativeModeTab.Output.acceptAll(vararg items: ItemLike) {
    items.forEach(this::accept)
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun CreativeModeTab.Output.acceptAllLists(vararg lists: Collection<ItemLike>) {
    lists.forEach(this::acceptAll)
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun <T : Any> CreativeModeTab.Output.addRegistryEntries(
    params: ItemDisplayParameters, registry: ResourceKey<Registry<T>>, stackMapper: Function<Holder<T>, ItemStack>,
) {
    params.holders().lookup(registry).ifPresent { potion ->
        potion.listElements()
            .map(stackMapper)
            .forEach(this::accept)
    }
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun CreativeModeTab.Output.addPotionEntries(params: ItemDisplayParameters, potionItem: Item) {
    addRegistryEntries(params, Registries.POTION) { potion ->
        PotionContents.createItemStack(potionItem, potion)
    }
}

// Modify Display Items
// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun FabricItemGroupEntries.addAfter(stack: ItemStack, list: Collection<ItemLike>) {
    addAfter(stack, list.mapToStack())
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun FabricItemGroupEntries.addAfter(item: ItemLike, list: Collection<ItemLike>) {
    addAfter(item, list.mapToStack())
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun FabricItemGroupEntries.addBefore(stack: ItemStack, list: Collection<ItemLike>) {
    addAfter(stack, list.mapToStack())
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun FabricItemGroupEntries.addBefore(item: ItemLike, list: Collection<ItemLike>) {
    addAfter(item, list.mapToStack())
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun FabricItemGroupEntries.prepend(list: Collection<ItemLike>) {
    list.forEach(this::prepend)
}

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun FabricItemGroupEntries.prepend(vararg items: ItemLike) {
    items.forEach(this::prepend)
}

// Misc
// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun Collection<ItemLike>.mapToStack() = map { it.asItem().defaultInstance }
