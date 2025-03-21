package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.OffsetType
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.world.gen.feature.TreeConfiguredFeatures
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.block.MoonberryVineBlock.Companion.moonberryLuminance
import org.teamvoided.dusks_and_dungeons.block.big.BigChainBlock
import org.teamvoided.dusks_and_dungeons.block.big.BigLanternBlock
import org.teamvoided.dusks_and_dungeons.block.big.BigLanternWithSpiralBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDOverlayBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.voidlib.consortium.block.ColorConsortium
import org.teamvoided.voidlib.consortium.block.VanillaColorCollections.CANDLES
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.set.createBlockSet
import org.teamvoided.voidlib.consortium.block.set.createHeadlessSet
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockSettings as Set


@Suppress("LargeClass", "TooManyFunctions", "MemberVisibilityCanBePrivate", "unused")
object DnDBlocks {
    val BLOCKS = mutableSetOf<Block>()
    val BLOCK_ITEMS = mutableMapOf<String, Item>()
    val SETS = mutableSetOf<AbstractBlockSet>()
    val COLOR_CONSORTIUM = mutableSetOf<ColorConsortium<*>>()


    val EVIL_BLOCKS = mutableSetOf<Block>()


    // region 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄 --- Flora --- 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄

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
    // endregion

    // region 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 --- Sold Oxygen --- 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳
//    val BLUE_ICE_SET = registerHeadlessSet("blue_ice", BLUE_ICE).pickaxe()
    // endregion

    // region 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ --- Big Blocks --- 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️

    val BIG_CHAIN = register("big_chain", BigChainBlock(copy(CHAIN).sounds(bigChainSound)).cutout().pickaxe())
    val BIG_LANTERN = register("big_lantern", BigLanternBlock(copy(LANTERN).sounds(bigLanternSound)).pickaxe())
    val BIG_SOUL_LANTERN =
        register("big_soul_lantern", BigLanternBlock(copy(SOUL_LANTERN).sounds(bigLanternSound)).pickaxe())

    //TODO Move this to Varinace
    /*   val BIG_REDSTONE_LANTERN = register(
         "big_redstone_lantern",
         BigRedstoneLanternBlock(copy(LANTERN).sounds(bigLanternSound).luminance(luminanceOf(8))).pickaxe()
     )*/
    // Normal
    val BIG_CANDLES = register("big_", "candle", CANDLES, ::bigCandleOf)
    val BIG_CANDLE_CAKES =
        registerNoItem("big_", "candle_cake", BIG_CANDLES.toColorCollection(), ::bigCandleCakeOf)
    val CANDELABRAS = register("candelabra", CANDLES, ::candelabraOf)

    // Soul
    val SOUL_CANDLES = register("soul_candle", CANDLES, ::soulCandleOf)
    val SOUL_CANDLE_CAKES = registerNoItem("soul_candle_cake", SOUL_CANDLES.toColorCollection(), ::soulCandleCakeOf)
    val BIG_SOUL_CANDLES = register("big_", "soul_candle", CANDLES, ::bigSoulCandleOf)
    val BIG_SOUL_CANDLE_CAKES =
        registerNoItem("big_", "soul_candle_cake", BIG_SOUL_CANDLES.toColorCollection(), ::bigSoulCandleCakeOf)
    val SOUL_CANDELABRAS = register("soul_candelabra", SOUL_CANDLES.toColorCollection(), ::candelabraOf)
    // endregion

    // region  🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 --- Rock & Stone --- 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨

    val STONE_PILLAR = register("stone_pillar", PillarBlock(copy(CHISELED_STONE_BRICKS)))
    val DEEPSLATE_PILLAR = register("deepslate_pillar", PillarBlock(copy(POLISHED_DEEPSLATE)))

    // Polish
    val POLISHED_STONE = registerSet("polished_stone", copy(SMOOTH_STONE)).pickaxe()
    val MOSSY_POLISHED_STONE = registerSet("mossy_polished_stone", copy(POLISHED_STONE)).pickaxe()

    // Overgrown
    val OVERGROWN_POLISHED_STONE = registerSet("overgrown_polished_stone", copy(MOSSY_POLISHED_STONE)).overgrown()
    val OVERGROWN_COBBLESTONE = registerSet("overgrown_cobblestone", copy(MOSSY_COBBLESTONE)).overgrown()
    val OVERGROWN_STONE_BRICKS = registerSet("overgrown_stone_brick", copy(MOSSY_STONE_BRICKS), "s").overgrown()

    // Graves
    val STONE_GRAVESTONE = registerGravestone("stone_gravestone", CHISELED_STONE_BRICKS)
    val SMALL_STONE_GRAVESTONE = registerSmallGravestone("small_stone_gravestone", STONE_GRAVESTONE)
    val DEEPSLATE_GRAVESTONE = registerGravestone("deepslate_gravestone", CHISELED_DEEPSLATE)
    val SMALL_DEEPSLATE_GRAVESTONE = registerSmallGravestone("small_deepslate_gravestone", DEEPSLATE_GRAVESTONE)
    val TUFF_GRAVESTONE = registerGravestone("tuff_gravestone", CHISELED_TUFF_BRICKS)
    val SMALL_TUFF_GRAVESTONE = registerSmallGravestone("small_tuff_gravestone", TUFF_GRAVESTONE)
    val BLACKSTONE_GRAVESTONE = registerGravestone("blackstone_gravestone", CHISELED_POLISHED_BLACKSTONE)
    val SMALL_BLACKSTONE_GRAVESTONE = registerSmallGravestone("small_blackstone_gravestone", BLACKSTONE_GRAVESTONE)
    val HEADSTONE =
        register("headstone", GravestoneBlock(headstoneShape, centerHeadstoneShape, copy(BIG_CHAIN)).cutout().pickaxe())
    // endregion

    // region  ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ --- ICE age --- ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄

    val ICE_SET =
        register(createHeadlessSet("ice", ICE).noStoneCutting().meltable().buildHeadless())
            .translucent().pickaxe()
    val PACKED_ICE_SET = registerHeadlessSet("packed_ice", PACKED_ICE).pickaxe()
    val BLUE_ICE_SET = registerHeadlessSet("blue_ice", BLUE_ICE).pickaxe()
    // endregion

    // region 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥  --- Hell ---  🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥

    val NETHERRACK_SET = registerHeadlessSet("netherrack", NETHERRACK).pickaxe()

    val NETHER_BRICK_PILLAR = register("nether_brick_pillar", PillarBlock(copy(NETHER_BRICKS)).pickaxe())
    val POLISHED_NETHER_BRICKS = registerSet("polished_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()

    // Red Nether Bricks
    val POLISHED_RED_NETHER_BRICKS =
        registerSet("polished_red_nether_brick", copy(RED_NETHER_BRICKS), "s").pickaxe()

    val CRACKED_RED_NETHER_BRICKS =
        register("cracked_red_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_FENCE =
        register("red_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_RED_NETHER_BRICKS =
        register("chiseled_red_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_PILLAR =
        register("red_nether_brick_pillar", PillarBlock(copy(RED_NETHER_BRICKS)).pickaxe())

    val MIXED_RED_NETHER_BRICKS = registerSet("mixed_red_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_RED_NETHER_BRICKS =
        register("cracked_mixed_red_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_RED_NETHER_BRICK_FENCE =
        register("mixed_red_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_RED_NETHER_BRICKS =
        register("chiseled_mixed_red_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_RED_NETHER_BRICK_PILLAR =
        register("mixed_red_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_RED_NETHER_BRICKS)).pickaxe())

    // Blue Nether Bricks
    val BLUE_NETHER_BRICKS = registerSet("blue_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_BLUE_NETHER_BRICKS =
        register("cracked_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_FENCE =
        register("blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_BLUE_NETHER_BRICKS =
        register("chiseled_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_PILLAR =
        register("blue_nether_brick_pillar", PillarBlock(copy(BLUE_NETHER_BRICKS)).pickaxe())

    val POLISHED_BLUE_NETHER_BRICKS = registerSet("polished_blue_nether_brick", copy(BLUE_NETHER_BRICKS), "s").pickaxe()

    val MIXED_BLUE_NETHER_BRICKS = registerSet("mixed_blue_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_BLUE_NETHER_BRICKS =
        register("cracked_mixed_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_FENCE =
        register("mixed_blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_BLUE_NETHER_BRICKS =
        register("chiseled_mixed_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_PILLAR = register(
        "mixed_blue_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_BLUE_NETHER_BRICKS)).pickaxe()
    )

    // Gray Nether Bricks
    val GRAY_NETHER_BRICKS = registerSet("gray_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_GRAY_NETHER_BRICKS =
        register("cracked_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_FENCE =
        register("gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_GRAY_NETHER_BRICKS =
        register("chiseled_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_PILLAR =
        register("gray_nether_brick_pillar", PillarBlock(copy(GRAY_NETHER_BRICKS)).pickaxe())

    val POLISHED_GRAY_NETHER_BRICKS = registerSet("polished_gray_nether_brick", copy(GRAY_NETHER_BRICKS), "s").pickaxe()

    val MIXED_GRAY_NETHER_BRICKS = registerSet("mixed_gray_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_GRAY_NETHER_BRICKS =
        register("cracked_mixed_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_FENCE =
        register("mixed_gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_GRAY_NETHER_BRICKS =
        register("chiseled_mixed_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_PILLAR = register(
        "mixed_gray_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_GRAY_NETHER_BRICKS)).pickaxe()
    )
    // endregion

    // region ☢ Experimental ☢
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

    val BUNNY_GRAVE = register("bunny_grave", BunnyGraveBlock(copy(STONE_BRICK_WALL)).pickaxe())
        .tellWitnessesThatIWasMurdered()

    // celestal block
    val BIG_CELESTAL_CHAIN = register(
        "big_celestal_chain", BigChainBlock(copy(CHAIN).sounds(BlockSoundGroup.BLOCK_VAULT_BREAK)).cutout().pickaxe()
    ).tellWitnessesThatIWasMurdered()
    val BIG_MOON_LANTERN = register(
        "big_moon_lantern",
        BigLanternWithSpiralBlock(
            0xE01638,
            0x8B3DB5,
            copy(BIG_SOUL_LANTERN).sounds(BlockSoundGroup.BLOCK_TRIAL_SPAWNER_BREAK)
        ).pickaxe()
    ).tellWitnessesThatIWasMurdered()
    val BIG_EARTH_LANTERN = register(
        "big_earth_lantern", BigLanternWithSpiralBlock(0xE5AE16, 0xE5B816, copy(BIG_MOON_LANTERN)).pickaxe()
    ).tellWitnessesThatIWasMurdered()
    val BIG_COMET_LANTERN = register(
        "big_comet_lantern", BigLanternWithSpiralBlock(0xE57716, 0xCC6C28, copy(BIG_MOON_LANTERN)).pickaxe()
    ).tellWitnessesThatIWasMurdered()
    val BIG_SUN_LANTERN = register(
        "big_sun_lantern", BigLanternWithSpiralBlock(0x16E5E5, 0x1470CC, copy(BIG_MOON_LANTERN)).pickaxe()
    ).tellWitnessesThatIWasMurdered()
    val BIG_STAR_LANTERN = register(
        "big_star_lantern", BigLanternWithSpiralBlock(0x7E16E5, 0xE52DE5, copy(BIG_MOON_LANTERN)).pickaxe()
    ).tellWitnessesThatIWasMurdered()
    val BIG_NEBULAE_LANTERN = register(
        "big_nebulae_lantern", BigLanternWithSpiralBlock(0x24CADA, 0x52D973, copy(BIG_MOON_LANTERN)).pickaxe()
    ).tellWitnessesThatIWasMurdered()
    val BIG_ECLIPSE_LANTERN = register(
        "big_eclipse_lantern", BigLanternWithSpiralBlock(0xE5E5E5, 0xBFBFBF, copy(BIG_MOON_LANTERN)).pickaxe()
    ).tellWitnessesThatIWasMurdered()

    // Haunted graves
    val HAUNTED_GRAVESTONE = registerHGravestone("haunted_gravestone", STONE_GRAVESTONE)
    val SMALL_HAUNTED_GRAVESTONE = registerSmallHGravestone("small_haunted_gravestone", STONE_GRAVESTONE)
    val HAUNTED_DEEPSLATE_GRAVESTONE = registerHGravestone("haunted_deepslate_gravestone", DEEPSLATE_GRAVESTONE)
    val SMALL_HAUNTED_DEEPSLATE_GRAVESTONE =
        registerSmallHGravestone("small_haunted_deepslate_gravestone", DEEPSLATE_GRAVESTONE)
    val HAUNTED_TUFF_GRAVESTONE = registerHGravestone("haunted_tuff_gravestone", TUFF_GRAVESTONE)
    val SMALL_HAUNTED_TUFF_GRAVESTONE = registerSmallHGravestone("small_haunted_tuff_gravestone", TUFF_GRAVESTONE)
    val HAUNTED_BLACKSTONE_GRAVESTONE = registerHGravestone("haunted_blackstone_gravestone", BLACKSTONE_GRAVESTONE)
    val SMALL_HAUNTED_BLACKSTONE_GRAVESTONE =
        registerSmallHGravestone("small_haunted_blackstone_gravestone", BLACKSTONE_GRAVESTONE)

    val SNOWY_STONE_BRICKS = registerSet("snowy_stone_brick", copy(STONE_BRICKS), "s").pickaxe()
        .tellWitnessesThatIWasMurdered()

    val ICE_BRICKS = register(
        createBlockSet("ice_brick", Set.ICE).s().noStoneCutting().parent(::IceBlock).meltable().build()
    ).translucent().pickaxe().tellWitnessesThatIWasMurdered()
    val PACKED_ICE_BRICKS = registerSet("packed_ice_brick", copy(PACKED_ICE), "s").pickaxe()
        .tellWitnessesThatIWasMurdered()
    val BLUE_ICE_BRICKS = registerSet("blue_ice_brick", copy(BLUE_ICE), "s").pickaxe()
        .tellWitnessesThatIWasMurdered()

    val CELESTAL_BELL = register("celestal_bell", CelestalBellBlock(copy(BELL))).tellWitnessesThatIWasMurdered()

    val MOONCORE = register(
        "mooncore", CrytalClusterWithParticlesBlock(12.0f, 2.0f, Set.MOONCORE).cutout()
    ).tellWitnessesThatIWasMurdered()
    val TALL_REDSTONE_CRYSTAL = register(
        "tall_redstone_crystal", TallRedstoneCrystalBlock(Set.REDSTONE_CRYSTAL).cutout()
    ).tellWitnessesThatIWasMurdered()
    val POT_O_SCREAMS = register("pot_o_screams", PotOScreamsBlock(copy(DECORATED_POT))).tellWitnessesThatIWasMurdered()
    val CHEST_O_SOULS = register("chest_o_souls", ChestOSoulsBlock(copy(CHEST))).tellWitnessesThatIWasMurdered()

    val QUARTER_BLOCK_PILE = registerNoItem("quarter_block_pile", QuarterBlockPileBlock(Settings.create())).cutout()

    val MOLTEN_LAVASPONGE =
        register("molten_lavasponge", TransformingBlock(copy(BASALT), LAVA)).pickaxe().tellWitnessesThatIWasMurdered()
    val BRITTLE_LAVASPONGE =
        register("brittle_lavasponge", LavaSpongeBlock(copy(BASALT), 3, 32, MOLTEN_LAVASPONGE)).pickaxe()
            .tellWitnessesThatIWasMurdered()
    val GLOWING_LAVASPONGE =
        register("glowing_lavasponge", Block(copy(BASALT))).pickaxe().tellWitnessesThatIWasMurdered()
    val LAVASPONGE =
        register("lavasponge", LavaSpongeBlock(copy(BASALT), 6, 64, GLOWING_LAVASPONGE)).pickaxe()
            .tellWitnessesThatIWasMurdered()
    // endregion

    //    🌈 🌈 🌈 🌈 --- GAY BLOCK --- 🌈 🌈 🌈 🌈
    val GAY_BLOCK = registerSet("gay_block", copy(BEACON))

    fun init() {
        DnDWoodTypes.init()
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_PLANKS, 5, 20)
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LOGS, 5, 5)
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LEAVES, 30, 60)

        DnDOverlayBlocks.init()
        DnDWoodBlocks.init()
    }

    fun register(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        // Switch away from this when the file has been merged
        DnDItems.register(id, BlockItem(regBlock, Item.Settings()))
//        BLOCK_ITEMS[id]?.let { error("Id $it already exists in BLOCK_ITEMS") }
//        BLOCK_ITEMS[id] = BlockItem(regBlock, Item.Settings())
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCKS.add(regBlock)
        return regBlock
    }
}
