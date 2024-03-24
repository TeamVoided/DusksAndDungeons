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
import org.teamvoided.dusk_autumn.init.DuskBlocks
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    private val ALL_TEX: TextureKey = TextureKey.of("all")
    private fun leafPile(num: Number? = null): Identifier =
        id("block/parent/leaf_pile${if (num != null) "_$num" else ""}")

    private fun leafPileHanging(num: Number? = null): Identifier =
        id("block/parent/leaf_pile_hanging${if (num != null) "_$num" else ""}")


    val leafPiles = listOf(
        Pair(DuskBlocks.OAK_LEAF_PILE, Blocks.OAK_LEAVES)
    )

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        leafPiles.forEach { (it, texture) ->

            val layer1 = gen.parentedModel(it, texture, leafPile())
            val layer2 = gen.parentedModel(it.modelSuffix("_8"), texture, leafPile(8))
            val layer3 = gen.parentedModel(it.modelSuffix("_12"), texture, leafPile(12))
            val layer4 = gen.parentedModel(it.modelSuffix("_16"), texture, leafPile(16))


            val hanging1 = gen.parentedModel(it.modelSuffix("_hanging"), texture, leafPileHanging())
            val hanging2 = gen.parentedModel(it.modelSuffix("_hanging_8"), texture, leafPileHanging(8))
            val hanging3 = gen.parentedModel(it.modelSuffix("_hanging_12"), texture, leafPileHanging(12))
            val hanging4 = gen.parentedModel(it.modelSuffix("_hanging_16"), texture, leafPileHanging(16))

            gen.blockStateCollector.accept(
                MultipartBlockStateSupplier.create(DuskBlocks.OAK_LEAF_PILE)
                    .with(
                        When.create().set(Properties.LAYERS, 1).set(Properties.HANGING, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, layer1)
                    ).with(
                        When.create().set(Properties.LAYERS, 2).set(Properties.HANGING, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, layer2)
                    ).with(
                        When.create().set(Properties.LAYERS, 3).set(Properties.HANGING, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, layer3)
                    ).with(
                        When.create().set(Properties.LAYERS, 4).set(Properties.HANGING, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, layer4)
                    )
                    .with(
                        When.create().set(Properties.LAYERS, 1).set(Properties.HANGING, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, hanging1)
                    ).with(
                        When.create().set(Properties.LAYERS, 2).set(Properties.HANGING, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, hanging2)
                    ).with(
                        When.create().set(Properties.LAYERS, 3).set(Properties.HANGING, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, hanging3)
                    ).with(
                        When.create().set(Properties.LAYERS, 4).set(Properties.HANGING, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, hanging4)
                    )
            )
        }
//                .with(
//                    When.create().set(Properties.LAYERS, 8),
//                    BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, Rotation.R270)
//                        .put(VariantSettings.UVLOCK, true)
//                )
    }

    override fun generateItemModels(gen: ItemModelGenerator) {}

//    private fun BlockStateModelGenerator.parentedModel(block: Block, parent: Identifier): Identifier =
//        Model(parent.myb, Optional.empty(), ALL_TEX)
//            .upload(block.model(), Texture().put(ALL_TEX, block.model()), this.modelCollector)

    private fun BlockStateModelGenerator.parentedModel(block: Block, textBlock: Block, parent: Identifier): Identifier =
        Model(parent.myb, Optional.empty(), ALL_TEX)
            .upload(block.model(), Texture().put(ALL_TEX, textBlock.model()), this.modelCollector)

    private fun BlockStateModelGenerator.parentedModel(
        block: Identifier,
        textBlock: Block,
        parent: Identifier
    ): Identifier =
        Model(parent.myb, Optional.empty(), ALL_TEX)
            .upload(block, Texture().put(ALL_TEX, textBlock.model()), this.modelCollector)


    private val <T : Any?> T.myb get() = Optional.ofNullable(this)

    private fun Block.modelSuffix(str: String) = this.model().suffix(str)

    private fun Identifier.suffix(str: String) = Identifier(this.namespace, "${this.path}$str")
    private fun Block.model(): Identifier = ModelIds.getBlockModelId(this)
}