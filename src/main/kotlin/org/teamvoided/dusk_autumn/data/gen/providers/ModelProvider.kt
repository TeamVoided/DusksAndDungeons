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
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import org.teamvoided.dusk_autumn.util.*
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    private val ALL_KRY: TextureKey = TextureKey.of("all")

    val leafPiles = listOf(
        (DuskBlocks.OAK_LEAF_PILE to Blocks.OAK_LEAVES),
        (DuskBlocks.SPRUCE_LEAF_PILE to Blocks.SPRUCE_LEAVES),
        (DuskBlocks.BIRCH_LEAF_PILE to Blocks.BIRCH_LEAVES),
        (DuskBlocks.JUNGLE_LEAF_PILE to Blocks.JUNGLE_LEAVES),
        (DuskBlocks.ACACIA_LEAF_PILE to Blocks.ACACIA_LEAVES),
        (DuskBlocks.DARK_OAK_LEAF_PILE to Blocks.DARK_OAK_LEAVES),
        (DuskBlocks.MANGROVE_LEAF_PILE to Blocks.MANGROVE_LEAVES),
        (DuskBlocks.CHERRY_LEAF_PILE to Blocks.CHERRY_LEAVES),
        (DuskBlocks.AZALEA_LEAF_PILE to Blocks.AZALEA_LEAVES),
        (DuskBlocks.FLOWERING_AZALEA_LEAF_PILE to Blocks.FLOWERING_AZALEA_LEAVES),
        (DuskBlocks.CASCADE_LEAF_PILE to DuskBlocks.CASCADE_LEAVES),
        (DuskBlocks.GOLDEN_BIRCH_LEAF_PILE to DuskBlocks.GOLDEN_BIRCH_LEAVES)
    )
//    val blockFamily = listOf(
//    )

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        DuskBlockFamilies.allBlockFamilies.forEach {
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
        gen.registerSimpleCubeAll(DuskBlocks.MIXED_NETHER_BRICKS)
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

        val mossyCobble = "overgrown/cobblestone_overlay"
        val mossyBrick = "overgrown/bricks_overlay"
        gen.cubeWithTintedOverlay(DuskBlocks.OVERGROWN_COBBLESTONE, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.stairsWithTintedOverlay(DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.slabWithTintedOverlay(DuskBlocks.OVERGROWN_COBBLESTONE_SLAB, DuskBlocks.OVERGROWN_COBBLESTONE.model(), Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.wallWithTintedOverlay(DuskBlocks.OVERGROWN_COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.cubeWithTintedOverlay(DuskBlocks.OVERGROWN_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.stairsWithTintedOverlay(DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.slabWithTintedOverlay(DuskBlocks.OVERGROWN_STONE_BRICK_SLAB, DuskBlocks.OVERGROWN_STONE_BRICKS.model(), Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.wallWithTintedOverlay(DuskBlocks.OVERGROWN_STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICKS, mossyBrick)

        leafPiles.forEach { (it, texture) ->
            gen.createLeafPile(it, texture)
        }
        gen.registerSingleton(DuskBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES)
        gen.registerFlowerbed(DuskBlocks.BLUE_PETALS)
        gen.registerDoubleBlock(DuskBlocks.WILD_WHEAT, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerCrop(DuskBlocks.GOLDEN_BEETROOTS, Properties.AGE_3, 0, 1, 2, 3)
        gen.registerItemModel(DuskItems.MOONBERRY_VINELET)
        gen.createMoonberryVine(DuskBlocks.MOONBERRY_VINE)
        gen.registerItemModel(DuskItems.MOONBERRIES)

        val stone = "cobbled/stone_overlay"
        val deepslate = "cobbled/deepslate_overlay"
        val blackstone = "cobbled/blackstone_overlay"
        gen.rockyGrassOverlay(DuskBlocks.ROCKY_GRASS, stone)
        gen.rockyTopSoilsOverlay(DuskBlocks.ROCKY_PODZOL, Blocks.PODZOL, DuskBlocks.ROCKY_GRASS, stone)
        gen.rockyTopSoilsOverlay(DuskBlocks.ROCKY_MYCELIUM, Blocks.MYCELIUM, DuskBlocks.ROCKY_GRASS, stone)
        gen.pathWithOverlay(DuskBlocks.ROCKY_DIRT_PATH, Blocks.DIRT_PATH, Blocks.DIRT, stone)
        gen.cubeWithOverlay(DuskBlocks.ROCKY_DIRT, Blocks.DIRT, stone)
        gen.cubeWithOverlay(DuskBlocks.ROCKY_COARSE_DIRT, Blocks.COARSE_DIRT, stone)
        gen.cubeWithOverlay(DuskBlocks.ROCKY_MUD, Blocks.MUD, stone)
        gen.cubeWithOverlay(DuskBlocks.ROCKY_SNOW, Blocks.SNOW, stone)
        gen.cubeWithOverlay(DuskBlocks.ROCKY_GRAVEL, Blocks.GRAVEL, stone)
        gen.rotatableCubeWithOverlay(DuskBlocks.ROCKY_SAND, Blocks.SAND, stone)
        gen.rotatableCubeWithOverlay(DuskBlocks.ROCKY_RED_SAND, Blocks.RED_SAND, stone)
        gen.cubeWithOverlay(DuskBlocks.ROCKY_SOUL_SAND, Blocks.SOUL_SAND, stone)
        gen.cubeWithOverlay(DuskBlocks.ROCKY_SOUL_SOIL, Blocks.SOUL_SOIL, stone)

        gen.rockyGrassOverlay(DuskBlocks.SLATED_GRASS, deepslate)
        gen.rockyTopSoilsOverlay(DuskBlocks.SLATED_PODZOL, Blocks.PODZOL, DuskBlocks.SLATED_GRASS, deepslate)
        gen.rockyTopSoilsOverlay(DuskBlocks.SLATED_MYCELIUM, Blocks.MYCELIUM, DuskBlocks.SLATED_GRASS, deepslate)
        gen.pathWithOverlay(DuskBlocks.SLATED_DIRT_PATH, Blocks.DIRT_PATH, Blocks.DIRT, deepslate)
        gen.cubeWithOverlay(DuskBlocks.SLATED_DIRT, Blocks.DIRT, deepslate)
        gen.cubeWithOverlay(DuskBlocks.SLATED_COARSE_DIRT, Blocks.COARSE_DIRT, deepslate)
        gen.cubeWithOverlay(DuskBlocks.SLATED_MUD, Blocks.MUD, deepslate)
        gen.cubeWithOverlay(DuskBlocks.SLATED_SNOW, Blocks.SNOW, deepslate)
        gen.cubeWithOverlay(DuskBlocks.SLATED_GRAVEL, Blocks.GRAVEL, deepslate)
        gen.rotatableCubeWithOverlay(DuskBlocks.SLATED_SAND, Blocks.SAND, deepslate)
        gen.rotatableCubeWithOverlay(DuskBlocks.SLATED_RED_SAND, Blocks.RED_SAND, deepslate)
        gen.cubeWithOverlay(DuskBlocks.SLATED_SOUL_SAND, Blocks.SOUL_SAND, deepslate)
        gen.cubeWithOverlay(DuskBlocks.SLATED_SOUL_SOIL, Blocks.SOUL_SOIL, deepslate)

        gen.rockyGrassOverlay(DuskBlocks.BLACKSTONE_GRASS, blackstone)
        gen.rockyTopSoilsOverlay(DuskBlocks.BLACKSTONE_PODZOL, Blocks.PODZOL, DuskBlocks.BLACKSTONE_GRASS, blackstone)
        gen.rockyTopSoilsOverlay(
            DuskBlocks.BLACKSTONE_MYCELIUM,
            Blocks.MYCELIUM,
            DuskBlocks.BLACKSTONE_GRASS,
            blackstone
        )
        gen.pathWithOverlay(DuskBlocks.BLACKSTONE_DIRT_PATH, Blocks.DIRT_PATH, Blocks.DIRT, blackstone)
        gen.cubeWithOverlay(DuskBlocks.BLACKSTONE_DIRT, Blocks.DIRT, blackstone)
        gen.cubeWithOverlay(DuskBlocks.BLACKSTONE_COARSE_DIRT, Blocks.COARSE_DIRT, blackstone)
        gen.cubeWithOverlay(DuskBlocks.BLACKSTONE_MUD, Blocks.MUD, blackstone)
        gen.cubeWithOverlay(DuskBlocks.BLACKSTONE_SNOW, Blocks.SNOW, blackstone)
        gen.cubeWithOverlay(DuskBlocks.BLACKSTONE_GRAVEL, Blocks.GRAVEL, blackstone)
        gen.rotatableCubeWithOverlay(DuskBlocks.BLACKSTONE_SAND, Blocks.SAND, blackstone)
        gen.rotatableCubeWithOverlay(DuskBlocks.BLACKSTONE_RED_SAND, Blocks.RED_SAND, blackstone)
        gen.cubeWithOverlay(DuskBlocks.BLACKSTONE_SOUL_SAND, Blocks.SOUL_SAND, blackstone)
        gen.cubeWithOverlay(DuskBlocks.BLACKSTONE_SOUL_SOIL, Blocks.SOUL_SOIL, blackstone)

        /*.with(
            When.create().set(LeafPileBlock.PILE_LAYERS, 8),
            BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
        )*/

        /*val allDirectionFalse = When.create()
            .set(AbstractLichenBlock.getProperty(Direction.NORTH), false)
            .set(AbstractLichenBlock.getProperty(Direction.SOUTH), false)
            .set(AbstractLichenBlock.getProperty(Direction.EAST), false)
            .set(AbstractLichenBlock.getProperty(Direction.WEST), false)
            .set(AbstractLichenBlock.getProperty(Direction.DOWN), false)
            .set(AbstractLichenBlock.getProperty(Direction.UP), false)
        gen.blockStateCollector.accept(
            MultipartBlockStateSupplier.create(DuskBlocks.MOONBERRY_VINE)
                .with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.NORTH), true)
                        .set(MoonberryVineBlock.BERRIES, 0),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        )
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.NORTH), true)
                        .set(MoonberryVineBlock.BERRIES, 1),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries")
                        )
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.NORTH), true)
                        .set(MoonberryVineBlock.BERRIES, 2),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries_wall_extra")
                        )
                ).with(
                    allDirectionFalse,
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        )
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.EAST), true)
                        .set(MoonberryVineBlock.BERRIES, 0),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R90)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.EAST), true)
                        .set(MoonberryVineBlock.BERRIES, 1),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R90)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.EAST), true)
                        .set(MoonberryVineBlock.BERRIES, 2),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries_wall_extra")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R90)
                ).with(
                    allDirectionFalse,
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R90)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.SOUTH), true)
                        .set(MoonberryVineBlock.BERRIES, 0),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R180)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.SOUTH), true)
                        .set(MoonberryVineBlock.BERRIES, 1),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R180)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.SOUTH), true)
                        .set(MoonberryVineBlock.BERRIES, 2),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries_wall_extra")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R180)
                ).with(
                    allDirectionFalse,
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R180)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.WEST), true)
                        .set(MoonberryVineBlock.BERRIES, 0),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R270)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.WEST), true)
                        .set(MoonberryVineBlock.BERRIES, 1),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R270)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.WEST), true)
                        .set(MoonberryVineBlock.BERRIES, 2),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries_wall_extra")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R270)
                ).with(
                    allDirectionFalse,
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.Y, VariantSettings.Rotation.R270)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.UP), true)
                        .set(MoonberryVineBlock.BERRIES, 0),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.X, VariantSettings.Rotation.R270)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.UP), true)
                        .set(MoonberryVineBlock.BERRIES, 1),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries")
                        ).put(VariantSettings.X, VariantSettings.Rotation.R270)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.UP), true)
                        .set(MoonberryVineBlock.BERRIES, 2),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries_floor_extra")
                        ).put(VariantSettings.X, VariantSettings.Rotation.R180)
                ).with(
                    allDirectionFalse,
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.X, VariantSettings.Rotation.R90)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.DOWN), true)
                        .set(MoonberryVineBlock.BERRIES, 0),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.X, VariantSettings.Rotation.R90)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.DOWN), true)
                        .set(MoonberryVineBlock.BERRIES, 1),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries")
                        ).put(VariantSettings.X, VariantSettings.Rotation.R90)
                ).with(
                    When.create().set(AbstractLichenBlock.getProperty(Direction.DOWN), true)
                        .set(MoonberryVineBlock.BERRIES, 2),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine_berries_floor_extra")
                        )
                ).with(
                    allDirectionFalse,
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            id("block/moonberry_vine")
                        ).put(VariantSettings.X, VariantSettings.Rotation.R90)
                )
        )*/
    }

    override fun generateItemModels(gen: ItemModelGenerator) {
//        gen.register(DuskItems.CRAB_SPAWN_EGG, Models.)
    }

//    private fun BlockStateModelGenerator.parentedModel(block: Block, parent: Identifier): Identifier = this.parentedModel(block, block, parent)

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

    private fun Block.modelSuffix(str: String) = this.model().suffix(str)

    private fun Identifier.suffix(str: String) = Identifier.of(this.namespace, "${this.path}$str")
    private fun Block.model(): Identifier = ModelIds.getBlockModelId(this)
}
