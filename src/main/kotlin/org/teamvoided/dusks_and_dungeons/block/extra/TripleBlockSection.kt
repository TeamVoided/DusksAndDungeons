package org.teamvoided.dusks_and_dungeons.block.extra

import net.minecraft.core.Direction
import net.minecraft.util.StringRepresentable

enum class TripleBlockSection(val direction: Direction) : StringRepresentable {
    TOP(Direction.DOWN),
    MIDDLE(Direction.UP),
    BOTTOM(Direction.UP);

    override fun toString(): String = getSerializedName()

    override fun getSerializedName(): String = name.lowercase()

    fun getNumber(): Int {
        return when (this) {
            TOP -> 3
            MIDDLE -> 2
            BOTTOM -> 1
        }
    }

    companion object {

        fun getBelowSection(section: TripleBlockSection): TripleBlockSection? {
            return when (section) {
                TOP -> MIDDLE
                MIDDLE -> BOTTOM
                BOTTOM -> null
            }
        }

    }
}