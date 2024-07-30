package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.fabricmc.fabric.api.registry.TillableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.OffsetType
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.variantOf
import net.minecraft.block.Blocks.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.block.sapling.SaplingBlock
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.HoeItem
import net.minecraft.item.ItemConvertible
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Color
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.block.sapling.SaplingGenerators
import org.teamvoided.dusk_autumn.block.sapling.ThreeWideTreeSaplingBlock
import org.teamvoided.dusk_autumn.init.DuskItems.BlockItem
import org.teamvoided.voidmill.sign.VoidCeilingHangingSignBlock
import org.teamvoided.voidmill.sign.VoidSignBlock
import org.teamvoided.voidmill.sign.VoidWallHangingSignBlock
import org.teamvoided.voidmill.sign.VoidWallSignBlock


@Suppress("HasPlatformType", "MemberVisibilityCanBePrivate", "unused", "DEPRECATION")
object DuskBlocks {
    val BLOCKS = mutableListOf<Block>()

    val leafPileSettings = Settings.create()
        .mapColor(MapColor.PLANT).strength(0.2F).nonOpaque().suffocates(Blocks::nonSolid).blockVision(Blocks::nonSolid)
        .lavaIgnitable()
        .solidBlock(Blocks::nonSolid).noCollision().nonSolid()
        .sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)

    val BLUE_PETALS = register(
        "blue_petals", PinkPetalsBlock(
            Settings.create().mapColor(MapColor.PLANT)
                .noCollision().sounds(BlockSoundGroup.PINK_PETALS).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val VIOLET_DAISY = register(
        "violet_daisy", FlowerBlock(
            StatusEffects.HASTE, 10f, Settings.create()
                .mapColor(MapColor.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)
                .offsetType(OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val POTTED_VIOLET_DAISY = registerNoItem("potted_violet_daisy", pottedVariant(VIOLET_DAISY))

    val CASCADE_SAPLING = register(
        "cascade_sapling", ThreeWideTreeSaplingBlock(
            SaplingGenerators.CASCADE,
            Settings.create()
                .mapColor(MapColor.RED).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.CHERRY_SAPLING).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val POTTED_CASCADE_SAPLING = registerNoItem("potted_cascade_sapling", pottedVariant(CASCADE_SAPLING))
    val CASCADE_LOG = register(
        "cascade_log", logOf(MapColor.BLUE, MapColor.BROWN, BlockSoundGroup.CHERRY_WOOD),
    )
    val CASCADE_WOOD = register(
        "cascade_wood", PillarBlock(
            Settings.create().mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0f)
                .sounds(BlockSoundGroup.CHERRY_WOOD).lavaIgnitable()
        )
    )

    val STRIPPED_CASCADE_LOG = register(
        "stripped_cascade_log", logOf(MapColor.BLUE, MapColor.BLUE, BlockSoundGroup.CHERRY_WOOD)
    )
    val STRIPPED_CASCADE_WOOD = register(
        "stripped_cascade_wood", PillarBlock(
            Settings.create().mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.BASS).strength(2.0f)
                .sounds(BlockSoundGroup.CHERRY_WOOD).lavaIgnitable()
        )
    )
    val CASCADE_PLANKS = register(
        "cascade_planks", Block(
            Settings.create()
                .mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.CHERRY_WOOD).lavaIgnitable()
        )
    )
    val CASCADE_STAIRS = register(
        "cascade_stairs", legacyStairsOf(CASCADE_PLANKS)
    )
    val CASCADE_SLAB = register(
        "cascade_slab",
        SlabBlock(
            Settings.create()
                .mapColor(CASCADE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).sounds(BlockSoundGroup.CHERRY_WOOD).lavaIgnitable()
        )
    )
    val CASCADE_FENCE = register(
        "cascade_fence",
        FenceBlock(
            Settings.create()
                .mapColor(CASCADE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).sounds(BlockSoundGroup.CHERRY_WOOD).solid().lavaIgnitable()
        )
    )
    val CASCADE_FENCE_GATE = register(
        "cascade_fence_gate",
        FenceGateBlock(
            DuskWoodTypes.CASCADE_WOOD_TYPE,
            Settings.create()
                .mapColor(CASCADE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).solid().lavaIgnitable()
        )
    )
    val CASCADE_DOOR = registerNoItem(
        "cascade_door", DoorBlock(
            DuskWoodTypes.CASCADE_BLOCK_SET_TYPE,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
        )
    )
    val BLUE_DOOR = registerNoItem(
        "blue_door", DoorBlock(
            BlockSetType.DARK_OAK,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
        )
    )
    val CASCADE_TRAPDOOR = register(
        "cascade_trapdoor", TrapdoorBlock(
            DuskWoodTypes.CASCADE_BLOCK_SET_TYPE,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0f).nonOpaque()
                .allowsSpawning(Blocks::nonSpawnable).lavaIgnitable(),
        )
    )
    val CASCADE_PRESSURE_PLATE = register(
        "cascade_pressure_plate",
        PressurePlateBlock(
            DuskWoodTypes.CASCADE_BLOCK_SET_TYPE,
            Settings.create()
                .mapColor(CASCADE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS).noCollision()
                .strength(0.5f).solid().lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val CASCADE_BUTTON = register("cascade_button", buttonOf(DuskWoodTypes.CASCADE_BLOCK_SET_TYPE))
    val CASCADE_LEAVES = register(
        "cascade_leaves", FallingLeavesBlock(
            Settings.create().strength(0.2f).ticksRandomly()
                .nonOpaque().allowsSpawning(Blocks::allowOcelotsAndParrots).suffocates(Blocks::nonSolid)
                .blockVision(Blocks::nonSolid)
                .lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::nonSolid)
                .sounds(BlockSoundGroup.CHERRY_LEAVES)
                .mapColor(MapColor.RED), DuskParticles.CASCADE_LEAF_PARTICLE
        )
    )
    val CASCADE_SIGN = registerNoItem(
        "cascade_sign", VoidSignBlock(
            DuskWoodTypes.CASCADE_WOOD_TYPE,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(),
        )
    )
    val CASCADE_WALL_SIGN = registerNoItem(
        "cascade_wall_sign", VoidWallSignBlock(
            DuskWoodTypes.CASCADE_WOOD_TYPE,
            Settings.create().mapColor(CASCADE_LOG.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).dropsLike(CASCADE_SIGN)
                .lavaIgnitable(),
        )
    )
    val CASCADE_HANGING_SIGN = registerNoItem(
        "cascade_hanging_sign", VoidCeilingHangingSignBlock(
            DuskWoodTypes.CASCADE_WOOD_TYPE,
            Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(),
        )
    )
    val CASCADE_WALL_HANGING_SIGN = registerNoItem(
        "cascade_wall_hanging_sign", VoidWallHangingSignBlock(
            DuskWoodTypes.CASCADE_WOOD_TYPE,
            Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable()
                .dropsLike(CASCADE_HANGING_SIGN),
        )
    )

    val GOLDEN_BIRCH_LEAVES = register(
        "golden_birch_leaves", LeavesBlock(
            Settings.create().mapColor(MapColor.YELLOW)
                .strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque()
                .allowsSpawning(Blocks::allowOcelotsAndParrots)
                .suffocates(Blocks::nonSolid).blockVision(Blocks::nonSolid).lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(Blocks::nonSolid)
        )
    )
    val GOLDEN_BIRCH_SAPLING = register(
        "golden_birch_sapling", SaplingBlock(
            SaplingGenerators.GOLDEN_BIRCH,
            Settings.create()
                .mapColor(MapColor.YELLOW).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val POTTED_GOLDEN_BIRCH_SAPLING =
        registerNoItem("potted_golden_birch_sapling", pottedVariant(GOLDEN_BIRCH_SAPLING))

    val PINE_PLANKS = register(
        "pine_planks", Block(
            Settings.create()
                .mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.WOOD).lavaIgnitable()
        )
    )
    val PINE_STAIRS = register(
        "pine_stairs", legacyStairsOf(PINE_PLANKS)
    )
    val PINE_SLAB = register(
        "pine_slab",
        SlabBlock(
            variantOf(PINE_PLANKS)
        )
    )
    val PINE_FENCE = register(
        "pine_fence",
        FenceBlock(
            Settings.create()
                .mapColor(PINE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD).solid().lavaIgnitable()
        )
    )
    val PINE_FENCE_GATE = register(
        "pine_fence_gate",
        FenceGateBlock(
            DuskWoodTypes.PINE_WOOD_TYPE,
            Settings.create()
                .mapColor(PINE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).solid().lavaIgnitable()
        )
    )

    //logs are done differently and crash when varianted, but the woods have the exact same properties, just use them
    val OAK_LOG_PILE = register("oak_log_pile", LogPileBlock(variantOf(OAK_WOOD).nonOpaque()))
    val SPRUCE_LOG_PILE = register("spruce_log_pile", LogPileBlock(variantOf(SPRUCE_WOOD).nonOpaque()))
    val BIRCH_LOG_PILE = register("birch_log_pile", LogPileBlock(variantOf(BIRCH_WOOD).nonOpaque()))
    val JUNGLE_LOG_PILE = register("jungle_log_pile", LogPileBlock(variantOf(JUNGLE_WOOD).nonOpaque()))
    val ACACIA_LOG_PILE = register("acacia_log_pile", LogPileBlock(variantOf(ACACIA_WOOD).nonOpaque()))
    val DARK_OAK_LOG_PILE = register("dark_oak_log_pile", LogPileBlock(variantOf(DARK_OAK_WOOD).nonOpaque()))
    val MANGROVE_LOG_PILE = register("mangrove_log_pile", LogPileBlock(variantOf(MANGROVE_WOOD).nonOpaque()))
    val CASCADE_LOG_PILE = register("cascade_log_pile", LogPileBlock(variantOf(CASCADE_WOOD).nonOpaque()))
    val CHERRY_LOG_PILE = register("cherry_log_pile", LogPileBlock(variantOf(CHERRY_WOOD).nonOpaque()))
    val OAK_LEAF_PILE = register("oak_leaf_pile", LeafPileBlock(leafPileSettings))
    val SPRUCE_LEAF_PILE = register("spruce_leaf_pile", LeafPileBlock(leafPileSettings))
    val BIRCH_LEAF_PILE = register("birch_leaf_pile", LeafPileBlock(leafPileSettings))
    val JUNGLE_LEAF_PILE = register("jungle_leaf_pile", LeafPileBlock(leafPileSettings))
    val ACACIA_LEAF_PILE = register("acacia_leaf_pile", LeafPileBlock(leafPileSettings))
    val DARK_OAK_LEAF_PILE = register("dark_oak_leaf_pile", LeafPileBlock(leafPileSettings))
    val MANGROVE_LEAF_PILE = register("mangrove_leaf_pile", LeafPileBlock(leafPileSettings))
    val CASCADE_LEAF_PILE = register(
        "cascade_leaf_pile", FallingLeafPileBlock(
            leafPileSettings.sounds(BlockSoundGroup.CHERRY_LEAVES).mapColor(MapColor.RED),
            DuskParticles.CASCADE_LEAF_PARTICLE
        )
    )
    val CHERRY_LEAF_PILE = register(
        "cherry_leaf_pile",
        FallingLeafPileBlock(leafPileSettings.mapColor(MapColor.PINK), ParticleTypes.CHERRY_LEAVES)
    )
    val AZALEA_LEAF_PILE = register(
        "azalea_leaf_pile",
        LeafPileBlock(leafPileSettings.sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(MapColor.PLANT))
    )
    val FLOWERING_AZALEA_LEAF_PILE = register(
        "flowering_azalea_leaf_pile",
        LeafPileBlock(leafPileSettings.sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(MapColor.PLANT))
    )
    val GOLDEN_BIRCH_LEAF_PILE = register(
        "golden_birch_leaf_pile",
        LeafPileBlock(leafPileSettings.sounds(BlockSoundGroup.GRASS).mapColor(MapColor.YELLOW))
    )

    val bigChainSound = BlockSoundGroup(
        1.0F,
        0.8F,
        SoundEvents.BLOCK_CHAIN_BREAK,
        SoundEvents.BLOCK_CHAIN_STEP,
        SoundEvents.BLOCK_CHAIN_PLACE,
        SoundEvents.BLOCK_CHAIN_HIT,
        SoundEvents.BLOCK_CHAIN_FALL
    )
    val bigLanternSound = BlockSoundGroup(
        1.0F,
        0.8F,
        SoundEvents.BLOCK_LANTERN_BREAK,
        SoundEvents.BLOCK_LANTERN_STEP,
        SoundEvents.BLOCK_LANTERN_PLACE,
        SoundEvents.BLOCK_LANTERN_HIT,
        SoundEvents.BLOCK_LANTERN_FALL
    )
    val bigCandleSound = BlockSoundGroup(
        1.0F,
        0.8F,
        SoundEvents.BLOCK_CANDLE_BREAK,
        SoundEvents.BLOCK_CANDLE_STEP,
        SoundEvents.BLOCK_CANDLE_PLACE,
        SoundEvents.BLOCK_CANDLE_HIT,
        SoundEvents.BLOCK_CANDLE_FALL
    )
    val BIG_CHAIN = register("big_chain", BigChainBlock(variantOf(CHAIN).sounds(bigChainSound)))
    val BIG_LANTERN =
        register("big_lantern", BigLanternBlock(variantOf(LANTERN).sounds(bigLanternSound)))
    val BIG_SOUL_LANTERN =
        register("big_soul_lantern", BigLanternBlock(variantOf(SOUL_LANTERN).sounds(bigLanternSound)))
    val BIG_CANDLE = register("big_candle", bigCandleOf(MapColor.SAND))
    val BIG_WHITE_CANDLE = register("big_white_candle", bigCandleOf(MapColor.WOOL))
    val BIG_LIGHT_GRAY_CANDLE = register("big_light_gray_candle", bigCandleOf(MapColor.LIGHT_GRAY))
    val BIG_GRAY_CANDLE = register("big_gray_candle", bigCandleOf(MapColor.GRAY))
    val BIG_BLACK_CANDLE = register("big_black_candle", bigCandleOf(MapColor.BLACK))
    val BIG_BROWN_CANDLE = register("big_brown_candle", bigCandleOf(MapColor.BROWN))
    val BIG_RED_CANDLE = register("big_red_candle", bigCandleOf(MapColor.RED))
    val BIG_ORANGE_CANDLE = register("big_orange_candle", bigCandleOf(MapColor.ORANGE))
    val BIG_YELLOW_CANDLE = register("big_yellow_candle", bigCandleOf(MapColor.YELLOW))
    val BIG_LIME_CANDLE = register("big_lime_candle", bigCandleOf(MapColor.LIME))
    val BIG_GREEN_CANDLE = register("big_green_candle", bigCandleOf(MapColor.GREEN))
    val BIG_CYAN_CANDLE = register("big_cyan_candle", bigCandleOf(MapColor.CYAN))
    val BIG_LIGHT_BLUE_CANDLE = register("big_light_blue_candle", bigCandleOf(MapColor.LIGHT_BLUE))
    val BIG_BLUE_CANDLE = register("big_blue_candle", bigCandleOf(MapColor.BLUE))
    val BIG_PURPLE_CANDLE = register("big_purple_candle", bigCandleOf(MapColor.PURPLE))
    val BIG_MAGENTA_CANDLE = register("big_magenta_candle", bigCandleOf(MapColor.MAGENTA))
    val BIG_PINK_CANDLE = register("big_pink_candle", bigCandleOf(MapColor.PINK))

    val MIXED_NETHER_BRICKS = register("mixed_nether_bricks", Block(variantOf(NETHER_BRICKS)))
    val CRACKED_MIXED_NETHER_BRICKS = register("cracked_mixed_nether_bricks", Block(variantOf(CRACKED_NETHER_BRICKS)))
    val MIXED_NETHER_BRICK_STAIRS = register("mixed_nether_brick_stairs", legacyStairsOf(NETHER_BRICK_STAIRS))
    val MIXED_NETHER_BRICK_SLAB = register("mixed_nether_brick_slab", SlabBlock(variantOf(NETHER_BRICK_SLAB)))
    val MIXED_NETHER_BRICK_WALL = register("mixed_nether_brick_wall", WallBlock(variantOf(NETHER_BRICK_WALL)))
    val MIXED_NETHER_BRICK_FENCE = register("mixed_nether_brick_fence", FenceBlock(variantOf(NETHER_BRICK_FENCE)))
    val CHISELED_MIXED_NETHER_BRICKS =
        register("chiseled_mixed_nether_bricks", Block(variantOf(CHISELED_NETHER_BRICKS)))
    val MIXED_NETHER_BRICK_PILLAR = register("mixed_nether_brick_pillar", PillarBlock(variantOf(MIXED_NETHER_BRICKS)))
    val CRACKED_RED_NETHER_BRICKS = register("cracked_red_nether_bricks", Block(variantOf(CRACKED_NETHER_BRICKS)))
    val RED_NETHER_BRICK_FENCE = register("red_nether_brick_fence", FenceBlock(variantOf(NETHER_BRICK_FENCE)))
    val CHISELED_RED_NETHER_BRICKS = register("chiseled_red_nether_bricks", Block(variantOf(CHISELED_NETHER_BRICKS)))
    val NETHER_BRICK_PILLAR = register("nether_brick_pillar", PillarBlock(variantOf(NETHER_BRICKS)))
    val POLISHED_NETHER_BRICKS = register("polished_nether_bricks", Block(variantOf(NETHER_BRICKS)))
    val POLISHED_NETHER_BRICK_STAIRS = register("polished_nether_brick_stairs", legacyStairsOf(NETHER_BRICK_STAIRS))
    val POLISHED_NETHER_BRICK_SLAB = register("polished_nether_brick_slab", SlabBlock(variantOf(NETHER_BRICK_SLAB)))
    val POLISHED_NETHER_BRICK_WALL = register("polished_nether_brick_wall", WallBlock(variantOf(NETHER_BRICK_WALL)))
    val RED_NETHER_BRICK_PILLAR = register("red_nether_brick_pillar", PillarBlock(variantOf(RED_NETHER_BRICKS)))
    val POLISHED_RED_NETHER_BRICKS = register("polished_red_nether_bricks", Block(variantOf(RED_NETHER_BRICKS)))
    val POLISHED_RED_NETHER_BRICK_STAIRS =
        register("polished_red_nether_brick_stairs", legacyStairsOf(RED_NETHER_BRICK_STAIRS))
    val POLISHED_RED_NETHER_BRICK_SLAB =
        register("polished_red_nether_brick_slab", SlabBlock(variantOf(RED_NETHER_BRICK_SLAB)))
    val POLISHED_RED_NETHER_BRICK_WALL =
        register("polished_red_nether_brick_wall", WallBlock(variantOf(RED_NETHER_BRICK_WALL)))


    val OVERGROWN_COBBLESTONE = register("overgrown_cobblestone", Block(variantOf(MOSSY_COBBLESTONE)))
    val OVERGROWN_COBBLESTONE_STAIRS = register("overgrown_cobblestone_stairs", legacyStairsOf(OVERGROWN_COBBLESTONE))
    val OVERGROWN_COBBLESTONE_SLAB =
        register("overgrown_cobblestone_slab", SlabBlock(variantOf(MOSSY_COBBLESTONE_SLAB)))
    val OVERGROWN_COBBLESTONE_WALL =
        register("overgrown_cobblestone_wall", WallBlock(variantOf(MOSSY_COBBLESTONE_WALL)))
    val OVERGROWN_STONE_BRICKS = register("overgrown_stone_bricks", Block(variantOf(MOSSY_STONE_BRICKS)))
    val OVERGROWN_STONE_BRICK_STAIRS = register("overgrown_stone_brick_stairs", legacyStairsOf(MOSSY_STONE_BRICKS))
    val OVERGROWN_STONE_BRICK_SLAB =
        register("overgrown_stone_brick_slab", SlabBlock(variantOf(MOSSY_STONE_BRICK_SLAB)))
    val OVERGROWN_STONE_BRICK_WALL =
        register("overgrown_stone_brick_wall", WallBlock(variantOf(MOSSY_STONE_BRICK_WALL)))

    val ROOT_BLOCK = register(
        "root_block",
        MangroveRootsBlock(
            Settings.create().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS)
                .strength(0.7f).nonOpaque().suffocates(Blocks::nonSolid).blockVision(Blocks::nonSolid).nonOpaque()
                .lavaIgnitable().sounds(
                    BlockSoundGroup(
                        1f,
                        0.8f,
                        SoundEvents.BLOCK_HANGING_ROOTS_BREAK,
                        SoundEvents.BLOCK_HANGING_ROOTS_STEP,
                        SoundEvents.BLOCK_HANGING_ROOTS_PLACE,
                        SoundEvents.BLOCK_HANGING_ROOTS_HIT,
                        SoundEvents.BLOCK_HANGING_ROOTS_FALL
                    )
                )
        )
    )
    val ROCKY_DIRT = register("dirty_rocks", Block(variantOf(DIRT).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_GRASS = register(
        "grassy_rocks",
        RockyGrassBlock(ROCKY_DIRT, variantOf(GRASS_BLOCK).mapColor(COBBLESTONE.defaultMapColor))
    )
    val ROCKY_PODZOL = register("podzol_rocks", SnowyBlock(variantOf(PODZOL).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_MYCELIUM = register(
        "mycelium_rocks",
        RockyMyceliumBlock(ROCKY_DIRT, variantOf(MYCELIUM).mapColor(COBBLESTONE.defaultMapColor))
    )
    val ROCKY_COARSE_DIRT =
        register("coarsely_dirty_rocks", Block(variantOf(COARSE_DIRT).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_DIRT_PATH = register(
        "rocky_path",
        RockyDirtPathBlock(ROCKY_DIRT, variantOf(DIRT_PATH).mapColor(COBBLESTONE.defaultMapColor))
    )
    val ROCKY_MUD = register("muddy_rocks", MudBlock(variantOf(MUD).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_SNOW = register("snowy_rocks", Block(variantOf(SNOW_BLOCK).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_GRAVEL =
        register("rocky_rocks", GravelBlock(Color(-8356741), variantOf(GRAVEL).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_SAND =
        register("sandy_rocks", GravelBlock(Color(14406560), variantOf(SAND).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_RED_SAND = register(
        "red_sandy_rocks",
        GravelBlock(Color(11098145), variantOf(RED_SAND).mapColor(COBBLESTONE.defaultMapColor))
    )
    val ROCKY_SOUL_SAND =
        register("rocky_soul_sand", SoulSandBlock(variantOf(SOUL_SAND).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_SOUL_SOIL = register("rocky_soul_soil", Block(variantOf(SOUL_SOIL).mapColor(COBBLESTONE.defaultMapColor)))

    val SLATED_DIRT = register("dirty_slate", Block(variantOf(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_GRASS = register(
        "grassy_slate",
        RockyGrassBlock(SLATED_DIRT, variantOf(GRASS_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val SLATED_PODZOL =
        register("podzol_slate", SnowyBlock(variantOf(PODZOL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_MYCELIUM = register(
        "mycelium_slate",
        RockyMyceliumBlock(SLATED_DIRT, variantOf(MYCELIUM).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val SLATED_COARSE_DIRT =
        register("coarsely_dirty_slate", Block(variantOf(COARSE_DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_DIRT_PATH = register(
        "slated_path",
        RockyDirtPathBlock(SLATED_DIRT, variantOf(DIRT_PATH).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val SLATED_MUD = register("muddy_slate", MudBlock(variantOf(MUD).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_SNOW = register("snowy_slate", Block(variantOf(SNOW_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_GRAVEL = register(
        "slated_slate",
        GravelBlock(Color(-8356741), variantOf(GRAVEL).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val SLATED_SAND = register(
        "sandy_slate",
        GravelBlock(Color(14406560), variantOf(SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val SLATED_RED_SAND = register(
        "red_sandy_slate",
        GravelBlock(Color(11098145), variantOf(RED_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val SLATED_SOUL_SAND =
        register("slated_soul_sand", SoulSandBlock(variantOf(SOUL_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_SOUL_SOIL =
        register("slated_soul_soil", Block(variantOf(SOUL_SOIL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))

    val BLACKSTONE_DIRT =
        register("dirty_blackstone", Block(variantOf(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_GRASS = register(
        "grassy_blackstone",
        RockyGrassBlock(BLACKSTONE_DIRT, variantOf(GRASS_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val BLACKSTONE_PODZOL =
        register("podzol_blackstone", SnowyBlock(variantOf(PODZOL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_MYCELIUM = register(
        "mycelium_blackstone",
        RockyMyceliumBlock(BLACKSTONE_DIRT, variantOf(MYCELIUM).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val BLACKSTONE_COARSE_DIRT =
        register("coarsely_dirty_blackstone", Block(variantOf(COARSE_DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_DIRT_PATH = register(
        "blackstoned_path",
        RockyDirtPathBlock(BLACKSTONE_DIRT, variantOf(DIRT_PATH).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val BLACKSTONE_MUD =
        register("muddy_blackstone", MudBlock(variantOf(MUD).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_SNOW =
        register("snowy_blackstone", Block(variantOf(SNOW_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_GRAVEL = register(
        "blackstoned_blackstone",
        GravelBlock(Color(-8356741), variantOf(GRAVEL).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val BLACKSTONE_SAND = register(
        "sandy_blackstone",
        GravelBlock(Color(14406560), variantOf(SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val BLACKSTONE_RED_SAND = register(
        "red_sandy_blackstone",
        GravelBlock(Color(11098145), variantOf(RED_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val BLACKSTONE_SOUL_SAND = register(
        "blackstoned_soul_sand",
        SoulSandBlock(variantOf(SOUL_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
    )
    val BLACKSTONE_SOUL_SOIL =
        register("blackstoned_soul_soil", Block(variantOf(SOUL_SOIL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))


    val WILD_WHEAT = registerNoItem(
        "wild_wheat",
        TallPlantBlock(
            Settings.create().mapColor(MapColor.PLANT).noCollision().breakInstantly()
                .sounds(BlockSoundGroup.CROP).offsetType(OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val GOLDEN_BEETROOTS = registerNoItem(
        "golden_beetroots",
        GoldenBeetrootsBlock(
            Settings.create().mapColor(MapColor.GOLD).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val MOONBERRY_VINE = register(
        "moonberry_vine",
        MoonberryVineBlock(
            Settings.create().mapColor(MapColor.PURPLE).noCollision().strength(0.2f)
                .sounds(BlockSoundGroup.CAVE_VINES).luminance(MoonberryVineBlock.getLuminanceSupplier(8, 11))
                .lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val MOONBERRY_VINELET = registerNoItem(
        "moonberry_vinelet", MoonberryVineletBlock(
            Settings.create().mapColor(MapColor.PURPLE).noCollision().strength(0.2f).ticksRandomly()
                .breakInstantly().sounds(BlockSoundGroup.CAVE_VINES).lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )

    fun init() {
        DuskBlockFamilies.init()
        StrippableBlockRegistry.register(CASCADE_LOG, STRIPPED_CASCADE_LOG)
        StrippableBlockRegistry.register(CASCADE_WOOD, STRIPPED_CASCADE_WOOD)

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

    fun bigCandleOf(color: MapColor): Block {
        return BigCandleBlock(
            Settings.create().mapColor(color).nonOpaque().strength(0.1f).sounds(bigCandleSound)
                .luminance(CandleBlock.STATE_TO_LUMINANCE).pistonBehavior(PistonBehavior.DESTROY)
        )
    }

    fun dirtPath(input: Block, output: Block) {
        FlattenableBlockRegistry.register(input, output.defaultState)
    }

    fun removeRocks(input: Block, output: Block, craftingIngredient: ItemConvertible) {
        TillableBlockRegistry.register(
            input, { true },
            HoeItem.createTillAndDropAction(output.defaultState, craftingIngredient)
        )
    }

    fun register(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        DuskItems.register(id, BlockItem(regBlock))
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCKS.add(regBlock)
        return regBlock
    }

}
