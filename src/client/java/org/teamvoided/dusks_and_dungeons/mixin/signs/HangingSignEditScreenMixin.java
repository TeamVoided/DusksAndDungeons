package org.teamvoided.dusks_and_dungeons.mixin.signs;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.HangingSignEditScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.util.SignColorProvider;

import static org.teamvoided.dusks_and_dungeons.util.GuiGraphicsHelpersKt.blit;

@Mixin(HangingSignEditScreen.class)
public abstract class HangingSignEditScreenMixin implements SignColorProvider {

    @WrapOperation(
            method = "renderSignBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V")
    )
    void renderTinted(GuiGraphics gui, ResourceLocation texture, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight, Operation<Void> original) {
        var color = dnd_getSignColor();
        if (color != null) {
            blit(gui, texture, x, y, uOffset, vOffset, width, height, textureWidth, textureHeight, FastColor.ARGB32.opaque(color));
        } else {
            original.call(gui, texture, x, y, uOffset, vOffset, width, height, textureWidth, textureHeight);
        }
    }

}
