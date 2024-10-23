package org.teamvoided.dusk_autumn.init.blocks

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.OffsetType
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.Items
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.world.gen.feature.TreeConfiguredFeatures
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.util.*

object DnDFloraBlocks {
    fun init() = Unit

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
    ).tellWitnessesThatIWasMurdered()
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
    ).tellWitnessesThatIWasMurdered()
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
    ).tellWitnessesThatIWasMurdered()
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

    val SMALL_CARVED_PUMPKIN = DnDBlocks.registerHeadEquipable(
        "small_carved_pumpkin", SmallCarvedPumpkinBlock(
            Settings.create().mapColor(MapColor.ORANGE).strength(1.0f).sounds(BlockSoundGroup.WOOD)
                .pistonBehavior(PistonBehavior.DESTROY)
        ).axe()
    ).shh()
    val SMALL_GLOWING_PUMPKIN = DnDBlocks.register(
        "small_jack_o_lantern", SmallCarvedPumpkinBlock(
            copy(SMALL_CARVED_PUMPKIN).luminance(light(15))
        ).axe()
    ).shh()
    val SMALL_PUMPKIN = DnDBlocks.register(
        "small_pumpkin", SmallPumpkinBlock(
            SMALL_CARVED_PUMPKIN,
            Items.PUMPKIN_SEEDS,
            copy(SMALL_CARVED_PUMPKIN)
        ).axe()
    ).shh()
    val CARVED_LANTERN_PUMPKIN = DnDBlocks.registerHeadEquipable(
        "carved_lantern_pumpkin", CarvedPumpkin2Block(
            copy(CARVED_PUMPKIN).mapColor(MapColor.YELLOW)
        ).axe()
    ).shh()
    val GLOWING_LANTERN_PUMPKIN = DnDBlocks.register(
        "glowing_lantern_pumpkin", CarvedPumpkin2Block(
            copy(CARVED_LANTERN_PUMPKIN).luminance(light(15))
        ).axe()
    ).shh()
    val LANTERN_PUMPKIN = DnDBlocks.register(
        "lantern_pumpkin", DnDPumpkinBlock(
            CARVED_LANTERN_PUMPKIN,
            Items.PUMPKIN_SEEDS,
            copy(CARVED_LANTERN_PUMPKIN)
        ).axe()
    ).shh()
    val SMALL_CARVED_LANTERN_PUMPKIN = DnDBlocks.registerHeadEquipable(
        "small_carved_lantern_pumpkin", SmallCarvedPumpkinBlock(
            copy(SMALL_CARVED_PUMPKIN).mapColor(CARVED_LANTERN_PUMPKIN.defaultMapColor)
        ).axe()
    ).shh()
    val SMALL_GLOWING_LANTERN_PUMPKIN = DnDBlocks.register(
        "small_glowing_lantern_pumpkin", SmallCarvedPumpkinBlock(
            copy(SMALL_CARVED_LANTERN_PUMPKIN).luminance(light(15))
        ).axe()
    ).shh()
    val SMALL_LANTERN_PUMPKIN = DnDBlocks.register(
        "small_lantern_pumpkin", SmallPumpkinBlock(
            SMALL_CARVED_LANTERN_PUMPKIN,
            Items.PUMPKIN_SEEDS,
            copy(SMALL_CARVED_LANTERN_PUMPKIN)
        ).axe()
    ).shh()
    val CARVED_MOSSKIN_PUMPKIN = DnDBlocks.registerHeadEquipable(
        "carved_mosskin_pumpkin", CarvedPumpkin2Block(
            copy(CARVED_PUMPKIN).mapColor(MapColor.GREEN)
        ).axe()
    ).shh()
    val GLOWING_MOSSKIN_PUMPKIN = DnDBlocks.register(
        "glowing_mosskin_pumpkin", CarvedPumpkin2Block(
            copy(CARVED_MOSSKIN_PUMPKIN).luminance(light(15))
        ).axe()
    ).shh()
    val MOSSKIN_PUMPKIN = DnDBlocks.register(
        "mosskin_pumpkin", DnDPumpkinBlock(
            CARVED_MOSSKIN_PUMPKIN,
            Items.PUMPKIN_SEEDS,
            copy(CARVED_MOSSKIN_PUMPKIN)
        ).axe()
    ).shh()
    val SMALL_CARVED_MOSSKIN_PUMPKIN = DnDBlocks.registerHeadEquipable(
        "small_carved_mosskin_pumpkin", SmallCarvedPumpkinBlock(
            copy(SMALL_CARVED_PUMPKIN).mapColor(CARVED_MOSSKIN_PUMPKIN.defaultMapColor)
        ).axe()
    ).shh()
    val SMALL_GLOWING_MOSSKIN_PUMPKIN = DnDBlocks.register(
        "small_glowing_mosskin_pumpkin", SmallCarvedPumpkinBlock(
            copy(SMALL_CARVED_MOSSKIN_PUMPKIN).luminance(light(15))
        ).axe()
    ).shh()
    val SMALL_MOSSKIN_PUMPKIN = DnDBlocks.register(
        "small_mosskin_pumpkin", SmallPumpkinBlock(
            SMALL_CARVED_MOSSKIN_PUMPKIN,
            Items.PUMPKIN_SEEDS,
            copy(SMALL_CARVED_MOSSKIN_PUMPKIN)
        ).axe()
    ).shh()
    val CARVED_PALE_PUMPKIN = DnDBlocks.registerHeadEquipable(
        "carved_pale_pumpkin", CarvedPumpkin2Block(
            copy(CARVED_PUMPKIN).mapColor(MapColor.SNOW)
        ).axe()
    ).shh()
    val GLOWING_PALE_PUMPKIN = DnDBlocks.register(
        "glowing_pale_pumpkin", CarvedPumpkin2Block(
            copy(CARVED_PALE_PUMPKIN).luminance(light(15))
        ).axe()
    ).shh()
    val PALE_PUMPKIN = DnDBlocks.register(
        "pale_pumpkin", DnDPumpkinBlock(
            CARVED_PALE_PUMPKIN,
            Items.PUMPKIN_SEEDS,
            copy(CARVED_PALE_PUMPKIN)
        ).axe()
    ).shh()
    val SMALL_CARVED_PALE_PUMPKIN = DnDBlocks.registerHeadEquipable(
        "small_carved_pale_pumpkin", SmallCarvedPumpkinBlock(
            copy(SMALL_CARVED_PUMPKIN).mapColor(CARVED_PALE_PUMPKIN.defaultMapColor)
        ).axe()
    ).shh()
    val SMALL_GLOWING_PALE_PUMPKIN = DnDBlocks.register(
        "small_glowing_pale_pumpkin", SmallCarvedPumpkinBlock(
            copy(SMALL_CARVED_PALE_PUMPKIN).luminance(light(15))
        ).axe()
    ).shh()
    val SMALL_PALE_PUMPKIN = DnDBlocks.register(
        "small_pale_pumpkin", SmallPumpkinBlock(
            SMALL_CARVED_PALE_PUMPKIN,
            Items.PUMPKIN_SEEDS,
            copy(SMALL_CARVED_PALE_PUMPKIN)
        ).axe()
    ).shh()
    val CARVED_GLOOM_PUMPKIN = DnDBlocks.registerHeadEquipable(
        "carved_gloom_pumpkin", CarvedPumpkin2Block(
            copy(CARVED_PUMPKIN).mapColor(MapColor.PURPLE_TERRACOTTA)
        ).axe()
    ).shh()
    val GLOWING_GLOOM_PUMPKIN = DnDBlocks.register(
        "glowing_gloom_pumpkin", CarvedPumpkin2Block(
            copy(CARVED_GLOOM_PUMPKIN).luminance(light(15))
        ).axe()
    ).shh()
    val GLOOM_PUMPKIN = DnDBlocks.register(
        "gloom_pumpkin", DnDPumpkinBlock(
            CARVED_GLOOM_PUMPKIN,
            Items.PUMPKIN_SEEDS,
            copy(CARVED_GLOOM_PUMPKIN)
        ).axe()
    ).shh()
    val SMALL_CARVED_GLOOM_PUMPKIN = DnDBlocks.registerHeadEquipable(
        "small_carved_gloom_pumpkin", SmallCarvedPumpkinBlock(
            copy(SMALL_CARVED_PUMPKIN).mapColor(CARVED_GLOOM_PUMPKIN.defaultMapColor)
        ).axe()
    ).shh()
    val SMALL_GLOWING_GLOOM_PUMPKIN = DnDBlocks.register(
        "small_glowing_gloom_pumpkin", SmallCarvedPumpkinBlock(
            copy(SMALL_CARVED_GLOOM_PUMPKIN).luminance(light(15))
        ).axe()
    ).shh()
    val SMALL_GLOOM_PUMPKIN = DnDBlocks.register(
        "small_gloom_pumpkin", SmallPumpkinBlock(
            SMALL_CARVED_GLOOM_PUMPKIN,
            Items.PUMPKIN_SEEDS,
            copy(SMALL_CARVED_GLOOM_PUMPKIN)
        ).axe()
    ).shh()


    val LANTERN_PUMPKIN_STEM = DnDBlocks.registerNoItem(
        "lantern_pumpkin_stem",
        DnDPumpkinStemBlock(LANTERN_PUMPKIN, SMALL_LANTERN_PUMPKIN, copy(PUMPKIN_STEM)).cutout().axe().sword()
    )
    val MOSSKIN_PUMPKIN_STEM = DnDBlocks.registerNoItem(
        "mosskin_pumpkin_stem",
        DnDPumpkinStemBlock(MOSSKIN_PUMPKIN, SMALL_MOSSKIN_PUMPKIN, copy(PUMPKIN_STEM)).cutout().axe().sword()
    )
    val PALE_PUMPKIN_STEM = DnDBlocks.registerNoItem(
        "pale_pumpkin_stem",
        DnDPumpkinStemBlock(PALE_PUMPKIN, SMALL_PALE_PUMPKIN, copy(PUMPKIN_STEM)).cutout().axe().sword()
    )
    val GLOOM_PUMPKIN_STEM = DnDBlocks.registerNoItem(
        "gloom_pumpkin_stem",
        DnDPumpkinStemBlock(GLOOM_PUMPKIN, SMALL_GLOOM_PUMPKIN, copy(PUMPKIN_STEM)).cutout().axe().sword()
    )

    val CORN_CROP = DnDBlocks.registerNoItem(
        "corn_crop", CornCropBlock(
            Settings.create().mapColor(MapColor.PLANT).ticksRandomly().noCollision().breakInstantly()
                .sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
        ).sword().axe().cutout()
    ).shh()
    val CORN = DnDBlocks.registerNoItem(
        "corn", CornMazeBlock(
            Settings.create().mapColor(MapColor.PLANT).offsetType(OffsetType.XYZ).noCollision().breakInstantly()
                .sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
        ).sword().axe().cutout()
    ).shh()
    val CORN_BLOCK = DnDBlocks.register(
        "corn_block", PillarBlock(
            copy(CHERRY_PLANKS).mapColor(GOLD_BLOCK.defaultMapColor)
        ).axe()
    ).shh()

    @JvmField
    val CORN_SYRUP_BLOCK = DnDBlocks.register(
        "corn_syrup_block", CornSyrupBlock(
            copy(HONEY_BLOCK).mapColor(PODZOL.defaultMapColor).sounds(cornSyrupBlockSound)
        ).translucent()
    ).shh()

    val JOUNCESHROOM_BLOCK = DnDBlocks.register(
        "jounceshroom_block", MushroomLaunchBlock(
            copy(BROWN_MUSHROOM_BLOCK).sounds(BlockSoundGroup.SHROOMLIGHT).mapColor(MapColor.PURPLE_TERRACOTTA)
        )
    ).tellWitnessesThatIWasMurdered()
    val WATER_FERN = DnDBlocks.registerNoItem(
        "water_fern", WaterFernBlock(
            copy(LILY_PAD)
        ).cutout()
    ).tellWitnessesThatIWasMurdered()
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
