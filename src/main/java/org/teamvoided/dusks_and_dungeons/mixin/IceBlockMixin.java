package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.block.meltable.Meltable;

@Mixin(HalfTransparentBlock.class)
public abstract class IceBlockMixin {

    @ModifyReturnValue(method = "skipRendering", at = @At("RETURN"))
    boolean makeIceCullWithCustom(boolean original, BlockState state, BlockState neighborState, Direction direction) {
        //noinspection ConstantValue
        return (((Object) this instanceof IceBlock) && Meltable.shouldCullFace(state, neighborState, direction)) || original;
    }

}
