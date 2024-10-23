package org.teamvoided.dusk_autumn.util

import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.ModContainer
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DusksAndDungeons.MODID
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import java.util.function.Consumer

val BETTER_BRICK_NAMES = id("fancy_names")

fun registerBuiltInPack(id: Identifier, packType: ResourcePackActivationType = ResourcePackActivationType.NORMAL) =
    useMod {
        assert(
            ResourceManagerHelper.registerBuiltinResourcePack(id, it, packType)
        ) { "Failed to register built-in pack \"$id\" !" }
    }

@Suppress("unused")
fun registerBuiltInPack(
    id: Identifier, name: Text, packType: ResourcePackActivationType = ResourcePackActivationType.NORMAL
) = useMod {
    assert(
        ResourceManagerHelper.registerBuiltinResourcePack(id, it, name, packType)
    ) { "Failed to register built-in pack \"$id\" !" }
}


fun useMod(consumer: Consumer<ModContainer>) {
    FabricLoader.getInstance().getModContainer(MODID).ifPresent(consumer)
}
