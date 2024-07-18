package org.teamvoided.dusk_autumn

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.client.render.RenderLayer
import net.minecraft.component.type.DyedColorComponent
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskBlocks.CASCADE_LEAF_COLOR
import org.teamvoided.dusk_autumn.init.DuskItems
import org.teamvoided.dusk_autumn.init.DuskParticles.CASCADE_LEAF_PARTICLE
import org.teamvoided.dusk_autumn.particle.FallingLeafParticle.Companion.FallingLeafFactory

class DuskAutumnsClient {

    fun init() {
        blocksClient()
        initClient()
        ParticleFactoryRegistry.getInstance().register(CASCADE_LEAF_PARTICLE, ::FallingLeafFactory)
    }


    private fun blocksClient() {
        listOf(
            DuskBlocks.CASCADE_SAPLING,
            DuskBlocks.POTTED_CASCADE_SAPLING,
            DuskBlocks.CASCADE_LEAVES,
            DuskBlocks.BLUE_PETALS,
            DuskBlocks.VIOLET_DAISY,
            DuskBlocks.POTTED_VIOLET_DAISY,
            DuskBlocks.GOLDEN_BIRCH_SAPLING,
            DuskBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
            DuskBlocks.GOLDEN_BIRCH_LEAVES,
            DuskBlocks.CASCADE_LEAF_PILE,
            DuskBlocks.OAK_LEAF_PILE,
            DuskBlocks.SPRUCE_LEAF_PILE,
            DuskBlocks.BIRCH_LEAF_PILE,
            DuskBlocks.JUNGLE_LEAF_PILE,
            DuskBlocks.ACACIA_LEAF_PILE,
            DuskBlocks.DARK_OAK_LEAF_PILE,
            DuskBlocks.MANGROVE_LEAF_PILE,
            DuskBlocks.CHERRY_LEAF_PILE,
            DuskBlocks.AZALEA_LEAF_PILE,
            DuskBlocks.FLOWERING_AZALEA_LEAF_PILE,
            DuskBlocks.GOLDEN_BIRCH_LEAF_PILE,
            DuskBlocks.WILD_WHEAT,
            DuskBlocks.GOLDEN_BEETROOTS,
            DuskBlocks.MOONBERRY_VINE,
            DuskBlocks.MOONBERRY_VINELET
        ).forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout()) }

        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                if (world != null && pos != null) BiomeColors.getFoliageColor(world, pos)
                else FoliageColors.getColor(0.8, 0.4)
            },
            DuskBlocks.OAK_LEAF_PILE, DuskBlocks.JUNGLE_LEAF_PILE,
            DuskBlocks.ACACIA_LEAF_PILE, DuskBlocks.DARK_OAK_LEAF_PILE, DuskBlocks.MANGROVE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                if (world != null && pos != null) BiomeColors.getGrassColor(world, pos)
                else GrassColors.getColor(0.8, 0.4)
            }, DuskBlocks.BLUE_PETALS
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getSpruceColor() }, DuskBlocks.SPRUCE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getBirchColor() }, DuskBlocks.BIRCH_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> CASCADE_LEAF_COLOR }, DuskBlocks.CASCADE_LEAVES, DuskBlocks.CASCADE_LEAF_PILE
        )

//    val GOLDEN_BIRCH_COLOR = 16761873
//    val GOLDEN_BIRCH_COLOR = 16760872    }
    }

    private fun initClient() {
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getDefaultColor() },
            DuskItems.OAK_LEAF_PILE,
            DuskItems.JUNGLE_LEAF_PILE,
            DuskItems.ACACIA_LEAF_PILE,
            DuskItems.DARK_OAK_LEAF_PILE,
            DuskItems.MANGROVE_LEAF_PILE
        )

        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getSpruceColor() }, DuskItems.SPRUCE_LEAF_PILE)
        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getBirchColor() }, DuskItems.BIRCH_LEAF_PILE)
        ColorProviderRegistry.ITEM.register(
            { _, _ -> CASCADE_LEAF_COLOR }, DuskItems.CASCADE_LEAVES, DuskItems.CASCADE_LEAF_PILE
        )
        ColorProviderRegistry.ITEM.register(
            { stack, _ -> DyedColorComponent.getColorOrDefault(stack, 0xffffff) }, DuskItems.FARMERS_HAT
        )
    }
}