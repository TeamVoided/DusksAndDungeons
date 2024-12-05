package org.teamvoided.dusks_and_dungeons.init.misc

import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks
import net.minecraft.block.MapColor
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.dusks_and_dungeons.util.block.luminance

val ICE_SETIN = copy(Blocks.ICE)

// ☢ Experimental ☢
val MOONCORE_SETIN = Settings.create()
    .mapColor(MapColor.LIGHT_BLUE)
    .solid().nonOpaque()
    .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
    .strength(1.5f)
    .ticksRandomly()
    .luminance(15)
    .pistonBehavior(PistonBehavior.DESTROY)
val REDSTONE_CRYSTAL_SETIN = Settings.create()
    .mapColor(MapColor.RED)
    .solid().nonOpaque()
    .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
    .strength(1.5f)
    .ticksRandomly()
    .luminance(9)
    .pistonBehavior(PistonBehavior.DESTROY)