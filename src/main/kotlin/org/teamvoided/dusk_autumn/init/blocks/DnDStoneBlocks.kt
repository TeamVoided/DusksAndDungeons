package org.teamvoided.dusk_autumn.init.blocks

import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks.*
import net.minecraft.block.MapColor
import net.minecraft.block.PillarBlock
import org.teamvoided.dusk_autumn.block.BunnyGraveBlock
import org.teamvoided.dusk_autumn.block.GravestoneBlock
import org.teamvoided.dusk_autumn.block.HauntedGravestoneBlock
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.item.DnDFoodComponents
import org.teamvoided.dusk_autumn.util.*

object DnDStoneBlocks {
    fun init() = Unit

    val GRAVESTONE = DnDBlocks.register(
        "gravestone", GravestoneBlock(
            gravestoneShape,
            centerGravestoneShape,
            copy(CHISELED_STONE_BRICKS).solid()
        ).pickaxe()
    ).shh()
    val SMALL_GRAVESTONE = DnDBlocks.register(
        "small_gravestone", GravestoneBlock(
            smallGravestoneShape,
            centerSmallGravestoneShape,
            copy(GRAVESTONE)
        ).pickaxe()
    ).shh()
    val DEEPSLATE_GRAVESTONE = DnDBlocks.register(
        "deepslate_gravestone", GravestoneBlock(
            gravestoneShape,
            centerGravestoneShape,
            copy(CHISELED_DEEPSLATE).solid()
        ).pickaxe().shh()
    )
    val SMALL_DEEPSLATE_GRAVESTONE = DnDBlocks.register(
        "small_deepslate_gravestone", GravestoneBlock(
            smallGravestoneShape,
            centerSmallGravestoneShape,
            copy(DEEPSLATE_GRAVESTONE)
        ).pickaxe().shh()
    )
    val TUFF_GRAVESTONE = DnDBlocks.register(
        "tuff_gravestone", GravestoneBlock(
            gravestoneShape,
            centerGravestoneShape,
            copy(CHISELED_TUFF_BRICKS).solid()
        ).pickaxe().shh()
    )
    val SMALL_TUFF_GRAVESTONE = DnDBlocks.register(
        "small_tuff_gravestone", GravestoneBlock(
            smallGravestoneShape,
            centerSmallGravestoneShape,
            copy(TUFF_GRAVESTONE)
        ).pickaxe().shh()
    )
    val BLACKSTONE_GRAVESTONE = DnDBlocks.register(
        "blackstone_gravestone", GravestoneBlock(
            gravestoneShape,
            centerGravestoneShape,
            copy(CHISELED_POLISHED_BLACKSTONE).solid()
        ).pickaxe().shh()
    )
    val SMALL_BLACKSTONE_GRAVESTONE = DnDBlocks.register(
        "small_blackstone_gravestone", GravestoneBlock(
            smallGravestoneShape,
            centerSmallGravestoneShape,
            copy(BLACKSTONE_GRAVESTONE)
        ).pickaxe().shh()
    )

    val HAUNTED_GRAVESTONE = DnDBlocks.register(
        "haunted_gravestone", HauntedGravestoneBlock(
            gravestoneShape,
            centerGravestoneShape,
            copy(GRAVESTONE)
        ).pickaxe().shh()
    )
    val SMALL_HAUNTED_GRAVESTONE = DnDBlocks.register(
        "small_haunted_gravestone", HauntedGravestoneBlock(
            smallGravestoneShape,
            centerSmallGravestoneShape,
            copy(GRAVESTONE)
        ).pickaxe().shh()
    )
    val HAUNTED_DEEPSLATE_GRAVESTONE = DnDBlocks.register(
        "haunted_deepslate_gravestone", HauntedGravestoneBlock(
            gravestoneShape,
            centerGravestoneShape,
            copy(DEEPSLATE_GRAVESTONE)
        ).pickaxe().shh()
    )
    val SMALL_HAUNTED_DEEPSLATE_GRAVESTONE = DnDBlocks.register(
        "small_haunted_deepslate_gravestone", HauntedGravestoneBlock(
            smallGravestoneShape,
            centerSmallGravestoneShape,
            copy(DEEPSLATE_GRAVESTONE)
        ).pickaxe().shh()
    )
    val HAUNTED_TUFF_GRAVESTONE = DnDBlocks.register(
        "haunted_tuff_gravestone", HauntedGravestoneBlock(
            gravestoneShape,
            centerGravestoneShape,
            copy(TUFF_GRAVESTONE)
        ).pickaxe().shh()
    )
    val SMALL_HAUNTED_TUFF_GRAVESTONE = DnDBlocks.register(
        "small_haunted_tuff_gravestone", HauntedGravestoneBlock(
            smallGravestoneShape,
            centerSmallGravestoneShape,
            copy(TUFF_GRAVESTONE)
        ).pickaxe().shh()
    )
    val HAUNTED_BLACKSTONE_GRAVESTONE = DnDBlocks.register(
        "haunted_blackstone_gravestone", HauntedGravestoneBlock(
            gravestoneShape,
            centerGravestoneShape,
            copy(BLACKSTONE_GRAVESTONE)
        ).pickaxe().shh()
    )
    val SMALL_HAUNTED_BLACKSTONE_GRAVESTONE = DnDBlocks.register(
        "small_haunted_blackstone_gravestone", HauntedGravestoneBlock(
            smallGravestoneShape,
            centerSmallGravestoneShape,
            copy(BLACKSTONE_GRAVESTONE)
        ).pickaxe().shh()
    )

    val HEADSTONE =
        DnDBlocks.register(
            "headstone", GravestoneBlock(
                headstoneShape,
                centerHeadstoneShape,
                copy(CHAIN).sounds(bigChainSound)
            ).cutout().pickaxe().shh()
        )
    val BUNNY_GRAVE = DnDBlocks.register("bunny_grave", BunnyGraveBlock(copy(STONE_BRICK_WALL)).pickaxe()).shh()

    val STONE_PILLAR = DnDBlocks.register("stone_pillar", PillarBlock(copy(CHISELED_STONE_BRICKS)))
    val DEEPSLATE_PILLAR = DnDBlocks.register("deepslate_pillar", PillarBlock(copy(POLISHED_DEEPSLATE)))
    val POLISHED_STONE = DnDBlocks.register("polished_stone", Block(copy(SMOOTH_STONE)).pickaxe())
    val POLISHED_STONE_STAIRS = DnDBlocks.register("polished_stone_stairs", stairsOf(POLISHED_STONE).pickaxe())
    val POLISHED_STONE_SLAB = DnDBlocks.register("polished_stone_slab", slabOf(POLISHED_STONE).pickaxe())
    val POLISHED_STONE_WALL = DnDBlocks.register("polished_stone_wall", wallOf(POLISHED_STONE).pickaxe())
    val MOSSY_POLISHED_STONE = DnDBlocks.register("mossy_polished_stone", Block(copy(POLISHED_STONE)).pickaxe())
    val MOSSY_POLISHED_STONE_STAIRS =
        DnDBlocks.register("mossy_polished_stone_stairs", stairsOf(MOSSY_POLISHED_STONE).pickaxe())
    val MOSSY_POLISHED_STONE_SLAB =
        DnDBlocks.register("mossy_polished_stone_slab", slabOf(MOSSY_POLISHED_STONE).pickaxe())
    val MOSSY_POLISHED_STONE_WALL =
        DnDBlocks.register("mossy_polished_stone_wall", wallOf(MOSSY_POLISHED_STONE).pickaxe())
    val OVERGROWN_POLISHED_STONE =
        DnDBlocks.register("overgrown_polished_stone", Block(copy(MOSSY_POLISHED_STONE)).cutout().grass().pickaxe())
    val OVERGROWN_POLISHED_STONE_STAIRS =
        DnDBlocks.register(
            "overgrown_polished_stone_stairs",
            stairsOf(OVERGROWN_POLISHED_STONE).cutout().grass().pickaxe()
        )
    val OVERGROWN_POLISHED_STONE_SLAB =
        DnDBlocks.register("overgrown_polished_stone_slab", slabOf(OVERGROWN_POLISHED_STONE).cutout().grass().pickaxe())
    val OVERGROWN_POLISHED_STONE_WALL =
        DnDBlocks.register("overgrown_polished_stone_wall", wallOf(OVERGROWN_POLISHED_STONE).cutout().grass().pickaxe())
    val OVERGROWN_COBBLESTONE =
        DnDBlocks.register("overgrown_cobblestone", Block(copy(MOSSY_COBBLESTONE)).cutout().grass().pickaxe())
    val OVERGROWN_COBBLESTONE_STAIRS =
        DnDBlocks.register("overgrown_cobblestone_stairs", stairsOf(OVERGROWN_COBBLESTONE).cutout().grass().pickaxe())
    val OVERGROWN_COBBLESTONE_SLAB =
        DnDBlocks.register("overgrown_cobblestone_slab", slabOf(MOSSY_COBBLESTONE_SLAB).cutout().grass().pickaxe())
    val OVERGROWN_COBBLESTONE_WALL =
        DnDBlocks.register("overgrown_cobblestone_wall", wallOf(MOSSY_COBBLESTONE_WALL).cutout().grass().pickaxe())
    val OVERGROWN_STONE_BRICKS =
        DnDBlocks.register("overgrown_stone_bricks", Block(copy(MOSSY_STONE_BRICKS)).cutout().grass().pickaxe())
    val OVERGROWN_STONE_BRICK_STAIRS =
        DnDBlocks.register("overgrown_stone_brick_stairs", stairsOf(MOSSY_STONE_BRICKS).cutout().grass().pickaxe())
    val OVERGROWN_STONE_BRICK_SLAB =
        DnDBlocks.register("overgrown_stone_brick_slab", slabOf(MOSSY_STONE_BRICK_SLAB).cutout().grass().pickaxe())
    val OVERGROWN_STONE_BRICK_WALL =
        DnDBlocks.register("overgrown_stone_brick_wall", wallOf(MOSSY_STONE_BRICK_WALL).cutout().grass().pickaxe())
    val SNOWY_STONE_BRICKS =
        DnDBlocks.register("snowy_stone_bricks", Block(copy(STONE_BRICKS)).pickaxe()).tellWitnessesThatIWasMurdered()
    val SNOWY_STONE_BRICK_STAIRS =
        DnDBlocks.register("snowy_stone_brick_stairs", stairsOf(STONE_BRICKS).pickaxe()).tellWitnessesThatIWasMurdered()
    val SNOWY_STONE_BRICK_SLAB =
        DnDBlocks.register("snowy_stone_brick_slab", slabOf(STONE_BRICK_SLAB).pickaxe()).tellWitnessesThatIWasMurdered()
    val SNOWY_STONE_BRICK_WALL =
        DnDBlocks.register("snowy_stone_brick_wall", wallOf(STONE_BRICK_WALL).pickaxe()).tellWitnessesThatIWasMurdered()
}