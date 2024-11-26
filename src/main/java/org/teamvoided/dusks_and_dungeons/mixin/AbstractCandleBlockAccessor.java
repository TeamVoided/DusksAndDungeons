package org.teamvoided.dusks_and_dungeons.mixin;

import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractCandleBlock.class)
public interface AbstractCandleBlockAccessor {
    @Invoker("spawnCandleParticles")
    static void dusks_and_dungeons$invokeSpawnCandleParticles(World world, Vec3d vec3d, RandomGenerator random) {
        throw new AssertionError();
    }
}
