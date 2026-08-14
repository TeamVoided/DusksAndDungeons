package org.teamvoided.dusks_and_dungeons.mixin.accessors;

import net.minecraft.Util;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Util.class)
public interface UtilAccessor {

    @Accessor("LOGGER")
    static Logger dnd_logger() {
        throw new IllegalStateException("Mixin Failed");
    }
}
