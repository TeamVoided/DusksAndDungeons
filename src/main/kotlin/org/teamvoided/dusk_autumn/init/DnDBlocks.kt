package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.*
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.org.teamvoided.dusk_autumn.block.TallRedstoneCrystalBlock
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.block.sapling.SaplingBlock
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Color
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.block.big.BigChainBlock
import org.teamvoided.dusk_autumn.block.big.BigLanternBlock
import org.teamvoided.dusk_autumn.block.rocky.RockyDirtPathBlock
import org.teamvoided.dusk_autumn.block.rocky.RockyGrassBlock
import org.teamvoided.dusk_autumn.block.rocky.RockyMyceliumBlock
import org.teamvoided.dusk_autumn.block.sapling.SaplingGenerators
import org.teamvoided.dusk_autumn.block.sapling.ThreeWideTreeSaplingBlock
import org.teamvoided.dusk_autumn.init.DnDItems.BlockItem
import org.teamvoided.dusk_autumn.util.*
import org.teamvoided.voidmill.sign.VoidCeilingHangingSignBlock
import org.teamvoided.voidmill.sign.VoidSignBlock
import org.teamvoided.voidmill.sign.VoidWallHangingSignBlock
import org.teamvoided.voidmill.sign.VoidWallSignBlock


@Suppress("LargeClass", "TooManyFunctions", "MemberVisibilityCanBePrivate", "unused")
object DnDBlocks {
    val BLOCKS = mutableSetOf<Block>()
    val CUTOUT_BLOCKS = mutableSetOf<Block>()
    val WALLS = mutableSetOf<Block>()
    val SWORDABLE = mutableSetOf<Block>()
    val PICKAXABLE = mutableSetOf<Block>()
    val AXABLE = mutableSetOf<Block>()
    val SHOVELABLE = mutableSetOf<Block>()
    val HOEABLE = mutableSetOf<Block>()


    val CASCADE_SAPLING = register(
        "cascade_sapling", ThreeWideTreeSaplingBlock(
            SaplingGenerators.CASCADE,
            Settings.create()
                .mapColor(MapColor.RED).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.CHERRY_SAPLING).pistonBehavior(PistonBehavior.DESTROY)
        )
    ).cutout()
    val POTTED_CASCADE_SAPLING = registerNoItem("potted_cascade_sapling", pottedVariant(CASCADE_SAPLING)).cutout()
    val CASCADE_LOG = register(
        "cascade_log", logOf(MapColor.BLUE, MapColor.BROWN, BlockSoundGroup.CHERRY_WOOD)
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
    val CASCADE_STAIRS = register("cascade_stairs", stairsOf(CASCADE_PLANKS).axe())
    val CASCADE_SLAB = register("cascade_slab", slabOf(CASCADE_PLANKS).axe())
    val CASCADE_FENCE = register("cascade_fence", fenceOf(CASCADE_PLANKS).axe())
    val CASCADE_FENCE_GATE =
        register("cascade_fence_gate", fenceGateOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS).axe())
    val CASCADE_DOOR = registerNoItem(
        "cascade_door", DoorBlock(
            DnDWoodTypes.CASCADE_BLOCK_SET_TYPE,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
        ).axe()
    )
    val BLUE_DOOR = registerNoItem(
        "blue_door", DoorBlock(
            BlockSetType.DARK_OAK,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
        ).axe()
    )
    val CASCADE_TRAPDOOR = register(
        "cascade_trapdoor", TrapdoorBlock(
            DnDWoodTypes.CASCADE_BLOCK_SET_TYPE,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0f).nonOpaque()
                .allowsSpawning(Blocks::nonSpawnable).lavaIgnitable(),
        ).axe()
    )
    val CASCADE_PRESSURE_PLATE = register(
        "cascade_pressure_plate",
        PressurePlateBlock(
            DnDWoodTypes.CASCADE_BLOCK_SET_TYPE,
            Settings.create()
                .mapColor(CASCADE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS).noCollision()
                .strength(0.5f).solid().lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY)
        ).axe()
    )
    val CASCADE_BUTTON = register("cascade_button", buttonOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE).axe())
    val CASCADE_LEAVES = register(
        "cascade_leaves", FallingLeavesBlock(
            Settings.create().strength(0.2f).ticksRandomly()
                .nonOpaque().allowsSpawning(Blocks::allowOcelotsAndParrots).suffocates(Blocks::nonSolid)
                .blockVision(Blocks::nonSolid)
                .lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::nonSolid)
                .sounds(BlockSoundGroup.CHERRY_LEAVES)
                .mapColor(MapColor.RED), DnDParticles.CASCADE_LEAF_PARTICLE
        ).cutout().axe()
    )
    val CASCADE_SIGN = registerNoItem(
        "cascade_sign", VoidSignBlock(
            DnDWoodTypes.CASCADE_WOOD_TYPE,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(),
        ).axe()
    )
    val CASCADE_WALL_SIGN = registerNoItem(
        "cascade_wall_sign", VoidWallSignBlock(
            DnDWoodTypes.CASCADE_WOOD_TYPE,
            Settings.create().mapColor(CASCADE_LOG.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).dropsLike(CASCADE_SIGN)
                .lavaIgnitable(),
        ).axe()
    )
    val CASCADE_HANGING_SIGN = registerNoItem(
        "cascade_hanging_sign", VoidCeilingHangingSignBlock(
            DnDWoodTypes.CASCADE_WOOD_TYPE,
            Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(),
        ).axe()
    )
    val CASCADE_WALL_HANGING_SIGN = registerNoItem(
        "cascade_wall_hanging_sign", VoidWallHangingSignBlock(
            DnDWoodTypes.CASCADE_WOOD_TYPE,
            Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable()
                .dropsLike(CASCADE_HANGING_SIGN),
        ).axe()
    )

    val GOLDEN_BIRCH_LEAVES = register(
        "golden_birch_leaves", LeavesBlock(
            Settings.create().mapColor(MapColor.YELLOW)
                .strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque()
                .allowsSpawning(Blocks::allowOcelotsAndParrots)
                .suffocates(Blocks::nonSolid).blockVision(Blocks::nonSolid).lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(Blocks::nonSolid)
        ).cutout()
    )
    val GOLDEN_BIRCH_SAPLING = register(
        "golden_birch_sapling", SaplingBlock(
            SaplingGenerators.GOLDEN_BIRCH,
            Settings.create()
                .mapColor(MapColor.YELLOW).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)
                .pistonBehavior(PistonBehavior.DESTROY)
        ).cutout()
    )
    val POTTED_GOLDEN_BIRCH_SAPLING =
        registerNoItem("potted_golden_birch_sapling", pottedVariant(GOLDEN_BIRCH_SAPLING).cutout())

//    val PINE_PLANKS = register(
//        "pine_planks", Block(
//            Settings.create()
//                .mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
//                .sounds(BlockSoundGroup.WOOD).lavaIgnitable()
//        ).axe()
//    )
//    val PINE_STAIRS = register("pine_stairs", stairsOf(PINE_PLANKS).axe())
//    val PINE_SLAB = register("pine_slab", slabOf(PINE_PLANKS).axe())
//    val PINE_FENCE = register("pine_fence", fenceOf(PINE_PLANKS))
//    val PINE_FENCE_GATE = register(
//        "pine_fence_gate",
//        FenceGateBlock(
//            DnDWoodTypes.PINE_WOOD_TYPE,
//            Settings.create()
//                .mapColor(PINE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS)
//                .strength(2.0f, 3.0f).solid()
//        ).axe()
//    )

    val BONEWOOD_PLANKS = register(
        "bonewood_planks", Block(
            Settings.create()
                .mapColor(MapColor.SNOW).instrument(NoteBlockInstrument.XYLOPHONE).strength(2.0F, 3.0F)
                .sounds(bonewoodSound)
        ).axe()
    )
    val BONEWOOD_STAIRS = register("bonewood_stairs", stairsOf(BONEWOOD_PLANKS).axe())
    val BONEWOOD_SLAB = register("bonewood_slab", slabOf(BONEWOOD_PLANKS).axe())
    val BONEWOOD_FENCE = register("bonewood_fence", fenceOf(BONEWOOD_PLANKS).axe())
    val BONEWOOD_FENCE_GATE = register(
        "bonewood_fence_gate",
        FenceGateBlock(
            DnDWoodTypes.BONEWOOD_WOOD_TYPE,
            copy(BONEWOOD_PLANKS).solid()

        ).axe()
    )
    val BONEWOOD_DOOR = registerNoItem(
        "bonewood_door", DoorBlock(
            DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE,
            copy(BONEWOOD_PLANKS).strength(3.0f).nonOpaque(),
        ).cutout().axe()
    )
    val BONEWOOD_TRAPDOOR = register(
        "bonewood_trapdoor", TrapdoorBlock(
            DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE,
            copy(BONEWOOD_DOOR).allowsSpawning(Blocks::nonSpawnable),
        ).cutout().axe()
    )
    val WITHERING_BONEWOOD_PLANKS = register(
        "withering_bonewood_planks", Block(
            copy(BONEWOOD_PLANKS).mapColor(MapColor.BLACK).sounds(witheringBonewoodSound)
        ).axe()
    )
    val WITHERING_BONEWOOD_STAIRS = register("withering_bonewood_stairs", stairsOf(WITHERING_BONEWOOD_PLANKS).axe())
    val WITHERING_BONEWOOD_SLAB = register("withering_bonewood_slab", slabOf(WITHERING_BONEWOOD_PLANKS).axe())
    val WITHERING_BONEWOOD_FENCE = register("withering_bonewood_fence", fenceOf(WITHERING_BONEWOOD_PLANKS).axe())
    val WITHERING_BONEWOOD_FENCE_GATE = register(
        "withering_bonewood_fence_gate",
        FenceGateBlock(
            DnDWoodTypes.WITHERING_BONEWOOD_WOOD_TYPE,
            copy(WITHERING_BONEWOOD_PLANKS).solid()
        ).axe()
    )
    val WITHERING_BONEWOOD_DOOR = registerNoItem(
        "withering_bonewood_door", DoorBlock(
            DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE,
            copy(WITHERING_BONEWOOD_PLANKS).strength(3.0f).nonOpaque(),
        ).cutout().axe()
    )
    val WITHERING_BONEWOOD_TRAPDOOR = register(
        "withering_bonewood_trapdoor", TrapdoorBlock(
            DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE,
            copy(WITHERING_BONEWOOD_DOOR).allowsSpawning(Blocks::nonSpawnable),
        ).cutout().axe()
    )

    //logs are done differently and crash when varianted, but the woods have the exact same properties, just use them
    val OAK_LOG_PILE = register("oak_log_pile", logPile(OAK_WOOD))
    val SPRUCE_LOG_PILE = register("spruce_log_pile", logPile(SPRUCE_WOOD))
    val BIRCH_LOG_PILE = register("birch_log_pile", logPile(BIRCH_WOOD))
    val JUNGLE_LOG_PILE = register("jungle_log_pile", logPile(JUNGLE_WOOD))
    val ACACIA_LOG_PILE = register("acacia_log_pile", logPile(ACACIA_WOOD))
    val DARK_OAK_LOG_PILE = register("dark_oak_log_pile", logPile(DARK_OAK_WOOD))
    val MANGROVE_LOG_PILE = register("mangrove_log_pile", logPile(MANGROVE_WOOD))
    val CHERRY_LOG_PILE = register("cherry_log_pile", logPile(CHERRY_WOOD))
    val CASCADE_LOG_PILE = register("cascade_log_pile", logPile(CASCADE_WOOD))
    val CRIMSON_STEM_PILE = register("crimson_stem_pile", logPile(CRIMSON_HYPHAE))
    val WARPED_STEM_PILE = register("warped_stem_pile", logPile(WARPED_HYPHAE))
    val OAK_LEAF_PILE = register("oak_leaf_pile", leafPile().cutout())
    val SPRUCE_LEAF_PILE = register("spruce_leaf_pile", leafPile().cutout())
    val BIRCH_LEAF_PILE = register("birch_leaf_pile", leafPile().cutout())
    val JUNGLE_LEAF_PILE = register("jungle_leaf_pile", leafPile().cutout())
    val ACACIA_LEAF_PILE = register("acacia_leaf_pile", leafPile().cutout())
    val DARK_OAK_LEAF_PILE = register("dark_oak_leaf_pile", leafPile().cutout())
    val MANGROVE_LEAF_PILE = register("mangrove_leaf_pile", leafPile().cutout())
    val CHERRY_LEAF_PILE = register(
        "cherry_leaf_pile",
        FallingLeafPileBlock(
            fallingLeafPile(MapColor.PINK, BlockSoundGroup.CHERRY_LEAVES),
            ParticleTypes.CHERRY_LEAVES
        ).cutout()
    )
    val CASCADE_LEAF_PILE = register(
        "cascade_leaf_pile", FallingLeafPileBlock(
            fallingLeafPile(MapColor.RED, BlockSoundGroup.CHERRY_LEAVES),
            DnDParticles.CASCADE_LEAF_PARTICLE
        ).cutout()
    )
    val AZALEA_LEAF_PILE = register(
        "azalea_leaf_pile",
        leafPile(BlockSoundGroup.AZALEA_LEAVES).cutout()
    )
    val FLOWERING_AZALEA_LEAF_PILE = register(
        "flowering_azalea_leaf_pile",
        leafPile(BlockSoundGroup.AZALEA_LEAVES).cutout()
    )
    val GOLDEN_BIRCH_LEAF_PILE = register(
        "golden_birch_leaf_pile",
        leafPile(MapColor.YELLOW).cutout()
    )

    val STONE_PILLAR = register("stone_pillar", PillarBlock(copy(CHISELED_STONE_BRICKS)))
    val DEEPSLATE_PILLAR = register("deepslate_pillar", PillarBlock(copy(POLISHED_DEEPSLATE)))
    val POLISHED_STONE = register("polished_stone", Block(copy(SMOOTH_STONE)).pickaxe())
    val POLISHED_STONE_STAIRS = register("polished_stone_stairs", stairsOf(POLISHED_STONE).pickaxe())
    val POLISHED_STONE_SLAB = register("polished_stone_slab", slabOf(POLISHED_STONE).pickaxe())
    val POLISHED_STONE_WALL = register("polished_stone_wall", wallOf(POLISHED_STONE).pickaxe())
    val MOSSY_POLISHED_STONE = register("mossy_polished_stone", Block(copy(POLISHED_STONE)).pickaxe())
    val MOSSY_POLISHED_STONE_STAIRS = register("mossy_polished_stone_stairs", stairsOf(MOSSY_POLISHED_STONE).pickaxe())
    val MOSSY_POLISHED_STONE_SLAB = register("mossy_polished_stone_slab", slabOf(MOSSY_POLISHED_STONE).pickaxe())
    val MOSSY_POLISHED_STONE_WALL = register("mossy_polished_stone_wall", wallOf(MOSSY_POLISHED_STONE).pickaxe())
    val OVERGROWN_POLISHED_STONE = register("overgrown_polished_stone", Block(copy(MOSSY_POLISHED_STONE)).cutout().pickaxe())
    val OVERGROWN_POLISHED_STONE_STAIRS =
        register("overgrown_polished_stone_stairs", stairsOf(OVERGROWN_POLISHED_STONE).cutout().pickaxe())
    val OVERGROWN_POLISHED_STONE_SLAB =
        register("overgrown_polished_stone_slab", slabOf(OVERGROWN_POLISHED_STONE).cutout().pickaxe())
    val OVERGROWN_POLISHED_STONE_WALL =
        register("overgrown_polished_stone_wall", wallOf(OVERGROWN_POLISHED_STONE).cutout().pickaxe())
    val OVERGROWN_COBBLESTONE =
        register("overgrown_cobblestone", Block(copy(MOSSY_COBBLESTONE)).cutout().pickaxe())
    val OVERGROWN_COBBLESTONE_STAIRS =
        register("overgrown_cobblestone_stairs", stairsOf(OVERGROWN_COBBLESTONE).cutout().pickaxe())
    val OVERGROWN_COBBLESTONE_SLAB =
        register("overgrown_cobblestone_slab", slabOf(MOSSY_COBBLESTONE_SLAB).cutout().pickaxe())
    val OVERGROWN_COBBLESTONE_WALL =
        register("overgrown_cobblestone_wall", wallOf(MOSSY_COBBLESTONE_WALL).cutout().pickaxe())
    val OVERGROWN_STONE_BRICKS =
        register("overgrown_stone_bricks", Block(copy(MOSSY_STONE_BRICKS)).cutout().pickaxe())
    val OVERGROWN_STONE_BRICK_STAIRS =
        register("overgrown_stone_brick_stairs", stairsOf(MOSSY_STONE_BRICKS).cutout().pickaxe())
    val OVERGROWN_STONE_BRICK_SLAB =
        register("overgrown_stone_brick_slab", slabOf(MOSSY_STONE_BRICK_SLAB).cutout().pickaxe())
    val OVERGROWN_STONE_BRICK_WALL =
        register("overgrown_stone_brick_wall", wallOf(MOSSY_STONE_BRICK_WALL).cutout().pickaxe())
    val SNOWY_STONE_BRICKS = register("snowy_stone_bricks", Block(copy(STONE_BRICKS)).pickaxe())
    val SNOWY_STONE_BRICK_STAIRS = register("snowy_stone_brick_stairs", stairsOf(STONE_BRICKS).pickaxe())
    val SNOWY_STONE_BRICK_SLAB = register("snowy_stone_brick_slab", slabOf(STONE_BRICK_SLAB).pickaxe())
    val SNOWY_STONE_BRICK_WALL = register("snowy_stone_brick_wall", wallOf(STONE_BRICK_WALL).pickaxe())

    val MOONCORE = register(
        "mooncore", CrytalClusterWithParticlesBlock(
            12.0f, 2.0f,
            Settings.create().mapColor(MapColor.LIGHT_BLUE).solid().nonOpaque().sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                .strength(1.5f).ticksRandomly().luminance { _: BlockState -> 15 }
                .pistonBehavior(PistonBehavior.DESTROY)
        ).cutout()
    )
    val TALL_REDSTONE_CRYSTAL = register(
        "tall_redstone_crystal", TallRedstoneCrystalBlock(
            Settings.create().mapColor(MapColor.RED).solid().nonOpaque().sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                .strength(1.5f).ticksRandomly().luminance(luminanceOf(9))
                .pistonBehavior(PistonBehavior.DESTROY)
        ).cutout()
    )

    val BIG_CHAIN = register("big_chain", BigChainBlock(copy(CHAIN).sounds(bigChainSound)).cutout().pickaxe())
    val BIG_LANTERN =
        register("big_lantern", BigLanternBlock(copy(LANTERN).sounds(bigLanternSound)).pickaxe())
    val BIG_SOUL_LANTERN =
        register("big_soul_lantern", BigLanternBlock(copy(SOUL_LANTERN).sounds(bigLanternSound)).pickaxe())
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

    // soul candles
    val SOUL_CANDLE = register("soul_candle", soulCandleOf(MapColor.SAND))
    val WHITE_SOUL_CANDLE = register("white_soul_candle", soulCandleOf(MapColor.WOOL))
    val LIGHT_GRAY_SOUL_CANDLE = register("light_gray_soul_candle", soulCandleOf(MapColor.LIGHT_GRAY))
    val GRAY_SOUL_CANDLE = register("gray_soul_candle", soulCandleOf(MapColor.GRAY))
    val BLACK_SOUL_CANDLE = register("black_soul_candle", soulCandleOf(MapColor.BLACK))
    val BROWN_SOUL_CANDLE = register("brown_soul_candle", soulCandleOf(MapColor.BROWN))
    val RED_SOUL_CANDLE = register("red_soul_candle", soulCandleOf(MapColor.RED))
    val ORANGE_SOUL_CANDLE = register("orange_soul_candle", soulCandleOf(MapColor.ORANGE))
    val YELLOW_SOUL_CANDLE = register("yellow_soul_candle", soulCandleOf(MapColor.YELLOW))
    val LIME_SOUL_CANDLE = register("lime_soul_candle", soulCandleOf(MapColor.LIME))
    val GREEN_SOUL_CANDLE = register("green_soul_candle", soulCandleOf(MapColor.GREEN))
    val CYAN_SOUL_CANDLE = register("cyan_soul_candle", soulCandleOf(MapColor.CYAN))
    val LIGHT_BLUE_SOUL_CANDLE = register("light_blue_soul_candle", soulCandleOf(MapColor.LIGHT_BLUE))
    val BLUE_SOUL_CANDLE = register("blue_soul_candle", soulCandleOf(MapColor.BLUE))
    val PURPLE_SOUL_CANDLE = register("purple_soul_candle", soulCandleOf(MapColor.PURPLE))
    val MAGENTA_SOUL_CANDLE = register("magenta_soul_candle", soulCandleOf(MapColor.MAGENTA))
    val PINK_SOUL_CANDLE = register("pink_soul_candle", soulCandleOf(MapColor.PINK))
    val BIG_SOUL_CANDLE = register("big_soul_candle", bigSoulCandleOf(MapColor.SAND))
    val BIG_WHITE_SOUL_CANDLE = register("big_white_soul_candle", bigSoulCandleOf(MapColor.WOOL))
    val BIG_LIGHT_GRAY_SOUL_CANDLE = register("big_light_gray_soul_candle", bigSoulCandleOf(MapColor.LIGHT_GRAY))
    val BIG_GRAY_SOUL_CANDLE = register("big_gray_soul_candle", bigSoulCandleOf(MapColor.GRAY))
    val BIG_BLACK_SOUL_CANDLE = register("big_black_soul_candle", bigSoulCandleOf(MapColor.BLACK))
    val BIG_BROWN_SOUL_CANDLE = register("big_brown_soul_candle", bigSoulCandleOf(MapColor.BROWN))
    val BIG_RED_SOUL_CANDLE = register("big_red_soul_candle", bigSoulCandleOf(MapColor.RED))
    val BIG_ORANGE_SOUL_CANDLE = register("big_orange_soul_candle", bigSoulCandleOf(MapColor.ORANGE))
    val BIG_YELLOW_SOUL_CANDLE = register("big_yellow_soul_candle", bigSoulCandleOf(MapColor.YELLOW))
    val BIG_LIME_SOUL_CANDLE = register("big_lime_soul_candle", bigSoulCandleOf(MapColor.LIME))
    val BIG_GREEN_SOUL_CANDLE = register("big_green_soul_candle", bigSoulCandleOf(MapColor.GREEN))
    val BIG_CYAN_SOUL_CANDLE = register("big_cyan_soul_candle", bigSoulCandleOf(MapColor.CYAN))
    val BIG_LIGHT_BLUE_SOUL_CANDLE = register("big_light_blue_soul_candle", bigSoulCandleOf(MapColor.LIGHT_BLUE))
    val BIG_BLUE_SOUL_CANDLE = register("big_blue_soul_candle", bigSoulCandleOf(MapColor.BLUE))
    val BIG_PURPLE_SOUL_CANDLE = register("big_purple_soul_candle", bigSoulCandleOf(MapColor.PURPLE))
    val BIG_MAGENTA_SOUL_CANDLE = register("big_magenta_soul_candle", bigSoulCandleOf(MapColor.MAGENTA))
    val BIG_PINK_SOUL_CANDLE = register("big_pink_soul_candle", bigSoulCandleOf(MapColor.PINK))

    val BIG_CANDLE_CAKE = registerNoItem("big_candle_cake", bigCandleCakeOf(BIG_CANDLE, CANDLE_CAKE))
    val BIG_WHITE_CANDLE_CAKE = registerNoItem("big_white_candle_cake", bigCandleCakeOf(BIG_WHITE_CANDLE))
    val BIG_LIGHT_GRAY_CANDLE_CAKE =
        registerNoItem("big_light_gray_candle_cake", bigCandleCakeOf(BIG_LIGHT_GRAY_CANDLE))
    val BIG_GRAY_CANDLE_CAKE = registerNoItem("big_gray_candle_cake", bigCandleCakeOf(BIG_GRAY_CANDLE))
    val BIG_BLACK_CANDLE_CAKE = registerNoItem("big_black_candle_cake", bigCandleCakeOf(BIG_BLACK_CANDLE))
    val BIG_BROWN_CANDLE_CAKE = registerNoItem("big_brown_candle_cake", bigCandleCakeOf(BIG_BROWN_CANDLE))
    val BIG_RED_CANDLE_CAKE = registerNoItem("big_red_candle_cake", bigCandleCakeOf(BIG_RED_CANDLE))
    val BIG_ORANGE_CANDLE_CAKE = registerNoItem("big_orange_candle_cake", bigCandleCakeOf(BIG_ORANGE_CANDLE))
    val BIG_YELLOW_CANDLE_CAKE = registerNoItem("big_yellow_candle_cake", bigCandleCakeOf(BIG_YELLOW_CANDLE))
    val BIG_LIME_CANDLE_CAKE = registerNoItem("big_lime_candle_cake", bigCandleCakeOf(BIG_LIME_CANDLE))
    val BIG_GREEN_CANDLE_CAKE = registerNoItem("big_green_candle_cake", bigCandleCakeOf(BIG_GREEN_CANDLE))
    val BIG_CYAN_CANDLE_CAKE = registerNoItem("big_cyan_candle_cake", bigCandleCakeOf(BIG_CYAN_CANDLE))
    val BIG_LIGHT_BLUE_CANDLE_CAKE =
        registerNoItem("big_light_blue_candle_cake", bigCandleCakeOf(BIG_LIGHT_BLUE_CANDLE))
    val BIG_BLUE_CANDLE_CAKE = registerNoItem("big_blue_candle_cake", bigCandleCakeOf(BIG_BLUE_CANDLE))
    val BIG_PURPLE_CANDLE_CAKE = registerNoItem("big_purple_candle_cake", bigCandleCakeOf(BIG_PURPLE_CANDLE))
    val BIG_MAGENTA_CANDLE_CAKE = registerNoItem("big_magenta_candle_cake", bigCandleCakeOf(BIG_MAGENTA_CANDLE))
    val BIG_PINK_CANDLE_CAKE = registerNoItem("big_pink_candle_cake", bigCandleCakeOf(BIG_PINK_CANDLE))
    val SOUL_CANDLE_CAKE = registerNoItem("soul_candle_cake", soulCandleCakeOf(SOUL_CANDLE, CANDLE_CAKE))
    val WHITE_SOUL_CANDLE_CAKE = registerNoItem("white_soul_candle_cake", soulCandleCakeOf(WHITE_SOUL_CANDLE))
    val LIGHT_GRAY_SOUL_CANDLE_CAKE =
        registerNoItem("light_gray_soul_candle_cake", soulCandleCakeOf(LIGHT_GRAY_SOUL_CANDLE))
    val GRAY_SOUL_CANDLE_CAKE = registerNoItem("gray_soul_candle_cake", soulCandleCakeOf(GRAY_SOUL_CANDLE))
    val BLACK_SOUL_CANDLE_CAKE = registerNoItem("black_soul_candle_cake", soulCandleCakeOf(BLACK_SOUL_CANDLE))
    val BROWN_SOUL_CANDLE_CAKE = registerNoItem("brown_soul_candle_cake", soulCandleCakeOf(BROWN_SOUL_CANDLE))
    val RED_SOUL_CANDLE_CAKE = registerNoItem("red_soul_candle_cake", soulCandleCakeOf(RED_SOUL_CANDLE))
    val ORANGE_SOUL_CANDLE_CAKE = registerNoItem("orange_soul_candle_cake", soulCandleCakeOf(ORANGE_SOUL_CANDLE))
    val YELLOW_SOUL_CANDLE_CAKE = registerNoItem("yellow_soul_candle_cake", soulCandleCakeOf(YELLOW_SOUL_CANDLE))
    val LIME_SOUL_CANDLE_CAKE = registerNoItem("lime_soul_candle_cake", soulCandleCakeOf(LIME_SOUL_CANDLE))
    val GREEN_SOUL_CANDLE_CAKE = registerNoItem("green_soul_candle_cake", soulCandleCakeOf(GREEN_SOUL_CANDLE))
    val CYAN_SOUL_CANDLE_CAKE = registerNoItem("cyan_soul_candle_cake", soulCandleCakeOf(CYAN_SOUL_CANDLE))
    val LIGHT_BLUE_SOUL_CANDLE_CAKE =
        registerNoItem("light_blue_soul_candle_cake", soulCandleCakeOf(LIGHT_BLUE_SOUL_CANDLE))
    val BLUE_SOUL_CANDLE_CAKE = registerNoItem("blue_soul_candle_cake", soulCandleCakeOf(BLUE_SOUL_CANDLE))
    val PURPLE_SOUL_CANDLE_CAKE = registerNoItem("purple_soul_candle_cake", soulCandleCakeOf(PURPLE_SOUL_CANDLE))
    val MAGENTA_SOUL_CANDLE_CAKE = registerNoItem("magenta_soul_candle_cake", soulCandleCakeOf(MAGENTA_SOUL_CANDLE))
    val PINK_SOUL_CANDLE_CAKE = registerNoItem("pink_soul_candle_cake", soulCandleCakeOf(PINK_SOUL_CANDLE))
    val BIG_SOUL_CANDLE_CAKE =
        registerNoItem("big_soul_candle_cake", bigSoulCandleCakeOf(BIG_SOUL_CANDLE, SOUL_CANDLE_CAKE))
    val BIG_WHITE_SOUL_CANDLE_CAKE =
        registerNoItem("big_white_soul_candle_cake", bigSoulCandleCakeOf(BIG_WHITE_SOUL_CANDLE))
    val BIG_LIGHT_GRAY_SOUL_CANDLE_CAKE =
        registerNoItem("big_light_gray_soul_candle_cake", bigSoulCandleCakeOf(BIG_LIGHT_GRAY_SOUL_CANDLE))
    val BIG_GRAY_SOUL_CANDLE_CAKE =
        registerNoItem("big_gray_soul_candle_cake", bigSoulCandleCakeOf(BIG_GRAY_SOUL_CANDLE))
    val BIG_BLACK_SOUL_CANDLE_CAKE =
        registerNoItem("big_black_soul_candle_cake", bigSoulCandleCakeOf(BIG_BLACK_SOUL_CANDLE))
    val BIG_BROWN_SOUL_CANDLE_CAKE =
        registerNoItem("big_brown_soul_candle_cake", bigSoulCandleCakeOf(BIG_BROWN_SOUL_CANDLE))
    val BIG_RED_SOUL_CANDLE_CAKE =
        registerNoItem("big_red_soul_candle_cake", bigSoulCandleCakeOf(BIG_RED_SOUL_CANDLE))
    val BIG_ORANGE_SOUL_CANDLE_CAKE =
        registerNoItem("big_orange_soul_candle_cake", bigSoulCandleCakeOf(BIG_ORANGE_SOUL_CANDLE))
    val BIG_YELLOW_SOUL_CANDLE_CAKE =
        registerNoItem("big_yellow_soul_candle_cake", bigSoulCandleCakeOf(BIG_YELLOW_SOUL_CANDLE))
    val BIG_LIME_SOUL_CANDLE_CAKE =
        registerNoItem("big_lime_soul_candle_cake", bigSoulCandleCakeOf(BIG_LIME_SOUL_CANDLE))
    val BIG_GREEN_SOUL_CANDLE_CAKE =
        registerNoItem("big_green_soul_candle_cake", bigSoulCandleCakeOf(BIG_GREEN_SOUL_CANDLE))
    val BIG_CYAN_SOUL_CANDLE_CAKE =
        registerNoItem("big_cyan_soul_candle_cake", bigSoulCandleCakeOf(BIG_CYAN_SOUL_CANDLE))
    val BIG_LIGHT_BLUE_SOUL_CANDLE_CAKE =
        registerNoItem("big_light_blue_soul_candle_cake", bigSoulCandleCakeOf(BIG_LIGHT_BLUE_SOUL_CANDLE))
    val BIG_BLUE_SOUL_CANDLE_CAKE =
        registerNoItem("big_blue_soul_candle_cake", bigSoulCandleCakeOf(BIG_BLUE_SOUL_CANDLE))
    val BIG_PURPLE_SOUL_CANDLE_CAKE =
        registerNoItem("big_purple_soul_candle_cake", bigSoulCandleCakeOf(BIG_PURPLE_SOUL_CANDLE))
    val BIG_MAGENTA_SOUL_CANDLE_CAKE =
        registerNoItem("big_magenta_soul_candle_cake", bigSoulCandleCakeOf(BIG_MAGENTA_SOUL_CANDLE))
    val BIG_PINK_SOUL_CANDLE_CAKE =
        registerNoItem("big_pink_soul_candle_cake", bigSoulCandleCakeOf(BIG_PINK_SOUL_CANDLE))

    val NETHERRACK_STAIRS = register("netherrack_stairs", stairsOf(NETHERRACK).pickaxe())
    val NETHERRACK_SLAB = register("netherrack_slab", slabOf(NETHERRACK).pickaxe())
    val NETHERRACK_WALL = register("netherrack_wall", wallOf(NETHERRACK).pickaxe())
    val WARPED_WART = register(
        "warped_wart",
        WarpedNetherWartBlock(
            Settings.create().mapColor(MapColor.WARPED_STEM).noCollision().ticksRandomly()
                .sounds(BlockSoundGroup.NETHER_WART).pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().sword().axe()
    )
    val NETHER_BRICK_PILLAR = register("nether_brick_pillar", PillarBlock(copy(NETHER_BRICKS)).pickaxe())
    val POLISHED_NETHER_BRICKS = register("polished_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val POLISHED_NETHER_BRICK_STAIRS = register("polished_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val POLISHED_NETHER_BRICK_SLAB = register("polished_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val POLISHED_NETHER_BRICK_WALL = register("polished_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val MIXED_NETHER_BRICKS = register("mixed_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_MIXED_NETHER_BRICKS =
        register("cracked_mixed_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_NETHER_BRICK_STAIRS = register("mixed_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val MIXED_NETHER_BRICK_SLAB = register("mixed_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val MIXED_NETHER_BRICK_WALL = register("mixed_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val MIXED_NETHER_BRICK_FENCE =
        register("mixed_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_NETHER_BRICKS =
        register("chiseled_mixed_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_NETHER_BRICK_PILLAR =
        register("mixed_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_NETHER_BRICKS)).pickaxe())
    val CRACKED_RED_NETHER_BRICKS =
        register("cracked_red_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_FENCE = register("red_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_RED_NETHER_BRICKS =
        register("chiseled_red_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_PILLAR =
        register("red_nether_brick_pillar", PillarBlock(copy(RED_NETHER_BRICKS)).pickaxe())
    val POLISHED_RED_NETHER_BRICKS =
        register("polished_red_nether_bricks", Block(copy(RED_NETHER_BRICKS)).pickaxe())
    val POLISHED_RED_NETHER_BRICK_STAIRS =
        register("polished_red_nether_brick_stairs", stairsOf(RED_NETHER_BRICK_STAIRS).pickaxe())
    val POLISHED_RED_NETHER_BRICK_SLAB =
        register("polished_red_nether_brick_slab", slabOf(RED_NETHER_BRICK_SLAB).pickaxe())
    val POLISHED_RED_NETHER_BRICK_WALL =
        register("polished_red_nether_brick_wall", wallOf(RED_NETHER_BRICK_WALL).pickaxe())
    val MIXED_BLUE_NETHER_BRICKS = register("mixed_blue_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_MIXED_BLUE_NETHER_BRICKS =
        register("cracked_mixed_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_STAIRS =
        register("mixed_blue_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_SLAB = register("mixed_blue_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_WALL = register("mixed_blue_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_FENCE =
        register("mixed_blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_BLUE_NETHER_BRICKS =
        register("chiseled_mixed_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_PILLAR =
        register("mixed_blue_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_BLUE_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICKS = register("blue_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_BLUE_NETHER_BRICKS =
        register("cracked_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_STAIRS = register("blue_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val BLUE_NETHER_BRICK_SLAB = register("blue_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val BLUE_NETHER_BRICK_WALL = register("blue_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val BLUE_NETHER_BRICK_FENCE =
        register("blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_BLUE_NETHER_BRICKS =
        register("chiseled_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_PILLAR =
        register("blue_nether_brick_pillar", PillarBlock(copy(BLUE_NETHER_BRICKS)).pickaxe())
    val POLISHED_BLUE_NETHER_BRICKS =
        register("polished_blue_nether_bricks", Block(copy(BLUE_NETHER_BRICKS)).pickaxe())
    val POLISHED_BLUE_NETHER_BRICK_STAIRS =
        register("polished_blue_nether_brick_stairs", stairsOf(BLUE_NETHER_BRICK_STAIRS).pickaxe())
    val POLISHED_BLUE_NETHER_BRICK_SLAB =
        register("polished_blue_nether_brick_slab", slabOf(BLUE_NETHER_BRICK_SLAB).pickaxe())
    val POLISHED_BLUE_NETHER_BRICK_WALL =
        register("polished_blue_nether_brick_wall", wallOf(BLUE_NETHER_BRICK_WALL).pickaxe())
    val MIXED_GRAY_NETHER_BRICKS = register("mixed_gray_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_MIXED_GRAY_NETHER_BRICKS =
        register("cracked_mixed_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_STAIRS =
        register("mixed_gray_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_SLAB = register("mixed_gray_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_WALL = register("mixed_gray_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_FENCE =
        register("mixed_gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_GRAY_NETHER_BRICKS =
        register("chiseled_mixed_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_PILLAR =
        register("mixed_gray_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_GRAY_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICKS = register("gray_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_GRAY_NETHER_BRICKS =
        register("cracked_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_STAIRS = register("gray_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val GRAY_NETHER_BRICK_SLAB = register("gray_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val GRAY_NETHER_BRICK_WALL = register("gray_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val GRAY_NETHER_BRICK_FENCE =
        register("gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_GRAY_NETHER_BRICKS =
        register("chiseled_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_PILLAR =
        register("gray_nether_brick_pillar", PillarBlock(copy(GRAY_NETHER_BRICKS)).pickaxe())
    val POLISHED_GRAY_NETHER_BRICKS =
        register("polished_gray_nether_bricks", Block(copy(GRAY_NETHER_BRICKS)).pickaxe())
    val POLISHED_GRAY_NETHER_BRICK_STAIRS =
        register("polished_gray_nether_brick_stairs", stairsOf(GRAY_NETHER_BRICK_STAIRS).pickaxe())
    val POLISHED_GRAY_NETHER_BRICK_SLAB =
        register("polished_gray_nether_brick_slab", slabOf(GRAY_NETHER_BRICK_SLAB).pickaxe())
    val POLISHED_GRAY_NETHER_BRICK_WALL =
        register("polished_gray_nether_brick_wall", wallOf(GRAY_NETHER_BRICK_WALL).pickaxe())

    val ROOT_BLOCK = register(
        "root_block",
        MangroveRootsBlock(
            Settings.create().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS)
                .strength(0.7f).nonOpaque().suffocates(Blocks::nonSolid).blockVision(Blocks::nonSolid).nonOpaque()
                .lavaIgnitable().sounds(rootBlockSound)
        ).cutout().sword().axe()
    )

    val BLUE_PETALS = register(
        "blue_petals", PinkPetalsBlock(
            Settings.create().mapColor(MapColor.PLANT)
                .noCollision().sounds(BlockSoundGroup.PINK_PETALS).pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().sword().hoe()
    )

    val WILD_WHEAT = registerNoItem(
        "wild_wheat",
        TallPlantBlock(
            Settings.create().mapColor(MapColor.PLANT).noCollision().breakInstantly()
                .sounds(BlockSoundGroup.CROP).offsetType(OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().axe()
    )
    val GOLDEN_BEETROOTS = registerNoItem(
        "golden_beetroots",
        GoldenBeetrootsBlock(
            Settings.create().mapColor(MapColor.GOLD).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().axe()
    )
    val MOONBERRY_VINE = register(
        "moonberry_vine",
        MoonberryVineBlock(
            Settings.create().mapColor(MapColor.PURPLE).noCollision().strength(0.2f)
                .sounds(BlockSoundGroup.CAVE_VINES).luminance(MoonberryVineBlock.getLuminanceSupplier(8, 11))
                .lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().axe().sword()
    )
    val MOONBERRY_VINELET = registerNoItem(
        "moonberry_vinelet", MoonberryVineletBlock(
            Settings.create().mapColor(MapColor.PURPLE).noCollision().strength(0.2f).ticksRandomly()
                .breakInstantly().sounds(BlockSoundGroup.CAVE_VINES).lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY)
        ).cutout().axe().sword()
    )

    val ROCKY_DIRT =
        register(
            "dirty_rocks",
            Block(copy(DIRT).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val ROCKY_GRASS = register(
        "grassy_rocks",
        RockyGrassBlock(ROCKY_DIRT, copy(GRASS_BLOCK).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe()
            .shovel()
    )
    val ROCKY_PODZOL =
        register(
            "podzol_rocks",
            SnowyBlock(copy(PODZOL).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val ROCKY_MYCELIUM = register(
        "mycelium_rocks",
        RockyMyceliumBlock(ROCKY_DIRT, copy(MYCELIUM).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe()
            .shovel()
    )
    val ROCKY_COARSE_DIRT = register(
        "coarsely_dirty_rocks",
        Block(copy(COARSE_DIRT).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
    )
    val ROCKY_DIRT_PATH = register(
        "rocky_path",
        RockyDirtPathBlock(ROCKY_DIRT, copy(DIRT_PATH).mapColor(COBBLESTONE.defaultMapColor)).cutout()
    )
    val ROCKY_MUD = register("muddy_rocks", MudBlock(copy(MUD).mapColor(COBBLESTONE.defaultMapColor)).cutout())
    val ROCKY_SNOW =
        register("snowy_rocks", Block(copy(SNOW_BLOCK).mapColor(COBBLESTONE.defaultMapColor)).cutout())
    val ROCKY_GRAVEL = register(
        "rocky_rocks",
        GravelBlock(Color(-8356741), copy(GRAVEL).mapColor(COBBLESTONE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val ROCKY_SAND = register(
        "sandy_rocks",
        GravelBlock(Color(14406560), copy(SAND).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
    )
    val ROCKY_RED_SAND = register(
        "red_sandy_rocks", GravelBlock(Color(11098145), copy(RED_SAND).mapColor(COBBLESTONE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val ROCKY_SOUL_SAND =
        register(
            "rocky_soul_sand",
            SoulSandBlock(copy(SOUL_SAND).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val ROCKY_SOUL_SOIL = register(
        "rocky_soul_soil",
        Block(copy(SOUL_SOIL).mapColor(COBBLESTONE.defaultMapColor)).cutout().pickaxe().shovel()
    )

    val SLATED_DIRT =
        register(
            "dirty_slate",
            Block(copy(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_GRASS = register(
        "grassy_slate",
        RockyGrassBlock(SLATED_DIRT, copy(GRASS_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_PODZOL =
        register(
            "podzol_slate",
            SnowyBlock(copy(PODZOL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_MYCELIUM = register(
        "mycelium_slate",
        RockyMyceliumBlock(SLATED_DIRT, copy(MYCELIUM).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout()
            .pickaxe()
            .shovel()
    )
    val SLATED_COARSE_DIRT =
        register(
            "coarsely_dirty_slate",
            Block(copy(COARSE_DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_DIRT_PATH = register(
        "slated_path",
        RockyDirtPathBlock(SLATED_DIRT, copy(DIRT_PATH).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_MUD =
        register(
            "muddy_slate",
            MudBlock(copy(MUD).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_SNOW = register(
        "snowy_slate",
        Block(copy(SNOW_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
    )
    val SLATED_GRAVEL = register(
        "slated_slate",
        GravelBlock(Color(-8356741), copy(GRAVEL).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_SAND = register(
        "sandy_slate",
        GravelBlock(Color(14406560), copy(SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_RED_SAND = register(
        "red_sandy_slate",
        GravelBlock(Color(11098145), copy(RED_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val SLATED_SOUL_SAND =
        register(
            "slated_soul_sand",
            SoulSandBlock(copy(SOUL_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val SLATED_SOUL_SOIL =
        register(
            "slated_soul_soil",
            Block(copy(SOUL_SOIL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )

    val BLACKSTONE_DIRT =
        register(
            "dirty_blackstone",
            Block(copy(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_GRASS = register(
        "grassy_blackstone",
        RockyGrassBlock(BLACKSTONE_DIRT, copy(GRASS_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_PODZOL =
        register(
            "podzol_blackstone",
            SnowyBlock(copy(PODZOL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_MYCELIUM = register(
        "mycelium_blackstone",
        RockyMyceliumBlock(BLACKSTONE_DIRT, copy(MYCELIUM).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_COARSE_DIRT =
        register(
            "coarsely_dirty_blackstone",
            Block(copy(COARSE_DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_DIRT_PATH = register(
        "blackstoned_path",
        RockyDirtPathBlock(BLACKSTONE_DIRT, copy(DIRT_PATH).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_MUD =
        register(
            "muddy_blackstone",
            MudBlock(copy(MUD).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_SNOW =
        register(
            "snowy_blackstone",
            Block(copy(SNOW_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )
    val BLACKSTONE_GRAVEL = register(
        "blackstoned_blackstone",
        GravelBlock(Color(-8356741), copy(GRAVEL).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_SAND = register(
        "sandy_blackstone",
        GravelBlock(Color(14406560), copy(SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_RED_SAND = register(
        "red_sandy_blackstone",
        GravelBlock(Color(11098145), copy(RED_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor))
            .cutout().pickaxe().shovel()
    )
    val BLACKSTONE_SOUL_SAND = register(
        "blackstoned_soul_sand",
        SoulSandBlock(copy(SOUL_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
    )
    val BLACKSTONE_SOUL_SOIL =
        register(
            "blackstoned_soul_soil",
            Block(copy(SOUL_SOIL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)).cutout().pickaxe().shovel()
        )

    fun init() {
        DnDFamilies.init()
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

    fun Block.cutout(): Block {
        CUTOUT_BLOCKS.add(this)
        return this
    }

    fun Block.sword(): Block {
        SWORDABLE.add(this)
        return this
    }

    fun Block.pickaxe(): Block {
        PICKAXABLE.add(this)
        return this
    }

    fun Block.axe(): Block {
        AXABLE.add(this)
        return this
    }

    fun Block.shovel(): Block {
        SHOVELABLE.add(this)
        return this
    }

    fun Block.hoe(): Block {
        HOEABLE.add(this)
        return this
    }

    fun register(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        DnDItems.register(id, BlockItem(regBlock))
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCKS.add(regBlock)
        return regBlock
    }

}
