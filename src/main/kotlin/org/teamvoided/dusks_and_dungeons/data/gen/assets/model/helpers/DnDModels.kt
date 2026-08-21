package org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers

import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.data.models.model.TextureSlot.*
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import java.util.*

/**
 * Object for all DnD `"parent/"` models
 */
data object DnDModels {

    val SMALL_PUMPKIN = block("parent/small_pumpkin", PARTICLE, ALL)
    val SMALL_CARVED_PUMPKIN = block("parent/small_carved_pumpkin", PARTICLE, ALL)
    val SMALL_CARVED_PUMPKIN_WALL = block("parent/small_carved_pumpkin_wall", "_wall", PARTICLE, ALL)
    val SMALL_GLOWING_PUMPKIN = block("parent/small_glowing_pumpkin", PARTICLE, ALL)
    val SMALL_GLOWING_PUMPKIN_WALL = block("parent/small_glowing_pumpkin_wall", "_wall", PARTICLE, ALL)

    val BIG_CHAIN = block("parent/big_chain", PARTICLE, ALL)
    val BIG_LANTERN = block("parent/big_lantern", PARTICLE, SIDE, END)

    // region Tinted
    val BUTTON_TINTED = tintedBlock("button", TEXTURE)
    val BUTTON_PRESSED_TINTED = tintedBlock("button_pressed", "_pressed", TEXTURE)
    val BUTTON_INVENTORY_TINTED = tintedBlock("button_inventory", "_inventory", TEXTURE)

    val PRESSURE_PLATE_UP_TINTED = tintedBlock("pressure_plate_up", TEXTURE)
    val PRESSURE_PLATE_DOWN_TINTED = tintedBlock("pressure_plate_down", "_down", TEXTURE)
    // endregion

    fun tintedBlock(parent: String, vararg slots: TextureSlot) = block("parent/tint/$parent", *slots)
    fun tintedBlock(parent: String, suffix: String, vararg slots: TextureSlot): ModelTemplate {
        return block("parent/tint/$parent", suffix, *slots)
    }

    fun block(parent: String, vararg slots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(id("block/$parent")), Optional.empty(), *slots)
    }

    fun block(parent: String, suffix: String, vararg slots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(id("block/$parent")), Optional.of(suffix), *slots)
    }

}
