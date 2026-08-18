package org.teamvoided.dusks_and_dungeons.data.gen.assets.models

import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.data.models.model.TextureSlot.ALL
import net.minecraft.data.models.model.TextureSlot.PARTICLE
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import java.util.*

data object DnDModels {

    val SMALL_PUMPKIN = block("parent/small_pumpkin", PARTICLE, ALL)
    val SMALL_CARVED_PUMPKIN = block("parent/small_carved_pumpkin", PARTICLE, ALL)
    val SMALL_GLOWING_PUMPKIN = block("parent/small_glowing_pumpkin", PARTICLE, ALL)


    fun block(parent: String, vararg requiredTextures: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(id("block/$parent")), Optional.empty(), *requiredTextures)
    }

}
