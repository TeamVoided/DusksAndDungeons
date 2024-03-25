package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.registry.Holder;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.collection.IdListUtil;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusk_autumn.data.DuskBiomeTags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;

@Mixin(FoxEntity.Variant.class)
public class FoxEntityVariantMixin {
    @Mutable
    @Shadow
    @Final
    private static FoxEntity.Variant[] field_18003;
    @Mutable
    @Shadow
    @Final
    private static StringIdentifiable.EnumCodec<FoxEntity.Variant> CODEC;
    @Mutable
    @Shadow
    @Final
    private static IntFunction<FoxEntity.Variant> VARIANTS;


    @Inject(method = "fromBiome", at = @At("HEAD"), cancellable = true)
    private static void fixBiomeSpawning(Holder<Biome> biome, CallbackInfoReturnable<FoxEntity.Variant> cir) {
        int x = 0;
        if (biome.isIn(BiomeTags.SPAWNS_SNOW_FOXES)) x = 1;
        else if (biome.isIn(DuskBiomeTags.SPAWNS_SILVER_FOXES)) x = 2;

        cir.setReturnValue(FoxEntity.Variant.get(x));
    }

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clInit(CallbackInfo ci) {
        register("SILVER", 2, "silver");
        CODEC = StringIdentifiable.createCodec(() -> field_18003);
        VARIANTS = IdListUtil.sortArray(FoxEntity.Variant::getId, field_18003, IdListUtil.OutOfBoundsHandler.ZERO);
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