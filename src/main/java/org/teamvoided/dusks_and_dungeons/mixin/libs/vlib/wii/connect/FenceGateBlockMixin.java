package org.teamvoided.dusks_and_dungeons.mixin.libs.vlib.wii.connect;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voidlib.api.BlockConnection;

// TODO(lib) move to voidlib
@Mixin(FenceGateBlock.class)
public abstract class FenceGateBlockMixin {

    @ModifyReturnValue(method = "isWall", at = @At("RETURN"))
    private boolean blockConnectionCheck(boolean original, BlockState state) {
        return original || (state.getBlock() instanceof BlockConnection connection && connection.allowGateInWallState(state));
    }

}