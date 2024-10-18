package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.block.piston.PistonHandler;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusk_autumn.DusksAndDungeons;
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks;

@Mixin(PistonHandler.class)
public abstract class PistonHandlerMixin {
    @Shadow
    private static boolean isBlockSticky(BlockState state) {
        return false;
    }

    @Inject(at = @At("HEAD"), method = "isBlockSticky ", cancellable = true)
    private static void isSyrupSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(DnDFloraBlocks.CORN_SYRUP_BLOCK))
            cir.setReturnValue(true);
    }

    @Inject(at = @At("HEAD"), method = "isAdjacentBlockStuck", cancellable = true)
    private static void isAdjacentBlockStuck(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
        if (isBlockSticky(state) && (state != adjacentState && isBlockSticky(adjacentState)))
            cir.setReturnValue(false);
    }
}