package org.teamvoided.dusk_autumn.mixin;

import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags;
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks;

@Mixin(PistonBlockEntity.class)
public abstract class PistonBlockEntityMixin extends BlockEntity {

    @Shadow private BlockState movedState;

    public PistonBlockEntityMixin(BlockEntityType type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.movedState = Blocks.AIR.getDefaultState();
    }

    @Inject(at = @At("HEAD"), method = "isMovingHoneyBlock ", cancellable = true)
    private void isMovingSyrupBlock(CallbackInfoReturnable<Boolean> cir) {
        if (this.movedState.isOf(DnDFloraBlocks.CORN_SYRUP_BLOCK))
            cir.setReturnValue(true);
    }
}