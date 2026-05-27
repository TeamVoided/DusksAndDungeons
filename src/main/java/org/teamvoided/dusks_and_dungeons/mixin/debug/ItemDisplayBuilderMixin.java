package org.teamvoided.dusks_and_dungeons.mixin.debug;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons;

@Mixin(targets = "net.minecraft.world.item.CreativeModeTab$ItemDisplayBuilder")
public class ItemDisplayBuilderMixin {

    @Shadow
    @Final
    private CreativeModeTab tab;

    @Inject(method = "accept", at = @At(value = "INVOKE", target = "Ljava/lang/IllegalArgumentException;<init>(Ljava/lang/String;)V"))
    void logBigStack(ItemStack itemStack, CreativeModeTab.TabVisibility tabVisibility, CallbackInfo ci) {
        DusksAndDungeons.log.info("Tab[{}] has faulty Stack: {}, Count: {}", tab.getDisplayName().getString(), itemStack.getDisplayName().getString(), itemStack.getCount());
    }
}
