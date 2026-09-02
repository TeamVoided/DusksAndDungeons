package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.PinkPetalsBlock
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags

class VivionbedBlock(properties: Properties) : PinkPetalsBlock(properties) {

    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return floor.`is`(DnDBlockTags.SUPPORTS_VIVIONBED)
    }

}