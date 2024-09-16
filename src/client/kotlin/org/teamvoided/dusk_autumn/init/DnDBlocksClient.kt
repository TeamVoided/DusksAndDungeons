package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.util.DnDBlockLists

object DnDBlocksClient {
    fun init() {
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                foliageColorOrDefault(world, pos)
            },
            DnDWoodBlocks.OAK_LEAF_PILE,
            DnDWoodBlocks.JUNGLE_LEAF_PILE,
            DnDWoodBlocks.ACACIA_LEAF_PILE,
            DnDWoodBlocks.DARK_OAK_LEAF_PILE,
            DnDWoodBlocks.MANGROVE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, tintIndex ->
                if (tintIndex != 0) {
                    grassColorOrDefault(world, pos)
                } else -1
            },
            *DnDBlockLists.flowerbedBlocks.toTypedArray()
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ -> grassColorOrDefault(world, pos) },
            *DnDBlocks.GRASS_TINT_BLOCKS.toTypedArray()
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getSpruceColor() }, DnDWoodBlocks.SPRUCE_LEAF_PILE
        )
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> FoliageColors.getBirchColor() }, DnDWoodBlocks.BIRCH_LEAF_PILE
        )

        DnDBlocks.CUTOUT_BLOCKS.forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout()) }
        DnDBlocks.TRANSLUCENT_BLOCKS.forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getTranslucent()) }


        ParticleRenderEvents.ALLOW_BLOCK_DUST_TINT.register { blockState, _, _ ->
            blockState.block !in DnDBlocks.GRASS_TINT_BLOCKS
        }
    }

    private fun grassColorOrDefault(world: BlockRenderView?, pos: BlockPos?): Int {
        return if (world != null && pos != null) BiomeColors.getGrassColor(world, pos)
        else GrassColors.getDefault()
    }

    private fun foliageColorOrDefault(world: BlockRenderView?, pos: BlockPos?): Int {
        return if (world != null && pos != null) BiomeColors.getFoliageColor(world, pos)
        else FoliageColors.getColor(0.8, 0.4)
    }
}