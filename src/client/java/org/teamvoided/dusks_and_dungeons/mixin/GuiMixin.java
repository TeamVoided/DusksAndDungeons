package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.DnDGui;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Shadow
    protected abstract void renderTextureOverlay(GuiGraphics guiGraphics, ResourceLocation resourceLocation, float f);

    @Inject(method = "renderCameraOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    void renderPumpkinOverlays(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, @Local ItemStack stack) {
        var resourceLocation = DnDGui.carvedOverlay(stack.getItem());
        if (resourceLocation != null) {
            this.renderTextureOverlay(guiGraphics, resourceLocation, 1F);
        }
    }
}
