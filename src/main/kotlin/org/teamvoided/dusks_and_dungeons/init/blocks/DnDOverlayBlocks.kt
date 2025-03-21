package org.teamvoided.dusks_and_dungeons.init.blocks

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.util.Color
import org.teamvoided.dusks_and_dungeons.block.rocky.RockyDirtPathBlock
import org.teamvoided.dusks_and_dungeons.block.rocky.RockyGrassBlock
import org.teamvoided.dusks_and_dungeons.block.rocky.RockyMyceliumBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.register
import org.teamvoided.dusks_and_dungeons.util.block.dirtPath
import org.teamvoided.dusks_and_dungeons.util.block.grass
import org.teamvoided.dusks_and_dungeons.util.block.removeRocks
import org.teamvoided.dusks_and_dungeons.util.block.rocky
import org.teamvoided.voidlib.consortium.block.BlockCollection

object DnDOverlayBlocks {
    val OVERLAYS = mutableSetOf<RockyBlocks>()

    val ROCKY_BLOCKS = register(RockyBlocks("rocks", "rocky", STONE))
    val SLATE_BLOCKS = register(RockyBlocks("slate", "slated", STONE))
    val BLACKSTONE_BLOCKS = register(RockyBlocks("blackstone", "blackstoned", STONE))

    fun init() {
        OVERLAYS.forEach {
            it.rocky()
            it.grass.grass()
            println(it.gravel.name.string)
        }
    }

    fun register(overlays: RockyBlocks): RockyBlocks {
        OVERLAYS.add(overlays)
        overlays.register(::register)
        return overlays
    }

    data class RockyBlocks(val mainName: String, val defaultName: String, val block: Block, val color: MapColor) :
        BlockCollection<Block> {
        constructor(mainName: String, defaultName: String, block: Block)
                : this(mainName, defaultName, block, block.defaultMapColor)
        val dirt = Block(copy(DIRT).mapColor(color))
        val grass = RockyGrassBlock(dirt, copy(GRASS_BLOCK).mapColor(color))
        val podzol = SnowyBlock(copy(PODZOL).mapColor(color))
        val mycelium = RockyMyceliumBlock(dirt, copy(MYCELIUM).mapColor(color))
        val coarseDirt = Block(copy(COARSE_DIRT).mapColor(color))
        val path = RockyDirtPathBlock(dirt, copy(DIRT_PATH).mapColor(color))
        val mud = MudBlock(copy(MUD).mapColor(color))
        val snow = Block(copy(SNOW_BLOCK).mapColor(color))
        val gravel = GravelBlock(Color(-8356741), copy(GRAVEL).mapColor(color))
        val sand = GravelBlock(Color(14406560), copy(SAND).mapColor(color))
        val redSand = GravelBlock(Color(11098145), copy(RED_SAND).mapColor(color))
        val soulSand = SoulSandBlock(copy(SOUL_SAND).mapColor(color))
        val soulSoil = Block(copy(SOUL_SOIL).mapColor(color))

        override val size: Int = 13
        override val list: List<Block> = listOf(
            dirt, grass, podzol, mycelium, coarseDirt, path, mud, snow, gravel, sand, redSand, soulSand, soulSoil
        )

        override fun getIdMap(): Map<String, Block> = mapOf(
            "dirty_$mainName" to dirt,
            "grassy_$mainName" to grass,
            "podzol_$mainName" to podzol,
            "mycelium_$mainName" to mycelium,
            "coarsely_dirty_$mainName" to coarseDirt,
            "${defaultName}_path" to path,
            "muddy_$mainName" to mud,
            "snowy_$mainName" to snow,
            "${defaultName}_gravel" to gravel,
            "sandy_$mainName" to sand,
            "red_sandy_$mainName" to redSand,
            "${defaultName}_soul_sand" to soulSand,
            "${defaultName}_soul_soil" to soulSoil
        )

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