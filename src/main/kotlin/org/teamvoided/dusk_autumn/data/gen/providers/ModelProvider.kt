package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.DuskBlockFamilies
import org.teamvoided.dusk_autumn.block.DuskBlockLists
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import org.teamvoided.dusk_autumn.item.DuskItemLists
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
//            },
            DuskBlocks.MIXED_NETHER_BRICK_PILLAR to TexturedModel.CUBE_COLUMN[DuskBlocks.MIXED_NETHER_BRICK_PILLAR].texture { texture: Texture ->
                texture.put(TextureKey.TOP, Texture.getSubId(DuskBlocks.NETHER_BRICK_PILLAR, "_top"))
                texture.put(TextureKey.BOTTOM, Texture.getSubId(DuskBlocks.RED_NETHER_BRICK_PILLAR, "_top"))
            }
        )
        DuskBlockFamilies.modelsBlockFamilies.forEach {
            gen.registerCubeAllModelTexturePool(it.baseBlock).family(it)
        }
        gen.registerFlowerPotPlant(
            DuskBlocks.CASCADE_SAPLING,
            DuskBlocks.POTTED_CASCADE_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerLog(DuskBlocks.CASCADE_LOG)
            .log(DuskBlocks.CASCADE_LOG)
            .wood(DuskBlocks.CASCADE_WOOD)
        gen.registerLog(DuskBlocks.STRIPPED_CASCADE_LOG)
            .log(DuskBlocks.STRIPPED_CASCADE_LOG)
            .wood(DuskBlocks.STRIPPED_CASCADE_WOOD)
        gen.registerHangingSign(
            DuskBlocks.STRIPPED_CASCADE_LOG,
            DuskBlocks.CASCADE_HANGING_SIGN,
            DuskBlocks.CASCADE_WALL_HANGING_SIGN
        )
        gen.registerSingleton(DuskBlocks.CASCADE_LEAVES, TexturedModel.LEAVES)
        gen.registerDoor(DuskBlocks.BLUE_DOOR)
        gen.registerFlowerPotPlant(
            DuskBlocks.GOLDEN_BIRCH_SAPLING,
            DuskBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )

        gen.registerItemModel(DuskBlocks.BIG_CHAIN.asItem())
        gen.registerAxisRotated(DuskBlocks.BIG_CHAIN, ModelIds.getBlockModelId(DuskBlocks.BIG_CHAIN))
        gen.registerBigLantern(DuskBlocks.BIG_LANTERN)
        gen.registerBigLantern(DuskBlocks.BIG_SOUL_LANTERN)
        DuskBlockLists.bigCandles.forEach { (candle, cake) ->
            gen.registerBigCandle(candle, cake)
        }
        DuskBlockLists.soulCandles.forEach { (candle, cake) ->
            gen.registerCandle(candle, cake)
        }
        DuskBlockLists.bigSoulCandles.forEach { (candle, cake) ->
            gen.registerBigCandle(candle, cake)
        }

        gen.fence(DuskBlocks.BRICK_FENCE, Blocks.BRICKS)

        gen.stairs(DuskBlocks.NETHERRACK_STAIRS, Blocks.NETHERRACK)
        gen.slab(DuskBlocks.NETHERRACK_SLAB, Blocks.NETHERRACK)
        gen.wall(DuskBlocks.NETHERRACK_WALL, Blocks.NETHERRACK)
        gen.fence(DuskBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DuskBlocks.CRACKED_RED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DuskBlocks.CHISELED_RED_NETHER_BRICKS)
        gen.fence(DuskBlocks.MIXED_NETHER_BRICK_FENCE, DuskBlocks.MIXED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DuskBlocks.CRACKED_MIXED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DuskBlocks.CHISELED_MIXED_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar()
        gen.registerAxisRotated(
            DuskBlocks.NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerAxisRotated(
            DuskBlocks.RED_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        DuskItemLists.blackstoneTools.forEach {
            gen.registerHandheldItem(it)
        }

        val mossyCobble = id("block/overgrown/cobblestone_overlay")
        val mossyBrick = id("block/overgrown/bricks_overlay")
        gen.registerTintedOverlay(mossyCobble)
        gen.registerTintedOverlay(mossyBrick)
        gen.cubeAllWithOverlay(DuskBlocks.OVERGROWN_COBBLESTONE, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.stairsWithTintedOverlay(DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.slabWithTintedOverlay(
            DuskBlocks.OVERGROWN_COBBLESTONE_SLAB,
            Blocks.MOSSY_COBBLESTONE,
            mossyCobble
        )
        gen.wallWithOverlay(DuskBlocks.OVERGROWN_COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.cubeAllWithOverlay(DuskBlocks.OVERGROWN_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.stairsWithTintedOverlay(DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.slabWithTintedOverlay(
            DuskBlocks.OVERGROWN_STONE_BRICK_SLAB,
            Blocks.MOSSY_STONE_BRICKS,
            mossyBrick
        )
        gen.wallWithOverlay(DuskBlocks.OVERGROWN_STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICKS, mossyBrick)

        DuskBlockLists.logPiles.forEach { (it, texture) ->
            gen.createLogPile(it, texture)
        }
        DuskBlockLists.leafPiles.forEach { (it, texture) ->
            gen.createLeafPile(it, texture)
        }
        gen.registerSingleton(DuskBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES)
        gen.registerFlowerbed(DuskBlocks.BLUE_PETALS)
        gen.registerDoubleBlock(DuskBlocks.WILD_WHEAT, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerCrop(DuskBlocks.GOLDEN_BEETROOTS, Properties.AGE_3, 0, 1, 2, 3)
        gen.registerFloorCrop(DuskBlocks.MOONBERRY_VINELET, Properties.AGE_2, 0, 1, 2)
        gen.createMoonberryVine(DuskBlocks.MOONBERRY_VINE)
        gen.registerItemModel(DuskItems.MOONBERRIES)

        gen.registerSingleton(
            DuskBlocks.ROOT_BLOCK,
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

        gen.grassWithOverlay(DuskBlocks.ROCKY_GRASS, Blocks.GRASS_BLOCK, stone)
        gen.cubeSnowableColumnWithOverlay(DuskBlocks.ROCKY_PODZOL, Blocks.PODZOL, stone)
        gen.cubeSnowableColumnWithOverlay(DuskBlocks.ROCKY_MYCELIUM, Blocks.MYCELIUM, stone)
        gen.cube15WithOverlay(DuskBlocks.ROCKY_DIRT_PATH, Blocks.DIRT_PATH, stone)
        gen.cubeAllWithOverlay(DuskBlocks.ROCKY_DIRT, Blocks.DIRT, stone)
        gen.cubeAllWithOverlay(DuskBlocks.ROCKY_COARSE_DIRT, Blocks.COARSE_DIRT, stone)
        gen.cubeAllWithOverlay(DuskBlocks.ROCKY_MUD, Blocks.MUD, stone)
        gen.cubeAllWithOverlay(DuskBlocks.ROCKY_SNOW, Blocks.SNOW_BLOCK, stone)
        gen.cubeAllWithOverlay(DuskBlocks.ROCKY_GRAVEL, Blocks.GRAVEL, stone)
        gen.rotatableCubeAllWithOverlay(DuskBlocks.ROCKY_SAND, Blocks.SAND, stone)
        gen.rotatableCubeAllWithOverlay(DuskBlocks.ROCKY_RED_SAND, Blocks.RED_SAND, stone)
        gen.cubeAllWithOverlay(DuskBlocks.ROCKY_SOUL_SAND, Blocks.SOUL_SAND, stone)
        gen.cubeAllWithOverlay(DuskBlocks.ROCKY_SOUL_SOIL, Blocks.SOUL_SOIL, stone)

        gen.grassWithOverlay(DuskBlocks.SLATED_GRASS, Blocks.GRASS_BLOCK, deepslate)
        gen.cubeSnowableColumnWithOverlay(DuskBlocks.SLATED_PODZOL, Blocks.PODZOL, deepslate)
        gen.cubeSnowableColumnWithOverlay(DuskBlocks.SLATED_MYCELIUM, Blocks.MYCELIUM, deepslate)
        gen.cube15WithOverlay(DuskBlocks.SLATED_DIRT_PATH, Blocks.DIRT_PATH, deepslate)
        gen.cubeAllWithOverlay(DuskBlocks.SLATED_DIRT, Blocks.DIRT, deepslate)
        gen.cubeAllWithOverlay(DuskBlocks.SLATED_COARSE_DIRT, Blocks.COARSE_DIRT, deepslate)
        gen.cubeAllWithOverlay(DuskBlocks.SLATED_MUD, Blocks.MUD, deepslate)
        gen.cubeAllWithOverlay(DuskBlocks.SLATED_SNOW, Blocks.SNOW_BLOCK, deepslate)
        gen.cubeAllWithOverlay(DuskBlocks.SLATED_GRAVEL, Blocks.GRAVEL, deepslate)
        gen.rotatableCubeAllWithOverlay(DuskBlocks.SLATED_SAND, Blocks.SAND, deepslate)
        gen.rotatableCubeAllWithOverlay(DuskBlocks.SLATED_RED_SAND, Blocks.RED_SAND, deepslate)
        gen.cubeAllWithOverlay(DuskBlocks.SLATED_SOUL_SAND, Blocks.SOUL_SAND, deepslate)
        gen.cubeAllWithOverlay(DuskBlocks.SLATED_SOUL_SOIL, Blocks.SOUL_SOIL, deepslate)

        gen.grassWithOverlay(DuskBlocks.BLACKSTONE_GRASS, Blocks.GRASS_BLOCK, blackstone)
        gen.cubeSnowableColumnWithOverlay(DuskBlocks.BLACKSTONE_PODZOL, Blocks.PODZOL, blackstone)
        gen.cubeSnowableColumnWithOverlay(DuskBlocks.BLACKSTONE_MYCELIUM, Blocks.MYCELIUM, blackstone)
        gen.cube15WithOverlay(DuskBlocks.BLACKSTONE_DIRT_PATH, Blocks.DIRT_PATH, blackstone)
        gen.cubeAllWithOverlay(DuskBlocks.BLACKSTONE_DIRT, Blocks.DIRT, blackstone)
        gen.cubeAllWithOverlay(DuskBlocks.BLACKSTONE_COARSE_DIRT, Blocks.COARSE_DIRT, blackstone)
        gen.cubeAllWithOverlay(DuskBlocks.BLACKSTONE_MUD, Blocks.MUD, blackstone)
        gen.cubeAllWithOverlay(DuskBlocks.BLACKSTONE_SNOW, Blocks.SNOW_BLOCK, blackstone)
        gen.cubeAllWithOverlay(DuskBlocks.BLACKSTONE_GRAVEL, Blocks.GRAVEL, blackstone)
        gen.rotatableCubeAllWithOverlay(DuskBlocks.BLACKSTONE_SAND, Blocks.SAND, blackstone)
        gen.rotatableCubeAllWithOverlay(DuskBlocks.BLACKSTONE_RED_SAND, Blocks.RED_SAND, blackstone)
        gen.cubeAllWithOverlay(DuskBlocks.BLACKSTONE_SOUL_SAND, Blocks.SOUL_SAND, blackstone)
        gen.cubeAllWithOverlay(DuskBlocks.BLACKSTONE_SOUL_SOIL, Blocks.SOUL_SOIL, blackstone)

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
