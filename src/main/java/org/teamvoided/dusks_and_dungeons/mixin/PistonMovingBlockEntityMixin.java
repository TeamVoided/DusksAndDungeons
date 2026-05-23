package org.teamvoided.dusks_and_dungeons.mixin;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks;

// TODO depend on Taglighting to re implement
@Mixin(PistonMovingBlockEntity.class)
public abstract class PistonMovingBlockEntityMixin extends BlockEntity {
    @Shadow
    private BlockState movedState;

    public PistonMovingBlockEntityMixin(BlockEntityType type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.movedState = Blocks.AIR.defaultBlockState();
    }
/*
    @Inject(at = @At("HEAD"), method = "isMovingHoneyBlock ", cancellable = true)
    private void isMovingSyrupBlock(CallbackInfoReturnable<Boolean> cir) {
        if (this.movedState.is(DnDBlocks.CORN_SYRUP_BLOCK))
            cir.setReturnValue(true);
    }*/
}