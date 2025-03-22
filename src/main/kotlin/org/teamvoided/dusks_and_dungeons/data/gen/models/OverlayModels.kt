package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.block.collections.RockyBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object OverlayModels {
    fun overlayModels(gen: BlockStateModelGenerator) {
        val stone = DusksAndDungeons.id("block/cobbled/stone_overlay")
        val deepslate = DusksAndDungeons.id("block/cobbled/deepslate_overlay")
        val blackstone = DusksAndDungeons.id("block/cobbled/blackstone_overlay")
        gen.cubeOverlay(stone)
        gen.cubeOverlay(deepslate)
        gen.cubeOverlay(blackstone)
        gen.cube15Overlay(stone)
        gen.cube15Overlay(deepslate)
        gen.cube15Overlay(blackstone)

        gen.rockyBlocks(DnDBlocks.ROCKY_BLOCKS, stone)
        gen.rockyBlocks(DnDBlocks.SLATE_BLOCKS, deepslate)
        gen.rockyBlocks(DnDBlocks.BLACKSTONE_BLOCKS, blackstone)
    }

    fun BlockStateModelGenerator.rockyBlocks(rocks: RockyBlocks, overlay: Identifier) {
        this.cubeAllWithTintedOverlay(rocks.dirt, Blocks.DIRT, overlay)
        this.grassWithOverlay(rocks.grass, Blocks.GRASS_BLOCK, overlay)
        this.cubeSnowableColumnWithOverlay(rocks.podzol, Blocks.PODZOL, overlay)
        this.cubeSnowableColumnWithOverlay(rocks.mycelium, Blocks.MYCELIUM, overlay)
        this.cube15WithOverlay(rocks.path, Blocks.DIRT_PATH, overlay)
        this.cubeAllWithTintedOverlay(rocks.coarseDirt, Blocks.COARSE_DIRT, overlay)
        this.cubeAllWithTintedOverlay(rocks.mud, Blocks.MUD, overlay)
        this.cubeAllWithTintedOverlay(rocks.snow, Blocks.SNOW_BLOCK, overlay)
        this.cubeAllWithTintedOverlay(rocks.gravel, Blocks.GRAVEL, overlay)
        this.rotatableCubeAllWithOverlay(rocks.sand, Blocks.SAND, overlay)
        this.rotatableCubeAllWithOverlay(rocks.redSand, Blocks.RED_SAND, overlay)
        this.cubeAllWithTintedOverlay(rocks.soulSand, Blocks.SOUL_SAND, overlay)
        this.cubeAllWithTintedOverlay(rocks.soulSoil, Blocks.SOUL_SOIL, overlay)
    }
}