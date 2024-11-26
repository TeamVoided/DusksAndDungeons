@file:Suppress("unused")

package org.teamvoided.voidlib.helpers

fun <T : Any> MutableCollection<T>.addInPlace(element: T): MutableCollection<T> {
    this.add(element)
    return this
}

fun <T : Any> MutableCollection<T>.addAllInPlace(vararg element: T): MutableCollection<T> {
    this.addAll(element)
    return this
}

fun <T : Any> MutableCollection<T>.addAndReturn(element: T): T {
    this.add(element)
    return element
}
