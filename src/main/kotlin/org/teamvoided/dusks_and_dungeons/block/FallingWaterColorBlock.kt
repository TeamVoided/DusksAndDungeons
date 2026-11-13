package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.FallingBlock
import net.minecraft.world.level.block.state.BlockState

class FallingWaterColorBlock(properties: Properties) : FallingBlock(properties) {

    public override fun codec(): MapCodec<FallingWaterColorBlock> = CODEC

    override fun getDustColor(state: BlockState, level: BlockGetter, pos: BlockPos): Int {
        return level.getBiomeFabric(pos)?.value()?.waterColor ?: FALLBACK_COLOR
    }

    companion object {

        // Here in case I did a bad:
        // 4159204
        const val FALLBACK_COLOR = 0x3F76E4

        val CODEC: MapCodec<FallingWaterColorBlock> = simpleCodec(::FallingWaterColorBlock)

    }
}