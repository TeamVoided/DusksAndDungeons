package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.PinkPetalsBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags

class VivionbedBlock(settings: Settings) : PinkPetalsBlock(settings) {
    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return floor.isIn(DnDBlockTags.VIVIONBED_PLACEABLE)
    }
}