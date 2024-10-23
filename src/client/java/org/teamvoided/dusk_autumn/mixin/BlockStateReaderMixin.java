package org.teamvoided.dusk_autumn.mixin;

import net.minecraft.unmapped.C_xecqvems;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;

import static org.teamvoided.dusk_autumn.DusksAndDungeons.*;

@Mixin(C_xecqvems.class)
public class BlockStateReaderMixin {
    @Unique
    private static final ArrayList<Identifier> TRACKED_MODELS = new ArrayList<>();

    @Redirect(method = "method_61066", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", ordinal = 0))
    void removeErrors(Logger logger, String s, Object idO, Object val) {
        if (idO instanceof Identifier id && id.getNamespace().equals(MODID)) {
            if (!TRACKED_MODELS.contains(id) && isDev()) {
                log.info("Suppressed Model : {}", id);
                TRACKED_MODELS.add(id);
            }
        } else {
            logger.warn(s, idO, val);
        }
    }

}
