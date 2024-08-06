package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util.DnDBlockLists
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
        gen.registerAmethyst(DnDBlocks.MOONCORE)
        gen.registerTallCrystal(DnDBlocks.TALL_REDSTONE_CRYSTAL)

        gen.registerItemModel(DnDBlocks.BIG_CHAIN.asItem())
        gen.registerAxisRotated(DnDBlocks.BIG_CHAIN, ModelIds.getBlockModelId(DnDBlocks.BIG_CHAIN))
        gen.registerBigLantern(DnDBlocks.BIG_LANTERN)
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

        gen.stairs(DnDBlocks.NETHERRACK_STAIRS, Blocks.NETHERRACK)
        gen.slab(DnDBlocks.NETHERRACK_SLAB, Blocks.NETHERRACK)
        gen.wall(DnDBlocks.NETHERRACK_WALL, Blocks.NETHERRACK)
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
        gen.registerHandheldItem(DnDItems.ICE_SWORD)


        val mossyCobble = id("block/overgrown/cobblestone_overlay")
        val mossyBrick = id("block/overgrown/bricks_overlay")
        gen.registerTintedOverlay(mossyCobble)
        gen.registerTintedOverlay(mossyBrick)
        gen.cubeAllWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.stairsWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.slabWithTintedOverlay(
            DnDBlocks.OVERGROWN_COBBLESTONE_SLAB,
            Blocks.MOSSY_COBBLESTONE,
            mossyCobble
        )
        gen.wallWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.cubeAllWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.stairsWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.slabWithTintedOverlay(
            DnDBlocks.OVERGROWN_STONE_BRICK_SLAB,
            Blocks.MOSSY_STONE_BRICKS,
            mossyBrick
        )
        gen.wallWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICKS, mossyBrick)

        DnDBlockLists.logPiles.forEach { (it, texture) ->
            gen.createLogPile(it, texture)
        }
        DnDBlockLists.leafPiles.forEach { (it, texture) ->
            gen.createLeafPile(it, texture)
        }
        gen.registerSingleton(DnDBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES)
        gen.registerFlowerbed(DnDBlocks.BLUE_PETALS)
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
