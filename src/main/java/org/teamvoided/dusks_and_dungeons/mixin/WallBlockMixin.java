package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags;

@Mixin(WallBlock.class)
public abstract class WallBlockMixin extends Block implements Waterloggable {

    protected WallBlockMixin(AbstractBlock.Settings settings) {
        super(settings);
    }

    @ModifyReturnValue(method = "shouldConnectTo", at = @At("RETURN"))
    private boolean connectToWoodenOrRegular(boolean original, BlockState state) {
        if (this.getDefaultState().isIn(DnDBlockTags.WOODEN_WALLS)) {
            if (state.isIn(DnDBlockTags.WOODEN_WALLS)) return true;
            else if (state.isIn(BlockTags.WALLS)) return false;
        }
        return original;
    }
}