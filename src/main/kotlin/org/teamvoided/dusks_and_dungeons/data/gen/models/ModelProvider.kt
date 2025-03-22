package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.DnDFamilies
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.ICE_BRICKS
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.ICE_SET
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.block.WOOD_SETS
import org.teamvoided.dusks_and_dungeons.util.datagen.iceStairs
import org.teamvoided.dusks_and_dungeons.util.datagen.registerTallCrystal
import org.teamvoided.dusks_and_dungeons.util.datagen.slab
import org.teamvoided.dusks_and_dungeons.util.datagen.wall
import org.teamvoided.voidlib.devin.extensions.model.createBlockSet
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    private val ALL_KRY: TextureKey = TextureKey.of("all")
    val excludeModels = WOOD_SETS + listOf(
        ICE_SET, ICE_BRICKS,
        DnDBlocks.OVERGROWN_POLISHED_STONE,
        DnDBlocks.OVERGROWN_COBBLESTONE,
        DnDBlocks.OVERGROWN_STONE_BRICKS,
    )


    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
//        gen.registerItemModel(Items.AIR) //fer debug porpoises
        DnDFamilies.modelsBlockFamilies.forEach {
            gen.registerCubeAllModelTexturePool(it.baseBlock).family(it)
        }

        BigModels.register(gen)
        FloraModels.register(gen)
        NetherModels.netherModels(gen)
        OverlayModels.overlayModels(gen)
        StoneModels.stoneModels(gen)
        WoodModels.woodModels(gen)


        gen.iceStairs(ICE_SET.stairs, Blocks.ICE)
        gen.slab(ICE_SET.slab, Blocks.ICE)
        gen.wall(ICE_SET.wall, Blocks.ICE)
        gen.registerSimpleCubeAll(ICE_BRICKS.parent)

        gen.iceStairs(ICE_BRICKS.stairs, ICE_BRICKS.parent)
        gen.slab(ICE_BRICKS.slab, ICE_BRICKS.parent)
        gen.wall(ICE_BRICKS.wall, ICE_BRICKS.parent)

        gen.registerAmethyst(DnDBlocks.MOONCORE)
        gen.registerTallCrystal(DnDBlocks.TALL_REDSTONE_CRYSTAL)
        @Suppress("DEPRECATION")
        gen.registerBuiltin(ModelIds.getMinecraftNamespacedBlock("decorated_pot"), Blocks.TERRACOTTA)
            .includeWithoutItem(DnDBlocks.POT_O_SCREAMS)
        @Suppress("DEPRECATION")
        gen.registerBuiltin(ModelIds.getMinecraftNamespacedBlock("chest"), Blocks.OAK_PLANKS)
            .includeWithoutItem(DnDBlocks.CHEST_O_SOULS)

        gen.registerSimpleCubeAll(DnDBlocks.MOLTEN_LAVASPONGE)
        gen.registerSimpleCubeAll(DnDBlocks.BRITTLE_LAVASPONGE)
        gen.registerSimpleCubeAll(DnDBlocks.GLOWING_LAVASPONGE)
        gen.registerSimpleCubeAll(DnDBlocks.LAVASPONGE)

        /*.with(
            When.create().set(LeafPileBlock.PILE_LAYERS, 8),
            BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
        )*/

        SETS.filterNot(excludeModels::contains).forEach(gen::createBlockSet)
    }

    private val single = listOf(
        DnDItems.SCARECROW_ITEM,
        DnDItems.DIE_ITEM,
        DnDItems.LANTERN_PUMPKIN_SEEDS,
        DnDItems.MOSSKIN_PUMPKIN_SEEDS,
        DnDItems.PALE_PUMPKIN_SEEDS,
        DnDItems.GLOOM_PUMPKIN_SEEDS,
        DnDItems.CORN_KERNELS,
        DnDItems.CORN,
        DnDItems.CORN_SYRUP_BOTTLE,
        DnDItems.CHILL_CHARGE
    )

    override fun generateItemModels(gen: ItemModelGenerator) {
        single.forEach { gen.register(it, Models.SINGLE_LAYER_ITEM) }
        gen.register(DnDItems.FREEZE_ROD, Models.HANDHELD_ROD)
//        gen.register(DnDItems.ICE_SWORD, Models.HANDHELD)
        gen.register(DnDItems.HARVESTER_SCYTHE, item("parent/handheld_32", TextureKey.LAYER0))

        val webWeaver = item("web_weaver", TextureKey.LAYER0)
        gen.register(DnDItems.WEB_WEAVER, "_0", webWeaver)
        gen.register(DnDItems.WEB_WEAVER, "_1", webWeaver)
        gen.register(DnDItems.WEB_WEAVER, "_2", webWeaver)
    }

    private fun item(parent: String, vararg requiredTextures: TextureKey): Model =
        Model(Optional.of(id("item/$parent")), Optional.empty(), *requiredTextures)

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
