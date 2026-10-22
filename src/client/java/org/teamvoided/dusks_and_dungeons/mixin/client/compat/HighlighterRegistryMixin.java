package org.teamvoided.dusks_and_dungeons.mixin.client.compat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.client.compat.BorderHighlighter;
import xaero.map.highlight.HighlighterRegistry;

@Pseudo
@Mixin(HighlighterRegistry.class)
public abstract class HighlighterRegistryMixin {

    @Inject(method = "end", at = @At("HEAD"))
    private void registerCustomHighlight(CallbackInfo ci) {
        HighlighterRegistry registry = (HighlighterRegistry) (Object) this;
//        if (!registry.getHighlighters().contains(BorderHighlighter.INSTANCE)) {
//            registry.register(BorderHighlighter.INSTANCE);
//        }
    }

}