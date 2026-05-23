package org.teamvoided.dusks_and_dungeons.mixin;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks;


// TODO depend on Taglighting to re implement
@Mixin(PistonStructureResolver.class)
public abstract class PistonStructureResolverMixin {
    @Shadow
    private static boolean isSticky(BlockState state) {
        return false;
    }

//    @Inject(at = @At("HEAD"), method = "isBlockSticky ", cancellable = true)
//    private static void isSyrupSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
//        if (state.is(DnDBlocks.CORN_SYRUP_BLOCK))
//            cir.setReturnValue(true);
//    }
//
//    @Inject(at = @At("HEAD"), method = "canStickToEachOther", cancellable = true)
//    private static void isAdjacentBlockStuck(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
//        if (isSticky(state) && (state != adjacentState && isSticky(adjacentState)))
//            cir.setReturnValue(false);
//    }
}