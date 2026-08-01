package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.world.phys.Vec3
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.mixin.accessors.AbstractCandleBlockAccessor


fun Level.spawnCandleParticles(vec3d: Vec3, random: RandomSource) =
    AbstractCandleBlockAccessor.dnd_addParticlesAndSound(this, vec3d, random)
