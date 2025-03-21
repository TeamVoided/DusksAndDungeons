package org.teamvoided.voidlib.consortium.block

import net.minecraft.block.Block
import org.teamvoided.voidlib.consortium.utils.Registrable
import java.util.function.BiConsumer

interface BlockCollection<T> : Collection<T>, Registrable<T> where T : Block {
    val list: List<T>
    override val size: Int get() = this.list.size
    fun getIdMap(): Map<String, T>

    // Default implementation for Collection
    override fun contains(element: T): Boolean = this.list.contains(element)
    override fun containsAll(elements: Collection<T>): Boolean = this.list.containsAll(elements)
    override fun isEmpty(): Boolean = this.list.isEmpty()
    override fun iterator(): Iterator<T> = this.list.iterator()
    override fun register(consumer: BiConsumer<String, T>) = this.getIdMap().forEach(consumer)
}