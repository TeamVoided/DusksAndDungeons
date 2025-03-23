package org.teamvoided.dusks_and_dungeons.init.misc

import net.minecraft.block.AbstractBlock.OffsetType
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks
import net.minecraft.block.Blocks.*
import net.minecraft.block.MapColor
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.dusks_and_dungeons.util.block.cornSyrupBlockSound
import org.teamvoided.dusks_and_dungeons.util.block.luminance
import org.teamvoided.dusks_and_dungeons.util.block.rootBlockSound

//(ender) This file should not reference DnDBlocks
object DnDBlockSettings {
    val ICE: Settings = copy(Blocks.ICE)

    val WARPED_WART: Settings = Settings.create()
        .mapColor(MapColor.WARPED_STEM)
        .noCollision()
        .ticksRandomly()
        .sounds(BlockSoundGroup.NETHER_WART)
        .pistonBehavior(PistonBehavior.DESTROY)
    val CORN_SYRUP: Settings = copy(HONEY_BLOCK)
        .mapColor(MapColor.PODZOL)
        .sounds(cornSyrupBlockSound)
    val ROOT_BLOCK: Settings = Settings.create()
        .mapColor(MapColor.PODZOL)
        .instrument(NoteBlockInstrument.BASS)
        .strength(0.7f)
        .nonOpaque()
        .suffocates(Blocks::nonSolid)
        .blockVision(Blocks::nonSolid)
        .lavaIgnitable()
        .sounds(rootBlockSound)
    val WILD_WHEAT: Settings = Settings.create()
        .mapColor(MapColor.PLANT)
        .noCollision()
        .breakInstantly()
        .sounds(BlockSoundGroup.CROP)
        .offsetType(OffsetType.XZ)
        .pistonBehavior(PistonBehavior.DESTROY)
    val GOLDEN_BEETROOT: Settings = Settings.create()
        .mapColor(MapColor.GOLD)
        .noCollision()
        .ticksRandomly()
        .breakInstantly()
        .sounds(BlockSoundGroup.CROP)
        .pistonBehavior(PistonBehavior.DESTROY)

    // Wood Types
    val CASCADE_SAPLING: Settings = Settings.create()
        .mapColor(MapColor.RED)
        .noCollision()
        .ticksRandomly()
        .breakInstantly()
        .sounds(BlockSoundGroup.CHERRY_SAPLING)
        .pistonBehavior(PistonBehavior.DESTROY)
    val CASCADE_LEAVES: Settings = Settings.create()
        .strength(0.2f)
        .ticksRandomly()
        .nonOpaque()
        .allowsSpawning(Blocks::allowOcelotsAndParrots)
        .suffocates(Blocks::nonSolid)
        .blockVision(Blocks::nonSolid)
        .lavaIgnitable()
        .pistonBehavior(PistonBehavior.DESTROY)
        .solidBlock(Blocks::nonSolid)
        .sounds(BlockSoundGroup.AZALEA_LEAVES)
        .mapColor(MapColor.RED)
    val CASCADE_WOOD: Settings = Settings.create()
        .mapColor(MapColor.BROWN)
        .instrument(NoteBlockInstrument.BASS)
        .strength(2.0f)
        .sounds(BlockSoundGroup.CHERRY_WOOD)
        .lavaIgnitable()
    val CASCADE_PLANKS: Settings = Settings.create()
        .mapColor(MapColor.BLUE)
        .instrument(NoteBlockInstrument.BASS)
        .strength(2.0F, 3.0F)
        .sounds(BlockSoundGroup.CHERRY_WOOD)
        .lavaIgnitable()
    val BLUE_DOOR: Settings =  Settings.create()
        .mapColor(MapColor.BLUE)
        .instrument(NoteBlockInstrument.BASS)
        .strength(3.0f)
        .nonOpaque()
        .lavaIgnitable()
        .pistonBehavior(PistonBehavior.DESTROY)
    val GOLDEN_MUSHROOM_BLOCK: Settings = copy(BROWN_MUSHROOM_BLOCK).mapColor(MapColor.GOLD)
    val GOLDEN_MUSHROOM: Settings = Settings.create()
        .mapColor(MapColor.GOLD)
        .noCollision()
        .ticksRandomly()
        .breakInstantly()
        .sounds(BlockSoundGroup.GRASS)
        .luminance(11)
        .offsetType(OffsetType.XYZ)
        .postProcess(Blocks::solid)
        .pistonBehavior(PistonBehavior.DESTROY)

    // Fun!
    fun petals(color: MapColor): Settings = copy(PINK_PETALS).mapColor(color)
    fun vivions(color: MapColor): Settings = petals(color).sounds(BlockSoundGroup.NETHER_SPROUTS)
    fun corn(): Settings = Settings.create()
        .mapColor(MapColor.PLANT)
        .noCollision()
        .breakInstantly()
        .sounds(BlockSoundGroup.CROP)
        .pistonBehavior(PistonBehavior.DESTROY)

    fun moonbery(): Settings = Settings.create()
        .mapColor(MapColor.PURPLE)
        .noCollision()
        .strength(0.2f)
        .sounds(BlockSoundGroup.CAVE_VINES)
        .lavaIgnitable()
        .pistonBehavior(PistonBehavior.DESTROY)

    fun smallPumpkin(color: MapColor): Settings = Settings.create()
        .mapColor(color)
        .strength(1.0f)
        .sounds(BlockSoundGroup.WOOD)
        .pistonBehavior(PistonBehavior.DESTROY)
}
