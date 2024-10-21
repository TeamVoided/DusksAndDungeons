package org.teamvoided.dusk_autumn.util

import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.mixin.AbstractCandleBlockAccessor


fun spawnCandleParticles(world: World, vec3d: Vec3d, random: RandomGenerator) =
    AbstractCandleBlockAccessor.invokeSpawnCandleParticles(world, vec3d, random)
