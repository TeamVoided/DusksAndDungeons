
package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.BeetrootBlock
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.init.DnDItems

class GoldenBeetrootsBlock(properties: Properties) : BeetrootBlock(properties) {
    
    override fun getBaseSeedId(): ItemLike = DnDItems.GOLDEN_BEETROOT
    
    override fun isValidBonemealTarget(leve: LevelReader, pos: BlockPos, state: BlockState): Boolean = false
    
    override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (random.nextInt(2) != 0) {
            super.randomTick(state, level, pos, random)
        }
    }
    
}