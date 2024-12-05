package org.teamvoided.dusks_and_dungeons.init.blocks

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.OffsetType
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.world.gen.feature.TreeConfiguredFeatures
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.block.MoonberryVineBlock.Companion.moonberryLuminance
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.register
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.registerNoItem
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockSettings as Set

object DnDFloraBlocks {

    val WARPED_WART = register("warped_wart", WarpedNetherWartBlock(Set.WARPED_WART).grassLike())

    // Petals
    val WHITE_PETALS = register("white_petals", PinkPetalsBlock(Set.petals(MapColor.SNOW)).plant())
    val RED_PETALS = register("red_petals", PinkPetalsBlock(Set.petals(MapColor.RED)).plant())
    val ORANGE_PETALS = register("orange_petals", PinkPetalsBlock(Set.petals(MapColor.ORANGE)).plant())
    val BLUE_PETALS = register("blue_petals", PinkPetalsBlock(Set.petals(MapColor.BLUE)).plant())
    val WILD_PETALS = register("wild_petals", PinkPetalsBlock(Set.petals(MapColor.PURPLE)).plant())

    val CRIMSON_VIVIONS = register("crimson_vivions", VivionbedBlock(Set.vivions(MapColor.RED)).plant())
    val WARPED_VIVIONS = register("warped_vivions", VivionbedBlock(Set.vivions(MapColor.WARPED_WART_BLOCK)).plant())

    // Smol Punkin
    val SMALL_CARVED_PUMPKIN = registerHeadEquipable("small_carved_pumpkin", sCarvedPumpkinOf(CARVED_PUMPKIN).axe())
    val SMALL_GLOWING_PUMPKIN = register("small_jack_o_lantern", sGlowingPumpkinOf(SMALL_CARVED_PUMPKIN).axe())
    val SMALL_PUMPKIN = register("small_pumpkin", sPumpkinOf(SMALL_CARVED_PUMPKIN).axe())

    // Lantern ---
    val CARVED_LANTERN_PUMPKIN = registerHeadEquipable("carved_lantern_pumpkin", carvedPumpkin(MapColor.YELLOW).axe())
    val GLOWING_LANTERN_PUMPKIN = register("glowing_lantern_pumpkin", glowingPumpkinOf(CARVED_LANTERN_PUMPKIN).axe())
    val LANTERN_PUMPKIN = register("lantern_pumpkin", pumpkinOf(CARVED_LANTERN_PUMPKIN).axe())
    val SMALL_CARVED_LANTERN_PUMPKIN =
        registerHeadEquipable("small_carved_lantern_pumpkin", sCarvedPumpkinOf(CARVED_LANTERN_PUMPKIN).axe())
    val SMALL_GLOWING_LANTERN_PUMPKIN =
        register("small_glowing_lantern_pumpkin", sGlowingPumpkinOf(SMALL_CARVED_LANTERN_PUMPKIN).axe())
    val SMALL_LANTERN_PUMPKIN = register("small_lantern_pumpkin", sPumpkinOf(SMALL_CARVED_LANTERN_PUMPKIN).axe())
    val LANTERN_PUMPKIN_STEM = registerNoItem("lantern_pumpkin_stem", stemOf(LANTERN_PUMPKIN).grassLike())

    // Mosskin ---
    val CARVED_MOSSKIN_PUMPKIN = registerHeadEquipable("carved_mosskin_pumpkin", carvedPumpkin(MapColor.GREEN).axe())
    val GLOWING_MOSSKIN_PUMPKIN = register("glowing_mosskin_pumpkin", glowingPumpkinOf(CARVED_MOSSKIN_PUMPKIN).axe())
    val MOSSKIN_PUMPKIN = register("mosskin_pumpkin", pumpkinOf(CARVED_MOSSKIN_PUMPKIN).axe())
    val SMALL_CARVED_MOSSKIN_PUMPKIN =
        registerHeadEquipable("small_carved_mosskin_pumpkin", sCarvedPumpkinOf(CARVED_MOSSKIN_PUMPKIN).axe())
    val SMALL_GLOWING_MOSSKIN_PUMPKIN =
        register("small_glowing_mosskin_pumpkin", sGlowingPumpkinOf(SMALL_CARVED_MOSSKIN_PUMPKIN).axe())
    val SMALL_MOSSKIN_PUMPKIN = register("small_mosskin_pumpkin", sPumpkinOf(SMALL_CARVED_MOSSKIN_PUMPKIN).axe())
    val MOSSKIN_PUMPKIN_STEM = registerNoItem("mosskin_pumpkin_stem", stemOf(MOSSKIN_PUMPKIN).grassLike())

    // Gloom ---
    val CARVED_GLOOM_PUMPKIN =
        registerHeadEquipable("carved_gloom_pumpkin", carvedPumpkin(MapColor.PURPLE_TERRACOTTA).axe())
    val GLOWING_GLOOM_PUMPKIN = register("glowing_gloom_pumpkin", glowingPumpkinOf(CARVED_GLOOM_PUMPKIN).axe())
    val GLOOM_PUMPKIN = register("gloom_pumpkin", pumpkinOf(CARVED_GLOOM_PUMPKIN).axe())
    val SMALL_CARVED_GLOOM_PUMPKIN =
        registerHeadEquipable("small_carved_gloom_pumpkin", sCarvedPumpkinOf(CARVED_GLOOM_PUMPKIN).axe())
    val SMALL_GLOWING_GLOOM_PUMPKIN =
        register("small_glowing_gloom_pumpkin", sGlowingPumpkinOf(SMALL_CARVED_GLOOM_PUMPKIN).axe())
    val SMALL_GLOOM_PUMPKIN = register("small_gloom_pumpkin", sPumpkinOf(SMALL_CARVED_GLOOM_PUMPKIN).axe())
    val GLOOM_PUMPKIN_STEM = registerNoItem("gloom_pumpkin_stem", stemOf(GLOOM_PUMPKIN).grassLike())

    // Pale ---
    val CARVED_PALE_PUMPKIN = registerHeadEquipable("carved_pale_pumpkin", carvedPumpkin(MapColor.SNOW).axe())
    val GLOWING_PALE_PUMPKIN = register("glowing_pale_pumpkin", glowingPumpkinOf(CARVED_PALE_PUMPKIN).axe())
    val PALE_PUMPKIN = register("pale_pumpkin", pumpkinOf(CARVED_PALE_PUMPKIN).axe())
    val SMALL_CARVED_PALE_PUMPKIN =
        registerHeadEquipable("small_carved_pale_pumpkin", sCarvedPumpkinOf(CARVED_PALE_PUMPKIN).axe())
    val SMALL_GLOWING_PALE_PUMPKIN =
        register("small_glowing_pale_pumpkin", sGlowingPumpkinOf(SMALL_CARVED_PALE_PUMPKIN).axe())
    val SMALL_PALE_PUMPKIN = register("small_pale_pumpkin", sPumpkinOf(SMALL_CARVED_PALE_PUMPKIN).axe())
    val PALE_PUMPKIN_STEM = registerNoItem("pale_pumpkin_stem", stemOf(PALE_PUMPKIN).grassLike())

    // Corn
    val CORN_CROP = registerNoItem("corn_crop", CornCropBlock(Set.corn().ticksRandomly()).grassLike())
    val CORN = registerNoItem("corn", CornMazeBlock(Set.corn().offsetType(OffsetType.XYZ)).grassLike())
    val CORN_BLOCK = register("corn_block", PillarBlock(copy(CHERRY_PLANKS).mapColor(MapColor.GOLD)).axe())

    @JvmField
    val CORN_SYRUP_BLOCK = register("corn_syrup_block", CornSyrupBlock(Set.CORN_SYRUP)).translucent()

    // The Rest
    val ROOT_BLOCK = register("root_block", MangroveRootsBlock(Set.ROOT_BLOCK).grassLike().flammableLeaves())
    val WILD_WHEAT = registerNoItem("wild_wheat", TallPlantBlock(Set.WILD_WHEAT).grassLike())
    val GOLDEN_BEETROOTS = registerNoItem("golden_beetroots", GoldenBeetrootsBlock(Set.GOLDEN_BEETROOT).grassLike())
    val MOONBERRY_VINE = register(
        "moonberry_vine", MoonberryVineBlock(Set.moonbery().moonberryLuminance(8, 11))
    ).grassLike().flammableLogs()
    val MOONBERRY_VINELET = registerNoItem(
        "moonberry_vinelet", MoonberryVineletBlock(Set.moonbery().ticksRandomly().breakInstantly())
    ).grassLike().flammableLogs()

    // ☢ Experimental ☢
    val PAINTED_ROSE = register("painted_rose", PaintedRoseBlock(Set.PAINTED_ROSE).cutout())
        .tellWitnessesThatIWasMurdered()
    val GOLDEN_MUSHROOM = register(
        "golden_mushroom", MushroomWithSporesPlantBlock(
            TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM, 0xFFD800, 0.5, Set.GOLDEN_MUSHROOM
        ).cutout()
    ).tellWitnessesThatIWasMurdered()
    val GOLDEN_MUSHROOM_BLOCK = register(
        "golden_mushroom_block", MushroomWithSporesBlock(
            0xFFD800, 0.5, Set.GOLDEN_MUSHROOM_BLOCK.luminance(11)
        )
    ).tellWitnessesThatIWasMurdered()
    val GOLDEN_MUSHROOM_STEM_BLOCK = register(
        "golden_mushroom_stem_block", MushroomBlock(Set.GOLDEN_MUSHROOM_BLOCK.luminance(9))
    ).tellWitnessesThatIWasMurdered()
    val BROWN_TREE_FUNGUS = register("brown_tree_fungus", TransparentBlock(copy(BROWN_MUSHROOM)).cutout())
        .tellWitnessesThatIWasMurdered()
    val SPIDERLILY = register(
        "spiderlily", SpiderlilyBlock(copy(ROSE_BUSH).ticksRandomly()).plant()
    ).tellWitnessesThatIWasMurdered()
    val JOUNCESHROOM_BLOCK = register(
        "jounceshroom_block", MushroomLaunchBlock(
            copy(BROWN_MUSHROOM_BLOCK).sounds(BlockSoundGroup.SHROOMLIGHT).mapColor(MapColor.PURPLE_TERRACOTTA)
        )
    ).tellWitnessesThatIWasMurdered()
    val WATER_FERN = registerNoItem("water_fern", WaterFernBlock(copy(LILY_PAD)).cutout())
        .tellWitnessesThatIWasMurdered()

    fun init() = Unit
}
