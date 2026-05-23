package org.teamvoided.dusks_and_dungeons.mixin;

import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractCandleBlock.class)
public interface AbstractCandleBlockAccessor {
    @Invoker("addParticlesAndSound")
    static void invokeAddParticlesAndSound(Level world, Vec3 vec3d, RandomSource random) {
        throw new AssertionError();
    }
}
