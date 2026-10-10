/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.FallingBlock
import net.minecraft.world.level.block.state.BlockState

class FallingWaterColorBlock(properties: Properties) : FallingBlock(properties) {

    public override fun codec(): MapCodec<FallingWaterColorBlock> = CODEC

    override fun getDustColor(blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos): Int {
        val biome = blockGetter.getBiomeFabric(blockPos)
        if (biome != null) return biome.value().waterColor
        return 4159204
    }

    companion object {
        val CODEC: MapCodec<FallingWaterColorBlock> = simpleCodec(::FallingWaterColorBlock)
    }
}