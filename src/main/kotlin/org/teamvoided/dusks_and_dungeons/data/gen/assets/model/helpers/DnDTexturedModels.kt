package org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers

import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Block
import java.util.function.Function

object DnDTexturedModels {

    val CARPET_DOWN = createDefault(TextureMapping::wool, DnDModels.CARPET_DOWN)


    fun createDefault(fn: Function<Block, TextureMapping>, model: ModelTemplate): TexturedModel.Provider {
        return TexturedModel.createDefault(fn, model)
    }

}