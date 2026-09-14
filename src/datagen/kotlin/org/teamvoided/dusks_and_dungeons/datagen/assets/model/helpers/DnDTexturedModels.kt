package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Block
import java.util.function.Function

/**
 * Object for all DnD `TexturedModel.Provider`'s
 */
object DnDTexturedModels {

    val CARPET_DOWN = createDefault(TextureMapping::wool, DnDModels.CARPET_DOWN)

    // region Tinted
    val TINTED_CUBE = createDefault(TextureMapping::cube, DnDModels.CUBE_ALL_TINTED)
    val TINTED_TOP_BOTTOM_WITH_WALL = createDefault(TextureMapping::cubeBottomTopWithWall, DnDModels.CUBE_BOTTOM_TOP)


    val TINTED_CARPET = createDefault(TextureMapping::wool, DnDModels.CARPET_TINTED)
    val TINTED_CARPET_DOWN = createDefault(TextureMapping::wool, DnDModels.CARPET_DOWN_TINTED)
    // endregion

    fun createDefault(fn: Function<Block, TextureMapping>, model: ModelTemplate): TexturedModel.Provider {
        return TexturedModel.createDefault(fn, model)
    }

}