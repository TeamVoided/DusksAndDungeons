package org.teamvoided.dusks_and_dungeons.mixin.client.vlib.tab;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.client.gui.screens.inventory.TabSectionController;

@Mixin(CreativeModeInventoryScreen.ItemPickerMenu.class)
public abstract class ItemPickerMenuMixin {

    @ModifyReturnValue(method = "canScroll", at = @At("RETURN"))
    boolean allowScrollInSections(boolean original) {
        return (TabSectionController.hasSections) ? TabSectionController.maxRows > 5 : original;
    }

    @ModifyReturnValue(method = "calculateRowCount", at = @At("RETURN"))
    int calcSectionRowCount(int original) {
        return (TabSectionController.hasSections) ? TabSectionController.maxRows - 5 : original;
    }

}