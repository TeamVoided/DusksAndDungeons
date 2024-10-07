package org.teamvoided.dusk_autumn.data.gen.providers.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.BlockStateModelGenerator
import org.teamvoided.dusk_autumn.DusksAndDungeons
import org.teamvoided.dusk_autumn.init.blocks.DnDOverlayBlocks
import org.teamvoided.dusk_autumn.util.*

object OverlayModels {
    fun overlayModels(gen: BlockStateModelGenerator){
        val stone = DusksAndDungeons.id("block/cobbled/stone_overlay")
        val deepslate = DusksAndDungeons.id("block/cobbled/deepslate_overlay")
        val blackstone = DusksAndDungeons.id("block/cobbled/blackstone_overlay")
        gen.cubeOverlay(stone)
        gen.cubeOverlay(deepslate)
        gen.cubeOverlay(blackstone)
        gen.cube15Overlay(stone)
        gen.cube15Overlay(deepslate)
        gen.cube15Overlay(blackstone)

        gen.grassWithOverlay(DnDOverlayBlocks.ROCKY_GRASS, Blocks.GRASS_BLOCK, stone)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.ROCKY_PODZOL, Blocks.PODZOL, stone)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.ROCKY_MYCELIUM, Blocks.MYCELIUM, stone)
        gen.cube15WithOverlay(DnDOverlayBlocks.ROCKY_DIRT_PATH, Blocks.DIRT_PATH, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_DIRT, Blocks.DIRT, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_COARSE_DIRT, Blocks.COARSE_DIRT, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_MUD, Blocks.MUD, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_SNOW, Blocks.SNOW_BLOCK, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_GRAVEL, Blocks.GRAVEL, stone)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.ROCKY_SAND, Blocks.SAND, stone)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.ROCKY_RED_SAND, Blocks.RED_SAND, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_SOUL_SAND, Blocks.SOUL_SAND, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_SOUL_SOIL, Blocks.SOUL_SOIL, stone)

        gen.grassWithOverlay(DnDOverlayBlocks.SLATED_GRASS, Blocks.GRASS_BLOCK, deepslate)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.SLATED_PODZOL, Blocks.PODZOL, deepslate)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.SLATED_MYCELIUM, Blocks.MYCELIUM, deepslate)
        gen.cube15WithOverlay(DnDOverlayBlocks.SLATED_DIRT_PATH, Blocks.DIRT_PATH, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_DIRT, Blocks.DIRT, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_COARSE_DIRT, Blocks.COARSE_DIRT, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_MUD, Blocks.MUD, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_SNOW, Blocks.SNOW_BLOCK, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_GRAVEL, Blocks.GRAVEL, deepslate)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.SLATED_SAND, Blocks.SAND, deepslate)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.SLATED_RED_SAND, Blocks.RED_SAND, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_SOUL_SAND, Blocks.SOUL_SAND, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_SOUL_SOIL, Blocks.SOUL_SOIL, deepslate)

        gen.grassWithOverlay(DnDOverlayBlocks.BLACKSTONE_GRASS, Blocks.GRASS_BLOCK, blackstone)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.BLACKSTONE_PODZOL, Blocks.PODZOL, blackstone)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.BLACKSTONE_MYCELIUM, Blocks.MYCELIUM, blackstone)
        gen.cube15WithOverlay(DnDOverlayBlocks.BLACKSTONE_DIRT_PATH, Blocks.DIRT_PATH, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_DIRT, Blocks.DIRT, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_COARSE_DIRT, Blocks.COARSE_DIRT, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_MUD, Blocks.MUD, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_SNOW, Blocks.SNOW_BLOCK, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_GRAVEL, Blocks.GRAVEL, blackstone)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.BLACKSTONE_SAND, Blocks.SAND, blackstone)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.BLACKSTONE_RED_SAND, Blocks.RED_SAND, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_SOUL_SAND, Blocks.SOUL_SAND, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL, Blocks.SOUL_SOIL, blackstone)
    }
}