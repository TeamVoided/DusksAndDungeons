package org.teamvoided.dusks_and_dungeons.mixin.debug;


import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(MappedRegistry.class)
public abstract class MappedRegistryMixin<T> {

    @Shadow
    @Final
    ResourceKey<? extends Registry<T>> key;

    @Shadow
    @Final
    private Map<ResourceLocation, Holder.Reference<T>> byLocation;

    @Shadow
    @Final
    private Map<T, Holder.Reference<T>> byValue;

    @Shadow
    @Final
    private static Logger LOGGER;

    @Inject(method = "register", at = @At(value = "INVOKE", target = "Ljava/util/Map;containsKey(Ljava/lang/Object;)Z", ordinal = 0))
    void throwOrWarn(ResourceKey<T> resourceKey, T object, RegistrationInfo registrationInfo, CallbackInfoReturnable<Holder.Reference<T>> cir) {
        if (byLocation.containsKey(resourceKey.location())) {
            throwInDev("Adding duplicate key '" + resourceKey.location() + "' to registry '" + key.location() + "'");
        }
        if (byValue.containsKey(object)) {
            throwInDev("Adding duplicate value [original: '" + object + "', duplicate: '" + resourceKey.location() + "'] to registry '" + key.location() + "'");
        }
    }

    @Unique
    private static void throwInDev(String message) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            throw new IllegalStateException(message);
        }
        LOGGER.error(message);
    }

}
