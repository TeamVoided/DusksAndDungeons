package org.teamvoided.dusks_and_dungeons.block.not_blocks

import net.minecraft.util.StringRepresentable
import net.minecraft.core.Direction

enum class CarpetSides(val string: String) : StringRepresentable {
    NONE("none"),
    SMALL("low"),
    TALL("tall");

    override fun toString(): String = this.serializedName

    override fun getSerializedName(): String = string
}