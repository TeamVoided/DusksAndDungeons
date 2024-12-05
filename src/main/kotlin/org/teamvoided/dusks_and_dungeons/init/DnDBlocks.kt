package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks.*
import net.minecraft.block.FenceBlock
import net.minecraft.block.IceBlock
import net.minecraft.block.PillarBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDBigBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDBigBlocks.BIG_CHAIN
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDFloraBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDOverlayBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks
import org.teamvoided.dusks_and_dungeons.util.block.*
import org.teamvoided.dusks_and_dungeons.util.shh
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.voidlib.consortium.block.AbstractBlockSet
import org.teamvoided.voidlib.consortium.block.createBlockSet
import org.teamvoided.voidlib.consortium.block.createHeadlessSet
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockSettings as Set


@Suppress("LargeClass", "TooManyFunctions", "MemberVisibilityCanBePrivate", "unused")
object DnDBlocks {
    val BLOCKS = mutableSetOf<Block>()
    val SETS = mutableSetOf<AbstractBlockSet>()

    val EVIL_BLOCKS = mutableSetOf<Block>()

    /*
        🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 --- Rock & Stone --- 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨 🪨
     */
    val STONE_PILLAR = register("stone_pillar", PillarBlock(copy(CHISELED_STONE_BRICKS)))
    val DEEPSLATE_PILLAR = register("deepslate_pillar", PillarBlock(copy(POLISHED_DEEPSLATE)))

    val POLISHED_STONE = registerSet("polished_stone", copy(SMOOTH_STONE)).pickaxe()
    val MOSSY_POLISHED_STONE = registerSet("mossy_polished_stone", copy(POLISHED_STONE)).pickaxe()

    val OVERGROWN_POLISHED_STONE = registerSet("overgrown_polished_stone", copy(MOSSY_POLISHED_STONE)).overgrown()
    val OVERGROWN_COBBLESTONE = registerSet("overgrown_cobblestone", copy(MOSSY_COBBLESTONE)).overgrown()
    val OVERGROWN_STONE_BRICKS = registerSet("overgrown_stone_brick", copy(MOSSY_STONE_BRICKS), "s").overgrown()

    val STONE_GRAVESTONE = registerGravestone("stone_gravestone", CHISELED_STONE_BRICKS)
    val SMALL_STONE_GRAVESTONE = registerSmallGravestone("small_stone_gravestone", STONE_GRAVESTONE)
    val DEEPSLATE_GRAVESTONE = registerGravestone("deepslate_gravestone", CHISELED_DEEPSLATE)
    val SMALL_DEEPSLATE_GRAVESTONE = registerSmallGravestone("small_deepslate_gravestone", DEEPSLATE_GRAVESTONE)
    val TUFF_GRAVESTONE = registerGravestone("tuff_gravestone", CHISELED_TUFF_BRICKS)
    val SMALL_TUFF_GRAVESTONE = registerSmallGravestone("small_tuff_gravestone", TUFF_GRAVESTONE)
    val BLACKSTONE_GRAVESTONE = registerGravestone("blackstone_gravestone", CHISELED_POLISHED_BLACKSTONE)
    val SMALL_BLACKSTONE_GRAVESTONE = registerSmallGravestone("small_blackstone_gravestone", BLACKSTONE_GRAVESTONE)
    val HEADSTONE =
        register("headstone", GravestoneBlock(headstoneShape, centerHeadstoneShape, copy(BIG_CHAIN)).cutout().pickaxe())

    /*
       ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ --- ICE AGE --- ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄
     */
    val ICE_SET = register(createHeadlessSet("ice", ICE).noStoneCutting().meltable().buildHeadless()).translucent().pickaxe()
    val PACKED_ICE_SET = registerHeadlessSet("packed_ice", PACKED_ICE).pickaxe()
    val BLUE_ICE_SET = registerHeadlessSet("blue_ice", BLUE_ICE).pickaxe()

    /*
    🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥 🔥
     */
    val NETHERRACK_SET = registerHeadlessSet("netherrack", NETHERRACK).pickaxe()

    val NETHER_BRICK_PILLAR = register("nether_brick_pillar", PillarBlock(copy(NETHER_BRICKS)).pickaxe())
    val POLISHED_NETHER_BRICKS = registerSet("polished_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()

    // Red Nether Bricks
    val POLISHED_RED_NETHER_BRICKS =
        registerSet("polished_red_nether_brick", copy(RED_NETHER_BRICKS), "s").pickaxe()

    val CRACKED_RED_NETHER_BRICKS =
        register("cracked_red_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_FENCE =
        register("red_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_RED_NETHER_BRICKS =
        register("chiseled_red_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val RED_NETHER_BRICK_PILLAR =
        register("red_nether_brick_pillar", PillarBlock(copy(RED_NETHER_BRICKS)).pickaxe())

    val MIXED_RED_NETHER_BRICKS = registerSet("mixed_red_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_RED_NETHER_BRICKS =
        register("cracked_mixed_red_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_RED_NETHER_BRICK_FENCE =
        register("mixed_red_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_RED_NETHER_BRICKS =
        register("chiseled_mixed_red_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_RED_NETHER_BRICK_PILLAR =
        register("mixed_red_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_RED_NETHER_BRICKS)).pickaxe())

    // Blue Nether Bricks
    val BLUE_NETHER_BRICKS = registerSet("blue_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_BLUE_NETHER_BRICKS =
        register("cracked_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_FENCE =
        register("blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_BLUE_NETHER_BRICKS =
        register("chiseled_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val BLUE_NETHER_BRICK_PILLAR =
        register("blue_nether_brick_pillar", PillarBlock(copy(BLUE_NETHER_BRICKS)).pickaxe())

    val POLISHED_BLUE_NETHER_BRICKS = registerSet("polished_blue_nether_brick", copy(BLUE_NETHER_BRICKS), "s").pickaxe()

    val MIXED_BLUE_NETHER_BRICKS = registerSet("mixed_blue_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_BLUE_NETHER_BRICKS =
        register("cracked_mixed_blue_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_FENCE =
        register("mixed_blue_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_BLUE_NETHER_BRICKS =
        register("chiseled_mixed_blue_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_BLUE_NETHER_BRICK_PILLAR = register(
        "mixed_blue_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_BLUE_NETHER_BRICKS)).pickaxe()
    )

    // Gray Nether Bricks
    val GRAY_NETHER_BRICKS = registerSet("gray_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_GRAY_NETHER_BRICKS =
        register("cracked_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_FENCE =
        register("gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_GRAY_NETHER_BRICKS =
        register("chiseled_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val GRAY_NETHER_BRICK_PILLAR =
        register("gray_nether_brick_pillar", PillarBlock(copy(GRAY_NETHER_BRICKS)).pickaxe())

    val POLISHED_GRAY_NETHER_BRICKS = registerSet("polished_gray_nether_brick", copy(GRAY_NETHER_BRICKS), "s").pickaxe()

    val MIXED_GRAY_NETHER_BRICKS = registerSet("mixed_gray_nether_brick", copy(NETHER_BRICKS), "s").pickaxe()
    val CRACKED_MIXED_GRAY_NETHER_BRICKS =
        register("cracked_mixed_gray_nether_bricks", Block(copy(CRACKED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_FENCE =
        register("mixed_gray_nether_brick_fence", FenceBlock(copy(NETHER_BRICK_FENCE)).pickaxe())
    val CHISELED_MIXED_GRAY_NETHER_BRICKS =
        register("chiseled_mixed_gray_nether_bricks", Block(copy(CHISELED_NETHER_BRICKS)).pickaxe())
    val MIXED_GRAY_NETHER_BRICK_PILLAR = register(
        "mixed_gray_nether_brick_pillar", SixWayFacingBlock(copy(MIXED_GRAY_NETHER_BRICKS)).pickaxe()
    )


    // ☢ Experimental ☢

    val BUNNY_GRAVE = register("bunny_grave", BunnyGraveBlock(copy(STONE_BRICK_WALL)).pickaxe()).shh()
        .tellWitnessesThatIWasMurdered()

    // Haunted graves
    val HAUNTED_GRAVESTONE = registerHGravestone("haunted_gravestone", STONE_GRAVESTONE)
    val SMALL_HAUNTED_GRAVESTONE = registerSmallHGravestone("small_haunted_gravestone", STONE_GRAVESTONE)
    val HAUNTED_DEEPSLATE_GRAVESTONE = registerHGravestone("haunted_deepslate_gravestone", DEEPSLATE_GRAVESTONE)
    val SMALL_HAUNTED_DEEPSLATE_GRAVESTONE =
        registerSmallHGravestone("small_haunted_deepslate_gravestone", DEEPSLATE_GRAVESTONE)
    val HAUNTED_TUFF_GRAVESTONE = registerHGravestone("haunted_tuff_gravestone", TUFF_GRAVESTONE)
    val SMALL_HAUNTED_TUFF_GRAVESTONE = registerSmallHGravestone("small_haunted_tuff_gravestone", TUFF_GRAVESTONE)
    val HAUNTED_BLACKSTONE_GRAVESTONE = registerHGravestone("haunted_blackstone_gravestone", BLACKSTONE_GRAVESTONE)
    val SMALL_HAUNTED_BLACKSTONE_GRAVESTONE =
        registerSmallHGravestone("small_haunted_blackstone_gravestone", BLACKSTONE_GRAVESTONE)

    val SNOWY_STONE_BRICKS = registerSet("snowy_stone_brick", copy(STONE_BRICKS), "s").pickaxe()
        .tellWitnessesThatIWasMurdered()

    val ICE_BRICKS = register(
        createBlockSet("ice_brick", Set.ICE).s().noStoneCutting().parent(::IceBlock).meltable().build()
    ).translucent().pickaxe().tellWitnessesThatIWasMurdered()
    val PACKED_ICE_BRICKS = registerSet("packed_ice_brick", copy(PACKED_ICE), "s").pickaxe()
        .tellWitnessesThatIWasMurdered()
    val BLUE_ICE_BRICKS = registerSet("blue_ice_brick", copy(BLUE_ICE), "s").pickaxe()
        .tellWitnessesThatIWasMurdered()

    val CELESTAL_BELL = register("celestal_bell", CelestalBellBlock(copy(BELL))).tellWitnessesThatIWasMurdered()

    val MOONCORE = register(
        "mooncore", CrytalClusterWithParticlesBlock(12.0f, 2.0f, Set.MOONCORE).cutout()
    ).tellWitnessesThatIWasMurdered()
    val TALL_REDSTONE_CRYSTAL = register(
        "tall_redstone_crystal", TallRedstoneCrystalBlock(Set.REDSTONE_CRYSTAL).cutout()
    ).tellWitnessesThatIWasMurdered()
    val POT_O_SCREAMS = register("pot_o_screams", PotOScreamsBlock(copy(DECORATED_POT))).shh()
    val CHEST_O_SOULS = register("chest_o_souls", ChestOSoulsBlock(copy(CHEST))).shh()

    val QUARTER_BLOCK_PILE = registerNoItem("quarter_block_pile", QuarterBlockPileBlock(Settings.create())).cutout()

    val MOLTEN_LAVASPONGE =
        register("molten_lavasponge", TransformingBlock(copy(BASALT), LAVA)).pickaxe().tellWitnessesThatIWasMurdered()
    val BRITTLE_LAVASPONGE =
        register("brittle_lavasponge", LavaSpongeBlock(copy(BASALT), 3, 32, MOLTEN_LAVASPONGE)).pickaxe()
            .tellWitnessesThatIWasMurdered()
    val GLOWING_LAVASPONGE =
        register("glowing_lavasponge", Block(copy(BASALT))).pickaxe().tellWitnessesThatIWasMurdered()
    val LAVASPONGE =
        register("lavasponge", LavaSpongeBlock(copy(BASALT), 6, 64, GLOWING_LAVASPONGE)).pickaxe()
            .tellWitnessesThatIWasMurdered()

    /*
        🌈 🌈 🌈 🌈 --- GAY BLOCK --- 🌈 🌈 🌈 🌈
    */
    val GAY_BLOCK = registerSet("gay_block", copy(BEACON))

    fun init() {
        DnDWoodTypes.init()

        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_PLANKS, 5, 20)
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LOGS, 5, 5)
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LEAVES, 30, 60)

        DnDBigBlocks.init()
        DnDFloraBlocks.init()
        DnDOverlayBlocks.init()
        DnDWoodBlocks.init()

    }

    fun register(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        DnDItems.register(id, BlockItem(regBlock, Item.Settings()))
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCKS.add(regBlock)
        return regBlock
    }
}
