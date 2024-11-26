package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap.INSTANCE as BLOCK_RL_MAP
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents.ALLOW_BLOCK_DUST_TINT
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.block.Block
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists


object DnDBlocksClient {
    fun init() {
        registerTint(
            { _, world, pos, _ -> foliageColor(world, pos) },
            DnDWoodBlocks.OAK_LEAF_PILE,
            DnDWoodBlocks.JUNGLE_LEAF_PILE,
            DnDWoodBlocks.ACACIA_LEAF_PILE,
            DnDWoodBlocks.DARK_OAK_LEAF_PILE,
            DnDWoodBlocks.MANGROVE_LEAF_PILE
        )
        registerTint(
            { _, world, pos, tintIndex -> if (tintIndex != 0) grassColor(world, pos) else -1 },
            *DnDBlockLists.flowerbedBlocks.toTypedArray()
        )
        registerTint({ _, world, pos, _ -> grassColor(world, pos) }, *DnDBlocks.GRASS_TINT_BLOCKS.toTypedArray())
        registerTint(FoliageColors.getSpruceColor(), DnDWoodBlocks.SPRUCE_LEAF_PILE)
        registerTint(FoliageColors.getBirchColor(), DnDWoodBlocks.BIRCH_LEAF_PILE)

        DnDBlocks.CUTOUT_BLOCKS.forEach { BLOCK_RL_MAP.putBlock(it, RenderLayer.getCutout()) }
        DnDBlocks.TRANSLUCENT_BLOCKS.forEach { BLOCK_RL_MAP.putBlock(it, RenderLayer.getTranslucent()) }

        ALLOW_BLOCK_DUST_TINT.register { state, _, _ -> state.block !in DnDBlocks.GRASS_TINT_BLOCKS }
    }

    fun registerTint(provider: BlockColorProvider, vararg blocks: Block) =
        ColorProviderRegistry.BLOCK.register(provider, *blocks)

    fun registerTint(tint: Int, vararg blocks: Block) = registerTint({ _, _, _, _ -> tint }, *blocks)

    fun grassColor(world: BlockRenderView?, pos: BlockPos?): Int {
        return if (world != null && pos != null) BiomeColors.getGrassColor(world, pos)
        else GrassColors.getDefault()
    }

    fun foliageColor(world: BlockRenderView?, pos: BlockPos?): Int {
        return if (world != null && pos != null) BiomeColors.getFoliageColor(world, pos)
        else FoliageColors.getColor(0.8, 0.4)
    }
}