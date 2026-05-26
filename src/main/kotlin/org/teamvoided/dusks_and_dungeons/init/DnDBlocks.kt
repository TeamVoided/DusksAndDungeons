package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.core.Registry
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.worldgen.features.TreeFeatures
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.Blocks.*
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.material.MapColor
import org.teamvoided.dusk_debris.block.OvergrowthBlock
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.api.BlockStrippingRegistry
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.block.MoonberryVineBlock.Companion.moonberryLuminance
import org.teamvoided.dusks_and_dungeons.block.big.BigChainBlock
import org.teamvoided.dusks_and_dungeons.block.big.BigLanternBlock
import org.teamvoided.dusks_and_dungeons.block.collections.RockyBlocks
import org.teamvoided.dusks_and_dungeons.block.sapling.SaplingGenerators
import org.teamvoided.dusks_and_dungeons.block.sapling.ThreeWideTreeSaplingBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.voidlib.consortium.block.color.ColorConsortium
import org.teamvoided.voidlib.consortium.block.color.VanillaColorCollections.CANDLES
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.set.createBlockSet
import org.teamvoided.voidlib.consortium.block.set.createHeadlessSet
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockSettings as Set


@Suppress("LargeClass", "TooManyFunctions", "MemberVisibilityCanBePrivate", "unused")
object DnDBlocks {
    val BLOCKS = mutableSetOf<Block>()
    val BLOCK_ITEMS = mutableMapOf<String, Item>()

    // Collections
    val SETS = mutableSetOf<AbstractBlockSet>()
    val COLOR_CONSORTIUM = mutableSetOf<ColorConsortium<*>>()
    val OVERLAYS = mutableSetOf<RockyBlocks>()


    val EVIL_BLOCKS = mutableSetOf<Block>()

    init { //  Pre Block Init
        DnDWoodTypes.init()
    }


    // region 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄 --- Flora --- 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄 🎄

    val WARPED_WART = register("warped_wart", WarpedNetherWartBlock(Set.WARPED_WART).grassLike())

    // Overgrowth
    val OVERGROWTH_BLOCK = register("overgrowth_block", OvergrowthBlock(ofFullCopy(MOSS_BLOCK))).grass().hoe()
        //overgrowth carpet (DELETE THIS CLASS WHEN PORTING TO FUTURE VERSIONS, USE PALE MOSS CARPET CLASS)
    val OVERGROWTH_BUSH = register("overgrowth_bush", OvergrowthBushBlock(ofFullCopy(AZALEA))).cutout().grass()
    val OVERGROWTH_LEAVES = register("overgrowth_leaves", LeavesBlock(ofFullCopy(AZALEA_LEAVES))).cutout().grass().hoe()
        //hanging overgrowth
        //overgrowth (covering) (also use this block class for the overlay replacements, may also want to make a moss and pale moss variant of this)

    // Petals
    val WHITE_PETALS = register("white_petals", PinkPetalsBlock(Set.petals(MapColor.SNOW)).plant())
    val RED_PETALS = register("red_petals", PinkPetalsBlock(Set.petals(MapColor.COLOR_RED)).plant())
    val ORANGE_PETALS = register("orange_petals", PinkPetalsBlock(Set.petals(MapColor.COLOR_ORANGE)).plant())
    val BLUE_PETALS = register("blue_petals", PinkPetalsBlock(Set.petals(MapColor.COLOR_BLUE)).plant())
    val COLD_WILDFLOWER = register("cold_wildflower", PinkPetalsBlock(Set.petals(MapColor.COLOR_PURPLE)).plant())
    val CRIMSON_VIVIONS = register("crimson_vivions", VivionbedBlock(Set.vivions(MapColor.COLOR_RED)).plant())
    val WARPED_VIVIONS = register("warped_vivions", VivionbedBlock(Set.vivions(MapColor.WARPED_WART_BLOCK)).plant())

    // Smol Punkin
    val SMALL_CARVED_PUMPKIN = registerHeadEquipable("small_carved_pumpkin", sCarvedPumpkinOf(CARVED_PUMPKIN).axe())
    val SMALL_GLOWING_PUMPKIN = register("small_jack_o_lantern", sGlowingPumpkinOf(SMALL_CARVED_PUMPKIN).axe())
    val SMALL_PUMPKIN = register("small_pumpkin", sPumpkinOf(SMALL_CARVED_PUMPKIN).axe())

    // Lantern ---
    val CARVED_LANTERN_PUMPKIN =
        registerHeadEquipable("carved_lantern_pumpkin", carvedPumpkin(MapColor.COLOR_YELLOW).axe())
    val GLOWING_LANTERN_PUMPKIN = register("glowing_lantern_pumpkin", glowingPumpkinOf(CARVED_LANTERN_PUMPKIN).axe())
    val LANTERN_PUMPKIN = register("lantern_pumpkin", pumpkinOf(CARVED_LANTERN_PUMPKIN).axe())
    val SMALL_CARVED_LANTERN_PUMPKIN =
        registerHeadEquipable("small_carved_lantern_pumpkin", sCarvedPumpkinOf(CARVED_LANTERN_PUMPKIN).axe())
    val SMALL_GLOWING_LANTERN_PUMPKIN =
        register("small_glowing_lantern_pumpkin", sGlowingPumpkinOf(SMALL_CARVED_LANTERN_PUMPKIN).axe())
    val SMALL_LANTERN_PUMPKIN = register("small_lantern_pumpkin", sPumpkinOf(SMALL_CARVED_LANTERN_PUMPKIN).axe())
    val LANTERN_PUMPKIN_STEM = registerNoItem("lantern_pumpkin_stem", stemOf(LANTERN_PUMPKIN).grassLike())

    // Mosskin ---
    val CARVED_MOSSKIN_PUMPKIN =
        registerHeadEquipable("carved_mosskin_pumpkin", carvedPumpkin(MapColor.COLOR_GREEN).axe())
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
        registerHeadEquipable("carved_gloom_pumpkin", carvedPumpkin(MapColor.TERRACOTTA_PURPLE).axe())
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
    val CORN_CROP = registerNoItem("corn_crop", CornCropBlock(Set.corn().randomTicks()).grassLike())
    val CORN = registerNoItem("corn", CornMazeBlock(Set.corn().offsetType(OffsetType.XYZ)).grassLike())
    val CORN_BLOCK = register("corn_block", RotatedPillarBlock(ofFullCopy(CHERRY_PLANKS).mapColor(MapColor.GOLD)).axe())

    @JvmField
    val CORN_SYRUP_BLOCK = register("corn_syrup_block", CornSyrupBlock(Set.CORN_SYRUP)).translucent()

    // The Rest
    val ROOT_BLOCK = register("root_block", MangroveRootsBlock(Set.ROOT_BLOCK).grassLike().flammableLeaves())
    val WILD_WHEAT = registerNoItem("wild_wheat", TallSpreadableBlock(Set.WILD_WHEAT).grassLike())
    val GOLDEN_BEETROOTS = registerNoItem("golden_beetroots", GoldenBeetrootsBlock(Set.GOLDEN_BEETROOT).grassLike())
    val MOONBERRY_VINE = register(
        "moonberry_vine", MoonberryVineBlock(Set.moonbery().moonberryLuminance(8, 11))
    ).grassLike().flammableLogs()
    val MOONBERRY_VINELET = registerNoItem(
        "moonberry_vinelet", MoonberryVineletBlock(Set.moonbery().randomTicks().instabreak())
    ).grassLike().flammableLogs()

    val GOLDEN_MUSHROOM = register(
        "golden_mushroom",
        MushroomWithSporesPlantBlock(TreeFeatures.HUGE_BROWN_MUSHROOM, 0xFFD800, 0.5, Set.GOLDEN_MUSHROOM)
    ).cutout().axe().sword()
        .tellWitnessesThatIWasMurdered()
    val GOLDEN_MUSHROOM_BLOCK = register(
        "golden_mushroom_block", MushroomWithSporesBlock(0xFFD800, 0.5, Set.GOLDEN_MUSHROOM_BLOCK.luminance(11))
    ).axe()
        .tellWitnessesThatIWasMurdered()
    val GOLDEN_MUSHROOM_STEM_BLOCK =
        register("golden_mushroom_stem_block", HugeMushroomBlock(Set.GOLDEN_MUSHROOM_BLOCK.luminance(9))).axe()
            .tellWitnessesThatIWasMurdered()

    // endregion

    // region 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳 --- Sold Oxygen --- 🌳 🌳 🌳 🌳 🌳 🌳 🌳 🌳
    val CASCADE_SAPLING = register(
        "cascade_sapling", ThreeWideTreeSaplingBlock(SaplingGenerators.CASCADE, Set.CASCADE_SAPLING)
    ).cutout()
    val POTTED_CASCADE_SAPLING = registerNoItem("potted_cascade_sapling", flowerPot(CASCADE_SAPLING)).cutout()
    val CASCADE_LEAVES = register(
        "cascade_leaves", FallingLeavesBlock(DnDParticles.CASCADE_LEAF_PARTICLE, Set.CASCADE_LEAVES)
    ).leaves()
    val CASCADE_LEAF_PILE = register(
        "cascade_leaf_pile",
        fallingLeafPile(DnDParticles.CASCADE_LEAF_PARTICLE, MapColor.COLOR_RED, SoundType.AZALEA_LEAVES)
    ).cutout()
    val CASCADE_LOG = register("cascade_log", log(MapColor.COLOR_BLUE, MapColor.COLOR_BROWN, SoundType.CHERRY_WOOD))
    val CASCADE_WOOD =
        register(createBlockSet("cascade_wood", Set.CASCADE_WOOD).noStoneCutting().parent(::RotatedPillarBlock).build())
            .woodSet()

    val CASCADE_LOG_PILE = register("cascade_log_pile", logPile(CASCADE_WOOD.parent))
    val STRIPPED_CASCADE_LOG =
        register("stripped_cascade_log", log(MapColor.COLOR_BLUE, MapColor.COLOR_BLUE, SoundType.CHERRY_WOOD))
    val STRIPPED_CASCADE_WOOD = register(
        createBlockSet("stripped_cascade_wood", Properties.of().mapColor(MapColor.COLOR_BLUE)).noStoneCutting()
            .parent(::RotatedPillarBlock).build()
    ).woodSet()
    val STRIPPED_CASCADE_LOG_PILE = register("stripped_cascade_log_pile", logPile(STRIPPED_CASCADE_WOOD.parent))

    val CASCADE_PLANKS = register("cascade_planks", Block(Set.CASCADE_PLANKS)).flammablePlanks()
    val CASCADE_STAIRS = register("cascade_stairs", stairsOf(CASCADE_PLANKS).wood())
    val CASCADE_SLAB = register("cascade_slab", slabOf(CASCADE_PLANKS).wood())
    val CASCADE_WALL = register("cascade_plank_wall", wallOf(CASCADE_PLANKS).wood())
    val CASCADE_FENCE = register("cascade_fence", fenceOf(CASCADE_PLANKS).wood())
    val CASCADE_FENCE_GATE =
        register("cascade_fence_gate", fenceGateOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS).wood())
    val CASCADE_DOOR =
        registerNoItem("cascade_door", doorOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS).wood())
    val BLUE_DOOR = registerNoItem("blue_door", DoorBlock(BlockSetType.DARK_OAK, Set.BLUE_DOOR).wood())
    val CASCADE_TRAPDOOR =
        register("cascade_trapdoor", trapdoorOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS).wood())
    val CASCADE_PRESSURE_PLATE =
        register("cascade_pressure_plate", pressurePlateOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS).wood())
    val CASCADE_BUTTON = register("cascade_button", woodenButton(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE).wood())
    val CASCADE_SIGN = registerNoItem("cascade_sign", signOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS).wood())
    val CASCADE_WALL_SIGN = registerNoItem(
        "cascade_wall_sign", wallSignOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS, CASCADE_SIGN).wood()
    )
    val CASCADE_HANGING_SIGN =
        registerNoItem("cascade_hanging_sign", hangingSignOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS).wood())
    val CASCADE_WALL_HANGING_SIGN = registerNoItem(
        "cascade_wall_hanging_sign",
        wallHangingSignOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS, CASCADE_HANGING_SIGN).wood()
    )

    val GOLDEN_BIRCH_SAPLING = register(
        "golden_birch_sapling",
        SaplingBlock(SaplingGenerators.GOLDEN_BIRCH, ofFullCopy(BIRCH_SAPLING).mapColor(MapColor.COLOR_YELLOW)).cutout()
    )
    val POTTED_GOLDEN_BIRCH_SAPLING =
        registerNoItem("potted_golden_birch_sapling", flowerPot(GOLDEN_BIRCH_SAPLING).cutout())
    val GOLDEN_BIRCH_LEAVES = register(
        "golden_birch_leaves", LeavesBlock(ofFullCopy(BIRCH_LEAVES).mapColor(MapColor.COLOR_YELLOW)).leaves()
    )
    val GOLDEN_BIRCH_LEAF_PILE = register("golden_birch_leaf_pile", leafPile(MapColor.COLOR_YELLOW).cutout())

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
            .settings(ofFullCopy(Blocks.STRIPPED_MANGROVE_WOOD).mapColor(MapColor.COLOR_RED)).noStoneCutting()
            .buildHeadless()
    ).woodSet()
    val STRIPPED_CHERRY_WOOD = registerWoodenSet("stripped_cherry_wood", Blocks.STRIPPED_CHERRY_WOOD)
    val STRIPPED_CRIMSON_HYPHAE = registerWoodenSet("stripped_crimson_hyphae", Blocks.STRIPPED_CRIMSON_HYPHAE)
    val STRIPPED_WARPED_HYPHAE = registerWoodenSet("stripped_warped_hyphae", Blocks.STRIPPED_WARPED_HYPHAE)

    val OAK_WALL = register("oak_wall", wallOf(OAK_PLANKS).wood())
    val SPRUCE_WALL = register("spruce_wall", wallOf(SPRUCE_PLANKS).wood())
    val BIRCH_WALL = register("birch_wall", wallOf(BIRCH_PLANKS).wood())
    val JUNGLE_WALL = register("jungle_wall", wallOf(JUNGLE_PLANKS).wood())
    val ACACIA_WALL = register("acacia_wall", wallOf(ACACIA_PLANKS).wood())
    val DARK_OAK_WALL = register("dark_oak_wall", wallOf(DARK_OAK_PLANKS).wood())
    val MANGROVE_WALL = register("mangrove_wall", wallOf(MANGROVE_PLANKS).wood())
    val CHERRY_WALL = register("cherry_wall", wallOf(CHERRY_PLANKS).wood())
    val CRIMSON_WALL = register("crimson_wall", wallOf(CRIMSON_PLANKS).wood())
    val WARPED_WALL = register("warped_wall", wallOf(WARPED_PLANKS).wood())
    val BAMBOO_WALL = register("bamboo_wall", wallOf(BAMBOO_PLANKS).wood())
    val BAMBOO_MOSAIC_WALL = register("bamboo_mosaic_wall", wallOf(BAMBOO_MOSAIC).wood())

    //(ender) Uses wood because logs have diff map colors based on if top is showing
    val OAK_LOG_PILE = register("oak_log_pile", logPile(Blocks.OAK_WOOD))
    val SPRUCE_LOG_PILE = register("spruce_log_pile", logPile(Blocks.SPRUCE_WOOD))
    val BIRCH_LOG_PILE = register("birch_log_pile", logPile(Blocks.BIRCH_WOOD))
    val JUNGLE_LOG_PILE = register("jungle_log_pile", logPile(Blocks.JUNGLE_WOOD))
    val ACACIA_LOG_PILE = register("acacia_log_pile", logPile(Blocks.ACACIA_WOOD))
    val DARK_OAK_LOG_PILE = register("dark_oak_log_pile", logPile(Blocks.DARK_OAK_WOOD))
    val MANGROVE_LOG_PILE = register("mangrove_log_pile", logPile(Blocks.MANGROVE_WOOD))
    val CHERRY_LOG_PILE = register("cherry_log_pile", logPile(Blocks.CHERRY_WOOD))
    val CRIMSON_STEM_PILE = register("crimson_stem_pile", logPile(Blocks.CRIMSON_HYPHAE))
    val WARPED_STEM_PILE = register("warped_stem_pile", logPile(Blocks.WARPED_HYPHAE))
    val BAMBOO_PILE = register("bamboo_pile", logPile(BAMBOO_PLANKS, MapColor.PLANT))

    val STRIPPED_OAK_LOG_PILE = register("stripped_oak_log_pile", logPile(Blocks.STRIPPED_OAK_WOOD))
    val STRIPPED_SPRUCE_LOG_PILE = register("stripped_spruce_log_pile", logPile(Blocks.STRIPPED_SPRUCE_WOOD))
    val STRIPPED_BIRCH_LOG_PILE = register("stripped_birch_log_pile", logPile(Blocks.STRIPPED_BIRCH_WOOD))
    val STRIPPED_JUNGLE_LOG_PILE = register("stripped_jungle_log_pile", logPile(Blocks.STRIPPED_JUNGLE_WOOD))
    val STRIPPED_ACACIA_LOG_PILE = register("stripped_acacia_log_pile", logPile(Blocks.STRIPPED_ACACIA_WOOD))
    val STRIPPED_DARK_OAK_LOG_PILE = register("stripped_dark_oak_log_pile", logPile(Blocks.STRIPPED_DARK_OAK_WOOD))
    val STRIPPED_MANGROVE_LOG_PILE =
        register("stripped_mangrove_log_pile", logPile(Blocks.STRIPPED_MANGROVE_WOOD, MapColor.COLOR_RED))
    val STRIPPED_CHERRY_LOG_PILE = register("stripped_cherry_log_pile", logPile(Blocks.STRIPPED_CHERRY_WOOD))
    val STRIPPED_CRIMSON_STEM_PILE = register("stripped_crimson_stem_pile", logPile(Blocks.STRIPPED_CRIMSON_HYPHAE))
    val STRIPPED_WARPED_STEM_PILE = register("stripped_warped_stem_pile", logPile(Blocks.STRIPPED_WARPED_HYPHAE))
    val STRIPPED_BAMBOO_PILE = register("stripped_bamboo_pile", logPile(BAMBOO_PLANKS))

    val OAK_LEAF_PILE = register("oak_leaf_pile", leafPile().cutout())
    val SPRUCE_LEAF_PILE = register("spruce_leaf_pile", leafPile().cutout())
    val BIRCH_LEAF_PILE = register("birch_leaf_pile", leafPile().cutout())
    val JUNGLE_LEAF_PILE = register("jungle_leaf_pile", leafPile().cutout())
    val ACACIA_LEAF_PILE = register("acacia_leaf_pile", leafPile().cutout())
    val DARK_OAK_LEAF_PILE = register("dark_oak_leaf_pile", leafPile().cutout())
    val MANGROVE_LEAF_PILE = register("mangrove_leaf_pile", leafPile().cutout())
    val CHERRY_LEAF_PILE = register(
        "cherry_leaf_pile",
        fallingLeafPile(ParticleTypes.CHERRY_LEAVES, MapColor.COLOR_PINK, SoundType.CHERRY_LEAVES).cutout()
    )
    val AZALEA_LEAF_PILE = register("azalea_leaf_pile", leafPile(SoundType.AZALEA_LEAVES).cutout())
    val FLOWERING_AZALEA_LEAF_PILE = register(
        "flowering_azalea_leaf_pile", leafPile(SoundType.AZALEA_LEAVES).cutout()
    )

    // TODO finish this before release
    val HOLLOW_OAK_LOG = register("hollow_oak_log", hollowLog(OAK_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_OAK_LOG =
        register("hollow_stripped_oak_log", hollowLog(STRIPPED_OAK_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_SPRUCE_LOG = register("hollow_spruce_log", hollowLog(SPRUCE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_SPRUCE_LOG =
        register("hollow_stripped_spruce_log", hollowLog(STRIPPED_SPRUCE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_BIRCH_LOG =
        register("hollow_birch_log", hollowLog(BIRCH_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_BIRCH_LOG =
        register("hollow_stripped_birch_log", hollowLog(STRIPPED_BIRCH_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_JUNGLE_LOG =
        register("hollow_jungle_log", hollowLog(JUNGLE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_JUNGLE_LOG =
        register("hollow_stripped_jungle_log", hollowLog(STRIPPED_JUNGLE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_ACACIA_LOG =
        register("hollow_acacia_log", hollowLog(ACACIA_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_ACACIA_LOG =
        register("hollow_stripped_acacia_log", hollowLog(STRIPPED_ACACIA_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_DARK_OAK_LOG =
        register("hollow_dark_oak_log", hollowLog(DARK_OAK_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_DARK_OAK_LOG =
        register("hollow_stripped_dark_oak_log", hollowLog(STRIPPED_DARK_OAK_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_MANGROVE_LOG =
        register("hollow_mangrove_log", hollowLog(MANGROVE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_MANGROVE_LOG =
        register("hollow_stripped_mangrove_log", hollowLog(STRIPPED_MANGROVE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_CHERRY_LOG =
        register("hollow_cherry_log", hollowLog(CHERRY_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_CHERRY_LOG =
        register("hollow_stripped_cherry_log", hollowLog(STRIPPED_CHERRY_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_BAMBOO_BLOCK =
        register("hollow_bamboo_block", hollowBambooBlock(BAMBOO_BLOCK)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_BAMBOO_BLOCK =
        register("hollow_stripped_bamboo_block", hollowBambooBlock(STRIPPED_BAMBOO_BLOCK))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_CRIMSON_STEM =
        register("hollow_crimson_stem", hollowLog(Blocks.CRIMSON_HYPHAE)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_CRIMSON_STEM =
        register(
            "hollow_stripped_crimson_stem",
            hollowLog(Blocks.STRIPPED_CRIMSON_HYPHAE)
        ).tellWitnessesThatIWasMurdered()
    val HOLLOW_WARPED_STEM =
        register("hollow_warped_stem", hollowLog(Blocks.WARPED_HYPHAE)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_WARPED_STEM =
        register(
            "hollow_stripped_warped_stem",
            hollowLog(Blocks.STRIPPED_WARPED_HYPHAE)
        ).tellWitnessesThatIWasMurdered()

    val HOLLOW_CASCADE_LOG = register("hollow_cascade_log", hollowLog(CASCADE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_CASCADE_LOG =
        register("hollow_stripped_cascade_log", hollowLog(STRIPPED_CASCADE_LOG)).tellWitnessesThatIWasMurdered()
    // endregion

    // region 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ --- Big Blocks --- 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️ 🕯️

    val BIG_CHAIN = register("big_chain", BigChainBlock(ofFullCopy(CHAIN).sound(bigChainSound)).cutout().pickaxe())
    val BIG_LANTERN = register("big_lantern", BigLanternBlock(ofFullCopy(LANTERN).sound(bigLanternSound)).pickaxe())
    val BIG_SOUL_LANTERN =
        register("big_soul_lantern", BigLanternBlock(ofFullCopy(SOUL_LANTERN).sound(bigLanternSound)).pickaxe())

    //TODO Move this to Variance
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

    val BIG_SCAFFOLDING = registerNoItem("big_scaffolding", BigScaffoldingBlock(ofFullCopy(SCAFFOLDING))).cutout().axe()
    // endregion

    // region  🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 --- Rock & Stone --- 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨

    val STONE_PILLAR = register("stone_pillar", RotatedPillarBlock(ofFullCopy(CHISELED_STONE_BRICKS))).pickaxe()
    val DEEPSLATE_PILLAR = register("deepslate_pillar", RotatedPillarBlock(ofFullCopy(POLISHED_DEEPSLATE))).pickaxe()

    // Polish
    val POLISHED_STONE = registerSet("polished_stone", ofFullCopy(SMOOTH_STONE)).pickaxe()
    val MOSSY_POLISHED_STONE = registerSet("mossy_polished_stone", copy(POLISHED_STONE)).pickaxe()

    // Overgrown
    val OVERGROWN_POLISHED_STONE = registerSet("overgrown_polished_stone", copy(MOSSY_POLISHED_STONE)).overgrown()
    val OVERGROWN_COBBLESTONE = registerSet("overgrown_cobblestone", ofFullCopy(MOSSY_COBBLESTONE)).overgrown()
    val OVERGROWN_STONE_BRICKS = registerSet("overgrown_stone_brick", ofFullCopy(MOSSY_STONE_BRICKS), "s").overgrown()

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
        register(
            "headstone",
            GravestoneBlock(headstoneShape, centerHeadstoneShape, ofFullCopy(BIG_CHAIN)).cutout().pickaxe()
        )
    // endregion

    // region  ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ --- ICE age --- ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄

    val ICE_SET = register(
        createHeadlessSet("ice", ICE).noStoneCutting().meltable().buildHeadless()
    ).translucent().pickaxe()
    val PACKED_ICE_SET = registerHeadlessSet("packed_ice", PACKED_ICE).pickaxe()
    val BLUE_ICE_SET = registerHeadlessSet("blue_ice", BLUE_ICE).pickaxe()
    // endregion

    // region 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥  --- Hell ---  🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥

    val NETHERRACK_SET = registerHeadlessSet("netherrack", NETHERRACK).pickaxe()

    val NETHER_BRICK_PILLAR = register("nether_brick_pillar", RotatedPillarBlock(ofFullCopy(NETHER_BRICKS)).pickaxe())
    val POLISHED_NETHER_BRICKS = registerSet("polished_nether_brick", ofFullCopy(NETHER_BRICKS), "s").pickaxe()

    // Red Nether Bricks
    val POLISHED_RED_NETHER_BRICKS =
        registerSet("polished_red_nether_brick", ofFullCopy(RED_NETHER_BRICKS), "s").pickaxe()

    val CRACKED_RED_NETHER_BRICKS =
        register("cracked_red_nether_bricks", Block(ofFullCopy(CRACKED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_FENCE =
        register("red_nether_brick_fence", FenceBlock(ofFullCopy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_RED_NETHER_BRICKS =
        register("chiseled_red_nether_bricks", Block(ofFullCopy(CHISELED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_PILLAR =
        register("red_nether_brick_pillar", RotatedPillarBlock(ofFullCopy(RED_NETHER_BRICKS)).pickaxe())

    val MIXED_RED_NETHER_BRICKS = registerSet("mixed_red_nether_brick", ofFullCopy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_RED_NETHER_BRICKS =
        register("cracked_mixed_red_nether_bricks", Block(ofFullCopy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_RED_NETHER_BRICK_FENCE =
        register("mixed_red_nether_brick_fence", FenceBlock(ofFullCopy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_RED_NETHER_BRICKS =
        register("chiseled_mixed_red_nether_bricks", Block(ofFullCopy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_RED_NETHER_BRICK_PILLAR =
        register("mixed_red_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_RED_NETHER_BRICKS)).pickaxe())

    // Blue Nether Bricks
    val BLUE_NETHER_BRICKS = registerSet("blue_nether_brick", ofFullCopy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_BLUE_NETHER_BRICKS =
        register("cracked_blue_nether_bricks", Block(ofFullCopy(CRACKED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_FENCE =
        register("blue_nether_brick_fence", FenceBlock(ofFullCopy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_BLUE_NETHER_BRICKS =
        register("chiseled_blue_nether_bricks", Block(ofFullCopy(CHISELED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_PILLAR =
        register("blue_nether_brick_pillar", RotatedPillarBlock(copy(BLUE_NETHER_BRICKS)).pickaxe())

    val POLISHED_BLUE_NETHER_BRICKS = registerSet("polished_blue_nether_brick", copy(BLUE_NETHER_BRICKS), "s").pickaxe()

    val MIXED_BLUE_NETHER_BRICKS = registerSet("mixed_blue_nether_brick", ofFullCopy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_BLUE_NETHER_BRICKS =
        register("cracked_mixed_blue_nether_bricks", Block(ofFullCopy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_FENCE =
        register("mixed_blue_nether_brick_fence", FenceBlock(ofFullCopy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_BLUE_NETHER_BRICKS =
        register("chiseled_mixed_blue_nether_bricks", Block(ofFullCopy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_PILLAR = register(
        "mixed_blue_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_BLUE_NETHER_BRICKS)).pickaxe()
    )

    // Gray Nether Bricks
    val GRAY_NETHER_BRICKS = registerSet("gray_nether_brick", ofFullCopy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_GRAY_NETHER_BRICKS =
        register("cracked_gray_nether_bricks", Block(ofFullCopy(CRACKED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_FENCE =
        register("gray_nether_brick_fence", FenceBlock(ofFullCopy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_GRAY_NETHER_BRICKS =
        register("chiseled_gray_nether_bricks", Block(ofFullCopy(CHISELED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_PILLAR =
        register("gray_nether_brick_pillar", RotatedPillarBlock(copy(GRAY_NETHER_BRICKS)).pickaxe())

    val POLISHED_GRAY_NETHER_BRICKS = registerSet("polished_gray_nether_brick", copy(GRAY_NETHER_BRICKS), "s").pickaxe()

    val MIXED_GRAY_NETHER_BRICKS = registerSet("mixed_gray_nether_brick", ofFullCopy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_GRAY_NETHER_BRICKS =
        register("cracked_mixed_gray_nether_bricks", Block(ofFullCopy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_FENCE =
        register("mixed_gray_nether_brick_fence", FenceBlock(ofFullCopy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_GRAY_NETHER_BRICKS =
        register("chiseled_mixed_gray_nether_bricks", Block(ofFullCopy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_PILLAR = register(
        "mixed_gray_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_GRAY_NETHER_BRICKS)).pickaxe()
    )
    // endregion

    // region  🌿 🌿 🌿 🌿 🌿 🌿  --- Grass Is Greener Here ---  🌿 🌿 🌿 🌿 🌿

    val ROCKY_BLOCKS = register(RockyBlocks("rocks", "rocky", COBBLESTONE)).rocky()
    val SLATE_BLOCKS = register(RockyBlocks("slate", "slated", COBBLED_DEEPSLATE)).rocky()
    val BLACKSTONE_BLOCKS = register(RockyBlocks("blackstone", "blackstoned", BLACKSTONE)).rocky()
    // endregion

    val MOLTEN_LAVASPONGE = register("molten_lavasponge", BreakTransformationBlock(ofFullCopy(BASALT), LAVA)).pickaxe()
        .tellWitnessesThatIWasMurdered()
    val BRITTLE_LAVASPONGE =
        register("brittle_lavasponge", LavaSpongeBlock(ofFullCopy(BASALT), 3, 32, MOLTEN_LAVASPONGE)).pickaxe()
            .tellWitnessesThatIWasMurdered()

    val FUSED_LAVASPONGE = register("fused_lavasponge", Block(ofFullCopy(BASALT))).pickaxe()
        .tellWitnessesThatIWasMurdered()
    val GLOWING_LAVASPONGE = registerNoItem(
        "glowing_lavasponge",
        ContactTransformationBlock(ofFullCopy(BASALT), FUSED_LAVASPONGE, WATER)
    ).pickaxe()
        .tellWitnessesThatIWasMurdered()
    val LAVASPONGE = register("lavasponge", LavaSpongeBlock(ofFullCopy(BASALT), 6, 64, GLOWING_LAVASPONGE)).pickaxe()
        .tellWitnessesThatIWasMurdered()

    /* Future Content
        val SNOWY_STONE_BRICKS = registerSet("snowy_stone_brick", copy(STONE_BRICKS), "s").pickaxe()
        val ICE_BRICKS = register(createBlockSet("ice_brick", Set.ICE).s().noStoneCutting().parent(::IceBlock).meltable().build()).translucent().pickaxe()
        val PACKED_ICE_BRICKS = registerSet("packed_ice_brick", copy(PACKED_ICE), "s").pickaxe()
        val BLUE_ICE_BRICKS = registerSet("blue_ice_brick", copy(BLUE_ICE), "s").pickaxe()
    */

    //    🌈 🌈 🌈 🌈 --- GAY BLOCK --- 🌈 🌈 🌈 🌈
    val GAY_BLOCK = registerSet("gay_block", ofFullCopy(BEACON))

    fun init() {
        // Striping
        StrippableBlockRegistry.register(CASCADE_LOG, STRIPPED_CASCADE_LOG)
        StrippableBlockRegistry.register(CASCADE_WOOD.parent, STRIPPED_CASCADE_WOOD.parent)

        registerStrippedSet(CASCADE_WOOD, STRIPPED_CASCADE_WOOD)
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
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_PLANKS, 5, 20)
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LOGS, 5, 5)
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LEAVES, 30, 60)
        // Misc
        OVERLAYS.forEach {
            it.grass.grass()
            it.init()
        }
    }

    fun registerStrippedSet(set: AbstractBlockSet, strippedSet: AbstractBlockSet) {
        BlockStrippingRegistry.register(set.stairs, strippedSet.stairs)
        BlockStrippingRegistry.register(set.slab, strippedSet.slab)
        BlockStrippingRegistry.register(set.wall, strippedSet.wall)
    }

    fun register(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        DnDItems.register(id, BlockItem(regBlock, Item.Properties()))
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block {
        val regBlock = Registry.register(BuiltInRegistries.BLOCK, id(id), block)
        BLOCKS.add(regBlock)
        return regBlock
    }

}
