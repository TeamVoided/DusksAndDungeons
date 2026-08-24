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

    val SMALL_PUMPKIN = parent("small_pumpkin", PARTICLE, ALL)
    val SMALL_CARVED_PUMPKIN = parent("small_carved_pumpkin", PARTICLE, ALL)
    val SMALL_CARVED_PUMPKIN_WALL = parent("small_carved_pumpkin_wall", "_wall", PARTICLE, ALL)
    val SMALL_GLOWING_PUMPKIN = parent("small_glowing_pumpkin", PARTICLE, ALL)
    val SMALL_GLOWING_PUMPKIN_WALL = parent("small_glowing_pumpkin_wall", "_wall", PARTICLE, ALL)

    val BIG_CHAIN = parent("big_chain", PARTICLE, ALL)
    val BIG_LANTERN = parent("big_lantern", PARTICLE, SIDE, END)

    // region Graves
    val GRAVESTONE = parent("gravestone", FRONT, SIDE)
    val GRAVESTONE_CENTERED = parent("gravestone_centered", FRONT, SIDE)
    val SMALL_GRAVESTONE = parent("small_gravestone", FRONT)
    val SMALL_GRAVESTONE_CENTERED = parent("small_gravestone_centered", FRONT)
    val HEADSTONE = parent("headstone", ALL)
    val HEADSTONE_CENTERED = parent("headstone_centered", ALL)
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

    val CARPET_DOWN = parent("carpet_down", "_down", WOOL)

    // region Offset Models
    val OFFSET_WALL_POST = parent("offset/wall_post", "_post", WALL)
    val OFFSET_WALL_SIDE = parent("offset/wall_side", "_side", WALL)
    val OFFSET_WALL_SIDE_TALL = parent("offset/wall_side_tall", "_side_tall", WALL)
    val OFFSET_WALL_INVENTORY = parent("offset/wall_inventory", "_inventory", WALL)
    // endregion


    // region Func's
    fun tintedBlock(name: String, vararg slots: TextureSlot): ModelTemplate {
        return parent("tint/$name", *slots)
    }

    fun tintedBlock(name: String, suffix: String, vararg slots: TextureSlot): ModelTemplate {
        return parent("tint/$name", suffix, *slots)
    }

    fun parent(name: String, vararg slots: TextureSlot): ModelTemplate {
        return block("parent/$name", *slots)
    }

    fun parent(name: String, suffix: String, vararg slots: TextureSlot): ModelTemplate {
        return block("parent/$name", suffix, *slots)
    }

    fun block(name: String, vararg slots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(id("block/$name")), Optional.empty(), *slots)
    }

    fun block(name: String, suffix: String, vararg slots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(id("block/$name")), Optional.of(suffix), *slots)
    }
    // endregion

}
