@file:Suppress("unused")

package org.teamvoided.voidlib.helpers

import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.ModContainer
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.Consumer

fun registerBuiltInPack(
    modId: String, id: Identifier, packType: ResourcePackActivationType = ResourcePackActivationType.NORMAL
) = useMod(modId) {
    assert(ResourceManagerHelper.registerBuiltinResourcePack(id, it, packType))
    { "Failed to register built-in pack \"$id\" !" }
}

fun registerBuiltInPack(
    modId: String, id: Identifier, name: Text,
    packType: ResourcePackActivationType = ResourcePackActivationType.NORMAL
) = useMod(modId) {
    assert(ResourceManagerHelper.registerBuiltinResourcePack(id, it, name, packType))
    { "Failed to register built-in pack \"$id\" !" }
}

fun useMod(id: String, consumer: Consumer<ModContainer>) =
    FabricLoader.getInstance().getModContainer(id).ifPresent(consumer)
