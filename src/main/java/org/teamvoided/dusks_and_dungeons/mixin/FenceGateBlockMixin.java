package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.block.not_blocks.BlockConnection;

@Mixin(FenceGateBlock.class)
public abstract class FenceGateBlockMixin {

    @ModifyReturnValue(method = "isWall", at = @At("RETURN"))
    private boolean connectToWoodenOrRegular(boolean original, BlockState state) {
        return original || (state instanceof BlockConnection && ((BlockConnection) state).gateInWall(state));
    }

}