package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.DusksAndDungeons.isModLoaded
import org.teamvoided.dusk_autumn.block.DnDFamilies
import org.teamvoided.dusk_autumn.compat.DramaticDoorsCompat
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.*
import org.teamvoided.dusk_autumn.util.*
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    private val ALL_KRY: TextureKey = TextureKey.of("all")

//    var woodStates =
//        BlockStateModelGenerator.StateFactory { block, identifier, texture, biConsumer ->
//            println("45642")
////            val model = Models.CUBE_COLUMN.create(block, texture, biConsumer)
//            val model1 = TexturedModel.END_FOR_TOP_CUBE_COLUMN.create(block, biConsumer)
//            val model2 = TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL.create(block, biConsumer)
//            BlockStateModelGenerator.createAxisRotatedBlockState(block, model1, model2)
//        }

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        gen.stoneStateFactories = mapOf(
//            DnDBlocks.CASCADE_WOOD to woodStates,
//            DnDBlocks.GALLERY_MAPLE_WOOD to woodStates
        )
        gen.sandstoneModels = mapOf(
//            DnDBlocks.CASCADE_WOOD to TexturedModel.SIDE_END_WALL.get(DnDBlocks.CASCADE_WOOD),
//            DnDBlocks.GALLERY_MAPLE_WOOD to TexturedModel.SIDE_END_WALL.get(DnDBlocks.GALLERY_MAPLE_WOOD)

//            DnDBlocks.MIXED_NETHER_BRICKS to TexturedModel.SIDE_TOP_BOTTOM_WALL[DnDBlocks.MIXED_NETHER_BRICKS].texture { texture: Texture ->
//                texture.put(TextureKey.TOP, Texture.getId(Blocks.NETHER_BRICKS))
//                texture.put(TextureKey.BOTTOM, Texture.getId(Blocks.RED_NETHER_BRICKS))
//                texture.put(TextureKey.SIDE, Texture.getId(DnDBlocks.MIXED_NETHER_BRICKS))
//            }
        )
        DnDFamilies.modelsBlockFamilies.forEach {
            gen.registerCubeAllModelTexturePool(it.baseBlock).family(it)
        }
        gen.registerFlowerPotPlant(
            DnDWoodBlocks.CASCADE_SAPLING,
            DnDWoodBlocks.POTTED_CASCADE_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )

        gen.registerLog(DnDWoodBlocks.CASCADE_LOG)
            .log(DnDWoodBlocks.CASCADE_LOG)
            .wood(DnDWoodBlocks.CASCADE_WOOD)
        gen.registerLog(DnDWoodBlocks.STRIPPED_CASCADE_LOG)
            .log(DnDWoodBlocks.STRIPPED_CASCADE_LOG)
            .log(DnDWoodBlocks.STRIPPED_CASCADE_WOOD)
        gen.registerHangingSign(
            DnDWoodBlocks.STRIPPED_CASCADE_LOG,
            DnDWoodBlocks.CASCADE_HANGING_SIGN,
            DnDWoodBlocks.CASCADE_WALL_HANGING_SIGN
        )
        gen.registerSingleton(DnDWoodBlocks.CASCADE_LEAVES, TexturedModel.LEAVES)
        gen.registerDoor(DnDWoodBlocks.BLUE_DOOR)
        gen.registerFlowerPotPlant(
            DnDWoodBlocks.GOLDEN_BIRCH_SAPLING,
            DnDWoodBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )

        gen.registerFlowerPotPlant(
            DnDWoodBlocks.GALLERY_MAPLE_SAPLING,
            DnDWoodBlocks.POTTED_GALLERY_MAPLE_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerLog(DnDWoodBlocks.GALLERY_MAPLE_LOG)
            .log(DnDWoodBlocks.GALLERY_MAPLE_LOG)
            .wood(DnDWoodBlocks.GALLERY_MAPLE_WOOD)
        gen.registerLog(DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_LOG)
            .log(DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_LOG)
            .wood(DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_WOOD)
        gen.registerHangingSign(
            DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_LOG,
            DnDWoodBlocks.GALLERY_MAPLE_HANGING_SIGN,
            DnDWoodBlocks.GALLERY_MAPLE_WALL_HANGING_SIGN
        )
        gen.registerSingleton(DnDWoodBlocks.GALLERY_MAPLE_LEAVES, TexturedModel.LEAVES)
//        gen.registerCoral(
//            DnDBlocks.CLUB_CORAL,
//            DnDBlocks.DEAD_CLUB_CORAL,
//            DnDBlocks.CLUB_CORAL_BLOCK,
//            DnDBlocks.DEAD_CLUB_CORAL_BLOCK,
//            DnDBlocks.CLUB_CORAL_FAN,
//            DnDBlocks.DEAD_CLUB_CORAL_FAN,
//            DnDBlocks.CLUB_CORAL_WALL_FAN,
//            DnDBlocks.DEAD_CLUB_CORAL_WALL_FAN
//        )
//        gen.createVerdureGrowth(
//            DnDFloraBlocks.VERDURE_DEEPSLATE,
//            Texture.getId(DnDFloraBlocks.VERDURE_BLOCK),
//            Texture.getSubId(Blocks.DEEPSLATE, "_top")
//        )
        gen.registerGalleryRose(DnDFloraBlocks.PAINTED_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerGoldenMushroomPlant(DnDFloraBlocks.GOLDEN_MUSHROOM)

        gen.registerSpiderlilly(DnDFloraBlocks.SPIDERLILY, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerFlowerbed2(DnDFloraBlocks.WHITE_PETALS, false)
        gen.registerFlowerbed2(DnDFloraBlocks.RED_PETALS, false)
        gen.registerFlowerbed2(DnDFloraBlocks.ORANGE_PETALS, false)
        gen.registerFlowerbed2(DnDFloraBlocks.BLUE_PETALS, false)
        gen.registerFlowerbed2(DnDFloraBlocks.WILD_PETALS, false, id("block/parent/wildflowerbed"))
        gen.registerFlowerbed(DnDFloraBlocks.CRIMSON_VIVIONS)
        gen.registerFlowerbed(DnDFloraBlocks.WARPED_VIVIONS)
        gen.registerTreeMushroom(DnDFloraBlocks.BROWN_TREE_FUNGUS, "parent/brown_tree_fungus")

        gen.registerAxisRotated(
            DnDStoneBlocks.STONE_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerAxisRotated(
            DnDStoneBlocks.DEEPSLATE_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.iceStairs(DnDBlocks.ICE_STAIRS, Blocks.ICE)
        gen.slab(DnDBlocks.ICE_SLAB, Blocks.ICE)
        gen.wall(DnDBlocks.ICE_WALL, Blocks.ICE)
        gen.stairs(DnDBlocks.PACKED_ICE_STAIRS, Blocks.PACKED_ICE)
        gen.slab(DnDBlocks.PACKED_ICE_SLAB, Blocks.PACKED_ICE)
        gen.wall(DnDBlocks.PACKED_ICE_WALL, Blocks.PACKED_ICE)
        gen.stairs(DnDBlocks.BLUE_ICE_STAIRS, Blocks.BLUE_ICE)
        gen.slab(DnDBlocks.BLUE_ICE_SLAB, Blocks.BLUE_ICE)
        gen.wall(DnDBlocks.BLUE_ICE_WALL, Blocks.BLUE_ICE)
        gen.registerSimpleCubeAll(DnDBlocks.ICE_BRICKS)
        gen.iceStairs(DnDBlocks.ICE_BRICK_STAIRS, DnDBlocks.ICE_BRICKS)
        gen.slab(DnDBlocks.ICE_BRICK_SLAB, DnDBlocks.ICE_BRICKS)
        gen.wall(DnDBlocks.ICE_BRICK_WALL, DnDBlocks.ICE_BRICKS)
        gen.registerAmethyst(DnDBlocks.MOONCORE)
        gen.registerTallCrystal(DnDBlocks.TALL_REDSTONE_CRYSTAL)

        gen.registerBigChain(DnDBigBlocks.BIG_CHAIN)
        gen.registerBigLantern(DnDBigBlocks.BIG_LANTERN)
        gen.registerBigLantern(DnDBigBlocks.BIG_REDSTONE_LANTERN, true)
        gen.registerBigLantern(DnDBigBlocks.BIG_SOUL_LANTERN)
        gen.registerBigChain(DnDBigBlocks.BIG_CELESTAL_CHAIN)
        val mLB = "block/big_celestal_lantern_bottom"
        gen.registerBigLantern(DnDBigBlocks.BIG_MOON_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_EARTH_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_COMET_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_SUN_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_STAR_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_NEBULAE_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_ECLIPSE_LANTERN, false, mLB)
        DnDBlockLists.bigCandles.forEach { (candle, cake) ->
            gen.registerBigCandle(candle, cake)
        }
        DnDBlockLists.soulCandles.forEach { (candle, cake) ->
            gen.registerCandle2(candle, cake)
        }
        DnDBlockLists.bigSoulCandles.forEach { (candle, cake) ->
            gen.registerBigCandle(candle, cake)
        }
        gen.registerBell(DnDBlocks.CELESTAL_BELL)

        gen.genPsudoFamily(
            DnDNetherBrickBlocks.NETHERRACK_STAIRS,
            DnDNetherBrickBlocks.NETHERRACK_SLAB,
            DnDNetherBrickBlocks.NETHERRACK_WALL,
            Blocks.NETHERRACK
        )
        gen.registerCropWithParent(DnDFloraBlocks.WARPED_WART, id("block/parent/crop"), Properties.AGE_3, 0, 1, 1, 2)
        gen.registerAxisRotated(
            DnDNetherBrickBlocks.NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerSimpleCubeAll(DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDNetherBrickBlocks.CHISELED_RED_NETHER_BRICKS)
        gen.fence(DnDNetherBrickBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS)
        gen.fence(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar(
            DnDNetherBrickBlocks.MIXED_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.RED_NETHER_BRICK_PILLAR
        )
        gen.registerAxisRotated(
            DnDNetherBrickBlocks.RED_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.BLUE_NETHER_BRICKS)
        gen.registerAxisRotated(
            DnDNetherBrickBlocks.BLUE_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar(
            DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.BLUE_NETHER_BRICK_PILLAR
        )
        gen.fence(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.GRAY_NETHER_BRICKS)
        gen.registerAxisRotated(
            DnDNetherBrickBlocks.GRAY_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar(
            DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.GRAY_NETHER_BRICK_PILLAR
        )

        DnDItemLists.blackstoneTools.forEach {
            gen.registerHandheldItem(it)
        }

        gen.registerItemModel(DnDItems.CHILL_CHARGE)
        gen.registerHandheldItem(DnDItems.FREEZE_ROD)
//        gen.registerHandheldItem(DnDItems.ICE_SWORD)


        val mossyPolish = id("block/overgrown/polished_overlay")
        val mossyCobble = id("block/overgrown/cobblestone_overlay")
        val mossyBrick = id("block/overgrown/bricks_overlay")
        gen.registerTintedOverlay(mossyPolish)
        gen.registerTintedOverlay(mossyCobble)
        gen.registerTintedOverlay(mossyBrick)
        gen.cubeAllWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE,
            DnDStoneBlocks.MOSSY_POLISHED_STONE,
            mossyPolish
        )
        gen.stairsWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE_STAIRS,
            DnDStoneBlocks.MOSSY_POLISHED_STONE,
            mossyPolish
        )
        gen.slabWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE_SLAB,
            DnDStoneBlocks.MOSSY_POLISHED_STONE,
            mossyPolish
        )
        gen.wallWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE_WALL,
            DnDStoneBlocks.MOSSY_POLISHED_STONE,
            mossyPolish
        )
        gen.cubeAllWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.stairsWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.slabWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.wallWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.cubeAllWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.stairsWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.slabWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.wallWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICKS, mossyBrick)

        gen.genPsudoFamily(
            DnDWoodBlocks.OAK_WOOD_STAIRS, DnDWoodBlocks.OAK_WOOD_SLAB, DnDWoodBlocks.OAK_WOOD_WALL,
            Blocks.OAK_LOG, Blocks.OAK_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.SPRUCE_WOOD_STAIRS, DnDWoodBlocks.SPRUCE_WOOD_SLAB, DnDWoodBlocks.SPRUCE_WOOD_WALL,
            Blocks.SPRUCE_LOG, Blocks.SPRUCE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.BIRCH_WOOD_STAIRS, DnDWoodBlocks.BIRCH_WOOD_SLAB, DnDWoodBlocks.BIRCH_WOOD_WALL,
            Blocks.BIRCH_LOG, Blocks.BIRCH_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.JUNGLE_WOOD_STAIRS, DnDWoodBlocks.JUNGLE_WOOD_SLAB, DnDWoodBlocks.JUNGLE_WOOD_WALL,
            Blocks.JUNGLE_LOG, Blocks.JUNGLE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.ACACIA_WOOD_STAIRS, DnDWoodBlocks.ACACIA_WOOD_SLAB, DnDWoodBlocks.ACACIA_WOOD_WALL,
            Blocks.ACACIA_LOG, Blocks.ACACIA_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.DARK_OAK_WOOD_STAIRS, DnDWoodBlocks.DARK_OAK_WOOD_SLAB, DnDWoodBlocks.DARK_OAK_WOOD_WALL,
            Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.MANGROVE_WOOD_STAIRS, DnDWoodBlocks.MANGROVE_WOOD_SLAB, DnDWoodBlocks.MANGROVE_WOOD_WALL,
            Blocks.MANGROVE_LOG, Blocks.MANGROVE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.CHERRY_WOOD_STAIRS, DnDWoodBlocks.CHERRY_WOOD_SLAB, DnDWoodBlocks.CHERRY_WOOD_WALL,
            Blocks.CHERRY_LOG, Blocks.CHERRY_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.CASCADE_WOOD_STAIRS, DnDWoodBlocks.CASCADE_WOOD_SLAB, DnDWoodBlocks.CASCADE_WOOD_WALL,
            DnDWoodBlocks.CASCADE_LOG, DnDWoodBlocks.CASCADE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.GALLERY_MAPLE_WOOD_STAIRS,
            DnDWoodBlocks.GALLERY_MAPLE_WOOD_SLAB,
            DnDWoodBlocks.GALLERY_MAPLE_WOOD_WALL,
            DnDWoodBlocks.GALLERY_MAPLE_LOG,
            DnDWoodBlocks.GALLERY_MAPLE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.CRIMSON_HYPHAE_STAIRS, DnDWoodBlocks.CRIMSON_HYPHAE_SLAB, DnDWoodBlocks.CRIMSON_HYPHAE_WALL,
            Blocks.CRIMSON_STEM, Blocks.CRIMSON_HYPHAE
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.WARPED_HYPHAE_STAIRS, DnDWoodBlocks.WARPED_HYPHAE_SLAB, DnDWoodBlocks.WARPED_HYPHAE_WALL,
            Blocks.WARPED_STEM, Blocks.WARPED_HYPHAE
        )
        DnDBlockLists.hollowLogs.forEachIndexed { idx, hollowLog ->
            val log = DnDBlockLists.logsAndStrippedLogs[idx].first
            val strippedLog = DnDBlockLists.logsAndStrippedLogs[idx].second
            gen.hollowLog(hollowLog, log, strippedLog)
            gen.hollowLog(DnDBlockLists.hollowStrippedLogs[idx], strippedLog)
        }
        gen.hollowBambooBlock(DnDWoodBlocks.HOLLOW_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK)
        gen.hollowBambooBlock(DnDWoodBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
        gen.createLogPile(DnDWoodBlocks.BAMBOO_PILE, Blocks.BAMBOO_BLOCK, true)
        gen.createLogPile(DnDWoodBlocks.STRIPPED_BAMBOO_PILE, Blocks.STRIPPED_BAMBOO_BLOCK, true)
        DnDBlockLists.logPiles.forEachIndexed { idx, pile ->
            gen.createLogPile(pile, DnDBlockLists.logsAndStrippedLogs[idx].first)
        }
        DnDBlockLists.leafPiles.forEachIndexed { idx, pile -> gen.createLeafPile(pile, DnDBlockLists.leaves[idx]) }
        gen.registerSingleton(DnDWoodBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES)
        gen.registerDoubleBlock(DnDFloraBlocks.WILD_WHEAT, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerCrop(DnDFloraBlocks.GOLDEN_BEETROOTS, Properties.AGE_3, 0, 1, 2, 3)
        gen.registerCropWithParent(
            DnDFloraBlocks.MOONBERRY_VINELET, id("block/parent/floor_plant"),
            Properties.AGE_2, 0, 1, 2
        )
        gen.createMoonberryVine(DnDFloraBlocks.MOONBERRY_VINE)
        gen.registerItemModel(DnDItems.MOONBERRIES)

        gen.registerSingleton(
            DnDFloraBlocks.ROOT_BLOCK,
            TexturedModel.makeFactory(Texture::all, block("parent/cube_in_eighths", TextureKey.ALL))
        )

        val stone = id("block/cobbled/stone_overlay")
        val deepslate = id("block/cobbled/deepslate_overlay")
        val blackstone = id("block/cobbled/blackstone_overlay")
        gen.cubeOverlay(stone)
        gen.cubeOverlay(deepslate)
        gen.cubeOverlay(blackstone)
        gen.cube15Overlay(stone)
        gen.cube15Overlay(deepslate)
        gen.cube15Overlay(blackstone)

        gen.grassWithOverlay(DnDOverlayBlocks.ROCKY_GRASS, Blocks.GRASS_BLOCK, stone)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.ROCKY_PODZOL, Blocks.PODZOL, stone)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.ROCKY_MYCELIUM, Blocks.MYCELIUM, stone)
        gen.cube15WithOverlay(DnDOverlayBlocks.ROCKY_DIRT_PATH, Blocks.DIRT_PATH, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_DIRT, Blocks.DIRT, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_COARSE_DIRT, Blocks.COARSE_DIRT, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_MUD, Blocks.MUD, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_SNOW, Blocks.SNOW_BLOCK, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_GRAVEL, Blocks.GRAVEL, stone)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.ROCKY_SAND, Blocks.SAND, stone)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.ROCKY_RED_SAND, Blocks.RED_SAND, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_SOUL_SAND, Blocks.SOUL_SAND, stone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.ROCKY_SOUL_SOIL, Blocks.SOUL_SOIL, stone)

        gen.grassWithOverlay(DnDOverlayBlocks.SLATED_GRASS, Blocks.GRASS_BLOCK, deepslate)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.SLATED_PODZOL, Blocks.PODZOL, deepslate)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.SLATED_MYCELIUM, Blocks.MYCELIUM, deepslate)
        gen.cube15WithOverlay(DnDOverlayBlocks.SLATED_DIRT_PATH, Blocks.DIRT_PATH, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_DIRT, Blocks.DIRT, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_COARSE_DIRT, Blocks.COARSE_DIRT, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_MUD, Blocks.MUD, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_SNOW, Blocks.SNOW_BLOCK, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_GRAVEL, Blocks.GRAVEL, deepslate)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.SLATED_SAND, Blocks.SAND, deepslate)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.SLATED_RED_SAND, Blocks.RED_SAND, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_SOUL_SAND, Blocks.SOUL_SAND, deepslate)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.SLATED_SOUL_SOIL, Blocks.SOUL_SOIL, deepslate)

        gen.grassWithOverlay(DnDOverlayBlocks.BLACKSTONE_GRASS, Blocks.GRASS_BLOCK, blackstone)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.BLACKSTONE_PODZOL, Blocks.PODZOL, blackstone)
        gen.cubeSnowableColumnWithOverlay(DnDOverlayBlocks.BLACKSTONE_MYCELIUM, Blocks.MYCELIUM, blackstone)
        gen.cube15WithOverlay(DnDOverlayBlocks.BLACKSTONE_DIRT_PATH, Blocks.DIRT_PATH, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_DIRT, Blocks.DIRT, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_COARSE_DIRT, Blocks.COARSE_DIRT, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_MUD, Blocks.MUD, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_SNOW, Blocks.SNOW_BLOCK, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_GRAVEL, Blocks.GRAVEL, blackstone)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.BLACKSTONE_SAND, Blocks.SAND, blackstone)
        gen.rotatableCubeAllWithOverlay(DnDOverlayBlocks.BLACKSTONE_RED_SAND, Blocks.RED_SAND, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_SOUL_SAND, Blocks.SOUL_SAND, blackstone)
        gen.cubeAllWithTintedOverlay(DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL, Blocks.SOUL_SOIL, blackstone)

        /*.with(
            When.create().set(LeafPileBlock.PILE_LAYERS, 8),
            BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
        )*/
        if (isModLoaded("dramaticdoors")) DramaticDoorsCompat.datagen(gen)
    }

    override fun generateItemModels(gen: ItemModelGenerator) = Unit

    private fun BlockStateModelGenerator.parentedModel(
        block: Block, textBlock: Block, parent: Identifier
    ): Identifier =
        Model(parent.myb, Optional.empty(), ALL_KRY)
            .upload(block.model(), Texture().put(ALL_KRY, textBlock.model()), this.modelCollector)

    private fun BlockStateModelGenerator.parentedModel(
        block: Identifier, textBlock: Block, parent: Identifier
    ): Identifier =
        Model(parent.myb, Optional.empty(), ALL_KRY)
            .upload(block, Texture().put(ALL_KRY, textBlock.model()), this.modelCollector)


    private
    val <T : Any?> T.myb get() = Optional.ofNullable(this)

    private fun Identifier.suffix(str: String) = Identifier.of(this.namespace, "${this.path}$str")
    private fun Block.model(): Identifier = ModelIds.getBlockModelId(this)
}
