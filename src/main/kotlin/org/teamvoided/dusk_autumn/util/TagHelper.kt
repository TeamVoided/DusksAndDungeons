package org.teamvoided.dusk_autumn.util

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider

fun <T> FabricTagProvider<T>.FabricTagBuilder.addAll(list: Collection<T>): FabricTagProvider<T>.FabricTagBuilder {
    list.forEach { this.add(it) }
    return this
}