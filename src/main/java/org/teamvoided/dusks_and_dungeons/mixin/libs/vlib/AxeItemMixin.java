package org.teamvoided.dusks_and_dungeons.mixin.libs.vlib;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

import static org.teamvoided.voidlib.impl.BlockStrippingRegistryIml.getPossibleStrippedState;

// TODO(lib) move to voidlib
@Mixin(AxeItem.class)
public abstract class AxeItemMixin {

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @ModifyReturnValue(method = "getStripped", at = @At("RETURN"))
    Optional<BlockState> addCustomStripping(Optional<BlockState> original, BlockState originalState) {
        return getPossibleStrippedState(original, originalState);
    }

}