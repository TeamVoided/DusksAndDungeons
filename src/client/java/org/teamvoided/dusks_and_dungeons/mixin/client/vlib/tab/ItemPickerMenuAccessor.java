package org.teamvoided.dusks_and_dungeons.mixin.client.vlib.tab;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import static net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen.*;

@Mixin(ItemPickerMenu.class)
public interface ItemPickerMenuAccessor {

    @Invoker("getRowIndexForScroll")
    int dnd_getRowIndexForScroll(float f);

}