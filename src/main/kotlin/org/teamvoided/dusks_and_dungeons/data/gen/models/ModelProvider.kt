package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.InfestedBlock
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.mc
import org.teamvoided.dusks_and_dungeons.block.DnDFamilies
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.BigModels
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.StoneModels
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers.fence
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers.modelId
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.block.WOOD_SETS
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.voidlib.consortium.block.color.VanillaColorCollections
import org.teamvoided.voidlib.devin.extensions.model.createBlockSet
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    private val ALL_KRY: TextureSlot = TextureSlot.create("all")

    val excludeModels = WOOD_SETS + listOf(
        DnDBlocks.ICE_SET,
        DnDBlocks.SNOW_SET,
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


        gen.iceStairs(DnDBlocks.ICE_SET.stairs, Blocks.ICE)
        gen.slab(DnDBlocks.ICE_SET.slab, Blocks.ICE)
        gen.wall(DnDBlocks.ICE_SET.wall, Blocks.ICE)

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

        gen.createBrushableBlock(DnDBlocks.SUSPICIOUS_RED_SAND)

        // region TODO(1.0) move all vv to appropriate files and categories
        val infestedBlocks = listOf(
            DnDBlocks.INFESTED_MOSSY_COBBLESTONE,
            DnDBlocks.INFESTED_COBBLED_DEEPSLATE,
            DnDBlocks.INFESTED_DEEPSLATE_BRICKS,
            DnDBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS,
            DnDBlocks.INFESTED_DEEPSLATE_TILES,
            DnDBlocks.INFESTED_CRACKED_DEEPSLATE_TILES,
            DnDBlocks.INFESTED_POLISHED_DEEPSLATE,
        ).map { it as InfestedBlock }

        for (block in infestedBlocks) {
            gen.copyModel(block.hostBlock, block)
        }

        gen.fence(DnDBlocks.BRICK_FENCE, Blocks.BRICKS)
        gen.redstoneLantern(DnDBlocks.REDSTONE_LANTERN)
        gen.denseCube(DnDBlocks.HEAVY_CUBE)

        gen.tintedPane(Blocks.TINTED_GLASS, DnDBlocks.TINTED_GLASS_PANE)

        gen.bookshelf(DnDBlocks.SPRUCE_BOOKSHELF, Blocks.SPRUCE_PLANKS)
        gen.bookshelf(DnDBlocks.BIRCH_BOOKSHELF, Blocks.BIRCH_PLANKS)
        gen.bookshelf(DnDBlocks.JUNGLE_BOOKSHELF, Blocks.JUNGLE_PLANKS)
        gen.bookshelf(DnDBlocks.ACACIA_BOOKSHELF, Blocks.ACACIA_PLANKS)
        gen.bookshelf(DnDBlocks.DARK_OAK_BOOKSHELF, Blocks.DARK_OAK_PLANKS)
        gen.bookshelf(DnDBlocks.MANGROVE_BOOKSHELF, Blocks.MANGROVE_PLANKS)
        gen.bookshelf(DnDBlocks.CHERRY_BOOKSHELF, Blocks.CHERRY_PLANKS)
        gen.bookshelf(DnDBlocks.BAMBOO_BOOKSHELF, Blocks.BAMBOO_PLANKS)
        gen.bookshelf(DnDBlocks.CRIMSON_BOOKSHELF, Blocks.CRIMSON_PLANKS)
        gen.bookshelf(DnDBlocks.WARPED_BOOKSHELF, Blocks.WARPED_PLANKS)

        for ((idx, block) in DnDBlocks.WOOL_CARPET_PLATE.withIndex()) {
            gen.carpetPlate(block, VanillaColorCollections.WOOL.list[idx])
        }
        gen.carpetPlate(DnDBlocks.MOSS_CARPET_PLATE, Blocks.MOSS_BLOCK)

        gen.stairs(DnDBlocks.SNOW_SET.stairs, Blocks.SNOW)
        gen.slab(DnDBlocks.SNOW_SET.slab, Blocks.SNOW, Blocks.SNOW_BLOCK)
        gen.wall(DnDBlocks.SNOW_SET.wall, Blocks.SNOW.model())




        // Pairs
        gen.stairs(DnDBlocks.SMOOTH_STONE_STAIR, Blocks.SMOOTH_STONE)
        gen.wall(DnDBlocks.SMOOTH_STONE_WALL, Blocks.SMOOTH_STONE)
        gen.stairs(DnDBlocks.CUT_SANDSTONE_STAIR, mc("block/sandstone_top"), modelId(Blocks.CUT_SANDSTONE))
        gen.wall(DnDBlocks.CUT_SANDSTONE_WALL, Blocks.CUT_SANDSTONE)
        gen.stairs(DnDBlocks.CUT_RED_SANDSTONE_STAIR, mc("block/red_sandstone_top"), modelId(Blocks.CUT_RED_SANDSTONE))
        gen.wall(DnDBlocks.CUT_RED_SANDSTONE_WALL, Blocks.CUT_RED_SANDSTONE)

        // Walls
        gen.wall(DnDBlocks.STONE_WALL, Blocks.STONE)
        gen.wall(DnDBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE)
        gen.wall(DnDBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE)
        gen.wall(DnDBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE)
        gen.wall(DnDBlocks.SMOOTH_SANDSTONE_WALL, mc("block/sandstone_top"))
        gen.wall(DnDBlocks.SMOOTH_RED_SANDSTONE_WALL, mc("block/red_sandstone_top"))
        gen.wall(DnDBlocks.PRISMARINE_BRICKS_WALL, Blocks.PRISMARINE_BRICKS)
        gen.wall(DnDBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE)
        gen.wallOffset(DnDBlocks.PURPUR_WALL, Blocks.PURPUR_BLOCK)
        gen.wall(DnDBlocks.QUARTZ_WALL, mc("block/quartz_block_side"))
        gen.wall(DnDBlocks.SMOOTH_QUARTZ_WALL, mc("block/quartz_block_bottom"))


        gen.addAxis(Blocks.MANGROVE_ROOTS)
        // endregion

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
        DnDItems.TINTED_GLASS_BOTTLE
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
    private fun ResourceLocation.suffix(str: String) =
        ResourceLocation.fromNamespaceAndPath(this.namespace, "${this.path}$str")

    private fun Block.model(): ResourceLocation = ModelLocationUtils.getModelLocation(this)
}
