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

    val BUTTON_TINTED = block("parent/tint/button", TEXTURE)
    val BUTTON_PRESSED_TINTED = block("parent/tint/button_pressed", "_pressed", TEXTURE)
    val BUTTON_INVENTORY_TINTED = block("parent/tint/button_inventory", "_inventory", TEXTURE)

    fun block(parent: String, vararg slots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(id("block/$parent")), Optional.empty(), *slots)
    }

    fun block(parent: String, suffix: String, vararg slots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(id("block/$parent")), Optional.of(suffix), *slots)
    }

}
