package org.teamvoided.dusks_and_dungeons.block.collections

import net.minecraft.block.*
import net.minecraft.util.Color
import org.teamvoided.dusks_and_dungeons.block.rocky.RockyDirtPathBlock
import org.teamvoided.dusks_and_dungeons.block.rocky.RockyGrassBlock
import org.teamvoided.dusks_and_dungeons.block.rocky.RockyMyceliumBlock
import org.teamvoided.dusks_and_dungeons.util.block.dirtPath
import org.teamvoided.dusks_and_dungeons.util.block.removeRocks
import org.teamvoided.voidlib.consortium.block.BlockCollection

data class RockyBlocks(val name: String, val variation: String, val block: Block, val color: MapColor) :
    BlockCollection<Block> {
    constructor(mainName: String, defaultName: String, block: Block)
            : this(mainName, defaultName, block, block.defaultMapColor)
    val dirt = Block(AbstractBlock.Settings.copy(Blocks.DIRT).mapColor(color))
    val grass = RockyGrassBlock(dirt, AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK).mapColor(color))
    val podzol = SnowyBlock(AbstractBlock.Settings.copy(Blocks.PODZOL).mapColor(color))
    val mycelium = RockyMyceliumBlock(dirt, AbstractBlock.Settings.copy(Blocks.MYCELIUM).mapColor(color))
    val coarseDirt = Block(AbstractBlock.Settings.copy(Blocks.COARSE_DIRT).mapColor(color))
    val path = RockyDirtPathBlock(dirt, AbstractBlock.Settings.copy(Blocks.DIRT_PATH).mapColor(color))
    val mud = MudBlock(AbstractBlock.Settings.copy(Blocks.MUD).mapColor(color))
    val snow = Block(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK).mapColor(color))
    val gravel = GravelBlock(Color(-8356741), AbstractBlock.Settings.copy(Blocks.GRAVEL).mapColor(color))
    val sand = GravelBlock(Color(14406560), AbstractBlock.Settings.copy(Blocks.SAND).mapColor(color))
    val redSand = GravelBlock(Color(11098145), AbstractBlock.Settings.copy(Blocks.RED_SAND).mapColor(color))
    val soulSand = SoulSandBlock(AbstractBlock.Settings.copy(Blocks.SOUL_SAND).mapColor(color))
    val soulSoil = Block(AbstractBlock.Settings.copy(Blocks.SOUL_SOIL).mapColor(color))

    override val size: Int = 13
    override val list: List<Block> = listOf(
        dirt, grass, podzol, mycelium, coarseDirt, path, mud, snow, gravel, sand, redSand, soulSand, soulSoil
    )

    override fun getIdMap(): Map<String, Block> = mapOf(
        "dirty_$name" to dirt,
        "grassy_$name" to grass,
        "podzol_$name" to podzol,
        "mycelium_$name" to mycelium,
        "coarsely_dirty_$name" to coarseDirt,
        "${variation}_path" to path,
        "muddy_$name" to mud,
        "snowy_$name" to snow,
        "${variation}_gravel" to gravel,
        "sandy_$name" to sand,
        "red_sandy_$name" to redSand,
        "${variation}_soul_sand" to soulSand,
        "${variation}_soul_soil" to soulSoil
    )

    fun init() {
        dirtPath(dirt, path)
        dirtPath(grass, path)
        dirtPath(podzol, path)
        dirtPath(mycelium, path)
        dirtPath(coarseDirt, path)

        removeRocks(grass, Blocks.GRASS_BLOCK, block)
        removeRocks(podzol, Blocks.PODZOL, block)
        removeRocks(mycelium, Blocks.MYCELIUM, block)
        removeRocks(path, Blocks.DIRT_PATH, block)
        removeRocks(dirt, Blocks.DIRT, block)
        removeRocks(coarseDirt, Blocks.COARSE_DIRT, block)
        removeRocks(mud, Blocks.MUD, block)
        removeRocks(snow, Blocks.SNOW_BLOCK, block)
        removeRocks(gravel, Blocks.GRAVEL, block)
        removeRocks(sand, Blocks.SAND, block)
        removeRocks(redSand, Blocks.RED_SAND, block)
        removeRocks(soulSand, Blocks.SOUL_SAND, block)
        removeRocks(soulSoil, Blocks.SOUL_SOIL, block)
    }
}