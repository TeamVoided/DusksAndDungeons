@file:Suppress("unused")

package org.teamvoided.dusks_and_dungeons.util

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.core.*
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.phys.Vec3
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import kotlin.jvm.optionals.getOrNull

const val pi = 3.1415927f
const val rotate360 = 6.28319f

fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment

fun <T : Any> isModHolder(holder: Holder<T>) = holder.`is` { it.location().namespace == MODID }

fun <T : Any> HolderLookup.Provider.getModHolders(registry: ResourceKey<Registry<T>>): List<Holder.Reference<T>> {
    return lookup(registry).getOrNull()
        ?.listElements()
        ?.filter(::isModHolder)
        ?.toList() ?: listOf()
}

fun <T : Any> getModHolders(registry: Registry<T>): List<Holder.Reference<T>> = registry.holders()
    .filter(::isModHolder)
    .toList()

fun <T : Any> getModEntries(registry: Registry<T>): List<T> = registry.holders()
    .filter(::isModHolder)
    .map(Holder<T>::value)
    .toList()

fun <V : Any, T : V> Registry<V>.register(id: ResourceLocation, entry: T): T = Registry.register(this, id, entry)
fun <V : Any, T : V> Registry<T>.registerHolder(id: ResourceLocation, entry: T): Holder.Reference<T> =
    Registry.registerForHolder(this, id, entry)

fun <T : Any, R : Registry<T>> ResourceKey<R>.tag(id: ResourceLocation): TagKey<T> = TagKey.create(this, id)
fun <T : Any, R : Registry<T>> ResourceKey<R>.key(id: ResourceLocation): ResourceKey<T> = ResourceKey.create(this, id)

fun <T : Any> Level.getTag(tag: TagKey<T>): HolderSet.Named<T>? {
    return registryAccess().lookup(tag.registry).getOrNull()?.get(tag)?.getOrNull()
}

fun ResourceKey<*>.toLangKey(): String = location().toLangKey()
fun ResourceLocation.toLangKey(): String = toLanguageKey().replace("/", ".")

fun ResourceKey<LootTable>.isEmpty(): Boolean = this == BuiltInLootTables.EMPTY

fun Vec3.map(func: (Double) -> Double): Vec3 = Vec3(func(x), func(y), func(z))
fun Vec3.add(pos: BlockPos): Vec3 = add(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())


fun Direction.counterClockWise(rotations: Int): Direction {
    if (axis.isVertical) {
        return this
    }

    var directionReturn = this
    repeat(rotations) {
        directionReturn = directionReturn.counterClockWise
    }

    return directionReturn
}


fun emptyItemList(capacity: Int): NonNullList<ItemStack> {
    return NonNullList.withSize(capacity, ItemStack.EMPTY)
}
