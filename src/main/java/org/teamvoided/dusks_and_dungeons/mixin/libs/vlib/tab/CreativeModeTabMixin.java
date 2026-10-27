package org.teamvoided.dusks_and_dungeons.mixin.libs.vlib.tab;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.voidlib.api.tab.TabSection;
import org.teamvoided.voidlib.api.tab.TabSectionRegistry;

import java.util.Collection;
import java.util.Set;

@Mixin(CreativeModeTab.class)
public class CreativeModeTabMixin {

    @Shadow
    private Collection<ItemStack> displayItems;

    @Shadow
    private Set<ItemStack> displayItemsSearchTab;

    @Inject(method = "buildContents", at = @At("RETURN"))
    void buildSections(CreativeModeTab.ItemDisplayParameters params, CallbackInfo ci, @Local ResourceKey<CreativeModeTab> key) {
        var tabSections = TabSectionRegistry.getSections(key);
        if (!tabSections.isEmpty()) {
            displayItems.clear();
            displayItemsSearchTab.clear();
            for (TabSection section : tabSections) {
                section.buildContents(params);
                displayItems.addAll(section.contents());
                displayItemsSearchTab.addAll(section.searchContents());
            }
        }
    }

}