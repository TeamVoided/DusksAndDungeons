package org.teamvoided.dusks_and_dungeons.init.blocks

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.util.Color
import org.teamvoided.dusks_and_dungeons.block.rocky.RockyDirtPathBlock
import org.teamvoided.dusks_and_dungeons.block.rocky.RockyGrassBlock
import org.teamvoided.dusks_and_dungeons.block.rocky.RockyMyceliumBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.register
import org.teamvoided.dusks_and_dungeons.util.block.*

object DnDOverlayBlocks {

    // Cobblestone
    private val stone = MapColor.STONE
    val ROCKY_DIRT = register("dirty_rocks", Block(copy(DIRT).mapColor(stone)).rocky())
    val ROCKY_GRASS =
        register("grassy_rocks", RockyGrassBlock(ROCKY_DIRT, copy(GRASS_BLOCK).mapColor(stone)).rocky().grass())
    val ROCKY_PODZOL = register("podzol_rocks", SnowyBlock(copy(PODZOL).mapColor(stone)).rocky())
    val ROCKY_MYCELIUM =
        register("mycelium_rocks", RockyMyceliumBlock(ROCKY_DIRT, copy(MYCELIUM).mapColor(stone)).rocky())
    val ROCKY_COARSE_DIRT = register("coarsely_dirty_rocks", Block(copy(COARSE_DIRT).mapColor(stone)).rocky())
    val ROCKY_DIRT_PATH =
        register("rocky_path", RockyDirtPathBlock(ROCKY_DIRT, copy(DIRT_PATH).mapColor(stone)).rocky())
    val ROCKY_MUD = register("muddy_rocks", MudBlock(copy(MUD).mapColor(stone)).rocky())
    val ROCKY_SNOW = register("snowy_rocks", Block(copy(SNOW_BLOCK).mapColor(stone)).rocky())
    val ROCKY_GRAVEL = register("rocky_rocks", gravel(stone).rocky())
    val ROCKY_SAND = register("sandy_rocks", sand(stone).rocky())
    val ROCKY_RED_SAND = register("red_sandy_rocks", redSand(stone).rocky())
    val ROCKY_SOUL_SAND = register("rocky_soul_sand", SoulSandBlock(copy(SOUL_SAND).mapColor(stone)).rocky())
    val ROCKY_SOUL_SOIL = register("rocky_soul_soil", Block(copy(SOUL_SOIL).mapColor(stone)).rocky())

    // Deepslate
    private val slate = MapColor.DEEPSLATE
    val SLATED_DIRT = register("dirty_slate", Block(copy(DIRT).mapColor(slate)).rocky())
    val SLATED_GRASS =
        register("grassy_slate", RockyGrassBlock(SLATED_DIRT, copy(GRASS_BLOCK).mapColor(slate)).rocky().grass())
    val SLATED_PODZOL = register("podzol_slate", SnowyBlock(copy(PODZOL).mapColor(slate)).rocky())
    val SLATED_MYCELIUM =
        register("mycelium_slate", RockyMyceliumBlock(SLATED_DIRT, copy(MYCELIUM).mapColor(slate)).rocky())
    val SLATED_COARSE_DIRT = register("coarsely_dirty_slate", Block(copy(COARSE_DIRT).mapColor(slate)).rocky())
    val SLATED_DIRT_PATH =
        register("slated_path", RockyDirtPathBlock(SLATED_DIRT, copy(DIRT_PATH).mapColor(slate)).rocky())
    val SLATED_MUD = register("muddy_slate", MudBlock(copy(MUD).mapColor(slate)).rocky())
    val SLATED_SNOW = register("snowy_slate", Block(copy(SNOW_BLOCK).mapColor(slate)).rocky())
    val SLATED_GRAVEL = register("slated_slate", gravel(slate).rocky())
    val SLATED_SAND = register("sandy_slate", sand(slate).rocky())
    val SLATED_RED_SAND = register("red_sandy_slate", redSand(slate).rocky())
    val SLATED_SOUL_SAND = register("slated_soul_sand", SoulSandBlock(copy(SOUL_SAND).mapColor(slate)).rocky())
    val SLATED_SOUL_SOIL = register("slated_soul_soil", Block(copy(SOUL_SOIL).mapColor(slate)).rocky())

    // Blackstone
    private val color = MapColor.BLACK
    val BLACKSTONE_DIRT = register("dirty_blackstone", Block(copy(DIRT).mapColor(color)).rocky())
    val BLACKSTONE_GRASS = register(
        "grassy_blackstone", RockyGrassBlock(BLACKSTONE_DIRT, copy(GRASS_BLOCK).mapColor(color)).rocky().grass()
    )
    val BLACKSTONE_PODZOL = register("podzol_blackstone", SnowyBlock(copy(PODZOL).mapColor(color)).rocky())
    val BLACKSTONE_MYCELIUM =
        register("mycelium_blackstone", RockyMyceliumBlock(BLACKSTONE_DIRT, copy(MYCELIUM).mapColor(color)).rocky())
    val BLACKSTONE_COARSE_DIRT = register("coarsely_dirty_blackstone", Block(copy(COARSE_DIRT).mapColor(color)).rocky())
    val BLACKSTONE_DIRT_PATH =
        register("blackstoned_path", RockyDirtPathBlock(BLACKSTONE_DIRT, copy(DIRT_PATH).mapColor(color)).rocky())
    val BLACKSTONE_MUD = register("muddy_blackstone", MudBlock(copy(MUD).mapColor(color)).rocky())
    val BLACKSTONE_SNOW = register("snowy_blackstone", Block(copy(SNOW_BLOCK).mapColor(color)).rocky())
    val BLACKSTONE_GRAVEL = register("blackstoned_blackstone", gravel(color).rocky())
    val BLACKSTONE_SAND = register("sandy_blackstone", sand(color).rocky())
    val BLACKSTONE_RED_SAND = register("red_sandy_blackstone", redSand(color).rocky())
    val BLACKSTONE_SOUL_SAND = register("blackstoned_soul_sand", SoulSandBlock(copy(SOUL_SAND).mapColor(color)).rocky())
    val BLACKSTONE_SOUL_SOIL = register("blackstoned_soul_soil", Block(copy(SOUL_SOIL).mapColor(color)).rocky())

    fun init() {
        dirtPath(ROCKY_GRASS, ROCKY_DIRT_PATH)
        dirtPath(ROCKY_PODZOL, ROCKY_DIRT_PATH)
        dirtPath(ROCKY_MYCELIUM, ROCKY_DIRT_PATH)
        dirtPath(ROCKY_DIRT, ROCKY_DIRT_PATH)
        dirtPath(ROCKY_COARSE_DIRT, ROCKY_DIRT_PATH)

        dirtPath(SLATED_GRASS, SLATED_DIRT_PATH)
        dirtPath(SLATED_PODZOL, SLATED_DIRT_PATH)
        dirtPath(SLATED_MYCELIUM, SLATED_DIRT_PATH)
        dirtPath(SLATED_DIRT, SLATED_DIRT_PATH)
        dirtPath(SLATED_COARSE_DIRT, SLATED_DIRT_PATH)

        dirtPath(BLACKSTONE_GRASS, BLACKSTONE_DIRT_PATH)
        dirtPath(BLACKSTONE_PODZOL, BLACKSTONE_DIRT_PATH)
        dirtPath(BLACKSTONE_MYCELIUM, BLACKSTONE_DIRT_PATH)
        dirtPath(BLACKSTONE_DIRT, BLACKSTONE_DIRT_PATH)
        dirtPath(BLACKSTONE_COARSE_DIRT, BLACKSTONE_DIRT_PATH)

        removeRocks(ROCKY_GRASS, GRASS_BLOCK, COBBLESTONE)
        removeRocks(ROCKY_PODZOL, PODZOL, COBBLESTONE)
        removeRocks(ROCKY_MYCELIUM, MYCELIUM, COBBLESTONE)
        removeRocks(ROCKY_DIRT_PATH, DIRT_PATH, COBBLESTONE)
        removeRocks(ROCKY_DIRT, DIRT, COBBLESTONE)
        removeRocks(ROCKY_COARSE_DIRT, COARSE_DIRT, COBBLESTONE)
        removeRocks(ROCKY_MUD, MUD, COBBLESTONE)
        removeRocks(ROCKY_SNOW, SNOW_BLOCK, COBBLESTONE)
        removeRocks(ROCKY_GRAVEL, GRAVEL, COBBLESTONE)
        removeRocks(ROCKY_SAND, SAND, COBBLESTONE)
        removeRocks(ROCKY_RED_SAND, RED_SAND, COBBLESTONE)
        removeRocks(ROCKY_SOUL_SAND, SOUL_SAND, COBBLESTONE)
        removeRocks(ROCKY_SOUL_SOIL, SOUL_SOIL, COBBLESTONE)

        removeRocks(SLATED_GRASS, GRASS_BLOCK, COBBLED_DEEPSLATE)
        removeRocks(SLATED_PODZOL, PODZOL, COBBLED_DEEPSLATE)
        removeRocks(SLATED_MYCELIUM, MYCELIUM, COBBLED_DEEPSLATE)
        removeRocks(SLATED_DIRT_PATH, DIRT_PATH, COBBLED_DEEPSLATE)
        removeRocks(SLATED_DIRT, DIRT, COBBLED_DEEPSLATE)
        removeRocks(SLATED_COARSE_DIRT, COARSE_DIRT, COBBLED_DEEPSLATE)
        removeRocks(SLATED_MUD, MUD, COBBLED_DEEPSLATE)
        removeRocks(SLATED_SNOW, SNOW_BLOCK, COBBLED_DEEPSLATE)
        removeRocks(SLATED_GRAVEL, GRAVEL, COBBLED_DEEPSLATE)
        removeRocks(SLATED_SAND, SAND, COBBLED_DEEPSLATE)
        removeRocks(SLATED_RED_SAND, RED_SAND, COBBLED_DEEPSLATE)
        removeRocks(SLATED_SOUL_SAND, SOUL_SAND, COBBLED_DEEPSLATE)
        removeRocks(SLATED_SOUL_SOIL, SOUL_SOIL, COBBLED_DEEPSLATE)

        removeRocks(BLACKSTONE_GRASS, GRASS_BLOCK, BLACKSTONE)
        removeRocks(BLACKSTONE_PODZOL, PODZOL, BLACKSTONE)
        removeRocks(BLACKSTONE_MYCELIUM, MYCELIUM, BLACKSTONE)
        removeRocks(BLACKSTONE_DIRT_PATH, DIRT_PATH, BLACKSTONE)
        removeRocks(BLACKSTONE_DIRT, DIRT, BLACKSTONE)
        removeRocks(BLACKSTONE_COARSE_DIRT, COARSE_DIRT, BLACKSTONE)
        removeRocks(BLACKSTONE_MUD, MUD, BLACKSTONE)
        removeRocks(BLACKSTONE_SNOW, SNOW_BLOCK, BLACKSTONE)
        removeRocks(BLACKSTONE_GRAVEL, GRAVEL, BLACKSTONE)
        removeRocks(BLACKSTONE_SAND, SAND, BLACKSTONE)
        removeRocks(BLACKSTONE_RED_SAND, RED_SAND, BLACKSTONE)
        removeRocks(BLACKSTONE_SOUL_SAND, SOUL_SAND, BLACKSTONE)
        removeRocks(BLACKSTONE_SOUL_SOIL, SOUL_SOIL, BLACKSTONE)
    }

    data class RockyBlock(val mainName: String, val defaultName: String, val block: Block, val color: MapColor) {
        val dirt = Block(copy(DIRT).mapColor(color))
        val grass = RockyGrassBlock(BLACKSTONE_DIRT, copy(GRASS_BLOCK).mapColor(color))
        val podzol = SnowyBlock(copy(PODZOL).mapColor(color))
        val mycelium = RockyMyceliumBlock(BLACKSTONE_DIRT, copy(MYCELIUM).mapColor(color))
        val coarseDirt = Block(copy(COARSE_DIRT).mapColor(color))
        val path = RockyDirtPathBlock(BLACKSTONE_DIRT, copy(DIRT_PATH).mapColor(color))
        val mud = MudBlock(copy(MUD).mapColor(color))
        val snow = Block(copy(SNOW_BLOCK).mapColor(color))
        val gravel = GravelBlock(Color(-8356741), copy(GRAVEL).mapColor(color))
        val sand = GravelBlock(Color(14406560), copy(SAND).mapColor(color))
        val redSand = GravelBlock(Color(11098145), copy(RED_SAND).mapColor(color))
        val soulSand = SoulSandBlock(copy(SOUL_SAND).mapColor(color))
        val soulSoil = Block(copy(SOUL_SOIL).mapColor(color))

        fun init() {
            dirtPath(dirt, path)
            dirtPath(grass, path)
            dirtPath(podzol, path)
            dirtPath(mycelium, path)
            dirtPath(coarseDirt, path)

            removeRocks(grass, GRASS_BLOCK, block)
            removeRocks(podzol, PODZOL, block)
            removeRocks(mycelium, MYCELIUM, block)
            removeRocks(path, DIRT_PATH, block)
            removeRocks(dirt, DIRT, block)
            removeRocks(coarseDirt, COARSE_DIRT, block)
            removeRocks(mud, MUD, block)
            removeRocks(snow, SNOW_BLOCK, block)
            removeRocks(gravel, GRAVEL, block)
            removeRocks(sand, SAND, block)
            removeRocks(redSand, RED_SAND, block)
            removeRocks(soulSand, SOUL_SAND, block)
            removeRocks(soulSoil, SOUL_SOIL, block)
        }
    }
}