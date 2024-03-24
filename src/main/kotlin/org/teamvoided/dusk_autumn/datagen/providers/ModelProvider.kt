package org.teamvoided.dusk_autumn.datagen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.init.DuskBlocks
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    private val ALL_TEX: TextureKey = TextureKey.of("all")
    private val leafPile: Identifier = id("block/parent/leaf_pile")
    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        val id = gen.parentedModel(DuskBlocks.OAK_LEAF_PILE, leafPile)

        gen.blockStateCollector.accept(
            MultipartBlockStateSupplier.create(DuskBlocks.OAK_LEAF_PILE)
                .with(
                    When.create().set(Properties.LAYERS, 1),
                    BlockStateVariant.create().put(VariantSettings.MODEL, id)
                ).with(
                    When.create().set(Properties.LAYERS, 2),
                    BlockStateVariant.create().put(VariantSettings.MODEL, id)
                ).with(
                    When.create().set(Properties.LAYERS, 4),
                    BlockStateVariant.create().put(VariantSettings.MODEL, id)
                ).with(
                    When.create().set(Properties.LAYERS, 8),
                    BlockStateVariant.create().put(VariantSettings.MODEL, id)
                )
        )
//                .with(
//                    When.create().set(Properties.LAYERS, 8),
//                    BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, Rotation.R270)
//                        .put(VariantSettings.UVLOCK, true)
//                )
    }

    override fun generateItemModels(gen: ItemModelGenerator) {}

    private fun BlockStateModelGenerator.parentedModel(block: Block, parent: Identifier): Identifier =
        Model(parent.myb, Optional.empty(), ALL_TEX)
            .upload(block.model(), Texture().put(ALL_TEX, block.model()), this.modelCollector)


    private val <T : Any?> T.myb get() = Optional.ofNullable(this)
    private fun Block.model(): Identifier = ModelIds.getBlockModelId(this)
}