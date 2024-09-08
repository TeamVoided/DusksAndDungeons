package org.teamvoided.dusk_autumn.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags;

@Mixin(MushroomPlantBlock.class)
public abstract class MushroomPlantBlockMixin extends AbstractPlantBlock implements Fertilizable {

    protected MushroomPlantBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At(value = "RETURN", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/tag/BlockTag;)Z", ordinal = 0), method = "canPlaceAt", cancellable = true)
    private void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir,
                            @Local(ordinal = 1) BlockState blockState, @Local(ordinal = 1) BlockPos blockPos) {
        if (blockState.isIn(BlockTags.MUSHROOM_GROW_BLOCK)) {
            cir.setReturnValue(sideCoversSmallSquare(world, pos.down(), Direction.UP));
        }
    }
}