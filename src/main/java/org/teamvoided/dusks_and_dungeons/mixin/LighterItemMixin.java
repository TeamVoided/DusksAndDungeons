package org.teamvoided.dusks_and_dungeons.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.FlintAndSteelItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_and_dungeons.block.CandelabraBlock;

// TODO add a void lib reg for this
@Mixin({FireChargeItem.class, FlintAndSteelItem.class})
public class LighterItemMixin {

    @ModifyExpressionValue(method = "useOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/CampfireBlock;canLight(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    boolean additionCanBeLitChecks(boolean original, @Local BlockState state) {
        return original ^ CandelabraBlock.canLiteCandelabra(state);
    }
}
