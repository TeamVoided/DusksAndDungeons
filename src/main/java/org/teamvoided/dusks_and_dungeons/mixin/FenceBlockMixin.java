package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.block.not_blocks.BlockConnection;

@Mixin(FenceBlock.class)
public abstract class FenceBlockMixin {

    @ModifyReturnValue(method = "connectsTo", at = @At("RETURN"))
    private boolean connectToWoodenOrRegular(boolean original, BlockState state, @Local(argsOnly = true) Direction dir) {
        return original || (state instanceof BlockConnection && ((BlockConnection) state).fencesConnect(state, dir));
    }

}