package org.teamvoided.dusks_and_dungeons.init.blocks

import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks.*
import net.minecraft.block.FenceBlock
import net.minecraft.block.PillarBlock
import org.teamvoided.dusks_and_dungeons.block.LavaSpongeBlock
import org.teamvoided.dusks_and_dungeons.block.SixWayFacingBlock
import org.teamvoided.dusks_and_dungeons.block.TransformingBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.registerHeadlessSet
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.registerSet
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered

object DnDNetherBrickBlocks {
    fun init() = Unit
    val NETHERRACK_SET = registerHeadlessSet("netherrack", NETHERRACK).pickaxe()

    val NETHER_BRICK_PILLAR = DnDBlocks.register("nether_brick_pillar", PillarBlock(copy(NETHER_BRICKS)).pickaxe())
    val POLISHED_NETHER_BRICKS = registerSet("polished_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()

    // Red Nether Bricks
    val POLISHED_RED_NETHER_BRICKS =
        registerSet("polished_red_nether_brick", copy(RED_NETHER_BRICKS), "s").pickaxe()

    val CRACKED_RED_NETHER_BRICKS =
        DnDBlocks.register("cracked_red_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_FENCE =
        DnDBlocks.register("red_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_RED_NETHER_BRICKS =
        DnDBlocks.register("chiseled_red_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_PILLAR =
        DnDBlocks.register("red_nether_brick_pillar", PillarBlock(copy(RED_NETHER_BRICKS)).pickaxe())

    val MIXED_RED_NETHER_BRICKS = registerSet("mixed_red_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_RED_NETHER_BRICKS =
        DnDBlocks.register("cracked_mixed_red_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_RED_NETHER_BRICK_FENCE =
        DnDBlocks.register("mixed_red_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_RED_NETHER_BRICKS =
        DnDBlocks.register("chiseled_mixed_red_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_RED_NETHER_BRICK_PILLAR =
        DnDBlocks.register("mixed_red_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_RED_NETHER_BRICKS)).pickaxe())


    // Blue Nether Bricks
    val BLUE_NETHER_BRICKS = registerSet("blue_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_BLUE_NETHER_BRICKS =
        DnDBlocks.register("cracked_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_FENCE =
        DnDBlocks.register("blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_BLUE_NETHER_BRICKS =
        DnDBlocks.register("chiseled_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_PILLAR =
        DnDBlocks.register("blue_nether_brick_pillar", PillarBlock(copy(BLUE_NETHER_BRICKS)).pickaxe())

    val POLISHED_BLUE_NETHER_BRICKS = registerSet("polished_blue_nether_brick", copy(BLUE_NETHER_BRICKS), "s").pickaxe()

    val MIXED_BLUE_NETHER_BRICKS = registerSet("mixed_blue_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_BLUE_NETHER_BRICKS =
        DnDBlocks.register("cracked_mixed_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_FENCE =
        DnDBlocks.register("mixed_blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_BLUE_NETHER_BRICKS =
        DnDBlocks.register("chiseled_mixed_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_PILLAR = DnDBlocks.register(
        "mixed_blue_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_BLUE_NETHER_BRICKS)).pickaxe()
    )


    // Gray Nether Bricks
    val GRAY_NETHER_BRICKS = registerSet("gray_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_GRAY_NETHER_BRICKS =
        DnDBlocks.register("cracked_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_FENCE =
        DnDBlocks.register("gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_GRAY_NETHER_BRICKS =
        DnDBlocks.register("chiseled_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_PILLAR =
        DnDBlocks.register("gray_nether_brick_pillar", PillarBlock(copy(GRAY_NETHER_BRICKS)).pickaxe())

    val POLISHED_GRAY_NETHER_BRICKS = registerSet("polished_gray_nether_brick", copy(GRAY_NETHER_BRICKS), "s").pickaxe()

    val MIXED_GRAY_NETHER_BRICKS = registerSet("mixed_gray_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_GRAY_NETHER_BRICKS =
        DnDBlocks.register("cracked_mixed_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_FENCE =
        DnDBlocks.register("mixed_gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_GRAY_NETHER_BRICKS =
        DnDBlocks.register("chiseled_mixed_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_PILLAR = DnDBlocks.register(
        "mixed_gray_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_GRAY_NETHER_BRICKS)).pickaxe()
    )


    // ☢ Experimental ☢
    val MOLTEN_LAVASPONGE =
        DnDBlocks.register("molten_lavasponge", TransformingBlock(copy(BASALT), LAVA)).pickaxe()
            .tellWitnessesThatIWasMurdered()
    val BRITTLE_LAVASPONGE =
        DnDBlocks.register("brittle_lavasponge", LavaSpongeBlock(copy(BASALT), 3, 32, MOLTEN_LAVASPONGE)).pickaxe()
            .tellWitnessesThatIWasMurdered()
    val GLOWING_LAVASPONGE =
        DnDBlocks.register("glowing_lavasponge", Block(copy(BASALT))).pickaxe().tellWitnessesThatIWasMurdered()
    val LAVASPONGE =
        DnDBlocks.register("lavasponge", LavaSpongeBlock(copy(BASALT), 6, 64, GLOWING_LAVASPONGE)).pickaxe()
            .tellWitnessesThatIWasMurdered()
}
