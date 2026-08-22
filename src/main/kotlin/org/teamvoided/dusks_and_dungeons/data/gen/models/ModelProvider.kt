package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.DnDFamilies
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.BigModels
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.StoneModels
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.ICE_SET
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.block.WOOD_SETS
import org.teamvoided.dusks_and_dungeons.util.datagen.createTrivialState
import org.teamvoided.dusks_and_dungeons.util.datagen.iceStairs
import org.teamvoided.dusks_and_dungeons.util.datagen.slab
import org.teamvoided.dusks_and_dungeons.util.datagen.wall
import org.teamvoided.voidlib.devin.extensions.model.createBlockSet
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    private val ALL_KRY: TextureSlot = TextureSlot.create("all")
    val excludeModels = WOOD_SETS + listOf(
        ICE_SET,
        DnDBlocks.OVERGROWN_POLISHED_STONE,
        DnDBlocks.OVERGROWN_COBBLESTONE,
        DnDBlocks.OVERGROWN_STONE_BRICKS,
    )


    override fun generateBlockStateModels(gen: BlockModelGenerators) {
//        gen.registerItemModel(Items.AIR) //fer debug porpoises
        DnDFamilies.modelsBlockFamilies.forEach {
            gen.family(it.baseBlock).generateFor(it)
        }

        BigModels.register(gen)
        FloraModels.register(gen)
        NetherModels.netherModels(gen)
        StoneModels.stoneModels(gen)
        WoodModels.woodModels(gen)


        gen.iceStairs(ICE_SET.stairs, Blocks.ICE)
        gen.slab(ICE_SET.slab, Blocks.ICE)
        gen.wall(ICE_SET.wall, Blocks.ICE)

        /* Future Content
        gen.registerSimpleCubeAll(ICE_BRICKS.parent)

        gen.iceStairs(ICE_BRICKS.stairs, ICE_BRICKS.parent)
        gen.slab(ICE_BRICKS.slab, ICE_BRICKS.parent)
        gen.wall(ICE_BRICKS.wall, ICE_BRICKS.parent)

        gen.registerSimpleCubeAll(DnDBlocks.MOLTEN_LAVASPONGE)
        gen.registerSimpleCubeAll(DnDBlocks.BRITTLE_LAVASPONGE)
        gen.registerSimpleCubeAll(DnDBlocks.GLOWING_LAVASPONGE)
        gen.registerSimpleCubeAll(DnDBlocks.LAVASPONGE)*/

        /*.with(
            When.create().set(LeafPileBlock.PILE_LAYERS, 8),
            BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
        )*/

        SETS.filterNot(excludeModels::contains).forEach(gen::createBlockSet)

        gen.createTrivialBlock(DnDBlocks.TINTED_SAND, TexturedModel.LEAVES)
        gen.createTrivialState(DnDBlocks.TINTED_SANDSTONE)
        gen.createTrivialState(DnDBlocks.CHISELED_TINTED_SANDSTONE)
        gen.createTrivialState(DnDBlocks.CUT_TINTED_SANDSTONE)

        gen.delegateItemModel(DnDItems.RACCOON_SPAWN_EGG, ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"))

    }

    private val single = listOf(
        DnDItems.SCARECROW_ITEM,
        DnDItems.LANTERN_PUMPKIN_SEEDS,
        DnDItems.MOSSKIN_PUMPKIN_SEEDS,
        DnDItems.PALE_PUMPKIN_SEEDS,
        DnDItems.GLOOM_PUMPKIN_SEEDS,
        DnDItems.CORN_KERNELS,
        DnDItems.CORN,
        DnDItems.CORN_SYRUP_BOTTLE,
    )

    override fun generateItemModels(gen: ItemModelGenerators) {
        single.forEach { gen.generateFlatItem(it, ModelTemplates.FLAT_ITEM) }
    }

    private fun item(parent: String, vararg requiredTextures: TextureSlot): ModelTemplate =
        ModelTemplate(Optional.of(id("item/$parent")), Optional.empty(), *requiredTextures)

    private fun BlockModelGenerators.parentedModel(
        block: Block, textBlock: Block, parent: ResourceLocation,
    ): ResourceLocation =
        ModelTemplate(parent.myb, Optional.empty(), ALL_KRY)
            .create(block.model(), TextureMapping().put(ALL_KRY, textBlock.model()), this.modelOutput)

    private fun BlockModelGenerators.parentedModel(
        block: ResourceLocation, textBlock: Block, parent: ResourceLocation,
    ): ResourceLocation =
        ModelTemplate(parent.myb, Optional.empty(), ALL_KRY)
            .create(block, TextureMapping().put(ALL_KRY, textBlock.model()), this.modelOutput)

    private val <T : Any?> T.myb get() = Optional.ofNullable(this)
    private fun ResourceLocation.suffix(str: String) = ResourceLocation.fromNamespaceAndPath(this.namespace, "${this.path}$str")
    private fun Block.model(): ResourceLocation = ModelLocationUtils.getModelLocation(this)
}
