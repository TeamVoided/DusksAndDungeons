package org.teamvoided.dusks_and_dungeons.mixin;

import net.minecraft.world.entity.animal.Fox;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;

@SuppressWarnings({"SameParameterValue", "deprecation"})
@Mixin(Fox.Type.class)
public class FoxEntityVariantMixin {
    // TODO replace with with mixin extentions
    @Mutable
    @Shadow
    @Final
    private static Fox.Type[] $VALUES;
    @Mutable
    @Shadow
    @Final
    public static StringRepresentable.EnumCodec<Fox.Type> CODEC;
    @Mutable
    @Shadow
    @Final
    private static IntFunction<Fox.Type> BY_ID;


    @Inject(method = "byBiome", at = @At("HEAD"), cancellable = true)
    private static void fixBiomeSpawning(Holder<Biome> biome, CallbackInfoReturnable<Fox.Type> cir) {
        int id = 0;
        if (biome.is(BiomeTags.SPAWNS_SNOW_FOXES)) id = 1;
        else if (biome.is(DnDBiomeTags.SPAWNS_SILVER_FOXES)) id = 2;

        cir.setReturnValue(Fox.Type.byId(id));
    }

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clInit(CallbackInfo ci) {
        register("SILVER", 2, "silver");
        CODEC = StringRepresentable.fromEnum(() -> $VALUES);
        BY_ID = ByIdMap.continuous(Fox.Type::getId, $VALUES, ByIdMap.OutOfBoundsStrategy.ZERO);
    }

    @Invoker("<init>")
    private static Fox.Type invokeInit(String name, int id, int id2, String typeName) {
        throw new AssertionError();
    }


    @SuppressWarnings({"UnusedReturnValue", "SequencedCollectionMethodCanBeUsed"})
    @Unique
    private static Fox.Type register(String name, int id, String typeName) {
        ArrayList<Fox.Type> values = new ArrayList<>(Arrays.asList($VALUES));
        Fox.Type type = invokeInit(name, values.get(values.size() - 1).ordinal() + 1, id, typeName);
        values.add(type);
        $VALUES = values.toArray(new Fox.Type[]{});
        return type;
    }
}