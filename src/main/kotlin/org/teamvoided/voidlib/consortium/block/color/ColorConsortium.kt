@file:Suppress("unused")

package org.teamvoided.voidlib.consortium.block.color

import net.minecraft.block.Block
import org.teamvoided.voidlib.consortium.block.BlockCollection
import java.util.function.BiConsumer

typealias NameCreator = (prefix: String, color: String) -> String

fun <T> basedFullColorCollections(
    name: String, provider: FullColorCollections,
    baseConstructor: (coloredBlock: Block) -> T,
    constructor: (coloredBlock: Block, uncoloredBlock: Block) -> T
): FullColorConsortium<T> where T : Block {
    val uncolored = baseConstructor(provider.uncolored())
    return FullColorConsortium(
        name,
        uncolored,
        constructor(provider.white, uncolored), constructor(provider.orange, uncolored),
        constructor(provider.magenta, uncolored), constructor(provider.lightBlue, uncolored),
        constructor(provider.yellow, uncolored), constructor(provider.lime, uncolored),
        constructor(provider.pink, uncolored), constructor(provider.gray, uncolored),
        constructor(provider.lightGray, uncolored), constructor(provider.cyan, uncolored),
        constructor(provider.purple, uncolored), constructor(provider.blue, uncolored),
        constructor(provider.brown, uncolored), constructor(provider.green, uncolored),
        constructor(provider.red, uncolored), constructor(provider.black, uncolored),
    )
}

open class FullColorConsortium<T>(
    name: String,
    val uncolored: T,
    white: T, orange: T,
    magenta: T, lightBlue: T,
    yellow: T, lime: T,
    pink: T, gray: T,
    lightGray: T, cyan: T,
    purple: T, blue: T,
    brown: T, green: T,
    red: T, black: T,
) : ColorConsortium<T>(
    name,
    white, orange, magenta, lightBlue, yellow, lime, pink, gray, lightGray, cyan, purple, blue, brown, green, red, black
) where T : Block {

    constructor(name: String, provider: FullColorCollections, constructor: (coloredBlock: Block) -> T) : this(
        name,
        constructor(provider.uncolored()),
        constructor(provider.white), constructor(provider.orange),
        constructor(provider.magenta), constructor(provider.lightBlue),
        constructor(provider.yellow), constructor(provider.lime),
        constructor(provider.pink), constructor(provider.gray),
        constructor(provider.lightGray), constructor(provider.cyan),
        constructor(provider.purple), constructor(provider.blue),
        constructor(provider.brown), constructor(provider.green),
        constructor(provider.red), constructor(provider.black),
    )

    override val size: Int = 17

    override val list = listOf(uncolored) + super.list
    override fun getIdMap() = mapOf("$prefix$name" to uncolored) + super.getIdMap()

    override fun toColorCollection(): FullColorCollections = FullColorCollections(
        uncolored,
        white, orange, magenta, lightBlue, yellow, lime, pink, gray,
        lightGray, cyan, purple, blue, brown, green, red, black,
    )
}


@Suppress("MemberVisibilityCanBePrivate")
open class ColorConsortium<T>(
    val name: String,
    val white: T, val orange: T,
    val magenta: T, val lightBlue: T,
    val yellow: T, val lime: T,
    val pink: T, val gray: T,
    val lightGray: T, val cyan: T,
    val purple: T, val blue: T,
    val brown: T, val green: T,
    val red: T, val black: T,
    var prefix: String = "",
) : BlockCollection<T> where T : Block {
    constructor(name: String, provider: ColorCollection, constructor: (coloredBlock: Block) -> T) : this(
        name,
        constructor(provider.white), constructor(provider.orange),
        constructor(provider.magenta), constructor(provider.lightBlue),
        constructor(provider.yellow), constructor(provider.lime),
        constructor(provider.pink), constructor(provider.gray),
        constructor(provider.lightGray), constructor(provider.cyan),
        constructor(provider.purple), constructor(provider.blue),
        constructor(provider.brown), constructor(provider.green),
        constructor(provider.red), constructor(provider.black),
    )


    override val size: Int = 16
    private val originalList = listOf(
        white, orange, magenta, lightBlue, yellow, lime, pink, gray,
        lightGray, cyan, purple, blue, brown, green, red, black
    )
    override val list = originalList.toList()
    override fun getIdMap() = listOf(
        "${prefix}white_$name", "${prefix}orange_$name",
        "${prefix}magenta_$name", "${prefix}light_blue_$name",
        "${prefix}yellow_$name", "${prefix}lime_$name",
        "${prefix}pink_$name", "${prefix}gray_$name",
        "${prefix}light_gray_$name", "${prefix}cyan_$name",
        "${prefix}purple_$name", "${prefix}blue_$name",
        "${prefix}brown_$name", "${prefix}green_$name",
        "${prefix}red_$name", "${prefix}black_$name",
    ).zip(originalList).toMap()


    override fun register(consumer: BiConsumer<String, T>) = getIdMap().forEach(consumer)

    override fun contains(element: T): Boolean = list.contains(element)
    override fun containsAll(elements: Collection<T>): Boolean = list.containsAll(elements)
    override fun isEmpty(): Boolean = false
    override fun iterator(): Iterator<T> = list.iterator()

    open fun toColorCollection(): ColorCollection = ColorCollection(
        white, orange, magenta, lightBlue, yellow, lime, pink, gray,
        lightGray, cyan, purple, blue, brown, green, red, black,
    )
}







