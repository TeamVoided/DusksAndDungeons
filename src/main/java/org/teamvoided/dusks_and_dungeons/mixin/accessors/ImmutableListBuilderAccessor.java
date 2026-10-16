package org.teamvoided.dusks_and_dungeons.mixin.accessors;

import com.google.common.collect.ImmutableList.Builder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Builder.class)
public interface ImmutableListBuilderAccessor {

    @Accessor("contents")
    Object[] dnd_contents();

}
