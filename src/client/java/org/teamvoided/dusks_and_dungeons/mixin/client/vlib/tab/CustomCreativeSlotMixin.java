package org.teamvoided.dusks_and_dungeons.mixin.client.vlib.tab;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.teamvoided.dusks_and_dungeons.client.gui.screens.inventory.TabSectionController;

@Mixin(targets = "net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen$CustomCreativeSlot")
public abstract class CustomCreativeSlotMixin extends Slot {

    public CustomCreativeSlotMixin(Container container, int i, int j, int k) {
        super(container, i, j, k);
    }

    @Override
    public boolean isActive() {
        if (TabSectionController.hasSections) {
            int idx = Math.floorDiv(getContainerSlot(), 9) + TabSectionController.scrollOffset;
            return TabSectionController.sections.get(idx) == null;
        }

        return super.isActive();
    }

}