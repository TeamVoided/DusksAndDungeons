package org.teamvoided.dusks_and_dungeons.mixin.client.vlib.tab;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.client.gui.screens.inventory.TabSectionController;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin extends EffectRenderingInventoryScreen<CreativeModeInventoryScreen.ItemPickerMenu> {

    @Shadow
    private float scrollOffs;

    public CreativeModeInventoryScreenMixin(CreativeModeInventoryScreen.ItemPickerMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    @Inject(method = "selectTab", at = @At("HEAD"))
    void refreshSelection(CreativeModeTab tab, CallbackInfo ci) {
        TabSectionController.updateTab(tab);
    }

    @Inject(method = "selectTab", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;addAll(Ljava/util/Collection;)Z", ordinal = 1))
    void refreshItems(CreativeModeTab tab, CallbackInfo ci) {
        if (TabSectionController.hasSections) {
            menu.items.clear();
            menu.items.addAll(TabSectionController.items);
        }
    }

    @Inject(method = "renderBg", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CreativeModeTab;getType()Lnet/minecraft/world/item/CreativeModeTab$Type;"))
    void renderSectionData(GuiGraphics guiGraphics, float tickDelta, int x, int y, CallbackInfo ci) {
        if (TabSectionController.hasSections) {
            var posStack = guiGraphics.pose();
            posStack.pushPose();
            posStack.translate(leftPos, topPos, 0f);
            TabSectionController.render(guiGraphics, x, y, tickDelta, ((ItemPickerMenuAccessor) menu).dnd_getRowIndexForScroll(scrollOffs));
            posStack.popPose();
        }
    }

}