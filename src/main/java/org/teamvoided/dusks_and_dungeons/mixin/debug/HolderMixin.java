package org.teamvoided.dusks_and_dungeons.mixin.debug;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.Holder;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Holder.class)
public interface HolderMixin<T> {

    @Shadow
    T value();

    @ModifyExpressionValue(method = "getRegisteredName", at = @At(value = "INVOKE", target = "Ljava/util/Optional;orElse(Ljava/lang/Object;)Ljava/lang/Object;"))
    default <Y> Y moreClearHolders(@Nullable Y original) {
        //noinspection unchecked
        return (Y) ("[unregistered:" + value().getClass().getSimpleName() + "]");
    }
}
