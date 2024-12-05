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

object DnDBlockSettings {
    val ICE = copy(Blocks.ICE)

    val WARPED_WART = Settings.create()
        .mapColor(MapColor.WARPED_STEM)
        .noCollision()
        .ticksRandomly()
        .sounds(BlockSoundGroup.NETHER_WART)
        .pistonBehavior(PistonBehavior.DESTROY)
    val CORN_SYRUP = copy(HONEY_BLOCK)
        .mapColor(MapColor.PODZOL)
        .sounds(cornSyrupBlockSound)
    val ROOT_BLOCK = Settings.create()
        .mapColor(MapColor.PODZOL)
        .instrument(NoteBlockInstrument.BASS)
        .strength(0.7f)
        .nonOpaque()
        .suffocates(Blocks::nonSolid)
        .blockVision(Blocks::nonSolid)
        .lavaIgnitable()
        .sounds(rootBlockSound)
    val WILD_WHEAT = Settings.create()
        .mapColor(MapColor.PLANT)
        .noCollision()
        .breakInstantly()
        .sounds(BlockSoundGroup.CROP)
        .offsetType(OffsetType.XZ)
        .pistonBehavior(PistonBehavior.DESTROY)
    val GOLDEN_BEETROOT = Settings.create()
        .mapColor(MapColor.GOLD)
        .noCollision()
        .ticksRandomly()
        .breakInstantly()
        .sounds(BlockSoundGroup.CROP)
        .pistonBehavior(PistonBehavior.DESTROY)

    // ☢ Experimental ☢
    val MOONCORE = Settings.create()
        .mapColor(MapColor.LIGHT_BLUE)
        .solid().nonOpaque()
        .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
        .strength(1.5f)
        .ticksRandomly()
        .luminance(15)
        .pistonBehavior(PistonBehavior.DESTROY)
    val REDSTONE_CRYSTAL = Settings.create()
        .mapColor(MapColor.RED)
        .solid().nonOpaque()
        .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
        .strength(1.5f)
        .ticksRandomly()
        .luminance(9)
        .pistonBehavior(PistonBehavior.DESTROY)
    val PAINTED_ROSE = Settings.create()
        .mapColor(MapColor.BLUE)
        .noCollision()
        .ticksRandomly()
        .breakInstantly()
        .offsetType(OffsetType.XZ)
        .sounds(BlockSoundGroup.GRASS)
        .pistonBehavior(PistonBehavior.DESTROY)
    val GOLDEN_MUSHROOM_BLOCK = copy(BROWN_MUSHROOM_BLOCK).mapColor(MapColor.GOLD)
    val GOLDEN_MUSHROOM = Settings.create()
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
