package org.teamvoided.dusks_and_dungeons.init.blocks

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.logOf
import net.minecraft.block.Blocks.pottedVariant
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.block.sapling.SaplingBlock
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.dusks_and_dungeons.block.DnDWoodTypes
import org.teamvoided.dusks_and_dungeons.block.FallingLeavesBlock
import org.teamvoided.dusks_and_dungeons.block.sapling.SaplingGenerators
import org.teamvoided.dusks_and_dungeons.block.sapling.ThreeWideTreeSaplingBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.register
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.registerNoItem
import org.teamvoided.dusks_and_dungeons.init.DnDParticles
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.voidlib.consortium.block.set.createBlockSet
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockSettings as Set


object DnDWoodBlocks {
    val CASCADE_SAPLING = register(
        "cascade_sapling", ThreeWideTreeSaplingBlock(SaplingGenerators.CASCADE, Set.CASCADE_SAPLING)
    ).cutout()
    val POTTED_CASCADE_SAPLING = registerNoItem("potted_cascade_sapling", pottedVariant(CASCADE_SAPLING)).cutout()
    val CASCADE_LEAVES = register(
        "cascade_leaves", FallingLeavesBlock(DnDParticles.CASCADE_LEAF_PARTICLE, Set.CASCADE_LEAVES)
    ).leaves()
    val CASCADE_LEAF_PILE = register(
        "cascade_leaf_pile",
        fallingLeafPile(DnDParticles.CASCADE_LEAF_PARTICLE, MapColor.RED, BlockSoundGroup.AZALEA_LEAVES)
    ).cutout()
    val CASCADE_LOG = register("cascade_log", logOf(MapColor.BLUE, MapColor.BROWN, BlockSoundGroup.CHERRY_WOOD))
    val CASCADE_WOOD =
        register(createBlockSet("cascade_wood", Set.CASCADE_WOOD).noStoneCutting().parent(::PillarBlock).build())
            .woodSet()

    val CASCADE_LOG_PILE = register("cascade_log_pile", logPile(CASCADE_WOOD.parent))
    val STRIPPED_CASCADE_LOG =
        register("stripped_cascade_log", logOf(MapColor.BLUE, MapColor.BLUE, BlockSoundGroup.CHERRY_WOOD))
    val STRIPPED_CASCADE_WOOD =
        register("stripped_cascade_wood", PillarBlock(copy(CASCADE_WOOD).mapColor(MapColor.BLUE)))

    val CASCADE_PLANKS = register(
        "cascade_planks", Block(
            Settings.create()
                .mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.CHERRY_WOOD).lavaIgnitable()
        )
    ).flammablePlanks()
    val CASCADE_STAIRS = register("cascade_stairs", stairsOf(CASCADE_PLANKS).wood())
    val CASCADE_SLAB = register("cascade_slab", slabOf(CASCADE_PLANKS).wood())
    val CASCADE_FENCE = register("cascade_fence", fenceOf(CASCADE_PLANKS).wood())
    val CASCADE_FENCE_GATE =
        register("cascade_fence_gate", fenceGateOf(DnDWoodTypes.CASCADE_WOOD_TYPE, CASCADE_PLANKS).wood())
    val CASCADE_DOOR =
        registerNoItem("cascade_door", doorOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS).wood())
    val BLUE_DOOR = registerNoItem(
        "blue_door", DoorBlock(
            BlockSetType.DARK_OAK,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
        ).wood()
    )
    val CASCADE_TRAPDOOR =
        register("cascade_trapdoor", trapdoorOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS).wood())
    val CASCADE_PRESSURE_PLATE =
        register("cascade_pressure_plate", pressurePlateOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE, CASCADE_PLANKS).wood())
    val CASCADE_BUTTON = register("cascade_button", Blocks.buttonOf(DnDWoodTypes.CASCADE_BLOCK_SET_TYPE).wood())
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
        SaplingBlock(SaplingGenerators.GOLDEN_BIRCH, copy(Blocks.BIRCH_SAPLING).mapColor(MapColor.YELLOW)).cutout()
    )
    val POTTED_GOLDEN_BIRCH_SAPLING =
        registerNoItem("potted_golden_birch_sapling", pottedVariant(GOLDEN_BIRCH_SAPLING).cutout())
    val GOLDEN_BIRCH_LEAVES = register(
        "golden_birch_leaves",
        LeavesBlock(copy(Blocks.BIRCH_LEAVES).mapColor(MapColor.YELLOW)).leaves()
    )
    val GOLDEN_BIRCH_LEAF_PILE = register("golden_birch_leaf_pile", leafPile(MapColor.YELLOW).cutout())

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

    //logs are done differently and crash when varianted, but the woods have the exact same properties, just use them
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
    val BAMBOO_PILE = register("bamboo_pile", logPile(Blocks.BAMBOO_PLANKS, MapColor.PLANT))
    val STRIPPED_BAMBOO_PILE = register("stripped_bamboo_pile", logPile(Blocks.BAMBOO_PLANKS))

    val OAK_LEAF_PILE = register("oak_leaf_pile", leafPile().cutout())
    val SPRUCE_LEAF_PILE = register("spruce_leaf_pile", leafPile().cutout())
    val BIRCH_LEAF_PILE = register("birch_leaf_pile", leafPile().cutout())
    val JUNGLE_LEAF_PILE = register("jungle_leaf_pile", leafPile().cutout())
    val ACACIA_LEAF_PILE = register("acacia_leaf_pile", leafPile().cutout())
    val DARK_OAK_LEAF_PILE = register("dark_oak_leaf_pile", leafPile().cutout())
    val MANGROVE_LEAF_PILE = register("mangrove_leaf_pile", leafPile().cutout())
    val CHERRY_LEAF_PILE = register(
        "cherry_leaf_pile",
        fallingLeafPile(ParticleTypes.CHERRY_LEAVES, MapColor.PINK, BlockSoundGroup.CHERRY_LEAVES).cutout()
    )
    val AZALEA_LEAF_PILE = register("azalea_leaf_pile", leafPile(BlockSoundGroup.AZALEA_LEAVES).cutout())
    val FLOWERING_AZALEA_LEAF_PILE = register(
        "flowering_azalea_leaf_pile", leafPile(BlockSoundGroup.AZALEA_LEAVES).cutout()
    )

    // Work on before release
    val HOLLOW_OAK_LOG = register("hollow_oak_log", hollowLog(Blocks.OAK_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_OAK_LOG = register("hollow_stripped_oak_log", hollowLog(Blocks.STRIPPED_OAK_LOG))
        .tellWitnessesThatIWasMurdered()
    val HOLLOW_SPRUCE_LOG =
        register("hollow_spruce_log", hollowLog(Blocks.SPRUCE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_SPRUCE_LOG =
        register("hollow_stripped_spruce_log", hollowLog(Blocks.STRIPPED_SPRUCE_LOG))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_BIRCH_LOG =
        register("hollow_birch_log", hollowLog(Blocks.BIRCH_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_BIRCH_LOG =
        register("hollow_stripped_birch_log", hollowLog(Blocks.STRIPPED_BIRCH_LOG))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_JUNGLE_LOG =
        register("hollow_jungle_log", hollowLog(Blocks.JUNGLE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_JUNGLE_LOG =
        register("hollow_stripped_jungle_log", hollowLog(Blocks.STRIPPED_JUNGLE_LOG))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_ACACIA_LOG =
        register("hollow_acacia_log", hollowLog(Blocks.ACACIA_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_ACACIA_LOG =
        register("hollow_stripped_acacia_log", hollowLog(Blocks.STRIPPED_ACACIA_LOG))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_DARK_OAK_LOG =
        register("hollow_dark_oak_log", hollowLog(Blocks.DARK_OAK_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_DARK_OAK_LOG =
        register("hollow_stripped_dark_oak_log", hollowLog(Blocks.STRIPPED_DARK_OAK_LOG))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_MANGROVE_LOG =
        register("hollow_mangrove_log", hollowLog(Blocks.MANGROVE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_MANGROVE_LOG =
        register("hollow_stripped_mangrove_log", hollowLog(Blocks.STRIPPED_MANGROVE_LOG))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_CHERRY_LOG =
        register("hollow_cherry_log", hollowLog(Blocks.CHERRY_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_CHERRY_LOG =
        register("hollow_stripped_cherry_log", hollowLog(Blocks.STRIPPED_CHERRY_LOG))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_BAMBOO_BLOCK = register("hollow_bamboo_block", hollowBambooBlock(Blocks.BAMBOO_BLOCK))
        .tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_BAMBOO_BLOCK =
        register("hollow_stripped_bamboo_block", hollowBambooBlock(Blocks.STRIPPED_BAMBOO_BLOCK))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_CRIMSON_STEM =
        register("hollow_crimson_stem", hollowLog(Blocks.CRIMSON_HYPHAE)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_CRIMSON_STEM =
        register("hollow_stripped_crimson_stem", hollowLog(Blocks.STRIPPED_CRIMSON_HYPHAE))
            .tellWitnessesThatIWasMurdered()
    val HOLLOW_WARPED_STEM =
        register("hollow_warped_stem", hollowLog(Blocks.WARPED_HYPHAE)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_WARPED_STEM =
        register("hollow_stripped_warped_stem", hollowLog(Blocks.STRIPPED_WARPED_HYPHAE))
            .tellWitnessesThatIWasMurdered()

    val HOLLOW_CASCADE_LOG = register("hollow_cascade_log", hollowLog(CASCADE_LOG)).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_CASCADE_LOG = register("hollow_stripped_cascade_log", hollowLog(STRIPPED_CASCADE_LOG))
        .tellWitnessesThatIWasMurdered()

    // ☢ Experimental ☢
    val GALLERY_MAPLE_SAPLING = register(
        "gallery_maple_sapling", ThreeWideTreeSaplingBlock(
            SaplingGenerators.CASCADE,
            Settings.create()
                .mapColor(MapColor.RED).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.AZALEA)
                .pistonBehavior(PistonBehavior.DESTROY).luminance(light(1))
        ).cutout()
    ).tellWitnessesThatIWasMurdered()
    val POTTED_GALLERY_MAPLE_SAPLING =
        registerNoItem("potted_gallery_maple_sapling", pottedVariant(GALLERY_MAPLE_SAPLING)).cutout()
    val GALLERY_MAPLE_LEAVES = register(
        "gallery_maple_leaves", LeavesBlock(
            Settings.create().strength(0.2f).ticksRandomly()
                .nonOpaque().allowsSpawning(Blocks::allowOcelotsAndParrots).suffocates(Blocks::nonSolid)
                .blockVision(Blocks::nonSolid).pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::nonSolid)
                .sounds(BlockSoundGroup.GRASS).mapColor(MapColor.RED)
        ).cutout().axe().hoe()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_LEAF_PILE = register(
        "gallery_maple_leaf_pile", fallingLeafPile(DnDParticles.CASCADE_LEAF_PARTICLE, MapColor.RED).cutout()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_LOG = register("gallery_maple_log", logOf(MapColor.GRAY, MapColor.BROWN, BlockSoundGroup.WOOD))
        .tellWitnessesThatIWasMurdered()
    val HOLLOW_GALLERY_MAPLE_LOG =
        register("hollow_gallery_maple_log", hollowLog(GALLERY_MAPLE_LOG)).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_WOOD = register(
        "gallery_maple_wood", PillarBlock(
            Settings.create().mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0f)
                .sounds(BlockSoundGroup.WOOD)
        )
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_WOOD_STAIRS =
        register("gallery_maple_wood_stairs", stairsOf(GALLERY_MAPLE_WOOD)).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_WOOD_SLAB =
        register("gallery_maple_wood_slab", slabOf(GALLERY_MAPLE_WOOD)).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_WOOD_WALL =
        register("gallery_maple_wood_wall", wallOf(GALLERY_MAPLE_WOOD)).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_LOG_PILE =
        register("gallery_maple_log_pile", logPile(GALLERY_MAPLE_WOOD)).tellWitnessesThatIWasMurdered()
    val STRIPPED_GALLERY_MAPLE_LOG = register(
        "stripped_gallery_maple_log", logOf(MapColor.GRAY, MapColor.GRAY, BlockSoundGroup.WOOD)
    ).tellWitnessesThatIWasMurdered()
    val HOLLOW_STRIPPED_GALLERY_MAPLE_LOG =
        register("hollow_stripped_gallery_maple_log", hollowLog(STRIPPED_GALLERY_MAPLE_LOG))
            .tellWitnessesThatIWasMurdered()
    val STRIPPED_GALLERY_MAPLE_WOOD = register(
        "stripped_gallery_maple_wood", PillarBlock(copy(GALLERY_MAPLE_WOOD).mapColor(MapColor.GRAY))
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_PLANKS = register(
        "gallery_maple_planks", Block(
            Settings.create()
                .mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.WOOD)
        ).axe()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_STAIRS =
        register("gallery_maple_stairs", stairsOf(GALLERY_MAPLE_PLANKS).axe()).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_SLAB =
        register("gallery_maple_slab", slabOf(GALLERY_MAPLE_PLANKS).axe()).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_FENCE =
        register("gallery_maple_fence", fenceOf(GALLERY_MAPLE_PLANKS).axe()).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_FENCE_GATE = register(
        "gallery_maple_fence_gate", fenceGateOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS).axe()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_DOOR = registerNoItem(
        "gallery_maple_door", doorOf(DnDWoodTypes.GALLERY_MAPLE_BLOCK_SET_TYPE, GALLERY_MAPLE_PLANKS).cutout().axe()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_TRAPDOOR = register(
        "gallery_maple_trapdoor",
        trapdoorOf(DnDWoodTypes.GALLERY_MAPLE_BLOCK_SET_TYPE, GALLERY_MAPLE_DOOR).cutout().axe()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_PRESSURE_PLATE = register(
        "gallery_maple_pressure_plate",
        pressurePlateOf(DnDWoodTypes.GALLERY_MAPLE_BLOCK_SET_TYPE, GALLERY_MAPLE_PLANKS).axe()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_BUTTON =
        register("gallery_maple_button", Blocks.buttonOf(DnDWoodTypes.GALLERY_MAPLE_BLOCK_SET_TYPE).axe())
            .tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_SIGN = registerNoItem(
        "gallery_maple_sign", signOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS).axe()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_WALL_SIGN = registerNoItem(
        "gallery_maple_wall_sign",
        wallSignOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS, GALLERY_MAPLE_SIGN).axe()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_HANGING_SIGN = registerNoItem(
        "gallery_maple_hanging_sign", hangingSignOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS).axe()
    ).tellWitnessesThatIWasMurdered()
    val GALLERY_MAPLE_WALL_HANGING_SIGN = registerNoItem(
        "gallery_maple_wall_hanging_sign",
        wallHangingSignOf(DnDWoodTypes.GALLERY_MAPLE_WOOD_TYPE, GALLERY_MAPLE_PLANKS, GALLERY_MAPLE_HANGING_SIGN).axe()
    ).tellWitnessesThatIWasMurdered()

    val BONEWOOD_PLANKS = register(
        "bonewood_planks", Block(
            Settings.create()
                .mapColor(MapColor.SNOW).instrument(NoteBlockInstrument.XYLOPHONE).strength(2.0F, 3.0F)
                .sounds(bonewoodSound)
        ).axe()
    ).tellWitnessesThatIWasMurdered()
    val BONEWOOD_STAIRS =
        register("bonewood_stairs", stairsOf(BONEWOOD_PLANKS).axe()).tellWitnessesThatIWasMurdered()
    val BONEWOOD_SLAB =
        register("bonewood_slab", slabOf(BONEWOOD_PLANKS).axe()).tellWitnessesThatIWasMurdered()
    val BONEWOOD_FENCE =
        register("bonewood_fence", fenceOf(BONEWOOD_PLANKS).axe()).tellWitnessesThatIWasMurdered()
    val BONEWOOD_FENCE_GATE = register(
        "bonewood_fence_gate", FenceGateBlock(DnDWoodTypes.BONEWOOD_WOOD_TYPE, copy(BONEWOOD_PLANKS).solid()).axe()
    ).tellWitnessesThatIWasMurdered()
    val BONEWOOD_DOOR = registerNoItem(
        "bonewood_door",
        DoorBlock(DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE, copy(BONEWOOD_PLANKS).strength(3.0f).nonOpaque()).cutout().axe()
    ).tellWitnessesThatIWasMurdered()
    val BONEWOOD_TRAPDOOR = register(
        "bonewood_trapdoor",
        TrapdoorBlock(
            DnDWoodTypes.BONEWOOD_BLOCK_SET_TYPE, copy(BONEWOOD_DOOR).allowsSpawning(Blocks::nonSpawnable),
        ).cutout().axe()
    ).tellWitnessesThatIWasMurdered()
    val WITHERING_BONEWOOD_PLANKS = register(
        "withering_bonewood_planks",
        Block(copy(BONEWOOD_PLANKS).mapColor(MapColor.BLACK).sounds(witheringBonewoodSound)).axe()
    ).tellWitnessesThatIWasMurdered()
    val WITHERING_BONEWOOD_STAIRS =
        register("withering_bonewood_stairs", stairsOf(WITHERING_BONEWOOD_PLANKS).axe())
            .tellWitnessesThatIWasMurdered()
    val WITHERING_BONEWOOD_SLAB = register("withering_bonewood_slab", slabOf(WITHERING_BONEWOOD_PLANKS).axe())
        .tellWitnessesThatIWasMurdered()
    val WITHERING_BONEWOOD_FENCE =
        register("withering_bonewood_fence", fenceOf(WITHERING_BONEWOOD_PLANKS).axe())
            .tellWitnessesThatIWasMurdered()
    val WITHERING_BONEWOOD_FENCE_GATE = register(
        "withering_bonewood_fence_gate",
        FenceGateBlock(DnDWoodTypes.WITHERING_BONEWOOD_WOOD_TYPE, copy(WITHERING_BONEWOOD_PLANKS).solid()).axe()
    ).tellWitnessesThatIWasMurdered()
    val WITHERING_BONEWOOD_DOOR = registerNoItem(
        "withering_bonewood_door",
        DoorBlock(
            DnDWoodTypes.WITHERING_BONEWOOD_BLOCK_SET_TYPE, copy(WITHERING_BONEWOOD_PLANKS).strength(3.0f).nonOpaque(),
        ).cutout().axe()
    ).tellWitnessesThatIWasMurdered()
    val WITHERING_BONEWOOD_TRAPDOOR = register(
        "withering_bonewood_trapdoor", TrapdoorBlock(
            DnDWoodTypes.WITHERING_BONEWOOD_BLOCK_SET_TYPE,
            copy(WITHERING_BONEWOOD_DOOR).allowsSpawning(Blocks::nonSpawnable),
        ).cutout().axe()
    ).tellWitnessesThatIWasMurdered()
}