package org.teamvoided.dusks_and_dungeons.block.rocky

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World

class RockyMyceliumBlock(dirt: Block, settings: Settings) :
    RockySpreadableBlock(Blocks.MYCELIUM, dirt, settings) {
    public override fun getCodec(): MapCodec<RockyMyceliumBlock> = CODEC
    override fun randomDisplayTick(state: BlockState?, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
        if (random.nextInt(10) == 0) world.addParticle(
            ParticleTypes.MYCELIUM,
            pos.x.toDouble() + random.nextDouble(), pos.y.toDouble() + 1.1, pos.z.toDouble() + random.nextDouble(),
            0.0, 0.0, 0.0
        )
    }

    companion object {
        val CODEC = createCodec { RockyMyceliumBlock(Blocks.DIRT, it) }
    }
}