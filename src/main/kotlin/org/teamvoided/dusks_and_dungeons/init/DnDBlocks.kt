package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.Blocks.*
import net.minecraft.block.IceBlock
import net.minecraft.block.MapColor
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.block.meltable.MeltableSlabBlock
import org.teamvoided.dusks_and_dungeons.block.meltable.MeltableStairsBlock
import org.teamvoided.dusks_and_dungeons.block.meltable.MeltableWallBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.blocks.*
import org.teamvoided.dusks_and_dungeons.init.misc.ICE
import org.teamvoided.dusks_and_dungeons.util.block.cutout
import org.teamvoided.dusks_and_dungeons.util.block.light
import org.teamvoided.dusks_and_dungeons.util.block.pickaxe
import org.teamvoided.dusks_and_dungeons.util.block.translucent
import org.teamvoided.dusks_and_dungeons.util.shh
import org.teamvoided.dusks_and_dungeons.util.tellWitnessesThatIWasMurdered
import org.teamvoided.voidlib.consortium.block.BlockSetBuilder
import org.teamvoided.voidlib.consortium.block.HeadlessBlockSet
import org.teamvoided.voidlib.consortium.block.createBlockSet
import org.teamvoided.voidlib.consortium.block.createHeadlessSet
import org.teamvoided.voidlib.helpers.item.EquipableBlockItem
import net.minecraft.block.Blocks.ICE as ICE_BLOCK


@Suppress("LargeClass", "TooManyFunctions", "MemberVisibilityCanBePrivate", "unused")
object DnDBlocks {
    val BLOCKS = mutableSetOf<Block>()
    val SETS = mutableListOf<HeadlessBlockSet>()

    val EVIL_BLOCKS = mutableSetOf<Block>()

    /*
       ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄  --- ICE AGE --- ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄
     */
    val ICE_SET = register(
        createHeadlessSet("ice", ICE_BLOCK).noStoneCutting().stairs(::MeltableStairsBlock)
            .slab(::MeltableSlabBlock).wall(::MeltableWallBlock)
    ).translucent().pickaxe()
    val PACKED_ICE_SET = registerHeadlessSet("packed_ice", PACKED_ICE).pickaxe()
    val BLUE_ICE_SET = registerHeadlessSet("blue_ice", BLUE_ICE).pickaxe()

    // ☢ Experimental ☢
    val ICE_BRICKS = register(
        createBlockSet("ice_brick", ICE).s().noStoneCutting().parent(::IceBlock)
            .stairs(::MeltableStairsBlock).slab(::MeltableSlabBlock).wall(::MeltableWallBlock)
    ).translucent().pickaxe().tellWitnessesThatIWasMurdered()
    val PACKED_ICE_BRICKS = register(createBlockSet("packed_ice_brick", copy(PACKED_ICE)).s())
        .pickaxe().tellWitnessesThatIWasMurdered()
    val BLUE_ICE_BRICKS = register(createBlockSet("blue_ice_brick", copy(BLUE_ICE)).s())
        .pickaxe().tellWitnessesThatIWasMurdered()

    val CELESTAL_BELL = register("celestal_bell", CelestalBellBlock(copy(BELL))).tellWitnessesThatIWasMurdered()

    val MOONCORE = register(
        "mooncore", CrytalClusterWithParticlesBlock(
            12.0f, 2.0f,
            Settings.create().mapColor(MapColor.LIGHT_BLUE).solid().nonOpaque().sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                .strength(1.5f).ticksRandomly().luminance(light(15))
                .pistonBehavior(PistonBehavior.DESTROY)
        ).cutout()
    ).tellWitnessesThatIWasMurdered()
    val TALL_REDSTONE_CRYSTAL = register(
        "tall_redstone_crystal", TallRedstoneCrystalBlock(
            Settings.create().mapColor(MapColor.RED).solid().nonOpaque().sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                .strength(1.5f).ticksRandomly().luminance(luminanceOf(9))
                .pistonBehavior(PistonBehavior.DESTROY)
        ).cutout()
    ).tellWitnessesThatIWasMurdered()
    val POT_O_SCREAMS = register("pot_o_screams", PotOScreamsBlock(copy(DECORATED_POT))).shh()
    val CHEST_O_SOULS = register("chest_o_souls", ChestOSoulsBlock(copy(CHEST))).shh()

    val QUARTER_BLOCK_PILE = registerNoItem(
        "quarter_block_pile", QuarterBlockPileBlock(
            Settings.create().mapColor(MapColor.NONE).nonOpaque().allowsSpawning(Blocks::nonSpawnable)
        )
    ).cutout()

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
        DnDNetherBrickBlocks.init()
        DnDOverlayBlocks.init()
        DnDStoneBlocks.init()
        DnDWoodBlocks.init()

    }

    fun register(builder: BlockSetBuilder): HeadlessBlockSet {
        val set = builder.build()
        SETS.add(set)
        set.register(::register)
        return set
    }
    fun registerSet(name: String, settings: Settings) = register(createBlockSet(name, settings))
    fun registerHeadlessSet(name: String, parent: Block) = register(createHeadlessSet(name, parent))

    fun register(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        DnDItems.register(id, BlockItem(regBlock, Item.Settings()))
        return regBlock
    }

    fun registerHeadEquipable(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        DnDItems.register(id, EquipableBlockItem(regBlock, Item.Settings()))
        return regBlock
    }

    fun registerEdible(id: String, foodComponent: FoodComponent, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        DnDItems.register(id, BlockItem(regBlock, Item.Settings().food(foodComponent)))
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCKS.add(regBlock)
        return regBlock
    }
}
