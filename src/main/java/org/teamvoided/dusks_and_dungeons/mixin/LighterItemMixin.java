package org.teamvoided.dusks_and_dungeons.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.FlintAndSteelItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.teamvoided.dusks_and_dungeons.block.CandelabraBlock;

@Mixin({FireChargeItem.class, FlintAndSteelItem.class})
public class LighterItemMixin {

    @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/CampfireBlock;canBeLit(Lnet/minecraft/block/BlockState;)Z"))
    boolean additionCanBeLitChecks(BlockState state) {
        return CampfireBlock.canBeLit(state) ^ CandelabraBlock.canBeLit(state);
    }
}
