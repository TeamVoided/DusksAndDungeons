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

    // region Graves
    val GRAVESTONE = block("parent/gravestone", FRONT, SIDE)
    val GRAVESTONE_CENTERED = block("parent/gravestone_centered", FRONT, SIDE)
    val SMALL_GRAVESTONE = block("parent/small_gravestone", FRONT)
    val SMALL_GRAVESTONE_CENTERED = block("parent/small_gravestone_centered", FRONT)
    val HEADSTONE = block("parent/headstone", ALL)
    val HEADSTONE_CENTERED = block("parent/headstone_centered", ALL)
    // endregion

    // region Tinted
    val BUTTON_TINTED = tintedBlock("button", TEXTURE)
    val BUTTON_PRESSED_TINTED = tintedBlock("button_pressed", "_pressed", TEXTURE)
    val BUTTON_INVENTORY_TINTED = tintedBlock("button_inventory", "_inventory", TEXTURE)

    val DOOR_BOTTOM_LEFT_TINTED = tintedBlock("door_bottom_left", "_bottom_left", TOP, BOTTOM)
    val DOOR_BOTTOM_LEFT_OPEN_TINTED = tintedBlock("door_bottom_left_open", "_bottom_left_open", TOP, BOTTOM)
    val DOOR_BOTTOM_RIGHT_TINTED = tintedBlock("door_bottom_right", "_bottom_right", TOP, BOTTOM)
    val DOOR_BOTTOM_RIGHT_OPEN_TINTED = tintedBlock("door_bottom_right_open", "_bottom_right_open", TOP, BOTTOM)
    val DOOR_TOP_LEFT_TINTED = tintedBlock("door_top_left", "_top_left", TOP, BOTTOM)
    val DOOR_TOP_LEFT_OPEN_TINTED = tintedBlock("door_top_left_open", "_top_left_open", TOP, BOTTOM)
    val DOOR_TOP_RIGHT_TINTED = tintedBlock("door_top_right", "_top_right", TOP, BOTTOM)
    val DOOR_TOP_RIGHT_OPEN_TINTED = tintedBlock("door_top_right_open", "_top_right_open", TOP, BOTTOM)

    val PRESSURE_PLATE_UP_TINTED = tintedBlock("pressure_plate_up", TEXTURE)
    val PRESSURE_PLATE_DOWN_TINTED = tintedBlock("pressure_plate_down", "_down", TEXTURE)

    val ORIENTABLE_TRAPDOOR_TOP_TINTED = tintedBlock("orientable_trapdoor_top", "_top", TEXTURE)
    val ORIENTABLE_TRAPDOOR_BOTTOM_TINTED = tintedBlock("orientable_trapdoor_bottom", "_bottom", TEXTURE)
    val ORIENTABLE_TRAPDOOR_OPEN_TINTED = tintedBlock("orientable_trapdoor_open", "_open", TEXTURE)
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
