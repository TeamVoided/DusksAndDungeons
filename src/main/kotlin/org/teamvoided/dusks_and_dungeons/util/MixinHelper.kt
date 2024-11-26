package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import org.teamvoided.dusks_and_dungeons.mixin.AbstractCandleBlockAccessor


fun World.spawnCandleParticles(vec3d: Vec3d, random: RandomGenerator) =
    AbstractCandleBlockAccessor.`dusks_and_dungeons$invokeSpawnCandleParticles`(this, vec3d, random)
