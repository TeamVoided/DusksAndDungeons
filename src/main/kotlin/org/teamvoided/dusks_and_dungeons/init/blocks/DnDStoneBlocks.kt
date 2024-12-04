package org.teamvoided.dusks_and_dungeons.init.blocks

import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks.*
import net.minecraft.block.PillarBlock
import org.teamvoided.dusks_and_dungeons.block.BunnyGraveBlock
import org.teamvoided.dusks_and_dungeons.block.GravestoneBlock
import org.teamvoided.dusks_and_dungeons.block.HauntedGravestoneBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDBigBlocks.BIG_CHAIN
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.dusks_and_dungeons.util.shh
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered

object DnDStoneBlocks {
    fun init() = Unit

    val GRAVESTONE = registerGravestone("gravestone", CHISELED_STONE_BRICKS)
    val SMALL_GRAVESTONE = registerSmallGravestone("small_gravestone", GRAVESTONE)
    val DEEPSLATE_GRAVESTONE = registerGravestone("deepslate_gravestone", CHISELED_DEEPSLATE)
    val SMALL_DEEPSLATE_GRAVESTONE = registerSmallGravestone("small_deepslate_gravestone", DEEPSLATE_GRAVESTONE)
    val TUFF_GRAVESTONE = registerGravestone("tuff_gravestone", CHISELED_TUFF_BRICKS)
    val SMALL_TUFF_GRAVESTONE = registerSmallGravestone("small_tuff_gravestone", TUFF_GRAVESTONE)
    val BLACKSTONE_GRAVESTONE = registerGravestone("blackstone_gravestone", CHISELED_POLISHED_BLACKSTONE)
    val SMALL_BLACKSTONE_GRAVESTONE = registerSmallGravestone("small_blackstone_gravestone", BLACKSTONE_GRAVESTONE)
    val HEADSTONE = DnDBlocks.register(
        "headstone", GravestoneBlock(headstoneShape, centerHeadstoneShape, copy(BIG_CHAIN)).cutout().pickaxe()
    )

    val STONE_PILLAR = DnDBlocks.register("stone_pillar", PillarBlock(copy(CHISELED_STONE_BRICKS)))
    val DEEPSLATE_PILLAR = DnDBlocks.register("deepslate_pillar", PillarBlock(copy(POLISHED_DEEPSLATE)))

    val POLISHED_STONE = DnDBlocks.registerSet("polished_stone", copy(SMOOTH_STONE)).pickaxe()
    val MOSSY_POLISHED_STONE = DnDBlocks.registerSet("mossy_polished_stone", copy(POLISHED_STONE)).pickaxe()
    val OVERGROWN_POLISHED_STONE = DnDBlocks.registerSet("overgrown_polished_stone", copy(MOSSY_POLISHED_STONE))
        .cutout().grass().pickaxe()
    val OVERGROWN_COBBLESTONE = DnDBlocks.registerSet("overgrown_cobblestone", copy(MOSSY_COBBLESTONE))
        .cutout().grass().pickaxe()
    val OVERGROWN_STONE_BRICKS = DnDBlocks.registerSet("overgrown_stone_brick", copy(MOSSY_STONE_BRICKS), "s")
        .cutout().grass().pickaxe()

    // ☢ Experimental ☢
    // Haunted graves
    val HAUNTED_GRAVESTONE = registerHGravestone("haunted_gravestone", GRAVESTONE)
    val SMALL_HAUNTED_GRAVESTONE = registerSmallHGravestone("small_haunted_gravestone", GRAVESTONE)
    val HAUNTED_DEEPSLATE_GRAVESTONE = registerHGravestone("haunted_deepslate_gravestone", DEEPSLATE_GRAVESTONE)
    val SMALL_HAUNTED_DEEPSLATE_GRAVESTONE =
        registerSmallHGravestone("small_haunted_deepslate_gravestone", DEEPSLATE_GRAVESTONE)
    val HAUNTED_TUFF_GRAVESTONE = registerHGravestone("haunted_tuff_gravestone", TUFF_GRAVESTONE)
    val SMALL_HAUNTED_TUFF_GRAVESTONE = registerSmallHGravestone("small_haunted_tuff_gravestone", TUFF_GRAVESTONE)
    val HAUNTED_BLACKSTONE_GRAVESTONE = registerHGravestone("haunted_blackstone_gravestone", BLACKSTONE_GRAVESTONE)
    val SMALL_HAUNTED_BLACKSTONE_GRAVESTONE =
        registerSmallHGravestone("small_haunted_blackstone_gravestone", BLACKSTONE_GRAVESTONE)

    val BUNNY_GRAVE = DnDBlocks.register("bunny_grave", BunnyGraveBlock(copy(STONE_BRICK_WALL)).pickaxe()).shh()

    val SNOWY_STONE_BRICKS = DnDBlocks.registerSet("snowy_stone_brick", copy(STONE_BRICKS), "s")
        .pickaxe().tellWitnessesThatIWasMurdered()

    // misc
    internal fun registerGravestone(name: String, block: Block) =
        DnDBlocks.register(name, GravestoneBlock(gravestoneShape, centerGravestoneShape, copy(block).solid())).pickaxe()

    internal fun registerSmallGravestone(name: String, block: Block) =
        DnDBlocks.register(name, GravestoneBlock(smallGravestoneShape, centerSmallGravestoneShape, copy(block)))
            .pickaxe()

    internal fun registerHGravestone(name: String, block: Block) =
        DnDBlocks.register(name, HauntedGravestoneBlock(gravestoneShape, centerGravestoneShape, copy(block).solid()))
            .pickaxe().shh().tellWitnessesThatIWasMurdered()

    internal fun registerSmallHGravestone(name: String, block: Block) =
        DnDBlocks.register(name, HauntedGravestoneBlock(smallGravestoneShape, centerSmallGravestoneShape, copy(block)))
            .pickaxe().shh().tellWitnessesThatIWasMurdered()
}