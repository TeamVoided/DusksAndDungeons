package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.block.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.block.sapling.SaplingBlock
import net.minecraft.block.sign.*
import net.minecraft.client.color.world.BiomeColors
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.EntityType
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.FallingLeafPileBlock
import org.teamvoided.dusk_autumn.block.FallingLeavesBlock
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.block.sapling.CascadeSaplingGenerator
import org.teamvoided.dusk_autumn.block.sapling.GoldenBirchSaplingGenerator

object DuskBlocks {
    @Suppress("DEPRECATION")
    val leafPileSettings = AbstractBlock.Settings.create()
        .strength(0.2F).nonOpaque().suffocates(Blocks::never).blockVision(Blocks::never).lavaIgnitable()
        .pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never).noCollision().nonSolid()
        .sounds(BlockSoundGroup.GRASS).mapColor(MapColor.PLANT)
    val CASCADE_LEAF_COLOR = 13846346


    val BLUE_PETALS = register(
        "blue_petals", PinkPetalsBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.PLANT)
                .noCollision().sounds(BlockSoundGroup.PINK_PETALS).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val VIOLET_DAISY = register(
        "violet_daisy", FlowerBlock(
            StatusEffects.HASTE, 10, AbstractBlock.Settings.create()
                .mapColor(MapColor.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)
                .offsetType(AbstractBlock.OffsetType.XZ)
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val POTTED_VIOLET_DAISY = register("potted_violet_daisy", Blocks.createFlowerPotBlock(VIOLET_DAISY))

    val CASCADE_SAPLING = register(
        "cascade_sapling", SaplingBlock(
            CascadeSaplingGenerator(), AbstractBlock.Settings.create()
                .mapColor(MapColor.RED).noCollision().ticksRandomly().breakInstantly()
                .sounds(BlockSoundGroup.CHERRY_SAPLING).pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val POTTED_CASCADE_SAPLING =
        register("potted_cascade_sapling", Blocks.createFlowerPotBlock(CASCADE_SAPLING))
    val CASCADE_LOG = register(
        "cascade_log",
        Blocks.createPillarBlock(
            MapColor.BLUE, MapColor.BROWN, BlockSoundGroup.CHERRY_WOOD
        ),
    )
    val STRIPPED_CASCADE_LOG = register(
        "stripped_cascade_log", Blocks.createPillarBlock(
            MapColor.BLUE, MapColor.BLUE, BlockSoundGroup.CHERRY_WOOD
        )
    )
    val CASCADE_PLANKS = register(
        "cascade_planks", Block(
            AbstractBlock.Settings.create()
                .mapColor(MapColor.BLUE).instrument(NoteBlockInstrument.BASS)
                .strength(2.0F, 3.0F).sounds(BlockSoundGroup.CHERRY_WOOD)
                .lavaIgnitable()
        )
    )
    val CASCADE_DOOR = Blocks.register(
        "cascade_door",
        DoorBlock(
            AbstractBlock.Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY), BlockSetType.CHERRY
        )
    )
    val BLUE_DOOR = Blocks.register(
        "blue_door",
        DoorBlock(
            AbstractBlock.Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(3.0f).nonOpaque().lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY), BlockSetType.DARK_OAK
        )
    )
    val CASCADE_TRAPDOOR = Blocks.register(
        "cascade_trapdoor",
        TrapdoorBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).instrument(NoteBlockInstrument.BASS)
                .strength(3.0f).nonOpaque()
                .allowsSpawning { state: BlockState?, world: BlockView?, pos: BlockPos?, type: EntityType<*>? ->
                    Blocks.never(state, world, pos, type)
                }.lavaIgnitable(), BlockSetType.CHERRY
        )
    )
    val CASCADE_LEAVES = register(
        "cascade_leaves", FallingLeavesBlock(
            AbstractBlock.Settings.create().strength(0.2f).ticksRandomly()
                .nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never)
                .blockVision(Blocks::never)
                .lavaIgnitable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never)
                .sounds(BlockSoundGroup.CHERRY_LEAVES)
                .mapColor(MapColor.RED), DuskParticles.CASCADE_LEAF_PARTICLE
        )
    )
    val CASCADE_SIGN = register(
        "cascade_sign",
        SignBlock(
            AbstractBlock.Settings.create().mapColor(CASCADE_PLANKS.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(), SignType.CHERRY
        )
    )
    val CASCADE_WALL_SIGN = register(
        "cascade_wall_sign",
        WallSignBlock(
            AbstractBlock.Settings.create().mapColor(CASCADE_LOG.defaultMapColor).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).dropsLike(CASCADE_SIGN)
                .lavaIgnitable(), SignType.CHERRY
        )
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        CeilingHangingSignBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable(), SignType.CHERRY
        )
    )
    val CASCADE_WALL_HANGING_SIGN = register(
        "cascade_wall_hanging_sign",
        WallHangingSignBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.BLUE_TERRACOTTA).solid()
                .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0f).lavaIgnitable()
                .dropsLike(CASCADE_HANGING_SIGN), SignType.CHERRY
        )
    )

    val GOLDEN_BIRCH_LEAVES = register(
        "golden_birch_leaves", LeavesBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.YELLOW)
                .strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque()
                .allowsSpawning(Blocks::canSpawnOnLeaves)
                .suffocates(Blocks::never).blockVision(Blocks::never).lavaIgnitable()
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(Blocks::never)
        )
    )
    val GOLDEN_BIRCH_SAPLING = register(
        "golden_birch_sapling", SaplingBlock(
            GoldenBirchSaplingGenerator(), AbstractBlock.Settings.create()
                .mapColor(MapColor.YELLOW).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )
    val POTTED_GOLDEN_BIRCH_SAPLING =
        register("potted_golden_birch_sapling", Blocks.createFlowerPotBlock(GOLDEN_BIRCH_SAPLING))

    val OAK_LEAF_PILE = register("oak_leaf_pile", LeafPileBlock(leafPileSettings))
    val SPRUCE_LEAF_PILE = register("spruce_leaf_pile", LeafPileBlock(leafPileSettings))
    val BIRCH_LEAF_PILE = register("birch_leaf_pile", LeafPileBlock(leafPileSettings))
    val JUNGLE_LEAF_PILE = register("jungle_leaf_pile", LeafPileBlock(leafPileSettings))
    val ACACIA_LEAF_PILE = register("acacia_leaf_pile", LeafPileBlock(leafPileSettings))
    val DARK_OAK_LEAF_PILE = register("dark_oak_leaf_pile", LeafPileBlock(leafPileSettings))
    val MANGROVE_LEAF_PILE = register("mangrove_leaf_pile", LeafPileBlock(leafPileSettings))
    val CASCADE_LEAF_PILE = register(
        "cascade_leaf_pile",
        FallingLeafPileBlock(
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

    fun init() {}
    fun initClient() {
        val cutoutList = listOf(
            CASCADE_SAPLING, POTTED_CASCADE_SAPLING, CASCADE_LEAVES,
            BLUE_PETALS, VIOLET_DAISY, POTTED_VIOLET_DAISY,
            GOLDEN_BIRCH_SAPLING, POTTED_GOLDEN_BIRCH_SAPLING, GOLDEN_BIRCH_LEAVES,
            CASCADE_LEAF_PILE, OAK_LEAF_PILE, SPRUCE_LEAF_PILE, BIRCH_LEAF_PILE, JUNGLE_LEAF_PILE, ACACIA_LEAF_PILE,
            DARK_OAK_LEAF_PILE, MANGROVE_LEAF_PILE, CHERRY_LEAF_PILE, AZALEA_LEAF_PILE,
            FLOWERING_AZALEA_LEAF_PILE, GOLDEN_BIRCH_LEAF_PILE
        )
        cutoutList.forEach {
            BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout());
        }

        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                if (world != null && pos != null) BiomeColors.getFoliageColor(world, pos) else FoliageColors.getColor(
                    0.8,
                    0.4
                )
            },
            *arrayOf<Block>(
                OAK_LEAF_PILE,
                JUNGLE_LEAF_PILE,
                ACACIA_LEAF_PILE,
                DARK_OAK_LEAF_PILE,
                MANGROVE_LEAF_PILE
            )
        )
        ColorProviderRegistry.BLOCK.register(
            { _, world, pos, _ ->
                if (world != null && pos != null) BiomeColors.getGrassColor(world, pos) else GrassColors.getColor(
                    0.8,
                    0.4
                )
            },
            *arrayOf<Block>(
                BLUE_PETALS
            )
        )
        ColorProviderRegistry.BLOCK.register({ _, _, _, _ -> FoliageColors.getSpruceColor() }, SPRUCE_LEAF_PILE)
        ColorProviderRegistry.BLOCK.register({ _, _, _, _ -> FoliageColors.getBirchColor() }, BIRCH_LEAF_PILE)
        ColorProviderRegistry.BLOCK.register(
            { _, _, _, _ -> CASCADE_LEAF_COLOR },
            *arrayOf(CASCADE_LEAVES, CASCADE_LEAF_PILE)
        )

//    val GOLDEN_BIRCH_COLOR = 16761873
//    val GOLDEN_BIRCH_COLOR = 16760872
    }

    fun register(id: String, block: Block): Block {
        return Registry.register(Registries.BLOCK, id(id), block)
    }
}