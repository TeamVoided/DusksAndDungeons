package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents.ALLOW_BLOCK_DUST_TINT
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.block.BlockColor
import net.minecraft.client.renderer.BiomeColors
import net.minecraft.client.renderer.RenderType
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockAndTintGetter
import net.minecraft.world.level.FoliageColor
import net.minecraft.world.level.GrassColor
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.block.CUTOUT_BLOCKS
import org.teamvoided.dusks_and_dungeons.util.block.GRASS_TINT_BLOCKS
import org.teamvoided.dusks_and_dungeons.util.block.TINT_PARTICLES
import org.teamvoided.dusks_and_dungeons.util.block.TRANSLUCENT_BLOCKS
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap.INSTANCE as BlockRenderLayerMap


object DnDBlocksClient {
    fun init() {
        registerTint(
            { _, world, pos, _ -> foliageColor(world, pos) },
            DnDBlocks.OAK_LEAF_PILE,
            DnDBlocks.JUNGLE_LEAF_PILE,
            DnDBlocks.ACACIA_LEAF_PILE,
            DnDBlocks.DARK_OAK_LEAF_PILE,
            DnDBlocks.MANGROVE_LEAF_PILE
        )
        registerTint(FoliageColor.getEvergreenColor(), DnDBlocks.SPRUCE_LEAF_PILE)
        registerTint(FoliageColor.getBirchColor(), DnDBlocks.BIRCH_LEAF_PILE)
        registerTint({ _, world, pos, _ -> grassColor(world, pos) }, *GRASS_TINT_BLOCKS.toTypedArray())
        registerTint(
            { _, world, pos, tintIndex -> if (tintIndex != 0) grassColor(world, pos) else -1 },
            *DnDBlockLists.flowerbedBlocks.toTypedArray()
        )

        registerTint(
            { _, world, pos, _ -> waterColor(world, pos) },
            DnDBlocks.TINTED_SAND,
            DnDBlocks.TINTED_SANDSTONE,
            DnDBlocks.CHISELED_TINTED_SANDSTONE,
            DnDBlocks.CUT_TINTED_SANDSTONE,
        )

        CUTOUT_BLOCKS.forEach { BlockRenderLayerMap.putBlock(it, RenderType.cutout()) }
        TRANSLUCENT_BLOCKS.forEach { BlockRenderLayerMap.putBlock(it, RenderType.translucent()) }
        ALLOW_BLOCK_DUST_TINT.register { state, _, _ -> state.block !in GRASS_TINT_BLOCKS || state.block in TINT_PARTICLES }
    }

    fun registerTint(provider: BlockColor, vararg blocks: Block) =
        ColorProviderRegistry.BLOCK.register(provider, *blocks)

    fun registerTint(tint: Int, vararg blocks: Block) = registerTint({ _, _, _, _ -> tint }, *blocks)

    fun grassColor(world: BlockAndTintGetter?, pos: BlockPos?): Int {
        return if (world != null && pos != null) BiomeColors.getAverageGrassColor(world, pos)
        else GrassColor.getDefaultColor()
    }

    fun foliageColor(world: BlockAndTintGetter?, pos: BlockPos?): Int {
        return if (world != null && pos != null) BiomeColors.getAverageFoliageColor(world, pos)
        else FoliageColor.get(0.8, 0.4)
    }

    fun waterColor(world: BlockAndTintGetter?, pos: BlockPos?): Int {
        return if (world != null && pos != null) BiomeColors.getAverageWaterColor(world, pos)
        else -1
    }
}