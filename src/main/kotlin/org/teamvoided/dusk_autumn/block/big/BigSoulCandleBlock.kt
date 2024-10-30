package org.teamvoided.dusk_autumn.block.big

import net.minecraft.block.AbstractCandleBlock
import net.minecraft.block.BlockState
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.init.DnDParticles
import java.util.function.Consumer

class BigSoulCandleBlock(settings: Settings) : BigCandleBlock(settings) {
    override val particle = ParticleTypes.SOUL_FIRE_FLAME
}
