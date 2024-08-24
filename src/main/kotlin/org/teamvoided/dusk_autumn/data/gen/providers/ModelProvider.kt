package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util.DnDItemLists
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.DnDFamilies
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.util.*
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    private val ALL_KRY: TextureKey = TextureKey.of("all")

//    val blockFamily = listOf(
//    )

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        gen.sandstoneModels = mapOf(
//            DuskBlocks.MIXED_NETHER_BRICKS to TexturedModel.SIDE_TOP_BOTTOM_WALL[DuskBlocks.MIXED_NETHER_BRICKS].texture { texture: Texture ->
//                texture.put(TextureKey.TOP, Texture.getId(Blocks.NETHER_BRICKS))
//                texture.put(TextureKey.BOTTOM, Texture.getId(Blocks.RED_NETHER_BRICKS))
//                texture.put(TextureKey.SIDE, Texture.getId(DuskBlocks.MIXED_NETHER_BRICKS))
//            }
        )
        DnDFamilies.modelsBlockFamilies.forEach {
            gen.registerCubeAllModelTexturePool(it.baseBlock).family(it)
        }
        gen.registerFlowerPotPlant(
            DnDBlocks.CASCADE_SAPLING,
            DnDBlocks.POTTED_CASCADE_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerLog(DnDBlocks.CASCADE_LOG)
            .log(DnDBlocks.CASCADE_LOG)
            .wood(DnDBlocks.CASCADE_WOOD)
        gen.registerLog(DnDBlocks.STRIPPED_CASCADE_LOG)
            .log(DnDBlocks.STRIPPED_CASCADE_LOG)
            .wood(DnDBlocks.STRIPPED_CASCADE_WOOD)
        gen.registerHangingSign(
            DnDBlocks.STRIPPED_CASCADE_LOG,
            DnDBlocks.CASCADE_HANGING_SIGN,
            DnDBlocks.CASCADE_WALL_HANGING_SIGN
        )
        gen.registerSingleton(DnDBlocks.CASCADE_LEAVES, TexturedModel.LEAVES)
        gen.registerDoor(DnDBlocks.BLUE_DOOR)
        gen.registerFlowerPotPlant(
            DnDBlocks.GOLDEN_BIRCH_SAPLING,
            DnDBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerSpiderlilly(DnDBlocks.SPIDERLILY, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerFlowerbed(DnDBlocks.BLUE_PETALS)
        gen.registerFlowerbed(DnDBlocks.WILD_PETALS)
        gen.registerTreeMushroom(DnDBlocks.BROWN_TREE_FUNGUS, "parent/brown_tree_fungus")

        gen.registerAxisRotated(
            DnDBlocks.STONE_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerAxisRotated(
            DnDBlocks.DEEPSLATE_PILLAR,
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

        gen.registerItemModel(DnDBlocks.BIG_CHAIN.asItem())
        gen.registerAxisRotated(DnDBlocks.BIG_CHAIN, ModelIds.getBlockModelId(DnDBlocks.BIG_CHAIN))
        gen.registerBigLantern(DnDBlocks.BIG_LANTERN)
        gen.registerBigLantern(DnDBlocks.BIG_REDSTONE_LANTERN, true)
        gen.registerBigLantern(DnDBlocks.BIG_SOUL_LANTERN)
        DnDBlockLists.bigCandles.forEach { (candle, cake) ->
            gen.registerBigCandle(candle, cake)
        }
        DnDBlockLists.soulCandles.forEach { (candle, cake) ->
            gen.registerCandle2(candle, cake)
        }
        DnDBlockLists.bigSoulCandles.forEach { (candle, cake) ->
            gen.registerBigCandle(candle, cake)
        }

        gen.genPsudoFamily(
            DnDBlocks.NETHERRACK_STAIRS, DnDBlocks.NETHERRACK_SLAB, DnDBlocks.NETHERRACK_WALL,
            Blocks.NETHERRACK
        )
        gen.registerCropWithParent(DnDBlocks.WARPED_WART, id("block/parent/crop"), Properties.AGE_3, 0, 1, 1, 2)
        gen.registerAxisRotated(
            DnDBlocks.NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerSimpleCubeAll(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CHISELED_RED_NETHER_BRICKS)
        gen.fence(DnDBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS)
        gen.fence(DnDBlocks.MIXED_NETHER_BRICK_FENCE, DnDBlocks.MIXED_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar(DnDBlocks.MIXED_NETHER_BRICK_PILLAR, DnDBlocks.RED_NETHER_BRICK_PILLAR)
        gen.registerAxisRotated(
            DnDBlocks.RED_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDBlocks.BLUE_NETHER_BRICK_FENCE, DnDBlocks.BLUE_NETHER_BRICKS)
        gen.registerAxisRotated(
            DnDBlocks.BLUE_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDBlocks.MIXED_BLUE_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar(
            DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
            DnDBlocks.BLUE_NETHER_BRICK_PILLAR
        )
        gen.fence(DnDBlocks.GRAY_NETHER_BRICK_FENCE, DnDBlocks.GRAY_NETHER_BRICKS)
        gen.registerAxisRotated(
            DnDBlocks.GRAY_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDBlocks.MIXED_GRAY_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar(
            DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
            DnDBlocks.GRAY_NETHER_BRICK_PILLAR
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
        gen.cubeAllWithTintedOverlay(DnDBlocks.OVERGROWN_POLISHED_STONE, DnDBlocks.MOSSY_POLISHED_STONE, mossyPolish)
        gen.stairsWithTintedOverlay(
            DnDBlocks.OVERGROWN_POLISHED_STONE_STAIRS,
            DnDBlocks.MOSSY_POLISHED_STONE,
            mossyPolish
        )
        gen.slabWithTintedOverlay(DnDBlocks.OVERGROWN_POLISHED_STONE_SLAB, DnDBlocks.MOSSY_POLISHED_STONE, mossyPolish)
        gen.wallWithTintedOverlay(DnDBlocks.OVERGROWN_POLISHED_STONE_WALL, DnDBlocks.MOSSY_POLISHED_STONE, mossyPolish)
        gen.cubeAllWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.stairsWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.slabWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.wallWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.cubeAllWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.stairsWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.slabWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.wallWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICKS, mossyBrick)

        gen.genPsudoFamily(
            DnDBlocks.OAK_WOOD_STAIRS, DnDBlocks.OAK_WOOD_SLAB, DnDBlocks.OAK_WOOD_WALL,
            Blocks.OAK_LOG, Blocks.OAK_WOOD
        )
        gen.genPsudoFamily(
            DnDBlocks.SPRUCE_WOOD_STAIRS, DnDBlocks.SPRUCE_WOOD_SLAB, DnDBlocks.SPRUCE_WOOD_WALL,
            Blocks.SPRUCE_LOG, Blocks.SPRUCE_WOOD
        )
        gen.genPsudoFamily(
            DnDBlocks.BIRCH_WOOD_STAIRS, DnDBlocks.BIRCH_WOOD_SLAB, DnDBlocks.BIRCH_WOOD_WALL,
            Blocks.BIRCH_LOG, Blocks.BIRCH_WOOD
        )
        gen.genPsudoFamily(
            DnDBlocks.JUNGLE_WOOD_STAIRS, DnDBlocks.JUNGLE_WOOD_SLAB, DnDBlocks.JUNGLE_WOOD_WALL,
            Blocks.JUNGLE_LOG, Blocks.JUNGLE_WOOD
        )
        gen.genPsudoFamily(
            DnDBlocks.ACACIA_WOOD_STAIRS, DnDBlocks.ACACIA_WOOD_SLAB, DnDBlocks.ACACIA_WOOD_WALL,
            Blocks.ACACIA_LOG, Blocks.ACACIA_WOOD
        )
        gen.genPsudoFamily(
            DnDBlocks.DARK_OAK_WOOD_STAIRS, DnDBlocks.DARK_OAK_WOOD_SLAB, DnDBlocks.DARK_OAK_WOOD_WALL,
            Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD
        )
        gen.genPsudoFamily(
            DnDBlocks.MANGROVE_WOOD_STAIRS, DnDBlocks.MANGROVE_WOOD_SLAB, DnDBlocks.MANGROVE_WOOD_WALL,
            Blocks.MANGROVE_LOG, Blocks.MANGROVE_WOOD
        )
        gen.genPsudoFamily(
            DnDBlocks.CHERRY_WOOD_STAIRS, DnDBlocks.CHERRY_WOOD_SLAB, DnDBlocks.CHERRY_WOOD_WALL,
            Blocks.CHERRY_LOG, Blocks.CHERRY_WOOD
        )
        gen.genPsudoFamily(
            DnDBlocks.CASCADE_WOOD_STAIRS, DnDBlocks.CASCADE_WOOD_SLAB, DnDBlocks.CASCADE_WOOD_WALL,
            DnDBlocks.CASCADE_LOG, DnDBlocks.CASCADE_WOOD
        )
        gen.genPsudoFamily(
            DnDBlocks.CRIMSON_HYPHAE_STAIRS, DnDBlocks.CRIMSON_HYPHAE_SLAB, DnDBlocks.CRIMSON_HYPHAE_WALL,
            Blocks.CRIMSON_STEM, Blocks.CRIMSON_HYPHAE
        )
        gen.genPsudoFamily(
            DnDBlocks.WARPED_HYPHAE_STAIRS, DnDBlocks.WARPED_HYPHAE_SLAB, DnDBlocks.WARPED_HYPHAE_WALL,
            Blocks.WARPED_STEM, Blocks.WARPED_HYPHAE
        )
        DnDBlockLists.hollowLogs.forEachIndexed { idx, it ->
            val log = DnDBlockLists.logsAndStrippedLogs[idx].first
            val strippedLog = DnDBlockLists.logsAndStrippedLogs[idx].second
            gen.hollowLog(
                it,
                log,
                strippedLog
            )
            gen.hollowLog(
                DnDBlockLists.hollowStrippedLogs[idx],
                strippedLog
            )
        }
        gen.hollowBambooBlock(DnDBlocks.HOLLOW_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK)
        gen.hollowBambooBlock(DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
        gen.createLogPile(DnDBlocks.BAMBOO_PILE, Blocks.BAMBOO_BLOCK, true)
        gen.createLogPile(DnDBlocks.STRIPPED_BAMBOO_PILE, Blocks.STRIPPED_BAMBOO_BLOCK, true)
        DnDBlockLists.logPiles.forEachIndexed { idx, it  ->
            gen.createLogPile(it, DnDBlockLists.logsAndStrippedLogs[idx].first)
        }
        DnDBlockLists.leafPiles.forEachIndexed { idx, it ->
            gen.createLeafPile(it, DnDBlockLists.leaves[idx])
        }
        gen.registerSingleton(DnDBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES)
        gen.registerDoubleBlock(DnDBlocks.WILD_WHEAT, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerCrop(DnDBlocks.GOLDEN_BEETROOTS, Properties.AGE_3, 0, 1, 2, 3)
        gen.registerCropWithParent(
            DnDBlocks.MOONBERRY_VINELET, id("block/parent/floor_plant"),
            Properties.AGE_2, 0, 1, 2
        )
        gen.createMoonberryVine(DnDBlocks.MOONBERRY_VINE)
        gen.registerItemModel(DnDItems.MOONBERRIES)

        gen.registerSingleton(
            DnDBlocks.ROOT_BLOCK,
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

        gen.grassWithOverlay(DnDBlocks.ROCKY_GRASS, Blocks.GRASS_BLOCK, stone)
        gen.cubeSnowableColumnWithOverlay(DnDBlocks.ROCKY_PODZOL, Blocks.PODZOL, stone)
        gen.cubeSnowableColumnWithOverlay(DnDBlocks.ROCKY_MYCELIUM, Blocks.MYCELIUM, stone)
        gen.cube15WithOverlay(DnDBlocks.ROCKY_DIRT_PATH, Blocks.DIRT_PATH, stone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.ROCKY_DIRT, Blocks.DIRT, stone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.ROCKY_COARSE_DIRT, Blocks.COARSE_DIRT, stone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.ROCKY_MUD, Blocks.MUD, stone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.ROCKY_SNOW, Blocks.SNOW_BLOCK, stone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.ROCKY_GRAVEL, Blocks.GRAVEL, stone)
        gen.rotatableCubeAllWithOverlay(DnDBlocks.ROCKY_SAND, Blocks.SAND, stone)
        gen.rotatableCubeAllWithOverlay(DnDBlocks.ROCKY_RED_SAND, Blocks.RED_SAND, stone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.ROCKY_SOUL_SAND, Blocks.SOUL_SAND, stone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.ROCKY_SOUL_SOIL, Blocks.SOUL_SOIL, stone)

        gen.grassWithOverlay(DnDBlocks.SLATED_GRASS, Blocks.GRASS_BLOCK, deepslate)
        gen.cubeSnowableColumnWithOverlay(DnDBlocks.SLATED_PODZOL, Blocks.PODZOL, deepslate)
        gen.cubeSnowableColumnWithOverlay(DnDBlocks.SLATED_MYCELIUM, Blocks.MYCELIUM, deepslate)
        gen.cube15WithOverlay(DnDBlocks.SLATED_DIRT_PATH, Blocks.DIRT_PATH, deepslate)
        gen.cubeAllWithTintedOverlay(DnDBlocks.SLATED_DIRT, Blocks.DIRT, deepslate)
        gen.cubeAllWithTintedOverlay(DnDBlocks.SLATED_COARSE_DIRT, Blocks.COARSE_DIRT, deepslate)
        gen.cubeAllWithTintedOverlay(DnDBlocks.SLATED_MUD, Blocks.MUD, deepslate)
        gen.cubeAllWithTintedOverlay(DnDBlocks.SLATED_SNOW, Blocks.SNOW_BLOCK, deepslate)
        gen.cubeAllWithTintedOverlay(DnDBlocks.SLATED_GRAVEL, Blocks.GRAVEL, deepslate)
        gen.rotatableCubeAllWithOverlay(DnDBlocks.SLATED_SAND, Blocks.SAND, deepslate)
        gen.rotatableCubeAllWithOverlay(DnDBlocks.SLATED_RED_SAND, Blocks.RED_SAND, deepslate)
        gen.cubeAllWithTintedOverlay(DnDBlocks.SLATED_SOUL_SAND, Blocks.SOUL_SAND, deepslate)
        gen.cubeAllWithTintedOverlay(DnDBlocks.SLATED_SOUL_SOIL, Blocks.SOUL_SOIL, deepslate)

        gen.grassWithOverlay(DnDBlocks.BLACKSTONE_GRASS, Blocks.GRASS_BLOCK, blackstone)
        gen.cubeSnowableColumnWithOverlay(DnDBlocks.BLACKSTONE_PODZOL, Blocks.PODZOL, blackstone)
        gen.cubeSnowableColumnWithOverlay(DnDBlocks.BLACKSTONE_MYCELIUM, Blocks.MYCELIUM, blackstone)
        gen.cube15WithOverlay(DnDBlocks.BLACKSTONE_DIRT_PATH, Blocks.DIRT_PATH, blackstone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.BLACKSTONE_DIRT, Blocks.DIRT, blackstone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.BLACKSTONE_COARSE_DIRT, Blocks.COARSE_DIRT, blackstone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.BLACKSTONE_MUD, Blocks.MUD, blackstone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.BLACKSTONE_SNOW, Blocks.SNOW_BLOCK, blackstone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.BLACKSTONE_GRAVEL, Blocks.GRAVEL, blackstone)
        gen.rotatableCubeAllWithOverlay(DnDBlocks.BLACKSTONE_SAND, Blocks.SAND, blackstone)
        gen.rotatableCubeAllWithOverlay(DnDBlocks.BLACKSTONE_RED_SAND, Blocks.RED_SAND, blackstone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.BLACKSTONE_SOUL_SAND, Blocks.SOUL_SAND, blackstone)
        gen.cubeAllWithTintedOverlay(DnDBlocks.BLACKSTONE_SOUL_SOIL, Blocks.SOUL_SOIL, blackstone)

        /*.with(
            When.create().set(LeafPileBlock.PILE_LAYERS, 8),
            BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
        )*/
    }

    override fun generateItemModels(gen: ItemModelGenerator) {
    }

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
