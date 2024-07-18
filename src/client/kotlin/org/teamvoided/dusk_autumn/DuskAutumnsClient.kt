package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.client.render.RenderLayer
import org.teamvoided.dusk_autumn.init.DuskBlocks.ACACIA_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.AZALEA_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.BIRCH_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.BLUE_PETALS
import org.teamvoided.dusk_autumn.init.DuskBlocks.CASCADE_LEAF_COLOR
import org.teamvoided.dusk_autumn.init.DuskBlocks.CASCADE_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.CASCADE_LEAVES
import org.teamvoided.dusk_autumn.init.DuskBlocks.CASCADE_SAPLING
import org.teamvoided.dusk_autumn.init.DuskBlocks.CHERRY_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.DARK_OAK_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.FLOWERING_AZALEA_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.GOLDEN_BEETROOTS
import org.teamvoided.dusk_autumn.init.DuskBlocks.GOLDEN_BIRCH_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.GOLDEN_BIRCH_LEAVES
import org.teamvoided.dusk_autumn.init.DuskBlocks.GOLDEN_BIRCH_SAPLING
import org.teamvoided.dusk_autumn.init.DuskBlocks.JUNGLE_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.MANGROVE_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.MOONBERRY_VINE
import org.teamvoided.dusk_autumn.init.DuskBlocks.MOONBERRY_VINELET
import org.teamvoided.dusk_autumn.init.DuskBlocks.OAK_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.POTTED_CASCADE_SAPLING
import org.teamvoided.dusk_autumn.init.DuskBlocks.POTTED_GOLDEN_BIRCH_SAPLING
import org.teamvoided.dusk_autumn.init.DuskBlocks.POTTED_VIOLET_DAISY
import org.teamvoided.dusk_autumn.init.DuskBlocks.SPRUCE_LEAF_PILE
import org.teamvoided.dusk_autumn.init.DuskBlocks.VIOLET_DAISY
import org.teamvoided.dusk_autumn.init.DuskBlocks.WILD_WHEAT

class DuskAutumnsClient {

    fun init() {
        blocksClient()

    }


    private fun blocksClient() {

        listOf(
            CASCADE_SAPLING, POTTED_CASCADE_SAPLING, CASCADE_LEAVES,
            BLUE_PETALS, VIOLET_DAISY, POTTED_VIOLET_DAISY,
            GOLDEN_BIRCH_SAPLING, POTTED_GOLDEN_BIRCH_SAPLING, GOLDEN_BIRCH_LEAVES,
            CASCADE_LEAF_PILE, OAK_LEAF_PILE, SPRUCE_LEAF_PILE, BIRCH_LEAF_PILE, JUNGLE_LEAF_PILE, ACACIA_LEAF_PILE,
            DARK_OAK_LEAF_PILE, MANGROVE_LEAF_PILE, CHERRY_LEAF_PILE, AZALEA_LEAF_PILE,
            FLOWERING_AZALEA_LEAF_PILE, GOLDEN_BIRCH_LEAF_PILE,
            WILD_WHEAT, GOLDEN_BEETROOTS, MOONBERRY_VINE, MOONBERRY_VINELET
        ).forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout()) }

        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                if (world != null && pos != null) BiomeColors.getFoliageColor(world, pos)
                else FoliageColors.getColor(0.8, 0.4)
            },
            OAK_LEAF_PILE,
            JUNGLE_LEAF_PILE,
            ACACIA_LEAF_PILE,
            DARK_OAK_LEAF_PILE,
            MANGROVE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                if (world != null && pos != null) BiomeColors.getGrassColor(world, pos)
                else GrassColors.getColor(0.8, 0.4)
            },
            BLUE_PETALS
        )
        ColorProviderRegistry.BLOCK.register({ _, _, _, _ -> FoliageColors.getSpruceColor() }, SPRUCE_LEAF_PILE)
        ColorProviderRegistry.BLOCK.register({ _, _, _, _ -> FoliageColors.getBirchColor() }, BIRCH_LEAF_PILE)
        ColorProviderRegistry.BLOCK.register({ _, _, _, _ -> CASCADE_LEAF_COLOR }, CASCADE_LEAVES, CASCADE_LEAF_PILE)

//    val GOLDEN_BIRCH_COLOR = 16761873
//    val GOLDEN_BIRCH_COLOR = 16760872    }
    }
}