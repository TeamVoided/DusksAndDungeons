package org.teamvoided.dusks_and_dungeons.block.rocky

import com.mojang.serialization.MapCodec
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level

class RockyMyceliumBlock(dirt: Block, settings: Properties) :
    RockySpreadableBlock(Blocks.MYCELIUM, dirt, settings) {
    public override fun codec(): MapCodec<RockyMyceliumBlock> = CODEC
    override fun animateTick(state: BlockState?, world: Level, pos: BlockPos, random: RandomSource) {
        super.animateTick(state, world, pos, random)
        if (random.nextInt(10) == 0) world.addParticle(
            ParticleTypes.MYCELIUM,
            pos.x.toDouble() + random.nextDouble(), pos.y.toDouble() + 1.1, pos.z.toDouble() + random.nextDouble(),
            0.0, 0.0, 0.0
        )
    }

    companion object {
        val CODEC = simpleCodec { RockyMyceliumBlock(Blocks.DIRT, it) }
    }
}