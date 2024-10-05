package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks.*
import net.minecraft.block.CoralBlockBlock
import net.minecraft.block.IceBlock
import net.minecraft.block.MapColor
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.block.CelestalBellBlock
import org.teamvoided.dusk_autumn.block.CrytalClusterWithParticlesBlock
import org.teamvoided.dusk_autumn.block.DnDFamilies
import org.teamvoided.dusk_autumn.block.TallRedstoneCrystalBlock
import org.teamvoided.dusk_autumn.block.meltable.MeltableSlabBlock
import org.teamvoided.dusk_autumn.block.meltable.MeltableStairsBlock
import org.teamvoided.dusk_autumn.block.meltable.MeltableWallBlock
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags
import org.teamvoided.dusk_autumn.init.DnDItems.BlockItem
import org.teamvoided.dusk_autumn.init.blocks.DnDOverlayBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.util.*


@Suppress("LargeClass", "TooManyFunctions", "MemberVisibilityCanBePrivate", "unused")
object DnDBlocks {
    val BLOCKS = mutableSetOf<Block>()
    val CUTOUT_BLOCKS = mutableSetOf<Block>()
    val TRANSLUCENT_BLOCKS = mutableSetOf<Block>()
    val GRASS_TINT_BLOCKS = mutableSetOf<Block>()
    val FOLIAGE_TINT_BLOCKS = mutableSetOf<Block>()
    val FLAMMABLE_PLANKS = mutableSetOf<Block>()
    val FLAMMABLE_LOGS = mutableSetOf<Block>()
    val FLAMMABLE_LEAVES = mutableSetOf<Block>()
    val SWORDABLE = mutableSetOf<Block>()
    val PICKAXABLE = mutableSetOf<Block>()
    val AXABLE = mutableSetOf<Block>()
    val SHOVELABLE = mutableSetOf<Block>()
    val HOEABLE = mutableSetOf<Block>()

    val CELESTAL_BELL = register("celestal_bell", CelestalBellBlock(copy(BELL))).tellWitnessesThatIWasMurdered()

    val ICE_STAIRS = register("ice_stairs", MeltableStairsBlock(ICE.defaultState, copy(ICE)).translucent().pickaxe())
    val ICE_SLAB = register("ice_slab", MeltableSlabBlock(copy(ICE)).translucent().pickaxe())
    val ICE_WALL = register("ice_wall", MeltableWallBlock(copy(ICE)).translucent().pickaxe())
    val ICE_BRICKS = register("ice_bricks", IceBlock(copy(ICE)).translucent().pickaxe()).tellWitnessesThatIWasMurdered()
    val ICE_BRICK_STAIRS =
        register("ice_brick_stairs", MeltableStairsBlock(ICE.defaultState, copy(ICE)).translucent().pickaxe()).tellWitnessesThatIWasMurdered()
    val ICE_BRICK_SLAB = register("ice_brick_slab", MeltableSlabBlock(copy(ICE)).translucent().pickaxe()).tellWitnessesThatIWasMurdered()
    val ICE_BRICK_WALL = register("ice_brick_wall", MeltableWallBlock(copy(ICE)).translucent().pickaxe()).tellWitnessesThatIWasMurdered()

    val PACKED_ICE_STAIRS = register("packed_ice_stairs", stairsOf(PACKED_ICE).pickaxe())
    val PACKED_ICE_SLAB = register("packed_ice_slab", slabOf(PACKED_ICE).pickaxe())
    val PACKED_ICE_WALL = register("packed_ice_wall", wallOf(PACKED_ICE).pickaxe())
    val PACKED_ICE_BRICKS = register("packed_ice_bricks", Block(copy(PACKED_ICE)).pickaxe()).tellWitnessesThatIWasMurdered()
    val PACKED_ICE_BRICK_STAIRS = register("packed_ice_brick_stairs", stairsOf(PACKED_ICE).pickaxe()).tellWitnessesThatIWasMurdered()
    val PACKED_ICE_BRICK_SLAB = register("packed_ice_brick_slab", slabOf(PACKED_ICE).pickaxe()).tellWitnessesThatIWasMurdered()
    val PACKED_ICE_BRICK_WALL = register("packed_ice_brick_wall", wallOf(PACKED_ICE).pickaxe()).tellWitnessesThatIWasMurdered()

    val BLUE_ICE_STAIRS = register("blue_ice_stairs", stairsOf(BLUE_ICE).pickaxe())
    val BLUE_ICE_SLAB = register("blue_ice_slab", slabOf(BLUE_ICE).pickaxe())
    val BLUE_ICE_WALL = register("blue_ice_wall", wallOf(BLUE_ICE).pickaxe())
    val BLUE_ICE_BRICKS = register("blue_ice_bricks", Block(copy(BLUE_ICE)).pickaxe()).tellWitnessesThatIWasMurdered()
    val BLUE_ICE_BRICK_STAIRS = register("blue_ice_brick_stairs", stairsOf(BLUE_ICE).pickaxe()).tellWitnessesThatIWasMurdered()
    val BLUE_ICE_BRICK_SLAB = register("blue_ice_brick_slab", slabOf(BLUE_ICE).pickaxe()).tellWitnessesThatIWasMurdered()
    val BLUE_ICE_BRICK_WALL = register("blue_ice_brick_wall", wallOf(BLUE_ICE).pickaxe()).tellWitnessesThatIWasMurdered()

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

//    val CLUB_CORAL = register(
//        "club_coral", Block(
//            copy(TUBE_CORAL_BLOCK).mapColor(MapColor.CYAN)
//        )
//    )
//    val DEAD_CLUB_CORAL = register(
//        "dead_club_coral", Block(
//            copy(TUBE_CORAL_BLOCK).mapColor(MapColor.CYAN)
//        )
//    )
//    val DEAD_CLUB_CORAL_BLOCK = DeadCoralBlock(
//        "dead_club_coral_block", Block(
//            copy(DEAD_TUBE_CORAL_BLOCK)
//        )
//    )
//    val CLUB_CORAL_BLOCK = CoralBlockBlock(
//        DEAD_CLUB_CORAL_BLOCK,
//        "club_coral_block", Block(
//            copy(TUBE_CORAL_BLOCK).mapColor(MapColor.CYAN)
//        )
//    )
//    val CLUB_CORAL_FAN = register(
//        "club_coral_fan", Block(
//            copy(TUBE_CORAL_BLOCK).mapColor(MapColor.CYAN)
//        )
//    )
//    val DEAD_CLUB_CORAL_FAN = register(
//        "dead_club_coral_fan", Block(
//            copy(DEAD_TUBE_CORAL_BLOCK)
//        )
//    )
//    val CLUB_CORAL_WALL_FAN = register(
//        "club_coral_wall_fan", Block(
//            copy(TUBE_CORAL_BLOCK).mapColor(MapColor.CYAN)
//        )
//    )
//    val DEAD_CLUB_CORAL_WALL_FAN = register(
//        "dead_club_coral_wall_fan", Block(
//            copy(DEAD_TUBE_CORAL_BLOCK)
//        )
//    )

    fun init() {
        DnDFamilies.init()

        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_PLANKS, 5, 20)
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LOGS, 5, 5)
        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LEAVES, 30, 60)

        DnDWoodBlocks.init()
        DnDOverlayBlocks.init()
    }

    fun register(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        DnDItems.register(id, BlockItem(regBlock))
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCKS.add(regBlock)
        return regBlock
    }
}