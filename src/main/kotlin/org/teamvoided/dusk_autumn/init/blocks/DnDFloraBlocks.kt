package org.teamvoided.dusk_autumn.init.blocks

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.*
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.world.gen.feature.TreeConfiguredFeatures
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.util.*

object DnDFloraBlocks {
//    val VERDURE_DEEPSLATE = DnDBlocks.register(
//        "verdure_deepslate", Block(
//            copy(DEEPSLATE).luminance(light(1))
//        )
//    )
    val PAINTED_ROSE = DnDBlocks.register(
        "painted_rose", PaintedRoseBlock(
            Settings.create()
                .mapColor(MapColor.BLUE).noCollision().ticksRandomly().breakInstantly().offsetType(OffsetType.XZ)
                .sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)
        ).cutout()
    )
    val GOLDEN_MUSHROOM = DnDBlocks.register(
        "golden_mushroom", MushroomWithSporesPlantBlock(
            TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM,
            0xFFD800,
            0.5,
            Settings.create().mapColor(MapColor.GOLD).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.GRASS).luminance(light(11)).offsetType(OffsetType.XYZ)
                .postProcess(Blocks::solid).pistonBehavior(PistonBehavior.DESTROY)
        ).cutout()
    )
    val BROWN_TREE_FUNGUS = DnDBlocks.register(
        "brown_tree_fungus", TransparentBlock(
            copy(BROWN_MUSHROOM)
        ).cutout()
    )
    val WARPED_WART = DnDBlocks.register(
        "warped_wart", WarpedNetherWartBlock(
            Settings.create().mapColor(MapColor.WARPED_STEM).noCollision().ticksRandomly()
                .sounds(BlockSoundGroup.NETHER_WART).pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().sword().axe()
    )
    val SPIDERLILY = DnDBlocks.register(
        "spiderlily", SpiderlilyBlock(
            copy(ROSE_BUSH).ticksRandomly()
        ).cutout().sword().hoe()
    )
    val WHITE_PETALS = DnDBlocks.register(
        "white_petals", PinkPetalsBlock(
            copy(PINK_PETALS).mapColor(MapColor.SNOW)
        ).cutout().sword().hoe()
    )
    val RED_PETALS = DnDBlocks.register(
        "red_petals", PinkPetalsBlock(
            copy(WHITE_PETALS).mapColor(MapColor.RED)
        ).cutout().sword().hoe()
    )
    val ORANGE_PETALS = DnDBlocks.register(
        "orange_petals", PinkPetalsBlock(
            copy(WHITE_PETALS).mapColor(MapColor.ORANGE)
        ).cutout().sword().hoe()
    )
    val BLUE_PETALS = DnDBlocks.register(
        "blue_petals", PinkPetalsBlock(
            copy(WHITE_PETALS).mapColor(MapColor.BLUE)
        ).cutout().sword().hoe()
    )
    val CRIMSON_VIVIONS = DnDBlocks.register(
        "crimson_vivions", VivionbedBlock(
            copy(PINK_PETALS).mapColor(MapColor.RED).sounds(BlockSoundGroup.NETHER_SPROUTS)
        ).cutout().sword().hoe()
    )
    val WARPED_VIVIONS = DnDBlocks.register(
        "warped_vivions", VivionbedBlock(
            copy(CRIMSON_VIVIONS).mapColor(MapColor.WARPED_WART_BLOCK)
        ).cutout().sword().hoe()
    )
    val WILD_PETALS = DnDBlocks.register(
        "wild_petals", PinkPetalsBlock(
            copy(PINK_PETALS).mapColor(MapColor.PURPLE)
        ).cutout().sword().hoe()
    )
    val WATER_FERN = DnDBlocks.registerNoItem(
        "water_fern", WaterFernBlock(
            copy(LILY_PAD)
        ).cutout()
    )
    val ROOT_BLOCK = DnDBlocks.register(
        "root_block", MangroveRootsBlock(
            Settings.create().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS)
                .strength(0.7f).nonOpaque().suffocates(Blocks::nonSolid).blockVision(Blocks::nonSolid).nonOpaque()
                .lavaIgnitable().sounds(rootBlockSound)
        ).cutout().flammableLeaves().sword().axe()
    )

    val WILD_WHEAT = DnDBlocks.registerNoItem(
        "wild_wheat", TallPlantBlock(
            Settings.create().mapColor(MapColor.PLANT).noCollision().breakInstantly()
                .sounds(BlockSoundGroup.CROP).offsetType(OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().axe()
    )
    val GOLDEN_BEETROOTS = DnDBlocks.registerNoItem(
        "golden_beetroots", GoldenBeetrootsBlock(
            Settings.create().mapColor(MapColor.GOLD).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().axe()
    )
    val MOONBERRY_VINE = DnDBlocks.register(
        "moonberry_vine", MoonberryVineBlock(
            Settings.create().mapColor(MapColor.PURPLE).noCollision().strength(0.2f)
                .sounds(BlockSoundGroup.CAVE_VINES).luminance(MoonberryVineBlock.getLuminanceSupplier(8, 11))
                .lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().flammableLogs().axe().sword()
    )
    val MOONBERRY_VINELET = DnDBlocks.registerNoItem(
        "moonberry_vinelet", MoonberryVineletBlock(
            Settings.create().mapColor(MapColor.PURPLE).noCollision().strength(0.2f).ticksRandomly()
                .breakInstantly().sounds(BlockSoundGroup.CAVE_VINES).lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().flammableLogs().axe().sword()
    )
}