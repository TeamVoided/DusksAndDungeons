package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.PinkPetalsBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags

class VivionbedBlock(settings: Properties) : PinkPetalsBlock(settings) {
    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean =
        floor.`is`(DnDBlockTags.SUPPORTS_VIVIONBED)
}