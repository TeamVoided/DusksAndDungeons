package org.teamvoided.dusk_autumn.init

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.OffsetType
import net.minecraft.block.Blocks.logOf
import net.minecraft.block.Blocks.pottedVariant
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.block.sapling.SaplingBlock
import net.minecraft.block.sign.CeilingHangingSignBlock
import net.minecraft.block.sign.SignBlock
import net.minecraft.block.sign.WallHangingSignBlock
import net.minecraft.block.sign.WallSignBlock
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.block.sapling.SaplingGenerators
import org.teamvoided.dusk_autumn.block.sapling.ThreeWideTreeSaplingBlock

@Suppress("HasPlatformType", "MemberVisibilityCanBePrivate", "unused", "DEPRECATION")
object DuskBlocks {
    val leafPileSettings = AbstractBlock.Settings.create()
        .strength(0.2F).nonOpaque().suffocates(Blocks::nonSolid).blockVision(Blocks::nonSolid).lavaIgnitable()
        .pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::nonSolid).noCollision().nonSolid()
        .sounds(BlockSoundGroup.GRASS).mapColor(MapColor.PLANT)

    const val CASCADE_LEAF_COLOR = 13846346

    val BLUE_PETALS = register(
        "blue_petals", PinkPetalsBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.PLANT)
                .noCollision().sounds(BlockSoundGroup.PINK_PETALS).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val VIOLET_DAISY = register(
        "violet_daisy", FlowerBlock(
            StatusEffects.HASTE, 10f, AbstractBlock.Settings.create()
                .mapColor(MapColor.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)
                .offsetType(OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val POTTED_VIOLET_DAISY = register("potted_violet_daisy", pottedVariant(VIOLET_DAISY))

    val CASCADE_SAPLING = register(
        "cascade_sapling", ThreeWideTreeSaplingBlock(
            SaplingGenerators.CASCADE,
            AbstractBlock.Settings.create()
                .mapColor(MapColor.RED).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.CHERRY_SAPLING).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val POTTED_CASCADE_SAPLING = register("potted_cascade_sapling", pottedVariant(CASCADE_SAPLING))
    val CASCADE_LOG = register(
        "cascade_log", logOf(MapColor.BLUE, MapColor.BROWN, BlockSoundGroup.CHERRY_WOOD),
    )
    val CASCADE_LOG_STRAIGHT = register(
        "cascade_log_straight", logOf(MapColor.BLUE, MapColor.BROWN, BlockSoundGroup.CHERRY_WOOD),
    )
    val STRIPPED_CASCADE_LOG = register(
        "stripped_cascade_log", logOf(MapColor.BLUE, MapColor.BLUE, BlockSoundGroup.CHERRY_WOOD)
    )
    val CASCADE_PLANKS = register(
        "cascade_planks", Block(
            AbstractBlock.Settings.create()
                .mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.BASS)
                .strength(2.0F, 3.0F).sounds(BlockSoundGroup.CHERRY_WOOD)
                .lavaIgnitable()
        )
    )
    val CASCADE_DOOR = register(
        "cascade_door", DoorBlock(
            BlockSetType.CHERRY,
            AbstractBlock.Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
        )
    )
    val BLUE_DOOR = register(
        "blue_door", DoorBlock(
            BlockSetType.DARK_OAK,
            AbstractBlock.Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY),
        )
    )
    val CASCADE_TRAPDOOR = register(
        "cascade_trapdoor", TrapdoorBlock(
            BlockSetType.CHERRY,
            AbstractBlock.Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).instrument(NoteBlockInstrument.BASS)
                .strength(3.0f).nonOpaque()
                .allowsSpawning(Blocks::nonSpawnable).lavaIgnitable(),
        )
    )
    val CASCADE_LEAVES = register(
        "cascade_leaves", FallingLeavesBlock(
            AbstractBlock.Settings.create().strength(0.2f).ticksRandomly()
                .nonOpaque().allowsSpawning(Blocks::allowOcelotsAndParrots).suffocates(Blocks::nonSolid)
                .blockVision(Blocks::nonSolid)
                .lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::nonSolid)
                .sounds(BlockSoundGroup.CHERRY_LEAVES)
                .mapColor(MapColor.RED), DuskParticles.CASCADE_LEAF_PARTICLE
        )
    )
    val CASCADE_SIGN = register(
        "cascade_sign", SignBlock(
            WoodType.CHERRY,
            AbstractBlock.Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(),
        )
    )
    val CASCADE_WALL_SIGN = register(
        "cascade_wall_sign", WallSignBlock(
            WoodType.CHERRY,
            AbstractBlock.Settings.create().mapColor(CASCADE_LOG.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).dropsLike(CASCADE_SIGN)
                .lavaIgnitable(),
        )
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign", CeilingHangingSignBlock(
            WoodType.CHERRY,
            AbstractBlock.Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(),
        )
    )
    val CASCADE_WALL_HANGING_SIGN = register(
        "cascade_wall_hanging_sign", WallHangingSignBlock(
            WoodType.CHERRY,
            AbstractBlock.Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable()
                .dropsLike(CASCADE_HANGING_SIGN),
        )
    )

    val GOLDEN_BIRCH_LEAVES = register(
        "golden_birch_leaves", LeavesBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.YELLOW)
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
            AbstractBlock.Settings.create()
                .mapColor(MapColor.YELLOW).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val POTTED_GOLDEN_BIRCH_SAPLING =
        register("potted_golden_birch_sapling", pottedVariant(GOLDEN_BIRCH_SAPLING))

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
    val WILD_WHEAT = register(
        "wild_wheat",
        TallPlantBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.PLANT).noCollision().breakInstantly()
                .sounds(BlockSoundGroup.CROP).offsetType(OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val GOLDEN_BEETROOTS = register(
        "golden_beetroots",
        GoldenBeetrootsBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.GOLD).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val MOONBERRY_VINE = register(
        "moonberry_vine",
        MoonberryVineBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).noCollision().strength(0.2f)
                .sounds(BlockSoundGroup.CAVE_VINES).luminance(MoonberryVineBlock.getLuminanceSupplier(8, 11))
                .lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val MOONBERRY_VINELET = register(
        "moonberry_vinelet", MoonberryVineletBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).noCollision().strength(0.2f).ticksRandomly()
                .breakInstantly().sounds(BlockSoundGroup.CAVE_VINES).lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )

    fun init() {
        DuskBlockFamilies.init()
    }

    fun register(id: String, block: Block): Block = Registry.register(Registries.BLOCK, id(id), block)

}