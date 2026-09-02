package org.teamvoided.dusks_and_dungeons.datagen.old.models

import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.mc
import org.teamvoided.dusks_and_dungeons.block.DnDFamilies
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.BigModels
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.StoneModels
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.fence
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.modelId
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.tintedCarpetPlate
import org.teamvoided.dusks_and_dungeons.datagen.old.util.*
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.block.WOOD_SETS
import org.teamvoided.voidlib.consortium.block.color.VanillaColorCollections
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.extensions.model.createBlockSet

class ModelProvider(o: FabricOutput) : FabricModelProvider(o) {

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

        SETS.filterNot(excludeModels::contains).forEach(gen::createBlockSet)

        BigModels.create(gen)
        FloraModels.create(gen)
        NetherModels.create(gen)
        StoneModels.create(gen)
        WoodModels.create(gen)

        gen.iceStairs(DnDBlocks.ICE_SET.stairs, Blocks.ICE)
        gen.slab(DnDBlocks.ICE_SET.slab, Blocks.ICE)
        gen.wall(DnDBlocks.ICE_SET.wall, Blocks.ICE)

        // region TODO(1.0) move all vv to appropriate files and categories
        val infestedBlocks = listOf(
            DnDBlocks.INFESTED_MOSSY_COBBLESTONE,
            DnDBlocks.INFESTED_COBBLED_DEEPSLATE,
            DnDBlocks.INFESTED_DEEPSLATE_BRICKS,
            DnDBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS,
            DnDBlocks.INFESTED_DEEPSLATE_TILES,
            DnDBlocks.INFESTED_CRACKED_DEEPSLATE_TILES,
            DnDBlocks.INFESTED_POLISHED_DEEPSLATE,
        )

        for (block in infestedBlocks) {
            gen.copyModel(block.hostBlock, block)
        }

        for ((idx, block) in DnDBlocks.WOOL_CARPET_PLATE.withIndex()) {
            gen.carpetPlate(block, VanillaColorCollections.WOOL.list[idx])
        }
        gen.carpetPlate(DnDBlocks.MOSS_CARPET_PLATE, Blocks.MOSS_BLOCK)
        gen.tintedCarpetPlate(DnDBlocks.OVERGROWTH_CARPET_PLATE, DnDBlocks.OVERGROWTH_BLOCK)

        gen.fence(DnDBlocks.BRICK_FENCE, Blocks.BRICKS)

        gen.stairs(DnDBlocks.SNOW_SET.stairs, Blocks.SNOW)
        gen.slab(DnDBlocks.SNOW_SET.slab, Blocks.SNOW, Blocks.SNOW_BLOCK)
        gen.wall(DnDBlocks.SNOW_SET.wall, Blocks.SNOW)

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
        // endregion

        // Misc Blocks
        gen.redstoneLantern(DnDBlocks.REDSTONE_LANTERN)
        gen.denseCube(DnDBlocks.HEAVY_CUBE)

        gen.tintedPane(Blocks.TINTED_GLASS, DnDBlocks.TINTED_GLASS_PANE)
        gen.createBrushableBlock(DnDBlocks.SUSPICIOUS_RED_SAND)

        gen.createTrivialBlock(DnDBlocks.TINTED_SAND, TexturedModel.LEAVES)
        gen.createTrivialBlock(DnDBlocks.TINTED_SANDSTONE, TexturedModel.LEAVES)
        gen.createTrivialBlock(DnDBlocks.CHISELED_TINTED_SANDSTONE, TexturedModel.LEAVES)
        gen.createTrivialBlock(DnDBlocks.CUT_TINTED_SANDSTONE, TexturedModel.LEAVES)

        // Vanilla Overrides
        gen.addAxis(Blocks.MANGROVE_ROOTS)

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

}