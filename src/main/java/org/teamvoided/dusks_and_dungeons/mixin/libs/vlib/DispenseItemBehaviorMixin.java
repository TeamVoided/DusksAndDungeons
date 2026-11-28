package org.teamvoided.dusks_and_dungeons.mixin.libs.vlib;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.impl.LightableRegistryImpl;

// TODO(vlib) move to voidlib
@Mixin(targets = "net.minecraft.core.dispenser.DispenseItemBehavior$8")
public class DispenseItemBehaviorMixin {

    @ModifyExpressionValue(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/CandleCakeBlock;canLight(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    boolean additionCanBeLitChecks(boolean original, @Local BlockState state) {
        return original || LightableRegistryImpl.canLight(state);
    }

}