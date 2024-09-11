package org.teamvoided.dusk_autumn.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Util.class)
public class UtilMixin {
    @Inject(method = "getChoiceTypeInternal", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V", shift = At.Shift.AFTER), cancellable = true)
    private static void getChoiceTypeInternal(DSL.TypeReference typeReference, String id, CallbackInfoReturnable<Type<?>> cir,
                                              @Local Type<?> x) {
        cir.setReturnValue(x);
    }
}