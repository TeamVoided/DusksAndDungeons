package org.teamvoided.dusk_autumn.init.blocks

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.block.sapling.SaplingBlock
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.dusk_autumn.block.DnDWoodTypes
import org.teamvoided.dusk_autumn.block.FallingLeavesBlock
import org.teamvoided.dusk_autumn.block.sapling.SaplingGenerators
import org.teamvoided.dusk_autumn.block.sapling.ThreeWideTreeSaplingBlock
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDParticles
import org.teamvoided.dusk_autumn.util.*

object DnDWoodBlocks {
    val CASCADE_SAPLING = DnDBlocks.register(
        "cascade_sapling", ThreeWideTreeSaplingBlock(
            SaplingGenerators.CASCADE,
            Settings.create()
                .mapColor(MapColor.RED).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.CHERRY_SAPLING).pistonBehavior(PistonBehavior.DESTROY)
        )
    ).cutout()
    val POTTED_CASCADE_SAPLING = DnDBlocks.registerNoItem(
        "potted_cascade_sapling",
        Blocks.pottedVariant(CASCADE_SAPLING)
    ).cutout()
    val CASCADE_LEAVES = DnDBlocks.register(
        "cascade_leaves", FallingLeavesBlock(
            Settings.create().strength(0.2f).ticksRandomly()
                .nonOpaque().allowsSpawning(Blocks::allowOcelotsAndParrots).suffocates(Blocks::nonSolid)
                .blockVision(Blocks::nonSolid)
                .lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::nonSolid)
                .sounds(BlockSoundGroup.AZALEA_LEAVES)
                .mapColor(MapColor.RED), DnDParticles.CASCADE_LEAF_PARTICLE
        ).cutout().flammableLeaves().axe()
    )
    val CASCADE_LEAF_PILE = DnDBlocks.register(
        "cascade_leaf_pile",
        fallingLeafPile(DnDParticles.CASCADE_LEAF_PARTICLE, MapColor.RED, BlockSoundGroup.AZALEA_LEAVES).cutout()
    )
    val CASCADE_LOG = DnDBlocks.register(
        "cascade_log", Blocks.logOf(MapColor.BLUE, MapColor.BROWN, BlockSoundGroup.CHERRY_WOOD)
    )
    val HOLLOW_CASCADE_LOG = DnDBlocks.register("hollow_cascade_log", hollowLog(CASCADE_LOG))
    val CASCADE_WOOD = DnDBlocks.register(
        "cascade_wood", PillarBlock(
            Settings.create().mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0f)
                .sounds(BlockSoundGroup.CHERRY_WOOD).lavaIgnitable()
        )
    )
    val CASCADE_WOOD_STAIRS = DnDBlocks.register("cascade_wood_stairs", stairsOf(CASCADE_WOOD))
    val CASCADE_WOOD_SLAB = DnDBlocks.register("cascade_wood_slab", slabOf(CASCADE_WOOD))
    val CASCADE_WOOD_WALL = DnDBlocks.register("cascade_wood_wall", wallOf(CASCADE_WOOD))
    val CASCADE_LOG_PILE = DnDBlocks.register("cascade_log_pile", logPile(CASCADE_WOOD))
    val STRIPPED_CASCADE_LOG = DnDBlocks.register(
        "stripped_cascade_log", Blocks.logOf(MapColor.BLUE, MapColor.BLUE, BlockSoundGroup.CHERRY_WOOD)
    )
    val HOLLOW_STRIPPED_CASCADE_LOG =
        DnDBlocks.register("hollow_stripped_cascade_log", hollowLog(STRIPPED_CASCADE_LOG))
    val STRIPPED_CASCADE_WOOD = DnDBlocks.register(
        "stripped_cascade_wood",
        PillarBlock(copy(CASCADE_WOOD).mapColor(MapColor.BLUE))
    )
    val CASCADE_PLANKS = DnDBlocks.register(
        "cascade_planks", Block(
            Settings.create()
                .mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.CHERRY_WOOD).lavaIgnitable()
        )
    ).flammablePlanks()
    val CASCADE_STAIRS = DnDBlocks.register("cascade_stairs", stairsOf(CASCADE_PLANKS).axe().flammablePlanks())
    val CASCADE_SLAB = DnDBlocks.register("cascade_slab", slabOf(CASCADE_PLANKS).axe().flammablePlanks())
    val CASCADE_FENCE = DnDBlocks.register("cascade_fence", fenceOf(CASCADE_PLANKS).axe().flammablePlanks())
    val CASCADE_FENCE_GATE = DnDBlocks.register(
        "cascade_fence_gate", fenceGateOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS).axe().flammablePlanks()
    )
    val CASCADE_DOOR = DnDBlocks.registerNoItem(
        "cascade_door",
        doorOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS).axe().flammablePlanks()
    )
    val BLUE_DOOR = DnDBlocks.registerNoItem(
        "blue_door", DoorBlock(
            BlockSetType.DARK_OAK,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
        ).axe().flammablePlanks()
    )
    val CASCADE_TRAPDOOR = DnDBlocks.register(
        "cascade_trapdoor",
        trapdoorOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS).axe().flammablePlanks()
    )
    val CASCADE_PRESSURE_PLATE = DnDBlocks.register(
        "cascade_pressure_plate",
        pressurePlateOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS).axe().flammablePlanks()
    )
    val CASCADE_BUTTON = DnDBlocks.register(
        "cascade_button",
        Blocks.buttonOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE).axe().flammablePlanks()
    )
    val CASCADE_SIGN = DnDBlocks.registerNoItem(
        "cascade_sign",
        signOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS).axe().flammablePlanks()
    )
    val CASCADE_WALL_SIGN = DnDBlocks.registerNoItem(
        "cascade_wall_sign",
        wallSignOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS, CASCADE_SIGN).axe().flammablePlanks()
    )
    val CASCADE_HANGING_SIGN = DnDBlocks.registerNoItem(
        "cascade_hanging_sign",
        hangingSignOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS).axe().flammablePlanks()
    )
    val CASCADE_WALL_HANGING_SIGN = DnDBlocks.registerNoItem(
        "cascade_wall_hanging_sign", wallHangingSignOf(
            DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS, CASCADE_HANGING_SIGN
        ).axe().flammablePlanks()
    )

    val GOLDEN_BIRCH_SAPLING = DnDBlocks.register(
        "golden_birch_sapling", SaplingBlock(
            SaplingGenerators.GOLDEN_BIRCH,
            copy(Blocks.BIRCH_SAPLING).mapColor(MapColor.YELLOW)
        ).cutout()
    )
    val POTTED_GOLDEN_BIRCH_SAPLING =
        DnDBlocks.registerNoItem("potted_golden_birch_sapling", Blocks.pottedVariant(GOLDEN_BIRCH_SAPLING).cutout())
    val GOLDEN_BIRCH_LEAVES = DnDBlocks.register(
        "golden_birch_leaves", LeavesBlock(
            copy(Blocks.BIRCH_LEAVES).mapColor(MapColor.YELLOW)
        ).cutout().flammableLeaves()
    )
    val GOLDEN_BIRCH_LEAF_PILE = DnDBlocks.register(
        "golden_birch_leaf_pile",
        leafPile(MapColor.YELLOW).cutout()
    )

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

    val GALLERY_MAPLE_SAPLING = DnDBlocks.register(
        "gallery_maple_sapling", ThreeWideTreeSaplingBlock(
            SaplingGenerators.CASCADE,
            Settings.create()
                .mapColor(MapColor.RED).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.AZALEA)
                .pistonBehavior(PistonBehavior.DESTROY).luminance(light(1))
        )
    ).cutout()
    val POTTED_GALLERY_MAPLE_SAPLING =
        DnDBlocks.registerNoItem("potted_gallery_maple_sapling", Blocks.pottedVariant(GALLERY_MAPLE_SAPLING)).cutout()
    val GALLERY_MAPLE_LEAVES = DnDBlocks.register(
        "gallery_maple_leaves", LeavesBlock(
            Settings.create().strength(0.2f).ticksRandomly()
                .nonOpaque().allowsSpawning(Blocks::allowOcelotsAndParrots).suffocates(Blocks::nonSolid)
                .blockVision(Blocks::nonSolid).pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::nonSolid)
                .sounds(BlockSoundGroup.GRASS).mapColor(MapColor.RED)
        ).cutout().axe()
    )
    val GALLERY_MAPLE_LEAF_PILE = DnDBlocks.register(
        "gallery_maple_leaf_pile",
        fallingLeafPile(DnDParticles.CASCADE_LEAF_PARTICLE, MapColor.RED).cutout()
    )
    val GALLERY_MAPLE_LOG = DnDBlocks.register(
        "gallery_maple_log", Blocks.logOf(MapColor.GRAY, MapColor.BROWN, BlockSoundGroup.WOOD)
    )
    val HOLLOW_GALLERY_MAPLE_LOG = DnDBlocks.register("hollow_gallery_maple_log", hollowLog(GALLERY_MAPLE_LOG))
    val GALLERY_MAPLE_WOOD = DnDBlocks.register(
        "gallery_maple_wood", PillarBlock(
            Settings.create().mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0f)
                .sounds(BlockSoundGroup.WOOD)
        )
    )
    val GALLERY_MAPLE_WOOD_STAIRS = DnDBlocks.register("gallery_maple_wood_stairs", stairsOf(GALLERY_MAPLE_WOOD))
    val GALLERY_MAPLE_WOOD_SLAB = DnDBlocks.register("gallery_maple_wood_slab", slabOf(GALLERY_MAPLE_WOOD))
    val GALLERY_MAPLE_WOOD_WALL = DnDBlocks.register("gallery_maple_wood_wall", wallOf(GALLERY_MAPLE_WOOD))
    val GALLERY_MAPLE_LOG_PILE = DnDBlocks.register("gallery_maple_log_pile", logPile(GALLERY_MAPLE_WOOD))
    val STRIPPED_GALLERY_MAPLE_LOG = DnDBlocks.register(
        "stripped_gallery_maple_log", Blocks.logOf(MapColor.GRAY, MapColor.GRAY, BlockSoundGroup.WOOD)
    )
    val HOLLOW_STRIPPED_GALLERY_MAPLE_LOG =
        DnDBlocks.register("hollow_stripped_gallery_maple_log", hollowLog(STRIPPED_GALLERY_MAPLE_LOG))
    val STRIPPED_GALLERY_MAPLE_WOOD = DnDBlocks.register(
        "stripped_gallery_maple_wood", PillarBlock(
            copy(GALLERY_MAPLE_WOOD).mapColor(MapColor.GRAY)
        )
    )
    val GALLERY_MAPLE_PLANKS = DnDBlocks.register(
        "gallery_maple_planks", Block(
            Settings.create()
                .mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.WOOD)
        ).axe()
    )
    val GALLERY_MAPLE_STAIRS = DnDBlocks.register("gallery_maple_stairs", stairsOf(GALLERY_MAPLE_PLANKS).axe())
    val GALLERY_MAPLE_SLAB = DnDBlocks.register("gallery_maple_slab", slabOf(GALLERY_MAPLE_PLANKS).axe())
    val GALLERY_MAPLE_FENCE = DnDBlocks.register("gallery_maple_fence", fenceOf(GALLERY_MAPLE_PLANKS).axe())
    val GALLERY_MAPLE_FENCE_GATE = DnDBlocks.register(
        "gallery_maple_fence_gate",
        fenceGateOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS).axe()
    )
    val GALLERY_MAPLE_DOOR = DnDBlocks.registerNoItem(
        "gallery_maple_door",
        doorOf(DnDWoodTypes.GALLERY_MAPLE_BLOCK_SET_TYPE, GALLERY_MAPLE_PLANKS).cutout().axe()
    )
    val GALLERY_MAPLE_TRAPDOOR = DnDBlocks.register(
        "gallery_maple_trapdoor",
        trapdoorOf(DnDWoodTypes.GALLERY_MAPLE_BLOCK_SET_TYPE, GALLERY_MAPLE_DOOR).cutout().axe()
    )
    val GALLERY_MAPLE_PRESSURE_PLATE = DnDBlocks.register(
        "gallery_maple_pressure_plate",
        pressurePlateOf(DnDWoodTypes.GALLERY_MAPLE_BLOCK_SET_TYPE, GALLERY_MAPLE_PLANKS).axe()
    )
    val GALLERY_MAPLE_BUTTON =
        DnDBlocks.register("gallery_maple_button", Blocks.buttonOf(DnDWoodTypes.GALLERY_MAPLE_BLOCK_SET_TYPE).axe())
    val GALLERY_MAPLE_SIGN = DnDBlocks.registerNoItem(
        "gallery_maple_sign", signOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS).axe()
    )
    val GALLERY_MAPLE_WALL_SIGN = DnDBlocks.registerNoItem(
        "gallery_maple_wall_sign",
        wallSignOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS, GALLERY_MAPLE_SIGN).axe()
    )
    val GALLERY_MAPLE_HANGING_SIGN = DnDBlocks.registerNoItem(
        "gallery_maple_hanging_sign", hangingSignOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS).axe()
    )
    val GALLERY_MAPLE_WALL_HANGING_SIGN = DnDBlocks.registerNoItem(
        "gallery_maple_wall_hanging_sign",
        wallHangingSignOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS, GALLERY_MAPLE_HANGING_SIGN).axe()
    )

    val BONEWOOD_PLANKS = DnDBlocks.register(
        "bonewood_planks", Block(
            Settings.create()
                .mapColor(MapColor.SNOW).instrument(NoteBlockInstrument.XYLOPHONE).strength(2.0F, 3.0F)
                .sounds(bonewoodSound)
        ).axe()
    )
    val BONEWOOD_STAIRS = DnDBlocks.register("bonewood_stairs", stairsOf(BONEWOOD_PLANKS).axe())
    val BONEWOOD_SLAB = DnDBlocks.register("bonewood_slab", slabOf(BONEWOOD_PLANKS).axe())
    val BONEWOOD_FENCE = DnDBlocks.register("bonewood_fence", fenceOf(BONEWOOD_PLANKS).axe())
    val BONEWOOD_FENCE_GATE = DnDBlocks.register(
        "bonewood_fence_gate",
        FenceGateBlock(
            DnDWoodTypes.BONEWOOD_WOOD_TYPE,
            copy(BONEWOOD_PLANKS).solid()

        ).axe()
    )
    val BONEWOOD_DOOR = DnDBlocks.registerNoItem(
        "bonewood_door", DoorBlock(
            DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE,
            copy(BONEWOOD_PLANKS).strength(3.0f).nonOpaque(),
        ).cutout().axe()
    )
    val BONEWOOD_TRAPDOOR = DnDBlocks.register(
        "bonewood_trapdoor", TrapdoorBlock(
            DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE,
            copy(BONEWOOD_DOOR).allowsSpawning(Blocks::nonSpawnable),
        ).cutout().axe()
    )
    val WITHERING_BONEWOOD_PLANKS = DnDBlocks.register(
        "withering_bonewood_planks", Block(
            copy(BONEWOOD_PLANKS).mapColor(MapColor.BLACK).sounds(witheringBonewoodSound)
        ).axe()
    )
    val WITHERING_BONEWOOD_STAIRS =
        DnDBlocks.register("withering_bonewood_stairs", stairsOf(WITHERING_BONEWOOD_PLANKS).axe())
    val WITHERING_BONEWOOD_SLAB = DnDBlocks.register("withering_bonewood_slab", slabOf(WITHERING_BONEWOOD_PLANKS).axe())
    val WITHERING_BONEWOOD_FENCE =
        DnDBlocks.register("withering_bonewood_fence", fenceOf(WITHERING_BONEWOOD_PLANKS).axe())
    val WITHERING_BONEWOOD_FENCE_GATE = DnDBlocks.register(
        "withering_bonewood_fence_gate",
        FenceGateBlock(
            DnDWoodTypes.WITHERING_BONEWOOD_WOOD_TYPE,
            copy(WITHERING_BONEWOOD_PLANKS).solid()
        ).axe()
    )
    val WITHERING_BONEWOOD_DOOR = DnDBlocks.registerNoItem(
        "withering_bonewood_door", DoorBlock(
            DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE,
            copy(WITHERING_BONEWOOD_PLANKS).strength(3.0f).nonOpaque(),
        ).cutout().axe()
    )
    val WITHERING_BONEWOOD_TRAPDOOR = DnDBlocks.register(
        "withering_bonewood_trapdoor", TrapdoorBlock(
            DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE,
            copy(WITHERING_BONEWOOD_DOOR).allowsSpawning(Blocks::nonSpawnable),
        ).cutout().axe()
    )

    val HOLLOW_OAK_LOG = DnDBlocks.register("hollow_oak_log", hollowLog(Blocks.OAK_LOG))
    val HOLLOW_STRIPPED_OAK_LOG = DnDBlocks.register("hollow_stripped_oak_log", hollowLog(Blocks.STRIPPED_OAK_LOG))
    val HOLLOW_SPRUCE_LOG = DnDBlocks.register("hollow_spruce_log", hollowLog(Blocks.SPRUCE_LOG))
    val HOLLOW_STRIPPED_SPRUCE_LOG =
        DnDBlocks.register("hollow_stripped_spruce_log", hollowLog(Blocks.STRIPPED_SPRUCE_LOG))
    val HOLLOW_BIRCH_LOG = DnDBlocks.register("hollow_birch_log", hollowLog(Blocks.BIRCH_LOG))
    val HOLLOW_STRIPPED_BIRCH_LOG =
        DnDBlocks.register("hollow_stripped_birch_log", hollowLog(Blocks.STRIPPED_BIRCH_LOG))
    val HOLLOW_JUNGLE_LOG = DnDBlocks.register("hollow_jungle_log", hollowLog(Blocks.JUNGLE_LOG))
    val HOLLOW_STRIPPED_JUNGLE_LOG =
        DnDBlocks.register("hollow_stripped_jungle_log", hollowLog(Blocks.STRIPPED_JUNGLE_LOG))
    val HOLLOW_ACACIA_LOG = DnDBlocks.register("hollow_acacia_log", hollowLog(Blocks.ACACIA_LOG))
    val HOLLOW_STRIPPED_ACACIA_LOG =
        DnDBlocks.register("hollow_stripped_acacia_log", hollowLog(Blocks.STRIPPED_ACACIA_LOG))
    val HOLLOW_DARK_OAK_LOG = DnDBlocks.register("hollow_dark_oak_log", hollowLog(Blocks.DARK_OAK_LOG))
    val HOLLOW_STRIPPED_DARK_OAK_LOG =
        DnDBlocks.register("hollow_stripped_dark_oak_log", hollowLog(Blocks.STRIPPED_DARK_OAK_LOG))
    val HOLLOW_MANGROVE_LOG = DnDBlocks.register("hollow_mangrove_log", hollowLog(Blocks.MANGROVE_LOG))
    val HOLLOW_STRIPPED_MANGROVE_LOG =
        DnDBlocks.register("hollow_stripped_mangrove_log", hollowLog(Blocks.STRIPPED_MANGROVE_LOG))
    val HOLLOW_CHERRY_LOG = DnDBlocks.register("hollow_cherry_log", hollowLog(Blocks.CHERRY_LOG))
    val HOLLOW_STRIPPED_CHERRY_LOG =
        DnDBlocks.register("hollow_stripped_cherry_log", hollowLog(Blocks.STRIPPED_CHERRY_LOG))
    val HOLLOW_BAMBOO_BLOCK = DnDBlocks.register("hollow_bamboo_block", hollowBambooBlock(Blocks.BAMBOO_BLOCK))
    val HOLLOW_STRIPPED_BAMBOO_BLOCK =
        DnDBlocks.register("hollow_stripped_bamboo_block", hollowBambooBlock(Blocks.STRIPPED_BAMBOO_BLOCK))
    val HOLLOW_CRIMSON_STEM = DnDBlocks.register("hollow_crimson_stem", hollowLog(Blocks.CRIMSON_HYPHAE))
    val HOLLOW_STRIPPED_CRIMSON_STEM =
        DnDBlocks.register("hollow_stripped_crimson_stem", hollowLog(Blocks.STRIPPED_CRIMSON_HYPHAE))
    val HOLLOW_WARPED_STEM = DnDBlocks.register("hollow_warped_stem", hollowLog(Blocks.WARPED_HYPHAE))
    val HOLLOW_STRIPPED_WARPED_STEM =
        DnDBlocks.register("hollow_stripped_warped_stem", hollowLog(Blocks.STRIPPED_WARPED_HYPHAE))

    val OAK_WOOD_STAIRS = DnDBlocks.register("oak_wood_stairs", stairsOf(Blocks.OAK_WOOD))
    val OAK_WOOD_SLAB = DnDBlocks.register("oak_wood_slab", slabOf(Blocks.OAK_WOOD))
    val OAK_WOOD_WALL = DnDBlocks.register("oak_wood_wall", wallOf(Blocks.OAK_WOOD))
    val SPRUCE_WOOD_STAIRS = DnDBlocks.register("spruce_wood_stairs", stairsOf(Blocks.SPRUCE_WOOD))
    val SPRUCE_WOOD_SLAB = DnDBlocks.register("spruce_wood_slab", slabOf(Blocks.SPRUCE_WOOD))
    val SPRUCE_WOOD_WALL = DnDBlocks.register("spruce_wood_wall", wallOf(Blocks.SPRUCE_WOOD))
    val BIRCH_WOOD_STAIRS = DnDBlocks.register("birch_wood_stairs", stairsOf(Blocks.BIRCH_WOOD))
    val BIRCH_WOOD_SLAB = DnDBlocks.register("birch_wood_slab", slabOf(Blocks.BIRCH_WOOD))
    val BIRCH_WOOD_WALL = DnDBlocks.register("birch_wood_wall", wallOf(Blocks.BIRCH_WOOD))
    val JUNGLE_WOOD_STAIRS = DnDBlocks.register("jungle_wood_stairs", stairsOf(Blocks.JUNGLE_WOOD))
    val JUNGLE_WOOD_SLAB = DnDBlocks.register("jungle_wood_slab", slabOf(Blocks.JUNGLE_WOOD))
    val JUNGLE_WOOD_WALL = DnDBlocks.register("jungle_wood_wall", wallOf(Blocks.JUNGLE_WOOD))
    val ACACIA_WOOD_STAIRS = DnDBlocks.register("acacia_wood_stairs", stairsOf(Blocks.ACACIA_WOOD))
    val ACACIA_WOOD_SLAB = DnDBlocks.register("acacia_wood_slab", slabOf(Blocks.ACACIA_WOOD))
    val ACACIA_WOOD_WALL = DnDBlocks.register("acacia_wood_wall", wallOf(Blocks.ACACIA_WOOD))
    val DARK_OAK_WOOD_STAIRS = DnDBlocks.register("dark_oak_wood_stairs", stairsOf(Blocks.DARK_OAK_WOOD))
    val DARK_OAK_WOOD_SLAB = DnDBlocks.register("dark_oak_wood_slab", slabOf(Blocks.DARK_OAK_WOOD))
    val DARK_OAK_WOOD_WALL = DnDBlocks.register("dark_oak_wood_wall", wallOf(Blocks.DARK_OAK_WOOD))
    val MANGROVE_WOOD_STAIRS = DnDBlocks.register("mangrove_wood_stairs", stairsOf(Blocks.MANGROVE_WOOD))
    val MANGROVE_WOOD_SLAB = DnDBlocks.register("mangrove_wood_slab", slabOf(Blocks.MANGROVE_WOOD))
    val MANGROVE_WOOD_WALL = DnDBlocks.register("mangrove_wood_wall", wallOf(Blocks.MANGROVE_WOOD))
    val CHERRY_WOOD_STAIRS = DnDBlocks.register("cherry_wood_stairs", stairsOf(Blocks.CHERRY_WOOD))
    val CHERRY_WOOD_SLAB = DnDBlocks.register("cherry_wood_slab", slabOf(Blocks.CHERRY_WOOD))
    val CHERRY_WOOD_WALL = DnDBlocks.register("cherry_wood_wall", wallOf(Blocks.CHERRY_WOOD))
    val CRIMSON_HYPHAE_STAIRS = DnDBlocks.register("crimson_hyphae_stairs", stairsOf(Blocks.CRIMSON_HYPHAE))
    val CRIMSON_HYPHAE_SLAB = DnDBlocks.register("crimson_hyphae_slab", slabOf(Blocks.CRIMSON_HYPHAE))
    val CRIMSON_HYPHAE_WALL = DnDBlocks.register("crimson_hyphae_wall", wallOf(Blocks.CRIMSON_HYPHAE))
    val WARPED_HYPHAE_STAIRS = DnDBlocks.register("warped_hyphae_stairs", stairsOf(Blocks.WARPED_HYPHAE))
    val WARPED_HYPHAE_SLAB = DnDBlocks.register("warped_hyphae_slab", slabOf(Blocks.WARPED_HYPHAE))
    val WARPED_HYPHAE_WALL = DnDBlocks.register("warped_hyphae_wall", wallOf(Blocks.WARPED_HYPHAE))

    //logs are done differently and crash when varianted, but the woods have the exact same properties, just use them
    val OAK_LOG_PILE = DnDBlocks.register("oak_log_pile", logPile(Blocks.OAK_WOOD))
    val SPRUCE_LOG_PILE = DnDBlocks.register("spruce_log_pile", logPile(Blocks.SPRUCE_WOOD))
    val BIRCH_LOG_PILE = DnDBlocks.register("birch_log_pile", logPile(Blocks.BIRCH_WOOD))
    val JUNGLE_LOG_PILE = DnDBlocks.register("jungle_log_pile", logPile(Blocks.JUNGLE_WOOD))
    val ACACIA_LOG_PILE = DnDBlocks.register("acacia_log_pile", logPile(Blocks.ACACIA_WOOD))
    val DARK_OAK_LOG_PILE = DnDBlocks.register("dark_oak_log_pile", logPile(Blocks.DARK_OAK_WOOD))
    val MANGROVE_LOG_PILE = DnDBlocks.register("mangrove_log_pile", logPile(Blocks.MANGROVE_WOOD))
    val CHERRY_LOG_PILE = DnDBlocks.register("cherry_log_pile", logPile(Blocks.CHERRY_WOOD))
    val CRIMSON_STEM_PILE = DnDBlocks.register("crimson_stem_pile", logPile(Blocks.CRIMSON_HYPHAE))
    val WARPED_STEM_PILE = DnDBlocks.register("warped_stem_pile", logPile(Blocks.WARPED_HYPHAE))
    val BAMBOO_PILE = DnDBlocks.register("bamboo_pile", logPile(Blocks.BAMBOO_PLANKS, MapColor.PLANT))
    val STRIPPED_BAMBOO_PILE = DnDBlocks.register("stripped_bamboo_pile", logPile(Blocks.BAMBOO_PLANKS))


    val OAK_LEAF_PILE = DnDBlocks.register("oak_leaf_pile", leafPile().cutout())
    val SPRUCE_LEAF_PILE = DnDBlocks.register("spruce_leaf_pile", leafPile().cutout())
    val BIRCH_LEAF_PILE = DnDBlocks.register("birch_leaf_pile", leafPile().cutout())
    val JUNGLE_LEAF_PILE = DnDBlocks.register("jungle_leaf_pile", leafPile().cutout())
    val ACACIA_LEAF_PILE = DnDBlocks.register("acacia_leaf_pile", leafPile().cutout())
    val DARK_OAK_LEAF_PILE = DnDBlocks.register("dark_oak_leaf_pile", leafPile().cutout())
    val MANGROVE_LEAF_PILE = DnDBlocks.register("mangrove_leaf_pile", leafPile().cutout())
    val CHERRY_LEAF_PILE = DnDBlocks.register(
        "cherry_leaf_pile",
        fallingLeafPile(ParticleTypes.CHERRY_LEAVES, MapColor.PINK, BlockSoundGroup.CHERRY_LEAVES).cutout()
    )
    val AZALEA_LEAF_PILE = DnDBlocks.register(
        "azalea_leaf_pile",
        leafPile(BlockSoundGroup.AZALEA_LEAVES).cutout()
    )
    val FLOWERING_AZALEA_LEAF_PILE = DnDBlocks.register(
        "flowering_azalea_leaf_pile",
        leafPile(BlockSoundGroup.AZALEA_LEAVES).cutout()
    )

    fun init() {
        StrippableBlockRegistry.register(CASCADE_LOG, STRIPPED_CASCADE_LOG)
        StrippableBlockRegistry.register(CASCADE_WOOD, STRIPPED_CASCADE_WOOD)
    }
}