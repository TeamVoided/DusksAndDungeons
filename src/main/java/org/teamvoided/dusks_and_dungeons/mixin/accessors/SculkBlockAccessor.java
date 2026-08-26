package org.teamvoided.dusks_and_dungeons.mixin.accessors;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SculkBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SculkBlock.class)
public interface SculkBlockAccessor {

    @Invoker("getRandomGrowthState")
    BlockState dnd_getRandomGrowthState(LevelAccessor levelAccessor, BlockPos blockPos, RandomSource randomSource, boolean bl);

}