package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.*
import net.minecraft.block.AbstractBlock.Settings.variantOf
import net.minecraft.block.Blocks.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.block.sapling.SaplingBlock
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Color
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.block.DuskWoodTypes.CASCADE_BLOCK_SET_TYPE
import org.teamvoided.dusk_autumn.block.DuskWoodTypes.CASCADE_WOOD_TYPE
import org.teamvoided.dusk_autumn.block.sapling.SaplingGenerators
import org.teamvoided.dusk_autumn.block.sapling.ThreeWideTreeSaplingBlock
import org.teamvoided.dusk_autumn.init.DuskItems.BlockItem
import org.teamvoided.voidmill.sign.VoidCeilingHangingSignBlock
import org.teamvoided.voidmill.sign.VoidSignBlock
import org.teamvoided.voidmill.sign.VoidWallHangingSignBlock
import org.teamvoided.voidmill.sign.VoidWallSignBlock


@Suppress("HasPlatformType", "MemberVisibilityCanBePrivate", "unused", "DEPRECATION")
object DuskBlocks {
    val leafPileSettings = Settings.create()
        .strength(0.2F).nonOpaque().suffocates(Blocks::nonSolid).blockVision(Blocks::nonSolid).lavaIgnitable()
        .pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::nonSolid).noCollision().nonSolid()
        .sounds(BlockSoundGroup.GRASS).mapColor(MapColor.PLANT)

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
        "cascade_wood", logOf(MapColor.BLUE, MapColor.BROWN, BlockSoundGroup.CHERRY_WOOD),
    )
    val STRIPPED_CASCADE_LOG = register(
        "stripped_cascade_log", logOf(MapColor.BLUE, MapColor.BLUE, BlockSoundGroup.CHERRY_WOOD)
    )
    val STRIPPED_CASCADE_WOOD = register(
        "stripped_cascade_wood", logOf(MapColor.BLUE, MapColor.BLUE, BlockSoundGroup.CHERRY_WOOD)
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
            CASCADE_WOOD_TYPE,
            Settings.create()
                .mapColor(CASCADE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f).solid().lavaIgnitable()
        )
    )
    val CASCADE_DOOR = registerNoItem(
        "cascade_door", DoorBlock(
            CASCADE_BLOCK_SET_TYPE,
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
            CASCADE_BLOCK_SET_TYPE,
            Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(3.0f).nonOpaque()
                .allowsSpawning(Blocks::nonSpawnable).lavaIgnitable(),
        )
    )
    val CASCADE_PRESSURE_PLATE = register(
        "cascade_pressure_plate",
        PressurePlateBlock(
            CASCADE_BLOCK_SET_TYPE,
            Settings.create()
                .mapColor(CASCADE_PLANKS.defaultMapColor).instrument(NoteBlockInstrument.BASS).noCollision()
                .strength(0.5f).solid().lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY)
        )
    )

    val CASCADE_BUTTON = register("cascade_button", buttonOf(CASCADE_BLOCK_SET_TYPE))

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

    val ROCKY_DIRT = register("dirty_rocks", Block(variantOf(DIRT).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_GRASS = register("grassy_rocks", RockyGrassBlock(ROCKY_DIRT, variantOf(GRASS_BLOCK).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_PODZOL = register("podzol_rocks", SnowyBlock(variantOf(PODZOL).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_MYCELIUM =register("mycelium_rocks", RockyMyceliumBlock(ROCKY_DIRT, variantOf(MYCELIUM).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_COARSE_DIRT = register("coarsely_dirty_rocks", Block(variantOf(COARSE_DIRT).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_DIRT_PATH = register("rocky_path",RockyDirtPathBlock(ROCKY_DIRT, variantOf(DIRT_PATH).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_MUD = register("muddy_rocks", MudBlock(variantOf(MUD).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_SNOW = register("snowy_rocks", Block(variantOf(SNOW_BLOCK).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_GRAVEL = register("rocky_rocks", GravelBlock(Color(-8356741), variantOf(DIRT).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_SAND = register("sandy_rocks", GravelBlock(Color(14406560),variantOf(SAND).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_RED_SAND = register("red_sandy_rocks", GravelBlock(Color(11098145),variantOf(RED_SAND).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_SOUL_SAND = register("rocky_soul_sand", SoulSandBlock(variantOf(SOUL_SAND).mapColor(COBBLESTONE.defaultMapColor)))
    val ROCKY_SOUL_SOIL = register("rocky_soul_soil", Block(variantOf(SOUL_SOIL).mapColor(COBBLESTONE.defaultMapColor)))

    val SLATED_DIRT = register("dirty_slate", Block(variantOf(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_GRASS = register("grassy_slate", RockyGrassBlock(SLATED_DIRT, variantOf(GRASS_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_PODZOL = register("podzol_slate", SnowyBlock(variantOf(PODZOL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_MYCELIUM =register("mycelium_slate", RockyMyceliumBlock(SLATED_DIRT, variantOf(MYCELIUM).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_COARSE_DIRT = register("coarsely_dirty_slate", Block(variantOf(COARSE_DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_DIRT_PATH = register("slated_path",RockyDirtPathBlock(SLATED_DIRT, variantOf(DIRT_PATH).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_MUD = register("muddy_slate", MudBlock(variantOf(MUD).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_SNOW = register("snowy_slate", Block(variantOf(SNOW_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_GRAVEL = register("slated_slate", GravelBlock(Color(-8356741), variantOf(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_SAND = register("sandy_slate", GravelBlock(Color(14406560),variantOf(SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_RED_SAND = register("red_sandy_slate", GravelBlock(Color(11098145),variantOf(RED_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_SOUL_SAND = register("slated_soul_sand", SoulSandBlock(variantOf(SOUL_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val SLATED_SOUL_SOIL = register("slated_soul_soil", Block(variantOf(SOUL_SOIL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))

    val BLACKSTONE_DIRT = register("dirty_blackstone", Block(variantOf(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_GRASS = register("grassy_blackstone", RockyGrassBlock(BLACKSTONE_DIRT, variantOf(GRASS_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_PODZOL = register("podzol_blackstone", SnowyBlock(variantOf(PODZOL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_MYCELIUM =register("mycelium_blackstone", RockyMyceliumBlock(BLACKSTONE_DIRT, variantOf(MYCELIUM).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_COARSE_DIRT = register("coarsely_dirty_blackstone", Block(variantOf(COARSE_DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_DIRT_PATH = register("blackstoned_path",RockyDirtPathBlock(BLACKSTONE_DIRT, variantOf(DIRT_PATH).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_MUD = register("muddy_blackstone", MudBlock(variantOf(MUD).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_SNOW = register("snowy_blackstone", Block(variantOf(SNOW_BLOCK).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_GRAVEL = register("blackstoned_blackstone", GravelBlock(Color(-8356741), variantOf(DIRT).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_SAND = register("sandy_blackstone", GravelBlock(Color(14406560),variantOf(SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_RED_SAND = register("red_sandy_blackstone", GravelBlock(Color(11098145),variantOf(RED_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_SOUL_SAND = register("blackstoned_soul_sand", SoulSandBlock(variantOf(SOUL_SAND).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))
    val BLACKSTONE_SOUL_SOIL = register("blackstoned_soul_soil", Block(variantOf(SOUL_SOIL).mapColor(COBBLED_DEEPSLATE.defaultMapColor)))


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
        FlattenableBlockRegistry.register(ROCKY_GRASS, ROCKY_DIRT_PATH.defaultState)
        FlattenableBlockRegistry.register(ROCKY_PODZOL, ROCKY_DIRT_PATH.defaultState)
        FlattenableBlockRegistry.register(ROCKY_MYCELIUM, ROCKY_DIRT_PATH.defaultState)
        FlattenableBlockRegistry.register(ROCKY_DIRT, ROCKY_DIRT_PATH.defaultState)
        FlattenableBlockRegistry.register(ROCKY_COARSE_DIRT, ROCKY_DIRT_PATH.defaultState)
    }

    fun register(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        DuskItems.register(id, BlockItem(regBlock))
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block = Registry.register(Registries.BLOCK, id(id), block)

}
