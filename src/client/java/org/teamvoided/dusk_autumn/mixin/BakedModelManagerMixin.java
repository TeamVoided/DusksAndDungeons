package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.util.ModelIdentifier;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.teamvoided.dusk_autumn.DusksAndDungeons.*;


@Mixin(BakedModelManager.class)
public class BakedModelManagerMixin {
    @Redirect(method = "method_45879", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"))
    private static void removeErrors(Logger logger, String s, Object id, Object model) {
        if (id instanceof ModelIdentifier mid && mid.id().getNamespace().equals(MODID) && isDev()) {
            log.info("Suppressed Model : {}", id);
        } else {
            logger.warn(s, id, model);
        }
    }
}
