package org.teamvoided.dusk_autumn.mixin;

import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags;

@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlock implements ItemConvertible, FabricBlock {

    protected BlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "cannotConnect", cancellable = true)
    private static void cannotConnectTag(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(DnDBlockTags.BLOCKS_CANNOT_CONNECT_TO))
                cir.setReturnValue(false);
    }
}