package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.entity.passive.FoxEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(FoxEntity.Variant.class)
public class FoxEntityVariantMixin {
    @Mutable
    @Shadow
    @Final
    private static FoxEntity.Variant[] field_18003;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clInit(CallbackInfo ci) {
        register("SILVER", 2 , "silver");
    }

    @Invoker("<init>")
    private static FoxEntity.Variant invokeInit(String name, int id, int id2, String typeName) {
        throw new AssertionError();
    }

    @Unique
    private static FoxEntity.Variant register(String name, int id, String typeName) {
        ArrayList<FoxEntity.Variant> values = new ArrayList<>(Arrays.asList(field_18003));
        FoxEntity.Variant type = invokeInit(name, values.get(values.size() - 1).ordinal() + 1, id, typeName);
        values.add(type);
        field_18003 = values.toArray(new FoxEntity.Variant[]{});
        return type;
    }
}
