package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.teamvoided.dusk_autumn.DusksAndDungeons.*;

@Mixin(ModelLoader.class)
public class ModelLoaderMixin {
    @Redirect(method = "getOrLoadModel", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;[Ljava/lang/Object;)V"))
    void removeErrors(org.slf4j.Logger logger, String format, Object... args) {
        if (args[0] instanceof Identifier id && id.getNamespace().equals(MODID)) {
            if (isDev()) log.info("Suppressed Model : {}", id);
        } else {
            logger.warn(format, args);
        }
    }
}
