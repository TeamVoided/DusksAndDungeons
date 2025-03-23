package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.AbstractPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MushroomPlantBlock.class)
public abstract class MushroomPlantBlockMixin extends AbstractPlantBlock implements Fertilizable {

    protected MushroomPlantBlockMixin(Settings settings) {
        super(settings);
    }

    @ModifyExpressionValue(method = "canPlaceAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean canPlaceAt(boolean original, BlockState state, WorldView world, BlockPos pos) {
        return original && sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }
}