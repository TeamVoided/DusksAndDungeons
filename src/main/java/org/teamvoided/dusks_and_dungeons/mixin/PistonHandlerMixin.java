package org.teamvoided.dusks_and_dungeons.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks;


// TODO depend on Taglighting
@Mixin(PistonHandler.class)
public abstract class PistonHandlerMixin {
    @Shadow
    private static boolean isBlockSticky(BlockState state) {
        return false;
    }

    @Inject(at = @At("HEAD"), method = "isBlockSticky ", cancellable = true)
    private static void isSyrupSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(DnDBlocks.CORN_SYRUP_BLOCK))
            cir.setReturnValue(true);
    }

    @Inject(at = @At("HEAD"), method = "isAdjacentBlockStuck", cancellable = true)
    private static void isAdjacentBlockStuck(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
        if (isBlockSticky(state) && (state != adjacentState && isBlockSticky(adjacentState)))
            cir.setReturnValue(false);
    }
}