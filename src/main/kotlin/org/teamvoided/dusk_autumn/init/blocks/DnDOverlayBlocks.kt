package org.teamvoided.dusk_autumn.init.blocks

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.util.Color
import org.teamvoided.dusk_autumn.block.rocky.RockyDirtPathBlock
import org.teamvoided.dusk_autumn.block.rocky.RockyGrassBlock
import org.teamvoided.dusk_autumn.block.rocky.RockyMyceliumBlock
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.util.*

object DnDOverlayBlocks {
    val ROCKY_DIRT =
        DnDBlocks.register(
            "dirty_rocks",
            Block(copy(DIRT).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val ROCKY_GRASS = DnDBlocks.register(
        "grassy_rocks",
        RockyGrassBlock(ROCKY_DIRT, copy(GRASS_BLOCK).mapColor(COBBLESTONE.defaultMapColor))
            .cutout().grass().pickaxe().shovel()
    )
    val ROCKY_PODZOL =
        DnDBlocks.register(
            "podzol_rocks",
            SnowyBlock(copy(PODZOL).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val ROCKY_MYCELIUM = DnDBlocks.register(
        "mycelium_rocks",
        RockyMyceliumBlock(ROCKY_DIRT, copy(MYCELIUM).mapColor(COBBLESTONE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val ROCKY_COARSE_DIRT = DnDBlocks.register(
        "coarsely_dirty_rocks",
        Block(copy(COARSE_DIRT).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
    )
    val ROCKY_DIRT_PATH = DnDBlocks.register(
        "rocky_path",
        RockyDirtPathBlock(ROCKY_DIRT, copy(DIRT_PATH).mapColor(COBBLESTONE.defaultMapColor)).cutout()
    )
    val ROCKY_MUD =
        DnDBlocks.register("muddy_rocks", MudBlock(copy(MUD).mapColor(COBBLESTONE.defaultMapColor)).cutout())
    val ROCKY_SNOW =
        DnDBlocks.register("snowy_rocks", Block(copy(SNOW_BLOCK).mapColor(COBBLESTONE.defaultMapColor)).cutout())
    val ROCKY_GRAVEL = DnDBlocks.register(
        "rocky_rocks",
        GravelBlock(Color(-8356741), copy(GRAVEL).mapColor(COBBLESTONE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val ROCKY_SAND = DnDBlocks.register(
        "sandy_rocks",
        GravelBlock(Color(14406560), copy(SAND).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
    )
    val ROCKY_RED_SAND = DnDBlocks.register(
        "red_sandy_rocks", GravelBlock(Color(11098145), copy(RED_SAND).mapColor(COBBLESTONE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val ROCKY_SOUL_SAND =
        DnDBlocks.register(
            "rocky_soul_sand",
            SoulSandBlock(copy(SOUL_SAND).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val ROCKY_SOUL_SOIL = DnDBlocks.register(
        "rocky_soul_soil",
        Block(copy(SOUL_SOIL).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
    )

    val SLATED_DIRT =
        DnDBlocks.register(
            "dirty_slate",
            Block(copy(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_GRASS = DnDBlocks.register(
        "grassy_slate",
        RockyGrassBlock(SLATED_DIRT, copy(GRASS_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().grass().pickaxe().shovel()
    )
    val SLATED_PODZOL =
        DnDBlocks.register(
            "podzol_slate",
            SnowyBlock(copy(PODZOL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_MYCELIUM = DnDBlocks.register(
        "mycelium_slate",
        RockyMyceliumBlock(SLATED_DIRT, copy(MYCELIUM).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_COARSE_DIRT =
        DnDBlocks.register(
            "coarsely_dirty_slate",
            Block(copy(COARSE_DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_DIRT_PATH = DnDBlocks.register(
        "slated_path",
        RockyDirtPathBlock(SLATED_DIRT, copy(DIRT_PATH).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_MUD =
        DnDBlocks.register(
            "muddy_slate",
            MudBlock(copy(MUD).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_SNOW = DnDBlocks.register(
        "snowy_slate",
        Block(copy(SNOW_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
    )
    val SLATED_GRAVEL = DnDBlocks.register(
        "slated_slate",
        GravelBlock(Color(-8356741), copy(GRAVEL).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_SAND = DnDBlocks.register(
        "sandy_slate",
        GravelBlock(Color(14406560), copy(SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_RED_SAND = DnDBlocks.register(
        "red_sandy_slate",
        GravelBlock(Color(11098145), copy(RED_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_SOUL_SAND =
        DnDBlocks.register(
            "slated_soul_sand",
            SoulSandBlock(copy(SOUL_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_SOUL_SOIL =
        DnDBlocks.register(
            "slated_soul_soil",
            Block(copy(SOUL_SOIL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )

    val BLACKSTONE_DIRT =
        DnDBlocks.register(
            "dirty_blackstone",
            Block(copy(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_GRASS = DnDBlocks.register(
        "grassy_blackstone",
        RockyGrassBlock(BLACKSTONE_DIRT, copy(GRASS_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().grass().pickaxe().shovel()
    )
    val BLACKSTONE_PODZOL =
        DnDBlocks.register(
            "podzol_blackstone",
            SnowyBlock(copy(PODZOL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_MYCELIUM = DnDBlocks.register(
        "mycelium_blackstone",
        RockyMyceliumBlock(BLACKSTONE_DIRT, copy(MYCELIUM).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_COARSE_DIRT =
        DnDBlocks.register(
            "coarsely_dirty_blackstone",
            Block(copy(COARSE_DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_DIRT_PATH = DnDBlocks.register(
        "blackstoned_path",
        RockyDirtPathBlock(BLACKSTONE_DIRT, copy(DIRT_PATH).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_MUD =
        DnDBlocks.register(
            "muddy_blackstone",
            MudBlock(copy(MUD).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_SNOW =
        DnDBlocks.register(
            "snowy_blackstone",
            Block(copy(SNOW_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_GRAVEL = DnDBlocks.register(
        "blackstoned_blackstone",
        GravelBlock(Color(-8356741), copy(GRAVEL).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_SAND = DnDBlocks.register(
        "sandy_blackstone",
        GravelBlock(Color(14406560), copy(SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_RED_SAND = DnDBlocks.register(
        "red_sandy_blackstone",
        GravelBlock(Color(11098145), copy(RED_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_SOUL_SAND = DnDBlocks.register(
        "blackstoned_soul_sand",
        SoulSandBlock(copy(SOUL_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
    )
    val BLACKSTONE_SOUL_SOIL =
        DnDBlocks.register(
            "blackstoned_soul_soil",
            Block(copy(SOUL_SOIL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )

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
}