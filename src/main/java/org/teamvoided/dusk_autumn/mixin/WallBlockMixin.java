package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags;

@Debug(export = true)
@Mixin(WallBlock.class)
public abstract class WallBlockMixin extends Block implements Waterloggable {

    protected WallBlockMixin(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "shouldConnectTo", cancellable = true)
    private void connectToWoodenOrRegular(BlockState state, boolean faceFullSquare, Direction side, CallbackInfoReturnable<Boolean> cir) {
        if (this.getDefaultState().isIn(DnDBlockTags.WOODEN_WALLS)) {
            if (state.isIn(DnDBlockTags.WOODEN_WALLS))
                cir.setReturnValue(true);
            else if (state.isIn(BlockTags.WALLS))
                cir.setReturnValue(false);
        }
    }
}