package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.ColorRGBA
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.Blocks.*
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.api.BlockStrippingRegistry
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.block.MoonberryVineBlock.Companion.moonberryLuminance
import org.teamvoided.dusks_and_dungeons.block.big.BigChainBlock
import org.teamvoided.dusks_and_dungeons.block.big.BigLanternBlock
import org.teamvoided.dusks_and_dungeons.block.big.BigRedstoneLanternBlock
import org.teamvoided.dusks_and_dungeons.block.meltable.MeltableSlabBlock
import org.teamvoided.dusks_and_dungeons.block.meltable.MeltableStairsBlock
import org.teamvoided.dusks_and_dungeons.block.meltable.MeltableWallBlock
import org.teamvoided.dusks_and_dungeons.block.sapling.SaplingGenerators
import org.teamvoided.dusks_and_dungeons.block.sapling.ThreeWideTreeSaplingBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockSetTypes
import org.teamvoided.dusks_and_dungeons.util.*
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.voidlib.consortium.block.color.ColorConsortium
import org.teamvoided.voidlib.consortium.block.color.VanillaColorCollections
import org.teamvoided.voidlib.consortium.block.color.VanillaColorCollections.CANDLES
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.set.createBlockSet
import org.teamvoided.voidlib.consortium.block.set.createHeadlessSet
import java.util.function.Function
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockProperties as Prop


object DnDBlocks {

    val BLOCKS get() = getModEntries(BuiltInRegistries.BLOCK)

    // Collections
    val SETS = mutableSetOf<AbstractBlockSet>()
    val COLOR_CONSORTIUM = mutableSetOf<ColorConsortium<*>>()


    val EVIL_BLOCKS = mutableSetOf<Block>()

    init { //  Pre Block Init
        DnDBlockSetTypes.init()
        DnDWoodTypes.init()
    }


    // region 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄 --- Flora --- 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄

    val WARPED_WART = register("warped_wart", ::WarpedNetherWartBlock, Prop.WARPED_WART).grassLike()

    // Petals
    val WHITE_PETALS = register("white_petals", ::PinkPetalsBlock, Prop.petals(MapColor.SNOW)).plant()
    val RED_PETALS = register("red_petals", ::PinkPetalsBlock, Prop.petals(MapColor.COLOR_RED)).plant()
    val ORANGE_PETALS = register("orange_petals", ::PinkPetalsBlock, Prop.petals(MapColor.COLOR_ORANGE)).plant()
    val BLUE_PETALS = register("blue_petals", ::PinkPetalsBlock, Prop.petals(MapColor.COLOR_BLUE)).plant()
    val COLD_WILDFLOWER = register("cold_wildflower", ::PinkPetalsBlock, Prop.petals(MapColor.COLOR_PURPLE)).plant()
    val CRIMSON_VIVIONS = register("crimson_vivions", ::VivionbedBlock, Prop.vivions(MapColor.COLOR_RED)).plant()
    val WARPED_VIVIONS = register("warped_vivions", ::VivionbedBlock, Prop.vivions(MapColor.WARPED_WART_BLOCK)).plant()

    // Smol Punkin
    val SMALL_CARVED_PUMPKIN = registerHeadEquipable("small_carved_pumpkin", sCarvedPumpkinOf(CARVED_PUMPKIN)).axe()
    val SMALL_GLOWING_PUMPKIN = registerOld("small_jack_o_lantern", sGlowingPumpkinOf(SMALL_CARVED_PUMPKIN)).axe()
    val SMALL_PUMPKIN = registerOld("small_pumpkin", sPumpkinOf(SMALL_CARVED_PUMPKIN)).axe()

    // Lantern ---
    val CARVED_LANTERN_PUMPKIN =
        registerHeadEquipable("carved_lantern_pumpkin", carvedPumpkin(MapColor.COLOR_YELLOW)).axe()
    val GLOWING_LANTERN_PUMPKIN = registerOld("glowing_lantern_pumpkin", glowingPumpkinOf(CARVED_LANTERN_PUMPKIN)).axe()
    val LANTERN_PUMPKIN = registerOld("lantern_pumpkin", pumpkinOf(CARVED_LANTERN_PUMPKIN)).axe()
    val SMALL_CARVED_LANTERN_PUMPKIN =
        registerHeadEquipable("small_carved_lantern_pumpkin", sCarvedPumpkinOf(CARVED_LANTERN_PUMPKIN)).axe()
    val SMALL_GLOWING_LANTERN_PUMPKIN =
        registerOld("small_glowing_lantern_pumpkin", sGlowingPumpkinOf(SMALL_CARVED_LANTERN_PUMPKIN)).axe()
    val SMALL_LANTERN_PUMPKIN = registerOld("small_lantern_pumpkin", sPumpkinOf(SMALL_CARVED_LANTERN_PUMPKIN)).axe()
    val LANTERN_PUMPKIN_STEM = registerNoItemOld("lantern_pumpkin_stem", stemOf(LANTERN_PUMPKIN)).grassLike()

    // Mosskin ---
    val CARVED_MOSSKIN_PUMPKIN =
        registerHeadEquipable("carved_mosskin_pumpkin", carvedPumpkin(MapColor.COLOR_GREEN)).axe()
    val GLOWING_MOSSKIN_PUMPKIN = registerOld("glowing_mosskin_pumpkin", glowingPumpkinOf(CARVED_MOSSKIN_PUMPKIN)).axe()
    val MOSSKIN_PUMPKIN = registerOld("mosskin_pumpkin", pumpkinOf(CARVED_MOSSKIN_PUMPKIN)).axe()
    val SMALL_CARVED_MOSSKIN_PUMPKIN =
        registerHeadEquipable("small_carved_mosskin_pumpkin", sCarvedPumpkinOf(CARVED_MOSSKIN_PUMPKIN)).axe()
    val SMALL_GLOWING_MOSSKIN_PUMPKIN =
        registerOld("small_glowing_mosskin_pumpkin", sGlowingPumpkinOf(SMALL_CARVED_MOSSKIN_PUMPKIN)).axe()
    val SMALL_MOSSKIN_PUMPKIN = registerOld("small_mosskin_pumpkin", sPumpkinOf(SMALL_CARVED_MOSSKIN_PUMPKIN)).axe()
    val MOSSKIN_PUMPKIN_STEM = registerNoItemOld("mosskin_pumpkin_stem", stemOf(MOSSKIN_PUMPKIN)).grassLike()

    // Gloom ---
    val CARVED_GLOOM_PUMPKIN =
        registerHeadEquipable("carved_gloom_pumpkin", carvedPumpkin(MapColor.TERRACOTTA_PURPLE)).axe()
    val GLOWING_GLOOM_PUMPKIN = registerOld("glowing_gloom_pumpkin", glowingPumpkinOf(CARVED_GLOOM_PUMPKIN)).axe()
    val GLOOM_PUMPKIN = registerOld("gloom_pumpkin", pumpkinOf(CARVED_GLOOM_PUMPKIN)).axe()
    val SMALL_CARVED_GLOOM_PUMPKIN =
        registerHeadEquipable("small_carved_gloom_pumpkin", sCarvedPumpkinOf(CARVED_GLOOM_PUMPKIN)).axe()
    val SMALL_GLOWING_GLOOM_PUMPKIN =
        registerOld("small_glowing_gloom_pumpkin", sGlowingPumpkinOf(SMALL_CARVED_GLOOM_PUMPKIN)).axe()
    val SMALL_GLOOM_PUMPKIN = registerOld("small_gloom_pumpkin", sPumpkinOf(SMALL_CARVED_GLOOM_PUMPKIN)).axe()
    val GLOOM_PUMPKIN_STEM = registerNoItemOld("gloom_pumpkin_stem", stemOf(GLOOM_PUMPKIN)).grassLike()

    // Pale ---
    val CARVED_PALE_PUMPKIN = registerHeadEquipable("carved_pale_pumpkin", carvedPumpkin(MapColor.SNOW)).axe()
    val GLOWING_PALE_PUMPKIN = registerOld("glowing_pale_pumpkin", glowingPumpkinOf(CARVED_PALE_PUMPKIN)).axe()
    val PALE_PUMPKIN = registerOld("pale_pumpkin", pumpkinOf(CARVED_PALE_PUMPKIN)).axe()
    val SMALL_CARVED_PALE_PUMPKIN =
        registerHeadEquipable("small_carved_pale_pumpkin", sCarvedPumpkinOf(CARVED_PALE_PUMPKIN)).axe()
    val SMALL_GLOWING_PALE_PUMPKIN =
        registerOld("small_glowing_pale_pumpkin", sGlowingPumpkinOf(SMALL_CARVED_PALE_PUMPKIN)).axe()
    val SMALL_PALE_PUMPKIN = registerOld("small_pale_pumpkin", sPumpkinOf(SMALL_CARVED_PALE_PUMPKIN)).axe()
    val PALE_PUMPKIN_STEM = registerNoItemOld("pale_pumpkin_stem", stemOf(PALE_PUMPKIN)).grassLike()

    // Corn
    val CORN_CROP = registerNoItem("corn_crop", ::CornCropBlock, Prop.corn().randomTicks()).grassLike()
    val CORN = registerNoItem("corn", ::CornMazeBlock, Prop.corn().offsetType(OffsetType.XYZ)).grassLike()
    val CORN_BLOCK = register("corn_block", ::RotatedPillarBlock, ofFullCopy(CHERRY_PLANKS).mapColor(MapColor.GOLD))
        .axe()

    @JvmField
    val CORN_SYRUP_BLOCK = register("corn_syrup_block", ::CornSyrupBlock, Prop.CORN_SYRUP).translucent()

    // The Rest
    val ROOT_BLOCK = register("root_block", ::MangroveRootsBlock, Prop.ROOT_BLOCK).grassLike().flammableLeaves()
    val WILD_WHEAT = registerNoItem("wild_wheat", ::TallSpreadableBlock, Prop.WILD_WHEAT).grassLike()
    val GOLDEN_BEETROOTS = registerNoItem("golden_beetroots", ::GoldenBeetrootsBlock, Prop.GOLDEN_BEETROOT).grassLike()
    val MOONBERRY_VINE = register(
        "moonberry_vine", ::MoonberryVineBlock, Prop.moonbery().moonberryLuminance(8, 11)
    ).grassLike().flammableLogs()
    val MOONBERRY_VINELET = registerNoItem(
        "moonberry_vinelet", ::MoonberryVineletBlock, Prop.moonbery().randomTicks().instabreak()
    ).grassLike().flammableLogs()

    val GOLDEN_MUSHROOM = register(
        "golden_mushroom",
        { MushroomWithSporesPlantBlock(DnDConfiguredFeature.HUGE_GOLDEN_MUSHROOM, 0xFFD800, 0.5, it) },
        Prop.GOLDEN_MUSHROOM
    ).cutout().axe().sword()
    val GOLDEN_MUSHROOM_BLOCK = register(
        "golden_mushroom_block",
        { MushroomWithSporesBlock(0xFFD800, 0.5, it) },
        Prop.GOLDEN_MUSHROOM_BLOCK.luminance(11)
    ).axe()
    val GOLDEN_MUSHROOM_STEM_BLOCK = register(
        "golden_mushroom_stem_block", ::HugeMushroomBlock, Prop.GOLDEN_MUSHROOM_BLOCK.luminance(9)
    ).axe()

    // Overgrowth
    val OVERGROWTH_BLOCK = register("overgrowth_block", ::OvergrowthBlock, ofFullCopy(MOSS_BLOCK)).grass().tint().hoe()
    val OVERGROWTH_CARPET = register("overgrowth_carpet", ::MossyCarpetBlock, ofFullCopy(MOSS_CARPET).noOcclusion())
        .cutout().grass().tint().hoe().sword()
    val OVERGROWTH_BUSH = register("overgrowth_bush", ::OvergrowthBushBlock, ofFullCopy(AZALEA))
        .cutout().grass().tint()
    val POTTED_OVERGROWTH_BUSH =
        registerNoItemOld("potted_overgrowth_bush", flowerPot(OVERGROWTH_BUSH)).cutout().grass()
    val HANGING_OVERGROWTH = register(
        "hanging_overgrowth", ::HangingFloraBlock,
        Properties.of()
            .mapColor(OVERGROWTH_BLOCK.defaultMapColor())
            .instabreak()
            .noCollission()
            .sound(SoundType.CAVE_VINES)
            .pushReaction(PushReaction.DESTROY)
    ).cutout().grass().tint().hoe()
    //overgrowth (covering) (also use this block class for the overlay replacements, may also want to make a moss and pale moss variant of this)

    // endregion

    // region 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 --- Sold Oxygen --- 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳

    // region Cascade
    val CASCADE_SAPLING = registerOld(
        "cascade_sapling", ThreeWideTreeSaplingBlock(SaplingGenerators.CASCADE, Prop.CASCADE_SAPLING)
    ).cutout()
    val POTTED_CASCADE_SAPLING = registerNoItemOld("potted_cascade_sapling", flowerPot(CASCADE_SAPLING)).cutout()
    val CASCADE_LEAVES = registerOld(
        "cascade_leaves", FallingLeavesBlock(DnDParticles.CASCADE_LEAF_PARTICLE, Prop.CASCADE_LEAVES)
    ).leaves()
    val CASCADE_LEAF_PILE = registerOld(
        "cascade_leaf_pile",
        fallingLeafPile(DnDParticles.CASCADE_LEAF_PARTICLE, MapColor.COLOR_RED, SoundType.AZALEA_LEAVES)
    ).cutout()
    val CASCADE_LOG = registerOld("cascade_log", log(MapColor.COLOR_BLUE, MapColor.COLOR_BROWN, SoundType.CHERRY_WOOD))
    val CASCADE_WOOD = register(
        createBlockSet("cascade_wood", Prop.CASCADE_WOOD)
            .noStoneCutting()
            .parent(::RotatedPillarBlock)
            .build()
    ).woodSet()

    val CASCADE_LOG_PILE = registerOld("cascade_log_pile", logPile(CASCADE_WOOD.parent))
    val STRIPPED_CASCADE_LOG =
        registerOld("stripped_cascade_log", log(MapColor.COLOR_BLUE, MapColor.COLOR_BLUE, SoundType.CHERRY_WOOD))
    val STRIPPED_CASCADE_WOOD = register(
        createBlockSet("stripped_cascade_wood", Prop.STRIPPED_CASCADE_WOOD).noStoneCutting()
            .parent(::RotatedPillarBlock).build()
    ).woodSet()
    val STRIPPED_CASCADE_LOG_PILE = registerOld("stripped_cascade_log_pile", logPile(STRIPPED_CASCADE_WOOD.parent))

    val CASCADE_PLANKS = register("cascade_planks", Prop.CASCADE_PLANKS).flammablePlanks()
    val CASCADE_STAIRS = registerOld("cascade_stairs", stairsOf(CASCADE_PLANKS)).wood()
    val CASCADE_SLAB = registerOld("cascade_slab", slabOf(CASCADE_PLANKS)).wood()
    val CASCADE_WALL = registerOld("cascade_wall", wallOf(CASCADE_PLANKS)).wood()
    val CASCADE_FENCE = registerOld("cascade_fence", fenceOf(CASCADE_PLANKS)).wood()
    val CASCADE_FENCE_GATE =
        registerOld("cascade_fence_gate", fenceGateOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS)).wood()
    val CASCADE_DOOR =
        registerNoItemOld("cascade_door", doorOf(DnDBlockSetTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS)).wood()
    val BLUE_DOOR = registerNoItemOld("blue_door", DoorBlock(BlockSetType.DARK_OAK, Prop.BLUE_DOOR)).wood()
    val CASCADE_TRAPDOOR =
        registerOld("cascade_trapdoor", trapdoorOf(DnDBlockSetTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS)).wood()
    val CASCADE_PRESSURE_PLATE = registerOld(
        "cascade_pressure_plate",
        pressurePlateOf(DnDBlockSetTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS)
    ).wood()
    val CASCADE_BUTTON = registerOld("cascade_button", woodenButton(DnDBlockSetTypes.CASCADE_BLOCK_SET_TYPE)).wood()
    val CASCADE_SIGN = registerNoItemOld("cascade_sign", signOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS)).wood()
    val CASCADE_WALL_SIGN = registerNoItemOld(
        "cascade_wall_sign", wallSignOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS, CASCADE_SIGN).wood()
    )
    val CASCADE_HANGING_SIGN =
        registerNoItemOld("cascade_hanging_sign", hangingSignOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS)).wood()
    val CASCADE_WALL_HANGING_SIGN = registerNoItemOld(
        "cascade_wall_hanging_sign",
        wallHangingSignOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS, CASCADE_HANGING_SIGN).wood()
    )
    // endregion

    // region Sypia
    val SYPIA_SAPLING = registerOld(
        "sypia_sapling",
        SaplingBlock(SaplingGenerators.SYPIA, ofFullCopy(BIRCH_SAPLING).mapColor(MapColor.COLOR_YELLOW))
    ).cutout()
    val POTTED_SYPIA_SAPLING = registerNoItemOld("potted_sypia_sapling", flowerPot(SYPIA_SAPLING)).cutout()
    val SYPIA_LEAVES = register(
        "sypia_leaves", ::LeavesBlock, ofFullCopy(BIRCH_LEAVES).mapColor(MapColor.COLOR_YELLOW)
    ).leaves()
    val SYPIA_LEAF_PILE = registerOld("sypia_leaf_pile", leafPile(MapColor.COLOR_YELLOW)).cutout()

    val SYPIA_LOG = registerOld("sypia_log", log(MapColor.COLOR_BLUE, MapColor.COLOR_BROWN, SoundType.CHERRY_WOOD))
    val SYPIA_WOOD = register(
        createBlockSet("sypia_wood", Prop.SYPIA_WOOD)
            .noStoneCutting()
            .parent(::RotatedPillarBlock)
            .build()
    ).woodSet()

    val SYPIA_LOG_PILE = registerOld("sypia_log_pile", logPile(SYPIA_WOOD.parent))
    val STRIPPED_SYPIA_LOG =
        registerOld("stripped_sypia_log", log(MapColor.COLOR_BLUE, MapColor.COLOR_BLUE, SoundType.CHERRY_WOOD))
    val STRIPPED_SYPIA_WOOD = register(
        createBlockSet("stripped_sypia_wood", Prop.STRIPPED_SYPIA_WOOD)
            .noStoneCutting()
            .parent(::RotatedPillarBlock)
            .build()
    ).woodSet()
    val STRIPPED_SYPIA_LOG_PILE = registerOld("stripped_sypia_log_pile", logPile(STRIPPED_SYPIA_WOOD.parent))

    val SYPIA_PLANKS = register("sypia_planks", Prop.SYPIA_PLANKS).flammablePlanks()
    val SYPIA_STAIRS = registerOld("sypia_stairs", stairsOf(SYPIA_PLANKS)).wood()
    val SYPIA_SLAB = registerOld("sypia_slab", slabOf(SYPIA_PLANKS)).wood()
    val SYPIA_WALL = registerOld("sypia_wall", wallOf(SYPIA_PLANKS)).wood()
    val SYPIA_FENCE = registerOld("sypia_fence", fenceOf(SYPIA_PLANKS)).wood()
    val SYPIA_FENCE_GATE =
        registerOld("sypia_fence_gate", fenceGateOf(DnDWoodTypes.SYPIA_WOOD_TYPE, SYPIA_PLANKS)).wood()
    val SYPIA_DOOR =
        registerNoItemOld("sypia_door", doorOf(DnDBlockSetTypes.SYPIA_BLOCK_SET_TYPE, SYPIA_PLANKS)).wood()
    val SYPIA_TRAPDOOR =
        registerOld("sypia_trapdoor", trapdoorOf(DnDBlockSetTypes.SYPIA_BLOCK_SET_TYPE, SYPIA_PLANKS)).wood()
    val SYPIA_PRESSURE_PLATE =
        registerOld("sypia_pressure_plate", pressurePlateOf(DnDBlockSetTypes.SYPIA_BLOCK_SET_TYPE, SYPIA_PLANKS)).wood()
    val SYPIA_BUTTON = registerOld("sypia_button", woodenButton(DnDBlockSetTypes.SYPIA_BLOCK_SET_TYPE)).wood()
    val SYPIA_SIGN = registerNoItemOld("sypia_sign", signOf(DnDWoodTypes.SYPIA_WOOD_TYPE, SYPIA_PLANKS)).wood()
    val SYPIA_WALL_SIGN = registerNoItemOld(
        "sypia_wall_sign", wallSignOf(DnDWoodTypes.SYPIA_WOOD_TYPE, SYPIA_PLANKS, SYPIA_SIGN).wood()
    )
    val SYPIA_HANGING_SIGN =
        registerNoItemOld("sypia_hanging_sign", hangingSignOf(DnDWoodTypes.SYPIA_WOOD_TYPE, SYPIA_PLANKS)).wood()
    val SYPIA_WALL_HANGING_SIGN = registerNoItemOld(
        "sypia_wall_hanging_sign",
        wallHangingSignOf(DnDWoodTypes.SYPIA_WOOD_TYPE, SYPIA_PLANKS, SYPIA_HANGING_SIGN).wood()
    )
    // endregion

    // region Verdant
    val VERDANT_LEAVES = register("verdant_leaves", ::LeavesBlock, ofFullCopy(AZALEA_LEAVES))
        .cutout().grass().tint().hoe()
    val VERDANT_LEAF_PILE = registerOld(
        "verdant_leaf_pile",
        leafPile(VERDANT_LEAVES.defaultMapColor(), SoundType.AZALEA_LEAVES)
    ).cutout().grass().tint().hoe()

    val VERDANT_LOG = registerOld("verdant_log", log(MapColor.GRASS, MapColor.COLOR_BROWN)).grass().cutout()
    val VERDANT_WOOD = register(
        createBlockSet("verdant_wood", Prop.VERDANT_WOOD)
            .noStoneCutting()
            .parent(::RotatedPillarBlock)
            .build()
    ).woodSet().grass()


    val VERDANT_LOG_PILE = registerOld("verdant_log_pile", logPile(VERDANT_WOOD.parent)).grass()
    val STRIPPED_VERDANT_LOG = registerOld("stripped_verdant_log", log(MapColor.GRASS, MapColor.GRASS)).grass().tint()
    val STRIPPED_VERDANT_WOOD = register(
        createBlockSet("stripped_verdant_wood", Prop.STRIPPED_VERDANT_WOOD)
            .noStoneCutting()
            .parent(::RotatedPillarBlock)
            .build()
    ).woodSet().grass().tint()
    val STRIPPED_VERDANT_LOG_PILE = registerOld("stripped_verdant_log_pile", logPile(STRIPPED_VERDANT_WOOD.parent))
        .grass().tint()


    val VERDANT_PLANKS = register("verdant_planks", Prop.VERDANT_PLANKS).flammablePlanks().grass().tint()
    val VERDANT_STAIRS = registerOld("verdant_stairs", stairsOf(VERDANT_PLANKS)).wood().grass().tint()
    val VERDANT_SLAB = registerOld("verdant_slab", slabOf(VERDANT_PLANKS)).wood().grass().tint()
    val VERDANT_WALL = registerOld("verdant_wall", wallOf(VERDANT_PLANKS)).wood().grass().tint()
    val VERDANT_FENCE = registerOld("verdant_fence", fenceOf(VERDANT_PLANKS)).wood().grass().tint()
    val VERDANT_FENCE_GATE =
        registerOld("verdant_fence_gate", fenceGateOf(DnDWoodTypes.VERDANT_WOOD_TYPE, VERDANT_PLANKS))
            .wood().grass().tint()
    val VERDANT_DOOR =
        registerNoItemOld("verdant_door", doorOf(DnDBlockSetTypes.VERDANT_BLOCK_SET_TYPE, VERDANT_PLANKS))
            .wood().grass().tint()
    val VERDANT_TRAPDOOR = registerOld(
        "verdant_trapdoor", trapdoorOf(DnDBlockSetTypes.VERDANT_BLOCK_SET_TYPE, VERDANT_PLANKS)
    ).wood().grass().tint()
    val VERDANT_PRESSURE_PLATE = registerOld(
        "verdant_pressure_plate", pressurePlateOf(DnDBlockSetTypes.VERDANT_BLOCK_SET_TYPE, VERDANT_PLANKS)
    ).wood().grass().tint()
    val VERDANT_BUTTON = registerOld("verdant_button", woodenButton(DnDBlockSetTypes.VERDANT_BLOCK_SET_TYPE))
        .wood().grass().tint()

    val VERDANT_SIGN = registerNoItemOld("verdant_sign", signOf(DnDWoodTypes.VERDANT_WOOD_TYPE, VERDANT_PLANKS))
        .wood().tint().grass()
    val VERDANT_WALL_SIGN = registerNoItemOld(
        "verdant_wall_sign", wallSignOf(DnDWoodTypes.VERDANT_WOOD_TYPE, VERDANT_PLANKS, VERDANT_SIGN)
    ).wood().tint().grass()
    val VERDANT_HANGING_SIGN = registerNoItemOld(
        "verdant_hanging_sign", hangingSignOf(DnDWoodTypes.VERDANT_WOOD_TYPE, VERDANT_PLANKS)
    ).wood().tint().grass()
    val VERDANT_WALL_HANGING_SIGN = registerNoItemOld(
        "verdant_wall_hanging_sign",
        wallHangingSignOf(DnDWoodTypes.VERDANT_WOOD_TYPE, VERDANT_PLANKS, VERDANT_HANGING_SIGN)
    ).wood().tint().grass()
    // endregion

    val OAK_WOOD = registerWoodenSet("oak_wood", Blocks.OAK_WOOD)
    val SPRUCE_WOOD = registerWoodenSet("spruce_wood", Blocks.SPRUCE_WOOD)
    val BIRCH_WOOD = registerWoodenSet("birch_wood", Blocks.BIRCH_WOOD)
    val JUNGLE_WOOD = registerWoodenSet("jungle_wood", Blocks.JUNGLE_WOOD)
    val ACACIA_WOOD = registerWoodenSet("acacia_wood", Blocks.ACACIA_WOOD)
    val DARK_OAK_WOOD = registerWoodenSet("dark_oak_wood", Blocks.DARK_OAK_WOOD)
    val MANGROVE_WOOD = registerWoodenSet("mangrove_wood", Blocks.MANGROVE_WOOD)
    val CHERRY_WOOD = registerWoodenSet("cherry_wood", Blocks.CHERRY_WOOD)
    val CRIMSON_HYPHAE = registerWoodenSet("crimson_hyphae", Blocks.CRIMSON_HYPHAE)
    val WARPED_HYPHAE = registerWoodenSet("warped_hyphae", Blocks.WARPED_HYPHAE)

    val STRIPPED_OAK_WOOD = registerWoodenSet("stripped_oak_wood", Blocks.STRIPPED_OAK_WOOD)
    val STRIPPED_SPRUCE_WOOD = registerWoodenSet("stripped_spruce_wood", Blocks.STRIPPED_SPRUCE_WOOD)
    val STRIPPED_BIRCH_WOOD = registerWoodenSet("stripped_birch_wood", Blocks.STRIPPED_BIRCH_WOOD)
    val STRIPPED_JUNGLE_WOOD = registerWoodenSet("stripped_jungle_wood", Blocks.STRIPPED_JUNGLE_WOOD)
    val STRIPPED_ACACIA_WOOD = registerWoodenSet("stripped_acacia_wood", Blocks.STRIPPED_ACACIA_WOOD)
    val STRIPPED_DARK_OAK_WOOD = registerWoodenSet("stripped_dark_oak_wood", Blocks.STRIPPED_DARK_OAK_WOOD)
    val STRIPPED_MANGROVE_WOOD = register(
        createHeadlessSet("stripped_mangrove_wood", Blocks.STRIPPED_MANGROVE_WOOD)
            .settings(ofFullCopy(Blocks.STRIPPED_MANGROVE_WOOD).mapColor(MapColor.COLOR_RED))
            .noStoneCutting()
            .buildHeadless()
    ).woodSet()
    val STRIPPED_CHERRY_WOOD = registerWoodenSet("stripped_cherry_wood", Blocks.STRIPPED_CHERRY_WOOD)
    val STRIPPED_CRIMSON_HYPHAE = registerWoodenSet("stripped_crimson_hyphae", Blocks.STRIPPED_CRIMSON_HYPHAE)
    val STRIPPED_WARPED_HYPHAE = registerWoodenSet("stripped_warped_hyphae", Blocks.STRIPPED_WARPED_HYPHAE)

    //(ender) Uses wood because logs have diff map colors based on if top is showing
    val OAK_LOG_PILE = registerOld("oak_log_pile", logPile(Blocks.OAK_WOOD))
    val SPRUCE_LOG_PILE = registerOld("spruce_log_pile", logPile(Blocks.SPRUCE_WOOD))
    val BIRCH_LOG_PILE = registerOld("birch_log_pile", logPile(Blocks.BIRCH_WOOD))
    val JUNGLE_LOG_PILE = registerOld("jungle_log_pile", logPile(Blocks.JUNGLE_WOOD))
    val ACACIA_LOG_PILE = registerOld("acacia_log_pile", logPile(Blocks.ACACIA_WOOD))
    val DARK_OAK_LOG_PILE = registerOld("dark_oak_log_pile", logPile(Blocks.DARK_OAK_WOOD))
    val MANGROVE_LOG_PILE = registerOld("mangrove_log_pile", logPile(Blocks.MANGROVE_WOOD))
    val CHERRY_LOG_PILE = registerOld("cherry_log_pile", logPile(Blocks.CHERRY_WOOD))
    val CRIMSON_STEM_PILE = registerOld("crimson_stem_pile", logPile(Blocks.CRIMSON_HYPHAE))
    val WARPED_STEM_PILE = registerOld("warped_stem_pile", logPile(Blocks.WARPED_HYPHAE))
    val BAMBOO_PILE = registerOld("bamboo_pile", logPile(BAMBOO_PLANKS, MapColor.PLANT))

    val STRIPPED_OAK_LOG_PILE = registerOld("stripped_oak_log_pile", logPile(Blocks.STRIPPED_OAK_WOOD))
    val STRIPPED_SPRUCE_LOG_PILE = registerOld("stripped_spruce_log_pile", logPile(Blocks.STRIPPED_SPRUCE_WOOD))
    val STRIPPED_BIRCH_LOG_PILE = registerOld("stripped_birch_log_pile", logPile(Blocks.STRIPPED_BIRCH_WOOD))
    val STRIPPED_JUNGLE_LOG_PILE = registerOld("stripped_jungle_log_pile", logPile(Blocks.STRIPPED_JUNGLE_WOOD))
    val STRIPPED_ACACIA_LOG_PILE = registerOld("stripped_acacia_log_pile", logPile(Blocks.STRIPPED_ACACIA_WOOD))
    val STRIPPED_DARK_OAK_LOG_PILE = registerOld("stripped_dark_oak_log_pile", logPile(Blocks.STRIPPED_DARK_OAK_WOOD))
    val STRIPPED_MANGROVE_LOG_PILE =
        registerOld("stripped_mangrove_log_pile", logPile(Blocks.STRIPPED_MANGROVE_WOOD, MapColor.COLOR_RED))
    val STRIPPED_CHERRY_LOG_PILE = registerOld("stripped_cherry_log_pile", logPile(Blocks.STRIPPED_CHERRY_WOOD))
    val STRIPPED_CRIMSON_STEM_PILE = registerOld("stripped_crimson_stem_pile", logPile(Blocks.STRIPPED_CRIMSON_HYPHAE))
    val STRIPPED_WARPED_STEM_PILE = registerOld("stripped_warped_stem_pile", logPile(Blocks.STRIPPED_WARPED_HYPHAE))
    val STRIPPED_BAMBOO_PILE = registerOld("stripped_bamboo_pile", logPile(BAMBOO_PLANKS))

    val OAK_LEAF_PILE = registerOld("oak_leaf_pile", leafPile()).cutout()
    val SPRUCE_LEAF_PILE = registerOld("spruce_leaf_pile", leafPile()).cutout()
    val BIRCH_LEAF_PILE = registerOld("birch_leaf_pile", leafPile()).cutout()
    val JUNGLE_LEAF_PILE = registerOld("jungle_leaf_pile", leafPile()).cutout()
    val ACACIA_LEAF_PILE = registerOld("acacia_leaf_pile", leafPile()).cutout()
    val DARK_OAK_LEAF_PILE = registerOld("dark_oak_leaf_pile", leafPile()).cutout()
    val MANGROVE_LEAF_PILE = registerOld("mangrove_leaf_pile", leafPile()).cutout()
    val CHERRY_LEAF_PILE = registerOld(
        "cherry_leaf_pile", fallingLeafPile(ParticleTypes.CHERRY_LEAVES, MapColor.COLOR_PINK, SoundType.CHERRY_LEAVES)
    ).cutout()
    val AZALEA_LEAF_PILE = registerOld("azalea_leaf_pile", leafPile(SoundType.AZALEA_LEAVES)).cutout()
    val FLOWERING_AZALEA_LEAF_PILE = registerOld(
        "flowering_azalea_leaf_pile", leafPile(SoundType.AZALEA_LEAVES).cutout()
    )

    val HOLLOW_OAK_LOG = registerHollowLog("hollow_oak_log", OAK_LOG)
    val HOLLOW_STRIPPED_OAK_LOG = registerHollowLog("hollow_stripped_oak_log", STRIPPED_OAK_LOG)
    val HOLLOW_SPRUCE_LOG = registerHollowLog("hollow_spruce_log", SPRUCE_LOG)
    val HOLLOW_STRIPPED_SPRUCE_LOG = registerHollowLog("hollow_stripped_spruce_log", STRIPPED_SPRUCE_LOG)
    val HOLLOW_BIRCH_LOG = registerHollowLog("hollow_birch_log", BIRCH_LOG)
    val HOLLOW_STRIPPED_BIRCH_LOG = registerHollowLog("hollow_stripped_birch_log", STRIPPED_BIRCH_LOG)
    val HOLLOW_JUNGLE_LOG = registerHollowLog("hollow_jungle_log", JUNGLE_LOG)
    val HOLLOW_STRIPPED_JUNGLE_LOG = registerHollowLog("hollow_stripped_jungle_log", STRIPPED_JUNGLE_LOG)
    val HOLLOW_ACACIA_LOG = registerHollowLog("hollow_acacia_log", ACACIA_LOG)
    val HOLLOW_STRIPPED_ACACIA_LOG = registerHollowLog("hollow_stripped_acacia_log", STRIPPED_ACACIA_LOG)
    val HOLLOW_DARK_OAK_LOG = registerHollowLog("hollow_dark_oak_log", DARK_OAK_LOG)
    val HOLLOW_STRIPPED_DARK_OAK_LOG = registerHollowLog("hollow_stripped_dark_oak_log", STRIPPED_DARK_OAK_LOG)
    val HOLLOW_MANGROVE_LOG = registerHollowLog("hollow_mangrove_log", MANGROVE_LOG)
    val HOLLOW_STRIPPED_MANGROVE_LOG = registerHollowLog("hollow_stripped_mangrove_log", STRIPPED_MANGROVE_LOG)
    val HOLLOW_CHERRY_LOG = registerHollowLog("hollow_cherry_log", CHERRY_LOG)
    val HOLLOW_STRIPPED_CHERRY_LOG = registerHollowLog("hollow_stripped_cherry_log", STRIPPED_CHERRY_LOG)
    val HOLLOW_BAMBOO_BLOCK = registerOld("hollow_bamboo_block", hollowBambooBlock(BAMBOO_BLOCK))
    val HOLLOW_STRIPPED_BAMBOO_BLOCK =
        registerOld("hollow_stripped_bamboo_block", hollowBambooBlock(STRIPPED_BAMBOO_BLOCK))
    val HOLLOW_CRIMSON_STEM = registerHollowLog("hollow_crimson_stem", Blocks.CRIMSON_HYPHAE)
    val HOLLOW_STRIPPED_CRIMSON_STEM =
        registerHollowLog("hollow_stripped_crimson_stem", Blocks.STRIPPED_CRIMSON_HYPHAE)
    val HOLLOW_WARPED_STEM = registerHollowLog("hollow_warped_stem", Blocks.WARPED_HYPHAE)
    val HOLLOW_STRIPPED_WARPED_STEM = registerHollowLog("hollow_stripped_warped_stem", Blocks.STRIPPED_WARPED_HYPHAE)

    val HOLLOW_CASCADE_LOG = registerHollowLog("hollow_cascade_log", CASCADE_LOG)
    val HOLLOW_STRIPPED_CASCADE_LOG = registerHollowLog("hollow_stripped_cascade_log", STRIPPED_CASCADE_LOG)
    val HOLLOW_SYPIA_LOG = registerHollowLog("hollow_sypia_log", CASCADE_LOG)
    val HOLLOW_STRIPPED_SYPIA_LOG = registerHollowLog("hollow_stripped_sypia_log", STRIPPED_CASCADE_LOG)
    val HOLLOW_VERDANT_LOG = registerHollowLog("hollow_verdant_log", VERDANT_LOG).cutout().grass()
    val HOLLOW_STRIPPED_VERDANT_LOG = registerHollowLog("hollow_stripped_verdant_log", STRIPPED_VERDANT_LOG)
        .grass().tint()

    // Refined wood blocks
    val OAK_WALL = registerWall("oak_wall", OAK_PLANKS).wood()
    val SPRUCE_WALL = registerWall("spruce_wall", SPRUCE_PLANKS).wood()
    val BIRCH_WALL = registerWall("birch_wall", BIRCH_PLANKS).wood()
    val JUNGLE_WALL = registerWall("jungle_wall", JUNGLE_PLANKS).wood()
    val ACACIA_WALL = registerWall("acacia_wall", ACACIA_PLANKS).wood()
    val DARK_OAK_WALL = registerWall("dark_oak_wall", DARK_OAK_PLANKS).wood()
    val MANGROVE_WALL = registerWall("mangrove_wall", MANGROVE_PLANKS).wood()
    val CHERRY_WALL = registerWall("cherry_wall", CHERRY_PLANKS).wood()
    val CRIMSON_WALL = registerWall("crimson_wall", CRIMSON_PLANKS).wood()
    val WARPED_WALL = registerWall("warped_wall", WARPED_PLANKS).wood()
    val BAMBOO_WALL = registerWall("bamboo_wall", BAMBOO_PLANKS).wood()
    val BAMBOO_MOSAIC_WALL = registerWall("bamboo_mosaic_wall", BAMBOO_MOSAIC).wood()

    val SPRUCE_BOOKSHELF = register("spruce_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val BIRCH_BOOKSHELF = register("birch_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val JUNGLE_BOOKSHELF = register("jungle_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val ACACIA_BOOKSHELF = register("acacia_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val DARK_OAK_BOOKSHELF = register("dark_oak_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val MANGROVE_BOOKSHELF = register("mangrove_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val CHERRY_BOOKSHELF = register("cherry_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val BAMBOO_BOOKSHELF = register("bamboo_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val CRIMSON_BOOKSHELF = register("crimson_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val WARPED_BOOKSHELF = register("warped_bookshelf", ofFullCopy(BOOKSHELF)).axe()

    val CASCADE_BOOKSHELF = register("cascade_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val SYPIA_BOOKSHELF = register("sypia_bookshelf", ofFullCopy(BOOKSHELF)).axe()
    val VERDANT_BOOKSHELF = register("verdant_bookshelf", ofFullCopy(BOOKSHELF)).axe().grass().tint().cutout()

    // endregion

    // region 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ --- Big Blocks --- 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️

    val BIG_CHAIN = register("big_chain", ::BigChainBlock, ofFullCopy(CHAIN).sound(bigChainSound)).pickaxe().cutout()
    val BIG_LANTERN = register("big_lantern", ::BigLanternBlock, ofFullCopy(LANTERN).sound(bigLanternSound)).pickaxe()
    val BIG_SOUL_LANTERN =
        register("big_soul_lantern", ::BigLanternBlock, ofFullCopy(SOUL_LANTERN).sound(bigLanternSound)).pickaxe()

    val BIG_REDSTONE_LANTERN = register(
        "big_redstone_lantern", ::BigRedstoneLanternBlock, ofFullCopy(BIG_LANTERN).lightLevel(litBlockEmission(8))
    ).pickaxe()

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

    val BIG_SCAFFOLDING = registerNoItem("big_scaffolding", ::BigScaffoldingBlock, ofFullCopy(SCAFFOLDING))
        .cutout().axe()
    // endregion

    // region  🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 --- Rock & Stone --- 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨

    // Infested blocks
    val INFESTED_MOSSY_COBBLESTONE = register(
        "infested_mossy_cobblestone", { InfestedBlock(MOSSY_COBBLESTONE, it) }, Properties.of().mapColor(MapColor.CLAY)
    ).pickaxe()
    val INFESTED_COBBLED_DEEPSLATE = register(
        "infested_cobbled_deepslate", { InfestedBlock(COBBLED_DEEPSLATE, it) }, Prop.INFESTED_DEEPSLATE
    ).pickaxe()
    val INFESTED_DEEPSLATE_BRICKS = register(
        "infested_deepslate_bricks", { InfestedBlock(DEEPSLATE_BRICKS, it) }, Prop.INFESTED_DEEPSLATE
    ).pickaxe()
    val INFESTED_CRACKED_DEEPSLATE_BRICKS = register(
        "infested_cracked_deepslate_bricks", { InfestedBlock(CRACKED_DEEPSLATE_BRICKS, it) }, Prop.INFESTED_DEEPSLATE
    ).pickaxe()
    val INFESTED_DEEPSLATE_TILES = register(
        "infested_deepslate_tiles", { InfestedBlock(DEEPSLATE_TILES, it) }, Prop.INFESTED_DEEPSLATE
    ).pickaxe()
    val INFESTED_CRACKED_DEEPSLATE_TILES = register(
        "infested_cracked_deepslate_tiles", { InfestedBlock(CRACKED_DEEPSLATE_TILES, it) }, Prop.INFESTED_DEEPSLATE
    ).pickaxe()
    val INFESTED_POLISHED_DEEPSLATE = register(
        "infested_polished_deepslate", { InfestedBlock(POLISHED_DEEPSLATE, it) }, Prop.INFESTED_DEEPSLATE
    ).pickaxe()


    val STONE_PILLAR = register("stone_pillar", ::RotatedPillarBlock, ofFullCopy(CHISELED_STONE_BRICKS)).pickaxe()
    val DEEPSLATE_PILLAR =
        register("deepslate_pillar", ::RotatedPillarBlock, ofFullCopy(POLISHED_DEEPSLATE)).pickaxe()

    // Polish
    val POLISHED_STONE = registerSet("polished_stone", ofFullCopy(SMOOTH_STONE)).pickaxe()
    val MOSSY_POLISHED_STONE = registerSet("mossy_polished_stone", copy(POLISHED_STONE)).pickaxe()

    // Overgrown
    val OVERGROWN_POLISHED_STONE = registerSet("overgrown_polished_stone", copy(MOSSY_POLISHED_STONE)).overgrown()
    val OVERGROWN_COBBLESTONE = registerSet("overgrown_cobblestone", ofFullCopy(MOSSY_COBBLESTONE)).overgrown()
    val OVERGROWN_STONE_BRICKS = registerSet("overgrown_stone_brick", ofFullCopy(MOSSY_STONE_BRICKS), "s").overgrown()

    // Bricks
    val BRICK_FENCE = register("brick_fence", ::FenceBlock, ofFullCopy(BRICKS)).pickaxe()
    val CHISELED_BRICKS = register("chiseled_bricks", ofFullCopy(BRICKS)).pickaxe()
    val CRACKED_BRICKS = registerSet("cracked_brick", ofFullCopy(BRICKS), "s").pickaxe()

    // Graves
    val STONE_BRICK_GRAVESTONE = registerGravestone("stone_brick_gravestone", CHISELED_STONE_BRICKS)
    val SMALL_STONE_BRICK_GRAVESTONE = registerSmallGravestone("small_stone_brick_gravestone", STONE_BRICK_GRAVESTONE)
    val DEEPSLATE_BRICK_GRAVESTONE = registerGravestone("deepslate_brick_gravestone", CHISELED_DEEPSLATE)
    val SMALL_DEEPSLATE_BRICK_GRAVESTONE =
        registerSmallGravestone("small_deepslate_brick_gravestone", DEEPSLATE_BRICK_GRAVESTONE)
    val TUFF_BRICK_GRAVESTONE = registerGravestone("tuff_brick_gravestone", CHISELED_TUFF_BRICKS)
    val SMALL_TUFF_BRICK_GRAVESTONE = registerSmallGravestone("small_tuff_brick_gravestone", TUFF_BRICK_GRAVESTONE)
    val BLACKSTONE_BRICK_GRAVESTONE = registerGravestone("blackstone_brick_gravestone", CHISELED_POLISHED_BLACKSTONE)
    val SMALL_BLACKSTONE_BRICK_GRAVESTONE =
        registerSmallGravestone("small_blackstone_brick_gravestone", BLACKSTONE_BRICK_GRAVESTONE)
    val IRON_HEADSTONE = register("iron_headstone", GravestoneBlock::newHeadstone, ofFullCopy(BIG_CHAIN))
        .cutout().pickaxe()
    // endregion

    // region  ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ --- ICE age --- ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄

    val ICE_SET = register(
        createHeadlessSet("ice", ICE)
            .noStoneCutting()
            .stairs(::MeltableStairsBlock)
            .slab(::MeltableSlabBlock)
            .wall(::MeltableWallBlock)
            .buildHeadless()
    ).translucent().pickaxe()
    val PACKED_ICE_SET = registerHeadlessSet("packed_ice", PACKED_ICE).pickaxe()
    val BLUE_ICE_SET = registerHeadlessSet("blue_ice", BLUE_ICE).pickaxe()
    // endregion

    // region 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥  --- Hell ---  🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥

    val NETHERRACK_SET = registerHeadlessSet("netherrack", NETHERRACK).pickaxe()

    val NETHER_BRICK_PILLAR =
        register("nether_brick_pillar", ::RotatedPillarBlock, ofFullCopy(NETHER_BRICKS)).pickaxe()
    val POLISHED_NETHER_BRICKS = registerSet("polished_nether_brick", ofFullCopy(NETHER_BRICKS), "s").pickaxe()

    // Red Nether Bricks
    val POLISHED_RED_NETHER_BRICKS =
        registerSet("polished_red_nether_brick", ofFullCopy(RED_NETHER_BRICKS), "s").pickaxe()

    val CRACKED_RED_NETHER_BRICKS =
        register("cracked_red_nether_bricks", ofFullCopy(CRACKED_NETHER_BRICKS)).pickaxe()
    val RED_NETHER_BRICK_FENCE =
        register("red_nether_brick_fence", ::FenceBlock, ofFullCopy(NETHER_BRICK_FENCE)).pickaxe()
    val CHISELED_RED_NETHER_BRICKS =
        register("chiseled_red_nether_bricks", ofFullCopy(CHISELED_NETHER_BRICKS)).pickaxe()
    val RED_NETHER_BRICK_PILLAR =
        register("red_nether_brick_pillar", ::RotatedPillarBlock, ofFullCopy(RED_NETHER_BRICKS)).pickaxe()

    // Blue Nether Bricks
    val BLUE_NETHER_BRICKS = registerSet("blue_nether_brick", ofFullCopy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_BLUE_NETHER_BRICKS =
        register("cracked_blue_nether_bricks", ofFullCopy(CRACKED_NETHER_BRICKS)).pickaxe()
    val BLUE_NETHER_BRICK_FENCE =
        register("blue_nether_brick_fence", ::FenceBlock, ofFullCopy(NETHER_BRICK_FENCE)).pickaxe()
    val CHISELED_BLUE_NETHER_BRICKS =
        register("chiseled_blue_nether_bricks", ofFullCopy(CHISELED_NETHER_BRICKS)).pickaxe()
    val BLUE_NETHER_BRICK_PILLAR =
        register("blue_nether_brick_pillar", ::RotatedPillarBlock, copy(BLUE_NETHER_BRICKS)).pickaxe()

    val POLISHED_BLUE_NETHER_BRICKS = registerSet("polished_blue_nether_brick", copy(BLUE_NETHER_BRICKS), "s").pickaxe()

    // Gray Nether Bricks
    val GRAY_NETHER_BRICKS = registerSet("gray_nether_brick", ofFullCopy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_GRAY_NETHER_BRICKS =
        register("cracked_gray_nether_bricks", ofFullCopy(CRACKED_NETHER_BRICKS)).pickaxe()
    val GRAY_NETHER_BRICK_FENCE =
        register("gray_nether_brick_fence", ::FenceBlock, ofFullCopy(NETHER_BRICK_FENCE)).pickaxe()
    val CHISELED_GRAY_NETHER_BRICKS =
        register("chiseled_gray_nether_bricks", ofFullCopy(CHISELED_NETHER_BRICKS)).pickaxe()
    val GRAY_NETHER_BRICK_PILLAR =
        register("gray_nether_brick_pillar", ::RotatedPillarBlock, copy(GRAY_NETHER_BRICKS)).pickaxe()

    val POLISHED_GRAY_NETHER_BRICKS = registerSet("polished_gray_nether_brick", copy(GRAY_NETHER_BRICKS), "s").pickaxe()

    val MOLTEN_LAVASPONGE = register(
        "molten_lavasponge", { FilledLavaspongeBlock(it, OBSIDIAN, WATER, LAVA) }, ofFullCopy(BASALT)
    ).pickaxe()
        .tellWitnessesThatIWasMurdered()
    val BRITTLE_LAVASPONGE = registerNoItem(
        "brittle_lavasponge", { LavaSpongeBlock(it, 6, 64, MOLTEN_LAVASPONGE) }, ofFullCopy(BASALT)
    ).pickaxe()
        .tellWitnessesThatIWasMurdered()

    val FUSED_LAVASPONGE = registerNoItem("fused_lavasponge", ::Block, ofFullCopy(OBSIDIAN)).pickaxe()
        .tellWitnessesThatIWasMurdered()
    val GLOWING_LAVASPONGE = registerNoItem(
        "glowing_lavasponge", { FilledLavaspongeBlock(it, FUSED_LAVASPONGE, WATER) }, ofFullCopy(BASALT)
    ).pickaxe()
        .tellWitnessesThatIWasMurdered()
    val LAVASPONGE =
        registerNoItem("lavasponge", { LavaSpongeBlock(it, 10, 256, GLOWING_LAVASPONGE) }, ofFullCopy(BASALT)).pickaxe()
            .tellWitnessesThatIWasMurdered()
    // endregion

    val TINTED_SAND = register(
        "tinted_sand", { ColoredFallingBlock(ColorRGBA(14406560), it) }, ofFullCopy(SAND).mapColor(MapColor.WATER)
    ).tellWitnessesThatIWasMurdered()

    val TINTED_SANDSTONE = register("tinted_sandstone", Prop.TINTED_SANDSTONE).pickaxe().tint()
        .tellWitnessesThatIWasMurdered()
    val CHISELED_TINTED_SANDSTONE = register("chiseled_tinted_sandstone", Prop.TINTED_SANDSTONE).pickaxe().tint()
        .tellWitnessesThatIWasMurdered()
    val CUT_TINTED_SANDSTONE = register("cut_tinted_sandstone", Prop.TINTED_SANDSTONE).pickaxe().tint()
        .tellWitnessesThatIWasMurdered()

    val SUSPICIOUS_RED_SAND = register(
        "suspicious_red_sand",
        { BrushableBlock(RED_SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED, it) },
        Prop.RED_SUS_SAND
    ).shovel()

    val REDSTONE_LANTERN = register("redstone_lantern", ::RedstoneLanternBlock, Prop.RESTONE_LANTERN).pickaxe().cutout()

    val HEAVY_CUBE = register("heavy_cube", ::CompositeBlock, ofFullCopy(HEAVY_CORE).noOcclusion()).pickaxe().cutout()

    val TINTED_GLASS_PANE = register("tinted_glass_pane", ::TintedPaneBlock, ofFullCopy(TINTED_GLASS)).translucent()

    // Carpet Plates
    val WOOL_CARPET_PLATE = register(
        ColorConsortium("carpet_plate", VanillaColorCollections.WOOL) { wool ->
            CarpetPlateBlock(DnDBlockSetTypes.WOOL, ofFullCopy(wool))
        }
    )
    val MOSS_CARPET_PLATE = register(
        "moss_carpet_plate", { CarpetPlateBlock(DnDBlockSetTypes.MOSS, it) }, ofFullCopy(MOSS_CARPET)
    ).hoe().sword()

    val OVERGROWTH_CARPET_PLATE = register(
        "overgrowth_carpet_plate", { CarpetPlateBlock(DnDBlockSetTypes.MOSS, it) }, ofFullCopy(OVERGROWTH_CARPET)
    ).hoe().sword().tint().grass()


    // Polished Sandstone
    val POLISHED_SANDSTONE = registerSet("polished_sandstone", ofFullCopy(CUT_SANDSTONE)).pickaxe()
    val POLISHED_RED_SANDSTONE = registerSet("polished_red_sandstone", ofFullCopy(CUT_RED_SANDSTONE)).pickaxe()

    // Rough Sandstone
    val ROUGH_SANDSTONE = registerSet("rough_sandstone", ofFullCopy(SANDSTONE)).pickaxe()
    val ROUGH_RED_SANDSTONE = registerSet("rough_red_sandstone", ofFullCopy(RED_SANDSTONE)).pickaxe()

    // Smooth Lapis
    val SMOOTH_LAPIS = registerSet("smooth_lapis", ofFullCopy(LAPIS_BLOCK)).pickaxe()

    // Mising Sets
    val SNOW_SET = register(
        createHeadlessSet("snow", SNOW_BLOCK)
            .noStoneCutting()
            .buildHeadless()
    ).shovel()
    val CALCITE_SET = registerHeadlessSet("calcite", CALCITE).pickaxe()
    val DRIPSTONE_SET = registerHeadlessSet("dripstone", DRIPSTONE_BLOCK).pickaxe()
    val END_STONE_SET = registerHeadlessSet("end_stone", END_STONE).pickaxe()
    val SMOOTH_BASALT_SET = registerHeadlessSet("smooth_basalt", SMOOTH_BASALT).pickaxe()
    val OBSIDIAN_SET = registerHeadlessSet("obsidian", OBSIDIAN, ofFullCopy(OBSIDIAN).pushReaction(PushReaction.BLOCK))
        .pickaxe()
    val CRYING_OBSIDIAN_SET = register(
        createHeadlessSet("crying_obsidian", CRYING_OBSIDIAN)
            .settings(ofFullCopy(CRYING_OBSIDIAN).pushReaction(PushReaction.BLOCK))
            .stairs(::CryingStairsBlock)
            .slab(::CryingSlabBlock)
            .wall(::CryingWallBlock)
            .buildHeadless()
    ).pickaxe()

    val PACKED_MUD_SET = registerHeadlessSet("packed_mud", PACKED_MUD).pickaxe()
    val QUARTZ_BRICK_SET = registerHeadlessSet("quartz_brick", QUARTZ_BRICKS).pickaxe()

    // Cracked Sets
    val CRACKED_STONE_BRICK_SET = registerHeadlessSet("cracked_stone_brick", CRACKED_STONE_BRICKS).pickaxe()
    val CRACKED_DEEPSLATE_BRICK_SET = registerHeadlessSet("cracked_deepslate_brick", CRACKED_DEEPSLATE_BRICKS).pickaxe()
    val CRACKED_DEEPSLATE_TILE_SET = registerHeadlessSet("cracked_deepslate_tile", CRACKED_DEEPSLATE_TILES).pickaxe()
    val CRACKED_NETHER_BRICK_SET = registerHeadlessSet("cracked_nether_brick", CRACKED_NETHER_BRICKS).pickaxe()
    val CRACKED_POLISHED_BLACKSTONE_BRICK_SET =
        registerHeadlessSet("cracked_polished_blackstone_brick", CRACKED_POLISHED_BLACKSTONE_BRICKS).pickaxe()

    // Pairs
    val SMOOTH_STONE_STAIR = registerStairs("smooth_stone_stairs", SMOOTH_STONE).pickaxe()
    val SMOOTH_STONE_WALL = registerWall("smooth_stone_wall", SMOOTH_STONE)

    val CUT_SANDSTONE_STAIR = registerStairs("cut_sandstone_stairs", CUT_SANDSTONE).pickaxe()
    val CUT_SANDSTONE_WALL = registerWall("cut_sandstone_wall", CUT_SANDSTONE)
    val CUT_RED_SANDSTONE_STAIR = registerStairs("cut_red_sandstone_stairs", CUT_RED_SANDSTONE).pickaxe()
    val CUT_RED_SANDSTONE_WALL = registerWall("cut_red_sandstone_wall", CUT_RED_SANDSTONE)

    // Walls
    val STONE_WALL = registerWall("stone_wall", STONE)

    val POLISHED_GRANITE_WALL = registerWall("polished_granite_wall", POLISHED_GRANITE)
    val POLISHED_DIORITE_WALL = registerWall("polished_diorite_wall", POLISHED_DIORITE)
    val POLISHED_ANDESITE_WALL = registerWall("polished_andesite_wall", POLISHED_ANDESITE)

    val SMOOTH_SANDSTONE_WALL = registerWall("smooth_sandstone_wall", SMOOTH_SANDSTONE)
    val SMOOTH_RED_SANDSTONE_WALL = registerWall("smooth_red_sandstone_wall", SMOOTH_RED_SANDSTONE)

    val PRISMARINE_BRICKS_WALL = registerWall("prismarine_bricks_wall", PRISMARINE_BRICKS)
    val DARK_PRISMARINE_WALL = registerWall("dark_prismarine_wall", DARK_PRISMARINE)
    val PURPUR_WALL = registerWall("purpur_wall", PURPUR_BLOCK)

    val QUARTZ_WALL = registerWall("quartz_wall", QUARTZ_BLOCK)
    val SMOOTH_QUARTZ_WALL = registerWall("smooth_quartz_wall", SMOOTH_QUARTZ)

    /* Future Content
        val SNOWY_STONE_BRICKS = registerSet("snowy_stone_brick", copy(STONE_BRICKS), "s").pickaxe()
        val ICE_BRICKS = register(createBlockSet("ice_brick", Set.ICE).s().noStoneCutting().parent(::IceBlock).meltable().build()).translucent().pickaxe()
        val PACKED_ICE_BRICKS = registerSet("packed_ice_brick", copy(PACKED_ICE), "s").pickaxe()
        val BLUE_ICE_BRICKS = registerSet("blue_ice_brick", copy(BLUE_ICE), "s").pickaxe()
        // Buttons
        val SMOOTH_STONE_BUTTON = createStoneBtn(Blocks.SMOOTH_STONE)
        val DEEPSLATE_BUTTON = createStoneBtn(Blocks.DEEPSLATE)
        val POLISHED_DEEPSLATE_BUTTON = createStoneBtn(Blocks.POLISHED_DEEPSLATE)
        val BLACKSTONE_BUTTON = createStoneBtn(Blocks.BLACKSTONE)
        // Pressure plates
        val SMOOTH_STONE_PRESSURE_PLATE = createStonePlate(Blocks.SMOOTH_STONE)
        val DEEPSLATE_PRESSURE_PLATE = createStonePlate(Blocks.DEEPSLATE)
        val POLISHED_DEEPSLATE_PRESSURE_PLATE = createStonePlate(Blocks.POLISHED_DEEPSLATE)
        val BLACKSTONE_PRESSURE_PLATE = createStonePlate(Blocks.BLACKSTONE)
    */

    //    🌈 🌈 🌈 🌈 --- GAY BLOCK --- 🌈 🌈 🌈 🌈
    @Suppress("unused")
    val GAY_BLOCK = registerSet("gay_block", ofFullCopy(BEACON))//otherwise known as the Glock

    fun init() {
        // Striping
        StrippableBlockRegistry.register(CASCADE_LOG, STRIPPED_CASCADE_LOG)
        StrippableBlockRegistry.register(CASCADE_WOOD.parent, STRIPPED_CASCADE_WOOD.parent)
        StrippableBlockRegistry.register(SYPIA_LOG, STRIPPED_SYPIA_LOG)
        StrippableBlockRegistry.register(SYPIA_WOOD.parent, STRIPPED_SYPIA_WOOD.parent)
        StrippableBlockRegistry.register(VERDANT_LOG, STRIPPED_VERDANT_LOG)
        StrippableBlockRegistry.register(VERDANT_WOOD.parent, STRIPPED_VERDANT_WOOD.parent)

        registerStrippedSet(CASCADE_WOOD, STRIPPED_CASCADE_WOOD)
        registerStrippedSet(SYPIA_WOOD, STRIPPED_SYPIA_WOOD)
        registerStrippedSet(VERDANT_WOOD, STRIPPED_VERDANT_WOOD)
        registerStrippedSet(OAK_WOOD, STRIPPED_OAK_WOOD)
        registerStrippedSet(SPRUCE_WOOD, STRIPPED_SPRUCE_WOOD)
        registerStrippedSet(BIRCH_WOOD, STRIPPED_BIRCH_WOOD)
        registerStrippedSet(JUNGLE_WOOD, STRIPPED_JUNGLE_WOOD)
        registerStrippedSet(ACACIA_WOOD, STRIPPED_ACACIA_WOOD)
        registerStrippedSet(DARK_OAK_WOOD, STRIPPED_DARK_OAK_WOOD)
        registerStrippedSet(MANGROVE_WOOD, STRIPPED_MANGROVE_WOOD)
        registerStrippedSet(CHERRY_WOOD, STRIPPED_CHERRY_WOOD)
        registerStrippedSet(CRIMSON_HYPHAE, STRIPPED_CRIMSON_HYPHAE)
        registerStrippedSet(WARPED_HYPHAE, STRIPPED_WARPED_HYPHAE)

        BlockStrippingRegistry.register(CASCADE_LOG_PILE, STRIPPED_CASCADE_LOG_PILE)
        BlockStrippingRegistry.register(SYPIA_LOG_PILE, STRIPPED_SYPIA_LOG_PILE)
        BlockStrippingRegistry.register(VERDANT_LOG_PILE, STRIPPED_VERDANT_LOG_PILE)
        BlockStrippingRegistry.register(OAK_LOG_PILE, STRIPPED_OAK_LOG_PILE)
        BlockStrippingRegistry.register(SPRUCE_LOG_PILE, STRIPPED_SPRUCE_LOG_PILE)
        BlockStrippingRegistry.register(BIRCH_LOG_PILE, STRIPPED_BIRCH_LOG_PILE)
        BlockStrippingRegistry.register(JUNGLE_LOG_PILE, STRIPPED_JUNGLE_LOG_PILE)
        BlockStrippingRegistry.register(ACACIA_LOG_PILE, STRIPPED_ACACIA_LOG_PILE)
        BlockStrippingRegistry.register(DARK_OAK_LOG_PILE, STRIPPED_DARK_OAK_LOG_PILE)
        BlockStrippingRegistry.register(MANGROVE_LOG_PILE, STRIPPED_MANGROVE_LOG_PILE)
        BlockStrippingRegistry.register(CHERRY_LOG_PILE, STRIPPED_CHERRY_LOG_PILE)
        BlockStrippingRegistry.register(CRIMSON_STEM_PILE, STRIPPED_CRIMSON_STEM_PILE)
        BlockStrippingRegistry.register(WARPED_STEM_PILE, STRIPPED_WARPED_STEM_PILE)
        BlockStrippingRegistry.register(BAMBOO_PILE, STRIPPED_BAMBOO_PILE)

        // Flammability
        FlammableBlockRegistry.getDefaultInstance().add(DnDBlockTags.FLAMMABLE_PLANKS, 5, 20)
        FlammableBlockRegistry.getDefaultInstance().add(DnDBlockTags.FLAMMABLE_LOGS, 5, 5)
        FlammableBlockRegistry.getDefaultInstance().add(DnDBlockTags.FLAMMABLE_LEAVES, 30, 60)
        FlammableBlockRegistry.getDefaultInstance().add(DnDBlockTags.BOOKSHELVES_THAT_BURN, 30, 20)

    }

    fun registerStrippedSet(set: AbstractBlockSet, strippedSet: AbstractBlockSet) {
        BlockStrippingRegistry.register(set.stairs, strippedSet.stairs)
        BlockStrippingRegistry.register(set.slab, strippedSet.slab)
        BlockStrippingRegistry.register(set.wall, strippedSet.wall)
    }

    // TODO(1.0) make this use 21.11 rules
    fun <T : Block> registerOld(id: String, block: T): T {
        return register(id, { block }, Properties.of())
    }

    // TODO(1.0) make this use 21.11 rules
    fun <T : Block> registerNoItemOld(name: String, block: T): T {
        return registerNoItem(name, { block }, Properties.of())
    }

    fun register(id: String, properties: Properties): Block = register(id, ::Block, properties)

    fun <T : Block> register(id: String, block: Function<Properties, T>, properties: Properties): T {
        val regBlock = registerNoItem(id, block, properties)
        DnDItems.register(id, { BlockItem(regBlock, it) })
        return regBlock
    }

    fun <T : Block> registerNoItem(name: String, block: Function<Properties, T>, properties: Properties): T {
        val id = Registries.BLOCK.key(id(name))
        ensureUnique(id, BuiltInRegistries.BLOCK)
//        properties.setId(id) // 1.21.11 code
        return BuiltInRegistries.BLOCK.register(id.location(), block.apply(properties))
    }

}