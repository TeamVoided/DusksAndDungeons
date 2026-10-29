@file:Suppress("unused")

package org.teamvoided.voidlib.helpers

fun <T : Any, Y : T> MutableCollection<T>.addInPlace(element: Y): MutableCollection<T> {
    add(element)
    return this
}

fun <T : Any, Y : T> MutableCollection<T>.addAllInPlace(vararg element: Y): MutableCollection<T> {
    addAll(element)
    return this
}

fun <T : Any, Y : T> MutableCollection<T>.addAndReturn(element: Y): Y {
    add(element)
    return element
}
