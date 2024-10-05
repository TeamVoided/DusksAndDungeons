package org.teamvoided.dusk_autumn.init.blocks

import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks.*
import net.minecraft.block.FenceBlock
import net.minecraft.block.MapColor
import net.minecraft.block.PillarBlock
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.dusk_autumn.block.SixWayFacingBlock
import org.teamvoided.dusk_autumn.block.WarpedNetherWartBlock
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.util.*

object DnDNetherBrickBlocks {
    fun init() = Unit
    val NETHERRACK_STAIRS =
        DnDBlocks.register("netherrack_stairs", stairsOf(NETHERRACK).pickaxe())
    val NETHERRACK_SLAB = DnDBlocks.register("netherrack_slab", slabOf(NETHERRACK).pickaxe())
    val NETHERRACK_WALL = DnDBlocks.register("netherrack_wall", wallOf(NETHERRACK).pickaxe())
    val NETHER_BRICK_PILLAR = DnDBlocks.register("nether_brick_pillar", PillarBlock(copy(NETHER_BRICKS)).pickaxe())
    val POLISHED_NETHER_BRICKS = DnDBlocks.register("polished_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val POLISHED_NETHER_BRICK_STAIRS =
        DnDBlocks.register("polished_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val POLISHED_NETHER_BRICK_SLAB =
        DnDBlocks.register("polished_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val POLISHED_NETHER_BRICK_WALL =
        DnDBlocks.register("polished_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val MIXED_NETHER_BRICKS = DnDBlocks.register("mixed_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_MIXED_NETHER_BRICKS =
        DnDBlocks.register("cracked_mixed_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_NETHER_BRICK_STAIRS =
        DnDBlocks.register("mixed_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val MIXED_NETHER_BRICK_SLAB = DnDBlocks.register("mixed_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val MIXED_NETHER_BRICK_WALL = DnDBlocks.register("mixed_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val MIXED_NETHER_BRICK_FENCE =
        DnDBlocks.register("mixed_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_NETHER_BRICKS =
        DnDBlocks.register("chiseled_mixed_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_NETHER_BRICK_PILLAR =
        DnDBlocks.register("mixed_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_NETHER_BRICKS)).pickaxe())
    val CRACKED_RED_NETHER_BRICKS =
        DnDBlocks.register("cracked_red_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_FENCE =
        DnDBlocks.register("red_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_RED_NETHER_BRICKS =
        DnDBlocks.register("chiseled_red_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_PILLAR =
        DnDBlocks.register("red_nether_brick_pillar", PillarBlock(copy(RED_NETHER_BRICKS)).pickaxe())
    val POLISHED_RED_NETHER_BRICKS =
        DnDBlocks.register("polished_red_nether_bricks", Block(copy(RED_NETHER_BRICKS)).pickaxe())
    val POLISHED_RED_NETHER_BRICK_STAIRS =
        DnDBlocks.register("polished_red_nether_brick_stairs", stairsOf(RED_NETHER_BRICK_STAIRS).pickaxe())
    val POLISHED_RED_NETHER_BRICK_SLAB =
        DnDBlocks.register("polished_red_nether_brick_slab", slabOf(RED_NETHER_BRICK_SLAB).pickaxe())
    val POLISHED_RED_NETHER_BRICK_WALL =
        DnDBlocks.register("polished_red_nether_brick_wall", wallOf(RED_NETHER_BRICK_WALL).pickaxe())
    val MIXED_BLUE_NETHER_BRICKS = DnDBlocks.register("mixed_blue_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_MIXED_BLUE_NETHER_BRICKS =
        DnDBlocks.register("cracked_mixed_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_STAIRS =
        DnDBlocks.register("mixed_blue_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_SLAB =
        DnDBlocks.register("mixed_blue_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_WALL =
        DnDBlocks.register("mixed_blue_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_FENCE =
        DnDBlocks.register("mixed_blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_BLUE_NETHER_BRICKS =
        DnDBlocks.register("chiseled_mixed_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_PILLAR =
        DnDBlocks.register(
            "mixed_blue_nether_brick_pillar",
            SixWayFacingBlock(copy(MIXED_BLUE_NETHER_BRICKS)).pickaxe()
        )
    val BLUE_NETHER_BRICKS = DnDBlocks.register("blue_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_BLUE_NETHER_BRICKS =
        DnDBlocks.register("cracked_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_STAIRS =
        DnDBlocks.register("blue_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val BLUE_NETHER_BRICK_SLAB = DnDBlocks.register("blue_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val BLUE_NETHER_BRICK_WALL = DnDBlocks.register("blue_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val BLUE_NETHER_BRICK_FENCE =
        DnDBlocks.register("blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_BLUE_NETHER_BRICKS =
        DnDBlocks.register("chiseled_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_PILLAR =
        DnDBlocks.register("blue_nether_brick_pillar", PillarBlock(copy(BLUE_NETHER_BRICKS)).pickaxe())
    val POLISHED_BLUE_NETHER_BRICKS =
        DnDBlocks.register("polished_blue_nether_bricks", Block(copy(BLUE_NETHER_BRICKS)).pickaxe())
    val POLISHED_BLUE_NETHER_BRICK_STAIRS =
        DnDBlocks.register("polished_blue_nether_brick_stairs", stairsOf(BLUE_NETHER_BRICK_STAIRS).pickaxe())
    val POLISHED_BLUE_NETHER_BRICK_SLAB =
        DnDBlocks.register("polished_blue_nether_brick_slab", slabOf(BLUE_NETHER_BRICK_SLAB).pickaxe())
    val POLISHED_BLUE_NETHER_BRICK_WALL =
        DnDBlocks.register("polished_blue_nether_brick_wall", wallOf(BLUE_NETHER_BRICK_WALL).pickaxe())
    val MIXED_GRAY_NETHER_BRICKS = DnDBlocks.register("mixed_gray_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_MIXED_GRAY_NETHER_BRICKS =
        DnDBlocks.register("cracked_mixed_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_STAIRS =
        DnDBlocks.register("mixed_gray_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_SLAB =
        DnDBlocks.register("mixed_gray_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_WALL =
        DnDBlocks.register("mixed_gray_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_FENCE =
        DnDBlocks.register("mixed_gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_GRAY_NETHER_BRICKS =
        DnDBlocks.register("chiseled_mixed_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_PILLAR =
        DnDBlocks.register(
            "mixed_gray_nether_brick_pillar",
            SixWayFacingBlock(copy(MIXED_GRAY_NETHER_BRICKS)).pickaxe()
        )
    val GRAY_NETHER_BRICKS = DnDBlocks.register("gray_nether_bricks", Block(copy(NETHER_BRICKS)).pickaxe())
    val CRACKED_GRAY_NETHER_BRICKS =
        DnDBlocks.register("cracked_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_STAIRS =
        DnDBlocks.register("gray_nether_brick_stairs", stairsOf(NETHER_BRICK_STAIRS).pickaxe())
    val GRAY_NETHER_BRICK_SLAB = DnDBlocks.register("gray_nether_brick_slab", slabOf(NETHER_BRICK_SLAB).pickaxe())
    val GRAY_NETHER_BRICK_WALL = DnDBlocks.register("gray_nether_brick_wall", wallOf(NETHER_BRICK_WALL).pickaxe())
    val GRAY_NETHER_BRICK_FENCE =
        DnDBlocks.register("gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_GRAY_NETHER_BRICKS =
        DnDBlocks.register("chiseled_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_PILLAR =
        DnDBlocks.register("gray_nether_brick_pillar", PillarBlock(copy(GRAY_NETHER_BRICKS)).pickaxe())
    val POLISHED_GRAY_NETHER_BRICKS =
        DnDBlocks.register("polished_gray_nether_bricks", Block(copy(GRAY_NETHER_BRICKS)).pickaxe())
    val POLISHED_GRAY_NETHER_BRICK_STAIRS =
        DnDBlocks.register("polished_gray_nether_brick_stairs", stairsOf(GRAY_NETHER_BRICK_STAIRS).pickaxe())
    val POLISHED_GRAY_NETHER_BRICK_SLAB =
        DnDBlocks.register("polished_gray_nether_brick_slab", slabOf(GRAY_NETHER_BRICK_SLAB).pickaxe())
    val POLISHED_GRAY_NETHER_BRICK_WALL =
        DnDBlocks.register("polished_gray_nether_brick_wall", wallOf(GRAY_NETHER_BRICK_WALL).pickaxe())
}