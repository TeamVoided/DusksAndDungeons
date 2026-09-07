package org.teamvoided.dusks_and_dungeons.mixin.accessors;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.TorchBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TorchBlock.class)
public interface TorchBlockAccessor {

    @Accessor("flameParticle")
    SimpleParticleType dnd_fameParticle();

}