package org.teamvoided.dusk_autumn.block.rocky

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World

class RockyMyceliumBlock(dirt: Block, settings: Settings) :
    RockySpreadableBlock(Blocks.MYCELIUM, dirt, settings) {

    public override fun getCodec(): MapCodec<RockyMyceliumBlock> {
        return CODEC
    }

    override fun randomDisplayTick(state: BlockState?, world: World, pos: BlockPos, random: RandomGenerator) {
        super.randomDisplayTick(state, world, pos, random)
        if (random.nextInt(10) == 0) {
            world.addParticle(
                ParticleTypes.MYCELIUM,
                pos.x.toDouble() + random.nextDouble(),
                pos.y.toDouble() + 1.1,
                pos.z.toDouble() + random.nextDouble(),
                0.0,
                0.0,
                0.0
            )
        }
    }

    companion object {
        val CODEC: MapCodec<RockyMyceliumBlock> = createCodec { settings: Settings ->
            RockyMyceliumBlock(
                Blocks.DIRT,
                settings
            )
        }
    }
}