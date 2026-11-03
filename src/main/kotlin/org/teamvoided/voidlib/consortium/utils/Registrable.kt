package org.teamvoided.voidlib.consortium.utils

import java.util.function.BiConsumer

interface Registrable<T : Any> {
    fun register(consumer: BiConsumer<String, T>)
}