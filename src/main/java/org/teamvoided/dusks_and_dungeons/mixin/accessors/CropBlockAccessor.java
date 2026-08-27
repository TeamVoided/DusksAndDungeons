package org.teamvoided.dusks_and_dungeons.mixin.accessors;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CropBlock.class)
public interface CropBlockAccessor {

    @Invoker("hasSufficientLight")
    static boolean dnd_hasSufficientLight(LevelReader levelReader, BlockPos blockPos) {
        throw new UnsupportedOperationException();
    }

    @Invoker("getGrowthSpeed")
    static float dnd_getGrowthSpeed(Block block, BlockGetter blockGetter, BlockPos blockPos) {
        throw new UnsupportedOperationException();
    }

}