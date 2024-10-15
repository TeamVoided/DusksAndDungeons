package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.item.Items
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DusksAndDungeons.isModLoaded
import org.teamvoided.dusk_autumn.block.DnDFamilies
import org.teamvoided.dusk_autumn.compat.DramaticDoorsCompat
import org.teamvoided.dusk_autumn.data.gen.providers.models.*
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
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
//        gen.stoneStateFactories = mapOf(
//            DnDBlocks.CASCADE_WOOD to woodStates,
//            DnDBlocks.GALLERY_MAPLE_WOOD to woodStates
//        )
//        gen.sandstoneModels = mapOf(
//            DnDBlocks.CASCADE_WOOD to TexturedModel.SIDE_END_WALL.get(DnDBlocks.CASCADE_WOOD),
//            DnDBlocks.GALLERY_MAPLE_WOOD to TexturedModel.SIDE_END_WALL.get(DnDBlocks.GALLERY_MAPLE_WOOD)

//            DnDBlocks.MIXED_NETHER_BRICKS to TexturedModel.SIDE_TOP_BOTTOM_WALL[DnDBlocks.MIXED_NETHER_BRICKS].texture { texture: Texture ->
//                texture.put(TextureKey.TOP, Texture.getId(Blocks.NETHER_BRICKS))
//                texture.put(TextureKey.BOTTOM, Texture.getId(Blocks.RED_NETHER_BRICKS))
//                texture.put(TextureKey.SIDE, Texture.getId(DnDBlocks.MIXED_NETHER_BRICKS))
//            }
//        )
        gen.registerItemModel(Items.AIR) //fer debug porpoises
        DnDFamilies.modelsBlockFamilies.forEach {
            gen.registerCubeAllModelTexturePool(it.baseBlock).family(it)
        }

        BigModels.bigModels(gen)
        FloraModels.floraModels(gen)
        NetherModels.netherModels(gen)
        OverlayModels.overlayModels(gen)
        StoneModels.stoneModels(gen)
        WoodModels.woodModels(gen)


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

        /*.with(
            When.create().set(LeafPileBlock.PILE_LAYERS, 8),
            BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
        )*/
        if (isModLoaded("dramaticdoors")) DramaticDoorsCompat.datagen(gen)
    }

    private val single = listOf(DnDItems.SCARECROW_ITEM, DnDItems.DIE_ITEM, DnDItems.CHILL_CHARGE)

    override fun generateItemModels(gen: ItemModelGenerator) {
        single.forEach { gen.register(it, Models.SINGLE_LAYER_ITEM) }
        gen.register(DnDItems.FREEZE_ROD, Models.HANDHELD_ROD)
//        gen.register(DnDItems.ICE_SWORD, Models.HANDHELD)
        gen.register(DnDItems.WEB_WEAVER, Models.HANDHELD)
        gen.register(DnDItems.HARVESTER_SCYTHE, Models.HANDHELD)

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
