package org.teamvoided.dusk_autumn.datagen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.init.DuskBlocks
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    private val ALL_KRY: TextureKey = TextureKey.of("all")
    private fun leafPile(num: String? = null): Identifier =
        id("block/parent/leaf_pile${if (num != null) "_$num" else ""}")

    private fun leafPileHanging(num: String? = null): Identifier =
        id("block/parent/leaf_pile_hanging${if (num != null) "_$num" else ""}")


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

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {

        gen.registerDoor(DuskBlocks.CASCADE_DOOR)
        gen.registerDoor(DuskBlocks.BLUE_DOOR)
        gen.registerTrapdoor(DuskBlocks.CASCADE_TRAPDOOR)

        leafPiles.forEach { (it, texture) ->
            val layer1 = gen.parentedModel(it, texture, leafPile())
            val layer2 = gen.parentedModel(it.modelSuffix("_8"), texture, leafPile("8"))
            val layer3 = gen.parentedModel(it.modelSuffix("_12"), texture, leafPile("12"))
            val hanging1 = gen.parentedModel(it.modelSuffix("_hanging"), texture, leafPileHanging())
            val hanging2 = gen.parentedModel(it.modelSuffix("_hanging_8"), texture, leafPileHanging("8"))
            val hanging3 = gen.parentedModel(it.modelSuffix("_hanging_12"), texture, leafPileHanging("12"))
            val full = gen.parentedModel(it.modelSuffix("_full"), texture, leafPile("full"))
            gen.blockStateCollector.accept(
                MultipartBlockStateSupplier.create(it)
                    .with(
                        When.create().set(LeafPileBlock.PILE_LAYERS, 1).set(Properties.HANGING, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, layer1)
                    ).with(
                        When.create().set(LeafPileBlock.PILE_LAYERS, 2).set(Properties.HANGING, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, layer2)
                    ).with(
                        When.create().set(LeafPileBlock.PILE_LAYERS, 3).set(Properties.HANGING, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, layer3)
                    )
                    .with(
                        When.create().set(LeafPileBlock.PILE_LAYERS, 1).set(Properties.HANGING, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, hanging1)
                    ).with(
                        When.create().set(LeafPileBlock.PILE_LAYERS, 2).set(Properties.HANGING, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, hanging2)
                    ).with(
                        When.create().set(LeafPileBlock.PILE_LAYERS, 3).set(Properties.HANGING, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, hanging3)
                    )
                    .with(
                        When.create().set(LeafPileBlock.PILE_LAYERS, 4),
                        BlockStateVariant.create().put(VariantSettings.MODEL, full)
                    )
            )
        }

        /*.with(
            When.create().set(LeafPileBlock.PILE_LAYERS, 8),
            BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
        )*/

    }

    override fun generateItemModels(gen: ItemModelGenerator) {}

//    private fun BlockStateModelGenerator.parentedModel(block: Block, parent: Identifier): Identifier = this.parentedModel(block, block, parent)

    private fun BlockStateModelGenerator.parentedModel(block: Block, textBlock: Block, parent: Identifier): Identifier =
        Model(parent.myb, Optional.empty(), ALL_KRY)
            .upload(block.model(), Texture().put(ALL_KRY, textBlock.model()), this.modelCollector)

    private fun BlockStateModelGenerator.parentedModel(
        block: Identifier, textBlock: Block, parent: Identifier
    ): Identifier =
        Model(parent.myb, Optional.empty(), ALL_KRY)
            .upload(block, Texture().put(ALL_KRY, textBlock.model()), this.modelCollector)


    private val <T : Any?> T.myb get() = Optional.ofNullable(this)

    private fun Block.modelSuffix(str: String) = this.model().suffix(str)

    private fun Identifier.suffix(str: String) = Identifier(this.namespace, "${this.path}$str")
    private fun Block.model(): Identifier = ModelIds.getBlockModelId(this)
}